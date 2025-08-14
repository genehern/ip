public class TodoTask extends Task{
    public TodoTask(String s){
        super(s);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
