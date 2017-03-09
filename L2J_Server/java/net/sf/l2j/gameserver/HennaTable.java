package net.sf.l2j.gameserver;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import la2.world.model.data.xml.XmlLoader;
import net.sf.l2j.gameserver.templates.L2Henna;

public class HennaTable {
	private static Logger _log = Logger.getLogger(HennaTable.class.getName());
	
	private final Map<Integer, L2Henna> henna = new TreeMap<>();
	
	public static HennaTable getInstance() {
		return SingletonHolder.instance;
	}

	private HennaTable() {
		XmlLoader.load("henna.xml").list.stream().
			map(L2Henna::new).
			forEach(entry -> henna.put(entry.symbol_id, entry));	
		_log.config("HennaTable: Loaded " + henna.size() + " Templates.");
	}
    
	public L2Henna getTemplate(int id) {
		return henna.get(id);
	}

	private final static class SingletonHolder {
		private static final HennaTable instance = new HennaTable();
	}
}