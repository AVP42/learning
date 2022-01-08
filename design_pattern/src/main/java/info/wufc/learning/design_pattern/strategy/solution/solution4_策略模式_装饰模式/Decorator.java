package info.wufc.learning.design_pattern.strategy.solution.solution4_策略模式_装饰模式;

import java.util.List;

/**
 * @ClassName: Decorator
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/27/027 23:32
 */
public class Decorator implements Strategy{
    private List<Strategy> strategies;

    public Decorator(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public void doStrategy() {
        for (Strategy strategy : strategies) {
            strategy.doStrategy();
        }
    }
}
