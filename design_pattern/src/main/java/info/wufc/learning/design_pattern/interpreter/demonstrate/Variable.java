package info.wufc.learning.design_pattern.interpreter.demonstrate;

/**
 * 终止表达式-变量
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 20:51
 */
public class Variable extends Expression{
    String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public boolean interpret(Context context) {
        return context.lookup(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Variable) {
            return o == this || ((Variable) o).variable == this.variable;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        // 将表达式转换为字符串
        return variable;
    }
}
