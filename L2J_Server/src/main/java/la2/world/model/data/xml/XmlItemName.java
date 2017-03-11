package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlItemName extends XmlEntry {
	@XmlAttribute public int id;
	@XmlAttribute public String name;
}
