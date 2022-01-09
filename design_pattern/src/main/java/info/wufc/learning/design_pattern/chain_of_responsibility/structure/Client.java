package info.wufc.learning.design_pattern.chain_of_responsibility.structure;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 14:52
 */
public class Client {
    public static void main(String[] args) {
        // 责任链是由客户端来创建的
        ConcreteHandler handler = new ConcreteHandler(new ConcreteHandler(null));
        handler.handleRequest();
    }
}
