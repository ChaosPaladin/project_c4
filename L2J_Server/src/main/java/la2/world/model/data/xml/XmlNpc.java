package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlNpc extends XmlEntry {
	@XmlAttribute public int id;
	@XmlAttribute public int idTemplate;
	@XmlAttribute public String name;
	@XmlAttribute public boolean serverSideName;
	@XmlAttribute public String title;
	@XmlAttribute public boolean serverSideTitle;
	@XmlAttribute public String javaClass;
	@XmlAttribute public int collisionRadius;
	@XmlAttribute public int collisionHeight;
	@XmlAttribute public int level;
	@XmlAttribute public String sex;
	@XmlAttribute public String type;
	@XmlAttribute public int attackrange;
	@XmlAttribute public int hp;
	@XmlAttribute public int mp;
	@XmlAttribute public int STR;
	@XmlAttribute public int CON;
	@XmlAttribute public int DEX;
	@XmlAttribute public int INT;
	@XmlAttribute public int WIT;
	@XmlAttribute public int MEN;
	@XmlAttribute public int exp;
	@XmlAttribute public int sp;
	@XmlAttribute public int patk;
	@XmlAttribute public int pdef;
	@XmlAttribute public int matk;
	@XmlAttribute public int mdef;
	@XmlAttribute public int atkspd;
	@XmlAttribute public int aggro;
	@XmlAttribute public int matkspd;
	@XmlAttribute public int rhand;
	@XmlAttribute public int lhand;
	@XmlAttribute public int armor;
	@XmlAttribute public int walkspd;
	@XmlAttribute public int runspd;
	@XmlAttribute public String factionId;
	@XmlAttribute public int factionRange;
	@XmlAttribute public boolean isUndead;
	@XmlAttribute public int absorbLevel;
}
