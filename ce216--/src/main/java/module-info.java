module com.example.ce216 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.ce216 to javafx.fxml;
    exports com.example.ce216;
}