package info.wufc.learning.design_pattern.prototype.structure.register;

import info.wufc.learning.design_pattern.prototype.structure.simple.ConcretePrototype;
import info.wufc.learning.design_pattern.prototype.structure.simple.Prototype;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 21:44
 */
public class Client {
    private PrototypeManager manager;
    private Prototype prototype;

    public Client(PrototypeManager manager) {
        this.manager = manager;
    }

    public void registerPrototype() {
        prototype = new ConcretePrototype();
        Prototype clone = prototype.clone();
        manager.add(clone);
    }

    public static void main(String[] args) {
        Client client = new Client(new PrototypeManager());
        client.registerPrototype();
        System.out.println(client.manager.getSize());
    }
}
