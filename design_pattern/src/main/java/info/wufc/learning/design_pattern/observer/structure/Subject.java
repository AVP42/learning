package info.wufc.learning.design_pattern.observer.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 抽象主题类   线程不安全
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 12:03
 */
public abstract class Subject {
    List<Observer> obs;

    public Subject() {
        this.obs = new ArrayList<>();
    }

    /**
     * 为了避免obs实时被更新了，所以应当先获取快照，然后再遍历
     */
    public void notifyObservers(){
        /**
         * 采用了备忘录模型，暂存观察者对象集合，避免集合发生变动，造成不必要的错误。
         */
        List<Observer> obs = getObservers();
        for (Observer ob :obs) {
            ob.update();
        }
    }

    public void addObserver(Observer observer) {
        obs.add(observer);
    }

    /**
     * 不能直接返回obs，避免被修改，应当返回当前的快照
     * @return
     */
    public List<Observer> getObservers() {
        return Collections.unmodifiableList(obs);
    }
}
