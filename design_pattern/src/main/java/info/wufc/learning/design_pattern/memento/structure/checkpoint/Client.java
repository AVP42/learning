package info.wufc.learning.design_pattern.memento.structure.checkpoint;

/**
 * @description: 一个系统中很多时候需要保存一个对象的多个状态，以便根据checkpoint回溯历史
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 19:50
 */
public class Client {

    static Originator originator = new Originator();
    static Caretaker caretaker = new Caretaker(originator);
    public static void main(String[] args) {
        originator.setState("AAAA");
        int checkpointA = caretaker.createMemento();
        originator.setState("BBBB");
        int checkpointB = caretaker.createMemento();
        originator.printStates();

        originator.setState("CCCC");
        int checkpointC = caretaker.createMemento();
        originator.setState("DDDD");
        int checkpointD = caretaker.createMemento();
        originator.printStates();

        caretaker.restore(checkpointB);
        originator.printStates();
        caretaker.restore(checkpointC);
        originator.printStates();
        caretaker.restore(checkpointA);
        originator.printStates();
        caretaker.restore(checkpointD);
        originator.printStates();



    }

}
