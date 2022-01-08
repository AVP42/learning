package info.wufc.learning.design_pattern.mediator.deformation;

/**
 * @description: 客户端
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-08 22:12
 */
public class Client {

    public static void main(String[] args) {
        ColleagueFactory factory = new ColleagueFactory();
        Colleague c1 = factory.createColleague1();
        Colleague c2 = factory.createColleague2();
        c1.change();
        c2.change();
    }
}
