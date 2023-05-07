package ce216.group3;

import ce216.group3.dictionary.DictionaryLanguages;
import ce216.group3.parser.AddWord;
import javafx.fxml.FXML;
// FX Scene
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.nio.charset.StandardCharsets;
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
        languagesMap.put(String.valueOf(DictionaryLanguages.MODERN_GREEK), dicFieldEll);

        // Setup Dictionary Pages
        dicLangOptions.getItems().addAll(String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.MODERN_GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.TURKISH));
        dicLangOptions.setValue(String.valueOf(DictionaryLanguages.ENGLISH));

        // Set up the language options and set default
        addNativeLang.getItems().addAll(String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.TURKISH));
        addNativeLang.setValue(String.valueOf(DictionaryLanguages.TURKISH));

        // Set up the language options and set default for the default native option (turkish, in this case)
        addTranslationLang.getItems().addAll(String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.GERMAN));
        addTranslationLang.setValue(String.valueOf(DictionaryLanguages.ENGLISH));

        // Change Available Fields for Lang
        dicLangOptions.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            dicFieldTur.clear();
            dicFieldTur.setDisable(false);

            dicFieldEng.clear();
            dicFieldEng.setDisable(false);

            dicFieldFra.clear();
            dicFieldFra.setDisable(false);

            dicFieldDeu.clear();
            dicFieldDeu.setDisable(false);

            dicFieldIta.clear();
            dicFieldIta.setDisable(false);

            dicFieldSwe.clear();
            dicFieldSwe.setDisable(false);

            dicFieldEll.clear();
            dicFieldEll.setDisable(false);
        });

        // Change Available Fields for Lang
        addNativeLang.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String newNativeLang = addNativeLang.getItems().get((Integer) newValue);

            switch (newNativeLang) {
                case "TURKISH" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.ENGLISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GERMAN));
                }
                case "ENGLISH" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.TURKISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.FRENCH));
                }
                case "FRENCH", "SWEDISH" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.ENGLISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.ENGLISH));
                }
                case "ITALIAN" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.GERMAN));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.GERMAN));
                }
                case "GERMAN" -> {
                    addTranslationLang.getItems().setAll(String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.TURKISH));
                    addTranslationLang.setValue(String.valueOf(DictionaryLanguages.TURKISH));
                }
            }
        });

        //
        dicSearchWord.textProperty().addListener((observable, oldValue, newValue) -> {
            searchDictionaries(newValue.toLowerCase());
        });
    }

    private void searchDictionaries(String dicWord) {
        HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> localizationMap = this.translator.getTranslator();
        dicLangOptions.getItems().setAll();

        if(dicWord.isBlank() || dicWord.isEmpty()){
            dicLangOptions.getItems().setAll(String.valueOf(DictionaryLanguages.GERMAN), String.valueOf(DictionaryLanguages.MODERN_GREEK), String.valueOf(DictionaryLanguages.ENGLISH), String.valueOf(DictionaryLanguages.FRENCH), String.valueOf(DictionaryLanguages.ITALIAN), String.valueOf(DictionaryLanguages.SWEDISH), String.valueOf(DictionaryLanguages.TURKISH));
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
                                dicLangOptions.setValue(String.valueOf(lang));
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

        HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> localizationMap = this.translator.getTranslator();

        HashMap<String, ArrayList<HashMap<String, String>>> localizationExecMap = localizationMap.get(dicWordLang);

        for ( Map.Entry<String, ArrayList<HashMap<String, String>>> entry : localizationExecMap.entrySet() ) {

            String targetLangKey = entry.getKey();
            TextField targetLang = this.languagesMap.get(targetLangKey);

            ArrayList<HashMap<String, String>> execList = entry.getValue();

            if (Objects.equals(targetLangKey, "TURKISH") || Objects.equals(targetLangKey, "GERMAN")  || Objects.equals(targetLangKey, "ENGLISH")) {
                System.out.println("System won't be edit these fields for turkish\n");
            } else {
                if ( execList != null ) {
                    for (HashMap<String, String> xToyListOfExecution : execList) {
                        if (xToyListOfExecution.containsKey(dicWord)) {
                            targetLang.setText(xToyListOfExecution.get(dicWord));

                            String canBeEditableCheck = Helper.getLangCode(targetLangKey) + "_" + Helper.getLangCode(dicWordLang);
                            targetLang.setDisable(Helper.canBeEditable(canBeEditableCheck));
                        } else {
                            targetLang.setText("Translation Doesn't Exist");
                            targetLang.setDisable(true);
                            break;
                        }
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
                            targetLang.setDisable(true);
                            dicTurSearch(xToyListOfExecution.get(dicWord));
                        } else {
                            String canBeEditableCheck = Helper.getLangCode(targetLangKey) + "_" + Helper.getLangCode(dicWordLang);
                            targetLang.setDisable(Helper.canBeEditable(canBeEditableCheck));
                        }

                        targetLang.setText(xToyListOfExecution.get(dicWord));
                    } else {
                        targetLang.setText("Translation Doesn't Exist");
                        targetLang.setDisable(true);
                        break;
                    }
                }
            } else {
                System.out.println(targetLangKey);
            }
        }

    }

    @FXML
    public void dicEdit() {
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
            switch (dicWordLang) {
                // Turkish
                case "TURKISH" -> {
                    // Editing Files
                    new EditDictionary(DictionaryOptions.tur_eng, dicWord, dicTextFieldEng);
                    new EditDictionary(DictionaryOptions.tur_deu, dicWord, dicTextFieldDeu);
                    // Fields will be edited from english
                    new EditDictionary(DictionaryOptions.eng_ita, dicTextFieldEng, dicTextFieldIta);
                    new EditDictionary(DictionaryOptions.eng_swe, dicTextFieldEng, dicTextFieldSwe);
                    new EditDictionary(DictionaryOptions.eng_fra, dicTextFieldEng, dicTextFieldFra);
                    // These options disabled due the file corruption problems
                    // eng_ell
                }
                // German
                case "GERMAN" -> {
                    new EditDictionary(DictionaryOptions.deu_tur, dicWord, dicTextFieldTur);
                    new EditDictionary(DictionaryOptions.deu_ita, dicWord, dicTextFieldIta);
                    // These options disabled due the file corruption problems
                    // deu_eng, deu_swe, deu_ell, deu_fra
                }
                // Italian
                case "ITALIAN" -> {
                    new EditDictionary(DictionaryOptions.ita_eng, dicWord, dicTextFieldEng);
                    new EditDictionary(DictionaryOptions.ita_deu, dicWord, dicTextFieldDeu);
                    // These options disabled due the file corruption problems
                    // ita_swe, ita_ell, ita_fra, ita_tur
                }
                // Swedish
                case "SWEDISH" -> {
                    new EditDictionary(DictionaryOptions.swe_eng, dicWord, dicTextFieldEng);
                    // These options disabled due the file corruption problems
                    // swe_tur, swe_ita, swe_deu, swe_ell, swe_fra
                }
                // English
                case "ENGLISH" -> {
                    new EditDictionary(DictionaryOptions.eng_tur, dicWord, dicTextFieldTur);
                    new EditDictionary(DictionaryOptions.eng_ita, dicWord, dicTextFieldIta);
                    new EditDictionary(DictionaryOptions.eng_swe, dicWord, dicTextFieldSwe);
                    new EditDictionary(DictionaryOptions.eng_fra, dicWord, dicTextFieldFra);
                    // These options disabled due the file corruption problems
                    // eng_ell, eng_deu
                }
                // Modern greek
                case "MODERN_GREEK" -> {
                    // These options disabled due the file corruption problems
                    // Modern Greek cannot be edited.
                }
                // French
                case "FRENCH" -> {
                    new EditDictionary(DictionaryOptions.fra_eng, dicWord, dicTextFieldEng);
                    // These options disabled due the file corruption problems
                    // fra_deu, fra_tur, fra_ita, fra_swe, fra_ell
                }
            }
        } catch (Exception ex) {
            // Print the stack trace to console for debugging
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("An error occurred while editing the TEI file");
            alert.showAndWait();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Success");
        alert.setContentText("TEI file edited successfully, please reload the application in order to see the updated word!");
        alert.showAndWait();
    }
    @FXML
    public void addWord() {
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
            new AddWord(dictionaryPath, addNativeWord.toLowerCase(), addLocalizeWord.toLowerCase());

            // Clear the input fields
            addNativeInput.clear();
            addLocalizedInput.clear();
            // Alert to inform
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "New word added! Please reload the application in order to see the word!", ButtonType.OK);
            alert.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
