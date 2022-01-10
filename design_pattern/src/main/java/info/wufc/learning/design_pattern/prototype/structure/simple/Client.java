package info.wufc.learning.design_pattern.prototype.structure.simple;

import java.io.IOException;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 21:33
 */
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Prototype prototype = new ConcretePrototype();
        Prototype clone = prototype.clone();
        Prototype prototype1 = prototype.deepClone();
        System.out.println(prototype1);
    }
}
