package net.sf.l2j.gameserver.skills;


public class ConditionPlayerHp extends Condition {
	final int _hp;
	
	public ConditionPlayerHp(int hp) {
		_hp = hp;
	}
	
	public boolean testImpl(Env env) {
		return env._player.getCurrentHp()*100/env._player.getMaxHp() <= _hp;
	}
}
