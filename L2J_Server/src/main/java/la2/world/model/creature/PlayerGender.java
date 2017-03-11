package la2.world.model.creature;

public enum PlayerGender {
	female(0),
	male(1);
	
	public final int id;
	
	private PlayerGender(int id) {
		this.id = id;
	}
}
