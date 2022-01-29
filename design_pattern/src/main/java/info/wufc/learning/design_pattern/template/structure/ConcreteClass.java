package info.wufc.learning.design_pattern.template.structure;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-29 10:34
 */
class ConcreteClass extends AbstractClass{
    @Override
    protected void doOperation1() {
        System.out.println("ConcreteClass.doOperation1");
    }

    @Override
    protected void doOperation2() {
        System.out.println("ConcreteClass.doOperation2");
    }
}
