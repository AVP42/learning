package info.wufc.learning.design_pattern.chain_of_responsibility.structure;

/**
 * @description: 抽象处理角色
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-09 14:46
 */
public abstract class Handler {
    protected Handler successor;

    public abstract void handleRequest();


    public void setSuccessor(Handler h){
        this.successor = h;
    }

    public Handler getSuccessor() {
        return successor;
    }
}
