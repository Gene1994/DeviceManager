package pers.quzhe.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * <p>Title: XmlUtils<／p>
 * <p>Description: <／p>
 * @author quzhe
 * 
 * 2017年12月7日
 */
public class XMLUtils {
	
	//获得Document
	public static Document getDocument() throws DocumentException{
		//获得流
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File("..\\LoginDemo\\src\\DB.xml"));
		return document;
	}
	
	//保存
	public static void write(Document document) throws IOException{
		//目标文件位置流
		OutputStream out = new FileOutputStream(new File("..\\LoginDemo\\src\\DB.xml"));
		//获得流
		XMLWriter writer = new XMLWriter(out);
		writer.write(document);
		writer.close();
		out.close();
	}
	
	
}
