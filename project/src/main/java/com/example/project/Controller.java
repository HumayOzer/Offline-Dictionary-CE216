package com.example.project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private Button editButton;

    @FXML
    private TextField ellTrans;

    @FXML
    private TextField englishTrans;

    @FXML
    private Button enterButton;

    @FXML
    private TextField enterWordTextBox;

    @FXML
    private Button exitButton;



    public void initialize() {
        exitButton.setDisable(false);
    }



    @FXML
    private TextField frenchTrans;

    @FXML
    private TextField germanTrans;

    @FXML
    private TextField italianTrans;

    @FXML
    private TextField swedishTrans;

    @FXML
    private TextField turkishTrans;

    private Translator translator;
    private String[] languages;
    private TextField[] languages_tf;

    public void initialize(){
        Translator translator = new Translator();
        this.translator = translator;

        this.languages = new String[]{"Turkish", "English", "French", "German", "Italian", "Swedish", "Modern Greek"};
        this.languages_tf = new TextField[]{turkishTrans, englishTrans, frenchTrans, germanTrans, italianTrans, swedishTrans, ellTrans};
    }

    @FXML
    public void exit1(ActionEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void translatorEnter(ActionEvent event) {

        String enteredword =  enterWordTextBox.getText();
        if(enteredword.equals("")){
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING, "Please write and select the language of the word!", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            enterWordTextBox.clear();
            return;
        }
        String source_lang = choicebox.getSelectionModel().getSelectedItem();
        if (source_lang == null) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING, "Please select the language of the word!", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            enterWordTextBox.clear();
            return;
        }

        ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>> x2all_lang_execution_list = null;
        HashMap<String, ArrayList<HashMap<String, String>>> xToy_translation_execution_map = null;
        ArrayList<HashMap<String, String>> xToy_list_of_executions = null;

        HashMap<String, ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>> translator_map = this.translator.getTranslator();

        x2all_lang_execution_list = translator_map.get(source_lang);
        for(int lang_index_i=0; lang_index_i<languages.length; lang_index_i++){
            xToy_translation_execution_map = x2all_lang_execution_list.get(lang_index_i);

            String target_lang = languages[lang_index_i];
            TextField target_lang_tf = languages_tf[lang_index_i];

            String translated_text = enteredword;
            System.out.printf("TARGET %s\n", target_lang);
            xToy_list_of_executions = xToy_translation_execution_map.get(target_lang);
            if(xToy_list_of_executions!=null) {
                for (int exec_index = 0; exec_index < xToy_list_of_executions.size(); exec_index++) {
                    HashMap<String, String> dict = xToy_list_of_executions.get(exec_index);
                    if (dict.containsKey(translated_text)) {
                        translated_text = xToy_list_of_executions.get(exec_index).get(translated_text);
                    } else {
                        translated_text = "";
                        break;
                    }
                }
            }

            target_lang_tf.setText(translated_text);
        }

        enterWordTextBox.clear();
    }



}
