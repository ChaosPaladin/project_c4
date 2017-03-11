package la2.world.model.item;

public enum ArmorType {
	none(1),
	light(2),
	heavy(3),
	magic(4),
	pet(5);
	
	public final int id;
	
	private ArmorType(int id) {
		this.id = id;
	}
}
