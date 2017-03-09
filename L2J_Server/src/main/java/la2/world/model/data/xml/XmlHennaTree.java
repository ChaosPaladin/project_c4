package la2.world.model.data.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import la2.world.model.creature.PlayerClass;

public class XmlHennaTree extends XmlEntry {
	@XmlJavaTypeAdapter(PlayerClassAdapter.class)
	@XmlAttribute public PlayerClass pClass;
	@XmlList public int[] symbols = {};
}
