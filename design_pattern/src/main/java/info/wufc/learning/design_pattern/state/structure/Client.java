package info.wufc.learning.design_pattern.state.structure;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-11 20:57
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        State state = new ConcreteState();
        context.setState(state);
        context.sampleOperation();

        context.setState(new ConcreteState2());
    }
}
