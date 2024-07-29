package Code;
import LL.LL;

public class CategoryTasks {
    private String category;
    private LL<Task> tasks;

    public CategoryTasks(String category) {
        this.category = category;
        this.tasks = new LL<>();
    }

    public String getCategory() {
        return category;
    }

    public LL<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.insertNode(task);
    }
}

