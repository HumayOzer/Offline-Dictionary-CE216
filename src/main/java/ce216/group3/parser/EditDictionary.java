package ce216.group3.parser;

import ce216.group3.Helper;
import ce216.group3.dictionary.DictionaryOptions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class EditDictionary {
    public EditDictionary(DictionaryOptions langSupport, String word, String newTranslation) throws Exception {
        File dictionaryFile = Helper.dictionaryFile(langSupport);
        // Create a DocumentBuilderFactory and DocumentBuilder
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(dictionaryFile);

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
                if (transElement != null) {
                    transElement.setTextContent(newTranslation);
                }
            }
        }

        javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(dictionaryFile);
        transformer.transform(source, result);
    }
}
