import Exceptions.TaskOutOfRangeException;
import Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    public TaskList() {
    }

    private final ArrayList<Task> tasks = new ArrayList<>();

    public String addTask(Task task) {
        tasks.add(task);
        return String.format("%sGot it. I've added this task:\n%s   %s\n%sNow you have %d tasks in the list.",
                Ui.SPACING, Ui.SPACING, task.toString(), Ui.SPACING, tasks.size());
    }

    public String markTask(int i) {
        int idx = i - 1;
        tasks.get(idx).mark();
        return String.format("Nice! I've marked this task as done:\n%s%s", Ui.SPACING, tasks.get(idx).toString());
    }

    public String unmarkTask(int i) {
        int idx = i - 1;
        tasks.get(idx).unmark();
        return String.format("OK, I've marked this task as not done yet:\n%s%s", Ui.SPACING, tasks.get(idx).toString());
    }

    public String deleteTask(int i) throws TaskOutOfRangeException {
        int idx = i - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskOutOfRangeException();
        }
        Task removed = tasks.remove(idx);
        return String.format("%sNoted. I've removed this task:\n%s   %s\n%sNow you have %d tasks in the list.",
                Ui.SPACING, Ui.SPACING, removed.toString(), Ui.SPACING, tasks.size());
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return Ui.SPACING + "You have no tasks in your list.";
        }
        StringBuilder res = new StringBuilder();
        res.append(Ui.SPACING).append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            res.append(String.format("%s %d. %s", Ui.SPACING, i + 1, tasks.get(i).toString()));
            if (i != tasks.size() - 1) {
                res.append("\n");
            }
        }
        return res.toString();
    }

}
