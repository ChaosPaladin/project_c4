package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlDropItem extends XmlEntry {
	@XmlAttribute public int mobId;
	@XmlAttribute public int itemId;
	@XmlAttribute public int min;
	@XmlAttribute public int max;
	@XmlAttribute public boolean sweep;
	@XmlAttribute public int chance;
	@XmlAttribute public int category;
}
