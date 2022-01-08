package info.wufc.learning.design_pattern.factory.simpleFactory.structure.simplified;

/**
 * @ClassName: Clienct
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 10:29
 */
class Client {
    public static void main(String[] args) {
        SimplifiedAbsProduct factory = SimplifiedAbsProduct.factory(1);
        SimplifiedAbsProduct factory2 = SimplifiedAbsProduct.factory(2);
    }
}
