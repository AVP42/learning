package info.wufc.learning.design_pattern.memento.structure.enhanced_caretaker;

/**
 * @description: 增强的负责人角色
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 19:35
 */
public class Caretaker {
    private Originator originator;

    private MementoIF memento;

    public Caretaker(Originator originator) {
        this.originator = originator;
    }

    public void createMemento() {
        memento = originator.createMemento();
    }

    public void restoreMemento() {
        originator.restoreMemento(memento);
    }
}
