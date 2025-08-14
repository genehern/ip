public class Tasks {
    public Tasks(){

    }
    private final String[] tasks = new String[100];
    private int currentTask = 0;

    public void addTask(String s){
        tasks[currentTask] = s;
        currentTask += 1;
        Gene.printFormatResponse(String.format("%s added: %s",Gene.SPACING, s));
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) break;
            res.append(String.format("%s %d. %s", Gene.SPACING, i + 1, tasks[i]));
            if (i + 1 < tasks.length && tasks[i + 1] != null) {
                res.append(System.lineSeparator());
            }
        }
        return res.toString();
    }
}
