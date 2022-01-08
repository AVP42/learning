package info.wufc.learning.design_pattern.decorator.structure.define;

/**
 * @ClassName: ConcreteComponent
 * @Description: 具体构件角色
 * @Info: createdBy alien on 2019/10/27/027 23:02
 */
public class ConcreteComponent extends AbstractComponent{
    @Override
    public void doSomething() {
        System.out.println("ConcreteComponent.doSomething");
    }
}
