/**
 * 观察者模式 发布-订阅模式， 模型-视图模式
 *      0. 理解：
 *          0.1 一句话总结: 观察者模式，通过观察者订阅主题，当主题发生变化时，会通知所有的观察者。
 *      1. 用意：当一个对象发生变化时，其他对象需要作出相应的改变，可以使用观察者模式来实现低耦合，高协作
 *      2. 角色：
 *          2.1
 *      3. 是否满足开闭原则
 *      4. 应用：
 *          4.1 基于观察者模式的事件处理模型(DEM) VS 基于责任链模式的事件处理模型
 *              在DEM -- Delegation Event Model 委派事件模型中，主题Subject发布publish事件，
 *              观察者角色订阅subscribe感兴趣的事件，当一个具体主题产生一个事件时，就会通知所有感兴趣的订阅则
 *              使用DEM的用语， publisher又叫事件源event source，subscriber 又叫事件监听器event listener
 *
 *      5. 使用：
 *          5.1 java 内置的Observable 和 Observer接口
 *      6. 与其他模式的关系
 *          6.1 与备忘录模式：观察者模式中使用了备忘录模式，展示将观察者对象存储在被观察者对象里面，避免被观察者集合在处理notify过程中发生了变化
 *          6.2 与MVC模式：MVC模式是架构模式，架构模式就是描述一个软件系统的基本结构。
 *              MVC模式包含3个角色：Model（模型），View（视图），Controller（控制器）
 *              观察者模式可以用来实现MVC模式。Observable就是Model+Controller; Observer就是View
 *              MVC模式是观察者模式，策略模式，合成模式等模式的组合
 *      7. 实例
 *          7.1 DEM事件机制被引用到了所有的java事件机制上，比如Servlet技术的事件处理机制
 *          7.2 比如SAX2浏览器的时间出来机制
 *      8. 优缺点
 *          8.1 优点：
 *              1） 在observable和observer之间建立一个抽象的耦合，
 *                  observable只聚合了observer，而不知道具体是什么类型的observer
 *              2） 支持广播
 *          8.2 缺点：
 *              1） 如果被观察者之间有循环依赖，将会导致不停的通知，最终导致崩溃
 *
 *
 *
 */
package info.wufc.learning.design_pattern.observer;