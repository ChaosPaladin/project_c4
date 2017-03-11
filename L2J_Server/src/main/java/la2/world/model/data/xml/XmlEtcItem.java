package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

import la2.world.model.item.ConsumeType;
import la2.world.model.item.EtcItemType;

public class XmlEtcItem extends XmlItem {
	@XmlAttribute public EtcItemType itemType;
	@XmlAttribute public ConsumeType consumeType;
	@XmlAttribute public String  dropCategory;
}
