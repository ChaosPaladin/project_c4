package net.sf.l2j.gameserver;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import la2.world.model.data.xml.XmlEntry;
import la2.world.model.data.xml.XmlLoader;
import net.sf.l2j.gameserver.model.L2Skill;

public final class SkillSpellbookTable {
	private static Logger _log = Logger.getLogger(SkillTreeTable.class.getName());
	private static SkillSpellbookTable instance;

	private static Map<Integer, Integer> skillSpellbooks;

	public static SkillSpellbookTable getInstance() {
        if (instance == null)
        	instance = new SkillSpellbookTable();
		return instance;
	}
    
	private SkillSpellbookTable() {
		skillSpellbooks = new TreeMap<Integer, Integer>();
		XmlLoader.load("skill_spellbooks.xml").list.stream().
			filter(XmlEntry::isSkillSpellbook).
			map(XmlEntry::asSkillSpellbook).
			forEach(entry -> {
				skillSpellbooks.put(entry.skillId, entry.itemId);
			});
		_log.config("SkillSpellbookTable: Loaded " + skillSpellbooks.size() + " Spellbooks.");
	}

    public int getBookForSkill(int skillId) {
    	return skillSpellbooks.getOrDefault(skillId, -1);
    }
    
    public int getBookForSkill(L2Skill skill) {
        return getBookForSkill(skill.getId());
    }
}
