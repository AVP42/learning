package info.wufc.learning.design_pattern.mediator.structure;

/**
 * @description: 客户端
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-08 22:12
 */
public class Client {

    public static void main(String[] args) {

        ConcreteMediator mediator = new ConcreteMediator();
        Colleague colleague1 = new ConcreteColleague1(mediator);
        Colleague colleague2 = new ConcreteColleague2(mediator);
        mediator.addColleague(colleague1);
        mediator.addColleague(colleague2);
    }
}
