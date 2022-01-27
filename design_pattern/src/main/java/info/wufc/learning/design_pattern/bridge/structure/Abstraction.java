package info.wufc.learning.design_pattern.bridge.structure;

/**
 * @ClassName: Abstraction
 * @Description: 抽象化角色
 * @Info: createdBy alien on 2019/10/20/020 23:14
 */
public abstract class Abstraction {
    private Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void doWork();
}
