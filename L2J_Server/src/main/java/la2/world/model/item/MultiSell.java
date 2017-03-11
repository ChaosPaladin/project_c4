package la2.world.model.item;

import java.util.logging.Logger;

import la2.world.model.data.xml.XmlMultiSell;
import net.sf.l2j.gameserver.ItemTable;
import net.sf.l2j.gameserver.model.L2ItemInstance;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.serverpackets.MultiSellList;
import net.sf.l2j.gameserver.templates.L2Item;

public class MultiSell {
	public final Logger log = Logger.getLogger(MultiSell.class.getName());
	
	public final String name;
	
	public final int id;
	
	public final boolean isDutyfree;
	
	public final boolean isShowAll;
	
	public final boolean keepEnchanted;
	
	public final Barter[] list;
	
	public MultiSell(final XmlMultiSell shop) {
		name = shop.name;
		id = shop.id;
		isDutyfree = shop.isDutyfree;
		isShowAll = shop.isShowAll;
		keepEnchanted = shop.keepEnchanted;
		list = shop.barter.stream().map(Barter::new).toArray(Barter[]::new);
	}
	
	public class Barter {
		public final int adena;
		
		public final Item[] goods;
		
 		public final Item[] production;
		
		public Barter(final XmlMultiSell.Barter data) {
			adena = data.adena;
			goods = data.goods.stream().map(Item::new).toArray(Item[]::new);
			production = data.goods.stream().map(Item::new).toArray(Item[]::new);
		}

		public class Item {
			public final L2Item item;
			public final int count;
			
			public Item(final XmlMultiSell.Item data) {
				item = ItemTable.getInstance().get(data.name);
				if(item == null)
					log.info("not found item with name " + data.name);
				count = data.count;
			}
		}
	}
	
	public void show(final L2PcInstance player) {
		int count = list.length/0x1c + (list.length % 0x1c == 0 ? 0 : 1);
		for(int i = 0 ; i < count ; i++)
			player.sendPacket(new MultiSellList(this, i, ((i == count - 1 && list.length % 0x1c != 0) ? ((list.length % 0x1c)) : 0x1c), i == (count - 1)));
	}
	
}
