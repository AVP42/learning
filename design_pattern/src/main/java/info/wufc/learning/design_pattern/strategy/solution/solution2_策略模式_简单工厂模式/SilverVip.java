package info.wufc.learning.design_pattern.strategy.solution.solution2_策略模式_简单工厂模式;

/**
 * @ClassName: SilverVip
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 14:00
 */
public class SilverVip implements CalPrice {

    @Override
    public Double CalPrice(Double amount) {
        return amount*0.8;
    }
}
