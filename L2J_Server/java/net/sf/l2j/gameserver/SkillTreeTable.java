package net.sf.l2j.gameserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import la2.world.model.creature.PlayerClass;
import la2.world.model.data.xml.XmlEntry;
import la2.world.model.data.xml.XmlLoader;
import la2.world.model.data.xml.XmlSkillLearn;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.L2SkillLearn;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.base.ClassId;

public final class SkillTreeTable {
	private static Logger _log = Logger.getLogger(SkillTreeTable.class.getName());
	private static SkillTreeTable _instance;
	
	private final L2SkillLearn[][] skillTree = new L2SkillLearn[PlayerClass.ARRAY_SIZE][];
	private final L2SkillLearn[] fishingSkillTree;
	private final L2SkillLearn[] expandDwarfCraftSkillTrees;

    //Массив в котором содержится для каждого класса, по id, списки со скиллами, включая родительские классы.
    private final ArrayList<L2SkillLearn[]> skilllisttable = new ArrayList<L2SkillLearn[]>(PlayerClass.ARRAY_SIZE);

    public static SkillTreeTable getInstance()
	{
        if (_instance == null)
            _instance = new SkillTreeTable();    
		return _instance;
	}
	
	/**
	 * Return the minimum level needed to have this Expertise.<BR><BR>
	 * 
	 * @param grade The grade level searched
	 */
    //DOC
	public int getExpertiseLevel(int grade) {
		if (grade <= 0)
			return 0;
		for(L2SkillLearn skill : skilllisttable.get(PlayerClass.paladin.id))
			if(skill.id == 239 && skill.level == grade)
				return skill.minLevel;
		throw new Error("Expertise not found for grade "+grade);
	}
	
	/*
	 * Find skill for playerclass with id and level
	 * If not found return Optional.empty
	 * @param id target skill id
	 * @param level target skill level
	 * @return
	 */
	private final Optional<L2SkillLearn> find(int id, int level, PlayerClass pClass) {
		do {
			for(L2SkillLearn sLearn : skillTree[pClass.id])
				if(sLearn.id == id && sLearn.level == level)
					return Optional.of(sLearn);
		} while((pClass = pClass.parent) != null);
		return Optional.empty();
	}
	
	//DEVNOTE map ClassId to PlayerClass
	private final static PlayerClass map(ClassId classId) {
		return PlayerClass.find(classId.getId());
	}
	
	//DOC
	public final int getMinSkillLevel(int id, int level, PlayerClass pClass) {
		if(id > 0 && level > 0)
			return find(id, level, pClass).map(x -> x.minLevel).orElse(0);
		return 0;
	}
	
	//DEVNOTE old version getMinSkillLevel
	@Deprecated
    public final int getMinSkillLevel(int skillID, ClassId classID, int skillLvl) {
    	return getMinSkillLevel(skillID, skillLvl, map(classID));
    }
	
    //TODO document
    public int getMinSkillLevel(int id, int level) {
    	if(id > 0 && level > 0)
	    	for(L2SkillLearn[] list : skillTree)
	    		for(L2SkillLearn sLearn : list)
	    			if(sLearn.level == level && sLearn.id == id)
	    				return sLearn.minLevel;
        return 0;
    }

    //DOC
    //собирает все скиллы включая родительский класс.
    //DEVNOTE helper function
    private final List<L2SkillLearn> collect(PlayerClass pClass, List<L2SkillLearn> result) {
    	if(pClass.parent != null)
    		collect(pClass.parent, result);
    	if(skillTree[pClass.id] != null)
    		for(L2SkillLearn sLearn : skillTree[pClass.id])
    			result.add(sLearn);
    	return result;
    }
    
    private SkillTreeTable() {
		int count   = 0;
		
		final List<XmlSkillLearn> skillListTree = 
				XmlLoader.load("skilltree.xml").list.
					stream().
					filter(XmlEntry::isSkillLearn).
					map(XmlEntry::asSkillLearn).
					collect(Collectors.toList());
		//set null array for all classes
		for(int i = 0 ; i < PlayerClass.ARRAY_SIZE ; i++)
			skillTree[i] = new L2SkillLearn[0];
		for(PlayerClass pClass : PlayerClass.values()) {
			List<L2SkillLearn> list = skillListTree.stream().filter(x -> x.pClass == pClass).map(L2SkillLearn::new).collect(Collectors.toList());
			skillTree[pClass.id] = list.toArray(new L2SkillLearn[list.size()]);
			count += list.size();
			_log.fine("SkillTreeTable: skill tree for class " + pClass + " has " + list.size() + " skills");
		}
		_log.config("SkillTreeTable: Loaded " + count + " skills.");
		
		//set null array for all classes
		for(int i = 0 ; i < PlayerClass.ARRAY_SIZE ; i++)
			skilllisttable.add(i, new L2SkillLearn[0]);
		//creating  separeted list for every class including skills of parent's classes 
		for(PlayerClass pClass : PlayerClass.values()) {
			final List<L2SkillLearn> result = collect(pClass, new LinkedList<L2SkillLearn>());	
			skilllisttable.add(pClass.id, result.toArray(new L2SkillLearn[result.size()]));
		}
      
    	final LinkedList<L2SkillLearn> fishingList = new LinkedList<>();//скилы для рыбалки
    	final LinkedList<L2SkillLearn> dwarfList = new LinkedList<>();//craft
        XmlLoader.load("fishing_skill_trees.xml").list.stream().
        	filter(XmlEntry::isFishingSkillLearn).
        	map(XmlEntry::asFishingSkillLearn).
        	forEach(entry -> {
        		L2SkillLearn sLearn = new L2SkillLearn(entry.asSkillLearn());
        		if(entry.isfordwarf)
        			dwarfList.add(sLearn);
        		else
        			fishingList.add(sLearn);
        	});
        
    	fishingSkillTree = fishingList.toArray(new L2SkillLearn[fishingList.size()]);
    	expandDwarfCraftSkillTrees = dwarfList.toArray(new L2SkillLearn[dwarfList.size()]);
    	
        _log.config("FishingSkillTreeTable: Loaded " + fishingList.size() + " general skills.");
        _log.config("FishingSkillTreeTable: Loaded " + dwarfList.size() + " dwarven skills.");
    }
    
	//TODO document
	public L2SkillLearn[] getAvailableSkills(L2PcInstance player, final PlayerClass pClass) {
		final List<L2SkillLearn> result = new LinkedList<L2SkillLearn>();
		return collect(player, player.getAllSkills(), pClass, result).toArray(new L2SkillLearn[result.size()]);
	}
	
	//TODO document
	//Возвращает список скилов, которые может изучить игрок
	//Производит поиск и добавления сначало в родительских класса, рекурсивным вызовом, после возврата собирает в дочернем классе, из которого был произвидент вызов
	private final List<L2SkillLearn> collect(final L2PcInstance player, final L2Skill[] skills, final PlayerClass pClass, final List<L2SkillLearn> result) {
		if(pClass == null)
			return result;
		
		if(skillTree[pClass.id] != null)
			for(L2SkillLearn sLearn : skillTree[pClass.id]) {
				if(sLearn.minLevel <= player.getLevel()) {
					boolean known = false;
					for(L2Skill pSkill : skills) {
						if(pSkill.getId() == sLearn.id) {
							known = true;
							if(pSkill.getLevel() == sLearn.level - 1)
								result.add(sLearn);
						}
					}
					if(!known && sLearn.level == 1)
						result.add(sLearn);
				}
			}
		return result;
	}

	//DEVNOTE helper function
	private final List<L2SkillLearn> collect(final L2PcInstance player, final L2Skill[] skills, final L2SkillLearn[] learnList, final LinkedList<L2SkillLearn> result) {
		for(L2SkillLearn sLearn : learnList) {
			if(sLearn.minLevel <= player.getLevel()) {
				boolean known = false;
				for(L2Skill pSkill : skills) {
					if(pSkill.getId() == sLearn.id) {
						known = true;
						if(pSkill.getLevel() == sLearn.level - 1)
							result.add(sLearn);
					}
				}
				if(!known && sLearn.level == 1)
					result.add(sLearn);
			}
		}
		return result;
	}
	
	@Deprecated
	public L2SkillLearn[] getAvailableSkills(L2PcInstance cha, ClassId classId) {
		return getAvailableSkills(cha, map(classId));
	}
    
	public L2SkillLearn[] getAvailableSkills(L2PcInstance player) {
		final LinkedList<L2SkillLearn> result = new LinkedList<>();
		final L2Skill[] skills = player.getAllSkills();
		collect(player, skills, fishingSkillTree, result);
		if(player.hasDwarvenCraft())
			collect(player, skills, expandDwarfCraftSkillTrees, result);
		return result.toArray(new L2SkillLearn[result.size()]);
	}
	
	/**
	 * Returns all allowed skills for a given class. - Fairy
	 * @param classId
	 * @return
	 */
	@Deprecated
	public List<L2SkillLearn> getAllowedSkills(ClassId classId) {
		return getAllowedSkills(map(classId));
	}
	
	private static final List<L2SkillLearn> empty = new LinkedList<>();
	
	//DOC
	public List<L2SkillLearn> getAllowedSkills(PlayerClass pClass) {
		return Optional.of(pClass).map(x -> Arrays.asList(skilllisttable.get(x.id))).orElse(empty);
	}
	
	//DEVNOTE helper function
	private int getMinLevel(L2SkillLearn[] list, int level) {
		for(L2SkillLearn sLearn : list)
			if(level == 0 || sLearn.minLevel < level)
				level = sLearn.minLevel;
		return level;
	}
	
	//DOC
	public final int getMinLevelForNewSkill(L2PcInstance player, PlayerClass pClass) {
		assert pClass == null : "getMinLevelForNewSkill null pClass";
		if(pClass == null)
			return 0;
		return getMinLevel(skilllisttable.get(pClass.id), 0);
				
	}

	//DOC
	@Deprecated
	public int getMinLevelForNewSkill(L2PcInstance cha, ClassId classId) {
		return getMinLevelForNewSkill(cha, map(classId));
	}
	
	public int getMinLevelForNewSkill(L2PcInstance player) {
		int level = getMinLevel(fishingSkillTree, 0);
		if(player.hasDwarvenCraft())
			level = getMinLevel(expandDwarfCraftSkillTrees, level);
		return level;
	}

    public int getSkillCost(L2PcInstance player, L2Skill skill) {
        int skillCost = 100000000;
        final PlayerClass pClass = map(player.getClassId());
        ClassId classId = player.getClassId();
        for(L2SkillLearn sLearn : skilllisttable.get(pClass.id)) {
        	if(sLearn.id != skill.getId() || sLearn.level != skill.getLevel() || sLearn.minLevel > player.getLevel())
        		continue;
        	skillCost = sLearn.sp;
        	//ДИЧЬ
        	if(!player.getClassId().equalsOrChildOf(classId)) {
        		if(skill.getCrossLearnAdd() < 0)
        			continue;
        		skillCost += skill.getCrossLearnAdd();
        		skillCost *= skill.getCrossLearnMul();
        	}
        	if((classId.getRace() != player.getRace()) && !player.isSubClassActive())
        		skillCost *= skill.getCrossLearnRace();
            if (classId.isMage() != player.getClassId().isMage())
                skillCost *= skill.getCrossLearnProf();
        }
        return skillCost;
    }
}
