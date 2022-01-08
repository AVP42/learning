package info.wufc.learning.design_pattern.factory.simpleFactory.demonstrate;

/**
 * @ClassName: Circle
 * @Description: 三角形
 * @Info: createdBy alien on 2019/10/19/019 11:09
 */
class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Triangle.draw");
    }

    @Override
    public void erase() {
        System.out.println("Triangle.erase");
    }
}
