package la2.world.model.data.xml;

import javax.xml.bind.JAXB;

public class XmlLoader {

	public static XmlData load(String file) {
		return JAXB.unmarshal(XmlLoader.class.getClassLoader().getResourceAsStream("data/"+file), XmlData.class);
	}
	
}
