package info.wufc.learning.design_pattern.strategy.definition;

import org.springframework.stereotype.Component;

/**
 * @ClassName: ConcreteStrategyB
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 13:34
 */
@Component("strategyB")
public class ConcreteStrategyB implements Strategy {

    @Override
    public void strategyMethod() {
        System.out.println("ConcreteStrategyB.strategyMethod");
    }
}
