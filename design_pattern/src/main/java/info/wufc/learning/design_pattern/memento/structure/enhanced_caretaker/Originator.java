package info.wufc.learning.design_pattern.memento.structure.enhanced_caretaker;

/**
 * @description: 发起人
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 19:35
 */
public class Originator {

    private String state;


    protected class Memento implements MementoIF{
        private String state;

        private Memento(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }
    }

    public void setState(String state){
        this.state = state;
        System.out.println("The state changes to " + state);
    }

    public MementoIF createMemento(){
        return new Memento(state);
    }

    public void restoreMemento(MementoIF m){
        setState(((Memento) m).getState());
    }

}
