package info.wufc.learning.design_pattern.adapter.structure.对象适配器模式;

/**
 * @ClassName: PowerAdapter
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 17:09
 */

/**
 * adapter角色--依赖source，实现destination
 */
public class PowerAdapter implements DC5_destination {
    private AC220_source mAC220;

    public PowerAdapter(AC220_source mAC220) {
        this.mAC220 = mAC220;
    }

    @Override
    public int output5v() {
        return mAC220.output220v()/44;
    }
}
