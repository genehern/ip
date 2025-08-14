import java.util.Scanner;

public class Gene {
    public static final String SPACING = "    ";
    private static final String LINE = SPACING + "____________________________";
    private static final int MAX_TASKS = 100;
    private final Task[] tasks = new Task[MAX_TASKS];
    private int currentTask = 0;

    public static void printFormatResponse(String s){
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }

    private static final String greeting =
            SPACING + "Hello! I'm Gene\n" +
            SPACING + "What can I do for you?";

    public void addTask(String s){
        tasks[currentTask] = new Task(s);
        currentTask += 1;
        printFormatResponse(String.format("%s added: %s", SPACING, s));
    }

    public void markTask(int i){
        i -= 1;
        tasks[i].mark();
        printFormatResponse(String.format("Nice! I've marked this task as done:\n%s%s", SPACING, tasks[i].toString()));
    }

    public void unmarkTask(int i){
        i -= 1;
        tasks[i].unmark();
        printFormatResponse(String.format("OK, I've marked this task as not done yet:\n%s%s", SPACING, tasks[i].toString()));
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) break;
            res.append(String.format("%s %d. %s", SPACING, i + 1, tasks[i].toString()));
            if (i + 1 < tasks.length && tasks[i + 1] != null) {
                res.append(System.lineSeparator());
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gene gene = new Gene();
        printFormatResponse(greeting);
        while (true){
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            if (input.equals("bye")) {
               printFormatResponse("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                printFormatResponse(gene.toString());
            }
            else if(inputArr[0].equals("mark")){
                gene.markTask(Integer.parseInt(inputArr[1]));
            }
            else if(inputArr[0].equals("unmark")){
                gene.unmarkTask(Integer.parseInt(inputArr[1]));
            }
            else {
                gene.addTask(input);
            }
        }
    }
}
