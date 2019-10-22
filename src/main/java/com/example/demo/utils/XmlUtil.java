package com.example.demo.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * @title: xml工具类
 * @date: 2018-10-26
 * @author: chy
 */
public class XmlUtil {
    /**
     * 解析xml字符串为Map
     *
     * @param str
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Map<String, String> parseXmlStringToMap(String str) throws ParserConfigurationException, SAXException, IOException {
        Map<String, String> map = new TreeMap<String, String>();
        StringReader sr = new StringReader(str);
        InputSource is = new InputSource(sr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //完全防御XML实体攻击
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                String key = node.getNodeName();
                String value = node.getTextContent();
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * map转换XML
     *
     * @param parameters
     * @return
     */
    public static String formatMapToXmlString(Map<String, String> parameters) {
        StringBuilder sb = new StringBuilder("<xml>");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            sb.append(String.format("<%s>%s</%s>", entry.getKey(), entry.getValue(), entry.getKey()));
        }
        sb.append("</xml>");
        return sb.toString();
    }
}
