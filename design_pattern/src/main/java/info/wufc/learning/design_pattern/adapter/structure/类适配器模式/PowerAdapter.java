package info.wufc.learning.design_pattern.adapter.structure.类适配器模式;

/**
 * @ClassName: PowerAdapter
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 17:09
 */

/**
 * adapter角色--继承source，实现destination
 */
public class PowerAdapter extends AC220_source implements DC5_destination{

    @Override
    public int output5v() {
        return output220v()/44;
    }
}
