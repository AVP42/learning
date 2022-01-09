package info.wufc.learning.design_pattern.memento.structure.whitebox;

/**
 * @description: 备忘录角色
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 17:08
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    /**
     * 白箱模式，破环了封装
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }
}
