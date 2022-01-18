package info.wufc.learning.design_pattern.interpreter.demonstrate;

/**
 * 非终结表达式-与门
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 20:56
 */
public class And extends Expression {
    Expression left;
    Expression right;

    public And(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Context context) {
        return left.interpret(context) && right.interpret(context);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof And) {
            return o == this ||
                    (((And) o).left.equals(this.left) && ((And) o).right.equals(this.right));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " AND " + right.toString() + ")";
    }
}
