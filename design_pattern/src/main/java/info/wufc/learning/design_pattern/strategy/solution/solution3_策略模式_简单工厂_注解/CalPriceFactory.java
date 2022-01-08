package info.wufc.learning.design_pattern.strategy.solution.solution3_策略模式_简单工厂_注解;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: CalPriceFactory
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 14:22
 */
public class CalPriceFactory {
    /**
     * 定义策略类扫描包
     */
    private static final String CAL_PRICE_PACKAGE = "com.example.designmodeldemo.strategy.solution.solution3_策略模式_简单工厂_注解";

    /**
     * 定义类加载器，用于加载定义了注解的策略角色类，如果是在spring环境下，且策略角色类给sprign管理，就不需要了
     */
    private ClassLoader classLoader = getClass().getClassLoader();

    /**
     * 定义具体策略角色列表
     */
    private List<Class<? extends CalPrice>> calPriceList;

    /**
     * 构造器中实现对注解类的扫描，加入到calPriceList中,使用private 单例模式
     */
    private CalPriceFactory() {
        init();
    }

    public static  CalPriceFactory getInstance() {
        return CalPriceFactoryHolder.calPriceFactory;
    }

    static class CalPriceFactoryHolder{
        static CalPriceFactory calPriceFactory = new CalPriceFactory();
    }

    /**
     * 初始化方法，需要初始化策略列表
     */
    private void init() {
        calPriceList = new ArrayList<>();
        //读取需要扫描的包的路径，读取.class文件
        File[] resources = getResources();
        //通过classLoader加载策略接口的clazz对象，用于判定扫描包下的类是否是该策略接口的实现类
        Class<CalPrice> calPriceClazz = null;
        try {
            calPriceClazz = (Class<CalPrice>) classLoader.loadClass(CalPrice.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("未找到策略接口");
        }
        Class<CalPrice> finalClazz = calPriceClazz;
        // 加载file成字节码对象
        Arrays.stream(resources).forEach(file -> {
            try {
                Class<?> clazz = classLoader.loadClass(CAL_PRICE_PACKAGE +"." + file.getName().replace(".class", ""));
                if (CalPrice.class.isAssignableFrom(clazz)&&clazz != finalClazz) {
                    calPriceList.add((Class<? extends CalPrice>) clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private File[] getResources() {
        try {
            //通过classLoader.getResource获取扫描包对象
            File packageFile = new File(classLoader.getResource(CAL_PRICE_PACKAGE.replace(".", "/")).toURI());
            //通过扫描包对象的listFiles获取.class文件对象
            return packageFile.listFiles(file -> file.getName().endsWith(".class"));
        } catch (Exception e) {
            throw new RuntimeException("没有找到指定的策略资源");
        }


    }

    /**
     * 通过totalAmount从list中获取对应的calPrice
     * @param totalAmount
     * @return
     */
    public CalPrice createCalPrice(Double totalAmount) {
        return calPriceList.stream().filter(clazz -> {
            //利用策略clazz，获取类上的priceRegion注解
            Annotation[] annotations = clazz.getDeclaredAnnotations();
            PriceRegion priceRegion = (PriceRegion) Arrays.stream(annotations).filter(annotation -> annotation instanceof PriceRegion).findFirst().orElse(null);
            //根据类上的priceRegion注解的区间判定使用哪个策略
            return priceRegion.max() > totalAmount && priceRegion.min() <= totalAmount;
        }).findFirst().map(clazz -> {
            try {
                return clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).orElse(null);
    }

}
