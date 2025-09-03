package gene.command;

import gene.Ui;
import gene.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasksList) {
        Ui.printFormatResponse(tasksList.toString());
    }
}
