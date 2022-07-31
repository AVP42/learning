package info.wufc.learning.java_basic.io.socketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * java NIO中的网络通道才是非阻塞IO的实现，基于事件驱动。
 * 适用于需要维持大量链接，数据交换量不大的情况，比如一些即时通信的服务（轻操作）
 *
 * java AIO 则适用于连接数较多，且连接时间长的场景（重操作）
 *
 * 能够检测多个注册的通道上是否有时间发生，如果有时间发送，便获取时间然后针对每个事件进行相应的处理
 * 即用一个线程取管理多个通道，也就是管理多个连接
 * ServerSocketChannel 也是一个channel，用于处理accept事件的channel
 *              调用accept之后会返回这个链接的SocketChannel，也需要再次注册到服务端的selector中
 * SocketChannel 也是一个channel，用于处理读写事件的channel
 */
public class SocketChannelDemo {
    // 方式1：client不使用selector
    static class NIOClient1{
        public static void main(String[] args) throws IOException {
            // 打开channel，设置channel为非阻塞
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            // channel链接服务端
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
            // 如果是非阻塞，则connect方法返回false
            if (!channel.connect(address)) {
                // 如果是非阻塞的方式，可以使用channel.finishConnect()来判断是否已经建立链接
                while(!channel.finishConnect()){
                    System.out.println("Client连接服务端的同时，还可以做其他事情");
                }
            }
            String message = "hello server";
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
            channel.write(buffer);
            // 用于阻止client关闭
            System.in.read();
        }
    }

    // 方式1：client使用selector
    static class NIOClient2{
        public static void main(String[] args) throws IOException {
            // 1. 打开channel，设置channel为非阻塞
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            // 2. channel链接服务端
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
            channel.connect(address);
            // 3. 创建selector
            Selector selector = Selector.open();
            // 4. 注册channel到selector，并对connect事件感兴趣，即即将完成链接建立或者有异常出现
            channel.register(selector, SelectionKey.OP_CONNECT);

            while(true){
                if(selector.select(2000)==0){
                    System.out.println("Client: 没有准备好的事件，可以干点别的事");
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    // isConnectable 判断是否已经建立好链接或者有异常出现，但是channel的整个连接过程还没有结束，需要channel.finishConnect来结束
                    if(key.isConnectable()){
                        SocketChannel sc = (SocketChannel) key.channel();
                        // 需要finishConnect
                        if(channel.isConnectionPending()){
                            // 在非阻塞模式下，如果已经建立了连接，返回true，否则返回false，且不会阻塞
                            channel.finishConnect();
                        }
                        ByteBuffer buffer = ByteBuffer.wrap("hello server".getBytes(StandardCharsets.UTF_8));
                        sc.write(buffer);
                        key.interestOps(SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len = sc.read(buffer);
                        if(len != -1){
                            System.out.println("Client: 接受到服务端的消息" + new String(buffer.array(), 0, len, StandardCharsets.UTF_8));
                        }
                    }
                }
            }
        }
    }

    static class NIOServer{

        public static void main(String[] args) throws IOException {
            // 1. 创建用于处理accept事件的channel
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            // 2. channel绑定到socket  亦可通过这种方式链接 ssc.socket().bind(address);
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
            ssc.bind(address);
            // 3. 创建selector
            Selector selector = Selector.open();
            // 4. 将channel注册到selecor中，并且只对accept事件感兴趣
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            // 服务端一直工作
            while(true){
                // selector.select返回客户端的通道数 timeout表示阻塞的时间
                if(selector.select(2000) == 0){
                    System.out.println("Server: 没有准备好的事件，可以干点别的事");
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    // 需要移除这个key，新的事件会采用新的key注册到selector上
                    iterator.remove();
                    handle(key);
                }
            }
        }

        private static void handle(SelectionKey key) throws IOException {
            if(!key.isValid()){
                System.out.println("key is invalid");
            }
            if(key.isAcceptable()){
                System.out.println("Server: 接收新链接");
                // accept 得到这条连接用于传送数据的channel
                SocketChannel sc =((ServerSocketChannel) key.channel()).accept();
                sc.configureBlocking(false);
                // 第三个参数是共享数据，这里用一个共享的缓冲
                sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
            }else if(key.isReadable()){
                SocketChannel sc  = (SocketChannel) key.channel();
                ByteBuffer buffer = (ByteBuffer) key.attachment();
                // 如果没有下面这两行，当client关闭后，会一直处于isReadable的死循环
                buffer.flip();
                buffer.limit(1024);
//                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int len = sc.read(buffer);
                // 不是所有read事件都会有数据
                if(len > 0){
                    System.out.println("Server: 接收到客户端发来的消息：" + new String(buffer.array(), 0, len,StandardCharsets.UTF_8));
                    // 响应客户端
                    buffer.flip();
                    buffer.put("hello client".getBytes(StandardCharsets.UTF_8));
                    buffer.flip();
                    sc.write(buffer);
                }
                System.out.println("readable:" + len);
                // 注册读写事件
                key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            }else if(key.isWritable()){
                SocketChannel sc = (SocketChannel) key.channel();
                System.out.println("Server: 响应客户端消息");
                // JAVA中 NIO事件触发是水平触发 level triggered，没有数据往外写的时候，需要取消对write事件感兴趣，有数据往外写的时候再注册write 事件
                // 不然如果上一次的还没write 完，就还会触发write事件。
                key.interestOps(SelectionKey.OP_READ);
            }
        }
    }
}
