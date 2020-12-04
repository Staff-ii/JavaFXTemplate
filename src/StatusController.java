import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Priority;
import model.Status;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusController {
    public ListView<Status> statusListView;
    public TextField nameTextField;
    public Status selectedItem = null;

    public void initialize(){

       statusListView.setItems(Status.getList());
    }
    public void itemSelected(MouseEvent mouseEvent) {

        Status s = statusListView.getSelectionModel().getSelectedItem();
        if (s!= null){
            nameTextField.setText(s.getName());
        }

    }
    public void cancelClicked(ActionEvent actionEvent) {
        // close Dialog
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void saveClicked(ActionEvent actionEvent) {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345763");

        PreparedStatement statement = null;
        if (selectedItem != null) {
            // update existing Item
            try {
                statement = conn.getConnection().prepareStatement("UPDATE gr1_priority SET description = '"+selectedItem.getName()+"' WHERE priority_id = "+ selectedItem.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            // insert new
            try {
                statement = conn.getConnection().prepareStatement("INSERT INTO gr1_priority (description) VALUES ('"+nameTextField.getText()+"')");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        nameTextField.clear();
        statusListView.getSelectionModel().clearSelection();
    }
    public void deleteClicked(ActionEvent actionEvent) {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345763");

        if(selectedItem != null) {
            // delete Item

            PreparedStatement statement = null;
            try {
                statement = conn.getConnection().prepareStatement("DELETE FROM gr1_prioritaet WHERE prioritaet_id = "+ selectedItem.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
