package com.tlaw.todolist2;

import com.tlaw.todolist2.datamodel.TodoData;
import com.tlaw.todolist2.datamodel.myDATA;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class HelloController {
    @FXML
    private Label deadLineLabel;
    @FXML
    private ListView<myDATA> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    private List<myDATA> todoItems;

    public void initialize() {

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<myDATA>() {
            @Override
            public void changed(ObservableValue<? extends myDATA> observableValue, myDATA myDATA, myDATA newValue) {
                if (newValue != null) {
                    myDATA item = todoListView.getSelectionModel().getSelectedItem();
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
    public void handleClickListView() {
        myDATA item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadLineLabel.setText(item.getDate().toString());


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
        dialog.setTitle("Post a new entry form");
        dialog.setHeaderText("Use this dialgo to post a new entry");
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoitemDialog.fxml"));

        try {

            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (Exception e) {
            System.out.println("Error loading dialog FXML");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Ok button pressed");
            HelloDialog controller = fxmlLoader.getController();
            myDATA newItem = controller.precessResults();
            todoListView.getSelectionModel().select(newItem);
            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            System.out.println("ListView updated...");
            deadLineLabel.setText("ListView Updated...");
        } else
            System.out.println("Canceled");

    }

}