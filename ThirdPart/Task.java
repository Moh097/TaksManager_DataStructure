import java.time.DateTimeException;
import java.time.LocalDate;

public class Task {
    private String name;
    private String day;
    private String month;
    private String year;
    private String priority;
    private String category;
    private boolean isCompleted;

    public Task(String name, String day, String month, String year, String priority, String category) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.priority = priority;
        this.category = category;
        this.isCompleted = false;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Method to convert day, month, year strings to a LocalDate object
    public LocalDate getDueDate() {
        try {
            int yearInt = Integer.parseInt(year);
            int monthInt = Integer.parseInt(month);
            int dayInt = Integer.parseInt(day);
            return LocalDate.of(yearInt, monthInt, dayInt);
        } catch (NumberFormatException | DateTimeException e) {
            System.err.println("Invalid date format: " + e.getMessage());
            return null;
        }
    }

    // toString method for displaying task details
    @Override
    public String toString() {
        return "Task " + name +
                ", dueDate=" + day + "-" + month + "-" + year +
                ", priority='" + priority +
                ", category='" + category +
                ", isCompleted=" + isCompleted;
    }
}