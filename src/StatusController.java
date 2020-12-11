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

    public void initialize() {

        statusListView.setItems(Status.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {

        Status s = statusListView.getSelectionModel().getSelectedItem();
        if (s != null) {
            nameTextField.setText(s.getName());
        }

    }

    public void cancelClicked(ActionEvent actionEvent) {
        // close Dialog
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void saveClicked(ActionEvent actionEvent) {
        PreparedStatement statement = null;
        // update existing Item
        if (selectedItem != null) {
            // update existing Item
            selectedItem.setName(nameTextField.getText());
            selectedItem.update();
        } else if (nameTextField.getText().length() > 0) {
            // insert new
            Status s = new Status(0, nameTextField.getText());
            s.insert();
            statusListView.getItems().add(s);
            statusListView.getSelectionModel().selectLast();
            selectedItem = s;
        }
    }

    public void newClicked(ActionEvent actionEvent) {
        selectedItem = null;
        nameTextField.clear();
        statusListView.getSelectionModel().clearSelection();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        if (selectedItem != null) {
            // delete Item
            selectedItem.delete();
            statusListView.getItems().remove(selectedItem);

            selectedItem = null;
            nameTextField.clear();

        }
    }
}
