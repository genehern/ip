package Gene;

public class Ui {

    public static final String SPACING = "    ";
    public static final String LINE = SPACING + "____________________________";
    private static final String GREETING =
            Ui.SPACING + "Hello! I'm Gene\n" +
                    Ui.SPACING + "What can I do for you?";

    public static void printFormatResponse(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }

    public static void printGreeting() {
        printFormatResponse(GREETING);
    }
}

