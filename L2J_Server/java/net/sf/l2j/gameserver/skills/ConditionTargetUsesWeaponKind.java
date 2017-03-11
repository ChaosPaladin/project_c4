package net.sf.l2j.gameserver.skills;

import net.sf.l2j.gameserver.templates.L2Weapon;

public class ConditionTargetUsesWeaponKind extends Condition  {
	final int _weaponMask;
	
	public ConditionTargetUsesWeaponKind(int weaponMask) {
		_weaponMask = weaponMask;
	}
	
	public boolean testImpl(Env env) {
		
		if (env._target == null)
			return false;
		
		L2Weapon item = env._target.getActiveWeaponItem();

		if(item == null)
			return false;

		return (item.getItemType().mask() & _weaponMask) != 0;
	}
}
