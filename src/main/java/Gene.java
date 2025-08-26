import Exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Gene {
    public static final String SPACING = "    ";
    private static final String LINE = SPACING + "____________________________";
    private final ArrayList<Task> tasks = new ArrayList<>();

    public static void printFormatResponse(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }

    private static final String greeting =
            SPACING + "Hello! I'm Gene\n" +
                    SPACING + "What can I do for you?";

    public void addTask(Task task) {
        tasks.add(task);
        printFormatResponse(String.format("%sGot it. I've added this task:\n%s   %s\n%sNow you have %d tasks in the list.",
                SPACING, SPACING, task.toString(), SPACING, tasks.size()));
    }

    public void markTask(int i) {
        int idx = i - 1;
        tasks.get(idx).mark();
        printFormatResponse(String.format("Nice! I've marked this task as done:\n%s%s", SPACING, tasks.get(idx).toString()));
    }

    public void unmarkTask(int i) {
        int idx = i - 1;
        tasks.get(idx).unmark();
        printFormatResponse(String.format("OK, I've marked this task as not done yet:\n%s%s", SPACING, tasks.get(idx).toString()));
    }

    public void deleteTask(int i) throws TaskOutOfRangeException {
        int idx = i - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskOutOfRangeException();
        }
        Task removed = tasks.remove(idx);
        printFormatResponse(String.format("%sNoted. I've removed this task:\n%s   %s\n%sNow you have %d tasks in the list.",
                SPACING, SPACING, removed.toString(), SPACING, tasks.size()));
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            res.append(String.format("%s %d. %s", SPACING, i + 1, tasks.get(i).toString()));
            if (i + 1 < tasks.size()) {
                res.append(System.lineSeparator());
            }
        }
        return res.toString();
    }

    public void loadTasksFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String type = parts[0].trim();
                boolean isMarked = parts[1].trim().equals("1");
                String description = parts[2].trim();
                String firstDate = parts.length > 3 ? parts[3].trim() : "";
                String secondDate = parts.length > 4 ? parts[4].trim() : "";
                Task task = null;
                try {
                    Commands commandType = Commands.valueOf(type);
                    switch (commandType) {
                        case TODO:
                            task = new TodoTask(description, isMarked);
                            break;
                        case DEADLINE:
                            task = new DeadlineTask(description, firstDate, isMarked);
                            if (isMarked) task.mark();
                            break;
                        case EVENT:
                            task = new EventTask(description, firstDate, secondDate, isMarked);
                            break;
                        default:
                            continue;
                    }
                } catch (IllegalArgumentException e) {
                    continue;
                }
                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gene gene = new Gene();
        printFormatResponse(greeting);

        gene.loadTasksFromFile("./data/gene.txt");

        while (true) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ", 2);
            try {
                String commandStr = inputArr[0].toUpperCase();
                Commands command = Commands.valueOf(commandStr);

                switch (command) {
                    case BYE:
                        printFormatResponse("Bye. Hope to see you again soon!");
                        return;
                    case LIST:
                        printFormatResponse(gene.toString());
                        break;
                    case MARK:
                        gene.markTask(Integer.parseInt(inputArr[1]));
                        break;
                    case UNMARK:
                        gene.unmarkTask(Integer.parseInt(inputArr[1]));
                        break;
                    case DELETE:
                        gene.deleteTask(Integer.parseInt(inputArr[1]));
                        break;
                    case TODO:
                        if (inputArr.length < 2) {
                            throw new EmptyTodoException();
                        }
                        gene.addTask(new TodoTask(inputArr[1], false));
                        break;
                    case DEADLINE:
                        String[] parts = inputArr[1].split(" /by ", 2);
                        if (parts.length < 2) {
                            throw new InvalidDeadlineException();
                        } else {
                            gene.addTask(new DeadlineTask(parts[0], parts[1], false));
                        }
                        break;
                    case EVENT:
                        String[] fromSplit = inputArr[1].split(" /from ", 2);
                        if (fromSplit.length < 2) {
                            throw new InvalidEventException();
                        }
                        String[] toSplit = fromSplit[1].split(" /to ", 2);
                        if (toSplit.length < 2) {
                            throw new InvalidEventException();
                        }
                        gene.addTask(new EventTask(fromSplit[0], toSplit[0], toSplit[1]));
                        break;
                    default:
                        System.out.println(SPACING + "I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                printFormatResponse(SPACING + e.getMessage());
            }
        }
    }
}
