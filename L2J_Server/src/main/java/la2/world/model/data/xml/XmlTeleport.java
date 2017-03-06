package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlTeleport extends XmlEntry {
	@XmlAttribute public String  description;
	@XmlAttribute public int  id;
	@XmlAttribute public int  x;
	@XmlAttribute public int  y;
	@XmlAttribute public int  z;
	@XmlAttribute public int  price;
}
