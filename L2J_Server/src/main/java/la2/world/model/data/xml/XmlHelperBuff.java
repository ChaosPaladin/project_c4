package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlHelperBuff extends XmlEntry {
	@XmlAttribute public int  id;
	@XmlAttribute public int  skillId;
	@XmlAttribute public String  name;
	@XmlAttribute public int  skillLevel;
	@XmlAttribute public int  lowerLevel;
	@XmlAttribute public int  upperLevel;
	@XmlAttribute public boolean  isMagicClass;
}
