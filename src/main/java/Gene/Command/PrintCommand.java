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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PrintCommand)) return false;
        PrintCommand other = (PrintCommand) o;
        return this.msg.equals(other.msg);
    }
}
