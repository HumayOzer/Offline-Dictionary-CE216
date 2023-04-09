package com.example.project;

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

public class XmlParser {
    public HashMap<String,String> dictionaryParserXML(String path, Dictionary.Language currLanguage) {

        HashMap<String,String> currDictionary = new HashMap<>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document doc;
        File file = new File(path);

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList entryNodeList;
            entryNodeList = doc.getElementsByTagName("entry");

            for (int i = 0; i < entryNodeList.getLength(); i++) {
                Node entryNodes = entryNodeList.item(i);
                if (entryNodes.getNodeType() == Node.ELEMENT_NODE){
                    Element entryElements = (Element) entryNodes;

                    String word = entryElements.getElementsByTagName("orth").item(0).getTextContent(); //word
                    String definition;

                    if (currLanguage == Dictionary.Language.GREEK){
                        definition = entryElements.getElementsByTagName("def").item(0).getTextContent();//definition
                    }else {
                        definition = entryElements.getElementsByTagName("quote").item(0).getTextContent();//definition
                    }
                    currDictionary.put(word,definition);

                }

            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }


        return currDictionary;
    }
}