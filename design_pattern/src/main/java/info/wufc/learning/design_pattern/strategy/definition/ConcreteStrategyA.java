package info.wufc.learning.design_pattern.strategy.definition;

import org.springframework.stereotype.Component;

/**
 * @ClassName: ConcreteStrategyA
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 13:34
 */
@Component("strategyA")
public class ConcreteStrategyA implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("ConcreteStrategyA.strategyMethod");
    }
}
