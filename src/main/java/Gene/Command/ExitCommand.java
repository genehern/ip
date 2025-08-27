package Gene.Command;

import Gene.TaskList;
import Gene.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasksList) {
        Ui.printFormatResponse("Bye. Hope to see you again soon!");
    }
}
