package la2.world.model.data.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;

import la2.world.model.item.ArmorType;
import la2.world.model.item.BodyPart;

public class XmlArmor extends XmlItem {
	@XmlList @XmlAttribute public BodyPart[] bodypart;
	@XmlAttribute public ArmorType armorType;
	@XmlAttribute public int avoidModify;
	@XmlAttribute public int pDef;
	@XmlAttribute public int mDef;
	@XmlAttribute public int mpBonus;
	
	@XmlElement(name = "for", type = xmlfor.class)
	public List<xmlfor> list;
}