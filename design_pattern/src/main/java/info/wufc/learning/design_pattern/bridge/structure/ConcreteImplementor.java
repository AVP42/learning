package info.wufc.learning.design_pattern.bridge.structure;

/**
 * @ClassName: ConcreteImplementor
 * @Description: 具体实现化角色
 * @Info: createdBy alien on 2019/10/20/020 23:18
 */
public class ConcreteImplementor extends Implementor{
    @Override
    public void doImplementation() {
        System.out.println("ConcreteImplementor.doImplementation");
    }
}
