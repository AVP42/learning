package info.wufc.learning.design_pattern.state.structure;

/**
 * 具体状态
 * @author : fuchang.wu@foxmail.com
 * @since  2022-01-11 20:57
 */
public class ConcreteState implements State {
    @Override
    public void sampleOperation() {
        System.out.println("ConcreteState.sampleOperation");
    }
}
