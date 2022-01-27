package info.wufc.learning.java_basic.io;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-25 22:35
 */
public class IOTest {

    public static void main(String[] args) throws IOException, URISyntaxException {
//        readFromConsole();
        readFromFile();
        writeToFile();
    }

    private static void writeToFile() throws URISyntaxException, IOException {
        InputStream in = IOTest.class.getClassLoader().getResourceAsStream("input.txt");
        File output = new File(IOTest.class.getClassLoader().getResource("").getPath(), "output.txt");
        FileOutputStream out = new FileOutputStream(output);
        byte[] bytes = new byte[100];
        in.read(bytes);
        out.write(bytes);
    }

    private static void readFromFile() throws IOException {
        InputStream in = IOTest.class.getClassLoader().getResourceAsStream("input.txt");
        readFromInputStream(in);
    }

    private static void readFromConsole() throws IOException {
        readFromInputStream(System.in);
    }

    private static void readFromInputStream(InputStream in) throws IOException {
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String s;
        while((s = bufferedReader.readLine())!=null){
            System.out.println(s);
        }
    }
}
