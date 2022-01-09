package info.wufc.learning.design_pattern.observer.java_support.usage;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 13:20
 */
public class Client {

    public static void main(String[] args) {
        Watched watched = new Watched();
        Watcher watcher1 = new Watcher();
        Watcher watcher2 = new Watcher();
        watched.addObserver(watcher1);
        watched.addObserver(watcher2);
        watched.changeData("hello, data has changed!");

    }
}
