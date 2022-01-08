package info.wufc.learning.design_pattern.decorator.structure.simplify2;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/27/027 23:10
 */
public class Client {
    public static void main(String[] args) {
        AbstractComponent component = new ConcreteComponent();
        component = new ConcreteDecorator(component);
        component.doSomething();
    }

}
