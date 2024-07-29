package Chatbot;

import Code.Task;
import Code.TaskManagerCode;
import Code.CategoryTasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import LL.*;

public class TaskManagerChatbot {
    private TaskManagerCode taskManager;
    private Scanner scanner;

    public TaskManagerChatbot() {
        this.taskManager = new TaskManagerCode();
        this.scanner = new Scanner(System.in);
    }

    public void startChat() {
        System.out.println("Welcome to the Task Manager Chatbot!");
        boolean running = true;
        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a new task");
            System.out.println("2. Display tasks by due date");
            System.out.println("3. Mark a task as completed");
            System.out.println("4. View completed tasks history");
            System.out.println("5. Retrieve urgent tasks");
            System.out.println("6. Sort/Show tasks by category");
            System.out.println("7. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    displayTasksByDueDate();
                    break;
                case 3:
                    markTaskAsCompleted();
                    break;
                case 4:
                    viewCompletedTasksHistory();
                    break;
                case 5:
                    retrieveUrgentTasks();
                    break;
                case 6:
                    sortTasksByCategory();
                    break;
                case 7:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void addTask() {
        System.out.println("\nAdd a New Task:");
        System.out.print("Name: ");
        String name = scanner.nextLine().toLowerCase();

        String day;
        String month;
        String year;
        LocalDate dueDate;

        // Validate day, month, and year input
        while (true) {
            try {
                System.out.print("Due Date (day): ");
                day = scanner.nextLine();
                int dayInt = Integer.parseInt(day);
                if (dayInt < 1 || dayInt > 31) {
                    throw new NumberFormatException("Day must be between 1 and 31");
                }

                System.out.print("Due Date (month): ");
                month = scanner.nextLine();
                int monthInt = Integer.parseInt(month);
                if (monthInt < 1 || monthInt > 12) {
                    throw new NumberFormatException("Month must be between 1 and 12");
                }

                System.out.print("Due Date (year): ");
                year = scanner.nextLine();
                int yearInt = Integer.parseInt(year);
                if (yearInt < LocalDate.now().getYear()) {
                    throw new NumberFormatException("Year must be equal to or greater than the current year");
                }

                // Attempt to create the LocalDate object to ensure the date is valid
                dueDate = LocalDate.of(yearInt, monthInt, dayInt);
                if (dueDate.isBefore(LocalDate.now())) {
                    System.out.println("Error: Due date cannot be in the past.");
                    continue;
                }
                break;
            } catch (NumberFormatException | DateTimeException e) {
                System.out.println("Error: Invalid date. " + e.getMessage());
            }
        }

        String priority;
        while (true) {
            System.out.print("Priority (urgent/normal): ");
            priority = scanner.nextLine().toLowerCase();
            if (priority.equals("urgent") || priority.equals("normal")) {
                break;
            } else {
                System.out.println("Invalid priority. Please enter 'urgent' or 'normal'.");
            }
        }

        System.out.print("Category: ");
        String category = scanner.nextLine();

        Task newTask = new Task(name, day, month, year, priority, category);
        taskManager.addTask(newTask);
        System.out.println("Task added successfully!");
    }

    private void displayTasksByDueDate() {
        System.out.println("\nTasks Ordered by Due Date:");
        String tasks = taskManager.getAllTasks();
        System.out.println(tasks.isEmpty() ? "No tasks found." : tasks);
    }

    private void markTaskAsCompleted() {
        System.out.println("\nMark Task as Completed:");
        System.out.print("Task Name: ");
        String name = scanner.nextLine().toLowerCase();

        LL<Task> allTasks = taskManager.getAllTasksLL();
        Node<Task> current = allTasks.getHead();
        boolean found = false;
        while (current != null) {
            if (current.item.getName().equals(name)) {
                taskManager.markTaskAsComplete(current.item);
                found = true;
                System.out.println("Task marked as completed.");
                break;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("Task not found.");
        }
    }

    private void viewCompletedTasksHistory() {
        System.out.println("\nCompleted Tasks History:");
        StringBuilder builder = new StringBuilder();
        LL<Task> completedTasks = taskManager.getCompletedTasksLL();
        Node<Task> current = completedTasks.getHead();

        while (current != null) {
            builder.append(current.item.toString()).append("\n");
            current = current.next;
        }

        String tasks = builder.toString();
        System.out.println(tasks.isEmpty() ? "No completed tasks found." : tasks);
    }

    private void retrieveUrgentTasks() {
        System.out.println("\nUrgent Tasks:");
        StringBuilder builder = new StringBuilder();
        LL<Task> urgentTasks = taskManager.getUrgentTasksLL();
        Node<Task> current = urgentTasks.getHead();

        while (current != null) {
            builder.append(current.item.toString()).append("\n");
            current = current.next;
        }

        String tasks = builder.toString();
        System.out.println(tasks.isEmpty() ? "No urgent tasks found." : tasks);
    }

    private void sortTasksByCategory() {
        System.out.println("\nTasks by Category:");

        LL<CategoryTasks> allCategories = taskManager.getAllCategories();
        Node<CategoryTasks> categoryNode = allCategories.getHead();
        if (categoryNode == null) {
            System.out.println("No tasks found.");
            return;
        }

        while (categoryNode != null) {
            CategoryTasks categoryTasks = categoryNode.item;
            System.out.println("Category: " + categoryTasks.getCategory());
            LL<Task> tasks = categoryTasks.getTasks();
            if (tasks.isEmpty()) {
                System.out.println("  No tasks found in this category.");
            } else {
                Node<Task> taskNode = tasks.getHead();
                while (taskNode != null) {
                    System.out.println("  " + taskNode.item);
                    taskNode = taskNode.next;
                }
            }
            categoryNode = categoryNode.next;
        }
    }

    public static void main(String[] args) {
        TaskManagerChatbot chatbot = new TaskManagerChatbot();
        chatbot.startChat();
    }
}
