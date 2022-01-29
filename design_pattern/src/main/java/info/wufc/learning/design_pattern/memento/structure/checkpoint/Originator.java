package info.wufc.learning.design_pattern.memento.structure.checkpoint;


import java.util.Vector;

/**
 * @description: 发起人角色
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 19:51
 */
public class Originator {

    private Vector<String> states;
    // 每发生状态改变都对应新的版本。
    private int idx;


    public Originator() {
        states = new Vector<>();
        idx = 0;
    }



    protected class Memento implements MementoIF{
        private Vector<String> states;
        private int idx;

        /**
         * 注意需要进行克隆
         * @param states
         * @param idx
         */
        private Memento(Vector<String> states, int idx) {
            this.states = (Vector<String>) states.clone();
            this.idx = idx;
        }

        private Vector<String> getStates() {
            return states;
        }

        private int getIdx() {
            return idx;
        }

    }

    public MementoIF createMemento() {
        return new Memento(states, idx);
    }

    public void restoreMemento(MementoIF m){
        Memento memento = (Memento) m;
        this.states =memento.getStates();
        this.idx = memento.getIdx();
    }

    public void setState(String state){
        states.add(state);
        idx ++;
    }

    public int getIdx() {
        return idx;
    }

    public void printStates(){
        System.out.println("---total number of states:" + idx);
        for (String state : states) {
            System.out.println(state);
        }
    }
}
