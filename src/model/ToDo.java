package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDo {
    private int id;
    private String name;
    private String description;

    public ToDo(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ObservableList<ToDo> getList() {
        ObservableList<ToDo> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM gr1_TODO");

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                ToDo tmp = new ToDo(results.getInt("todo_id"), results.getString("name"), results.getString("description"));

                list.add(tmp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

    @Override
    public String toString() {
        return name;
    }
}
