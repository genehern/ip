package Gene.Exceptions;

public class InvalidDeadlineException extends CreateTaskException {
    public InvalidDeadlineException() {
        super("Invalid deadline format. Use: deadline <description> /by <date>");
    }
}
