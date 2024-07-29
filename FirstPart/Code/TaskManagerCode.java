package Code;

import LL.*;
import Queue.*;
import Stack.*;

public class TaskManagerCode {
    private MyQueue<Task> tasksByDueDate;
    private MyQueue<Task> completedTasks; 
    private LL<CategoryTasks> tasksByCategory;
    private MyStack<Task> urgentTasks; 

    public TaskManagerCode() {
        this.tasksByDueDate = new MyQueue<>();
        this.completedTasks = new MyQueue<>(); 
        this.tasksByCategory = new LL<>();
        this.urgentTasks = new MyStack<>(); 
    }

    public void addTask(Task task) {
        this.tasksByDueDate.enqueueByDate(task);
        addTaskToCategory(task);
        if (task.getPriority().equals("urgent")) { 
            urgentTasks.push(task); 
        }
    }

    private void addTaskToCategory(Task task) {
        boolean found = false;
        Node<CategoryTasks> current = tasksByCategory.getHead();
        while (current != null) {
            if (current.item.getCategory().equals(task.getCategory())) {
                current.item.getTasks().insertNode(task);
                found = true;
                break;
            }
            current = current.next;
        }
        if (!found) {
            CategoryTasks newCategoryTasks = new CategoryTasks(task.getCategory());
            newCategoryTasks.getTasks().insertNode(task);
            tasksByCategory.insertNode(newCategoryTasks);
        }
    }

    public void markTaskAsComplete(Task task) {
        task.setCompleted(true);
        tasksByDueDate.remove(task);
        completedTasks.enqueue(task); 
    }

    public MyStack<Task> getUrgentTasks() {
        return urgentTasks.isEmpty() ? null : urgentTasks;
    }

    public LL<Task> getTasksByCategory(String category) {
        Node<CategoryTasks> current = tasksByCategory.getHead();
        while (current != null) {
            if (current.item.getCategory().equals(category)) {
                return current.item.getTasks();
            } 
            current = current.next;
        }
        return new LL<>(); 
    }

    public MyQueue<Task> getCompletedTasks() {
        return completedTasks;
    }

    public boolean isEmpty() {
        return tasksByDueDate.isEmpty();
    }

    public String getAllTasks() {
        StringBuilder builder = new StringBuilder();
        MyQueue<Task> tempQueue = new MyQueue<>();
        int initialSize = tasksByDueDate.size();   
    
        for (int i = 0; i < initialSize; i++) {
            Task task = tasksByDueDate.dequeue();
            builder.append(task.toString()).append("\n");
            tempQueue.enqueueByDate(task);
        }
    
        for (int i = 0; i < initialSize; i++) {
            tasksByDueDate.enqueueByDate(tempQueue.dequeue());
        }
    
        return builder.toString();
    }

    // Convert all tasks to LL<Task>
    public LL<Task> getAllTasksLL() {
        LL<Task> taskList = new LL<>();
        MyQueue<Task> tempQueue = new MyQueue<>(); // Temporary queue to preserve order
        int initialSize = tasksByDueDate.size();   
    
        for (int i = 0; i < initialSize; i++) {
            Task task = tasksByDueDate.dequeue();
            taskList.insertNode(task);
            tempQueue.enqueueByDate(task);
        }
    
        for (int i = 0; i < initialSize; i++) {
            tasksByDueDate.enqueueByDate(tempQueue.dequeue());
        }
    
        return taskList;
    }

    // Convert urgent tasks to LL<Task>
    public LL<Task> getUrgentTasksLL() {
        return convertStackToLL(urgentTasks);
    }

    // Convert completed tasks to LL<Task>
    public LL<Task> getCompletedTasksLL() {
        return convertQueueToLL(completedTasks);
    }

    private LL<Task> convertStackToLL(MyStack<Task> myStack) {
        LL<Task> taskList = new LL<>();
        MyStack<Task> tempStack = new MyStack<>(); // Temporary stack to preserve original stack

        while (myStack != null && !myStack.isEmpty()) {
            Task task = myStack.pop();
            taskList.insertNode(task);
            tempStack.push(task); // Push to temporary stack to preserve original
        }

        // Restore original stack
        while (!tempStack.isEmpty()) {
            myStack.push(tempStack.pop());
        }

        return taskList;
    }

    private LL<Task> convertQueueToLL(MyQueue<Task> myQueue) {
        LL<Task> taskList = new LL<>();
        MyQueue<Task> tempQueue = new MyQueue<>(); // Temporary queue to preserve original queue

        while (myQueue != null && !myQueue.isEmpty()) {
            Task task = myQueue.dequeue();
            taskList.insertNode(task);
            tempQueue.enqueue(task); // Enqueue to temporary queue to preserve original
        }

        // Restore original queue
        while (!tempQueue.isEmpty()) {
            myQueue.enqueue(tempQueue.dequeue());
        }

        return taskList;
    }

    // Get all categories and their tasks
    public LL<CategoryTasks> getAllCategories() {
        return tasksByCategory;
    }
}
