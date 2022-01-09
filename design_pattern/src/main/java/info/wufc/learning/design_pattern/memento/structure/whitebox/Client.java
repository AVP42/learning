package info.wufc.learning.design_pattern.memento.structure.whitebox;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 17:07
 */
public class Client {

    static Originator originator = new Originator();
    static Caretaker caretaker = new Caretaker();
    public static void main(String[] args) {
        originator.setState("On");
        Memento m = originator.createMemento();
        caretaker.saveMemento(m);
        originator.setState("Off");
        originator.restoreMemento(caretaker.retrieveMemento());
    }
}
