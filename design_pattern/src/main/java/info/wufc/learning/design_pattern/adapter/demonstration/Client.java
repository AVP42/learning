package info.wufc.learning.design_pattern.adapter.demonstration;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 14:56
 */
public class Client {
    public static void main(String[] args) {
        new ExtendsAdapter().wao();
        new DelegationAdapter(new Kittie()).fetchBall();
    }

}
