package la2.world.model.data.xml;

public class XmlEntry {

	public final boolean isFishingSkillLearn() {
		return this instanceof XmlFishingSkillLearn;
	}
	
	public final XmlFishingSkillLearn asFishingSkillLearn() {
		return (XmlFishingSkillLearn) this;
	}

	public final boolean isPetsStats() {
		return this instanceof XmlPetsStats;
	}
	
	public final XmlPetsStats asPetsStats() {
		return (XmlPetsStats) this;
	}
	
	public final boolean isRandomSpawn() {
		return this instanceof XmlRandomSpawn;
	}
	
	public final XmlRandomSpawn asRandomSpawn() {
		return (XmlRandomSpawn) this;
	}
	
	public final boolean isRandomSpawnLoc() {
		return this instanceof XmlRandomSpawnLoc;
	}
	
	public final XmlRandomSpawnLoc asRandomSpawnLoc() {
		return (XmlRandomSpawnLoc) this;
	}
	public final boolean isNpcSkillLearn() {
		return this instanceof XmlNpcSkillLearn;
	}
	
	public final XmlNpcSkillLearn asNpcSkillLearn() {
		return (XmlNpcSkillLearn) this;
	}
	
	public final boolean isSkillSpellbook() {
		return this instanceof XmlSkillSpellbook;
	}
	
	public final XmlSkillSpellbook asSkillSpellbook() {
		return (XmlSkillSpellbook) this;
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
