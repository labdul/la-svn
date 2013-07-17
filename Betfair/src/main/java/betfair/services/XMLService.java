package betfair.services;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XMLService {

	private XPathFactory xPathFactory;
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder builder;
	private Document document;
	private Node root;
	private XPath xPath;
	
	private final static String XPATH_TEXT="/text()";
	private final static String XPATH_COUNT = "count(%s)";
	private final static String XPATH_ATTRIB="/@";
	
	public XMLService(InputStream InFile) throws ParserConfigurationException, SAXException, IOException {
		
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		builder = documentBuilderFactory.newDocumentBuilder();
		document = builder.parse(InFile);
		root = document.getDocumentElement();
		xPathFactory = XPathFactory.newInstance();
		xPath = xPathFactory.newXPath();
		
	}
	
	public XMLService(String FilePath) throws ParserConfigurationException, SAXException, IOException {		
		
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		builder = documentBuilderFactory.newDocumentBuilder();
		InputStream InFile = new FileInputStream(FilePath);
		document = builder.parse(InFile);
		//document = builder.parse(XPATH_ATTRIB);
		root = document.getDocumentElement();
		xPathFactory = XPathFactory.newInstance();
		xPath = xPathFactory.newXPath();
		
	}
	
	/** This will return the count of a element represented by xPath (e.g. //div[@class='test']) in an XML file
	 * @param ElementxPath - The element identifier in xPath format
	 * @return The count of the element represented by xPath
	 */
	public int CountNode (String ElementxPath) {
		
		String xpath = String.format(XPATH_COUNT, ElementxPath);
		
		try {
			Number count = (Number)xPath.evaluate(xpath, root, XPathConstants.NUMBER);
			return count.intValue();			
		}
		catch (Exception e) {
			return 0;
		}		
	}
	
	/**This will return the count of a tag (e.g. div) in an XML file
	 * @param TagName - Name of the tag of which you need the count
	 * @return The count of the tags found in the XML file/stream
	 */
	public int CountTags(String TagName) {		
		return document.getElementsByTagName(TagName).getLength();
	}


	/** This method will return the text contained within the tag identified by xpath
	 * @param ElementxPath : The xpath to the element <b>without</b> the trailing <i>'/text()'</i> for which the text is to be retrieved.
	 * @return The string equivalent of the text value contained in the tags. 
	 * @throws XPathExpressionException
	 */
	public String GetText(String ElementxPath) throws XPathExpressionException {
		
		//String xPath = dbconf.getValue("RESP_SEL_OPENBET_ID_TEXT_TYPE3_XPATH_DYM"); 
		String xpath = ElementxPath + XPATH_TEXT;
		
		
		String Text = (String)xPath.evaluate(xpath, root, XPathConstants.STRING);
		return Text;
	}	

	/** This method will return the attrib value for the tag identified by xpath
	 * @param ElementxPath : The xpath to the element <b>without</b> the trailing <i>'/'</i> of which the attribute is to be retrieved.
	 * @param Attribute : The attribute of which the value has to be retrieved.
	 * @return The Attribute value
	 * @throws XPathExpressionException
	 */
	public String GetAttrib(String ElementxPath, String Attribute) throws XPathExpressionException {
		
		//String xPath = dbconf.getValue("RESP_SEL_OPENBET_ID_TEXT_TYPE3_XPATH_DYM"); 
		String xpath = ElementxPath + XPATH_ATTRIB + Attribute;
		String Text = (String)xPath.evaluate(xpath, root, XPathConstants.STRING);
		return Text;
	}
	

}