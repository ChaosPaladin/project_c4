package la2.world.model.item;

public enum Material {
	steel(0x00),
	fine_steel(0x01),
	blood_steel(0x02),
	bronze(0x03),
	silver(0x04),
	gold(0x05),
	mithril(0x06),
	oriharukon(0x07),
	paper(0x08),
	wood(0x09),
	cloth(0x0a),
	leather(0x0b),
	bone(0x0c),
	horn(0x0d),
	damascus(0x0e),
	adamantaite(0x0f),
	chrysolite(0x10),
	crystal(0x11),
	liquid(0x12),
	scale_of_dragon(0x13),
	dyestuff(0x14),
	cobweb(0x15),
	cotton(0x01);//TODO correct id
	
	public final int id;
	
	private Material(int id) {
		this.id = id;
	}
}
