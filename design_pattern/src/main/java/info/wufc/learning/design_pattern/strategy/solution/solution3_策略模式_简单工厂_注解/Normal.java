package info.wufc.learning.design_pattern.strategy.solution.solution3_策略模式_简单工厂_注解;

/**
 * @ClassName: Normal
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 14:00
 */
@PriceRegion(max=1000)
public class Normal implements CalPrice {

    @Override
    public Double CalPrice(Double amount) {
        return amount;
    }
}
