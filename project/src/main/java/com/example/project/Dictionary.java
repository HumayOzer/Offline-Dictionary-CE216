package com.example.project;

public class Dictionary {

    public static final  String eng_tur = "src\\main\\java\\com\\example\\project\\Languages\\eng-tur.tei"; //paths for xml files
    public static final  String eng_ita = "src\\main\\java\\com\\example\\project\\Languages\\eng-tur.tei";
    public static final  String eng_swe = "src\\main\\java\\com\\example\\project\\Languages\\eng-tur.tei";
    public static final  String eng_deu = "src\\main\\java\\com\\example\\project\\Languages\\eng-tur.tei";
    public static final  String eng_ell = "src\\main\\java\\com\\example\\project\\Languages\\eng-tur.tei";
    public static final  String eng_fra = "src\\main\\java\\com\\example\\project\\Languages\\eng-tur.tei";
    public static final String deu_ita = "src\\main\\java\\com\\example\\project\\Languages\\deu-ita.tei";
    public static final String deu_fra = "src\\main\\java\\com\\example\\project\\Languages\\deu-fra.tei";
    public static final String deu_eng = "src\\main\\java\\com\\example\\project\\Languages\\deu-eng.tei";
    public static final String deu_swe = "src\\main\\java\\com\\example\\project\\Languages\\deu-swe.tei";
    public static final String deu_tur = "src\\main\\java\\com\\example\\project\\Languages\\deu-tur.tei";

    public enum Language{ // to handle the differences of the xml files.
        ENGLISH,
        TURKISH,
        GERMAN,
        ITALIAN,
        SWEDISH,
        GREEK,
        FRENCH
    }
}
