package info.wufc.learning.design_pattern.mediator.deformation;


import java.util.Collection;
import java.util.LinkedList;

/**
 * @description: 同事对象通常是由一个对象创建的
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-08 22:57
 */
public class ColleagueFactory {

    Mediator mediator;

    public ColleagueFactory() {
        this.mediator = new ConcreteMediator();
    }

    public Colleague createColleague1() {
        Colleague c = new ConcreteColleague1(mediator);
        mediator.addColleague(c);
        return c;
    }

    public Colleague createColleague2() {
        Colleague c = new ConcreteColleague2(mediator);
        mediator.addColleague(c);
        return c;
    }

    static abstract class Mediator{
        abstract void colleagueChange(Colleague c);

        abstract void addColleague(Colleague colleague);
    }

    static class ConcreteMediator extends ColleagueFactory.Mediator {
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
            System.out.println("colleague:" + colleague.getClass().getSimpleName() + "has changed! " +
                    "brace yourself and ready for impact!");
            for (Colleague c : colleagues) {
                c.action();
            }
        }

        public Collection<Colleague> getColleagues() {
            return colleagues;
        }
    }

}
