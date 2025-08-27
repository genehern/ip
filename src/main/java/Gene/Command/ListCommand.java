package Gene.Command;

import Gene.Ui;
import Gene.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasksList) {
        Ui.printFormatResponse(tasksList.toString());
    }
}
