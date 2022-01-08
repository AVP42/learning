package info.wufc.learning.design_pattern.singleton;

/**
 * @ClassName: HungrySingleton
 * @Description: TODO
 * @Info: createdBy alien on 2018/4/2/002 21:43
 */
/*饿汉式单例*/
public class HungrySingleton {
    // 单例最好将构造器私有化
    private HungrySingleton() {

    }
    // 执行创建构造出对象
    private static final  HungrySingleton INSTANCE = new HungrySingleton();;

    // 向外提供单例
    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

    public  void work() {
        System.out.println("HungrySingleton.work");
    }
}
