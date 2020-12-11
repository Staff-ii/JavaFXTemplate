package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Status {
    public String name;
    public int id;

    public Status(int status_id, String name) {
        this.id = status_id;
        this.name = name;
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

    public static ObservableList<Status> getList(){
        ObservableList<Status> list = FXCollections.observableArrayList();

        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        try {
            PreparedStatement statement = conn.getConnection().prepareStatement("SELECT * FROM gr1_STATUS");

            ResultSet results = statement.executeQuery();

            while(results.next()){

                Status tmp =new  Status(results.getInt("status_id"), results.getString("name"));

                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public void update() {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        PreparedStatement statement = null;

            try {
                statement = conn.getConnection().prepareStatement("UPDATE gr1_STATUS SET NAME = '"+name+"' WHERE status_id = "+ id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    public void insert() {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        PreparedStatement statement = null;
        try {
            statement = conn.getConnection().prepareStatement("INSERT INTO gr1_STATUS (NAME) VALUES ('"+name+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete() {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        PreparedStatement statement = null;
        try {
            statement = conn.getConnection().prepareStatement("DELETE FROM gr1_STATUS WHERE status_id = "+ id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
