package info.wufc.learning.design_pattern.memento.structure.blackbox;

/**
 * @description: 负责人角色
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 18:50
 */
public class Caretaker {
    MementoIF memento;

    public void saveMemento(MementoIF m){
        this.memento = m;
    }

    public MementoIF retrieveMemento(){
        return memento;
    }

}
