package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlRandomSpawnLoc extends XmlEntry {
	@XmlAttribute public int groupId;
	@XmlAttribute public int x;
	@XmlAttribute public int y;
	@XmlAttribute public int z;
	@XmlAttribute public int heading;
}
