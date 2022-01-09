package info.wufc.learning.design_pattern.observer.java_support.usage;

import info.wufc.learning.design_pattern.observer.java_support.structure.Observable;
import info.wufc.learning.design_pattern.observer.java_support.structure.Observer;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 13:14
 */
public class Watcher implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Watcher.update:" + arg);
    }
}
