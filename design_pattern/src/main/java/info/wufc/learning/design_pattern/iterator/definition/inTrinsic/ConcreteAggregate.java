package info.wufc.learning.design_pattern.iterator.definition.inTrinsic;

/**
 * @ClassName: ConcreteAggregate
 * @Description: TODO
 * @Info: createdBy alien on 2019/12/2/002 22:19
 */
public class ConcreteAggregate extends AbstractAggregate {
    private Object[] objects = new Object[]{"alien","jacob"};


    @Override
    public Iterator iterate() {
        return new ConcreteIterator();
    }

    /**
     * @ClassName: ConcreteIterator
     * @Description: TODO
     * @Info: createdBy alien on 2019/12/2/002 22:20
     */
    public class ConcreteIterator implements Iterator {
        /** 持有游标*/
        private int cursor=0;


        public ConcreteIterator() {
            cursor = 0;
        }

        @Override
        public void first() {
            // 仅需要移动光标即可
            cursor = 0;
        }

        @Override
        public void next() {
            if (cursor < objects.length) {
                cursor++;
            }
        }

        @Override
        public Object current() {
            return objects[cursor];
        }

        @Override
        public boolean isDone() {
            return cursor >= objects.length;
        }
    }
}
