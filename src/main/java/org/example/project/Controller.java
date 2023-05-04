package org.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Controller {

    @FXML
    private ChoiceBox<String> choicebox;

    @FXML
    private Button editButton;

    @FXML
    private Button click;

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
    private HashMap<String, TextField> languages_tf_map;



    public void initialize(){
        Translator translator = new Translator();
        this.translator = translator;

        this.languages_tf_map = new HashMap<>();
        languages_tf_map.put("Turkish", turkishTrans);
        languages_tf_map.put("English", englishTrans);
        languages_tf_map.put("French", frenchTrans);
        languages_tf_map.put("German", germanTrans);
        languages_tf_map.put("Italian", italianTrans);
        languages_tf_map.put("Swedish", swedishTrans);
        languages_tf_map.put("Modern Greek", ellTrans);
    }

    @FXML
    public void exit1(ActionEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage) exitButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void translatorEnter(ActionEvent event) {

        String enteredword=  enterWordTextBox.getText();
        enteredword = enteredword.toLowerCase();
        if(enteredword.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please write and select the language of the word!", ButtonType.OK);
            alert.showAndWait();
            enterWordTextBox.clear();
            return;
        }
        String source_lang = choicebox.getSelectionModel().getSelectedItem();
        if (source_lang == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please write and select the language of the word!", ButtonType.OK);
            alert.showAndWait();
            enterWordTextBox.clear();
            return;
        }

        HashMap<String, ArrayList<HashMap<String, String>>> xToy_translation_execution_map = null;
        ArrayList<HashMap<String, String>> xToy_list_of_executions = null;

        HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> translator_map = this.translator.getTranslator();

        xToy_translation_execution_map = translator_map.get(source_lang);
        for(Map.Entry<String, ArrayList<HashMap<String, String>>> entry : xToy_translation_execution_map.entrySet()) {
            String target_lang = entry.getKey();
            TextField target_lang_tf = this.languages_tf_map.get(target_lang);
            xToy_list_of_executions = entry.getValue();

            String translated_text = enteredword;
            if(xToy_list_of_executions!=null) {
                for (int exec_index = 0; exec_index < xToy_list_of_executions.size(); exec_index++) {
                    System.out.printf("%s", translated_text);
                    HashMap<String, String> dict = xToy_list_of_executions.get(exec_index);
                    if (dict.containsKey(translated_text)) {
                        translated_text = xToy_list_of_executions.get(exec_index).get(translated_text);
                    } else {
                        translated_text = "'NOT FOUND!'";
                        break;
                    }
                }
            }else{
                System.out.printf("%s\n", target_lang);
            }

            target_lang_tf.setText(translated_text);
        }

    }



}