package com.tlaw.todolist2;

import com.tlaw.todolist2.datamodel.ToDoItems;
import com.tlaw.todolist2.datamodel.TodoData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class HelloController {
    @FXML
    private Label deadLineLabel;
    @FXML
    private ListView<ToDoItems> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    private List<ToDoItems> todoItems;

    public void initialize(){
//        ToDoItems item6 = new ToDoItems("John stark",
//                "John Stark Major-General John Stark (August 28, 1728 – May 8, 1822) was an American military officer who served during the French and Indian War and the Revolutionary War. " +
//                        "He became known as the \"Hero of Bennington\" for his exemplary service at the Battle of Bennington in 1777.", LocalDate.of(2022, Month.DECEMBER, 25));
//        ToDoItems item1 = new ToDoItems("John Adams",
//                "America's first Vice-President and second President. Sponsor of the American Revolution in Massachusetts, " +
//                        "and wrote the Massachusetts guarantee that freedom of press \"ought not to be restrained.\"", LocalDate.of(2022, Month.DECEMBER, 23));
//        ToDoItems item2 = new ToDoItems("Samuel Adams",
//                "American Revolutionary leader and patriot, Founder of the Sons of Liberty and one of the most vocal patriots for independence; signed the Declaration of Independence", LocalDate.of(2022, Month.DECEMBER, 22));
//        ToDoItems item3 = new ToDoItems("William Bradford",
//                "A Pilgrim, the second governor of the Plymouth colony, 1621-1657. He developed private land ownership and helped colonists get out of debt. He helped the colony survive droughts, " +
//                        "crop failures, and Indian attacks.", LocalDate.of(2022, Month.FEBRUARY, 21));
//        ToDoItems item4 = new ToDoItems("Benjamin Franklin",
//                "American patriot, writer, printer, and inventor. During the Revolutionary War he persuaded the French to help the colonists.", LocalDate.of(2022, Month.DECEMBER, 20));
//        ToDoItems item5 = new ToDoItems("Nathan Hale",
//                "A soldier of the American Revolution who was hanged as a spy by the British and is credited with the quote," +
//                        " \"I only regret that I have but one life to give to my country.\"", LocalDate.of(2022, Month.DECEMBER, 19));
//        ToDoItems item7 = new ToDoItems("Thomas Jefferson",
//                "Virginian, architect, author, governor, and president. Lived at Monticello. Wrote the Declaration of Independence. Second governor of Virgina." +
//                        " Third president of the United States. Designed the buildings of the University of Virginia.", LocalDate.of(2022, Month.DECEMBER, 20));
//
//        todoItems = new ArrayList<ToDoItems>();
//
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);
//        todoItems.add(item6);
//        todoItems.add(item7);
//
//        //finish adding items to listivew
//        TodoData.getInstance().setTodoItems(todoItems);
//        todoListView.getItems().setAll(todoItems);
//        System.out.println(todoItems);
        // added to a text file in TodoData.java

todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItems>() {
    @Override
    public void changed(ObservableValue<? extends ToDoItems> observableValue, ToDoItems toDoItems, ToDoItems newValue) {
        if(newValue != null){
            ToDoItems item = todoListView.getSelectionModel().getSelectedItem();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            itemDetailsTextArea.setText(item.getDetails());
            deadLineLabel.setText(df.format(item.getDate()));

        }
    }
});
        todoListView.getItems().addAll(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }
@FXML
    public void handleClickListView(){
    ToDoItems item = todoListView.getSelectionModel().getSelectedItem();
    itemDetailsTextArea.setText(item.getDetails());
    deadLineLabel.setText(item.getDate().toString());
//    System.out.println("The selected item is " + item);
//    StringBuilder sb = new StringBuilder(item.getDetails());
//    sb.append("\n\n\n\n\n");
//    sb.append("Due: ");
//    sb.append(item.getDate().toString());


//    itemDetailsTextArea.setText(sb.toString());


    }
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    private BorderPane mainBorderPane;
@FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoitemDialog.fxml"));


        try {
            Parent root = FXMLLoader.load(getClass().getResource("todoitemDialog.fxml"));
            dialog.getDialogPane().setContent(root);

        } catch (Exception e) {
            System.out.println("Error loading dialog FXML");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    Optional<ButtonType> result = dialog.showAndWait();
    if(result.isPresent() && result.get() == ButtonType.OK){
        System.out.println("Ok button pressed");
        HelloDialog controller = fxmlLoader.getController();
        controller.precessResults();
    } else
        System.out.println("Canceled");

}

}