package Gene.Command;

import Gene.TaskList;
import Gene.Tasks.Task;
import Gene.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasksList) {
        Ui.printFormatResponse(tasksList.addTask(task));
    }
}
