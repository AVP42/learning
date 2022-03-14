package info.wufc.learning.java_basic.Type;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-03-01 23:22
 */
public class Main {

    public static void main(String[] args) {

        Method[] methods = Main.class.getMethods();
        Method tMethod = null;
        for (Method method : methods) {
            if ("invokeParameter".equals(method.getName())) {
                tMethod = method;
            }
        }
        // getParameterTypes获取到的是不包含泛型信息的类型，这里是为了和getGenericParameterTypes对比
        Type[] commonTypes = tMethod.getParameterTypes();
        for (Type type : commonTypes) {
            System.out.println("CommonType = " + type + ", ParamTypeCategory = " + type.getClass().getSimpleName());
        }
        System.out.println();

        // getGenericParameterTypes获取到的是包含泛型信息的类型
        Type[] genericTypes = tMethod.getGenericParameterTypes();
        for (Type type : genericTypes) {
            System.out.println("ParamType = " + type + ", ParamTypeCategory = " + type.getClass().getSimpleName());
            if (type instanceof ParameterizedType) {
                Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
                for (int i = 0; i < actualTypes.length; i++) {
                    Type aType = actualTypes[i];
                    System.out.println("\t" + i + " actualType = " + aType + ", actualTypeCategory = " + aType.getClass().getInterfaces()[0].getName());
                    if (aType instanceof WildcardType) {
                        Type[] lower = ((WildcardType) aType).getLowerBounds();
                        Type[] upper = ((WildcardType) aType).getUpperBounds();
                        System.out.println("\t\tLowerBounds = " + Arrays.toString(lower) + ", UpperBounds = " + Arrays.toString(upper));
                    } else if (aType instanceof TypeVariable) {
                        Type[] bounds = ((TypeVariable<?>) aType).getBounds();
                        String name = ((TypeVariable<?>) aType).getName();
                        GenericDeclaration declaration = ((TypeVariable<?>) aType).getGenericDeclaration();
                        System.out.println("\t\tName = " + name + ", Declaration = " + declaration + ", Bounds = " + Arrays.toString(bounds));
                    } else if (aType instanceof ParameterizedType) {
                        System.out.println("\t\tRawType = " + ((ParameterizedType) aType).getRawType() + ", OwnerType = " + ((ParameterizedType) aType).getOwnerType()
                                + ", ActualTypeArguments = " + Arrays.toString(((ParameterizedType) aType).getActualTypeArguments()));
                    }
                }
            } else if (type instanceof GenericArrayType) {
                System.out.println("\t\tGenericComponentType = " + ((GenericArrayType) type).getGenericComponentType());
            }
        }
    }

    public <T, K extends String, V> void invokeParameter(String a0, Map<K, V> a1, List<List<T>> a2, List<? super String> a3, T[] a4, String[] a5) {

    }

}
