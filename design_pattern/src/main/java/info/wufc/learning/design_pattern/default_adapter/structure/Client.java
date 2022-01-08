package info.wufc.learning.design_pattern.default_adapter.structure;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-08 17:52
 */
public class Client {
    public static void main(String[] args) {
        AbstractService service = new ConcreteService();
        service.serviceOperation3();
    }
}
