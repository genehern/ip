public class Tasks {
    private final Task[] tasks = new Task[100];
    private int currentTask = 0;

    public void addTask(String s){
        tasks[currentTask] = new Task(s);
        currentTask += 1;
        Gene.printFormatResponse(String.format("%s added: %s",Gene.SPACING, s));
    }

    public void markTask(int i){
        i -= 1;
        tasks[i].mark();
        Gene.printFormatResponse(String.format("Nice! I've marked this task as done:\n%s%s", Gene.SPACING, tasks[i].toString()));
    }

    public void unmarkTask(int i){
        i -= 1;
        tasks[i].unmark();
        Gene.printFormatResponse(String.format("OK, I've marked this task as not done yet:\n%s%s", Gene.SPACING, tasks[i].toString()));
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) break;
            res.append(String.format("%s %d. %s", Gene.SPACING, i + 1, tasks[i].toString()));
            if (i + 1 < tasks.length && tasks[i + 1] != null) {
                res.append(System.lineSeparator());
            }
        }
        return res.toString();
    }
}
