package Exceptions;

public class InvalidEventException extends Exception {
    public InvalidEventException() {
        super("Invalid event format. Use: event <description> /from <start> /to <end>");
    }
}
