package info.wufc.learning.design_pattern.mediator.deformation;



/**
 * @description: 具体同事对象1
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-08 22:00
 */
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(ColleagueFactory.Mediator mediator) {
        super(mediator);
    }

    @Override
    public void action() {
        System.out.println("ConcreteColleague1.action");
    }

}
