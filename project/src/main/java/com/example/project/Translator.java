package com.example.project;

import java.util.ArrayList;
import java.util.HashMap;

public class Translator {
    HashMap<String, ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>> translation_execution_master_map= null;

    public Translator() {
        XmlParser xmlParser = new XmlParser();
        engTotur = xmlParser.dictionaryParserXML(Dictionary.eng_tur, Dictionary.Language.TURKISH);
        engTofra = xmlParser.dictionaryParserXML(Dictionary.eng_fra, Dictionary.Language.FRENCH);
        engToita = xmlParser.dictionaryParserXML(Dictionary.eng_ita, Dictionary.Language.ITALIAN);
        engToswe = xmlParser.dictionaryParserXML(Dictionary.eng_swe, Dictionary.Language.SWEDISH);
        engToell = xmlParser.dictionaryParserXML(Dictionary.eng_ell, Dictionary.Language.GREEK);
        engTodeu = xmlParser.dictionaryParserXML(Dictionary.eng_deu, Dictionary.Language.GERMAN);
        deuToita = xmlParser.dictionaryParserXML(Dictionary.deu_ita, Dictionary.Language.ITALIAN);
        deuTofra = xmlParser.dictionaryParserXML(Dictionary.deu_fra, Dictionary.Language.FRENCH);
        deuTotur = xmlParser.dictionaryParserXML(Dictionary.deu_tur, Dictionary.Language.TURKISH);
        deuToeng = xmlParser.dictionaryParserXML(Dictionary.deu_eng, Dictionary.Language.ENGLISH);
        deuToswe = xmlParser.dictionaryParserXML(Dictionary.deu_swe, Dictionary.Language.SWEDISH);
        deuToell = xmlParser.dictionaryParserXML(Dictionary.deu_ell, Dictionary.Language.GREEK);
        ellToeng = xmlParser.dictionaryParserXML(Dictionary.ell_eng, Dictionary.Language.ENGLISH);
        ellTofra = xmlParser.dictionaryParserXML(Dictionary.ell_fra, Dictionary.Language.FRENCH);
        ellToita = xmlParser.dictionaryParserXML(Dictionary.ell_ita, Dictionary.Language.ITALIAN);
        ellTotur = xmlParser.dictionaryParserXML(Dictionary.ell_tur, Dictionary.Language.TURKISH);
        ellTodeu = xmlParser.dictionaryParserXML(Dictionary.ell_deu, Dictionary.Language.GERMAN);
        ellToswe = xmlParser.dictionaryParserXML(Dictionary.ell_swe, Dictionary.Language.SWEDISH);
        fraTodeu = xmlParser.dictionaryParserXML(Dictionary.fra_deu, Dictionary.Language.GERMAN);
        fraToell = xmlParser.dictionaryParserXML(Dictionary.fra_ell, Dictionary.Language.GREEK);
        fraToeng = xmlParser.dictionaryParserXML(Dictionary.fra_eng, Dictionary.Language.ENGLISH);
        fraToita = xmlParser.dictionaryParserXML(Dictionary.fra_ita, Dictionary.Language.ITALIAN);
        fraToswe = xmlParser.dictionaryParserXML(Dictionary.fra_swe, Dictionary.Language.SWEDISH);
        fraTotur = xmlParser.dictionaryParserXML(Dictionary.fra_tur, Dictionary.Language.TURKISH);
        itaTodeu = xmlParser.dictionaryParserXML(Dictionary.ita_deu, Dictionary.Language.GERMAN);
        itaToell = xmlParser.dictionaryParserXML(Dictionary.ita_ell, Dictionary.Language.GREEK);
        itaToeng = xmlParser.dictionaryParserXML(Dictionary.ita_eng, Dictionary.Language.ENGLISH);
        itaToswe = xmlParser.dictionaryParserXML(Dictionary.ita_swe, Dictionary.Language.SWEDISH);
        itaTotur = xmlParser.dictionaryParserXML(Dictionary.ita_tur, Dictionary.Language.TURKISH);
        itaTofra = xmlParser.dictionaryParserXML(Dictionary.ita_fra, Dictionary.Language.FRENCH);
        sweTodeu = xmlParser.dictionaryParserXML(Dictionary.swe_deu, Dictionary.Language.GERMAN);
        sweToell = xmlParser.dictionaryParserXML(Dictionary.swe_ell, Dictionary.Language.GREEK);
        sweToeng = xmlParser.dictionaryParserXML(Dictionary.swe_eng, Dictionary.Language.ENGLISH);
        sweTofra = xmlParser.dictionaryParserXML(Dictionary.swe_fra, Dictionary.Language.FRENCH);
        sweToita = xmlParser.dictionaryParserXML(Dictionary.swe_ita, Dictionary.Language.ITALIAN);
        sweTotur = xmlParser.dictionaryParserXML(Dictionary.swe_tur, Dictionary.Language.TURKISH);
        turTodeu = xmlParser.dictionaryParserXML(Dictionary.tur_deu, Dictionary.Language.GERMAN);
        turToeng = xmlParser.dictionaryParserXML(Dictionary.tur_eng, Dictionary.Language.ENGLISH);

        translation_execution_master_map = new HashMap<>();
        ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>> x2all_lang_execution_list;
        HashMap<String, ArrayList<HashMap<String, String>>> xToy_translation_execution_map;
        ArrayList<HashMap<String, String>> xToy_list_of_executions;

        // ** Turkish
        x2all_lang_execution_list = new ArrayList<>();

        // Tur2Tur
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_translation_execution_map.put("Turkish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Tur2Eng
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getTurToeng());
        xToy_translation_execution_map.put("English", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Tur2Deu
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getTurTodeu());
        xToy_translation_execution_map.put("German", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Tur2Fra
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getTurToeng());
        xToy_list_of_executions.add(getEngTofra());
        xToy_translation_execution_map.put("French", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Tur2Swe
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getTurToeng());
        xToy_list_of_executions.add(getEngToswe());
        xToy_translation_execution_map.put("Swedish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Tur2Ita
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getTurToeng());
        xToy_list_of_executions.add(getEngToita());
        xToy_translation_execution_map.put("Italian", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        //Tur2Ell
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getTurToeng());
        xToy_list_of_executions.add(getEngToell());
        xToy_translation_execution_map.put("Modern Greek", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        translation_execution_master_map.put("Turkish", x2all_lang_execution_list);

        // ** Turkish End

        // ** German
        x2all_lang_execution_list = new ArrayList<>();

        // Deu2Tur
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getDeuTotur());
        xToy_translation_execution_map.put("Turkish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Deu2Eng
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getDeuToeng());
        xToy_translation_execution_map.put("English", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Deu2Deu
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_translation_execution_map.put("German", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Deu2Fra
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getDeuTofra());
        xToy_translation_execution_map.put("French", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Deu2Swe
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getDeuToswe());
        xToy_translation_execution_map.put("Swedish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Deu2Ita
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getDeuToita());
        xToy_translation_execution_map.put("Italian", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        //Deu2Ell
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getDeuToell());
        xToy_translation_execution_map.put("Modern Greek", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        translation_execution_master_map.put("German", x2all_lang_execution_list);

        // ** German End

        // ** French
        x2all_lang_execution_list = new ArrayList<>();

        // Fra2Tur
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getFraTotur());
        xToy_translation_execution_map.put("Turkish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Fra2Eng
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getFraToeng());
        xToy_translation_execution_map.put("English", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Fra2Deu
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getFraTodeu());
        xToy_translation_execution_map.put("German", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Fra2Fra
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_translation_execution_map.put("French", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Fra2Swe
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getFraToswe());
        xToy_translation_execution_map.put("Swedish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Fra2Ita
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getFraToita());
        xToy_translation_execution_map.put("Italian", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        //Fra2Ell
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getFraToell());
        xToy_translation_execution_map.put("Modern Greek", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        translation_execution_master_map.put("French", x2all_lang_execution_list);
        // ** French End

        // ** Swedish
        x2all_lang_execution_list = new ArrayList<>();

        // Swe2Tur
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getSweTotur());
        xToy_translation_execution_map.put("Turkish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Swe2Eng
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getSweToeng());
        xToy_translation_execution_map.put("English", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Swe2Deu
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getSweTodeu());
        xToy_translation_execution_map.put("German", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Swe2Fra
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getSweTofra());
        xToy_translation_execution_map.put("French", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Swe2Swe
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_translation_execution_map.put("Swedish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Swe2Ita
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getSweToita());
        xToy_translation_execution_map.put("Italian", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        //Swe2Ell
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getSweToell());
        xToy_translation_execution_map.put("Modern Greek", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        translation_execution_master_map.put("Swedish", x2all_lang_execution_list);
        // ** Swedish End

        // ** Italian
        x2all_lang_execution_list = new ArrayList<>();

        // Ita2Tur
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getItaTotur());
        xToy_translation_execution_map.put("Turkish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ita2Eng
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getItaToeng());
        xToy_translation_execution_map.put("English", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ita2Deu
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getItaTodeu());
        xToy_translation_execution_map.put("German", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ita2Fra
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getItaTofra());
        xToy_translation_execution_map.put("French", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ita2Swe
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getItaToswe());
        xToy_translation_execution_map.put("Swedish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ita2Ita
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_translation_execution_map.put("Italian", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        //Ita2Ell
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getItaToell());
        xToy_translation_execution_map.put("Modern Greek", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        translation_execution_master_map.put("Italian", x2all_lang_execution_list);
        // ** Italian End

        // ** Modern Greek
        x2all_lang_execution_list = new ArrayList<>();

        // Ell2Tur
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEllTotur());
        xToy_translation_execution_map.put("Turkish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ell2Eng
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEllToeng());
        xToy_translation_execution_map.put("English", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ell2Deu
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEllTodeu());
        xToy_translation_execution_map.put("German", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ell2Fra
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEllTofra());
        xToy_translation_execution_map.put("French", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ell2Swe
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEllToswe());
        xToy_translation_execution_map.put("Swedish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Ell2Ita
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEllToita());
        xToy_translation_execution_map.put("Italian", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        //Ell2Ell
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_translation_execution_map.put("Modern Greek", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        translation_execution_master_map.put("Modern Greek", x2all_lang_execution_list);
        // ** Modern Greek End


        // ** English
        x2all_lang_execution_list = new ArrayList<>();

        // Eng2Tur
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEngTotur());
        xToy_translation_execution_map.put("Turkish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Eng2Eng
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_translation_execution_map.put("English", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Eng2Deu
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEngTodeu());
        xToy_translation_execution_map.put("German", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Eng2Fra
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEngTofra());
        xToy_translation_execution_map.put("French", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Eng2Swe
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEngToswe());
        xToy_translation_execution_map.put("Swedish", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        // Eng2Ita
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEngToita());
        xToy_translation_execution_map.put("Italian", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);

        //Eng2Ell
        xToy_translation_execution_map = new HashMap<>();
        xToy_list_of_executions = new ArrayList<>();
        xToy_list_of_executions.add(getEngToell());
        xToy_translation_execution_map.put("Modern Greek", xToy_list_of_executions);
        x2all_lang_execution_list.add(xToy_translation_execution_map);


        translation_execution_master_map.put("English", x2all_lang_execution_list);
        // ** Modern Greek End

    }


    private HashMap<String,String> engTotur;
    private HashMap<String,String> engTofra;
    private HashMap<String,String> engToita;
    private HashMap<String,String> engToswe;
    private HashMap<String,String> engToell;
    private HashMap<String,String> engTodeu;
    private HashMap<String,String> deuToita;
    private HashMap<String,String> deuTofra;
    private HashMap<String,String> deuToeng;
    private HashMap<String,String> deuTotur;
    private HashMap<String,String> deuToswe;
    private HashMap<String,String> deuToell;
    private HashMap<String,String> ellToeng;
    private HashMap<String,String> ellTofra;
    private HashMap<String,String> ellToita;
    private HashMap<String,String> ellToswe;
    private HashMap<String,String> ellTotur;
    private HashMap<String,String> ellTodeu;
    private HashMap<String,String> fraTodeu;
    private HashMap<String,String> fraToell;
    private HashMap<String,String> fraToeng;
    private HashMap<String,String> fraToita;
    private HashMap<String,String> fraToswe;
    private HashMap<String,String> fraTotur;
    private HashMap<String,String> itaTodeu;
    private HashMap<String,String> itaToell;
    private HashMap<String,String> itaToeng;
    private HashMap<String,String> itaTofra;
    private HashMap<String,String> itaToswe;
    private HashMap<String,String> itaTotur;
    private HashMap<String,String> sweTodeu;
    private HashMap<String,String> sweToell;
    private HashMap<String,String> sweToeng;
    private HashMap<String,String> sweTofra;
    private HashMap<String,String> sweToita;
    private HashMap<String,String> sweTotur;
    private HashMap<String,String> turTodeu;
    private HashMap<String,String> turToeng;

    public HashMap<String, String> getEngTotur() {
        return engTotur;
    }

    public HashMap<String, String> getEngTofra() {
        return engTofra;
    }

    public HashMap<String, String> getEngToita() {
        return engToita;
    }

    public HashMap<String, String> getEngToswe() {
        return engToswe;
    }

    public HashMap<String, String> getEngToell() {
        return engToell;
    }

    public HashMap<String, String> getEngTodeu() {
        return engTodeu;
    }

    public HashMap<String, String> getDeuToita() {
        return deuToita;
    }

    public HashMap<String, String> getDeuTofra() {
        return deuTofra;
    }

    public HashMap<String, String> getDeuToeng() {
        return deuToeng;
    }

    public HashMap<String, String> getDeuTotur() {
        return deuTotur;
    }

    public HashMap<String, String> getDeuToswe() {
        return deuToswe;
    }
    public HashMap<String, String> getDeuToell() {
        return deuToell;
    }

    public HashMap<String, String> getEllToeng() {
        return ellToeng;
    }
    public HashMap<String, String> getEllTotur() {
        return ellTotur;
    }
    public HashMap<String, String> getEllTodeu() {
        return ellTodeu;
    }

    public HashMap<String, String> getEllTofra() {
        return ellTofra;
    }

    public HashMap<String, String> getEllToita() {
        return ellToita;
    }

    public HashMap<String, String> getEllToswe() {
        return ellToswe;
    }

    public HashMap<String, String> getFraTodeu() {
        return fraTodeu;
    }

    public HashMap<String, String> getFraToell() {
        return fraToell;
    }

    public HashMap<String, String> getFraToeng() {
        return fraToeng;
    }

    public HashMap<String, String> getFraToita() {
        return fraToita;
    }

    public HashMap<String, String> getFraToswe() {
        return fraToswe;
    }

    public HashMap<String, String> getFraTotur() {
        return fraTotur;
    }

    public HashMap<String, String> getItaTodeu() {
        return itaTodeu;
    }

    public HashMap<String, String> getItaToell() {
        return itaToell;
    }
    public HashMap<String, String> getItaTofra() {
        return itaTofra;
    }

    public HashMap<String, String> getItaToeng() {
        return itaToeng;
    }

    public HashMap<String, String> getItaToswe() {
        return itaToswe;
    }

    public HashMap<String, String> getItaTotur() {
        return itaTotur;
    }

    public HashMap<String, String> getSweTodeu() {
        return sweTodeu;
    }

    public HashMap<String, String> getSweToell() {
        return sweToell;
    }

    public HashMap<String, String> getSweToeng() {
        return sweToeng;
    }

    public HashMap<String, String> getSweTofra() {
        return sweTofra;
    }

    public HashMap<String, String> getSweToita() {
        return sweToita;
    }

    public HashMap<String, String> getSweTotur() {
        return sweTotur;
    }

    public HashMap<String, String> getTurTodeu() {
        return turTodeu;
    }

    public HashMap<String, String> getTurToeng() {
        return turToeng;
    }

    public HashMap<String, ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>> getTranslator() {
        return this.translation_execution_master_map;
    }
}
