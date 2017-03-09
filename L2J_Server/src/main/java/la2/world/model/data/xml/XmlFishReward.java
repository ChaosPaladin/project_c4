package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlFishReward extends XmlEntry {
	@XmlAttribute public int fishid;
	@XmlAttribute public int rewardid;
	@XmlAttribute public int count;
	@XmlAttribute public int minchance;
	@XmlAttribute public int maxchance;
}
