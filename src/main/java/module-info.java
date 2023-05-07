module org.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens ce216.group3 to javafx.fxml;
    exports ce216.group3;
}