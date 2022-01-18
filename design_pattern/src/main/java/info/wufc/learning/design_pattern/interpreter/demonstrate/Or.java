package info.wufc.learning.design_pattern.interpreter.demonstrate;

/**
 * 非终结表达式-或门
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 20:56
 */
public class Or extends Expression {
    Expression left;
    Expression right;

    public Or(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Context context) {
        return left.interpret(context) || right.interpret(context);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Or) {
            return o == this ||
                    (((Or) o).left.equals(this.left) && ((Or) o).right.equals(this.right));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " OR " + right.toString() + ")";
    }
}
