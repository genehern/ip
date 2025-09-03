package gene.command;

import gene.TaskList;
import gene.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasksList) {
        Ui.printFormatResponse("Bye. Hope to see you again soon!");
    }
}
