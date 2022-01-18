package info.wufc.learning.design_pattern.visitor.problem;

import info.wufc.learning.design_pattern.visitor.structure.ConcreteNodeA;
import info.wufc.learning.design_pattern.visitor.structure.ConcreteNodeB;

import java.util.Arrays;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 11:23
 */
public class Client {

    static ObjectStructure objStructure;
    public static void main(String[] args) {
        objStructure = new ObjectStructure(Arrays.asList(new ConcreteNodeA(), new ConcreteNodeB()));
        objStructure.foreach();
    }
}
