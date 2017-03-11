package net.sf.l2j.gameserver.skills;

import net.sf.l2j.gameserver.model.Inventory;
import net.sf.l2j.gameserver.model.L2ItemInstance;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;


public final class ConditionSlotItemId extends ConditionInventory {

	final int _itemId;
	final int _enchantLevel;
	
	public ConditionSlotItemId(int slot, int itemId, int enchantLevel) {
		super(slot);
		_itemId = itemId;
		_enchantLevel = enchantLevel;
	}
	
	public boolean testImpl(Env env)
	{
		if (!(env._player instanceof L2PcInstance))
			return false;
		Inventory inv = ((L2PcInstance)env._player).getInventory();
		L2ItemInstance item = inv.getPaperdollItem(_slot);
		if (item == null)
			return _itemId == 0;
		return item.getItemId() == _itemId && item.getEnchantLevel() >= _enchantLevel;
	}
}
