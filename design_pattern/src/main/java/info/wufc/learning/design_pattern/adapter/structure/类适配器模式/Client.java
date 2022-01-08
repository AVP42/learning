package info.wufc.learning.design_pattern.adapter.structure.类适配器模式;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 14:38
 */
public class Client {
    public static void main(String[] args) {
        PowerAdapter powerAdapter = new PowerAdapter();
        System.out.println(powerAdapter.output5v());
    }
}
