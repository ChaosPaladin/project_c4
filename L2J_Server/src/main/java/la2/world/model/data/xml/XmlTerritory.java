package la2.world.model.data.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class XmlTerritory extends XmlEntry {
	@XmlAttribute public int id;
	@XmlElement public List<XmlPoint> points = new LinkedList<>();
	
	
	public static class XmlPoint {
		@XmlAttribute public int x;
		@XmlAttribute public int y;
		@XmlAttribute public int zMin;
		@XmlAttribute public int zMax;
		@XmlAttribute public int proc;
	}
}
