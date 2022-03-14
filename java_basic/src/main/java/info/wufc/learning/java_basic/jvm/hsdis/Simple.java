package info.wufc.learning.java_basic.jvm.hsdis;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-02-20 12:56
 */
public class Simple {
    int a = 1;
    static int b = 2;

    public int sum(int c){
        return a + b + c;
    }

    public static void main(String[] args) {
        // -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp -XX:CompileCommand=dontinline,*Simple.sum -XX:CompileCommand=compileonly,*Simple.sum
        new Simple().sum(3);
    }

}
