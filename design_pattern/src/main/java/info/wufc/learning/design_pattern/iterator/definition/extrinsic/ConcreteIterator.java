package info.wufc.learning.design_pattern.iterator.definition.extrinsic;

/**
 * @ClassName: ConcreteIterator
 * @Description: TODO
 * @Info: createdBy alien on 2019/12/2/002 22:20
 */
public class ConcreteIterator implements Iterator {
    /** 持有聚集对象*/
    private ConcreteAggregate aggregate;

    /** 持有游标*/
    private int cursor=0;

    private int size=0;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
        size = aggregate.getSize();
        cursor = 0;
    }

    @Override
    public void first() {
        // 仅需要移动光标即可
        cursor = 0;
    }

    @Override
    public void next() {
        if (cursor < size) {
            cursor++;
        }
    }

    @Override
    public Object current() {
        return aggregate.getElement(cursor);
    }

    @Override
    public boolean isDone() {
        return cursor >= size;
    }
}
