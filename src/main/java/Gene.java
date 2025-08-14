import java.util.Scanner;

public class Gene {
    private static final String LINE = "____________________________________________________________\n";

    private static final String SPACING = "    ";

    private static void printFormatResponse(String s){
        System.out.println(LINE);
        System.out.println(SPACING + s);
        System.out.println(LINE);
    }

    private static void printGreeting(){ System.out.println(LINE +
            "Hello! I'm Gene\n" +
            "What can I do for you?\n" +
            LINE);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       printGreeting();

        while (true){
            String input = sc.nextLine();
            if (input.equals("bye")) {
               printFormatResponse("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(LINE);
            System.out.println("     " + input);
            System.out.println(LINE);
        }
    }
}
