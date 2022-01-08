package info.wufc.learning.design_pattern.strategy.solution.solution3_策略模式_简单工厂_注解;

/**
 * @ClassName: Player
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 13:44
 */

/**
 * 4种会员，购买总额达到1000，2000，3000升级，对应0.9 0.8 0.7优惠
 *
 * 该方式问题: 使用了简单工厂模式，虽然在客户类中不需要使用if-else判定，但是工厂中还是有if-else
 *              使用注解解决分情况的问题
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
        calPrice = CalPriceFactory.getInstance().createCalPrice(totalAmount);
    }

    public Double CalLastPrice() {
        return calPrice.CalPrice(amount);
    }
}
