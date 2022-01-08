package info.wufc.learning.design_pattern.composite.structure.transparent;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/11/7/007 21:23
 */
public class Client {

    public static void main(String[] args) {
        Leaf leaf1 = new Leaf();
        Leaf leaf2 = new Leaf();
        Composite composite = new Composite();
        composite.add(leaf1);
        composite.add(leaf2);
        composite.doOperation();
    }

}
