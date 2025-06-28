package velXml;

import java.io.File;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import velXml.model.Item;
import velXml.model.Items;

public class Main {

	public static void main(String[] args) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Items.class);

		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		// File Type (difficult to synchronize to Java models)
//		Schema schema = sf.newSchema(new File("xsd/items.xsd"));
		// Class Type
		Schema schema = sf.newSchema(new StreamSource(
				ClassLoader.getSystemResourceAsStream("velXml/xsd/items.xsd")));

		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setSchema(schema);

		Items items = (Items) unmarshaller.unmarshal(new File("xml/items.xml"));

		Properties p = new Properties();
		// File Type
//		p.setProperty("file.resource.loader.path", "template");
		// Class Type
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(p);

		VelocityContext mainContext = new VelocityContext();
		mainContext.put("items", items);

		List<String> itemTemplates = new ArrayList<>();
		for (Item item : items.getItemList()) {
			if (item.getType() != null
					&& item.getType().getValue() != null
					&& item.getType().getValue().equals("TYPE1")) {
				Template itemTemplate = Velocity.getTemplate("velXml/template/item_type1.vm");
				VelocityContext itemContext = new VelocityContext();
				itemContext.put("item", item);
				StringWriter sw = new StringWriter();
				itemTemplate.merge(itemContext, sw);
				itemTemplates.add(sw.toString());
			} else if (item.getType() != null
					&& item.getType().getValue() != null
					&& item.getType().getValue().equals("TYPE2")) {
				Template itemTemplate = Velocity.getTemplate("velXml/template/item_type2.vm");
				VelocityContext itemContext = new VelocityContext();
				itemContext.put("item", item);
				StringWriter sw = new StringWriter();
				itemTemplate.merge(itemContext, sw);
				itemTemplates.add(sw.toString());
			}
		}
		mainContext.put("itemTemplates", itemTemplates);

		Template itemsTemplate = Velocity.getTemplate("velXml/template/items.vm");
		StringWriter sw = new StringWriter();
		itemsTemplate.merge(mainContext, sw);

		Files.writeString(Paths.get("result/result.txt"), sw.toString(), Charset.forName("UTF-8"));
		System.out.println(sw.toString());
	}

}
