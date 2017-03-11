package net.sf.l2j.gameserver.templates;

import java.util.List;

import javolution.util.FastList;
import la2.world.model.Utils;
import la2.world.model.data.xml.XmlArmor;
import la2.world.model.data.xml.XmlEtcItem;
import la2.world.model.data.xml.XmlWeapon;
import la2.world.model.data.xml.XmlItem.xmladd;
import la2.world.model.data.xml.XmlItem.xmland;
import la2.world.model.data.xml.XmlItem.xmlcondition;
import la2.world.model.data.xml.XmlItem.xmldiv;
import la2.world.model.data.xml.XmlItem.xmlenchant;
import la2.world.model.data.xml.XmlItem.xmlfor;
import la2.world.model.data.xml.XmlItem.xmlgame;
import la2.world.model.data.xml.XmlItem.xmlmul;
import la2.world.model.data.xml.XmlItem.xmlnot;
import la2.world.model.data.xml.XmlItem.xmlor;
import la2.world.model.data.xml.XmlItem.xmlplayer;
import la2.world.model.data.xml.XmlItem.xmlset;
import la2.world.model.data.xml.XmlItem.xmlskill;
import la2.world.model.data.xml.XmlItem.xmlstat;
import la2.world.model.data.xml.XmlItem.xmlsub;
import la2.world.model.data.xml.XmlItem.xmltarget;
import la2.world.model.data.xml.XmlItem.xmlusing;
import la2.world.model.item.ArmorType;
import la2.world.model.item.BodyPart;
import la2.world.model.item.WeaponType;
import net.sf.l2j.Config;
import net.sf.l2j.gameserver.SkillTable;
import net.sf.l2j.gameserver.model.L2Character;
import net.sf.l2j.gameserver.model.L2Effect;
import net.sf.l2j.gameserver.model.L2ItemInstance;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.skills.Condition;
import net.sf.l2j.gameserver.skills.ConditionGameChance;
import net.sf.l2j.gameserver.skills.ConditionLogicAnd;
import net.sf.l2j.gameserver.skills.ConditionLogicNot;
import net.sf.l2j.gameserver.skills.ConditionLogicOr;
import net.sf.l2j.gameserver.skills.ConditionPlayerHp;
import net.sf.l2j.gameserver.skills.ConditionSlotItemId;
import net.sf.l2j.gameserver.skills.ConditionTargetUsesWeaponKind;
import net.sf.l2j.gameserver.skills.ConditionUsingItemType;
import net.sf.l2j.gameserver.skills.EffectTemplate;
import net.sf.l2j.gameserver.skills.Env;
import net.sf.l2j.gameserver.skills.Func;
import net.sf.l2j.gameserver.skills.FuncTemplate;
import net.sf.l2j.gameserver.skills.LambdaConst;
import net.sf.l2j.gameserver.skills.Stats;

/**
 * This class contains all informations concerning the item (weapon, armor, etc).<BR>
 * Mother class of :
 * <LI>L2Armor</LI>
 * <LI>L2EtcItem</LI>
 * <LI>L2Weapon</LI> 
 * @version $Revision: 1.7.2.2.2.5 $ $Date: 2005/04/06 18:25:18 $
 */
public abstract class L2Item
{
	public static final int TYPE1_WEAPON_RING_EARRING_NECKLACE = 0;
	public static final int TYPE1_SHIELD_ARMOR = 1;
	public static final int TYPE1_ITEM_QUESTITEM_ADENA = 4;

	public static final int TYPE2_WEAPON = 0;
	public static final int TYPE2_SHIELD_ARMOR = 1;
	public static final int TYPE2_ACCESSORY = 2;
	public static final int TYPE2_QUEST = 3;
	public static final int TYPE2_MONEY = 4;
    public static final int TYPE2_OTHER = 5;
    public static final int TYPE2_PET_WOLF = 6;
    public static final int TYPE2_PET_HATCHLING = 7;
    public static final int TYPE2_PET_STRIDER = 8;
	
	
	public static final int SLOT_NONE = 0x0000;
	public static final int SLOT_UNDERWEAR = 0x0001;
	public static final int SLOT_R_EAR = 0x0002;
	public static final int SLOT_L_EAR = 0x0004;
	public static final int SLOT_NECK = 0x0008;
	public static final int SLOT_R_FINGER = 0x0010;
	public static final int SLOT_L_FINGER = 0x0020;
	public static final int SLOT_HEAD = 0x0040;
	public static final int SLOT_R_HAND = 0x0080;
	public static final int SLOT_L_HAND = 0x0100;
	public static final int SLOT_GLOVES = 0x0200;
	public static final int SLOT_CHEST = 0x0400;
	public static final int SLOT_LEGS = 0x0800;
	public static final int SLOT_FEET = 0x1000;
	public static final int SLOT_BACK = 0x2000;
	public static final int SLOT_LR_HAND = 0x4000;
	public static final int SLOT_FULL_ARMOR = 0x8000;
    public static final int SLOT_HAIR = 0x010000;
    public static final int SLOT_WOLF = 0x020000;
    public static final int SLOT_HATCHLING = 0x040000;
    public static final int SLOT_STRIDER = 0x080000;
	
	public static final int MATERIAL_STEEL = 0x00; // ??
	public static final int MATERIAL_FINE_STEEL = 0x01; // ??
	public static final int MATERIAL_BLOOD_STEEL = 0x02; // ??
	public static final int MATERIAL_BRONZE = 0x03; // ??
	public static final int MATERIAL_SILVER = 0x04; // ??
	public static final int MATERIAL_GOLD = 0x05; // ??
	public static final int MATERIAL_MITHRIL = 0x06; // ??
	public static final int MATERIAL_ORIHARUKON = 0x07; // ??
	public static final int MATERIAL_PAPER = 0x08; // ??
	public static final int MATERIAL_WOOD = 0x09; // ??
	public static final int MATERIAL_CLOTH = 0x0a; // ??
	public static final int MATERIAL_LEATHER = 0x0b; // ??
	public static final int MATERIAL_BONE = 0x0c; // ??
	public static final int MATERIAL_HORN = 0x0d; // ??
	public static final int MATERIAL_DAMASCUS = 0x0e; // ??
	public static final int MATERIAL_ADAMANTAITE = 0x0f; // ??
	public static final int MATERIAL_CHRYSOLITE = 0x10; // ??
	public static final int MATERIAL_CRYSTAL = 0x11; // ??
	public static final int MATERIAL_LIQUID = 0x12; // ??
	public static final int MATERIAL_SCALE_OF_DRAGON = 0x13; // ??
	public static final int MATERIAL_DYESTUFF = 0x14; // ??
	public static final int MATERIAL_COBWEB = 0x15; // ??
    public static final int MATERIAL_SEED = 0x15; // ??

	public static final int CRYSTAL_NONE = 0x00; // ??
	public static final int CRYSTAL_D = 0x01; // ??
	public static final int CRYSTAL_C = 0x02; // ??
	public static final int CRYSTAL_B = 0x03; // ??
	public static final int CRYSTAL_A = 0x04; // ??
	public static final int CRYSTAL_S = 0x05; // ??
	
    public static final int[] crystalItemId = {0, 1458, 1459, 1460, 1461, 1462};
    public static final int[] crystalEnchantBonusArmor = {0, 11, 6, 11, 19, 25};
    public static final int[] crystalEnchantBonusWeapon = {0, 90, 45, 67, 144, 250};

	private final int _itemId;
	private final String _name;
	private final int _type1;	// needed for item list (inventory)
	private final int _type2;	// different lists for armor, weapon, etc
	private final int _weight;
	private final boolean _crystallizable;
	private final boolean _stackable;
	private final int _materialType;
	private final int _crystalType; // default to none-grade 
	private final int _durability;
	private final int _bodyPart;
	private final int _referencePrice;
	private final int _crystalCount;
	private final boolean _sellable;
	
	protected final Enum _type;
	
	protected FuncTemplate[] _funcTemplates;
	protected EffectTemplate[] _effectTemplates;
    protected L2Skill[] _skills;
    
    private static final Func[] _emptyFunctionSet = new Func[0];
    protected static final L2Effect[] _emptyEffectSet = new L2Effect[0];
	
    @SuppressWarnings("incomplete-switch")
	protected L2Item(final XmlEtcItem data) {
    	_itemId = data.id;
    	_name = data.name;
    	_weight = data.weight;
    	_crystallizable = data.crystallizable;
    	_materialType = data.material.id;
    	_crystalType = data.crystalType.id;
    	_durability = data.durability;
    	_referencePrice = data.price;
    	_crystalCount = data.crystalCount;
    	_sellable = data.sellable;	
		
		Enum<?> type = Utils.map(data.itemType);
		int bodypart = 0;
		int type1 = TYPE1_ITEM_QUESTITEM_ADENA;
		int type2 = TYPE2_OTHER;
		switch(data.itemType) {
		case lure:
		case arrow: bodypart = SLOT_L_HAND; break;
		case quest: type2 = TYPE2_QUEST; break;
		}
		
		if(type == null)
			type = L2EtcItemType.OTHER;
		switch(data.consumeType) {
		case stackable:
			_stackable = true;
			break;
		case asset:
			type = L2EtcItemType.MONEY;
			type2 = TYPE2_MONEY;
	    	_stackable = true;
			break;
		case normal:
		default:
			 _stackable = false;
			break;
		}
		
		_bodyPart = bodypart;
		_type = Utils.map(data.itemType);
		_type1 = type1;
		_type2 = type2;
    }
    
    protected L2Item(final XmlWeapon data) {
    	_type = Utils.map(data.weaponType);
    	_itemId = data.id;
    	_name = data.name;
    	_weight = data.weight;
    	_crystallizable = data.crystallizable;
    	_stackable = false;
    	_materialType = data.material.id;
    	_crystalType = data.crystalType.id;
    	_durability = data.durability;
    	_referencePrice = data.price;
    	_crystalCount = data.crystalCount;
    	_sellable = data.sellable;
    	if(data.weaponType == WeaponType.pet) {
    		_type1 = L2Item.TYPE1_WEAPON_RING_EARRING_NECKLACE;
    		switch(data.bodypart.id) {
    		case SLOT_WOLF: 
    			_type2 = TYPE2_PET_WOLF;
    			break;
    		case SLOT_HATCHLING:
    			_type2 = TYPE2_PET_HATCHLING;
    			break;
    		default:
    			_type2 = TYPE2_PET_STRIDER;
    		}
    		_bodyPart = BodyPart.rhand.id;
    	} else {
        	_bodyPart = data.bodypart.id;
        	if(data.weaponType == WeaponType.none) {
        		_type1 = TYPE1_SHIELD_ARMOR;
        		_type2 = TYPE2_SHIELD_ARMOR;
        	} else {
        		_type1 = TYPE1_WEAPON_RING_EARRING_NECKLACE;
        		_type2 = TYPE2_WEAPON;
        	}
    	}
    	stats(data.list);
    }
    
    protected L2Item(final XmlArmor data) {
    	_type = Utils.map(data.armorType);
    	_itemId = data.id;
    	_name = data.name;
    	_weight = data.weight;
    	_crystallizable = data.crystallizable;
    	_stackable = false;
    	_materialType = data.material.id;
    	if(data.crystalType == null)
    		System.out.println(_itemId);
    	_crystalType = data.crystalType == null ? CRYSTAL_NONE : data.crystalType.id;
    	_durability = data.durability;
    	_referencePrice = data.price;
    	_crystalCount = data.crystalCount;
    	_sellable = data.sellable;
    	
    	if(data.armorType == ArmorType.pet) {
    		_type1 = TYPE1_SHIELD_ARMOR;
    		switch(BodyPart.mask(data.bodypart)) {
    		case SLOT_WOLF: 
    			_type2 = TYPE2_PET_WOLF;
    			break;
    		case SLOT_HATCHLING:
    			_type2 = TYPE2_PET_HATCHLING;
    			break;
    		default:
    			_type2 = TYPE2_PET_STRIDER;
    		}
    		_bodyPart = SLOT_CHEST;
    	} else {
        	_bodyPart = BodyPart.mask(data.bodypart);
        	if((_bodyPart & (SLOT_BACK | SLOT_HAIR | SLOT_L_EAR | SLOT_L_FINGER)) != 0) {
        		_type1 = TYPE1_WEAPON_RING_EARRING_NECKLACE;
        		_type2 = TYPE2_ACCESSORY;
        	} else {
        		_type1 = TYPE1_SHIELD_ARMOR;
        		_type2 = TYPE2_SHIELD_ARMOR;
        	}
    	}
    	stats(data.list);
    }
    
    private final void stats(List<xmlfor> stats) {
    	stats.forEach(forItem -> {
    		forItem.list.forEach(mod -> {
    			if(mod instanceof xmlstat) {
    				xmlstat stat = (xmlstat) mod;
    				String name = "";
    				if(mod instanceof xmladd) name = "Add";
    				else if(mod instanceof xmlsub) name = "Sub";
    				else if(mod instanceof xmlmul) name = "Mul";
    				else if(mod instanceof xmldiv) name = "Div";
    				else if(mod instanceof xmlset) name = "Set";
    				else if(mod instanceof xmlenchant) name = "Enchant";
    				else throw new RuntimeException();
    				attach(new FuncTemplate(null, condition(stat.condition), name, Stats.valueOfXml(stat.stat), parseInt(stat.order), new LambdaConst(Double.parseDouble(stat.val))));
    			} else {
    				xmlskill sinfo = (xmlskill) mod;
					L2Skill skill = SkillTable.getInstance().getInfo(sinfo.id, sinfo.level);
    				if(sinfo.chance > -1 && skill != null) {
    					skill.attach(new ConditionGameChance(sinfo.chance));
    				}
    				if(sinfo.onCrit) ((L2Weapon) this).attachOnCrit(skill);
    				else if(sinfo.onCast) ((L2Weapon) this).attachOnCrit(skill);
    			}
    		});
    	});
    }
    
    private int parseInt(String value) {
        int radix = 10;
        if (value.length() > 2 && value.substring(0, 2).equalsIgnoreCase("0x"))
        {
            value = value.substring(2);
            radix = 16;
        }
        return Integer.valueOf(value, radix);
    }
    
    private final Condition condition(xmlcondition cond) {
    	if(cond == null)
    		return null;
    	if(cond instanceof xmland) return and(cond.condition);
    	else if(cond instanceof xmlor) return or(cond.condition);
    	else if(cond instanceof xmlnot) return not(cond.condition);
    	else if(cond instanceof xmlplayer) return new ConditionPlayerHp(((xmlplayer)cond).hp);
    	else if(cond instanceof xmltarget) {
    		int mask = 0;
    		for(String name : ((xmltarget) cond).using.split(",")) {
    			for(L2WeaponType wt : L2WeaponType.values())
    				if(wt.toString().equals(name)) {
    					mask |= wt.mask();
    					break;
    				}
    			for(L2ArmorType at : L2ArmorType.values())
    				if(at.toString().equals(name)) {
    					mask |= at.mask();
    					break;
    				}
    		}
    		return new ConditionTargetUsesWeaponKind(mask);
    	}
    	else if(cond instanceof xmlusing) {
    		xmlusing using = (xmlusing) cond;
    		if(using.kind != null) {
    			int mask = 0;
    			for(String item : using.kind.split(",")) {
                    for (L2WeaponType wt : L2WeaponType.values())
                    	if (wt.toString().equals(item)) {
                            mask |= wt.mask();
                            break;
                        }
                    for (L2ArmorType at : L2ArmorType.values())
                        if (at.toString().equals(item)) {
                            mask |= at.mask();
                            break;
                        }
                }
                return new ConditionUsingItemType(mask);
    		} else {
                String[] split = using.slotitem.split(";");
    			int id = Integer.parseInt(split[0].trim());
                int slot = Integer.parseInt(split[1].trim());
                int enchant = 0;
                if(split.length == 3)
                	enchant = Integer.parseInt(split[2]);
    			return new ConditionSlotItemId(slot, id, enchant);
    		}
    	} 
    	else if(cond instanceof xmlgame) return new ConditionGameChance(((xmlgame)cond).chance);
    	else throw new RuntimeException("item " + _itemId);
    }
    
    private final Condition and(List<xmlcondition> list) {
    	final ConditionLogicAnd stmt = new ConditionLogicAnd();
    	for(xmlcondition cond : list)
    		stmt.add(condition(cond));
    	return stmt;
    }
    
    private final Condition or(List<xmlcondition> list) {
    	final ConditionLogicOr stmt = new ConditionLogicOr();
    	for(xmlcondition cond : list)
    		stmt.add(condition(cond));
    	return stmt;
    }
  
    private final Condition not(List<xmlcondition> list) {
    	return new ConditionLogicNot(condition(list.get(0)));
    }
    
	/**
	 * Constructor of the L2Item that fill class variables.<BR><BR>
	 * <U><I>Variables filled :</I></U><BR>
	 * <LI>type</LI>
	 * <LI>_itemId</LI>
	 * <LI>_name</LI>
	 * <LI>_type1 & _type2</LI>
	 * <LI>_weight</LI>
	 * <LI>_crystallizable</LI>
	 * <LI>_stackable</LI>
	 * <LI>_materialType & _crystalType & _crystlaCount</LI>
	 * <LI>_durability</LI>
	 * <LI>_bodypart</LI>
	 * <LI>_referencePrice</LI>
	 * <LI>_sellable</LI>
	 * @param type : Enum designating the type of the item
	 * @param set : StatsSet corresponding to a set of couples (key,value) for description of the item
	 */
	protected L2Item(Enum type, StatsSet set)
	{
		_type = type;
		_itemId = set.getInteger("item_id");
		_name   = set.getString("name");
		_type1  = set.getInteger("type1");	// needed for item list (inventory)
		_type2  = set.getInteger("type2");	// different lists for armor, weapon, etc
		_weight = set.getInteger("weight");
		_crystallizable = set.getBool("crystallizable");
		_stackable      = set.getBool("stackable", false);
		_materialType   = set.getInteger("material");
		_crystalType    = set.getInteger("crystal_type", CRYSTAL_NONE); // default to none-grade 
		_durability     = set.getInteger("durability");
		_bodyPart       = set.getInteger("bodypart");
		_referencePrice = set.getInteger("price");
		_crystalCount   = set.getInteger("crystal_count", 0);
		_sellable       = set.getBool("sellable", true);
	}
	
	/**
	 * Returns the itemType.
	 * @return Enum
	 */
	public Enum getItemType()
	{
		return _type;
	}
	
	/**
	 * Returns the durability of th item
	 * @return int
	 */
	public final int getDurability()
	{
		return _durability;
	}

	/**
	 * Returns the ID of the iden
	 * @return int
	 */
	public final int getItemId()
	{
		return _itemId;
	}

	public abstract int getItemMask();
	
	/**
	 * Return the type of material of the item
	 * @return int
	 */
	public final int getMaterialType()
	{
		return _materialType;
	}

	/**
	 * Returns the type 2 of the item
	 * @return int
	 */
	public final int getType2()
	{
		return _type2;
	}

	/**
	 * Returns the weight of the item
	 * @return int
	 */
	public final int getWeight()
	{
		return _weight;
	}

	/**
	 * Returns if the item is crystallizable
	 * @return boolean
	 */
	public final boolean isCrystallizable()
	{
		return _crystallizable;
	}

	/**
	 * Return the type of crystal if item is crystallizable
	 * @return int
	 */
	public final int getCrystalType()
	{
		return _crystalType;
	}

    /**
     * Return the type of crystal if item is crystallizable
     * @return int
     */
    public final int getCrystalItemId()
    {
        return crystalItemId[_crystalType];
    }
    
	/**
	 * Returns the grade of the item.<BR><BR>
	 * <U><I>Concept :</I></U><BR>
	 * In fact, this fucntion returns the type of crystal of the item.
	 * @return int
	 */
    public final int getItemGrade()
    {
        return getCrystalType();
    }
    
    /**
     * Returns the quantity of crystals for crystallization
     * @return int
     */
	public final int getCrystalCount()
    {
		return _crystalCount;
	}

    /**
     * Returns the quantity of crystals for crystallization on specific enchant level
     * @return int
     */
    public final int getCrystalCount(int enchantLevel)
    {
        if (enchantLevel > 3) switch(_type2)
        {
            case TYPE2_SHIELD_ARMOR: case TYPE2_ACCESSORY: return _crystalCount + crystalEnchantBonusArmor[getCrystalType()] * (3*enchantLevel -6);
            case TYPE2_WEAPON: return _crystalCount + crystalEnchantBonusWeapon[getCrystalType()] * (2*enchantLevel -3);
            default: return _crystalCount;
        }
        else if (enchantLevel > 0) switch(_type2)
        {
            case TYPE2_SHIELD_ARMOR: case TYPE2_ACCESSORY: return _crystalCount + crystalEnchantBonusArmor[getCrystalType()] * enchantLevel;
            case TYPE2_WEAPON: return _crystalCount + crystalEnchantBonusWeapon[getCrystalType()] * enchantLevel;
            default: return _crystalCount;
        }
        else return _crystalCount;
    }

	/**
	 * Returns the name of the item
	 * @return String
	 */
	public final String getName()
	{
		return _name;
	}

	/**
	 * Return the part of the body used with the item.
	 * @return int
	 */
	public final int getBodyPart()
	{
		return _bodyPart;
	}

	/**
	 * Returns the type 1 of the item
	 * @return int
	 */
	public final int getType1()
	{
		return _type1;
	}

	/**
	 * Returns if the item is stackable
	 * @return boolean
	 */
	public final boolean isStackable()
	{
		return _stackable;
	}

    /**
     * Returns if the item is consumable
     * @return boolean
     */
    public boolean isConsumable()
    {
        return false;
    }

	/**
	 * Returns the price of reference of the item
	 * @return int
	 */
	public final int getReferencePrice()
	{
        return (isConsumable() ? (int)(_referencePrice * Config.RATE_CONSUMABLE_COST) : _referencePrice);
	}
	
	/**
	 * Returns if the item can be sold
	 * @return boolean
	 */
	public final boolean isSellable()
	{
		return _sellable;
	}
    
    /**
     * Returns if item is for hatchling
     * @return boolean
     */
    public boolean isForHatchling()
    {
        return (_type2 == TYPE2_PET_HATCHLING);
    }

    /**
     * Returns if item is for strider
     * @return boolean
     */
    public boolean isForStrider()
    {
        return (_type2 == TYPE2_PET_STRIDER);
    }
	
    /**
     * Returns if item is for wolf
     * @return boolean
     */
    public boolean isForWolf()
    {
        return (_type2 == TYPE2_PET_WOLF);
    }

    /**
	 * Returns array of Func objects containing the list of functions used by the item 
	 * @param instance : L2ItemInstance pointing out the item
	 * @param player : L2Character pointing out the player
	 * @return Func[] : array of functions
	 */
    public Func[] getStatFuncs(L2ItemInstance instance, L2Character player)
    {
		if (_funcTemplates == null)
			return _emptyFunctionSet;
    	List<Func> funcs = new FastList<Func>();
		for (FuncTemplate t : _funcTemplates) {
	    	Env env = new Env();
	    	env._player = player;
	    	env._target = player;
	    	env._item = instance;
	    	Func f = t.getFunc(env, this); // skill is owner
	    	if (f != null)
	    		funcs.add(f);
		}
    	if (funcs.size() == 0)
    		return _emptyFunctionSet;
    	return funcs.toArray(new Func[funcs.size()]);
    }
    
    /**
     * Returns the effects associated with the item.
     * @param instance : L2ItemInstance pointing out the item
     * @param player : L2Character pointing out the player
     * @return L2Effect[] : array of effects generated by the item
     */
    public L2Effect[] getEffects(L2ItemInstance instance, L2Character player)
    {
		if (_effectTemplates == null)
			return _emptyEffectSet;
    	List<L2Effect> effects = new FastList<L2Effect>();
		for (EffectTemplate et : _effectTemplates) {
	    	Env env = new Env();
	    	env._player = player;
	    	env._target = player;
	    	env._item = instance;
	    	L2Effect e = et.getEffect(env);
	    	if (e != null)
	    		effects.add(e);
		}
    	if (effects.size() == 0)
    		return _emptyEffectSet;
    	return effects.toArray(new L2Effect[effects.size()]);
    }
    
    /**
     * Returns effects of skills associated with the item.
     * @param caster : L2Character pointing out the caster
     * @param target : L2Character pointing out the target
     * @return L2Effect[] : array of effects generated by the skill
     */
    public L2Effect[] getSkillEffects(L2Character caster, L2Character target)
    {
        if (_skills == null)
            return _emptyEffectSet;
        List<L2Effect> effects = new FastList<L2Effect>();

        for (L2Skill skill : _skills)
        {
            if (!skill.checkCondition(caster)) 
                continue; // Skill condition not met
            
            if (target.getEffect(skill.getId()) != null)
                target.removeEffect(target.getEffect(skill.getId()));
            for (L2Effect e:skill.getEffects(caster, target))
                effects.add(e);
        }
        if (effects.size() == 0)
            return _emptyEffectSet;
        return effects.toArray(new L2Effect[effects.size()]);
    }
    
    
    /**
     * Add the FuncTemplate f to the list of functions used with the item
     * @param f : FuncTemplate to add
     */
    public void attach(FuncTemplate f)
    {
    	// If _functTemplates is empty, create it and add the FuncTemplate f in it
    	if (_funcTemplates == null)
    	{
    		_funcTemplates = new FuncTemplate[]{f};
    	}
    	else
    	{
    		int len = _funcTemplates.length;
    		FuncTemplate[] tmp = new FuncTemplate[len+1];
    		// Definition : arraycopy(array source, begins copy at this position of source, array destination, begins copy at this position in dest,
    		//						  number of components to be copied)
    		System.arraycopy(_funcTemplates, 0, tmp, 0, len);
    		tmp[len] = f;
    		_funcTemplates = tmp;
    	}
    }

    /**
     * Add the EffectTemplate effect to the list of effects generated by the item
     * @param effect : EffectTemplate
     */
    public void attach(EffectTemplate effect)
    {
    	if (_effectTemplates == null)
    	{
    		_effectTemplates = new EffectTemplate[]{effect};
    	}
    	else
    	{
    		int len = _effectTemplates.length;
    		EffectTemplate[] tmp = new EffectTemplate[len+1];
    		// Definition : arraycopy(array source, begins copy at this position of source, array destination, begins copy at this position in dest,
    		//						  number of components to be copied)
    		System.arraycopy(_effectTemplates, 0, tmp, 0, len);
    		tmp[len] = effect;
    		_effectTemplates = tmp;
    	}
    }

    /**
     * Add the L2Skill skill to the list of skills generated by the item
     * @param skill : L2Skill
     */
    public void attach(L2Skill skill)
    {
        if (_skills == null)
        {
            _skills = new L2Skill[]{skill};
        }
        else
        {
            int len = _skills.length;
            L2Skill[] tmp = new L2Skill[len+1];
            // Definition : arraycopy(array source, begins copy at this position of source, array destination, begins copy at this position in dest,
            //                        number of components to be copied)
            System.arraycopy(_skills, 0, tmp, 0, len);
            tmp[len] = skill;
            _skills = tmp;
        }
    }

    /**
     * Returns the name of the item
     * @return String
     */
	public String toString()
	{
		return _name;
	}
}
