package info.wufc.learning.java_basic.Type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-03-01 23:42
 */
public class Foo extends Bar<Integer>{

    private Map<String, Integer> map = new HashMap<String, Integer>();

    public List<Integer> test(List<String> t) {
        return new ArrayList<Integer>();
    }

    public static void main(String[] args) throws Exception {
        // 这种在静态编译的时候就已经明确了类型，可以通过反射获取到
        // 类
        Type genericSuperclass = Foo.class.getGenericSuperclass();
        if(genericSuperclass instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }

        // 字段
        Field field = Foo.class.getDeclaredField("map");
        field.setAccessible(true);
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
            System.out.println(actualTypeArguments);
        }

        // 方法参数，方法返回值
        Method test = Foo.class.getMethod("test", List.class);
        Type genericReturnType = test.getGenericReturnType();
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            System.out.println(actualTypeArguments);
        }
        Type[] genericParameterTypes = test.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                System.out.println(actualTypeArguments);
            }
        }


    }
}

class Bar<T>{

}
