package info.wufc.learning.design_pattern.command.structure;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @description: 宏命令（合成模式, 这里没必要知道parent，所以没有完全按照合成模式的结构来组织）
 * @author: fuchang.wu@foxmail.com
 * @date: 2022-01-10 21:01
 */
public class ConcreteMacroCommand implements MicroCommand{
    Collection<Command> commands = new ArrayList<>();

    @Override
    public void execute() {
        System.out.println("MacroCommand.execute");
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void add(Command c){
        commands.add(c);
    }

    @Override
    public void remove(Command c) {
        commands.removeIf(command -> command.equals(c));
    }
}
