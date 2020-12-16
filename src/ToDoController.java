import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Priority;
import model.Status;
import model.ToDo;
import model.db.AbstractDatabase;
import model.db.MySQLConnector;

import java.io.IOException;

public class ToDoController {

    public TextField nameToDo;
    public TextArea descriptionToDo;
    public ComboBox<Status> dropDownStatus;
    public ComboBox<Priority> dropDownPriority;
    public Button saveClicked;
    public Button cancelClicked;
    private ToDo selected = null;
    private ListView<ToDo> todoList;

    public void setToDo(ToDo item) {
        selected = item;
        displayItem();
    }

    private void displayItem() {
        // Hier sollen die Daten vom "selected" angezeigt werden
        nameToDo.setText(selected.getName());
        descriptionToDo.setText(selected.getDescription());
        dropDownStatus.setItems(Status.getList());
        dropDownPriority.setItems(Priority.getList());



    }

    public void setToDoList(ListView<ToDo> list) {
        this.todoList = list;
    }

    public void speichernClicked(ActionEvent actionEvent) {
        AbstractDatabase conn = new MySQLConnector("d0345761", "5AHEL2021", "rathgeb.at", 3306, "d0345761");
        if (selected != null) {
            // update existing Item
            selected.setName(nameToDo.getText());
            selected.setDescription(descriptionToDo.getText());
            // dropdowns speichern
            selected.update();
        } else if (nameToDo.getText().length() > 0) {
            // insert new
            ToDo t = new ToDo(0, nameToDo.getText(), descriptionToDo.getText());
            t.insert();
            todoList.getItems().add(t);
            selected = t;
        }
        todoList.refresh();
    }

    public void abbrechenClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
