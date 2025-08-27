package Gene.Command;

import Gene.TaskList;
import Gene.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasksList) {
        Ui.printFormatResponse(tasksList.markTask(index));
    }
}
