package info.wufc.learning.design_pattern.facade.definition;

/**
 * @ClassName: Facade
 * @Description: TODO
 * @Info: createdBy alien on 2018/10/7/007 16:21
 */
public class Facade {
    private SubSystemA subSystemA = new SubSystemA();
    private SubSystemB subSystemB = new SubSystemB();
    private SubSystemC subSystemC = new SubSystemC();

    public void start() {
        subSystemA.subSystemAMethodStart();
        subSystemB.subSystemBMethodStart();
        subSystemC.SubSystemCMethodStart();
    }

    public void end() {
        subSystemA.subSystemAMethodEnd();
        subSystemB.subSystemBMethodEnd();
        subSystemC.SubSystemCMethodEnd();
    }
}
