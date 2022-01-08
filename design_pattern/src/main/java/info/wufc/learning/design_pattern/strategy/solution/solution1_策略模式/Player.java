package info.wufc.learning.design_pattern.strategy.solution.solution1_策略模式;

/**
 * @ClassName: Player
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 13:44
 */

/**
 * 4种会员，购买总额达到1000，2000，3000升级，对应0.9 0.8 0.7优惠
 *
 * 该方式问题: buy方法只是构建calPrice对象，可以抽出作为简单工厂来实现
 */
public class Player {
    private Double totalAmount=0D;//总消费

    private Double amount;//单次消费

    private CalPrice calPrice;

    /**
     * 持有策略对象的引用
     */


    public void buy(Double amount) {
        this.amount = amount;
        this.totalAmount += amount;
        if (totalAmount>3000D) {
            calPrice = new GoldVip();
        } else if (totalAmount>2000D) {
            calPrice = new SilverVip();
        } else if (totalAmount > 1000D) {
            calPrice = new Vip();
        } else {
            calPrice = new Normal();
        }
    }

    public Double CalLastPrice() {
        return calPrice.CalPrice(amount);
    }
}
