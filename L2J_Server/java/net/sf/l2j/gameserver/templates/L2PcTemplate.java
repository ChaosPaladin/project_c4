package net.sf.l2j.gameserver.templates;

import java.util.LinkedList;
import java.util.List;

import la2.world.model.Utils;
import la2.world.model.data.xml.XmlPcStats;
import net.sf.l2j.gameserver.ItemTable;
import net.sf.l2j.gameserver.model.base.ClassId;
import net.sf.l2j.gameserver.model.base.Race;

public class L2PcTemplate extends L2CharTemplate {
	
	/** The Class object of the L2PcInstance */
	public final ClassId classId;
	
	public final Race   race;
	public final String className;
	public final int    baseLoad;
	
	public final int    spawnX;
	public final int    spawnY;
	public final int    spawnZ;
	
	public final boolean isMale;
	
	public final int     classBaseLevel;
	public final float   lvlHpAdd;
	public final float   lvlHpMod;
	public final float   lvlCpAdd;
	public final float   lvlCpMod;
	public final float   lvlMpAdd;
	public final float   lvlMpMod;
	
	private List<L2Item> _items = new LinkedList<L2Item>();
	
	public L2PcTemplate(XmlPcStats stats, boolean male) {
		super(stats);
		classId = Utils.map(stats.pClass);
		race = Race.values()[stats.race];
		className = stats.className;
		baseLoad = stats.load;
		spawnX = stats.x;
		spawnY = stats.y;
		spawnZ = stats.z;
		lvlHpAdd = stats.defaulthpadd;
		lvlHpMod = stats.defaulthpmod;
		lvlMpAdd = stats.defaultmpadd;
		lvlMpMod = stats.defaultmpmod;
		lvlCpAdd = stats.defaultcpadd;
		lvlCpMod = stats.defaultcpmod;
		classBaseLevel = stats.level;
		if(male) {
			collisionHeight = stats.mColHeight;
			collisionRadius = stats.mColRadius;
		} else {
			collisionHeight = stats.fColHeight;
			collisionRadius = stats.fColRadius;
		}
		isMale = male;
		for(int item : stats.items)
			if(item > 0)
				addItem(item);
	}
	
	public L2PcTemplate(StatsSet set) {
		super(set);
		classId   = ClassId.values()[set.getInteger("classId")];
		race      = Race.values()[set.getInteger("raceId")];
		className = set.getString("className");
		baseLoad  = set.getInteger("baseLoad");
		
		spawnX    = set.getInteger("spawnX");
		spawnY    = set.getInteger("spawnY");
		spawnZ    = set.getInteger("spawnZ");
		
		isMale    = set.getBool  ("isMale", true);

		classBaseLevel = set.getInteger("classBaseLevel");
		lvlHpAdd  = set.getFloat("lvlHpAdd");
		lvlHpMod  = set.getFloat("lvlHpMod");
        lvlCpAdd  = set.getFloat("lvlCpAdd");
        lvlCpMod  = set.getFloat("lvlCpMod");
		lvlMpAdd  = set.getFloat("lvlMpAdd");
		lvlMpMod  = set.getFloat("lvlMpMod");
	}
	
	/**
	 * add starter equipment
	 * @param i
	 */
	public void addItem(int itemId) {
		L2Item item = ItemTable.getInstance().getTemplate(itemId);
		if (item != null)
			_items.add(item);
	}
	
	/**
	 *
	 * @return itemIds of all the starter equipment
	 */
	public L2Item[] getItems()
	{
		return _items.toArray(new L2Item[_items.size()]);
	}

}
