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

    @FXML
    private ChoiceBox<String> choicebox2;

    @FXML
    private TextField ellTrans2;

    @FXML
    private TextField englishTrans2;

    @FXML
    private Button enterButton2;

    @FXML
    private TextField enterWordTextBox2;

    @FXML
    private Button exitButton2;

    @FXML
    private TextField frenchTrans2;

    @FXML
    private TextField germanTrans2;

    @FXML
    private TextField italianTrans2;

    @FXML
    private TextField swedishTrans2;

    @FXML
    private TextField turkishTrans2;


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

        // Set up the language choice box with options
        choicebox2.getItems().addAll("English-German", "English-French", "English-Italian", "English-Swedish", "English-Turkish");

        // Set the default language to English-German
        choicebox2.setValue("English-German");

        // Set up the enter button action
        enterButton2.setOnAction(e -> {
            try {
                // Get the selected language
                String selectedLanguage = choicebox2.getValue();

                // Get the word and translation from the input fields
                String word = enterWordTextBox2.getText();
                String translation2 = "";

                switch (selectedLanguage) {
                    case "English-German":
                        translation2 = germanTrans2.getText();
                        XmlParser.addWordToXML("src/main/resources/translations/eng-deu.tei", word, translation2);
                        break;
                    case "English-French":
                        translation2 = frenchTrans2.getText();
                        XmlParser.addWordToXML("src/main/resources/translations/eng-fra.tei", word, translation2);
                        break;
                    case "English-Italian":
                        translation2 = italianTrans2.getText();
                        XmlParser.addWordToXML("src/main/resources/translations/eng-ita.tei", word, translation2);
                        break;
                    case "English-Swedish":
                        translation2 = swedishTrans2.getText();
                        XmlParser.addWordToXML("src/main/resources/translations/eng-swe.tei", word, translation2);
                        break;
                    case "English-Turkish":
                        translation2 = turkishTrans2.getText();
                            XmlParser.addWordToXML("src/main/resources/translations/eng-tur.tei", word, translation2);
                        break;
                    case "English-Modern Greek":
                        translation2 = ellTrans2.getText();
                        XmlParser.addWordToXML("src/main/resources/translations/eng-ell.tei", word, translation2);
                        break;
                    default:
                        break;
                }

                // Clear the input fields
                enterWordTextBox2.clear();
                ellTrans2.clear();
                englishTrans2.clear();
                frenchTrans2.clear();
                germanTrans2.clear();
                italianTrans2.clear();
                swedishTrans2.clear();
                turkishTrans2.clear();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }


    @FXML
    public void exit1(ActionEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void exit2(ActionEvent event) {
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

    @FXML
    public void edit(ActionEvent event) {
        String word = enterWordTextBox.getText().trim();
        String selectedLanguage = choicebox.getValue();
        String translation = englishTrans.getText().trim();
        String translation2 = germanTrans.getText().trim();
        String translation3 = frenchTrans.getText().trim();
        String translation4 = italianTrans.getText().trim();
        String translation5 = swedishTrans.getText().trim();
        String translation6 = ellTrans.getText().trim();
        String translation7 = turkishTrans.getText().trim();


        if (word.isEmpty() || translation.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Word or translation is empty");
            alert.showAndWait();
            return;
        }

        try {

            //turkish
            if (selectedLanguage.equals("Turkish")) {
                // Edit TEI file for tur-eng translation
                java.io.File turEngTEIFile = new java.io.File("src/main/resources/translations/tur-eng.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(turEngTEIFile, word, translation);

                // Edit TEI file for tur-ger translation
                java.io.File turGerTEIFile = new java.io.File("src/main/resources/translations/tur-deu.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(turGerTEIFile, word, translation2);
            }

            //german
            else if (selectedLanguage.equals("German")){

                java.io.File deuengTEIFile = new java.io.File("src/main/resources/translations/deu-eng.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(deuengTEIFile, word, translation);


                java.io.File deuturTEIFile = new java.io.File("src/main/resources/translations/deu-tur.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(deuturTEIFile, word, translation7);


                java.io.File deuitaTEIFile = new java.io.File("src/main/resources/translations/deu-ita.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(deuitaTEIFile, word, translation4);


                java.io.File deusweTEIFile = new java.io.File("src/main/resources/translations/deu-swe.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(deusweTEIFile, word, translation5);


                java.io.File deuellTEIFile = new java.io.File("src/main/resources/translations/deu-ell.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(deuellTEIFile, word, translation6);


                java.io.File deufraTEIFile = new java.io.File("src/main/resources/translations/deu-fra.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(deufraTEIFile, word, translation3);

            }
            //italian
            else if (selectedLanguage.equals("Italian")){

                java.io.File itaengTEIFile = new java.io.File("src/main/resources/translations/ita-eng.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(itaengTEIFile, word, translation);


                java.io.File itaturTEIFile = new java.io.File("src/main/resources/translations/ita-tur.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(itaturTEIFile, word, translation7);


                java.io.File itadeuTEIFile = new java.io.File("src/main/resources/translations/ita-deu.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(itadeuTEIFile, word, translation2);


                java.io.File itasweTEIFile = new java.io.File("src/main/resources/translations/ita-swe.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(itasweTEIFile, word, translation5);


                java.io.File itaellTEIFile = new java.io.File("src/main/resources/translations/ita-ell.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(itaellTEIFile, word, translation6);


                java.io.File itafraTEIFile = new java.io.File("src/main/resources/translations/ita-fra.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(itafraTEIFile, word, translation3);

            }

            //swedish

            else if (selectedLanguage.equals("Swedish")){

                java.io.File sweengTEIFile = new java.io.File("src/main/resources/translations/swe-eng.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(sweengTEIFile, word, translation);


                java.io.File sweturTEIFile = new java.io.File("src/main/resources/translations/swe-tur.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(sweturTEIFile, word, translation7);


                java.io.File sweitaTEIFile = new java.io.File("src/main/resources/translations/swe-ita.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(sweitaTEIFile, word, translation4);


                java.io.File swedeuTEIFile = new java.io.File("src/main/resources/translations/swe-deu.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(swedeuTEIFile, word, translation2);


                java.io.File sweellTEIFile = new java.io.File("src/main/resources/translations/swe-ell.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(sweellTEIFile, word, translation6);


                java.io.File swefraTEIFile = new java.io.File("src/main/resources/translations/swe-fra.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(swefraTEIFile, word, translation3);

            }

            //english
            else if (selectedLanguage.equals("English")){

                java.io.File engdeuTEIFile = new java.io.File("src/main/resources/translations/eng-deu.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(engdeuTEIFile, word, translation2);


                java.io.File engturTEIFile = new java.io.File("src/main/resources/translations/eng-tur.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(engturTEIFile, word, translation7);


                java.io.File engitaTEIFile = new java.io.File("src/main/resources/translations/eng-ita.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(engitaTEIFile, word, translation4);


                java.io.File engsweTEIFile = new java.io.File("src/main/resources/translations/eng-swe.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(engsweTEIFile, word, translation5);


                java.io.File engellTEIFile = new java.io.File("src/main/resources/translations/eng-ell.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(engellTEIFile, word, translation6);


                java.io.File engfraTEIFile = new java.io.File("src/main/resources/translations/eng-fra.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(engfraTEIFile, word, translation3);

            }

            //modern greek
            else if (selectedLanguage.equals("Modern Greek")){

                java.io.File elldeuTEIFile = new java.io.File("src/main/resources/translations/ell-deu.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(elldeuTEIFile, word, translation2);


                java.io.File ellturTEIFile = new java.io.File("src/main/resources/translations/ell-tur.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(ellturTEIFile, word, translation7);


                java.io.File ellitaTEIFile = new java.io.File("src/main/resources/translations/ell-ita.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(ellitaTEIFile, word, translation4);


                java.io.File ellsweTEIFile = new java.io.File("src/main/resources/translations/ell-swe.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(ellsweTEIFile, word, translation5);


                java.io.File ellengTEIFile = new java.io.File("src/main/resources/translations/ell-eng.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(ellengTEIFile, word, translation);


                java.io.File ellfraTEIFile = new java.io.File("src/main/resources/translations/ell-fra.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(ellfraTEIFile, word, translation3);

            }
            //french
            else if (selectedLanguage.equals("French")){

                java.io.File fradeuTEIFile = new java.io.File("src/main/resources/translations/fra-deu.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(fradeuTEIFile, word, translation2);


                java.io.File fraturTEIFile = new java.io.File("src/main/resources/translations/fra-tur.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(fraturTEIFile, word, translation7);


                java.io.File fraitaTEIFile = new java.io.File("src/main/resources/translations/fra-ita.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(fraitaTEIFile, word, translation4);


                java.io.File frasweTEIFile = new java.io.File("src/main/resources/translations/fra-swe.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(frasweTEIFile, word, translation5);


                java.io.File fraellTEIFile = new java.io.File("src/main/resources/translations/fra-ell.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(fraellTEIFile, word, translation6);


                java.io.File fraengTEIFile = new java.io.File("src/main/resources/translations/fra-eng.tei");
                org.example.project.XmlParser.TEIEditor.editTEIFile(fraengTEIFile, word, translation);

            }



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success");
            alert.setContentText("TEI file edited successfully");
            alert.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace(); // print the stack trace to console for debugging
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("An error occurred while editing the TEI file");
            alert.showAndWait();


        }

    }
}
