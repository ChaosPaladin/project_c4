package la2.world.model.data.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class XmlData {
	@XmlElements({
		@XmlElement(name = "armor", type = XmlArmor.class),
		@XmlElement(name = "weapon", type = XmlWeapon.class),
		@XmlElement(name = "etcitem", type = XmlEtcItem.class),
		@XmlElement(name = "fish", type = XmlFish.class),
		@XmlElement(name = "fish-reward", type = XmlFishReward.class),
		@XmlElement(name = "helper-buff", type = XmlHelperBuff.class),
		@XmlElement(name = "pc-stats", type = XmlPcStats.class),
		@XmlElement(name = "territory", type = XmlTerritory.class),
		@XmlElement(name = "mapregion", type = XmlMapRegion.class),
		@XmlElement(name = "henna-tree", type = XmlHennaTree.class),
		@XmlElement(name = "henna", type = XmlHenna.class),
		@XmlElement(name = "shop", type = XmlShop.class),
		@XmlElement(name = "npc", type = XmlNpc.class),
		@XmlElement(name = "minion", type = XmlMinion.class),
		@XmlElement(name = "drop-item", type = XmlDropItem.class),
		@XmlElement(name = "npc-skill", type = XmlNpcSkill.class),
		@XmlElement(name = "pets-stats", type = XmlPetsStats.class),
		@XmlElement(name = "random-spawn", type = XmlRandomSpawn.class),
		@XmlElement(name = "random-spawn-loc", type = XmlRandomSpawnLoc.class),
		@XmlElement(name = "fishingskilllearn", type = XmlFishingSkillLearn.class),
		@XmlElement(name = "npc-skill-learn", type = XmlNpcSkillLearn.class),
		@XmlElement(name = "skill-spellbook", type = XmlSkillSpellbook.class),
		@XmlElement(name = "skilllearn", type = XmlSkillLearn.class),
		@XmlElement(name = "teleport", type = XmlTeleport.class),
		@XmlElement(name = "zone", type = XmlZone.class)
	})
	public List<XmlEntry> list;
}
