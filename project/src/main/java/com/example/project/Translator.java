package com.example.project;

import java.util.HashMap;

public class Translator {

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
}
