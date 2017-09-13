package util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlParser {
	
	   private Document document;
	   private String xmlFilePath;
	   
	   public XmlParser(String xmlFilePath){
		   this.xmlFilePath = xmlFilePath;
		   this.Load();
	   }
	   
	   public void Load() {
		   File xmlFile = new File(xmlFilePath);
			if (xmlFile.exists()) {
				SAXReader sr = new SAXReader();
				try {
					document = sr.read(xmlFile);
				} catch (DocumentException e) {
					System.out.println("xml读取文件失败: " + xmlFilePath);
				}
			}
			else {
				System.out.println("xml读取文件失败: " + xmlFilePath);
			}
	}
	   
	public Element getElementObject(String elementPath) {
		return (Element) document.selectSingleNode(elementPath);
	}
	
	@SuppressWarnings("unchecked")
	public List<Element> getElmentsObject(String elementPath){
		return document.selectNodes(elementPath);
	}
	
	public String getElementText(String elementPath) {
		String value = null;
		Element element = this.getElementObject(elementPath);
		if (element !=  null) {
			value = element.getText();
		}else {
			System.out.println("对象未找到");
		}
		return value;
	}
	
	public List<String> getElmentsTextList(String elementPath) {
		List<String> value = new ArrayList<String>();
		List<Element> result = this.getElmentsObject(elementPath);
		for (Element rElement:result) {
			value.add(rElement.getTextTrim());
		}
		return value;
	}
	
	public Map<String, String> getChildrenInfo(String elementPath) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Element> elements = this.getElmentsObject(elementPath);
		for (Element iterable_element : elements) {
			map.put(iterable_element.getName(), iterable_element.getTextTrim());
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getChildernInfoByElement(Element element) {
		Map<String, String> map = new HashMap<String, String>();
		List<Element> childrens = element.elements();
		for (Element e : childrens) {
			map.put(e.getName(), e.getText());
		}
		return map;
	}
	
	public boolean isExist(String elementPath) {
		boolean flag = false;
		Element element = this.getElementObject(elementPath);
		if (element != null) {
			flag = true;
		}
		return flag;
		
	}
	
	public static void main(String[] args) {
//		XmlParser xmlParser = new XmlParser("book.xml");
//		
//		String value1 = xmlParser.getElementObject("//book/title[text()='Harry Potter']/../author").getText();
//		System.out.println(value1);
//		System.out.println("-------------------------------------");
//		
//		String value2 = xmlParser.getElementText("//book/title[text()='Harry Potter']/../author");
//		System.out.println(value2);
//		System.out.println("-------------------------------------");
//		
//		List<Element> list1 = xmlParser.getElmentsObject("//book/title");
//		List<String> list2 =  new ArrayList<String>();
//		for (Element elementString : list1) {
//			list2.add(elementString.getTextTrim());
//		}
//		for (String listString : list2) {
//			System.out.println(listString);
//		}
//		System.out.println("-------------------------------------");
//		
//		List<String> list3 = xmlParser.getElmentsTextList("//book/title");
//		for (String value3 : list3) {
//			System.out.println(value3);
//		}
//		System.out.println("-------------------------------------");
//		
//		Map<String, String> map = xmlParser.getChildrenInfo("//book[1]/*");
//		Iterable<Entry<String, String>> key = map.entrySet();
//		for (Entry<String, String> string : key) {
//			System.out.print(string.getKey() + "  ");
//			System.out.println(string.getValue());
//		}
		
		XmlParser xParser = new XmlParser("test-data/object.xml");
		Map<String, String> map = new LinkedHashMap<>();
		List<Element> elements = xParser.getElmentsObject("/对象/登录/用户名");
		map = xParser.getChildernInfoByElement(elements.get(0));
		
	}
		

}
