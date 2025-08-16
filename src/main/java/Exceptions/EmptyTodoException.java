package Exceptions;

public class EmptyTodoException extends Exception {
    public EmptyTodoException() {
        super("The description of a todo cannot be empty.");
    }
}
