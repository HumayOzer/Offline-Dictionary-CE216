package ce216.group3.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class AddWord {
    public AddWord(String filePath, String addNativeWord, String addLocalizeWord) throws Exception {
        File dictionaryFile = new File(filePath);
        DocumentBuilderFactory docBuildFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuildFac.newDocumentBuilder();
        Document doc = docBuilder.parse(dictionaryFile);

        Element root = doc.getDocumentElement();
        NodeList bodyList = doc.getElementsByTagName("body");
        Element body;
        if (bodyList.getLength() > 0) {
            body = (Element) bodyList.item(0);
        } else {
            body = doc.createElement("body");
            root.appendChild(body);
        }

        // Create the new word entry and append it to the <text> element
        Element entry = doc.createElement("entry");
        Element form = doc.createElement("form");
        Element orth = doc.createElement("orth");
        orth.setTextContent(addNativeWord);
        form.appendChild(orth);
        entry.appendChild(form);

        Element sense = doc.createElement("sense");
        Element cit = doc.createElement("cit");
        cit.setAttribute("type", "trans");
        Element quote = doc.createElement("quote");
        quote.setTextContent(addLocalizeWord);
        cit.appendChild(quote);
        sense.appendChild(cit);
        entry.appendChild(sense);

        body.appendChild(entry);

        // Write the changes back to the file
        Transformer tFormer = TransformerFactory.newInstance().newTransformer();
        tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
        Source source = new DOMSource(doc);
        Result result = new StreamResult(dictionaryFile);
        tFormer.transform(source, result);
        System.out.println("Done");
    }
}
