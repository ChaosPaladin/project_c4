package net.sf.l2j.gameserver.model;

import la2.world.model.data.xml.XmlTeleport;

public class L2TeleportLocation {
	public final int id;
	public final int x;
	public final int y;
	public final int z;
	public final int price;

	public L2TeleportLocation(XmlTeleport teleport) {
		id = teleport.id;
		x = teleport.x;
		y = teleport.y;
		z = teleport.z;
		price = teleport.price;
	}
	
}