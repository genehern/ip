package Gene;

import Gene.Command.Command;

import java.util.Scanner;


public class Gene {
    private final TaskList tasksList;

    public Gene(String filePath) {
        Storage storage = new Storage(filePath);
        this.tasksList = new TaskList(storage);
    }


    @Override
    public String toString() {
        return this.tasksList.toString();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        Ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            String input = sc.nextLine();
            Command c = Parser.parse(input);
            c.execute(this.tasksList);
            isExit = c.isExit();

        }
    }

    public static void main(String[] args) {
        new Gene("./data/gene.txt").run();
    }
}
