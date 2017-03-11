package net.sf.l2j.gameserver;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;

import javolution.util.FastMap;
import la2.world.model.data.xml.XmlItemName;
import la2.world.model.data.xml.XmlLoader;
import net.sf.l2j.Config;
import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.idfactory.IdFactory;
import net.sf.l2j.gameserver.model.L2ItemInstance;
import net.sf.l2j.gameserver.model.L2Object;
import net.sf.l2j.gameserver.model.L2PetDataTable;
import net.sf.l2j.gameserver.model.L2World;
import net.sf.l2j.gameserver.model.L2ItemInstance.ItemLocation;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.templates.L2Armor;
import net.sf.l2j.gameserver.templates.L2ArmorType;
import net.sf.l2j.gameserver.templates.L2EtcItem;
import net.sf.l2j.gameserver.templates.L2Item;
import net.sf.l2j.gameserver.templates.L2Weapon;
import net.sf.l2j.gameserver.templates.L2WeaponType;

/**
 * This class ...
 * 
 * @version $Revision: 1.9.2.6.2.9 $ $Date: 2005/04/02 15:57:34 $
 */
public class ItemTable
{
	private static Logger _log = Logger.getLogger(ItemTable.class.getName());
	private static Logger _logItems = Logger.getLogger("item");
	
	private static final Map<String, Integer> _materials = new FastMap<String, Integer>();
	private static final Map<String, Integer> _crystalTypes = new FastMap<String, Integer>();
	private static final Map<String, L2WeaponType> _weaponTypes = new FastMap<String, L2WeaponType>();
	private static final Map<String, L2ArmorType> _armorTypes = new FastMap<String, L2ArmorType>();
	private static final Map<String, Integer> _slots = new FastMap<String, Integer>();
	
	private L2Item[] _allTemplates;
	private final Map<Integer, L2EtcItem> _etcItems = new TreeMap<>();
	private final Map<Integer, L2Armor>   _armors = new TreeMap<>();
	private final Map<Integer, L2Weapon>  _weapons = new TreeMap<>();
	
	static
	{
		_materials.put("paper",            L2Item.MATERIAL_PAPER);
		_materials.put("wood",             L2Item.MATERIAL_WOOD);
		_materials.put("liquid",           L2Item.MATERIAL_LIQUID);
		_materials.put("cloth",            L2Item.MATERIAL_CLOTH);
		_materials.put("leather",          L2Item.MATERIAL_LEATHER);
		_materials.put("horn",             L2Item.MATERIAL_HORN);
		_materials.put("bone",             L2Item.MATERIAL_BONE);
		_materials.put("bronze",           L2Item.MATERIAL_BRONZE);
		_materials.put("fine_steel",       L2Item.MATERIAL_FINE_STEEL);
		_materials.put("cotton",           L2Item.MATERIAL_FINE_STEEL);
		_materials.put("mithril",          L2Item.MATERIAL_MITHRIL);
		_materials.put("silver",           L2Item.MATERIAL_SILVER);
		_materials.put("gold",             L2Item.MATERIAL_GOLD);
		_materials.put("adamantaite",      L2Item.MATERIAL_ADAMANTAITE);
		_materials.put("steel",            L2Item.MATERIAL_STEEL);
		_materials.put("oriharukon",       L2Item.MATERIAL_ORIHARUKON);
		_materials.put("blood_steel",      L2Item.MATERIAL_BLOOD_STEEL);
		_materials.put("crystal",          L2Item.MATERIAL_CRYSTAL);
		_materials.put("damascus",         L2Item.MATERIAL_DAMASCUS);
		_materials.put("chrysolite",       L2Item.MATERIAL_CHRYSOLITE);
		_materials.put("scale_of_dragon",  L2Item.MATERIAL_SCALE_OF_DRAGON);
		_materials.put("dyestuff",         L2Item.MATERIAL_DYESTUFF);
		_materials.put("cobweb",           L2Item.MATERIAL_COBWEB);
        _materials.put("seed",             L2Item.MATERIAL_SEED);
        
		_crystalTypes.put("s",             L2Item.CRYSTAL_S);
		_crystalTypes.put("a",             L2Item.CRYSTAL_A);
		_crystalTypes.put("b",             L2Item.CRYSTAL_B);
		_crystalTypes.put("c",             L2Item.CRYSTAL_C);
		_crystalTypes.put("d",             L2Item.CRYSTAL_D);
		_crystalTypes.put("none",          L2Item.CRYSTAL_NONE);

		_weaponTypes.put("blunt",          L2WeaponType.BLUNT);
		_weaponTypes.put("bow",            L2WeaponType.BOW);
		_weaponTypes.put("dagger",         L2WeaponType.DAGGER);
		_weaponTypes.put("dual",           L2WeaponType.DUAL);
		_weaponTypes.put("dualfist",       L2WeaponType.DUALFIST);
		_weaponTypes.put("etc",            L2WeaponType.ETC);
		_weaponTypes.put("fist",           L2WeaponType.FIST);
		_weaponTypes.put("none",           L2WeaponType.NONE); // these are shields !
		_weaponTypes.put("pole",           L2WeaponType.POLE);
		_weaponTypes.put("sword",          L2WeaponType.SWORD);
        _weaponTypes.put("bigsword",       L2WeaponType.BIGSWORD); //Two-Handed Swords
        _weaponTypes.put("pet",            L2WeaponType.PET); //Pet Weapon 
        _weaponTypes.put("rod",            L2WeaponType.ROD); //Fishing Rods
        
		_armorTypes.put("none",            L2ArmorType.NONE);
		_armorTypes.put("light",           L2ArmorType.LIGHT);
		_armorTypes.put("heavy",           L2ArmorType.HEAVY);
		_armorTypes.put("magic",           L2ArmorType.MAGIC);
        _armorTypes.put("pet",             L2ArmorType.PET);
		
		_slots.put("chest",                L2Item.SLOT_CHEST);
		_slots.put("fullarmor",            L2Item.SLOT_FULL_ARMOR); 
		_slots.put("head",                 L2Item.SLOT_HEAD);
		_slots.put("hair",                 L2Item.SLOT_HAIR);
		_slots.put("underwear",            L2Item.SLOT_UNDERWEAR);
		_slots.put("back",                 L2Item.SLOT_BACK);
		_slots.put("neck",                 L2Item.SLOT_NECK);
		_slots.put("legs",                 L2Item.SLOT_LEGS);
		_slots.put("feet",                 L2Item.SLOT_FEET);
		_slots.put("gloves",               L2Item.SLOT_GLOVES);
		_slots.put("chest,legs",           L2Item.SLOT_CHEST | L2Item.SLOT_LEGS);
		_slots.put("rhand",                L2Item.SLOT_R_HAND);
		_slots.put("lhand",                L2Item.SLOT_L_HAND);
		_slots.put("lrhand",               L2Item.SLOT_LR_HAND);
		_slots.put("rear,lear",            L2Item.SLOT_R_EAR | L2Item.SLOT_L_EAR);
		_slots.put("rfinger,lfinger",      L2Item.SLOT_R_FINGER | L2Item.SLOT_L_FINGER);
        _slots.put("none",                 L2Item.SLOT_NONE);
        _slots.put("wolf",                 L2Item.SLOT_WOLF);  // for wolf
        _slots.put("hatchling",            L2Item.SLOT_HATCHLING); // for hatchling
        _slots.put("strider",              L2Item.SLOT_STRIDER); // for strider
	}


	//#name table
	private final HashMap<String, L2Item> names = new HashMap<>();
	
	public L2Item get(String name) {
		return names.get(name);
	}
	//#name table
	
	private static ItemTable _instance;

    /**
     * Returns instance of ItemTable
     * @return ItemTable 
     */
	public static ItemTable getInstance()
	{
		if (_instance == null)
		{
			_instance = new ItemTable();
		}
		return _instance;
	}
	
	/**
	 * Returns a new object Item
	 * @return
	 */
    public Item newItem() {
        return new Item();	
	}
    
    /**
     * Constructor.
     */
	public ItemTable() {
		_armors.clear();
		_etcItems.clear();
		_weapons.clear();
		XmlLoader.load("armor.xml").list.stream().
			map(L2Armor::new).
			forEach(item -> _armors.put(item.getItemId(), item));
		XmlLoader.load("etcitem.xml").list.stream().
			map(L2EtcItem::new).
			forEach(item -> _etcItems.put(item.getItemId(), item));
		XmlLoader.load("weapon.xml").list.stream().
			map(L2Weapon::new).
			forEach(item -> _weapons.put(item.getItemId(), item));
        _log.config("ItemTable: Loaded " + _armors.size() + " Armors.");
        _log.config("ItemTable: Loaded " + _etcItems.size() + " Items.");
        _log.config("ItemTable: Loaded " + _weapons.size() + " Weapons.");
        buildFastLookupTable();
        names.clear();
		XmlLoader.load("item-table.xml").list.stream().
			map(XmlItemName.class::cast).
			forEach(data -> {
				final L2Item item = getTemplate(data.id);
				if(item == null)
					_log.warning("not linked name with L2Item " + data.id + " " + data.name);
				else
					names.put(data.name, item);
			});
		
	}

    /**
     * Builds a variable in which all items are putting in in function of their ID.
     */
	private void buildFastLookupTable()
	{
		int highestId = 0;		
		
		// Get highest ID of item in armor FastMap, then in weapon FastMap, and finally in etcitem FastMap
		for (Iterator<Integer> iter = _armors.keySet().iterator(); iter.hasNext();)
		{
			Integer id = iter.next();
			L2Armor item = _armors.get(id);
			if (item.getItemId() > highestId)
			{
				highestId = item.getItemId();
			}
		}
		for (Iterator<Integer> iter = _weapons.keySet().iterator(); iter.hasNext();)
		{
			
			Integer id = iter.next();
			L2Weapon item = _weapons.get(id);
			if (item.getItemId() > highestId)
			{
				highestId = item.getItemId();
			}
		}
		for (Iterator<Integer> iter = _etcItems.keySet().iterator(); iter.hasNext();)
		{
			Integer id = iter.next();
			L2EtcItem item = _etcItems.get(id);
			if (item.getItemId() > highestId)
			{
				highestId = item.getItemId();
			}
		}
		
		// Create a FastLookUp Table called _allTemplates of size : value of the highest item ID
		if (Config.DEBUG) _log.fine("highest item id used:" + highestId);
		_allTemplates = new L2Item[highestId +1];
		
		// Insert armor item in Fast Look Up Table
		for (Iterator<Integer> iter = _armors.keySet().iterator(); iter.hasNext();)
		{
			Integer id = iter.next();
			L2Armor item = _armors.get(id);
			assert _allTemplates[id.intValue()] == null;
			_allTemplates[id.intValue()] = item;
		}

		// Insert weapon item in Fast Look Up Table
		for (Iterator<Integer> iter = _weapons.keySet().iterator(); iter.hasNext();)
		{
			Integer id = iter.next();
			L2Weapon item = _weapons.get(id);
			assert _allTemplates[id.intValue()] == null;
			_allTemplates[id.intValue()] = item;
		}
		
		// Insert etcItem item in Fast Look Up Table
		for (Iterator<Integer> iter = _etcItems.keySet().iterator(); iter.hasNext();)
		{
			Integer id = iter.next();
			L2EtcItem item = _etcItems.get(id);
			assert _allTemplates[id.intValue()] == null;
			_allTemplates[id.intValue()] = item;
		}
	}

	/**
	 * Returns the item corresponding to the item ID
	 * @param id : int designating the item
	 * @return L2Item
	 */
	public L2Item getTemplate(int id)
	{
		if (id > _allTemplates.length)
			return null;
		else
			return _allTemplates[id];
	}

	/**
	 * Create the L2ItemInstance corresponding to the Item Identifier and quantitiy add logs the activity.<BR><BR>
	 * 
	 * <B><U> Actions</U> :</B><BR><BR>
	 * <li>Create and Init the L2ItemInstance corresponding to the Item Identifier and quantity </li>
	 * <li>Add the L2ItemInstance object to _allObjects of L2world </li>
	 * <li>Logs Item creation according to log settings</li><BR><BR>
	 * 
	 * @param process : String Identifier of process triggering this action
     * @param itemId : int Item Identifier of the item to be created
     * @param count : int Quantity of items to be created for stackable items
	 * @param actor : L2PcInstance Player requesting the item creation
	 * @param reference : L2Object Object referencing current action like NPC selling item or previous item in transformation
     * @return L2ItemInstance corresponding to the new item
	 */
	public L2ItemInstance createItem(String process, int itemId, int count, L2PcInstance actor, L2Object reference)
	{
		// Create and Init the L2ItemInstance corresponding to the Item Identifier
		L2ItemInstance item = new L2ItemInstance(IdFactory.getInstance().getNextId(), itemId);
		if (Config.DEBUG) _log.fine("ItemTable: Item created  oid:" + item.getObjectId()+ " itemid:" + itemId);
		
		// Add the L2ItemInstance object to _allObjects of L2world
		L2World.getInstance().storeObject(item);

		// Set Item parameters
		if (item.isStackable() && count > 1) item.setCount(count);
		
		if (Config.LOG_ITEMS) 
		{
			LogRecord record = new LogRecord(Level.INFO, "CREATE:" + process);
			record.setLoggerName("item");
			record.setParameters(new Object[]{item, actor, reference});
			_logItems.log(record);
		}
        
		return item; 	
	}

	public L2ItemInstance createItem(String process, int itemId, int count, L2PcInstance actor)
	{
		return createItem(process, itemId, count, actor, null);
	}
	
	/**
	 * Returns a dummy (fr = factice) item.<BR><BR>
	 * <U><I>Concept :</I></U><BR>
	 * Dummy item is created by setting the ID of the object in the world at null value 
	 * @param itemId : int designating the item
	 * @return L2ItemInstance designating the dummy item created
	 */
	public L2ItemInstance createDummyItem(int itemId)
	{
		L2Item item = getTemplate(itemId);
		if (item == null)
			return null;
		L2ItemInstance temp = new L2ItemInstance(0, item);
		try
		{
			temp = new L2ItemInstance(0, itemId);
		} 
		catch (ArrayIndexOutOfBoundsException e)
		{
			// this can happen if the item templates were not initialized
		}
		
		if (temp.getItem() == null)
		{
			_log.warning("ItemTable: Item Template missing for Id: "+ itemId);
		}
		
		return temp; 	
	}

	/**
	 * Destroys the L2ItemInstance.<BR><BR>
	 * 
	 * <B><U> Actions</U> :</B><BR><BR>
	 * <li>Sets L2ItemInstance parameters to be unusable </li>
	 * <li>Removes the L2ItemInstance object to _allObjects of L2world </li>
	 * <li>Logs Item delettion according to log settings</li><BR><BR>
	 * 
	 * @param process : String Identifier of process triggering this action
     * @param itemId : int Item Identifier of the item to be created
	 * @param actor : L2PcInstance Player requesting the item destroy
	 * @param reference : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 */
	public void destroyItem(String process, L2ItemInstance item, L2PcInstance actor, L2Object reference)
	{
		synchronized (item)
		{
	    	item.setCount(0);
	        item.setOwnerId(0);
	        item.setLocation(ItemLocation.VOID);
	        item.setLastChange(L2ItemInstance.REMOVED);

	        L2World.getInstance().removeObject(item);
			IdFactory.getInstance().releaseId(item.getObjectId());

			if (Config.LOG_ITEMS) 
			{
				LogRecord record = new LogRecord(Level.INFO, "DELETE:" + process);
				record.setLoggerName("item");
				record.setParameters(new Object[]{item, actor, reference});
				_logItems.log(record);
			}

			// if it's a pet control item, delete the pet as well
			if (L2PetDataTable.isPetItem(item.getItemId()))
			{
				java.sql.Connection con = null;
				try
				{
					// Delete the pet in db
					con = L2DatabaseFactory.getInstance().getConnection();
					PreparedStatement statement = con.prepareStatement("DELETE FROM pets WHERE item_obj_id=?");
					statement.setInt(1, item.getObjectId());
					statement.execute();
					statement.close();
				}
				catch (Exception e)
				{
					_log.log(Level.WARNING, "could not delete pet objectid:", e);
				}
				finally
				{
					try { con.close(); } catch (Exception e) {}
				}
			}
		}
	}

	public void reload()
	{
		synchronized(_instance)
		{
			_instance = null;
			_instance = new ItemTable();
		}
	}
}
