package info.wufc.learning.design_pattern.interpreter.demonstrate;

/**
 * 客户端
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 21:06
 */
public class Client {

    private static Expression exp;
    private static Context cxt;
    public static void main(String[] args) {
        cxt = new Context();
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Constant c = new Constant(true);
        cxt.assign(x, false);
        cxt.assign(y, true);
        exp = new Or(new And(c, x), new And(y, new Not(x)));
        System.out.println(exp.toString() + " = " + exp.interpret(cxt));

    }
}
