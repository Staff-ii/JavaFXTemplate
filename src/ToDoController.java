import model.ToDo;

public class ToDoController {

    private ToDo selected = null;

    public void setToDo(ToDo item) {
        selected = item;
        displayItem();
    }

    private void displayItem() {
        // Hier sollen dei Daten vom "selected" angezeigt werden
        statusCombobox.setItems();
    }

}
