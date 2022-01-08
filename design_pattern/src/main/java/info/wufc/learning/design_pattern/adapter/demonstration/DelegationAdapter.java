package info.wufc.learning.design_pattern.adapter.demonstration;

/**
 * @ClassName: Adapter
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 14:52
 */
public class DelegationAdapter  implements Puppie {
    private Kittie kittie;

    public DelegationAdapter(Kittie kittie) {
        this.kittie = kittie;
    }

    @Override
    public void wao() {
        kittie.miao();
    }

    @Override
    public void fetchBall() {
        kittie.catchRat();
    }

    @Override
    public void run() {
        kittie.run();
    }

    @Override
    public void sleep() {
        kittie.sleep();
    }
}
