package info.wufc.learning.design_pattern.adapter.structure.对象适配器模式;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 14:01
 */
public class Client {
    public static void main(String[] args) {
        PowerAdapter adapter = new PowerAdapter(new AC220_source());
        System.out.println(adapter.output5v());
    }

}
