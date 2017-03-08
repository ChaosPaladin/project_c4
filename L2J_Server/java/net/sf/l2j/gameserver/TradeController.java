package net.sf.l2j.gameserver;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import la2.world.model.data.xml.XmlLoader;
import la2.world.model.data.xml.XmlShop;
import net.sf.l2j.gameserver.model.L2TradeList;

public final class TradeController {
	private static Logger _log = Logger.getLogger(TradeController.class.getName());
	
	private Map<Integer, L2TradeList> _lists;
	
	public static TradeController getInstance() {
		return SingletonHolder.instance;
	}
	
	private TradeController() {
		_lists = new TreeMap<Integer, L2TradeList>();
		XmlLoader.load("buysell.xml").list.stream()
			.filter(XmlShop.class::isInstance)
			.map(L2TradeList::new)
			.forEach(shop -> _lists.put(shop.getListId(), shop));
		_log.config("TradeController: Loaded " + _lists.size() + " Buylists.");
	}
	
	public L2TradeList getBuyList(int listId) {
		return _lists.get(new Integer(listId));
	}
	
	private static final class SingletonHolder {
		private static final TradeController instance = new TradeController();
	}
}
