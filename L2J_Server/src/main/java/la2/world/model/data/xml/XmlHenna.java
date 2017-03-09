package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlHenna extends XmlEntry {
	@XmlAttribute public int id;
	@XmlAttribute public String name;
	@XmlAttribute public int dyeId;
	@XmlAttribute public int dyeAmount;
	@XmlAttribute public int price;
	@XmlAttribute public int statINT;
	@XmlAttribute public int statSTR;
	@XmlAttribute public int statCON;
	@XmlAttribute public int statMEN;
	@XmlAttribute public int statDEX;
	@XmlAttribute public int statWIT;
}
