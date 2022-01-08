package info.wufc.learning.design_pattern.strategy.problem;

/**
 * @ClassName: Player
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 13:44
 */

/**
 * 4种会员，购买总额达到1000，2000，3000升级，对应0.9 0.8 0.7优惠
 *
 * 该方式问题: 客户端代码臃肿，判断语句内均为同一问题的不同实现，进行抽取--使用策略模式
 */
public class Player {
    private Double totalAmount = 0D;//总消费

    public Double buy(Double amount) {
        this.totalAmount += amount;
        if (totalAmount>3000D) {
            return amount * 0.7;
        } else if (totalAmount>2000D) {
            return amount * 0.8;
        } else if (totalAmount > 1000D) {
            return amount * 0.9;
        } else {
            return amount;
        }
    }
}
