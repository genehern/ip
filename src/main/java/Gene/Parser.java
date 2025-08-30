package Gene;

import Gene.Command.AddCommand;
import Gene.Command.ExitCommand;
import Gene.Command.Command;
import Gene.Command.ListCommand;
import Gene.Command.MarkCommand;
import Gene.Command.UnmarkCommand;
import Gene.Command.DeleteCommand;
import Gene.Command.PrintCommand;
import Gene.Enums.Commands;
import Gene.Exceptions.CreateTaskException;
import Gene.Exceptions.EmptyTodoException;
import Gene.Exceptions.InvalidDeadlineException;
import Gene.Exceptions.InvalidEventException;
import Gene.Tasks.DeadlineTask;
import Gene.Tasks.EventTask;
import Gene.Tasks.TodoTask;

public class Parser {
    public static Command parse(String input) {
        String[] inputArr = input.split(" ", 2);
        Command c = null;
        try {
            String commandStr = inputArr[0].toUpperCase();
            Commands command = Commands.valueOf(commandStr);
            switch (command) {
                case BYE:
                    c = new ExitCommand();
                    break;
                case LIST:
                    c = new ListCommand();
                    break;
                case MARK:
                    c = new MarkCommand(Integer.parseInt(inputArr[1]));
                    break;
                case UNMARK:
                    c = new UnmarkCommand(Integer.parseInt(inputArr[1]));
                    break;
                case DELETE:
                    c = new DeleteCommand(Integer.parseInt(inputArr[1]));
                    break;
                case TODO:
                    if (inputArr.length < 2) {
                        throw new EmptyTodoException();
                    }
                    c = new AddCommand(new TodoTask(inputArr[1], false));
                    break;
                case DEADLINE:
                    String[] parts = inputArr[1].split(" /by ", 2);
                    if (parts.length < 2) {
                        throw new InvalidDeadlineException();
                    } else {
                        c = new AddCommand(new DeadlineTask(parts[0], parts[1], false));
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
                    c = new AddCommand(new EventTask(fromSplit[0], toSplit[0], toSplit[1], false));
                    break;
            }
        } catch (IllegalArgumentException e) {
            c = new PrintCommand(Ui.SPACING + "I'm sorry, but I don't know what that means :-(");
        } catch (CreateTaskException e) {
            c = new PrintCommand(Ui.SPACING + e.getMessage());
        }
        return c;
    }
}
