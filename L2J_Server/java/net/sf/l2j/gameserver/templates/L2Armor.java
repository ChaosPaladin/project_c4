package net.sf.l2j.gameserver.templates;

import java.util.List;

import javolution.util.FastList;
import la2.world.model.data.xml.XmlArmor;
import la2.world.model.data.xml.XmlEntry;
import net.sf.l2j.gameserver.model.L2Character;
import net.sf.l2j.gameserver.model.L2ItemInstance;
import net.sf.l2j.gameserver.skills.Env;
import net.sf.l2j.gameserver.skills.Func;
import net.sf.l2j.gameserver.skills.FuncTemplate;
import net.sf.l2j.gameserver.templates.L2ArmorType;

public final class L2Armor extends L2Item {
	private final int avoidModifier;
	private final int pDef;
	private final int mDef;
	private final int mpBonus;
	//private final int _hpBonus;
	
	public L2Armor(final XmlEntry entry) {
		this((XmlArmor) entry);
	}
	
	public L2Armor(final XmlArmor data) {
		super(data);
		avoidModifier = data.avoidModify;
		pDef = data.mDef;
		mDef = data.pDef;
		mpBonus = data.mpBonus;
	}
	
    /**
     * Constructor for Armor.<BR><BR>
     * <U><I>Variables filled :</I></U><BR>
     * <LI>_avoidModifier</LI>
     * <LI>_pDef & _mDef</LI>
     * <LI>_mpBonus & _hpBonus</LI>
     * @param type : L2ArmorType designating the type of armor
     * @param set : StatsSet designating the set of couples (key,value) caracterizing the armor
     * @see L2Item constructor
     */
	public L2Armor(L2ArmorType type, StatsSet set)
	{
		super(type, set);
		avoidModifier = set.getInteger("avoid_modify");
		pDef          = set.getInteger("p_def");
		mDef          = set.getInteger("m_def");
		mpBonus       = set.getInteger("mp_bonus", 0);
		//_hpBonus       = set.getInteger("hp_bonus", 0);
	}
	
	/**
	 * Returns the type of the armor.
	 * @return L2ArmorType
	 */
	public L2ArmorType getItemType()
	{
		return (L2ArmorType)super._type;
	}
	
	/**
	 * Returns the ID of the item after applying the mask.
	 * @return int : ID of the item
	 */
	public final int getItemMask()
	{
		return getItemType().mask();
	}
	
	/**
	 * Returns the magical defense of the armor
	 * @return int : value of the magic defense
	 */
	public final int getMDef()
	{
		return mDef;
	}
	
	/**
	 * Returns the physical defense of the armor
	 * @return int : value of the physical defense
	 */
	public final int getPDef()
	{
		return pDef;
	}
	
	/**
	 * Returns avoid modifier given by the armor
	 * @return int : avoid modifier
	 */
	public final int getAvoidModifier()
	{
		return avoidModifier;
	}
	
	/**
	 * Returns magical bonus given by the armor
	 * @return int : value of the magical bonus
	 */
	public final int getMpBonus()
	{
		return mpBonus;
	}
	
	/**
	 * Returns physical bonus given by the armor
	 * @return int : value of the physical bonus
	 */
	//public final int getHpBonus()
	//{
	//	return _hpBonus;
	//}

	/**
	 * Returns array of Func objects containing the list of functions used by the armor 
	 * @param instance : L2ItemInstance pointing out the armor
	 * @param player : L2Character pointing out the player
	 * @return Func[] : array of functions
	 */
	public Func[] getStatFuncs(L2ItemInstance instance, L2Character player)
    {
    	List<Func> funcs = new FastList<Func>();
    	if (_funcTemplates != null)
    	{
    		for (FuncTemplate t : _funcTemplates) {
		    	Env env = new Env();
		    	env._player = player;
		    	env._item = instance;
		    	Func f = t.getFunc(env, instance);
		    	if (f != null)
			    	funcs.add(f);
    		}
    	}
    	return funcs.toArray(new Func[funcs.size()]);
    }
}
