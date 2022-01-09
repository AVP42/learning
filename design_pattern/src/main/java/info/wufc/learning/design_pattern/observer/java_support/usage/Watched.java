package info.wufc.learning.design_pattern.observer.java_support.usage;

import info.wufc.learning.design_pattern.observer.java_support.structure.Observable;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 13:15
 */
public class Watched extends Observable {

    private String data;

    public String retrieveData() {
        return data;
    }

    public synchronized void changeData(String data){
        this.data = data;
        setChanged();
        notifyObserver(data);
    }
}
