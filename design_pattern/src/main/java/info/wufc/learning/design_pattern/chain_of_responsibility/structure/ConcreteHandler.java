package info.wufc.learning.design_pattern.chain_of_responsibility.structure;

/**
 * @description: 具体处理者
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 14:48
 */
public class ConcreteHandler extends Handler {

    public ConcreteHandler(Handler successor){
        setSuccessor(successor);
    }

    @Override
    public void handleRequest() {
        if(getSuccessor() != null){
            System.out.println("The request is passed to " + getSuccessor());
            getSuccessor().handleRequest();
        }else{
            System.out.println("The request is handled here");
        }
    }
}
