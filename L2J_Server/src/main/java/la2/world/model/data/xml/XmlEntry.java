package la2.world.model.data.xml;

public class XmlEntry {

	public final boolean isFishingSkillLearn() {
		return this instanceof XmlFishingSkillLearn;
	}
	
	public final XmlFishingSkillLearn asFishingSkillLearn() {
		return (XmlFishingSkillLearn) this;
	}
	
	public final boolean isSkillLearn() {
		return this instanceof XmlSkillLearn;
	}
	
	public final XmlSkillLearn asSkillLearn() {
		return (XmlSkillLearn) this;
	}
	
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
