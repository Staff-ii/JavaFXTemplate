import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Priority;
import model.Status;
import model.ToDo;

import java.io.IOException;

public class Controller {

    public ComboBox statusComboBox; // befüllen
    public ComboBox priorityComboBox; // befüllen
    public ListView<ToDo> todoListView; // befüllen

    public TextField todonameTextField;

    public Pane contentPane;
    public TextField nameToDo;
    public TextArea descriptionToDo;
    public ComboBox dropDownStatus;
    public ComboBox dropDownPriority;
    public Button saveClicked;
    public Button cancelClicked;



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
            Parent root = FXMLLoader.load(getClass().getResource("prioritaet.fxml"));
            Stage p = new Stage();
            p.setTitle("Prioritäten");
            p.setScene(new Scene(root));
            p.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initialize() {
        todoListView.setItems(ToDo.getList());
        statusComboBox.setItems(Status.getList());
        priorityComboBox.setItems(Priority.getList());
    }

    public void onToDoClicked(MouseEvent mouseEvent) {
        ToDo selectedItem = todoListView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // stelle die Daten des gewählten ToDos auf der rechten Seite dar

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("todo.fxml"));
                Pane todoPane =loader.load();
                    ToDoController controller = (ToDoController) loader.getController();
                controller.setToDo(selectedItem);

                contentPane.getChildren().add(todoPane);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
