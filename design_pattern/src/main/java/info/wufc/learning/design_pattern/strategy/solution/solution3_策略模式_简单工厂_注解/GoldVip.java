package info.wufc.learning.design_pattern.strategy.solution.solution3_策略模式_简单工厂_注解;

/**
 * @ClassName: GoldVip
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 14:00
 */
@PriceRegion(min=3000)
public class GoldVip implements CalPrice {

    @Override
    public Double CalPrice(Double amount) {
        return amount*0.7;
    }
}
