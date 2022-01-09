package info.wufc.learning.design_pattern.observer.structure;

/**
 * @description: 客户端
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 12:02
 */
public class Client {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver1();
        Observer observer2 = new ConcreteObserver2();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.change("new state");
    }
}
