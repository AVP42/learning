package info.wufc.learning.design_pattern.strategy.solution.solution1_策略模式;

/**
 * @ClassName: Normal
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 14:00
 */
public class Normal implements CalPrice {

    @Override
    public Double CalPrice(Double amount) {
        return amount;
    }
}
