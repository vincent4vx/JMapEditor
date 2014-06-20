///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package org.pvemu.mapeditor.common;
//
//import java.io.File;
//import java.io.IOException;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import org.pvemu.mapeditor.action.JMapEditor;
//import org.pvemu.mapeditor.data.MapData;
//import org.pvemu.mapeditor.data.Tile;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
///**
// *
// * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
// */
//final public class XMLUtils {
//    
//    static public void saveMapXML(MapData map, File file) throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
//        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//        Element root = document.createElement("map");
//        
//        Element width = document.createElement("width");
//        width.appendChild(document.createTextNode("" + map.getWidth()));
//        root.appendChild(width);
//        
//        Element height = document.createElement("height");
//        height.appendChild(document.createTextNode("" + map.getHeight()));
//        root.appendChild(height);
//        
//        Element data = document.createElement("data");
//        data.appendChild(document.createTextNode(Compressor.compressMapData(map)));
//        root.appendChild(data);
//        
//        Element background = document.createElement("background");
//        background.appendChild(document.createTextNode("" + (map.getBackground() != null ? map.getBackground().getId() : 0)));
//        root.appendChild(background);
//        
//        document.appendChild(root);
//        
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//        DOMSource source = new DOMSource(document);
//        StreamResult result = new StreamResult(file);
//
//        transformer.transform(source, result);
//    }
//    
//    static public MapData loadMapXML(File file) throws ParserConfigurationException, SAXException, IOException, Exception{
//        int width = 0,
//            height = 0,
//            background = 0;
//        String mapData = null;
//        
//        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
//        
//        NodeList list = document.getElementsByTagName("map").item(0).getChildNodes();
//        
//        for(int i = 0; i < list.getLength(); ++i){
//            Element e = (Element)list.item(i);
//            
//            switch(e.getTagName()){
//                case "height":
//                    height = Integer.parseInt(e.getTextContent());
//                    break;
//                case "width":
//                    width = Integer.parseInt(e.getTextContent());
//                    break;
//                case "background":
//                    background = Integer.parseInt(e.getTextContent());
//                    break;
//                case "data":
//                    mapData = e.getTextContent();
//                    break;
//            }
//        }
//        
//        if(width == 0 || height == 0 || mapData == null || mapData.length() < Utils.getCellNumberBySize(width, height) * 10){
//            throw new Exception("données corrompues");
//        }
//        
//        MapData map = new MapData(width, height, Compressor.decompressMapData(mapData));
//        
//        if(background != 0){
//            Tile bg = JMapEditor.getTilesHandler().getBackgrounds().getTileById(background);
//            
//            if(bg != null)
//                map.setBackground(bg);
//        }
//        
//        return map;
//    }
//}
