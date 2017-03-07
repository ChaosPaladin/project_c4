package la2.world.model.creature;

public enum PlayerRace {
	human(0),
	elf(1),
	dark_elf(2),
	orc(3),
	dward(4);
	
	public final int id;
	
	private PlayerRace(int id) {
		this.id = id;
	}
}
