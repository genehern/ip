package Tasks;

import java.time.LocalDateTime;

import Enums.Commands;

public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public EventTask(String description, String from, String to, boolean b) {
        super(description, b);
        this.from = this.dateTimeParser(from);
        this.to = this.dateTimeParser(to);
    }

    @Override
    public String toDbString() {
        return String.format("%s | %d | %s | %s | %s", Commands.EVENT, isDone ? 1 : 0, description, this.dateTimeToDbString(from), this.dateTimeToDbString(to));
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", getStatusIcon(), description, this.dateTimeToString(from), this.dateTimeToString(to));
    }
}
