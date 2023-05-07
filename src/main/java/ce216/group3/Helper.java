package ce216.group3;
import ce216.group3.dictionary.DictionaryOptions;

import java.io.File;

public class Helper {
    public static String getLangCode(String lang) {
        String result = "";
        switch (lang) {
            case "TURKISH" -> {
                result = "tur";
            }
            case "GREEK" -> {
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
}
