package info.wufc.learning.design_pattern.command.structure;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 20:42
 */
public class ConcreteCommand implements Command{
    /** 如果是较“重”的command，那么可以没有接受者，直接由command完成*/
    Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println("ConcreteCommand.execute");
        receiver.action();
    }
}
