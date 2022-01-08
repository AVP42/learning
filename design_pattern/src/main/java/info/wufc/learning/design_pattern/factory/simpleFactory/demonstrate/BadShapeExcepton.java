package info.wufc.learning.design_pattern.factory.simpleFactory.demonstrate;

/**
 * @ClassName: BadShapeExcepton
 * @Description: TODO
 * @Info: createdBy alien on 2019/10/19/019 11:12
 */
class BadShapeExcepton extends RuntimeException{
    public BadShapeExcepton() {
        super();
    }

    public BadShapeExcepton(String message) {
        super(message);
    }

    public BadShapeExcepton(String message, Throwable cause) {
        super(message, cause);
    }

    public BadShapeExcepton(Throwable cause) {
        super(cause);
    }

    protected BadShapeExcepton(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
