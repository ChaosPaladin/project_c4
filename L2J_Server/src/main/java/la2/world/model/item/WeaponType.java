package la2.world.model.item;

public enum WeaponType {
	dagger(4),
	blunt(3),
	rod(13),
	bow(5),
	none(1),
	fist(8),
	pole(6),
	bigsword(11),
	sword(2),
	dual(9),
	etc(7),
	dualfist(10),
	pet(12);
	public final int id;
	
	private WeaponType(int id) {
		this.id = id;
	}

}
