/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author ADMIN
 */
public class XMLUtil {
    
     public static XMLStreamReader createStAXCursorReaderFromFile(String filePath) throws Exception {
        File file = new File(filePath);
        InputStream is = new FileInputStream(file);
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(is);
        return reader;
    }
     
     
    public static String getTextContent(XMLStreamReader currentCursor, String tagName) throws Exception {
        if (currentCursor != null) {
            while (currentCursor.hasNext()) {
                int cursor = currentCursor.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String name = currentCursor.getLocalName();
                    if (name.equals(tagName)) {
                        currentCursor.next();
                        String result = currentCursor.getText();
                        currentCursor.nextTag();
                        return result;
                    }
                }

            }
        }
        return null;
    }
}
