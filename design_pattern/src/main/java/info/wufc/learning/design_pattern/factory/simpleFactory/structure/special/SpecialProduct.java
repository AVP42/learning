package info.wufc.learning.design_pattern.factory.simpleFactory.structure.special;

/**
 * @ClassName: AdvancedSimplifiedAbsProd
 * @Description:1. 当该产品不会有相似的拓展，即只会有一种产品的时候，具体产品也可以作为自己等工厂角色，生产自身实例
 *              2. 如果涉及到循环使用，就需要使用登记的方式将已经创建好的实例保存下来，就可衍生成单例模式和多例模式
 *              3. 当然，单例和多例模式还有其他要求，比如构造器需要私有化等
 * @Info: createdBy alien on 2019/10/19/019 10:32
 */
class SpecialProduct {
    public static SpecialProduct factory() {
        return new SpecialProduct();
    }
}
