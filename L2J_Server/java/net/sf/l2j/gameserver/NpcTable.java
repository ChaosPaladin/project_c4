package net.sf.l2j.gameserver;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import la2.world.model.creature.PlayerClass;
import la2.world.model.data.xml.XmlDropItem;
import la2.world.model.data.xml.XmlEntry;
import la2.world.model.data.xml.XmlLoader;
import la2.world.model.data.xml.XmlMinion;
import la2.world.model.data.xml.XmlNpc;
import la2.world.model.data.xml.XmlNpcSkill;
import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.L2DropData;
import net.sf.l2j.gameserver.model.L2MinionData;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.base.ClassId;
import net.sf.l2j.gameserver.skills.Stats;
import net.sf.l2j.gameserver.templates.L2NpcTemplate;

public final class NpcTable {
	private static Logger _log = Logger.getLogger(NpcTable.class.getName());
	
	private Map<Integer, L2NpcTemplate> npcs;

	public static NpcTable getInstance() {
		return SingletonHolder.instance;
	}

	private NpcTable() {
		npcs = new TreeMap<Integer, L2NpcTemplate>();
        
		restoreNpcData();
	}

	//helper method
	private void addSkill(final XmlNpcSkill skill) {
		final L2NpcTemplate npc = npcs.get(skill.npcid);
		if(npc != null) {
		    if (npc.race == 0) {
		        if (skill.skillid >= 4290 && skill.skillid <= 4302) {
		        	npc.setRace(skill.skillid);
		            return;
		        }
            }
		    if(npc.rateHp == 1) {
		    	if(skill.skillid >= 4303 && skill.skillid <= 4310) {
		    		if(!Config.ENABLE_RATE_HP)
		    			return;
		    		npc.setRateHp(skill.skillid - 4301);
		    		return;
		    	} else if(skill.skillid == 4311) {
		    		if(!Config.ENABLE_RATE_HP)
		    			return;
		    		npc.setRateHp(0.5);
		    		return;
		    	}
		    }
		    switch(skill.skillid) {
			    case 4084: {
			    	int resistPAtk = npc.getResist(Stats.POWER_DEFENCE) - 20;
			    	if(resistPAtk < 0)
			    		resistPAtk = 0;
			    	npc.removeResist(Stats.POWER_DEFENCE);
			    	npc.addResist(Stats.POWER_DEFENCE, resistPAtk);
			    	break;
			    }
			    case 4272:
			    case 4276: {
	                int resistBow = npc.getResist(Stats.BOW_WPN_RES);
	                if (skill.skillid == 4272)
	                    resistBow -= 70;
	                else if (skill.skillid == 4276)
	                    resistBow += 20;
	                if (resistBow < 0) 
	                    resistBow = 0;
	                npc.removeResist(Stats.BOW_WPN_RES);
	                npc.addResist(Stats.BOW_WPN_RES, resistBow);
	                break;
			    }
			    case 4273: {
	                int resistDagger = npc.getResist(Stats.DAGGER_WPN_RES) - 80;
	                if (resistDagger < 0) 
	                    resistDagger = 0;
	                npc.removeResist(Stats.DAGGER_WPN_RES);
	                npc.addResist(Stats.DAGGER_WPN_RES, resistDagger);
	                break;
			    }
			    case 4274: {
	                int resistBlunt = npc.getResist(Stats.BLUNT_WPN_RES) + 20;
	                if(resistBlunt < 0) 
	                    resistBlunt = 0;
	                npc.removeResist(Stats.BLUNT_WPN_RES);
	                npc.addResist(Stats.BLUNT_WPN_RES, resistBlunt);
			    	break;
			    }
		    }  
			final L2Skill sInfo = SkillTable.getInstance().getInfo(skill.skillid, skill.level);
			if (sInfo != null) {
				npc.addSkill(sInfo);
			} else
				;//_log.warning("Npc skill: not found skill with id " + skill.skillid + " level " + skill.level);
		} else
			_log.warning("Npc skill: not found npc with id " + skill.npcid);		
	}
	
	//helper method
	private void addDrop(XmlDropItem entry) {
		L2NpcTemplate npc = npcs.get(entry.mobId);
        if (npc == null) {
            _log.severe("NPCTable: No npc correlating with id : " + entry.mobId);
            return;
        }
        final L2DropData drop = new L2DropData();
        drop.setItemId(entry.itemId);
        drop.setMinDrop(entry.min);
        drop.setMaxDrop(entry.max);
        drop.setSweep(entry.sweep);
        drop.setChance(entry.chance);
        
        // uncategorized drops: marked as uncategorized, or are sweep, or this is a raidboss
        // uncategorized allows many drops to be given from the same list
        // the following mobs are grandbosses.  Grandbosses are L2Monster but should be treated like RBs.
        // 12001    Queen Ant
        // 12169    Orfen
        // 12211    Antharas
        // 12372    Baium
        // 12374    Zaken
        // 12899    Valakas                
        if ((entry.category == 0)  || (drop.isSweep()) || (npc.type.compareToIgnoreCase("L2RaidBoss") == 0) || (npc.type.compareToIgnoreCase("L2Boss") == 0)) {
        	npc.addDropData(drop);
        } else {//  categorized as full drops and parts for armor/weapon/jewel
            if (entry.category == 1)
            	npc.addFullDropData(drop);
            else             // other items (miscellaneous like scrolls, potions, mats, etc).
            	npc.addMiscDropData(drop);
        }		
	}
	
	//helper methods
	public void addMinion(final XmlMinion entry) {
	    final L2NpcTemplate npc = npcs.get(entry.bossId);
	    if(npc != null) {
	    	final L2MinionData minion = new L2MinionData();
	    	minion.setMinionId(entry.minionId);
	    	minion.setAmountMin(entry.amountMin);
	    	minion.setAmountMax(entry.amountMax);
			npc.addRaidData(minion);
	    } else
	    	_log.warning("Npc table: not found raidboss " + entry.bossId + " for minion " + entry.minionId);
		
	}
	//helper function
	private void addNpc(final L2NpcTemplate npc) {
		npc.addResist(Stats.POWER_DEFENCE,100);
		npc.addResist(Stats.BOW_WPN_RES,100);
		npc.addResist(Stats.BLUNT_WPN_RES,100);
		npc.addResist(Stats.DAGGER_WPN_RES,100);
		npcs.put(npc.npcId, npc);
	}
	
	private void restoreNpcData() {
		XmlLoader.load("npc.xml").list.stream().
			filter(XmlNpc.class::isInstance).
			map(L2NpcTemplate::new).
			forEach(this::addNpc);
		_log.info("NPCTable: loaded npc's templates " + npcs.size());
		
		XmlLoader.load("npcskills.xml").list.stream().
			filter(XmlEntry::isNpcSkill).
			map(XmlEntry::asNpcSkill).
			forEach(this::addSkill);
		
		XmlLoader.load("droplist.xml").list.stream().
			filter(XmlDropItem.class::isInstance).
			map(XmlDropItem.class::cast).
			forEach(this::addDrop);

		XmlLoader.load("npc-skill-learn.xml").list.stream().
			filter(XmlEntry::isNpcSkillLearn).
			map(XmlEntry::asNpcSkillLearn).
			forEach(entry -> {
				final L2NpcTemplate npc = getTemplate(entry.npcId);
				if(npc != null) {
					npc.addTeachInfo(map(entry.pClass));
				} else
					_log.warning("NPCTable: Error getting NPC template ID " + entry.npcId + " while trying to load skill trainer data.");
			});
        
		XmlLoader.load("minions.xml").list.stream().
			filter(XmlMinion.class::isInstance).
			map(XmlMinion.class::cast).
			forEach(this::addMinion);
		//TODO _log.config("NpcTable: Loaded " + count + " Minions.");			 
	}
	
    // just wrapper
    public void reloadAllNpc() {
        restoreNpcData();
    }

	public L2NpcTemplate getTemplate(int id) {
		return npcs.get(id);
	}
	
    public L2NpcTemplate getTemplateByName(String name)
    {
        for (L2NpcTemplate npcTemplate : npcs.values())
            if (npcTemplate.name.equalsIgnoreCase(name))
                return npcTemplate;
       
        return null;
    }

	public L2NpcTemplate[] getAllOfLevel(int lvl)
	{
		List<L2NpcTemplate> list = new LinkedList<L2NpcTemplate>();
        
		for (L2NpcTemplate t : npcs.values())
			if (t.level == lvl)
				list.add(t);

		return list.toArray(new L2NpcTemplate[list.size()]);
	}
	
	//maping from new PlayerClass enum to old ClassId
	private static ClassId map(PlayerClass pClass) {
		return ClassId.values()[pClass.id];
	}
	
	private static final class SingletonHolder {
		private static final NpcTable instance = new NpcTable();
	}
}
