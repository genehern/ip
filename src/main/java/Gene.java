import Exceptions.EmptyTodoException;
import Exceptions.InvalidDeadlineException;
import Exceptions.InvalidEventException;

import Enums.Commands;
import Tasks.DeadlineTask;
import Tasks.EventTask;
import Tasks.Task;
import Tasks.TodoTask;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

public class Gene {
    public static final String FILE_PATH = "./data/gene.txt";
    private final TaskList tasksList = new TaskList();
    private final Ui ui = new Ui();

    private static final String greeting =
            Ui.SPACING + "Hello! I'm Gene\n" +
                    Ui.SPACING + "What can I do for you?";

    @Override
    public String toString() {
        return this.tasksList.toString();
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
                this.tasksList.addTask(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found: " + filename);
        }
    }

    public void saveTasksToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Task task : this.tasksList) {
                writer.write(task.toDbString() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gene gene = new Gene();
        gene.ui.printFormatResponse(greeting);

        gene.loadTasksFromFile(FILE_PATH);

        while (true) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ", 2);
            try {
                String commandStr = inputArr[0].toUpperCase();
                Commands command = Commands.valueOf(commandStr);
                switch (command) {
                    case BYE:
                        gene.ui.printFormatResponse("Bye. Hope to see you again soon!");
                        return;
                    case LIST:
                        gene.ui.printFormatResponse(gene.toString());
                        break;
                    case MARK:
                        gene.ui.printFormatResponse(gene.tasksList.markTask(Integer.parseInt(inputArr[1])));
                        break;
                    case UNMARK:
                        gene.ui.printFormatResponse(gene.tasksList.unmarkTask(Integer.parseInt(inputArr[1])));
                        break;
                    case DELETE:
                        gene.ui.printFormatResponse(gene.tasksList.deleteTask(Integer.parseInt(inputArr[1])));
                        break;
                    case TODO:
                        if (inputArr.length < 2) {
                            throw new EmptyTodoException();
                        }
                        gene.ui.printFormatResponse(gene.tasksList.addTask(new TodoTask(inputArr[1], false)));
                        break;
                    case DEADLINE:
                        String[] parts = inputArr[1].split(" /by ", 2);
                        if (parts.length < 2) {
                            throw new InvalidDeadlineException();
                        } else {
                            gene.ui.printFormatResponse(gene.tasksList.addTask(new DeadlineTask(parts[0], parts[1], false)));
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
                        gene.tasksList.addTask(new EventTask(fromSplit[0], toSplit[0], toSplit[1], false));
                        break;
                }
                //gene.saveTasksToFile(FILE_PATH);
            } catch (IllegalArgumentException e) {
                gene.ui.printFormatResponse(Ui.SPACING + "I'm sorry, but I don't know what that means :-(");
            } catch (Exception e) {
                gene.ui.printFormatResponse(Ui.SPACING + e.getMessage());
            }
        }
    }
}
