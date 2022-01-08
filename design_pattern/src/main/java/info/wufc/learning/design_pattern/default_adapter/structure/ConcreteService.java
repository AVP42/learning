package info.wufc.learning.design_pattern.default_adapter.structure;

/**
 * @description: 具体类
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-08 16:26
 */
public class ConcreteService extends ServiceAdapter{
    @Override
    public void serviceOperation3() {
        System.out.println("ConcreteService.serviceOperation3");
    }
}
