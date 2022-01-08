package info.wufc.learning.design_pattern.iterator.definition.extrinsic;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/12/2/002 22:36
 */
public class Client {
    public static void main(String[] args) {
        ConcreteAggregate aggregate = new ConcreteAggregate();
        Iterator iterate = aggregate.iterate();
        while (!iterate.isDone()) {
            Object current = iterate.current();
            System.out.println(current);
            iterate.next();
        }
    }
}
