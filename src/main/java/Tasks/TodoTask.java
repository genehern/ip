package Tasks;

import Enums.Commands;

public class TodoTask extends Task {
    public TodoTask(String s, boolean b) {
        super(s, b);
    }

    @Override
    public String toDbString() {
        return String.format("%s | %d | %s", Commands.TODO, isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
