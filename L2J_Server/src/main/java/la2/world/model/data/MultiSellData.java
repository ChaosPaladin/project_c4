package la2.world.model.data;

import java.util.TreeMap;
import java.util.logging.Logger;

import la2.world.model.data.xml.XmlLoader;
import la2.world.model.data.xml.XmlMultiSell;
import la2.world.model.item.MultiSell;
import net.sf.l2j.gameserver.model.actor.instance.L2NpcInstance;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.serverpackets.NpcHtmlMessage;

public final class MultiSellData {
	private final Logger log = Logger.getLogger(MultiSellData.class.getName());
	
	public static MultiSellData getInstance() {
		return SingletonHolder.instance;
	}
	
	private final TreeMap<Integer, MultiSell> data = new TreeMap<>();
	
	private MultiSellData() {
		XmlLoader.load("multisell.xml").list.stream().map(XmlMultiSell.class::cast).map(MultiSell::new).forEach(shop -> {
			data.put(shop.id, shop);
		});
		log.info("multesells " + data.size() + " loaded.");
	}
	
	private final static class SingletonHolder {
		private static final MultiSellData instance = new MultiSellData();
	}

	public MultiSell get(int id) {
		return data.get(id);
	}
	
	public final void debugList(final L2NpcInstance instance, final L2PcInstance player) {
		final StringBuilder str = new StringBuilder();
		str.append("<html><body>");
		for(MultiSell shop : data.values())
			str.append(shop.id).append(" <a action=\"bypass -h npc_").append(instance.getObjectId()).append("_xxx ").append(shop.id).append("\">").append(shop.name).append("</a><br>\n");
		str.append("</body></html>");
		final NpcHtmlMessage html = new NpcHtmlMessage(5);
		html.setHtml(str.toString());
		player.sendPacket(html);
	}
}
