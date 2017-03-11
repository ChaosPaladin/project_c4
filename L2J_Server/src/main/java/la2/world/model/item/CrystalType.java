package la2.world.model.item;

public enum CrystalType {
	none(0),
	d(1),
	c(2),
	b(3),
	a(4),
	s(5);
	
	public final int id;
	
	private CrystalType(int id) {
		this.id = id;
	}
}
