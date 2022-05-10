package info.wufc.learning.java_basic.io.socketio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BIO {

    static class Server{
        // BIO使用serverSocket
        public static void main(String[] args) throws IOException {
            ServerSocket socket = new ServerSocket(8080);
            while(true){
                try {
                    Socket accepted = socket.accept();
                    System.out.println("accepted new connection");

                    new Thread(() -> {
                        try {
                            handle(accepted);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private static void handle(Socket accepted) throws IOException {
            byte[] buffer = new byte[16];
            byte[] ans = new byte[1024];
            int p = 0;
            InputStream in = accepted.getInputStream();
            int ret;
            if((ret = in.read(buffer)) != -1){
                System.out.println(ret);
                System.arraycopy(buffer, 0, ans, p, buffer.length);
                p += buffer.length;
            }
            System.out.println(new String(ans, 0, p ));
            accepted.getOutputStream().write("helloclient".getBytes(StandardCharsets.UTF_8));
            accepted.getOutputStream().flush();
        }
    }

    static class Client{
        // BIO使用socket
        public static void main(String[] args) throws IOException {
            Socket socket = new Socket("127.0.0.1", 8080);
            socket.getOutputStream().write("helloserver".getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().flush();
            System.out.println("send to server");
            byte[] buffer = new byte[1024];
            socket.getInputStream().read(buffer);
            System.out.println(new String(buffer));
            socket.close();
        }
    }
}
