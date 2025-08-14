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
            String[] inputArr = input.split(" ");
            if (input.equals("bye")) {
               printFormatResponse("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                printFormatResponse(tasks.toString());
            }
            else if(inputArr[0].equals("mark")){
                tasks.markTask(Integer.parseInt(inputArr[1]));
            }
            else if(inputArr[0].equals("unmark")){
                tasks.unmarkTask(Integer.parseInt(inputArr[1]));
            }
            else {
                tasks.addTask(input);
            }
        }
    }
}
