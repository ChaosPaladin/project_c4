package la2.world.model.data.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class XmlData {
	@XmlElements({
		@XmlElement(name ="zone", type = XmlZone.class)
	})
	public List<XmlEntry> list;
}