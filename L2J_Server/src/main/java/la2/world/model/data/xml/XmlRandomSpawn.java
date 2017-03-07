package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlRandomSpawn extends XmlEntry {
	@XmlAttribute public int groupId;
	@XmlAttribute public int npcId;
	@XmlAttribute public int count;
	@XmlAttribute public int initialDelay;
	@XmlAttribute public int respawnDelay;
	@XmlAttribute public int despawnDelay;
	@XmlAttribute public boolean broadcastSpawn;
	@XmlAttribute public boolean randomSpawn;
}
