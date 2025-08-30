package Gene.Command;

import Gene.TaskList;
import Gene.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasksList) {
        Ui.printFormatResponse(tasksList.unmarkTask(index));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UnmarkCommand)) return false;
        UnmarkCommand other = (UnmarkCommand) o;
        return this.index == other.index;
    }
}
