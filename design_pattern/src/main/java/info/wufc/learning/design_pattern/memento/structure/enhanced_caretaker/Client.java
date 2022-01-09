package info.wufc.learning.design_pattern.memento.structure.enhanced_caretaker;

/**
 * @description: 在白箱实现和黑箱实现中，都需要Client直接调用发起人角色来创建memento，
 *      并设置到Caretaker中；然后恢复备忘记录也是需要调用originator来实现的。
 *      我们可以对将这部分逻辑交给负责人来实现
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 19:32
 */
public class Client {
    static Originator originator = new Originator();
    static Caretaker caretaker = new Caretaker(originator);
    public static void main(String[] args) {
        originator.setState("On");
        caretaker.createMemento();
        originator.setState("Off");
        caretaker.restoreMemento();
    }

}
