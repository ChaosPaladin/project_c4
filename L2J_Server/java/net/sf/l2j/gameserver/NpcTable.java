/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package net.sf.l2j.gameserver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javolution.util.FastList;
import javolution.util.FastMap;
import la2.world.model.creature.PlayerClass;
import la2.world.model.data.xml.XmlDropItem;
import la2.world.model.data.xml.XmlEntry;
import la2.world.model.data.xml.XmlLoader;
import la2.world.model.data.xml.XmlMinion;
import la2.world.model.data.xml.XmlNpcSkill;
import net.sf.l2j.Config;
import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.model.L2DropData;
import net.sf.l2j.gameserver.model.L2MinionData;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.base.ClassId;
import net.sf.l2j.gameserver.skills.Stats;
import net.sf.l2j.gameserver.templates.L2NpcTemplate;
import net.sf.l2j.gameserver.templates.StatsSet;

/**
 * This class ...
 * 
 * @version $Revision: 1.8.2.6.2.9 $ $Date: 2005/04/06 16:13:25 $
 */
public class NpcTable
{
	private static Logger _log = Logger.getLogger(NpcTable.class.getName());

	private static NpcTable _instance;

	private Map<Integer, L2NpcTemplate> _npcs;
	private boolean _initialized = false;

	public static NpcTable getInstance()
	{
		if (_instance == null)
			_instance = new NpcTable();

		return _instance;
	}

	private NpcTable() {
		_npcs = new TreeMap<Integer, L2NpcTemplate>();
        
		restoreNpcData();
	}

	//helper method
	private void addSkill(final XmlNpcSkill skill) {
		final L2NpcTemplate npc = _npcs.get(skill.npcid);
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
		L2NpcTemplate npc = _npcs.get(entry.mobId);
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
	    final L2NpcTemplate npc = _npcs.get(entry.bossId);
	    if(npc != null) {
	    	final L2MinionData minion = new L2MinionData();
	    	minion.setMinionId(entry.minionId);
	    	minion.setAmountMin(entry.amountMin);
	    	minion.setAmountMax(entry.amountMax);
			npc.addRaidData(minion);
	    } else
	    	_log.warning("Npc table: not found raidboss " + entry.bossId + " for minion " + entry.minionId);
		
	}
	private void restoreNpcData()
	{
		java.sql.Connection con = null;
        
		try
		{
			try 
            {
			    con = L2DatabaseFactory.getInstance().getConnection();
			    PreparedStatement statement;
			    statement = con.prepareStatement("SELECT " + L2DatabaseFactory.getInstance().safetyString(new String[] {"id", "idTemplate", "name", "serverSideName", "title", "serverSideTitle", "class", "collision_radius", "collision_height", "level", "sex", "type", "attackrange", "hp", "mp", "str", "con", "dex", "int", "wit", "men", "exp", "sp", "patk", "pdef", "matk", "mdef", "atkspd", "aggro", "matkspd", "rhand", "lhand", "armor", "walkspd", "runspd", "faction_id", "faction_range", "isUndead", "absorb_level"}) + " FROM npc");
			    ResultSet npcdata = statement.executeQuery();
			    
			    fillNpcTable(npcdata);
			    npcdata.close();
			    statement.close();
            } 
            catch (Exception e) {
                _log.severe("NPCTable: Error creating NPC table: " + e);
            }
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
        finally {
		    try { con.close(); } catch (Exception e) {}
		}
        
        _initialized = true;
	}

	private void fillNpcTable(ResultSet NpcData)
			throws Exception
	{
		while (NpcData.next())
		{
			StatsSet npcDat = new StatsSet(); 
			int id = NpcData.getInt("id");
			
            if (Config.ASSERT) 
                assert id < 1000000; 

			npcDat.set("npcId", id);
			npcDat.set("idTemplate",NpcData.getInt("idTemplate"));
			int level = NpcData.getInt("level");
			npcDat.set("level", level);
			npcDat.set("jClass", NpcData.getString("class"));

			npcDat.set("baseShldDef", 0);
			npcDat.set("baseShldRate", 0);
			npcDat.set("baseCritRate",  38);

			npcDat.set("name", NpcData.getString("name"));
			npcDat.set("serverSideName", NpcData.getBoolean("serverSideName"));
			//npcDat.set("name", "");
			npcDat.set("title",NpcData.getString("title"));
			npcDat.set("serverSideTitle",NpcData.getBoolean("serverSideTitle"));
			npcDat.set("collision_radius", NpcData.getDouble("collision_radius"));
			npcDat.set("collision_height", NpcData.getDouble("collision_height"));
			npcDat.set("sex", NpcData.getString("sex"));
			npcDat.set("type", NpcData.getString("type"));
			npcDat.set("baseAtkRange", NpcData.getInt("attackrange"));
			npcDat.set("revardExp", NpcData.getInt("exp"));
			npcDat.set("revardSp", NpcData.getInt("sp"));
			npcDat.set("basePAtkSpd", NpcData.getInt("atkspd"));
			npcDat.set("baseMAtkSpd", NpcData.getInt("matkspd"));
			npcDat.set("aggroRange", NpcData.getInt("aggro"));
			npcDat.set("rhand", NpcData.getInt("rhand"));
			npcDat.set("lhand", NpcData.getInt("lhand"));
			npcDat.set("armor", NpcData.getInt("armor"));
			npcDat.set("baseWalkSpd", NpcData.getInt("walkspd"));
			npcDat.set("baseRunSpd", NpcData.getInt("runspd"));

			npcDat.set("baseHpReg", 1.5 + ((level-1)/10));
            npcDat.set("baseCpReg", 0);
			npcDat.set("baseMpReg", 0.9 + 0.3*((level-1)/10));

			// constants, until we have stats in DB
			npcDat.set("baseSTR", NpcData.getInt("str"));
			npcDat.set("baseCON", NpcData.getInt("con"));
			npcDat.set("baseDEX", NpcData.getInt("dex"));
			npcDat.set("baseINT", NpcData.getInt("int"));
			npcDat.set("baseWIT", NpcData.getInt("wit"));
			npcDat.set("baseMEN", NpcData.getInt("men"));

			npcDat.set("baseHpMax", NpcData.getInt("hp"));
            npcDat.set("baseCpMax", 0);
			npcDat.set("baseMpMax", NpcData.getInt("mp"));
			npcDat.set("basePAtk", NpcData.getInt("patk"));
			npcDat.set("basePDef", NpcData.getInt("pdef"));
			npcDat.set("baseMAtk", NpcData.getInt("matk"));
			npcDat.set("baseMDef", NpcData.getInt("mdef"));

			npcDat.set("factionId", NpcData.getString("faction_id"));
			npcDat.set("factionRange", NpcData.getInt("faction_range"));
            
            npcDat.set("isUndead", NpcData.getString("isUndead"));
            
            npcDat.set("absorb_level", NpcData.getString("absorb_level"));

			L2NpcTemplate template = new L2NpcTemplate(npcDat);
			template.addResist(Stats.POWER_DEFENCE,100);
			template.addResist(Stats.BOW_WPN_RES,100);
			template.addResist(Stats.BLUNT_WPN_RES,100);
			template.addResist(Stats.DAGGER_WPN_RES,100);

			_npcs.put(id, template);
		}
        
		_log.config("NpcTable: Loaded " + _npcs.size() + " Npc Templates.");
	}

	public void reloadNpc(int id)
	{
		java.sql.Connection con = null;
        
		try
		{
			// save a copy of the old data
			L2NpcTemplate old = getTemplate(id);
			Map<Integer,L2Skill> skills = new FastMap<Integer,L2Skill>();
            
			if (old.getSkills() != null)
				skills.putAll(old.getSkills());
            
			List<L2DropData> drops = new FastList<L2DropData>();
            
			if (old.getDropData() != null)
				drops.addAll(old.getDropData());
            
			ClassId[] classIds = null;
            
			if (old.getTeachInfo() != null)
				classIds=old.getTeachInfo().clone();
            
			List<L2MinionData> minions = new FastList<L2MinionData>();
            
			if (old.getMinionData() != null)
				minions.addAll(old.getMinionData());

			// reload the NPC base data
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement st = con.prepareStatement("SELECT " + L2DatabaseFactory.getInstance().safetyString(new String[] {"id", "idTemplate", "name", "serverSideName", "title", "serverSideTitle", "class", "collision_radius", "collision_height", "level", "sex", "type", "attackrange", "hp", "mp", "str", "con", "dex", "int", "wit", "men", "exp", "sp", "patk", "pdef", "matk", "mdef", "atkspd", "aggro", "matkspd", "rhand", "lhand", "armor", "walkspd", "runspd", "faction_id", "faction_range", "isUndead", "absorb_level"}) + " FROM npc WHERE id=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			fillNpcTable(rs);
			rs.close();
			st.close();

			// restore additional data from saved copy
			L2NpcTemplate created = getTemplate(id);
            
			for (L2Skill skill : skills.values())
				created.addSkill(skill);
            
			for (L2DropData drop : drops)
				created.addDropData(drop);
            
			if (classIds != null)
				for (ClassId classId : classIds)
					created.addTeachInfo(classId);
            
			for (L2MinionData minion : minions)
				created.addRaidData(minion);
		}
		catch (Exception e)
		{
			_log.warning("NPCTable: Could not reload data for NPC " + id + ": " + e);
		}
		finally
		{
			try { con.close(); } catch (Exception e) {}
		}
	}

    // just wrapper
    public void reloadAllNpc()
    {
        restoreNpcData();
    }
    
    
    @Deprecated
    public void saveNpc(StatsSet npc)
    {
        java.sql.Connection con = null;
        String query = "";
        
        try
        {
            con = L2DatabaseFactory.getInstance().getConnection();
            Map<String, Object> set = npc.getSet();

            String name = "";
            String values = "";
            
            for (Object obj : set.keySet())
            {
                name = (String)obj;
                
                if (!name.equalsIgnoreCase("npcId"))
                {
                    if (values != "")
                        values += ", ";

                    values += name + " = '" + set.get(name) + "'";
                }
            }
            
            query = "UPDATE npc SET " + values + " WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, npc.getInteger("npcId"));
            statement.execute();
            statement.close();
        }
        catch (Exception e)
        {
            _log.warning("NPCTable: Could not store new NPC data in database: " + e);
        } 
        finally 
        {
            try { con.close(); } catch (Exception e) {}
        }
    }

	public boolean isInitialized()
	{
		return _initialized;
	}
    
    public void replaceTemplate(L2NpcTemplate npc)
    {
        _npcs.put(npc.npcId, npc);
    }

	public L2NpcTemplate getTemplate(int id)
	{
		return _npcs.get(id);
	}
	
    public L2NpcTemplate getTemplateByName(String name)
    {
        for (L2NpcTemplate npcTemplate : _npcs.values())
            if (npcTemplate.name.equalsIgnoreCase(name))
                return npcTemplate;
       
        return null;
    }

	public L2NpcTemplate[] getAllOfLevel(int lvl)
	{
		List<L2NpcTemplate> list = new FastList<L2NpcTemplate>();
        
		for (L2NpcTemplate t : _npcs.values())
			if (t.level == lvl)
				list.add(t);

		return list.toArray(new L2NpcTemplate[list.size()]);
	}
	
	//maping from new PlayerClass enum to old ClassId
	public static ClassId map(PlayerClass pClass) {
		return ClassId.values()[pClass.id];
	}
}
