import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void onStatusClicked(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("status.fxml"));

            Stage s = new Stage();
            s.setTitle("Status");
            s.setScene(new Scene(root));
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onPriorityClicked(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("prioritaet.fxml"));
            Stage p = new Stage();
            p.setTitle("Prioritäten");
            p.setScene(new Scene(root));
            p.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
