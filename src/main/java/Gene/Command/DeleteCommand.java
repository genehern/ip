package Gene.Command;

import Gene.TaskList;
import Gene.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasksList) {
        try {
            Ui.printFormatResponse(tasksList.deleteTask(index));
        } catch (Exception e) {
            Ui.printFormatResponse(Ui.SPACING + e.getMessage());
        }
    }
}
