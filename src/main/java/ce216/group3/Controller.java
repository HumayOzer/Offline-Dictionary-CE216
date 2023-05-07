package ce216.group3;

import ce216.group3.dictionary.DictionaryLanguages;
import ce216.group3.parser.AddWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
// FX Scene
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ce216.group3.parser.EditDictionary;
import ce216.group3.dictionary.DictionaryOptions;

public class Controller {
    private Translator translator;
    private HashMap<String, TextField> languagesMap;

    // Dictionary Fields
    @FXML
    private TextField dicFieldTur;
    @FXML
    private TextField dicFieldEng;
    @FXML
    private TextField dicFieldFra;
    @FXML
    private TextField dicFieldDeu;
    @FXML
    private TextField dicFieldIta;
    @FXML
    private TextField dicFieldSwe;
    @FXML
    private TextField dicFieldEll;
    @FXML
    private Button addSubmit;

    // Add Section
    @FXML
    private ChoiceBox<String> addNativeLang;
    @FXML
    private ChoiceBox<String> addTranslationLang;
    @FXML
    private TextField addNativeInput;
    @FXML
    private TextField addLocalizedInput;

    // Dictionary
    @FXML
    private ChoiceBox<String> dicLangOptions;
    @FXML
    private TextField dicSearchWord;

    public void initialize() {
        this.translator = new Translator();

        this.languagesMap = new HashMap<>();
        languagesMap.put(String.valueOf(DictionaryLanguages.TURKISH), dicFieldTur);
        languagesMap.put(String.valueOf(DictionaryLanguages.ENGLISH), dicFieldEng);
        languagesMap.put(String.valueOf(DictionaryLanguages.FRENCH), dicFieldFra);
        languagesMap.put(String.valueOf(DictionaryLanguages.GERMAN), dicFieldDeu);
        languagesMap.put(String.valueOf(DictionaryLanguages.ITALIAN), dicFieldIta);
        languagesMap.put(String.valueOf(DictionaryLanguages.SWEDISH), dicFieldSwe);
        languagesMap.put(String.valueOf(DictionaryLanguages.GREEK), dicFieldEll);

        // Setup Dictionary Pages
        dicLangOptions.getItems().addAll(String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.TURKISH));
        dicLangOptions.setValue(String.valueOf(DictionaryLanguages.ENGLISH));

        // Set up the language options and set default
        addNativeLang.getItems().addAll(String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.TURKISH));
        addNativeLang.setValue(String.valueOf(DictionaryLanguages.GERMAN));

        // Set up the language options and set default
        addTranslationLang.getItems().addAll(String.valueOf(DictionaryLanguages.GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.TURKISH));
        addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GREEK));

        // Change Available Fields for Lang
        dicLangOptions.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            dicFieldTur.clear();
            dicFieldEng.clear();
            dicFieldFra.clear();
            dicFieldDeu.clear();
            dicFieldIta.clear();
            dicFieldSwe.clear();
            dicFieldEll.clear();
        });

        // Change Available Fields for Lang
        addNativeLang.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String newNativeLang = addNativeLang.getItems().get((Integer) newValue);

            switch (newNativeLang) {
                case "TURKISH" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.ENGLISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GERMAN));
                }
                case "GREEK" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.TURKISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GERMAN));
                }
                case "ENGLISH" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.GREEK), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.TURKISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GERMAN));
                }
                case "FRENCH" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.TURKISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GERMAN));
                }
                case "ITALIAN" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.TURKISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GERMAN));
                }
                case "SWEDISH" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.TURKISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GERMAN));
                }
                case "GERMAN" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.TURKISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GREEK));
                }
            }
        });

        //
        dicSearchWord.textProperty().addListener((observable, oldValue, newValue) -> {
            searchDictionaries(newValue);
        });
    }

    private void searchDictionaries(String dicWord) {
        HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> localizationMap = this.translator.getTranslator();
        dicLangOptions.getItems().setAll();

        if(dicWord.isBlank() || dicWord.isEmpty()){
            dicLangOptions.getItems().setAll(String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.TURKISH));
            dicLangOptions.setValue(String.valueOf(DictionaryLanguages.ENGLISH));
            return;
        }

        for (DictionaryLanguages lang : DictionaryLanguages.values()) {
            HashMap<String, ArrayList<HashMap<String, String>>> localizationExecMap = localizationMap.get(String.valueOf(lang));

            for ( Map.Entry<String, ArrayList<HashMap<String, String>>> entry : localizationExecMap.entrySet() ) {

                ArrayList<HashMap<String, String>> execList = entry.getValue();

                if ( execList != null ) {
                    for (HashMap<String, String> xToyListOfExecution : execList) {
                        if (xToyListOfExecution.containsKey(dicWord)) {
                            if (!dicLangOptions.getItems().contains(String.valueOf(lang))) {
                                dicLangOptions.getItems().add(String.valueOf(lang));
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private void dicTurSearch(String dicWord) {
        String dicWordLang = "ENGLISH";
        System.out.println("Word " + dicWord + " selected as " + dicWordLang);

        HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> localizationMap = this.translator.getTranslator();

        HashMap<String, ArrayList<HashMap<String, String>>> localizationExecMap = localizationMap.get(dicWordLang);

        for ( Map.Entry<String, ArrayList<HashMap<String, String>>> entry : localizationExecMap.entrySet() ) {

            String targetLangKey = entry.getKey();
            System.out.println();

            TextField targetLang = this.languagesMap.get(targetLangKey);
            System.out.println(targetLangKey + "   " + targetLang);

            ArrayList<HashMap<String, String>> execList = entry.getValue();

            if (!Objects.equals(targetLangKey, "TURKISH") || !Objects.equals(targetLangKey, "GERMAN")  || !Objects.equals(targetLangKey, "ENGLISH")) {
                if ( execList != null ) {
                    for (HashMap<String, String> xToyListOfExecution : execList) {
                        targetLang.setText(xToyListOfExecution.getOrDefault(dicWord, "Translation Doesn't Exist"));
                    }
                }
            }
        }

    }

    @FXML
    public void dicSearch() {
        String dicWord = dicSearchWord.getText().toLowerCase();
        String dicWordLang = dicLangOptions.getSelectionModel().getSelectedItem();
        System.out.println("Word " + dicWord + " selected as " + dicWordLang);

        if(dicWord.equals("") || dicWordLang == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please write and select the language of the word!", ButtonType.OK);
            alert.showAndWait();
            dicSearchWord.clear();
            return;
        }

        HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> localizationMap = this.translator.getTranslator();

        HashMap<String, ArrayList<HashMap<String, String>>> localizationExecMap = localizationMap.get(dicWordLang);

        for ( Map.Entry<String, ArrayList<HashMap<String, String>>> entry : localizationExecMap.entrySet() ) {

            String targetLangKey = entry.getKey();
            TextField targetLang = this.languagesMap.get(targetLangKey);

            ArrayList<HashMap<String, String>> execList = entry.getValue();

            if ( execList != null ) {
                for (HashMap<String, String> xToyListOfExecution : execList) {
                    if (xToyListOfExecution.containsKey(dicWord)) {
                        if (Objects.equals(dicWordLang, "TURKISH") && Objects.equals(targetLangKey, "ENGLISH")) {
                            // If dicWordLang is TURKISH and targetLangKey is ENGLISH send it to dicTurSearch()
                            dicTurSearch(xToyListOfExecution.get(dicWord));
                        }
                        targetLang.setText(xToyListOfExecution.get(dicWord));
                    } else {
                        targetLang.setText("Translation Doesn't Exist");
                        break;
                    }
                }
            } else {
                System.out.println(targetLangKey);
            }
        }

    }

    @FXML
    public void dicEdit(ActionEvent event) {
        String dicWord = dicSearchWord.getText().trim();
        String dicWordLang = dicLangOptions.getValue();

        String dicTextFieldEng = dicFieldEng.getText().trim();
        String dicTextFieldDeu = dicFieldDeu.getText().trim();
        String dicTextFieldFra = dicFieldFra.getText().trim();
        String dicTextFieldIta = dicFieldIta.getText().trim();
        String dicTextFieldSwe = dicFieldSwe.getText().trim();
        String dicTextFieldEll = dicFieldEll.getText().trim();
        String dicTextFieldTur = dicFieldTur.getText().trim();


        if (dicWord.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Word or translation is empty");
            alert.showAndWait();
            return;
        }

        try {
            // Turkish
            switch (dicWordLang) {
                case "TURKISH" -> {
                    // Editing Files
                    new EditDictionary(DictionaryOptions.tur_eng, dicWord, dicTextFieldEng);
                    new EditDictionary(DictionaryOptions.tur_deu, dicWord, dicTextFieldDeu);
                }

                // German
                case "GERMAN" -> {
                    new EditDictionary(DictionaryOptions.deu_eng, dicWord, dicTextFieldEng);
                    new EditDictionary(DictionaryOptions.deu_tur, dicWord, dicTextFieldTur);
                    new EditDictionary(DictionaryOptions.deu_ita, dicWord, dicTextFieldIta);
                    new EditDictionary(DictionaryOptions.deu_swe, dicWord, dicTextFieldSwe);
                    new EditDictionary(DictionaryOptions.deu_ell, dicWord, dicTextFieldEll);
                    new EditDictionary(DictionaryOptions.deu_fra, dicWord, dicTextFieldFra);
                }
                // Italian
                case "ITALIAN" -> {
                    new EditDictionary(DictionaryOptions.ita_eng, dicWord, dicTextFieldEng);
                    new EditDictionary(DictionaryOptions.ita_tur, dicWord, dicTextFieldTur);
                    new EditDictionary(DictionaryOptions.ita_deu, dicWord, dicTextFieldDeu);
                    new EditDictionary(DictionaryOptions.ita_swe, dicWord, dicTextFieldSwe);
                    new EditDictionary(DictionaryOptions.ita_ell, dicWord, dicTextFieldEll);
                    new EditDictionary(DictionaryOptions.ita_fra, dicWord, dicTextFieldFra);
                }

                // Swedish
                case "SWEDISH" -> {
                    new EditDictionary(DictionaryOptions.swe_eng, dicWord, dicTextFieldEng);
                    new EditDictionary(DictionaryOptions.swe_tur, dicWord, dicTextFieldTur);
                    new EditDictionary(DictionaryOptions.swe_ita, dicWord, dicTextFieldIta);
                    new EditDictionary(DictionaryOptions.swe_deu, dicWord, dicTextFieldDeu);
                    new EditDictionary(DictionaryOptions.swe_ell, dicWord, dicTextFieldEll);
                    new EditDictionary(DictionaryOptions.swe_fra, dicWord, dicTextFieldFra);
                }

                // English
                case "ENGLISH" -> {
                    new EditDictionary(DictionaryOptions.eng_deu, dicWord, dicTextFieldDeu);
                    new EditDictionary(DictionaryOptions.eng_tur, dicWord, dicTextFieldTur);
                    new EditDictionary(DictionaryOptions.eng_ita, dicWord, dicTextFieldIta);
                    new EditDictionary(DictionaryOptions.eng_swe, dicWord, dicTextFieldSwe);
                    new EditDictionary(DictionaryOptions.eng_ell, dicWord, dicTextFieldEll);
                    new EditDictionary(DictionaryOptions.eng_fra, dicWord, dicTextFieldFra);
                }

                // Modern greek
                case "GREEK" -> {
                    new EditDictionary(DictionaryOptions.ell_deu, dicWord, dicTextFieldDeu);
                    new EditDictionary(DictionaryOptions.ell_tur, dicWord, dicTextFieldTur);
                    new EditDictionary(DictionaryOptions.ell_ita, dicWord, dicTextFieldIta);
                    new EditDictionary(DictionaryOptions.ell_swe, dicWord, dicTextFieldSwe);
                    new EditDictionary(DictionaryOptions.ell_eng, dicWord, dicTextFieldEng);
                    new EditDictionary(DictionaryOptions.ell_fra, dicWord, dicTextFieldFra);
                }
                //french
                case "FRENCH" -> {
                    new EditDictionary(DictionaryOptions.fra_deu, dicWord, dicTextFieldDeu);
                    new EditDictionary(DictionaryOptions.fra_tur, dicWord, dicTextFieldTur);
                    new EditDictionary(DictionaryOptions.fra_ita, dicWord, dicTextFieldIta);
                    new EditDictionary(DictionaryOptions.fra_swe, dicWord, dicTextFieldSwe);
                    new EditDictionary(DictionaryOptions.fra_ell, dicWord, dicTextFieldEll);
                    new EditDictionary(DictionaryOptions.fra_eng, dicWord, dicTextFieldEng);
                }
            }


            new Translator();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success");
            alert.setContentText("TEI file edited successfully");
            alert.showAndWait();
        } catch (Exception ex) {
            // Print the stack trace to console for debugging
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("An error occurred while editing the TEI file");
            alert.showAndWait();
        }

    }
    @FXML
    public void addWord(ActionEvent event) {
        try {
            String addNativeSelection = addNativeLang.getValue();
            String addLocalizeSelection = addTranslationLang.getValue();
            System.out.println(addNativeSelection + " " + addLocalizeSelection);

            String addNativeWord = addNativeInput.getText();
            String addLocalizeWord = addLocalizedInput.getText();
            System.out.println(addNativeWord + " " + addLocalizeWord);

            String countryCodeNative = Helper.getLangCode(addNativeSelection);
            String countryCodeLocalize = Helper.getLangCode(addLocalizeSelection);
            System.out.println(countryCodeNative + " " + countryCodeLocalize);

            String dictionaryPath = "src/main/resources/translations/"+ countryCodeNative + "_" + countryCodeLocalize + ".tei";
            new AddWord(dictionaryPath, addNativeWord, addLocalizeWord);

            // Clear the input fields
            addNativeInput.clear();
            addLocalizedInput.clear();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
