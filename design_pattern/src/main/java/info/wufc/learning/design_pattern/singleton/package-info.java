/**
 * 单例模式：
 *      1.单例应当满足的3个条件：
 *          1.1 只能有1个实例
 *          1.2 自行创建对象
 *          1.3 自行提供对象
 *       2.应用：
 *          2.1 java.lang.RunTime
 *          2.2 java.beans.Introspector
 *       3.与其他模式的联系
 *          3.1 使用了简单工厂模式，是简单工厂模式的特殊的简化模式，只有一个具体实现类
 *       4. 不完成单例模式（默认实例模式 Default Instance Pattern）：即开放构造子，不推荐，是一种不负责任的方式
 *       5.
 *
 *
 */
package info.wufc.learning.design_pattern.singleton;