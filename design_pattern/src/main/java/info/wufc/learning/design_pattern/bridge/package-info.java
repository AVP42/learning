/**
 * 桥接模式：
 *     1.用意：将抽象化（abstraction）与实现化（implementation）脱耦, 使得两者可以独立的变化
 *          1.1 抽象化：实体的概念性联系
 *          1.2 实现化：抽象化给出的实现
 *          1.3 脱耦：强关联（比如继承）改成弱关联（比如聚合）
 *        * 由于继承关系将抽象化角色和实现角色做成静态绑定，导致灵活性不够
 *      2.角色：
 *          2.1 抽象化角色：抽象化给出的定义，并持有一个对实现化对象的引用
 *          2.2 修正抽象化角色：用于拓展和修正抽象化角色的定义
 *          2.3 实现化角色：由于进行了脱耦，这里给出了实现化层的接口，其接口定义与抽象化角色的不一定相同，应该只给出底层操作，而抽象化应该给出更高层级的操作
 *          2.4 具体实现化角色：实现化角色的具体实现
 *      3.是否支持开闭
 *          抽象化拓展和实现化拓展 均支持开闭原则
 *      4.应用实例：
 *          任何驱动器都是一种桥梁模式的应用。
 *          使用JDBC驱动器的系统或者应用程序是抽象化角色
 *          Driver与不同数据库的驱动实现是实现化角色 比如mysql oracle等
 *      5.使用
 *          （个人理解）当需要拓展继承体系时，如果发现需要同时拓展同一个层级下面所有的实现，可以抽取出不同的继承等级结构，
 *                      并判断哪个是静态的，哪个是动态的，并将动态的抽出继承体系，作为聚合关系，改造成桥梁模式。这就是对变化的封装
 *                      因为每一个继承等级最好都只封装一种变化，所以应当使用两种独立的等级体系来封装两个不同的变化因素，并使用聚合关系
 *          5.1 实现化角色的退化：只有一个具体实现化角色，不需要抽象实现化角色
 *          5.2 抽象化角色的退化：没有修正抽象化角色
 *          5.3 多个实现类情况：可以通过参数进行逻辑构造，或者预先构造好默认的，再根据情况改变实现
 *          5.4 共享具体实现化：可以实现抽象化角色共享一个具体实现角色
 *
 *      6.与其他模式的关系
 *          6.1 适配器： 看上去很像，但是适配器模式是要改变现有的接口，来适应系统的需求（使两个没有关系的类一起工作）；而桥梁模式强调抽象化与实现化分离，两个角色的接口可以不相同
 *          6.2 策略模式：类图很像，这是应该对比设计模式的初始用意：策略模式是解决不同算法的封装，桥梁模式是要为同一个抽象化角色提供不同的实现
 *          6.3 装饰模式：都是通过聚合关系来避免产生太多子类，对于滥用继承关系使得太多子类的情况，
 *                      装饰模式的办法是：将子类多出来的行为放到单独的类中，然后将描述额外行为的该类的对象封装到子类的基类，通过这些描述额外行为的类排列组合来实现子类的功能
 *                      桥梁模式的办法是：将实现系列抽取出来形成实现化等级结构，并将原有的基类改成抽象化等级结构，而且可以实现连续使用，即该实现化等级结构可以使桥梁模式的抽象化等级结构
 *          6.4 状态模式：桥梁模式的退化版本
 *          6.5 抽象工厂模式：结合使用，比如java中的java构建等级结构（抽象化等级结构） Toolkit(抽象工厂等级结构) peer等级结构（实现化等级结构）
 *
 *
 *
 *
 */
package info.wufc.learning.design_pattern.bridge;