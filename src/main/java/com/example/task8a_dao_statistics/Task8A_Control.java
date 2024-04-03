package com.example.task8a_dao_statistics;

import com.example.task8a_dao_statistics.DAO.TaskDAO;
import com.example.task8a_dao_statistics.Components.Task;
import com.example.task8a_dao_statistics.Interfaces.ITask;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Task8A_Control implements Initializable {
    @FXML
    public ListView<String> listTask;
    @FXML
    public TextField id_stat;
    @FXML
    public TextField placeField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField surnameField;
    @FXML
    public TextField patronymicField;
    @FXML
    public TextField roleField;
    @FXML
    public TextField timeField;
    private ITask taskDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskDao = new TaskDAO();
        try {
            var list = taskDao.getAllTasks().stream().map(Task::toString).toList();
            System.out.println(list.get(0));


            listTask.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchButtonClick(ActionEvent actionEvent) throws SQLException {
        if (Objects.equals(id_stat.getText(), "")){
            var list = taskDao.getAllTasks().stream().map(Task::toString).toList();
            listTask.getItems().clear();
            listTask.setItems(FXCollections.observableArrayList(list));
            return;
        }

        var item = taskDao.getTask(Integer.parseInt(id_stat.getText()));


        listTask.getItems().clear();
        listTask.setItems(FXCollections.observableArrayList(item.toString()));
    }

    public void deleteButtonClick(ActionEvent actionEvent) throws SQLException {
        taskDao.deleteTask(Integer.parseInt(id_stat.getText()));
        var list = taskDao.getAllTasks().stream().map(Task::toString).toList();
        listTask.getItems().clear();
        listTask.setItems(FXCollections.observableArrayList(list));
    }

    public void addButtonClick(ActionEvent actionEvent) {
        try {
            String place = placeField.getText();
            String name = nameField.getText();
            String surname = surnameField.getText();
            String patronymic = patronymicField.getText();
            String role = roleField.getText();
            String time = timeField.getText();

            Task newTask = new Task(0, place, name, surname, patronymic, role, time);

            taskDao.addTask(newTask);

            // Очистить поля после добавления
            clearFields();

            // Обновить список задач
            refreshTaskList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateButtonClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(id_stat.getText());
            String place = placeField.getText();
            String name = nameField.getText();
            String surname = surnameField.getText();
            String patronymic = patronymicField.getText();
            String role = roleField.getText();
            String time = timeField.getText();

            Task updatedTask = new Task(id, place, name, surname, patronymic, role, time);

            taskDao.updateTask(updatedTask);

            // Очистить поля после обновления
            clearFields();

            // Обновить список задач
            refreshTaskList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        placeField.clear();
        nameField.clear();
        surnameField.clear();
        patronymicField.clear();
        roleField.clear();
        timeField.clear();
    }

    private void refreshTaskList() throws SQLException {
        var list = taskDao.getAllTasks().stream().map(Task::toString).toList();
        listTask.getItems().clear();
        listTask.setItems(FXCollections.observableArrayList(list));
    }
}