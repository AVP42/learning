package info.wufc.learning.design_pattern.bridge.structure;

/**
 * @ClassName: RedefineAbstraction
 * @Description: 修正抽象化接口
 * @Info: createdBy alien on 2019/10/20/020 23:17
 */
public  class RedefineAbstraction extends Abstraction{


    /**
     * 某个商业方法在修正抽象化角色上的实现
     */
    @Override
    public void doWork() {
        System.out.println("RedefineAbstraction.doWork");
    }
}
