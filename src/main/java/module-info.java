module com.tlaw.todolist2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tlaw.todolist2 to javafx.fxml;
    exports com.tlaw.todolist2;
}