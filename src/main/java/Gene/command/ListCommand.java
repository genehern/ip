package gene.command;

import gene.Ui;
import gene.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList tasksList) {
        return tasksList.toString();
    }
}
