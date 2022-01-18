package info.wufc.learning.design_pattern.singleton;

import java.io.Serializable;

/**
 * @ClassName: HungrySingleton
 * @Description: TODO
 * @Info: createdBy alien on 2018/4/2/002 21:43
 */
/*饿汉式单例*/
public class HungrySingleton {
    // 执行创建构造出对象
    private static final  HungrySingleton INSTANCE = new HungrySingleton();;

    // 单例最好将构造器私有化
    private HungrySingleton() {
        System.out.println("HungrySingleton construct");
    }

    // 向外提供单例
    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

    // 商业方法
    public  void work() {
        System.out.println("HungrySingleton.work");
    }

}
