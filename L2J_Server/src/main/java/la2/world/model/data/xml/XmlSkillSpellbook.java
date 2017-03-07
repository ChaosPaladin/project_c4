package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlSkillSpellbook extends XmlEntry {
	@XmlAttribute public int skillId;
	@XmlAttribute public int itemId;
}
