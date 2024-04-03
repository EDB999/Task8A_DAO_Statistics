package com.example.task8a_dao_statistics.Interfaces;

import com.example.task8a_dao_statistics.Components.Task;

import java.util.Collection;
import java.sql.SQLException;


public interface ITask {
    public Collection<Task> getAllTasks() throws SQLException;
    public Task getTask(int id_stat) throws SQLException;

    public void addTask(Task task) throws SQLException;

    public void updateTask(Task task) throws SQLException;
    public void deleteTask(int id_stat) throws SQLException;

}
