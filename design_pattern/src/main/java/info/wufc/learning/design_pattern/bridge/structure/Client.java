package info.wufc.learning.design_pattern.bridge.structure;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-26 22:58
 */
public class Client {

    public static void main(String[] args) {
        Implementor implementor = new ConcreteImplementor();
        Abstraction abstraction = new RedefineAbstraction(implementor);
        abstraction.doWork();
    }
}
