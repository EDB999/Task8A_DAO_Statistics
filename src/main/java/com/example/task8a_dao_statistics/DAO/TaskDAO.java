package com.example.task8a_dao_statistics.DAO;

import com.example.task8a_dao_statistics.Components.Task;
import com.example.task8a_dao_statistics.DataConnector;
import com.example.task8a_dao_statistics.Interfaces.ITask;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class TaskDAO implements ITask {
    @Override
    public Collection<Task> getAllTasks() throws SQLException {
        var connect = DataConnector.addDataSource().getConnection();

        PreparedStatement state = connect.prepareStatement("select * from \"TaskDAO_Statistics\"");

        var table = state.executeQuery();

        Collection<Task> list = new ArrayList<>();
        while (table.next()){
            list.add(new Task(table.getInt("id_statics"),table.getString("place"),
                    table.getString("Member_Name"),table.getString("Member_Surname"),
                    table.getString("Member_Patronymic"), table.getString("role"),
                    table.getString("time"))
            );
        }
        return list;
    }

    @Override
    public Task getTask(int id) throws SQLException {
        var connect = DataConnector.addDataSource().getConnection();

        PreparedStatement state = connect.prepareStatement("select * from \"TaskDAO_Statistics\" where id_statics = ?");

        state.setInt(1, id);

        var table = state.executeQuery();

        Task task = null;

        while (table.next()){
            task = new Task(table.getInt("id_statics"),table.getString("place"),
                    table.getString("Member_Name"),table.getString("Member_Surname"),
                    table.getString("Member_Patronymic"), table.getString("role"),
                    table.getString("time"));
        }
        return task;
    }

    @Override
    public void addTask(Task task) throws SQLException {
        var connect = DataConnector.addDataSource().getConnection();

        PreparedStatement state = connect.prepareStatement("insert into \"TaskDAO_Statistics\"(place, name, surname, patronymic, role, time) values (?,?,?,?,?,?)");
        state.setString(1,task.place);
        state.setString(2,task.name);
        state.setString(3,task.surname);
        state.setString(4,task.patronymic);
        state.setString(5,task.role);
        state.setString(6,task.time);

        state.executeUpdate();
    }

    @Override
    public void updateTask(Task task) throws SQLException {
        var connect = DataConnector.addDataSource().getConnection();

        PreparedStatement state = connect.prepareStatement("update \"TaskDAO_Statistics\" set place = ?, name = ?, surname = ?, patronymic = ?, role = ?, time = ? where id_statics = ?");
        state.setString(1,task.place);
        state.setString(2,task.name);
        state.setString(3,task.surname);
        state.setString(4,task.patronymic);
        state.setString(5,task.role);
        state.setString(6,task.time);

        state.executeUpdate();
    }

    @Override
    public void deleteTask(int id) throws SQLException {
        var connect = DataConnector.addDataSource().getConnection();

        PreparedStatement state = connect.prepareStatement("delete from \"TaskDAO_Statistics\" where id_statics = ?");
        state.setInt(1, id);

        state.executeUpdate();
    }
}
