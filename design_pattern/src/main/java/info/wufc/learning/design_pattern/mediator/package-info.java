/**
 *  【行为模式】调停者模式
 *      0. 理解：
 *          0.1 一句话概述：为了解决相互作用的多个对象之间因为耦合带来的问题，引入一个调停者角色，
 *                  使得对象与同事对象之间的相互作用被对象与调停者之间的相互作用替代，将多对多转变为1对多，
 *                  从而降低耦合，体现迪米特法则
 *          0.2 类比
 *              1）：中国加入WTO， WTO是mediator，中国是colleague
 *              2）：工作组中，小组长是mediator，组员是colleague
 *      1. 用意：通过中间协调者(调停者)，协调对象之间的相互作用，增加协作性，减少耦合度
 *      2. 角色：
 *          2.1 抽象调停者
 *          2.2 具体调停者
 *          2.3 抽象同事类
 *          2.4 具体同事类
 *      3. 是否支持开闭
 *      4. 应用：解决因为错综复杂的相互作用关系导致的 修改一组相互作用关系时也会影响其他相互作用对象 的问题
 *      5. 使用：
 *          5.1 条件：
 *              1） 对象的责任要清晰划分，避免产生不适当的复杂关系
 *              2） 同事对象之间是存在相互作用关系的，而不是单纯的pojo，mediator是协调者，不是对pojo的处理者
 *                     封装针对的是行为和状态，纯数据类的划分时没有道理和意义的。
 *
 *      6. 与其他模式的关系
 *          6.1 门面模式：两者都用来给出一个低耦合度的系统;
 *                      门面系统为一个子系统提供一个简单的接口(facade)，外部对象与子系统是cs架构，
 *                      只能外部对象发起请求，通过facade接口转发给子系统；
 *                      把外部对象和子系统看成一对同事对象的话，facade只能处理单向的请求。
 *                      而调停者与同事对象的相互作用是多方向的。
 *           6.2 观察者模式：两者功能类似，是竞争关系，只能二选一。观察者模式通过依赖观察者对象(observer)和主题对象(topic)
 *                  来达到将通信分散化的目的。
 *                  而调停者模式则封装了对象之间的通信，将他们之间的通信封装到一个中介对象上了。
 *                  观察者模式还是多对多方式，一个观察主题可以有多个观察者对象，一个观察者对象也可以观察多个主题。
 *                  观察者模式将性能分散到多个独享，更容易达到复用的目的。但是由于一次通信都涉及了多个对象，比较不容易读懂。
 *                  而调停者是比较容易读懂的，但是因为调停者模式将事件处理集中化，所以不适合大型系统的事件处理机制。
 *            6.3 适配器模式：可以结合使用。调停者对象经常使用适配器对象来接收同事对象的状态变化
 *      7. 实例：
 *      8. 优缺点：
 *          8.1 优点：网状关系图变为星型关系图，多对多作用关系变为一对多作用关系，减少同事对象之间的耦合
 *          8.2 缺点：代价就是增加了调停者对象的复杂度，在很多情况下，设置一个调停者并不比没有调停者的时候更好。
 *                  因为调停者中充满了各种协调代码，这些代码通常是不可用的。
 *                  因此调停者模式提供的拓展性是一种向同事对象倾斜的可拓展性
 *
 *
 */
package info.wufc.learning.design_pattern.mediator;