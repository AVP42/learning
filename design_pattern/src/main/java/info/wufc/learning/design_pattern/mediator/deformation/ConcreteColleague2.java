package info.wufc.learning.design_pattern.mediator.deformation;


/**
 * @description: 具体同事对象2
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-08 22:01
 */
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(ColleagueFactory.Mediator mediator) {
        super(mediator);
    }

    @Override
    public void action() {
        System.out.println("ConcreteColleague2.action");
    }
}
