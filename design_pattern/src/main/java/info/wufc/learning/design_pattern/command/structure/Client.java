package info.wufc.learning.design_pattern.command.structure;

/**
 * @description: 客户端
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 20:51
 */
public class Client {

    public static void main(String[] args) {
        // 客户端指令请求处理者
        Receiver receiver = new Receiver();
        // 创建指令，指令中包含着请求实际处理者
        Command command = new ConcreteCommand(receiver);
        // 指令由谁来传达
        Invoker invoker = new Invoker(command);
        invoker.action();
    }
}
