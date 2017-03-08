package la2.world.model.data.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class XmlShop extends XmlEntry {
	@XmlAttribute public int shopId;
	@XmlAttribute public String npcId;
	@XmlElement(name="buysell")
	public List<XmlBuysell> list = new LinkedList<>();
	
	public static class XmlBuysell {
		@XmlAttribute public int itemId;
		@XmlAttribute public int price;
		@XmlAttribute public int shopIid;
		@XmlAttribute public int order;
	}
}
