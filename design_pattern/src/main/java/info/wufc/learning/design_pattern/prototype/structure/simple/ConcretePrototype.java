package info.wufc.learning.design_pattern.prototype.structure.simple;

import java.io.*;

/**
 * @description: 具体原型
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 21:29
 */
public class ConcretePrototype implements Prototype{

    @Override
    public synchronized ConcretePrototype clone() {
        try {
            return (ConcretePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Prototype deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bo);
        out.writeObject(this);

        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bi);
        Object o = in.readObject();
        return (Prototype) o;
    }
}
