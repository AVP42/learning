package info.wufc.learning.design_pattern.strategy.definition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: Context
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 13:36
 */
@Service
public class Context {
    /**
     * 持有策略对象的引用
     */
    @Autowired
    private Strategy strategyA;

    @Autowired
    private Strategy strategyB;

    /**
     * 调用策略方法A
     */
    public void contextMethodA() {
        strategyA.strategyMethod();
        System.out.println("Context.contextMethodA");
    }

    /**
     * 调用策略方法B
     */
    public void contextMethodB() {
        strategyB.strategyMethod();
        System.out.println("Context.contextMethodB");
    }
}
