package info.wufc.learning.java_basic.io.zerocopy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Channel {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        FileChannel fileChannel = new FileInputStream("/test").getChannel();
        fileChannel.transferTo(0, fileChannel.size(), socketChannel);
    }
}
