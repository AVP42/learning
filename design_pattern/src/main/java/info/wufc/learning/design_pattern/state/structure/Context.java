package info.wufc.learning.design_pattern.state.structure;

/**
 * 环境类，即所要考察的类
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-11 21:10
 */
public class Context {
    State state;

    public void sampleOperation() {
        System.out.println("Context.sampleOperation");
        state.sampleOperation();
    }

    public void setState(State state) {
        this.state = state;
    }
}
