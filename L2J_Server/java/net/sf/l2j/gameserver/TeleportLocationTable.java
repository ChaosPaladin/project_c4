package net.sf.l2j.gameserver;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import la2.world.model.data.xml.XmlEntry;
import la2.world.model.data.xml.XmlLoader;
import la2.world.model.data.xml.XmlTeleport;
import net.sf.l2j.gameserver.model.L2TeleportLocation;

//FIX create teleport list for npc, player can inject packet and replate id when teleporting, and teleport to not allowed point.
public class TeleportLocationTable {
	private static Logger _log = Logger.getLogger(TeleportLocationTable.class.getName());
	
	private static TeleportLocationTable instance;
	
	private Map<Integer, L2TeleportLocation> teleports;
	
	public static TeleportLocationTable getInstance() {
		if (instance == null)
			instance = new TeleportLocationTable();
		return instance;
	}
	
	private TeleportLocationTable() {
	    load();
	}
	
	//FIX not synchronized realod
	public void reload() {
		load();
	}
	
	private void load() {
		teleports = new TreeMap<>();
		XmlLoader.load("teleport.xml").list.stream().
			filter(this::filter).
			map(this::map).
			map(L2TeleportLocation::of).
			forEach(this::add);
		_log.config("TeleportLocationTable: Loaded " + teleports.size() + " Teleport Location Templates.");
	}
	
	//DEVNOTE for stream
	private final boolean filter(XmlEntry entry) {
		return entry.isTeleport();
	}
	
	//DEVNOTE for stream
	private final XmlTeleport map(XmlEntry entry) {
		return entry.asTeleport();
	}
	
	//DEVNOTE for stream
	private final void add(L2TeleportLocation teleport) {
		teleports.put(teleport.id, teleport);
	}
	
	/**
	 * Find teleport template by id
	 * @param id template id
	 * @return return template teleport. If not found, return null.
	 */
	public L2TeleportLocation getTemplate(int id) {
		return teleports.get(id);
	}
}
