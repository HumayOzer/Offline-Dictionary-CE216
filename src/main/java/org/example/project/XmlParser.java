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
        public static class TEIEditor {
                public static void editTEIFile(File teiFile, String word, String newTranslation) throws Exception {
                        // Create a DocumentBuilderFactory and DocumentBuilder
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();


                        Document doc = dBuilder.parse(teiFile);

                        NodeList entryList = doc.getElementsByTagName("entry");
                        for (int i = 0; i < entryList.getLength(); i++) {
                                Element entryElement = (Element) entryList.item(i);
                                Element formElement = (Element) entryElement.getElementsByTagName("form").item(0);
                                Element orthElement = (Element) formElement.getElementsByTagName("orth").item(0);
                                if (orthElement.getTextContent().equals(word)) {
                                        Element transElement = (Element) entryElement.getElementsByTagName("quote").item(0);
                                        if (transElement == null) {
                                                transElement = (Element) entryElement.getElementsByTagName("dif").item(0);
                                        }
                                        transElement.setTextContent(newTranslation);
                                }
                        }

                        javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
                        javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
                        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
                        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(teiFile);
                        transformer.transform(source, result);
                }
        }

        public static void addWordToXML(String path, String word, String newTranslation) throws Exception {
                // Create a DocumentBuilderFactory and DocumentBuilder
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

                Document doc = dBuilder.parse(path);

                // Find the entry element with the matching word
                NodeList entryNodes = doc.getElementsByTagName("entry");
                Element entryElement = null;
                for (int i = 0; i < entryNodes.getLength(); i++) {
                        Element currentElement = (Element) entryNodes.item(i);
                        NodeList formNodes = currentElement.getElementsByTagName("form");
                        for (int j = 0; j < formNodes.getLength(); j++) {
                                Element formElement = (Element) formNodes.item(j);
                                Element orthElement = (Element) formElement.getElementsByTagName("orth").item(0);
                                if (orthElement.getTextContent().equals(word)) {
                                        entryElement = currentElement;
                                        break;
                                }
                        }
                        if (entryElement != null) {
                                break;
                        }
                }

                if (entryElement == null) {
                        // Create a new entry element if one with matching word does not exist
                        entryElement = doc.createElement("entry");
                        // Create form element
                        Element formElement = doc.createElement("form");
                        Element orthElement = doc.createElement("orth");
                        orthElement.setTextContent(word);
                        formElement.appendChild(orthElement);
                        entryElement.appendChild(formElement);

                        // Create sense element
                        Element senseElement = doc.createElement("sense");
                        entryElement.appendChild(senseElement);

                        // Add new sense element with translation quote
                        Element citElement = doc.createElement("cit");
                        citElement.setAttribute("type", "trans");
                        Element quoteElement = doc.createElement("quote");
                        quoteElement.setTextContent(newTranslation);
                        citElement.appendChild(quoteElement);
                        senseElement.appendChild(citElement);

                        // Add new entry to the XML file
                        Node dictionaryNode = doc.getElementsByTagName("dictionary").item(0);
                        if (dictionaryNode == null) {
                                // Create a new dictionary element if it doesn't exist
                                dictionaryNode = doc.createElement("dictionary");
                                doc.appendChild(dictionaryNode);
                        }
                        dictionaryNode.appendChild(entryElement);
                } else {
                        // Add new translation quote to existing sense element
                        Element senseElement = (Element) entryElement.getElementsByTagName("sense").item(0);
                        Element citElement = doc.createElement("cit");
                        citElement.setAttribute("type", "trans");
                        Element quoteElement = doc.createElement("quote");
                        quoteElement.setTextContent(newTranslation);
                        citElement.appendChild(quoteElement);
                        senseElement.appendChild(citElement);
                }

                // Write the changes to the file
                javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
                javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
                javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
                javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new File(path));
                transformer.transform(source, result);
        }


}


