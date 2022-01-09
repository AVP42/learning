package info.wufc.learning.design_pattern.memento.structure.blackbox;

/**
 * @description: 发起人角色
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 18:51
 */
public class Originator {

    private String state;

    /**
     * 使用双接口方法同时提供宽接口和窄接口
     * 双接口解决方法，用内部成员类实现
     * 利用方法的private权限，可以限制外界访问
     */
    protected class Memento implements MementoIF{
        private String state;

        public Memento(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }

        private void setState(String state){
            this.state = state;
        }
    }

    public MementoIF createMemento() {
        return new Memento(getState());
    }

    public void restoreMemento(MementoIF m){
        setState(((Memento) m).getState());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        System.out.println("state changes to "+ state);
    }
}
