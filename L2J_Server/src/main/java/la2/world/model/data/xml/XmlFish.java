package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlFish extends XmlEntry {
	@XmlAttribute public int id;
	@XmlAttribute public int level;
	@XmlAttribute public String name;
	@XmlAttribute public int hp;
	@XmlAttribute public int hpregen;
	@XmlAttribute public int type;
	@XmlAttribute public int speed;
}
