package info.wufc.learning.java_basic.file;

import java.net.URL;

/**
 * https://www.baeldung.com/reading-file-in-java
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-25 22:51
 */
public class PathTest {

    public static void main(String[] args) {

        // 使用clazz
        // path不以’/'开头时，默认是从此类所在的包下取资源；
        //path  以’/'开头时，则是从ClassPath根下获取；
        // file:/D:/learning/0_github/java_basic/target/classes/info/wufc/learning/java_basic/file/
        URL resource = PathTest.class.getResource("");
        // file:/D:/learning/0_github/java_basic/target/classes/
        URL resource1 = PathTest.class.getResource("/");
        // null
        URL resource2 = PathTest.class.getResource("./input.txt");
        // file:/D:/learning/0_github/java_basic/target/classes/input.txt
        URL resource3 = PathTest.class.getResource("/input.txt");
        URL resource4 = PathTest.class.getResource("D:/learning/0_github/java_basic/target/classes/input.txt");


        // 使用classLoader 获取的是以resources文件夹开头的绝对路径
        // path不能以’/'开头时；
        // path是从ClassPath根下获取
        // file:/D:/learning/0_github/java_basic/target/classes/
        URL url = PathTest.class.getClassLoader().getResource("");
        // NULL
        URL url2 = PathTest.class.getClassLoader().getResource("/");
        // file:/D:/learning/0_github/java_basic/target/classes/
        URL url3 = PathTest.class.getClassLoader().getResource("./");
        // file:/D:/learning/0_github/java_basic/target/classes/input.txt
        URL url4 = PathTest.class.getClassLoader().getResource("input.txt");
        // null
        URL url5 = PathTest.class.getClassLoader().getResource("/input.txt");
        // file:/D:/learning/0_github/java_basic/target/classes/input.txt
        URL url6 = PathTest.class.getClassLoader().getResource("./input.txt");
    }
}
