import java.util.Scanner;

public class Gene {
    public static final String SPACING = "    ";
    private static final String LINE = SPACING + "____________________________";

    public static void printFormatResponse(String s){
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }

    private static final String greeting =
            SPACING + "Hello! I'm Gene\n" +
            SPACING + "What can I do for you?";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tasks tasks = new Tasks();
        printFormatResponse(greeting);
        while (true){
            String input = sc.nextLine();
            if (input.equals("bye")) {
               printFormatResponse("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")){
                printFormatResponse(tasks.toString());
            }
            else {
                tasks.addTask(input);
            }
        }
    }
}
