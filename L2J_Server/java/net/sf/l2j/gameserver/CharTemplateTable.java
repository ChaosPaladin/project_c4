package net.sf.l2j.gameserver;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import la2.world.model.Utils;
import la2.world.model.data.xml.XmlLoader;
import la2.world.model.data.xml.XmlPcStats;
import net.sf.l2j.gameserver.model.base.ClassId;
import net.sf.l2j.gameserver.templates.L2PcTemplate;

public final class CharTemplateTable {
	private static Logger _log = Logger.getLogger(CharTemplateTable.class.getName());
	
    public static final String[] charClasses = {
                                                "Human Fighter", "Warrior", "Gladiator", "Warlord", "Human Knight", "Paladin", "Dark Avenger", "Rogue", "Treasure Hunter", "Hawkeye", "Human Mystic", "Human Wizard", "Sorceror", "Necromancer", "Warlock", "Cleric", "Bishop", "Prophet",
                                                "Elven Fighter", "Elven Knight", "Temple Knight", "Swordsinger", "Elven Scout", "Plainswalker", "Silver Ranger", "Elven Mystic", "Elven Wizard", "Spellsinger", "Elemental Summoner", "Elven Oracle", "Elven Elder",
                                                "Dark Fighter", "Palus Knight", "Shillien Knight", "Bladedancer", "Assassin", "Abyss Walker", "Phantom Ranger", "Dark Elven Mystic", "Dark Elven Wizard", "Spellhowler", "Phantom Summoner", "Shillien Oracle", "Shillien Elder",
                                                "Orc Fighter", "Orc Raider", "Destroyer", "Orc Monk", "Tyrant", "Orc Mystic", "Orc Shaman", "Overlord", "Warcryer",
                                                "Dwarven Fighter", "Dwarven Scavenger", "Bounty Hunter", "Dwarven Artisan", "Warsmith", 
                                                "dummyEntry1", "dummyEntry2", "dummyEntry3", "dummyEntry4", "dummyEntry5", "dummyEntry6", "dummyEntry7", "dummyEntry8", "dummyEntry9", "dummyEntry10", "dummyEntry11", "dummyEntry12", "dummyEntry13", "dummyEntry14", "dummyEntry15",
                                                "dummyEntry16", "dummyEntry17", "dummyEntry18", "dummyEntry19", "dummyEntry20", "dummyEntry21", "dummyEntry22", "dummyEntry23", "dummyEntry24", "dummyEntry25", "dummyEntry26", "dummyEntry27", "dummyEntry28", "dummyEntry29", "dummyEntry30", 
                                                "Duelist", "DreadNought", "Phoenix Knight", "Hell Knight", "Sagittarius", "Adventurer", 
                                                "Archmage", "Soultaker", "Arcana Lord", "Cardinal", "Hierophant", 
                                                "Eva Templar", "Sword Muse", "Wind Rider", "Moonlight Sentinel", "Mystic Muse", "Elemental Master", "Eva's Saint", 
                                                "Shillien Templar", "Spectral Dancer", "Ghost Hunter", "Ghost Sentinel", "Storm Screamer", "Spectral Master", "Shillien Saint", 
                                                "Titan", "Grand Khauatari", "Dominator", "Doomcryer", 
                                                "Fortune Seeker", "Maestro"
    };
	
	private final Map<Integer, L2PcTemplate> templates = new TreeMap<>();
	
	public static CharTemplateTable getInstance() {
		return SingletonHolder.instance;
	}
	
	private CharTemplateTable() {
		//TODO
		XmlLoader.load("pc-stats.xml").list.stream().
			map(XmlPcStats.class::cast).
			forEach(entry -> {
				templates.put(Utils.map(entry.pClass).getId() | 0x100, new L2PcTemplate(entry, false));
				templates.put(Utils.map(entry.pClass).getId(), new L2PcTemplate(entry, true));
			});
		_log.config("CharTemplateTable: Loaded " + templates.size() + " Character Templates.");
	}
	
	public L2PcTemplate getTemplate(ClassId classId, boolean female) {
		return getTemplate(classId.getId(), female);
	}
	
	public L2PcTemplate getTemplate(int classId, boolean female) {
		int key = classId;
		if (female)
			key |= 0x100;
		return templates.get(key);
	}
    
    public static final String getClassNameById(int classId) {
        return charClasses[classId];
    }
    
    public static final int getClassIdByName(String className) {
        int currId = 1;
        
        for (String name : charClasses)
        {
            if (name.equalsIgnoreCase(className))
                break;
            
            currId++;
        }
        
        return currId;
    }
	
    private static class SingletonHolder {
    	private static CharTemplateTable instance = new CharTemplateTable();
    }
}
