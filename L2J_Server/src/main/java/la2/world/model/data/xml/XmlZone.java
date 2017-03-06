package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlZone extends XmlEntry {
	@XmlAttribute public int  id;
	@XmlAttribute public String type;
	@XmlAttribute public String name;
	@XmlAttribute public int x1;
	@XmlAttribute public int y1;
	@XmlAttribute public int x2;
	@XmlAttribute public int y2;
	@XmlAttribute public int z;
	@XmlAttribute public int taxById;
}
