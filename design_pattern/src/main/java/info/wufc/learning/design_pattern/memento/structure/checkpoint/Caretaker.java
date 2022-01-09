package info.wufc.learning.design_pattern.memento.structure.checkpoint;

import java.util.Vector;

/**
 * @description: 负责人角色
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 19:51
 */
public class Caretaker {

    private Vector<MementoIF> mementos;

    private Originator originator;

    private int checkpoint;

    public Caretaker(Originator originator) {
        this.originator = originator;
        mementos = new Vector<>();
        checkpoint = 0;
    }

    public int createMemento(){
        mementos.add(originator.createMemento());
        return checkpoint ++;
    }

    public void restore(int checkpoint){
        originator.restoreMemento(mementos.elementAt(checkpoint));
    }

    public void removeMemento(int  checkpoint){
        mementos.removeElementAt(checkpoint);
    }
}
