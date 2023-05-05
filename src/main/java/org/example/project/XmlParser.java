package org.example.project;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class XmlParser {
        public HashMap<String,String> dictionaryParserXML(String path, Dictionary.Language currLanguage){

                HashMap<String,String> currDictionary = new HashMap<>();

                String extractEntryPatternString = "<entry(.*?)</entry>";
                Pattern extractEntryPattern = Pattern.compile(extractEntryPatternString, Pattern.DOTALL);

                String extractSourceWordString = "<orth>(.*?)</orth>";
                Pattern extractSourceWordPattern = Pattern.compile(extractSourceWordString, Pattern.DOTALL);

                String extractTargetWordString1 = "<sense[^>]*>\\s*<cit[^>]*>\\s*<quote[^>]*>(.*?)</quote>";
                Pattern extractTargetWordPattern1 = Pattern.compile(extractTargetWordString1, Pattern.DOTALL);

                String extractTargetWordString2 = "<def>(.*?)</def>";
                Pattern extractTargetWordPattern2 = Pattern.compile(extractTargetWordString2, Pattern.DOTALL);

                StringBuilder sb = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                                sb.append(line);
                                sb.append(System.lineSeparator());
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }

                String fileContent = sb.toString();
                Matcher matcher1 = extractEntryPattern.matcher(fileContent);
                while (matcher1.find()) {
                        String entryText = matcher1.group(1);

                        String orth = "";
                        String quote = "";

                        Matcher matcher2 = extractSourceWordPattern.matcher(entryText);
                        while (matcher2.find()) {
                                orth = matcher2.group(1).trim();
                                //System.out.println("Orth: " + orth);
                        }

                        Matcher matcher3 = extractTargetWordPattern1.matcher(entryText);
                        while (matcher3.find()) {
                                quote = matcher3.group(1).trim();
                                //System.out.println("Quote: " + quote);
                                break;
                        }

                        if (quote.equals("")) {
                                Matcher matcher4 = extractTargetWordPattern2.matcher(entryText);
                                while (matcher4.find()) {
                                        quote = matcher4.group(1).trim();
                                        //System.out.println("Quote: " + quote);
                                        break;
                                }
                        }

                        if (!orth.equals("") || !quote.equals("")) {
                                currDictionary.put(orth.toLowerCase(),quote.toLowerCase());
                        }


                }


                System.out.printf("File read: %s - %d\n", path, currDictionary.size());

                return currDictionary;
        }

        }



