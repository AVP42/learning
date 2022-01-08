package info.wufc.learning.design_pattern.iterator.definition.extrinsic;

/**
 * @ClassName: ConcreteAggregate
 * @Description: TODO
 * @Info: createdBy alien on 2019/12/2/002 22:19
 */
public class ConcreteAggregate extends AbstractAggregate {
    private Object[] objects = new Object[]{"alien","jacob"};


    @Override
    public Iterator iterate() {
        return new ConcreteIterator(this);
    }

    /** 白箱聚合 提供宽接口*/
    public Object getElement(int index) {
        if (index >= objects.length) {
            return null;
        }else{
            return objects[index];
        }
    }

    /** 白箱聚合 提供宽接口*/
    public int getSize() {
        return objects == null ? 0 : objects.length;
    }
}
