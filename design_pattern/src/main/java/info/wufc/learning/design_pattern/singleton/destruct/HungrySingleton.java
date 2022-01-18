package info.wufc.learning.design_pattern.singleton.destruct;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @ClassName: HungrySingleton
 * @Description: TODO
 * @Info: createdBy alien on 2018/4/2/002 21:43
 */
/*饿汉式单例*/
public class HungrySingleton implements Serializable {
    // 执行创建构造出对象
    private static final  HungrySingleton INSTANCE = new HungrySingleton();;

    // 为了避免通过反射来创建，可以直接在构造器中返回已经创建过的对象
    private HungrySingleton() {
        System.out.println("HungrySingleton construct");
        if(INSTANCE != null) throw new RuntimeException("单例已经创建！");
    }

    // 向外提供单例
    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

    // 商业方法
    public  void work() {
        System.out.println("HungrySingleton.work");
    }

    // 测试方法
    public static void doSomething() {
        System.out.println("HungrySingleton.doSomething");
    }

    /**
     * 通过readResolve方法，在反序列化的时候就会直接返回已经存在的对象。
     * @return
     * @throws ObjectStreamException
     */
    public Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

}
