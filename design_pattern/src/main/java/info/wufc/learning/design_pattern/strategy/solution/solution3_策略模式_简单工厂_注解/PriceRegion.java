package info.wufc.learning.design_pattern.strategy.solution.solution3_策略模式_简单工厂_注解;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义有效价格区间，给具体策略角色添加，表示在该区间的具体实现由该策略角色来实现
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceRegion {
    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;
}
