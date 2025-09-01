package Gene.Command;

import Gene.Tasks.Task;
import Gene.TaskList;
import Gene.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    public void execute(TaskList tasksList) {
        ArrayList<Task> tasksListKeyword = tasksList.findKeyword(this.keyword);

        class Helper {
            String formatTaskList(ArrayList<Task> tasks) {
                if (tasksListKeyword.isEmpty()) {
                    return Ui.SPACING + "No records found.";
                }
                StringBuilder res = new StringBuilder();
                res.append(Ui.SPACING).append("Here are the matching" +
                        " task in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    res.append(String.format("%s %d. %s", Ui.SPACING, i + 1, tasks.get(i).toString()));
                    if (i != tasks.size() - 1) {
                        res.append("\n");
                    }
                }
                return res.toString();
            }
        }

        Ui.printFormatResponse(new Helper().formatTaskList(tasksListKeyword));
    }
}
