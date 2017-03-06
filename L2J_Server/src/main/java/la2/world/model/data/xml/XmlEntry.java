package la2.world.model.data.xml;

public class XmlEntry {

	public final boolean isZone() {
		return this instanceof XmlZone;
	}
	
	public final XmlZone asZone() {
		return (XmlZone) this;
	}
}
