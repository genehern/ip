package Exceptions;

public class InvalidDeadlineException extends Exception {
    public InvalidDeadlineException() {
        super("Invalid deadline format. Use: deadline <description> /by <date>");
    }
}
