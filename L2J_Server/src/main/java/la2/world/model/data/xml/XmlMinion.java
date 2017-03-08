package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlMinion extends XmlEntry {
	@XmlAttribute public int bossId;
	@XmlAttribute public int minionId;
	@XmlAttribute public int amountMin;
	@XmlAttribute public int amountMax;
}
