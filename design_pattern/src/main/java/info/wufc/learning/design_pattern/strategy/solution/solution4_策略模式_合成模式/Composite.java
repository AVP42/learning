package info.wufc.learning.design_pattern.strategy.solution.solution4_策略模式_合成模式;

import java.util.List;

/**
 * @ClassName: Decorator
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/27/027 23:32
 */
public class Composite implements Strategy{
    private List<Strategy> strategies;

    public Composite(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public void doStrategy() {
        for (Strategy strategy : strategies) {
            strategy.doStrategy();
        }
    }
}
