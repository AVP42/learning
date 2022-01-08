package info.wufc.learning.design_pattern.builder.definition;

/**
 * @ClassName: Product
 * @Description: 产品
 * @Info: createdBy alien on 2019/12/15/015 21:42
 */
public class Product2 implements IProduct{
    private String part1;
    private String part2;

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    @Override
    public String toString() {
        return "Product2{" +
                "part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                '}';
    }
}
