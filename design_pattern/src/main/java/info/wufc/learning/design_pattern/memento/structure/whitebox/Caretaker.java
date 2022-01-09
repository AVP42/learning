package info.wufc.learning.design_pattern.memento.structure.whitebox;

/**
 * @description: 负责角色
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 17:08
 */
public class Caretaker {
    private Memento memento;

    public Memento retrieveMemento() {
        return this.memento;
    }

    public void saveMemento(Memento m) {
        this.memento = m;
    }

}
