package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlNpcSkill extends XmlEntry {
	@XmlAttribute public int npcid;
	@XmlAttribute public int skillid;
	@XmlAttribute public int level;
}
