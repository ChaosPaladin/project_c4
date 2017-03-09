package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlList;

public class XmlMapRegion extends XmlEntry {
	@XmlAttribute public int region;
	@XmlAttribute @XmlList public int[] seg;
}