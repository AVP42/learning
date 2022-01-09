package info.wufc.learning.design_pattern.observer.structure;

/**
 * @description: 具体主题类
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 12:03
 */
public class ConcreteSubject extends Subject {
    private String state;

    public void change(String state){
        this.state = state;
        super.notifyObservers();
    }

}
