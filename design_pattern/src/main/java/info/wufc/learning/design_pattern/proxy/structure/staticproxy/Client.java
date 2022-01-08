package info.wufc.learning.design_pattern.proxy.structure.staticproxy;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/20/020 12:13
 */
class Client {
     static void main(String[] args) {
        ProxyFoo proxyFoo = ProxyFoo.create();
        proxyFoo.doWork();
    }

}
