package info.wufc.learning.design_pattern.observer.java_support.structure;

import java.util.Vector;

/**
 * @description: 被观察者对象/主题对象
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 12:21
 */
public class Observable {

    private boolean changed;
    private Vector<Observer> obs;

    public Observable(){
        this.obs = new Vector<>();
    }

    public synchronized void addObserver(Observer o) {
        if(o == null) throw new NullPointerException();
        if(!obs.contains(o)){
            obs.add(o);
        }
    }

    public synchronized void deleteAllObservers(){
        obs.removeAllElements();
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    /**
     * 为什么一个赋值语句也要同步机制，因为可能有线程在<code>notifyObserver</code>中
     * 如果没有同步的话，可能该线程setChanged之后，在notifyObserver方法中的线程线程clearChanged了
     * 这样在该线程想要notify的时候，就会发现changed已经被改为false，无法notify观察者
     */
    protected synchronized void setChanged() {
        changed = true;
    }

    /**
     * clear 方法最好不要被外界调用，因为可能把刚刚setChanged的结果又清楚了，无法通知到观察者
     * 所以使用protected
     */
    protected synchronized void clearChanged(){
        changed = false;
    }

    public void notifyObserver(){
        notifyObserver( null);
    }

    public void notifyObserver(Object arg){
        Object[] arrLocal;

        /*
         * 判断是否changed，拷贝obs快照和清除changed需要同步
         */
        synchronized(this){
            if(!changed) return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (Object obj : arrLocal) {
            ((Observer)obj).update(this, arg);
        }
    }


}
