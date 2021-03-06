package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Priority {
    private int id;
    private String name;

    public Priority(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<Priority> getList() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM gr1_PRIORITAET");

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Priority tmp = new Priority(results.getInt("prioritaet_id"), results.getString("NAME"));

                list.add(tmp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

    public void update() {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        PreparedStatement statement = null;
        try {
            statement = conn.getConnection().prepareStatement("UPDATE gr1_PRIORITAET SET NAME = '" + name + "' WHERE prioritaet_id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert() {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        PreparedStatement statement = null;
        try {
            statement = conn.getConnection().prepareStatement("INSERT INTO gr1_PRIORITAET (NAME) VALUES ('" + name + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete() {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        PreparedStatement statement = null;
        try {
            statement = conn.getConnection().prepareStatement("DELETE FROM gr1_PRIORITAET WHERE prioritaet_id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
