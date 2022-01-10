package info.wufc.learning.design_pattern.command.structure;

/**
 * @description: 请求者 作为client与receiver的中间层
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 20:32
 */
public class Invoker {

    /**通过invoker可以较方便的添加command，比如这里设计集合*/
    Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action() {
        // 请求者接到指令后,可以将放到队列中等，操作。
        System.out.println("Invoker.invoke");
        command.execute();
    }
}
