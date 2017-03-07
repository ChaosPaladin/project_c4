package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import la2.world.model.creature.PlayerClass;

public class XmlNpcSkillLearn extends XmlEntry {
	@XmlAttribute public int npcId;
	@XmlJavaTypeAdapter(PlayerClassAdapter.class)
	@XmlAttribute public PlayerClass pClass; 
}
