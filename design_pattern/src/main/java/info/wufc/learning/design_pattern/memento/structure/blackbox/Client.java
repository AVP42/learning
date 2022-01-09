package info.wufc.learning.design_pattern.memento.structure.blackbox;

/**
 * @description: 黑箱实现
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 19:13
 */
public class Client {

    static Originator originator = new Originator();
    static Caretaker caretaker = new Caretaker();
    public static void main(String[] args) {
        originator.setState("On");
        MementoIF m = originator.createMemento();
        caretaker.saveMemento(m);
        originator.setState("Off");
        originator.restoreMemento(caretaker.retrieveMemento());
    }
}
