package la2.world.model.data.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class XmlMultiSell extends XmlEntry {
	@XmlAttribute public String name;
	@XmlAttribute public int id;
	@XmlAttribute public boolean isDutyfree = false;
	@XmlAttribute public boolean isShowAll = true;
	@XmlAttribute public boolean keepEnchanted = false;
	@XmlElement public List<Barter> barter;
	
	public static class Barter {
		@XmlElement public List<Item> goods;
		@XmlElement public List<Item> production;
		@XmlAttribute public int adena = 0;
	}

	public static class Item {
		@XmlAttribute public String name;
		@XmlAttribute public int count;
	}
}
