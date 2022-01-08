package info.wufc.learning.design_pattern.factory.simpleFactory.demonstrate;

/**
 * @ClassName: ArtTracer
 * @Description: 绘图员
 * @Info: createdBy alien on 2019/10/19/019 11:08
 */
class ArtTracer {
    public static Shape factory(String shape) {
        if ("circle".equalsIgnoreCase(shape)) {
            return new Circle();
        } else if ("triangle".equalsIgnoreCase(shape)) {
            return new Triangle();
        } else if ("square".equalsIgnoreCase(shape)) {
            return new Square();
        } else {
            throw new BadShapeExcepton();
        }
    }
}
