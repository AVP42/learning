package info.wufc.learning.design_pattern.mediator.structure;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @description: 具体调停者
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-08 22:00
 */
public class ConcreteMediator extends Mediator{
    /**
     * 调停者知道所有的同事对象，这里是改动了下，原示例直接是两个实例成员对应两个同事对象。
     */
    Collection<Colleague> colleagues = new LinkedList<>();

    /**
     * 添加同事对象，这里是改动了下，原示例是提供工厂方法
     */
    public void addColleague(Colleague c) {
        this.colleagues.add(c);
        this.colleagueChange(c);
    }

    @Override
    public void colleagueChange(Colleague colleague) {
        System.out.println("colleague:" + colleague.getClass().getSimpleName()
                + "has changed! brace yourself and ready for impact!");
        for (Colleague c : colleagues) {
            c.action();
        }
    }

    public Collection<Colleague> getColleagues() {
        return colleagues;
    }
}
