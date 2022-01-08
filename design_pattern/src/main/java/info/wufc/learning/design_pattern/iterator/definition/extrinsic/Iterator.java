package info.wufc.learning.design_pattern.iterator.definition.extrinsic;

/**
 * @ClassName: AbstractIterator
 * @Description: 抽象迭代子角色
 * @Info: createdBy alien on 2019/12/2/002 22:15
 */
public interface Iterator {
    void first();

    void next();

    Object current();

    boolean isDone();

}
