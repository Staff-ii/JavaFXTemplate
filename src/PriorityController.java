import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Priority;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
//import model.Status;

public class PriorityController {

    public ListView<Priority> priorityListView;
    public TextField nameTextField;
    public Priority selectedItem = null;

    public void initialize() {
        priorityListView.setItems(Priority.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {

        Priority p = priorityListView.getSelectionModel().getSelectedItem();
        if (p!= null){
            nameTextField.setText(p.getName());
            selectedItem = p;
        }

    }

    public void cancelClicked(ActionEvent actionEvent) {
        // close Dialog
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void saveClicked(ActionEvent actionEvent) {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");
        if (selectedItem != null) {
            // update existing Item
            PreparedStatement statement = null;
            try {
                statement = conn.getConnection().prepareStatement("UPDATE gr1_PRIORITAET SET NAME = '"+selectedItem.getName()+"' WHERE prioritaet_id = "+ selectedItem.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            // insert new
            PreparedStatement statement = null;
            try {
                statement = conn.getConnection().prepareStatement("INSERT INTO gr1_PRIORITAET (NAME) VALUES ('"+nameTextField.getText()+"')");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        priorityListView.setItems(Priority.getList());
    }

    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        nameTextField.clear();
        priorityListView.getSelectionModel().clearSelection();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");

        if(selectedItem != null) {
            // delete Item

            PreparedStatement statement = null;
            try {
                statement = conn.getConnection().prepareStatement("DELETE FROM gr1_PRIORITAET WHERE prioritaet_id = "+ selectedItem.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        priorityListView.setItems(Priority.getList());
    }
}

