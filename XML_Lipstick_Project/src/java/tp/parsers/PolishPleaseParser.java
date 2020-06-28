/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.parsers;

import com.sun.xml.internal.stream.events.XMLEventAllocatorImpl;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventAllocator;

/**
 *
 * @author ADMIN
 */
public class PolishPleaseParser {

    private static XMLEventAllocator allocator = null;
    
    public boolean  cleanHTMLFromProductDetails(String srcFile, String desFile) {
        boolean isXMLWellForm = true;
        
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newFactory();
            inputFactory.setEventAllocator(new XMLEventAllocatorImpl());
            allocator = inputFactory.getEventAllocator();
            XMLStreamReader xmlr = inputFactory.createXMLStreamReader(srcFile, new FileInputStream(srcFile));
                     
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(desFile), "UTF-8"));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            int cursor = xmlr.getEventType();
            
            boolean isEndSpan = false;
            int tdTagCount = 0;
            while(xmlr.hasNext()){
                cursor = xmlr.next();
                if(cursor == XMLStreamConstants.START_ELEMENT){
                    StartElement event = getXMLEvent(xmlr).asStartElement();
                    writer.write(event.toString().trim()+"\n");
                     if(xmlr.getLocalName().equals("section")){
                        isEndSpan = false;
                    }
                 
                }else if(cursor == XMLStreamConstants.END_ELEMENT){
                    EndElement event = getXMLEvent(xmlr).asEndElement();
                    writer.write(event.toString().trim()+"\n");
                    String tagName = xmlr.getLocalName();
                    if(tagName.equals("span")){
                        isEndSpan = true;
                    }
                }else if(cursor == XMLStreamConstants.CHARACTERS){
                    Characters event = getXMLEvent(xmlr).asCharacters();
                    if(!isEndSpan){
                    writer.write(event.toString().trim()+"\n");
                    }
                }else if( cursor == XMLStreamConstants.END_DOCUMENT){
                    break;
                }
            }
            writer.close();
        } catch (Exception ex) {
            Logger.getLogger(PolishPleaseParser.class.getName()).log(Level.SEVERE, null, ex);
            String msg = ex.getMessage();
            String msgErrorString = "The element type \"";
            if (msg.contains(msgErrorString)) {
                Logger.getLogger(PolishPleaseParser.class.getName()).log(Level.SEVERE, null, msg);
                isXMLWellForm = false;
            }
        }
        return isXMLWellForm;
    }
    
    private static XMLEvent getXMLEvent(XMLStreamReader reader) throws XMLStreamException{
        return  allocator.allocate(reader);
    } 

}
