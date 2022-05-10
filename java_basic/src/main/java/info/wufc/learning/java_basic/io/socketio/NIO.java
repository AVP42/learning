package info.wufc.learning.java_basic.io.socketio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NIO {

    static class Server{
        public static void main(String[] args) throws IOException {
            // NIO 使用SocketServerChannel
            ServerSocketChannel channel = ServerSocketChannel.open();
            // 必须配置为false，因为selector模式就是非阻塞的
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(9000));
            // 创建selector
            Selector selector = Selector.open();
            // 将serverChannel希望得到通知的accept事件注册到seelctor上
            channel.register(selector, SelectionKey.OP_ACCEPT);
            // 开始监听
            while(true){
                System.out.println("等待事件发生..");
                // 轮询监听channel中的key，这里是阻塞的
                int select = selector.select();

                System.out.println("有事件发生了..");
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    handle(key);
                }

            }


        }

        private static void handle(SelectionKey key) throws IOException {
            // 如果是accept事件
            if (key.isAcceptable()) {
                System.out.println("有客户端连接事件发生了");
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                // accept 也是阻塞的
                SocketChannel accepted = ssc.accept();
                // 设置客户端的channel为非阻塞
                accepted.configureBlocking(false);
                // 对read事件感兴趣
                accepted.register(key.selector(), SelectionKey.OP_READ);

            }else if(key.isReadable()){
                System.out.println("有客户端可读事件发生....");
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                // 已经准备就绪，这里堵塞读取，说明数据读完了
                int len = sc.read(buffer);
                if(len != -1){
                    System.out.println("读取到客户端发送的数据：" + new String(buffer.array(), 0, len));
                }
                ByteBuffer bufferToWrite = ByteBuffer.wrap("helloClient".getBytes(StandardCharsets.UTF_8));
                sc.write(bufferToWrite);
                // 对read或者write感兴趣
                key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            } else if (key.isWritable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                System.out.println("write 事件");
                // JAVA中 NIO事件触发是水平触发 level triggered，没有数据往外写的时候，需要取消对write事件感兴趣，有数据往外写的时候再注册write 事件
                // 不然如果上一次的还没write 完，就还会触发write事件。
                key.interestOps(SelectionKey.OP_READ);
            }
        }

    }

    static class Client{

        // NIO 使用socketChannel
        public static void main(String[] args) throws IOException {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("127.0.0.1", 9000));
            Selector selector = Selector.open();
            sc.register(selector, SelectionKey.OP_CONNECT);

            while(true){
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey key = it.next();
                    it.remove();
                    if(key.isConnectable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        if(channel.isConnectionPending()){
                            channel.finishConnect();
                        }
                        channel.configureBlocking(false);
                        ByteBuffer bufferToWrite = ByteBuffer.wrap("helloserver".getBytes(StandardCharsets.UTF_8));
                        channel.write(bufferToWrite);
                        channel.register(selector, SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len = channel.read(buffer);
                        if(len != -1){
                            System.out.println("客户端接受到信息：" + new String(buffer.array()));
                        }
                    }
                }
            }
        }
    }
}
