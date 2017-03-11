package la2.world.model;

import la2.world.model.creature.PlayerClass;
import la2.world.model.item.ArmorType;
import la2.world.model.item.EtcItemType;
import la2.world.model.item.WeaponType;
import net.sf.l2j.gameserver.model.base.ClassId;
import net.sf.l2j.gameserver.templates.L2ArmorType;
import net.sf.l2j.gameserver.templates.L2EtcItemType;
import net.sf.l2j.gameserver.templates.L2WeaponType;

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
	
	
	public static L2ArmorType map(ArmorType value) {
		for(L2ArmorType type : L2ArmorType.values())
			if(type._id == value.id)
				return type;
		return null;
	}
	
	public static ArmorType map(L2ArmorType value) {
		for(ArmorType type : ArmorType.values())
			if(type.id == value._id)
				return type;
		return null;
	}

	public static L2WeaponType map(WeaponType value) {
		for(L2WeaponType type : L2WeaponType.values())
			if(type._id == value.id)
				return type;
		return null;
	}

	public static L2EtcItemType map(EtcItemType value) {
		for(L2EtcItemType type : L2EtcItemType.values())
			if(type._id == value.id)
				return type;
		return null;
	}
}
