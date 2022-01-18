package info.wufc.learning.design_pattern.interpreter.demonstrate;

/**
 * 终结表达式-true/false
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 20:49
 */
public class Constant extends Expression{

    Boolean val;

    public Constant(Boolean val) {
        this.val = val;
    }

    @Override
    public boolean interpret(Context context) {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Constant) {
            return o == this || ((Constant) o).val == this.val;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }


    @Override
    public String toString() {
        // 将表达式转换为字符串
        return Boolean.toString(val);
    }
}
