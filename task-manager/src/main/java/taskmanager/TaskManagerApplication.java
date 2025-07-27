package taskmanager;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import taskmanager.model.SubTask;
import taskmanager.model.Task;
import taskmanager.service.TaskService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

    private final TaskService taskService;


    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> showAllTasks();
                case 2 -> showOverdueTasks();
                case 3 -> showTasksByCategory(scanner);
                case 4 -> addNewTask(scanner);
                case 5 -> deleteTask(scanner);
                case 6 -> {
                    System.out.println("Exiting Task Manager. Bye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Task Manager Menu ===");
        System.out.println("1. Show all tasks");
        System.out.println("2. Show overdue tasks");
        System.out.println("3. Show tasks by category");
        System.out.println("4. Add new task");
        System.out.println("5. Delete task");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");
    }

    private void showAllTasks() {
        taskService.getAllTasks().forEach(System.out::println);
    }

    private void showOverdueTasks() {
        taskService.getOverdueTasks().forEach(System.out::println);
    }

    private void showTasksByCategory(Scanner scanner) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        taskService.getTasksByCategory(category).forEach(System.out::println);
    }

    private void addNewTask(Scanner scanner) {
        Task task = new Task();
        task.setCreatedAt(LocalDateTime.now());
        System.out.print("Enter task name: ");
        task.setName(scanner.nextLine());
        System.out.print("Enter description: ");
        task.setDescription(scanner.nextLine());
        System.out.print("Enter deadline (YYYY-MM-DDTHH:MM): ");
        task.setDeadline(LocalDateTime.parse(scanner.nextLine()));
        System.out.print("Enter category: ");
        task.setCategory(scanner.nextLine());

        System.out.print("How many subtasks? ");
        int subtaskCount = Integer.parseInt(scanner.nextLine());
        List<SubTask> subtasks = new ArrayList<>();
        for (int i = 0; i < subtaskCount; i++) {
            SubTask subTask = new SubTask();
            System.out.print("Subtask name: ");
            subTask.setName(scanner.nextLine());
            System.out.print("Subtask description: ");
            subTask.setDescription(scanner.nextLine());
            subtasks.add(subTask);
        }
        task.setSubtasks(subtasks);

        taskService.saveTask(task);
        System.out.println("Task added successfully.");
    }

    private void deleteTask(Scanner scanner) {
        System.out.print("Enter task ID to delete: ");
        String id = scanner.nextLine();
        taskService.deleteTask(id);
        System.out.println("Task deleted successfully.");
    }
}
