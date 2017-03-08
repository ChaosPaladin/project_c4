package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlPetsStats extends XmlEntry {
	@XmlAttribute public String  type;
	@XmlAttribute public int  typeID;
	@XmlAttribute public int  level;
	@XmlAttribute public int  expMax;
	@XmlAttribute public int  hpMax;
	@XmlAttribute public int  mpMax;
	@XmlAttribute public int  patk;
	@XmlAttribute public int  pdef;
	@XmlAttribute public int  matk;
	@XmlAttribute public int  mdef;
	@XmlAttribute public int  acc;
	@XmlAttribute public int  evasion;
	@XmlAttribute public int  crit;
	@XmlAttribute public int  speed;
	@XmlAttribute public int  atkSpeed;
	@XmlAttribute public int  castSpeed;
	@XmlAttribute public int  feedMax;
	@XmlAttribute public int  feedbattle;
	@XmlAttribute public int  feednormal;
	@XmlAttribute public int  loadMax;
	@XmlAttribute public int  hpregen;
	@XmlAttribute public int  mpregen;
}
