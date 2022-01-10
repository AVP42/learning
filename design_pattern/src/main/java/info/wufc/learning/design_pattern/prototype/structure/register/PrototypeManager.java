package info.wufc.learning.design_pattern.prototype.structure.register;

import info.wufc.learning.design_pattern.prototype.structure.simple.Prototype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description: 原型管理器
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 21:35
 */
public class PrototypeManager {
    private List<Prototype> objects = new ArrayList<>();

    public void add(Prototype p){
        objects.add(p);
    }

    public Prototype getPrototype(int idx) {
        return objects.get(idx);
    }

    public int getSize(){
        return objects.size();
    }

}
