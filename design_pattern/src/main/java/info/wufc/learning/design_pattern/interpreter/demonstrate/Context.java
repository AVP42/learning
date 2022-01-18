package info.wufc.learning.design_pattern.interpreter.demonstrate;

import java.util.HashMap;
import java.util.Map;

/**
 * 环境角色
 *
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-15 20:44
 */
public class Context {
    Map<Variable, Boolean> map = new HashMap<>();

    public void assign(Variable variable, Boolean val) {
        map.put(variable, val);
    }

    public Boolean lookup(Variable variable){
        if (!map.containsKey(variable)) {
            throw new IllegalArgumentException();
        }
        return map.get(variable);
    }
}
