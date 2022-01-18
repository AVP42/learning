package info.wufc.learning.design_pattern.interpreter.demonstrate;

/**
 * 非终结表达式-非门
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 21:02
 */
public class Not extends Expression{
    Expression e;

    public Not(Expression e) {
        this.e = e;
    }

    @Override
    public boolean interpret(Context context) {
        return !e.interpret(context);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Not) {
            return o == this && ((Not) o).e.equals(this.e);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + "NOT " + e.toString() + ")";
    }
}
