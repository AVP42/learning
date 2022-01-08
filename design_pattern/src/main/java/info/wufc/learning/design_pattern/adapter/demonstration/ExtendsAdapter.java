package info.wufc.learning.design_pattern.adapter.demonstration;

/**
 * @ClassName: Adapter
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 14:52
 */
public class ExtendsAdapter extends Kittie implements Puppie {
    @Override
    public void wao() {
        super.miao();
    }

    @Override
    public void fetchBall() {
        super.catchRat();
    }
}
