package info.wufc.learning.design_pattern.mediator.structure;

/**
 * @description: 抽象同事类
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-08 21:58
 */
public abstract class Colleague {

    Mediator mediator;

    /**
     * 每个同事都只知道调停者，而不知道其他同事独享
     * @param mediator
     */
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    /**
     * 子类具体方法
     */
    abstract void action();

    /**
     * 状态改变方法，通知协调者
     */
    void change(){
        mediator.colleagueChange(this);
    }
}
