package info.wufc.learning.design_pattern.strategy.solution.solution2_策略模式_简单工厂模式;

/**
 * @ClassName: Test
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 14:10
 */
public class Test {
    public static void main(String[] args) {
        Player player = new Player();
        player.buy(500D);
        System.out.println(player.CalLastPrice());
        player.buy(700D);
        System.out.println(player.CalLastPrice());
        player.buy(900D);
        System.out.println(player.CalLastPrice());
        player.buy(1000D);
        System.out.println(player.CalLastPrice());
    }

}
