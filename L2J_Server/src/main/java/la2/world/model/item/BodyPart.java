package la2.world.model.item;

public enum BodyPart {
	none(0x0000),
	feet(0x1000),
	chest(0x0400),
	wolf(0x020000),
	back(0x2000),
	rear(0x0002), 
	lear(0x0004),
	neck(0x0008),
	strider(0x080000),
	head(0x0040),
	hair(0x010000),
	gloves(0x0200),
	fullarmor(0x8000),
	rhand(0x0080),
	legs(0x0800),
	lrhand(0x4000),
	underwear(0x0001),
	lhand(0x0100),
	hatchling(0x040000),
	rfinger(0x0010), 
	lfinger(0x0020);
	
	public final int id;
	
	private BodyPart(int id) {
		this.id = id;
	}
	
	public static int mask(BodyPart[] parts) {
		int value = 0;
		for(BodyPart p : parts)
			value |= p.id;
		return value;
	}

	public static boolean or(BodyPart[] bodypart, int value) {
		for(BodyPart p : bodypart)
			if((p.id & value) != 0)
				return true;
		return false;
	}

}
