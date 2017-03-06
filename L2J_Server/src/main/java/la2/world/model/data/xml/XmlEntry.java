package la2.world.model.data.xml;

public class XmlEntry {

	public final boolean isTeleport() {
		return this instanceof XmlTeleport;
	}
	
	public final XmlTeleport asTeleport() {
		return (XmlTeleport) this;
	}
	
	public final boolean isZone() {
		return this instanceof XmlZone;
	}
	
	public final XmlZone asZone() {
		return (XmlZone) this;
	}
}
