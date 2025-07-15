package model;

public class Task {
    private int id;
    private String title;
    private boolean isCompleted;

    public Task(int id, String title, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return isCompleted; }

    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() {
        return title + (isCompleted ? " (Done)" : "");
    }
}
