package Gene;

import Gene.Enums.Commands;
import Gene.Tasks.DeadlineTask;
import Gene.Tasks.EventTask;
import Gene.Tasks.Task;
import Gene.Tasks.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import java.util.ArrayList;

public class Storage {
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String type = parts[0].trim();
                boolean isMarked = parts[1].trim().equals("1");
                String description = parts[2].trim();
                String firstDate = parts.length > 3 ? parts[3].trim() : "";
                String secondDate = parts.length > 4 ? parts[4].trim() : "";
                Task task = null;
                try {
                    Commands commandType = Commands.valueOf(type);
                    switch (commandType) {
                        case TODO:
                            task = new TodoTask(description, isMarked);
                            break;
                        case DEADLINE:
                            task = new DeadlineTask(description, firstDate, isMarked);
                            if (isMarked) task.mark();
                            break;
                        case EVENT:
                            task = new EventTask(description, firstDate, secondDate, isMarked);
                            break;
                        default:
                            continue;
                    }
                } catch (IllegalArgumentException e) {
                    continue;
                }
                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found: " + fileName);
        }
        return tasks;
    }

    public void saveTasksToFile(ArrayList<Task> tasksList) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Task task : tasksList) {
                writer.write(task.toDbString() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }
}
