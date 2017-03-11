package la2.world.model.data.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import la2.world.model.item.BodyPart;
import la2.world.model.item.WeaponType;

public class XmlWeapon extends XmlItem {
	@XmlAttribute public BodyPart bodypart;
	@XmlAttribute public int soulshots;
	@XmlAttribute public int spiritshots;
	@XmlAttribute public int pDam;
	@XmlAttribute public int rndDam;
	@XmlAttribute public WeaponType weaponType;
	@XmlAttribute public int critical;
	@XmlAttribute public int hitModify;
	@XmlAttribute public int avoidModify;
	@XmlAttribute public int shieldDef;
	@XmlAttribute public int shieldDefRate;
	@XmlAttribute public int atkSpeed;
	@XmlAttribute public int mpConsume;
	@XmlAttribute public int mDam;
	
	@XmlElement(name = "for", type = xmlfor.class)
	public List<xmlfor> list;
}
