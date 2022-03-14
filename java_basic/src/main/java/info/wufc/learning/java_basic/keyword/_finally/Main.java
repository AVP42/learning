package info.wufc.learning.java_basic.keyword._finally;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-03-01 21:18
 */
public class Main {

    public static int returnInFinally(){
        // finally里面有return的情况会被优化为只有finally里面有return，查看class文件反编译可以知道
        int x;
        try{
            x = 1;
            return x;
        }catch (Exception e){
            x = 2;
            return x;
        }finally {
            x = 3;
            return x;
        }
    }

    public static int returnOutSide(){
        int x;
        try{
            x = 1;
            return x;
        }catch (Exception e){
            x = 2;
            return x;
        }finally {
            x = 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(returnInFinally());
        System.out.println(returnOutSide());
    }
}
