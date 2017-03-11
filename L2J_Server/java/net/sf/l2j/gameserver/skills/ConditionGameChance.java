package net.sf.l2j.gameserver.skills;

import net.sf.l2j.gameserver.lib.Rnd;

public class ConditionGameChance extends Condition {
    final int _chance;
    
    public ConditionGameChance(int chance) {
        _chance = chance;
    }
    
    public boolean testImpl(Env env) {
        return Rnd.get(100) < _chance;
    }
}
