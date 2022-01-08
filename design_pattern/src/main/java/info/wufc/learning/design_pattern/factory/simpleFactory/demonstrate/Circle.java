package info.wufc.learning.design_pattern.factory.simpleFactory.demonstrate;

/**
 * @ClassName: Circle
 * @Description: 圆形
 * @Info: createdBy alien on 2019/10/19/019 11:09
 */
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle.draw");
    }

    @Override
    public void erase() {
        System.out.println("Circle.erase");
    }
}
