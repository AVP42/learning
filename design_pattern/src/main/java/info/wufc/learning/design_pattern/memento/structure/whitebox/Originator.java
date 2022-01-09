package info.wufc.learning.design_pattern.memento.structure.whitebox;

/**
 * @description: 发起者角色
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 17:07
 */
public class Originator {

    private String state;

    public void setState(String state){
        this.state = state;
        System.out.println("state changes to "+ state);
    }

    public String getState() {
        return state;
    }

    public Memento createMemento(){
        return new Memento(state);
    }

    public void restoreMemento(Memento m){
        setState(m.getState());
    }

}
