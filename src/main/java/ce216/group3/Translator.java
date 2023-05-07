package ce216.group3;

import ce216.group3.parser.File;
import ce216.group3.dictionary.DictionaryOptions;
import ce216.group3.dictionary.DictionaryLanguages;

import java.util.ArrayList;
import java.util.HashMap;

public class Translator {
    HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> dictionaryList = new HashMap<>();

    private HashMap<String, ArrayList<HashMap<String, String>>> langHashMaps(DictionaryOptions[] opts, DictionaryLanguages[] langs) {
        HashMap<String, ArrayList<HashMap<String, String>>> result = new HashMap<>();

        for (int i = 0; i < opts.length; i++) {
            HashMap<String, String> file = File.get(Helper.dictionaryPath(opts[i]), langs[i]);
            ArrayList<HashMap<String, String>> lang = new ArrayList<>();
            lang.add(file);
            result.put(String.valueOf(langs[i]), lang);
        }

        return result;
    }

    public Translator() {
        // Turkish
        DictionaryOptions[] turOpts = new DictionaryOptions[2];
        DictionaryLanguages[] turLangs = new DictionaryLanguages[2];

        turOpts[0] = DictionaryOptions.tur_deu; turLangs[0] = DictionaryLanguages.GERMAN;
        turOpts[1] = DictionaryOptions.tur_eng; turLangs[1] = DictionaryLanguages.ENGLISH;

        // hm stands for HashMap
        HashMap<String, ArrayList<HashMap<String, String>>> hmTur = this.langHashMaps(turOpts, turLangs);
        dictionaryList.put(String.valueOf(DictionaryLanguages.TURKISH), hmTur);

        // English
        DictionaryOptions[] engOpts = new DictionaryOptions[6];
        DictionaryLanguages[] engLangs = new DictionaryLanguages[6];

        engOpts[0] = DictionaryOptions.eng_deu; engLangs[0] = DictionaryLanguages.GERMAN;
        engOpts[1] = DictionaryOptions.eng_ell; engLangs[1] = DictionaryLanguages.MODERN_GREEK;
        engOpts[2] = DictionaryOptions.eng_swe; engLangs[2] = DictionaryLanguages.SWEDISH;
        engOpts[3] = DictionaryOptions.eng_ita; engLangs[3] = DictionaryLanguages.ITALIAN;
        engOpts[4] = DictionaryOptions.eng_fra; engLangs[4] = DictionaryLanguages.FRENCH;
        engOpts[5] = DictionaryOptions.eng_tur; engLangs[5] = DictionaryLanguages.TURKISH;

        HashMap<String, ArrayList<HashMap<String, String>>> hmEng = this.langHashMaps(engOpts, engLangs);
        dictionaryList.put(String.valueOf(DictionaryLanguages.ENGLISH), hmEng);

        // German
        DictionaryOptions[] deuOpts = new DictionaryOptions[6];
        DictionaryLanguages[] deuLangs = new DictionaryLanguages[6];

        deuOpts[0] = DictionaryOptions.deu_eng; deuLangs[0] = DictionaryLanguages.ENGLISH;
        deuOpts[1] = DictionaryOptions.deu_ell; deuLangs[1] = DictionaryLanguages.MODERN_GREEK;
        deuOpts[2] = DictionaryOptions.deu_swe; deuLangs[2] = DictionaryLanguages.SWEDISH;
        deuOpts[3] = DictionaryOptions.deu_ita; deuLangs[3] = DictionaryLanguages.ITALIAN;
        deuOpts[4] = DictionaryOptions.deu_fra; deuLangs[4] = DictionaryLanguages.FRENCH;
        deuOpts[5] = DictionaryOptions.deu_tur; deuLangs[5] = DictionaryLanguages.TURKISH;

        HashMap<String, ArrayList<HashMap<String, String>>> hmDeu = this.langHashMaps(deuOpts, deuLangs);
        dictionaryList.put(String.valueOf(DictionaryLanguages.GERMAN), hmDeu);

        // Greek
        DictionaryOptions[] ellOpts = new DictionaryOptions[6];
        DictionaryLanguages[] ellLangs = new DictionaryLanguages[6];

        ellOpts[0] = DictionaryOptions.ell_eng; deuLangs[0] = DictionaryLanguages.ENGLISH;
        ellOpts[1] = DictionaryOptions.ell_deu; deuLangs[1] = DictionaryLanguages.GERMAN;
        ellOpts[2] = DictionaryOptions.ell_swe; deuLangs[2] = DictionaryLanguages.SWEDISH;
        ellOpts[3] = DictionaryOptions.ell_ita; deuLangs[3] = DictionaryLanguages.ITALIAN;
        ellOpts[4] = DictionaryOptions.ell_fra; deuLangs[4] = DictionaryLanguages.FRENCH;
        ellOpts[5] = DictionaryOptions.ell_tur; deuLangs[5] = DictionaryLanguages.TURKISH;

        HashMap<String, ArrayList<HashMap<String, String>>> hmEll = this.langHashMaps(ellOpts, ellLangs);
        dictionaryList.put(String.valueOf(DictionaryLanguages.MODERN_GREEK), hmEll);

        // French
        DictionaryOptions[] fraOpts = new DictionaryOptions[6];
        DictionaryLanguages[] fraLangs = new DictionaryLanguages[6];

        fraOpts[0] = DictionaryOptions.fra_eng; fraLangs[0] = DictionaryLanguages.ENGLISH;
        fraOpts[1] = DictionaryOptions.fra_deu; fraLangs[1] = DictionaryLanguages.GERMAN;
        fraOpts[2] = DictionaryOptions.fra_swe; fraLangs[2] = DictionaryLanguages.SWEDISH;
        fraOpts[3] = DictionaryOptions.fra_ita; fraLangs[3] = DictionaryLanguages.ITALIAN;
        fraOpts[4] = DictionaryOptions.fra_ell; fraLangs[4] = DictionaryLanguages.MODERN_GREEK;
        fraOpts[5] = DictionaryOptions.fra_tur; fraLangs[5] = DictionaryLanguages.TURKISH;

        HashMap<String, ArrayList<HashMap<String, String>>> hmFra = this.langHashMaps(fraOpts, fraLangs);
        dictionaryList.put(String.valueOf(DictionaryLanguages.FRENCH), hmFra);

        // Italian
        DictionaryOptions[] itaOpts = new DictionaryOptions[6];
        DictionaryLanguages[] itaLangs = new DictionaryLanguages[6];

        itaOpts[0] = DictionaryOptions.ita_eng; itaLangs[0] = DictionaryLanguages.ENGLISH;
        itaOpts[1] = DictionaryOptions.ita_deu; itaLangs[1] = DictionaryLanguages.GERMAN;
        itaOpts[2] = DictionaryOptions.ita_swe; itaLangs[2] = DictionaryLanguages.SWEDISH;
        itaOpts[3] = DictionaryOptions.ita_ell; itaLangs[3] = DictionaryLanguages.MODERN_GREEK;
        itaOpts[4] = DictionaryOptions.ita_fra; itaLangs[4] = DictionaryLanguages.FRENCH;
        itaOpts[5] = DictionaryOptions.ita_tur; itaLangs[5] = DictionaryLanguages.TURKISH;

        HashMap<String, ArrayList<HashMap<String, String>>> hmIta = this.langHashMaps(itaOpts, itaLangs);
        dictionaryList.put(String.valueOf(DictionaryLanguages.ITALIAN), hmIta);

        // Swedish
        DictionaryOptions[] sweOpts = new DictionaryOptions[6];
        DictionaryLanguages[] sweLangs = new DictionaryLanguages[6];

        sweOpts[0] = DictionaryOptions.swe_eng; sweLangs[0] = DictionaryLanguages.ENGLISH;
        sweOpts[1] = DictionaryOptions.swe_deu; sweLangs[1] = DictionaryLanguages.GERMAN;
        sweOpts[2] = DictionaryOptions.swe_ell; sweLangs[2] = DictionaryLanguages.MODERN_GREEK;
        sweOpts[3] = DictionaryOptions.swe_ita; sweLangs[3] = DictionaryLanguages.ITALIAN;
        sweOpts[4] = DictionaryOptions.swe_fra; sweLangs[4] = DictionaryLanguages.FRENCH;
        sweOpts[5] = DictionaryOptions.swe_tur; sweLangs[5] = DictionaryLanguages.TURKISH;

        HashMap<String, ArrayList<HashMap<String, String>>> hmSwe = this.langHashMaps(sweOpts, sweLangs);
        dictionaryList.put(String.valueOf(DictionaryLanguages.SWEDISH), hmSwe);
    }

    public HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> getTranslator() {
        return this.dictionaryList;
    }
}

