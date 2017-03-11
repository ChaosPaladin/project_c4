package la2.world.model.data.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import la2.world.model.item.CrystalType;
import la2.world.model.item.Material;

public  class XmlItem extends XmlEntry {
	@XmlAttribute public int id;
	@XmlAttribute public String name;
	@XmlAttribute public Material material;
	@XmlAttribute public int weight;
	@XmlAttribute public int  durability;
	@XmlAttribute public boolean crystallizable;
	@XmlAttribute public CrystalType crystalType;
	@XmlAttribute public int crystalCount;
	@XmlAttribute public int price;
	@XmlAttribute public boolean sellable;
	
	public static class xmlfor {
		@XmlElements({
			@XmlElement(name = "add", type = xmladd.class),
			@XmlElement(name = "sub", type = xmlsub.class),
			@XmlElement(name = "mul", type = xmlmul.class),
			@XmlElement(name = "div", type = xmldiv.class),
			@XmlElement(name = "set", type = xmlset.class),
			@XmlElement(name = "enchant", type = xmlenchant.class),
			@XmlElement(name = "skill", type = xmlskill.class)
		})
		public List<xmlmod> list;
	}
	
	public static class xmlmod {}
	
	public static class xmlstat extends xmlmod {
		@XmlAttribute public String val;
		@XmlAttribute public String order;
		@XmlAttribute public String stat;
		@XmlElements({
			@XmlElement(name = "and", type = xmland.class),
			@XmlElement(name = "or", type = xmlor.class),
			@XmlElement(name = "not", type = xmlnot.class),
		})
		public xmlcondition condition;
		
	}
	
	public static class xmladd extends xmlstat {}
	public static class xmlsub extends xmlstat {}
	public static class xmlmul extends xmlstat {}
	public static class xmldiv extends xmlstat {}
	public static class xmlset extends xmlstat {}
	public static class xmlenchant extends xmlstat {}
	@XmlType(name = "skill")
	public static class xmlskill extends xmlmod {
		@XmlAttribute public boolean onCrit;
		@XmlAttribute public boolean onCast;
		@XmlAttribute public int id;
		@XmlAttribute public int level;
		@XmlAttribute public int chance = -1;
	}
	
	public static class xmlcondition {
		@XmlElements({
			@XmlElement(name = "and", type = xmland.class),
			@XmlElement(name = "or", type = xmlor.class),
			@XmlElement(name = "not", type = xmlnot.class),
			@XmlElement(name = "player", type = xmlplayer.class),
			@XmlElement(name = "target", type = xmltarget.class),
			@XmlElement(name = "using", type = xmlusing.class),
			@XmlElement(name = "game", type = xmlgame.class)
		})
		public List<xmlcondition> condition;
	}
	
	public static class xmland extends xmlcondition {}
	public static class xmlor extends xmlcondition {}
	public static class xmlnot extends xmlcondition {}
	
	public static class xmlplayer extends xmlcondition {
		@XmlAttribute public int hp;
	}
	public static class xmltarget extends xmlcondition {
		@XmlAttribute public String using;
	}
	public static class xmlusing extends xmlcondition {
		@XmlAttribute public String slotitem;
		@XmlAttribute public String kind;
	}
	public static class xmlgame extends xmlcondition {
		@XmlAttribute public int chance;
	}
}
