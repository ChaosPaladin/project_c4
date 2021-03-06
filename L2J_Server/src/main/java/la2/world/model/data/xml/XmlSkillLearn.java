package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import la2.world.model.creature.PlayerClass;

public class XmlSkillLearn extends XmlEntry {
	@XmlJavaTypeAdapter(PlayerClassAdapter.class)
	@XmlAttribute(name="classId") public PlayerClass pClass;
	@XmlAttribute public int  skillId;
	@XmlAttribute public int  level;
	@XmlAttribute public String name;
	@XmlAttribute public int sp;
	@XmlAttribute public int minLevel;
	@XmlAttribute public int costid = 0;
	@XmlAttribute public int costcount = 0;
	

}
