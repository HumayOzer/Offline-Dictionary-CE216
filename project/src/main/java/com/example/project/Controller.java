package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Button exitButton;

    public void handleClose() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
