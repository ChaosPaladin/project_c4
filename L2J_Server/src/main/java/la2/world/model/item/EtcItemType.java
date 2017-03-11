package la2.world.model.item;

public enum EtcItemType {
	arrow(0),
	material(1),
	pet_collar(2),
	potion(3),
	recipe(4),
	scroll(5),
	quest(6),
	none(8),
	spellbook(9),
	seed(10),
	shot(11),
	race_ticket,
	lure(none.id),
	lotto,
	dye,
	castle_guard(scroll.id),
	harvest,
	ch3type2;
	
	public final int id;
	
	private EtcItemType(int id) {
		this.id = id;
	}
	
	private EtcItemType() {
		this(-1);
	}
}
