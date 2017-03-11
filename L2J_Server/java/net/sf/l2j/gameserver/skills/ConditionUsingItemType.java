package net.sf.l2j.gameserver.skills;

import net.sf.l2j.gameserver.model.Inventory;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;

public final class ConditionUsingItemType extends Condition {
	final int _mask;
	
	public ConditionUsingItemType(int mask) {
		_mask = mask;
	}
	
	public boolean testImpl(Env env)
	{
		if (!(env._player instanceof L2PcInstance))
			return false;
		Inventory inv = ((L2PcInstance)env._player).getInventory();
		return (_mask & inv.getWearedMask()) != 0;
	}
}
