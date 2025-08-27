import Exceptions.CreateTaskException;
import Exceptions.EmptyTodoException;
import Exceptions.InvalidDeadlineException;
import Exceptions.InvalidEventException;

import Enums.Commands;
import Tasks.DeadlineTask;
import Tasks.EventTask;
import Tasks.TodoTask;

import java.util.Scanner;


public class Gene {
    ;
    private final TaskList tasksList;
    private final Ui ui = new Ui();

    public Gene(String filePath) {
        Storage storage = new Storage(filePath);
        this.tasksList = new TaskList(storage);
    }


    @Override
    public String toString() {
        return this.tasksList.toString();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        this.ui.printGreeting();

        while (true) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ", 2);
            try {
                String commandStr = inputArr[0].toUpperCase();
                Commands command = Commands.valueOf(commandStr);
                switch (command) {
                    case BYE:
                        this.ui.printFormatResponse("Bye. Hope to see you again soon!");
                        return;
                    case LIST:
                        this.ui.printFormatResponse(this.toString());
                        break;
                    case MARK:
                        this.ui.printFormatResponse(this.tasksList.markTask(Integer.parseInt(inputArr[1])));
                        break;
                    case UNMARK:
                        this.ui.printFormatResponse(this.tasksList.unmarkTask(Integer.parseInt(inputArr[1])));
                        break;
                    case DELETE:
                        this.ui.printFormatResponse(this.tasksList.deleteTask(Integer.parseInt(inputArr[1])));
                        break;
                    case TODO:
                        if (inputArr.length < 2) {
                            throw new EmptyTodoException();
                        }
                        this.ui.printFormatResponse(this.tasksList.addTask(new TodoTask(inputArr[1], false)));
                        break;
                    case DEADLINE:
                        String[] parts = inputArr[1].split(" /by ", 2);
                        if (parts.length < 2) {
                            throw new InvalidDeadlineException();
                        } else {
                            this.ui.printFormatResponse(this.tasksList.addTask(new DeadlineTask(parts[0], parts[1], false)));
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
                        this.tasksList.addTask(new EventTask(fromSplit[0], toSplit[0], toSplit[1], false));
                        break;
                }
            } catch (IllegalArgumentException e) {
                this.ui.printFormatResponse(Ui.SPACING + "I'm sorry, but I don't know what that means :-(");
            } catch (CreateTaskException e) {
                this.ui.printFormatResponse(Ui.SPACING + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Gene("./data/gene.txt").run();
    }
}
