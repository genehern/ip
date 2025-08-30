package Gene;

import org.junit.jupiter.api.Test;

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

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void todo_success() {
        Command c = Parser.parse("todo cs2103 homework");
        Command actual = new AddCommand(new TodoTask("cs2103 homework", false));
        assertEquals(c, actual);
    }

    @Test
    public void event_success() {
        Command c = Parser.parse("event cs2103 homework /from 2023-01-01 1900 /to 2025-01-01 2000");
        Command actual = new AddCommand(new EventTask("cs2103 homework", "2023-01-01 1900", "2025-01-01 2000", false));
        assertEquals(c, actual);
    }

    @Test
    public void deadline_success() {
        Command c = Parser.parse("deadline cs2103 homework /by 2023-01-01 1900");
        Command actual = new AddCommand(new DeadlineTask("cs2103 homework", "2023-01-01 1900", false));
        assertEquals(c, actual);
    }

    @Test
    public void exit_command_success() {
        Command c = Parser.parse("Bye");
        assertInstanceOf(ExitCommand.class, c);
    }

    @Test
    public void list_command_success() {
        Command c = Parser.parse("List");
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    public void mark_command_success() {
        Command c = Parser.parse("Mark 3");
        Command actual = new MarkCommand(3);
        assertEquals(c, actual);
    }

    @Test
    public void unmark_command_success() {
        Command c = Parser.parse("Unmark 3");
        Command actual = new UnmarkCommand(3);
        assertEquals(c, actual);
    }

    @Test
    public void print_invalid_command_success() {
        Command c = Parser.parse("randomCommand to fail");
        Command actual = new PrintCommand(Ui.SPACING + "I'm sorry, but I don't know what that means :-(");
        assertEquals(c, actual);
    }

    @Test
    public void print_invalid_syntax_success() {
        Command c = Parser.parse("event cs2103 homework");
        Command actual = new PrintCommand(Ui.SPACING + "Invalid event format. Use: event <description> /from <start> /to <end>");
        assertEquals(c, actual);
    }


}
