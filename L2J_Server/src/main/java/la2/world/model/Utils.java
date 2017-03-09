package la2.world.model;

import la2.world.model.creature.PlayerClass;
import net.sf.l2j.gameserver.model.base.ClassId;

public class Utils {

	/*
	 * Maping old enum ClassId to new PlayerClass
	 */
	public static PlayerClass map(ClassId value) {
		return PlayerClass.find(value.getId());
	}
	
	/*
	 * Maping new enum PlayerClass to old ClassId
	 */
	public static ClassId map(PlayerClass value) {
		return ClassId.values()[value.id];
	}
	
}
