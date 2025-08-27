package Gene.Tasks;

import java.time.LocalDateTime;

import Gene.Enums.Commands;

public class DeadlineTask extends Task {
    protected LocalDateTime by;

    public DeadlineTask(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = this.dateTimeParser(by);
    }

    @Override
    public String toDbString() {
        return String.format("%s| %d | %s | %s", Commands.DEADLINE, isDone ? 1 : 0, description, this.dateTimeToDbString(by));
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, this.dateTimeToString(by));
    }
}
