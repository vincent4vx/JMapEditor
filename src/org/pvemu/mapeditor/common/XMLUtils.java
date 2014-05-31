/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.common;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.pvemu.mapeditor.data.MapData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class XMLUtils {
    
    static public void saveMapXML(MapData map, File file) throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElement("map");
        
        Element width = document.createElement("width");
        width.appendChild(document.createTextNode("" + map.getWidth()));
        root.appendChild(width);
        
        Element height = document.createElement("height");
        height.appendChild(document.createTextNode("" + map.getHeight()));
        root.appendChild(height);
        
        Element data = document.createElement("data");
        data.appendChild(document.createTextNode(Compressor.compressMapData(map)));
        root.appendChild(data);
        
        Element background = document.createElement("background");
        background.appendChild(document.createTextNode("" + (map.getBackground() != null ? map.getBackground().getId() : 0)));
        root.appendChild(background);
        
        document.appendChild(root);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(file);

        transformer.transform(source, result);
    }
}
