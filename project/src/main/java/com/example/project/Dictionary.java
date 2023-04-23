package com.example.project;
import java.io.*;
import java.util.*;
public class Dictionary {

    public static final String eng_tur = "src/main/resources/com/example/project/Language/eng-tur.tei";
    public static final String eng_ita = "src/main/resources/com/example/project/Language/eng-ita.tei";
    public static final String eng_swe = "src/main/resources/com/example/project/Language/eng-swe.tei";
    public static final String eng_deu = "src/main/resources/com/example/project/Languages/eng-deu.tei";
    public static final String eng_ell = "src/main/resources/com/example/project/Language/eng-ell.tei";
    public static final String eng_fra = "src/main/resources/com/example/project/Language/eng-fra.tei";
    public static final String deu_ita = "src/main/resources/com/example/project/Language/deu-ita.tei";
    public static final String deu_fra = "src/main/resources/com/example/project/Language/deu-fra.tei";
    public static final String deu_eng = "src/main/resources/com/example/project/Languages/deu-eng.tei";
    public static final String deu_swe = "src/main/resources/com/example/project/Language/deu-swe.tei";
    public static final String deu_tur = "src/main/resources/com/example/project/Language/deu-tur.tei";
    public static final String deu_ell = "src/main/resources/com/example/project/Language/deu-ell.tei";
    public static final String ell_eng = "src/main/resources/com/example/project/Language/ell-eng.tei";
    public static final String ell_tur = "src/main/resources/com/example/project/Languages/ell-tur.tei";
    public static final String ell_fra = "src/main/resources/com/example/project/Language/ell-fra.tei";
    public static final String ell_ita = "src/main/resources/com/example/project/Language/ell-ita.tei";
    public static final String ell_swe = "src/main/resources/com/example/project/Language/ell-swe.tei";
    public static final String ell_deu = "src/main/resources/com/example/project/Languages/ell-deu.tei";
    public static final String fra_deu = "src/main/resources/com/example/project/Language/fra-deu.tei";
    public static final String fra_ell = "src/main/resources/com/example/project/Language/fra-ell.tei";
    public static final String fra_eng = "src/main/resources/com/example/project/Language/fra-eng.tei";
    public static final String fra_ita = "src/main/resources/com/example/project/Language/fra-ita.tei";
    public static final String fra_swe = "src/main/resources/com/example/project/Language/fra-swe.tei";
    public static final String fra_tur = "src/main/resources/com/example/project/Language/fra-tur.tei";
    public static final String ita_deu = "src/main/resources/com/example/project/Language/ita-deu.tei";
    public static final String ita_ell = "src/main/resources/com/example/project/Language/ita-ell.tei";
    public static final String ita_eng = "src/main/resources/com/example/project/Language/ita-eng.tei";
    public static final String ita_swe = "src/main/resources/com/example/project/Language/ita-swe.tei";
    public static final String ita_tur = "src/main/resources/com/example/project/Language/ita-tur.tei";
    public static final String ita_fra = "src/main/resources/com/example/project/Languages/ita-fra.tei";
    public static final String swe_deu = "src/main/resources/com/example/project/Language/swe-deu.tei";
    public static final String swe_ell = "src/main/resources/com/example/project/Language/swe-ell.tei";
    public static final String swe_eng = "src/main/resources/com/example/project/Language/swe-eng.tei";
    public static final String swe_fra = "src/main/resources/com/example/project/Language/swe-fra.tei";
    public static final String swe_ita = "src/main/resources/com/example/project/Language/swe-ita.tei";
    public static final String swe_tur = "src/main/resources/com/example/project/Language/swe-tur.tei";
    public static final String tur_deu = "src/main/resources/com/example/project/Language/tur-deu.tei";
    public static final String tur_eng = "src/main/resources/com/example/project/Language/tur-eng.tei";

    public enum Language{ // to handle the differences of the xml files.
        ENGLISH,
        TURKISH,
        GERMAN,
        ITALIAN,
        SWEDISH,
        GREEK,
        FRENCH
    }
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> dictionary = new HashMap<>();

        // dosyadan kelime ve anlamlarını oku ve sözlük ekle
        try (BufferedReader br = new BufferedReader(new FileReader("eng-tur.tei"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("<form type=\"lemma\">")) {
                    String lemma = line.replaceAll("<form type=\"lemma\">|</form>", "").trim();
                    List<String> meanings = new ArrayList<>();
                    while ((line = br.readLine()) != null && !line.contains("</sense>")) {
                        if (line.contains("<gramGrp>") || line.contains("<cit type=\"translation\">")) {
                            String meaning = line.replaceAll("<[^>]+>", "").trim();
                            meanings.add(meaning);
                        }
                    }
                    dictionary.put(lemma, meanings);
                }
            }
        }

        // kelime sorgula ve anlamını göster, varsa kullanıcıya yeni anlam ekleme seçeneği sun
        while (true) {
            System.out.print("Enter a word: ");
            String word = scanner.nextLine().trim();

            if (word.isEmpty()) {
                System.out.println("Goodbye!");
                break;
            }

            List<String> meanings = dictionary.get(word);
            if (meanings != null) {
                System.out.println("Meanings:");
                for (String meaning : meanings) {
                    System.out.println("- " + meaning);
                }

                System.out.print("Do you want to add a new meaning? (y/n) ");
                String choice = scanner.nextLine().trim();
                if (choice.equalsIgnoreCase("y")) {
                    System.out.print("Enter the new meaning: ");
                    String newMeaning = scanner.nextLine().trim();
                    meanings.add(newMeaning);
                }
            } else {
                System.out.println("Word not found.");
            }
        }

        // sözlük dosyasına güncellenmiş anlamları yaz
        try (PrintWriter pw = new PrintWriter(new FileWriter("eng-tur.tei"))) {
            for (Map.Entry<String, List<String>> entry : dictionary.entrySet()) {
                String lemma = entry.getKey();
                List<String> meanings = entry.getValue();
                pw.println("<entry>");
                pw.println("<form type=\"lemma\">" + lemma + "</form>");
                for (String meaning : meanings) {
                    pw.println("<sense>");
                    pw.println("<gramGrp/>");
                    pw.println("<cit type=\"translation\">" + meaning + "</cit>");
                    pw.println("</sense>");
                }
                pw.println("</entry>");
            }
        }

    }

}


