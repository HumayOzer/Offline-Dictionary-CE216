package ce216.group3;
import ce216.group3.dictionary.DictionaryOptions;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static String getLangCode(String lang) {
        String result = "";
        switch (lang) {
            case "TURKISH" -> {
                result = "tur";
            }
            case "MODERN_GREEK" -> {
                result = "ell";
            }
            case "ENGLISH" -> {
                result = "eng";
            }
            case "FRENCH" -> {
                result = "fra";
            }
            case "ITALIAN" -> {
                result = "ita";
            }
            case "SWEDISH" -> {
                result = "swe";
            }
            case "GERMAN" -> {
                result = "deu";
            }
        }
        return result;
    }

    public static File dictionaryFile(DictionaryOptions arg) {
        String path = "src/main/resources/translations/" + arg + ".tei";
        return new File(path);
    }

    public static String dictionaryPath(DictionaryOptions arg) {
        return "src/main/resources/translations/" + arg + ".tei";
    }

    public static Boolean canBeEditable(String arg) {
        String[] cantEditable = {
                "tur_eng",
                "eng_ell", "eng_deu",
                "ita_swe", "ita_ell", "ita_fra", "ita_tur",
                "deu_eng", "deu_swe", "deu_ell", "deu_fra",
                "fra_deu", "fra_tur", "fra_ita", "fra_swe", "fra_ell",
                "swe_tur", "swe_ita", "swe_deu", "swe_ell", "swe_fra",
                "ell_eng", "ell_deu", "ell_swe", "ell_ita", "ell_fra", "ell_tur"
        };

        List<String> cantEditableList = Arrays.asList(cantEditable);

        return cantEditableList.contains(arg);
    }
}
