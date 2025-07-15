package ui;

import db.TaskDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Task;

public class TodoController {
    @FXML private TextField taskInput;
    @FXML private ListView<Task> taskList;

    private final TaskDAO dao = new TaskDAO();

    @FXML
    public void initialize() {
        refreshTaskList();
    }

    @FXML
    public void handleAddTask() {
        String title = taskInput.getText();
        if (!title.isEmpty()) {
            dao.addTask(title);
            taskInput.clear();
            refreshTaskList();
        }
    }
    
    @FXML
    public void handleEditTask() {
        Task selected = taskList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TextInputDialog dialog = new TextInputDialog(selected.getTitle());
            dialog.setTitle("Edit Task");
            dialog.setHeaderText("Edit the selected task");
            dialog.setContentText("New title:");

            dialog.showAndWait().ifPresent(newTitle -> {
                if (!newTitle.trim().isEmpty()) {
                    dao.updateTaskTitle(selected.getId(), newTitle.trim());
                    refreshTaskList();
                }
            });
        }
    }
	

    @FXML
    public void handleDeleteTask() {
        Task selected = taskList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dao.deleteTask(selected.getId());
            refreshTaskList();
        }
    }

    @FXML
    public void handleMarkComplete() {
        Task selected = taskList.getSelectionModel().getSelectedItem();
        if (selected != null && !selected.isCompleted()) {
            dao.markAsComplete(selected.getId());
            refreshTaskList();
        }
    }

    private void refreshTaskList() {
        taskList.getItems().setAll(dao.getAllTasks());
    }
}
