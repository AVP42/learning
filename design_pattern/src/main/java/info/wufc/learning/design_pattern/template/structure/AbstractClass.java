package info.wufc.learning.design_pattern.template.structure;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-29 10:24
 */
 abstract class AbstractClass {

    /**
     * 模版方法
     */
    public void templateMethod(){
        // 基本方法-抽象方法
         doOperation1();
        // 基本方法-抽象方法
         doOperation2();
        // 基本方法--具体方法，已经实现
         doOperation3();
         // 基本方法--钩子方法，空实现
         doOperation4();
     }


    protected abstract void doOperation1();

    protected abstract void doOperation2();

    private void doOperation3() {
        System.out.println("具体方法");
    }

    private void doOperation4() {
       // 钩子方法，空实现，以do开头，比如各种执行前调用，执行后调用
    }
}
