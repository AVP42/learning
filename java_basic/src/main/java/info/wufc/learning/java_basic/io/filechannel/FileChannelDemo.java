package info.wufc.learning.java_basic.io.filechannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileChannelDemo {
    static  class Output{
        public static void main(String[] args) throws IOException {
            FileOutputStream outputStream = new FileOutputStream(new File("D:\\learning\\0_github\\java_basic\\src\\main\\resources\\filechannel_output.txt"));
            // 从inputstream中获取通道
            FileChannel channel = outputStream.getChannel();
            // 创建缓冲
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello fileChannel".getBytes(StandardCharsets.UTF_8));
            // 需要调用flip将指针重置
            buffer.flip();
            // 将缓冲中的数据写入到通道中
            channel.write(buffer);
            // 关闭channel
            channel.close();
        }

    }

    static class Input{
        public static void main(String[] args) throws IOException {
            File file = new File("D:\\learning\\0_github\\java_basic\\src\\main\\resources\\filechannel_input.txt");
            FileInputStream inputStream = new FileInputStream(file);
            FileChannel channel = inputStream.getChannel();
            // 默认是分配HeapByteBuffer，是在堆里面的
            ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
            channel.read(buffer);
            System.out.println(new String(buffer.array()));
            // 关闭channel
            channel.close();
        }
    }

    static class Copy_BIO{
        public static void main(String[] args) throws IOException {
            // BIO 复制文件
            File src = new File("D:\\learning\\0_github\\java_basic\\src\\main\\resources\\filechannel_input.txt");
            File dst = new File("D:\\learning\\0_github\\java_basic\\src\\main\\resources\\filechannel_copy.txt");
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(dst);
            byte[] buffer = new byte[1024];
            int len = -1;
            // 读取到buffer中
            while((len = fis.read(buffer))!= -1){
                // 从buffer中写到文件中
                fos.write(buffer, 0, len);
            }
            fis.close();
            fos.close();
        }
    }

    static class Copy_NIO{
        public static void main(String[] args) throws IOException {
            // NIO复制文件
            File src = new File("D:\\learning\\0_github\\java_basic\\src\\main\\resources\\filechannel_input.txt");
            File dst = new File("D:\\learning\\0_github\\java_basic\\src\\main\\resources\\filechannel_copy2.txt");
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(dst);
            FileChannel fci = fis.getChannel();
            FileChannel fco = fos.getChannel();
            // fileChannel 底层会创建零时对外内存directByteBuffer来作为缓存
            // fileChannel并不支持非阻塞操作，java NIO中的网络通道才是非阻塞IO的实现，基于事件驱动，使用与需要维持大量链接，数据交换量不大的情况
            // 比如一些即时通信的服务
//            fci.transferTo(0, src.length(), fco);
            fco.transferFrom(fci, 0, src.length());
        }
    }
}
