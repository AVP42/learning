package info.wufc.learning.design_pattern.interpreter.demonstrate;

/**
 * 抽象表达式
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 20:43
 */
public abstract class Expression {

    public abstract boolean interpret(Context context);


    public abstract boolean equals(Object o);

    public abstract int hashCode();

    public abstract String toString();

}
