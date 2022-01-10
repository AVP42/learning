package info.wufc.learning.design_pattern.command.structure;

/**
 * @description:
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 21:05
 */
public interface MicroCommand extends Command {

    void add(Command c);

    void remove(Command c);

}
