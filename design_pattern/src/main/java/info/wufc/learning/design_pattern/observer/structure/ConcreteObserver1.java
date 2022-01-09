package info.wufc.learning.design_pattern.observer.structure;

/**
 * @description: 具体观察者
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 12:03
 */
public class ConcreteObserver1 extends Observer{

    @Override
    void update() {
        System.out.println("ConcreteObserver1.update");
    }
}
