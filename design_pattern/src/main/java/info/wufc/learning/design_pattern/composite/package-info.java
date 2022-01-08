/**
 * 合成模式：结构模式  又称 部分-整体模式（part-whole）
 *      1.用意：合成模式将对象组织到树结构中，可以用来描述部分与整体的关系；可以使得客户端将原子元素与组合元素同等看待。
 *      2.角色：
 *              2.1 抽象构件（component）：给出共有的接口与默认行为
 *              2.2 树叶构建（leaf）:没有下级的子对象，定义原子行为
 *              2.3 树枝构件（composite）: 包含0个或多个子对象的组合对象（子对象可以使树叶构建对象，也可以是树枝构件对象），并给出树枝构件对象的行为
 *          * 根据所实现的接口的区别可以分为：
 *               安全的合成模式：即在树枝构件composite中声明所有的用来管理子对象的方法，包括add remove 以及getChild（）方法，不够透明
 *               透明的合成模式：即在抽象构建中声明所有的用来管理子对象的方法，不够安全
 *          * 实现拓展：
 *              1） 在子对象中显示持有父对象的引用(实现双向树图)，可以比较方便的遍历父对象，使得方便引用责任模式
 *                      在子对象add到父对象时，需要设置子对象的父对象，反之，remove时，需要移除子对象的父对象引用
 *              2） 由于系统可能需要多次遍历，可以将遍历子对象的结果缓存到父构件中
 *      3.优缺点与使用场景：
 *          3.1 使用场景：
 *              需要描述对象的部分与整体的等级结构
 *              需要客户端忽略个体构件与组合构件的区别
 *          3.2 优点：
 *              比较容易增加新的构件（包括树叶构件与树枝构件）
 *              客户端不需要知道是树叶还是树枝
 *          3.3 缺点：
 *              不太容易控制树枝构件的类型
 *              使用继承的方式来增加新的行为很困难
 *                      比如需要在component角色中增加一个行为，如果不修改该类，使用继承方式来拓展，但是已有的树叶和树枝节点 不受影响；
 *                          为了保持多态，只能每个角色都构建子类，将新行为加到子类中
 *
 *      4.应用：
 *          文件系统
 *      5.与其他模式的关系
 *          与命令模式：可以应用到命令的合成上，几个具体命令合成宏命令
 *          与装饰模式：装饰模式一般是只持有1个对象
 *          与迭代子模式：内部使用迭代子模式类遍历子类对象
 *          与责任链模式：一个责任链模式往往应用到一个树结构中，会使用到合成模式，装饰模式，迭代子模式
 *          与享元模式：应用到了工厂模式，合成模式，其中复合享元对象就是树枝构件
 *
 */
package info.wufc.learning.design_pattern.composite;