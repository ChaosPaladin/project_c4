package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import la2.world.model.creature.PlayerClass;

public class XmlPcStats extends XmlEntry {
	
	@XmlJavaTypeAdapter(PlayerClassAdapter.class)
	@XmlAttribute public PlayerClass pClass;
	@XmlAttribute public String className;
	@XmlAttribute public int race;
	@XmlAttribute public int STR;
	@XmlAttribute public int CON;
	@XmlAttribute public int DEX;
	@XmlAttribute public int INT;
	@XmlAttribute public int WIT;
	@XmlAttribute public int MEN;
	@XmlAttribute public int pAtk;
	@XmlAttribute public int pDef;
	@XmlAttribute public int mAtk;
	@XmlAttribute public int mDef;
	@XmlAttribute public int pSpd;
	@XmlAttribute public int mSpd;
	@XmlAttribute public int acc;
	@XmlAttribute public int critical;
	@XmlAttribute public int evasion;
	@XmlAttribute public int moveSpeed;
	@XmlAttribute public int load;
	@XmlAttribute public int x;
	@XmlAttribute public int y;
	@XmlAttribute public int z;
	@XmlAttribute public boolean canCraft;
	@XmlAttribute public double mColRadius;
	@XmlAttribute public double mColHeight;
	@XmlAttribute public double fColRadius;
	@XmlAttribute public double fColHeight;
	@XmlAttribute public int[] items;
	
	@XmlAttribute public float defaulthpbase;
	@XmlAttribute public float defaulthpadd;
	@XmlAttribute public float defaulthpmod;
	@XmlAttribute public float defaultcpbase;
	@XmlAttribute public float defaultcpadd;
	@XmlAttribute public float defaultcpmod;
	@XmlAttribute public float defaultmpbase;
	@XmlAttribute public float defaultmpadd;
	@XmlAttribute public float defaultmpmod;
	@XmlAttribute public int level;
}
