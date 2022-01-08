package info.wufc.learning.design_pattern.builder.definition;

/**
 * @ClassName: Product
 * @Description: 产品
 * @Info: createdBy alien on 2019/12/15/015 21:42
 */
public class Product1 implements IProduct{
    private String part;

    @Override
    public void setPart1(String part1) {
        this.part = part1;
    }

    @Override
    public void setPart2(String part2) {
    }

    @Override
    public String toString() {
        return "Product1{" +
                "part='" + part + '\'' +
                '}';
    }
}
