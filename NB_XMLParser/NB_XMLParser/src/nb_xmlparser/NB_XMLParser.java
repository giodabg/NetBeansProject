/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb_xmlparser;

// http://www.java2s.com/Code/Java/XML/ParseanXMLstringUsingDOMandaStringReader.htm
// https://examples.javacodegeeks.com/core-java/xml/java-xml-parser-tutorial/
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Gio
 */
public class NB_XMLParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException,
            SAXException, IOException {
        // TODO code application logic here
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document;

        if (args.length != 1) {
            //throw new RuntimeException("The name of the XML file is required!");
            String xmlRecords = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                    + "<Employees>\n"
                    + "     <Employee ID=\"1\">\n"
                    + "          <Firstname>Lebron</Firstname >\n"
                    + "          <Lastname>James</Lastname>\n"
                    + "          <Age>30</Age>\n"
                    + "          <Salary>2500</Salary>\n"
                    + "     </Employee>\n"
                    + "     <Employee ID=\"2\">\n"
                    + "          <Firstname>Anthony</Firstname>\n"
                    + "          <Lastname>Davis</Lastname>\n"
                    + "          <Age>22</Age>\n"
                    + "          <Salary>1500</Salary>\n"
                    + "     </Employee>\n"
                    + "     <Employee ID=\"3\">\n"
                    + "          <Firstname>Paul</Firstname>\n"
                    + "          <Lastname>George</Lastname>\n"
                    + "          <Age>24</Age>\n"
                    + "          <Salary>2000</Salary>\n"
                    + "     </Employee>\n"
                    + "     <Employee ID=\"4\">\n"
                    + "          <Firstname>Blake</Firstname>\n"
                    + "          <Lastname>Griffin</Lastname>\n"
                    + "          <Age>25</Age>\n"
                    + "          <Salary>2250</Salary>\n"
                    + "     </Employee>\n"
                    + "</Employees>";

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlRecords));

            // Read XML string, parse it and return an instance of the
            // Document class.
            document = builder.parse(is);
        } else {

            // Load the input XML document, parse it and return an instance of the
            // Document class.
            document = builder.parse(new File(args[0]));
        }
        List<Employee> employees = new ArrayList<Employee>();

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                // Get the value of the ID attribute.
                String ID = node.getAttributes().getNamedItem("ID")
                        .getNodeValue();

                // Get the value of all sub-elements.
                String firstname = elem.getElementsByTagName("Firstname")
                        .item(0).getChildNodes().item(0).getNodeValue();

                Node nodeInt = elem.getElementsByTagName("Lastname").item(0);
                String lastname = nodeInt.getChildNodes().item(0).getNodeValue();

                nodeInt = elem.getElementsByTagName("Age").item(0);
                Node nodeLeaf = nodeInt.getChildNodes().item(0);
                Integer age = Integer.parseInt(nodeLeaf.getNodeValue());

                Double salary = Double.parseDouble(elem
                        .getElementsByTagName("Salary").item(0).getChildNodes()
                        .item(0).getNodeValue());

                employees.add(new Employee(ID, firstname, lastname, age, salary));
            }
        }

        // Print all employees.
        for (Employee empl : employees) {
            System.out.println(empl.toString());
        }
        System.out.println("\nVISUALIZZAZIONE DOM");
        printElements(document);
    }

    static void printElements(Document doc) {
        NodeList nl = doc.getElementsByTagName("Employee");
        Node n;

        for (int i = 0; i < nl.getLength(); i++) {
            n = nl.item(i);
            System.out.println(n.getNodeName() + " - ");
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                NodeList ncl = n.getChildNodes();
                Node nc;
                for (int j = 0; j < ncl.getLength(); j++) {
                    nc = ncl.item(i);
                    System.out.println(nc.getNodeType() + " + ");
                }

            }
        }

        System.out.println();
    }

    static void printElementAttributes(Document doc) {
        NodeList nl = doc.getElementsByTagName("*");
        Element e;
        Node n;
        NamedNodeMap nnm;

        String attrname;
        String attrval;
        int i, len;

        len = nl.getLength();
        for (int j = 0; j < len; j++) {
            e = (Element) nl.item(j);
            System.out.println(e.getTagName() + ":");
            nnm = e.getAttributes();
            if (nnm != null) {
                for (i = 0; i < nnm.getLength(); i++) {
                    n = nnm.item(i);
                    attrname = n.getNodeName();
                    attrval = n.getNodeValue();
                    System.out.print(" " + attrname + " = " + attrval);
                }
            }
            System.out.println();
        }
    }
}
