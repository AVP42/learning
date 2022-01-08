package info.wufc.learning.design_pattern.strategy.solution.solution4_策略模式_装饰模式;

import java.util.Arrays;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/27/027 23:34
 */
public class Client {
    public static void main(String[] args) {
        Strategy strategy = new ConcreteStrategy();
        Strategy strategy2 = new ConcreteStrategy2();
        Strategy combined = new Decorator(Arrays.asList(strategy,strategy2));
        combined.doStrategy();
    }
}
