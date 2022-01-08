package info.wufc.learning.design_pattern.singleton;

/**
 * @ClassName: EnumSingleton
 * @Description: TODO
 * @Info: createdBy alien on 2018/4/2/002 21:29
 */
/*枚举单例  最佳的单例模式,可以避免使用反射来创建多个实例(对于饿汉(提前创建好)和懒汉模式(需要时再创建))*/
enum EnumSingleton {
    INSTANCE;  //相当于EnumSingleton的实例

    public void work() {
        System.out.println("EnumSingleton.work");
    }
}


