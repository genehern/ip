package Gene.Command;

import Gene.TaskList;
import Gene.Ui;

public class PrintCommand extends Command {
    private final String msg;

    public PrintCommand(String msg) {
        super(false);
        this.msg = msg;
    }

    @Override
    public void execute(TaskList tasksList) {
        Ui.printFormatResponse(msg);
    }
}
