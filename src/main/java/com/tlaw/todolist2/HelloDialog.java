package com.tlaw.todolist2;

import com.tlaw.todolist2.datamodel.ToDoItems;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class HelloDialog {
@FXML
    private TextField shortDescriptionField;
@FXML
    private TextArea detailsArea;
@FXML
    private DatePicker datelinePicker;
public void precessResults(){
    String shortDescription = shortDescriptionField.getText().trim();
    String details = detailsArea.getText().trim();
    LocalDate deadlineValue = datelinePicker.getValue();
    ToDoItems.addTodoItem(new ToDoItems(shortDescription, details, deadlineValue));


}
}

