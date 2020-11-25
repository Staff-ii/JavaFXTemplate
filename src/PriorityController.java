import javafx.scene.control.TextField;
import model.Priority;

import javax.swing.text.html.ListView;
import java.awt.event.MouseEvent;

public class PriorityController {

    public ListView prioritryListView;
    public TextField nameTextField;

    public void initialize() {
        prioritryListView.setItems(Priority.getList());
    }

    public void itemSelected(MouseEvent mouseEvent) {
        Priority p = prioritryListView.getSelectionModel().getSelectedItem();
        if(p != null) {
            nameTextField.setText(p.getName());
        }
    }

}
