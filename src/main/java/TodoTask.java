public class TodoTask extends Task {
    public TodoTask(String s, boolean b) {
        super(s, b);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
