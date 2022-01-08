package info.wufc.learning.design_pattern.strategy.solution.solution2_策略模式_简单工厂模式;

/**
 * @ClassName: CalPriceFactory
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 14:22
 */
public class CalPriceFactory {

    public static CalPrice createCalPrice(Double totalAmount) {
        if (totalAmount > 3000D) {
            return new GoldVip();
        } else if (totalAmount > 2000D) {
            return new SilverVip();
        } else if (totalAmount > 1000D) {
            return new Vip();
        } else {
            return new Normal();
        }
    }

}
