import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Status;

import java.io.IOException;

public class StatusController {
    public ListView<Status> statusListView;
    public TextField nameTextField;

    public void initialize(){

       statusListView.setItems(Status.getList());
    }
    public void itemSelected(MouseEvent mouseEvent) {

        Status s = statusListView.getSelectionModel().getSelectedItem();
        if (s!= null){
            nameTextField.setText(s.getName());
        }

    }
}
