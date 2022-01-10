package info.wufc.learning.design_pattern.prototype.structure.simple;

import java.io.IOException;
import java.io.Serializable;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 21:28
 */
public interface Prototype extends Cloneable, Serializable {

    Prototype clone();

    Prototype deepClone() throws IOException, ClassNotFoundException;
}
