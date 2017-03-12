package la2.world.model.item;

import java.util.ArrayList;
import java.util.logging.Logger;

import la2.world.model.data.xml.XmlMultiSell;
import net.sf.l2j.gameserver.ItemTable;
import net.sf.l2j.gameserver.model.L2ItemInstance;
import net.sf.l2j.gameserver.model.PcInventory;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.serverpackets.ItemList;
import net.sf.l2j.gameserver.serverpackets.MultiSellList;
import net.sf.l2j.gameserver.serverpackets.StatusUpdate;
import net.sf.l2j.gameserver.serverpackets.SystemMessage;
import net.sf.l2j.gameserver.templates.L2Item;

public class MultiSell {
	public final Logger log = Logger.getLogger(MultiSell.class.getName());
	
	public int index = 1;
	
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
 		
 		public final int id = index++;
		
 		//подсчитанные данные при загрузге
 		//общий вес продукции
 		public final int weight;
 		
 		//количество слотов неообходимое для продукции
 		public final int slots;
 		
 		//общее количество адены необходимоя для торговли(для пыстрого проверки налчичия нужно количества у игрока)
 		public final int alladena;
 		
		public Barter(final XmlMultiSell.Barter data) {
			adena = data.adena;
			goods = data.goods.stream().map(Item::new).toArray(Item[]::new);
			production = data.production.stream().map(Item::new).toArray(Item[]::new);
			
			//counting all adena
			int adena = 0;
			for(Item item : goods)
				if(item.item.getItemId() == 57)
					adena += item.count; 
			alladena = this.adena + adena;
			
			int w = 0;
			int s = 0;
			
			for(Item item : production) {
				w += item.count * item.item.getWeight();
				if(item.item.isStackable())
					s++;
				else
					s += item.count;
			}
			weight = w;
			slots = s;
		}

		public boolean test(final PcInventory inventory) {
			if(adena > inventory.getAdena())
				return false;
			for(Item item : goods) {
				final L2ItemInstance x = inventory.getItemByItemId(item.item.getItemId());
				if(x == null)
					return false;
				if(x.isEquipped() || x.getCount() < item.count)
					return false;
			}
			return true;
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

		/*
		 * Общий алгорит
		 * Проверка наличия адены, если есть необходимость(складывает не облагаемую налогом аденну)
		 * Проверка наличия необходимых предметов
		 * Проверка наличия места в инвенторе(проверка исключает предметы которые уже есть в инвенторе)
		 * Проверка наличия свободно веса
		 * Унижчтожение предметов
		 * Добавление предметов
		 * Отправка сообщения
		 * 
		 * TODO тразакция с откатом...
		 * TODO ecnhant level
		 */
		public void exchange(final L2PcInstance player, int amount) {
			final PcInventory inventory = player.getInventory();
			if(alladena > 0 && inventory.getAdena() < alladena*amount) {
				player.sendPacket(new SystemMessage(SystemMessage.YOU_NOT_ENOUGH_ADENA));
				return;
			}
			if(!inventory.validateWeight(weight*amount)) {
				player.sendPacket(new SystemMessage(SystemMessage.WEIGHT_LIMIT_EXCEEDED));
				return;
			}
			for(Item need : goods) {
				final L2ItemInstance item = inventory.getItemByItemId(need.item.getItemId());
				if(amount*need.count > Integer.MAX_VALUE) {
					player.sendPacket(new SystemMessage(SystemMessage.YOU_HAVE_EXCEEDED_QUANTITY_THAT_CAN_BE_INPUTTED));
					return;
				}
				if(item == null || (need.count*amount) > inventory.getInventoryItemCount(item.getItemId(), 0)) {
					player.sendPacket(new SystemMessage(SystemMessage.NOT_ENOUGH_ITEMS));
					return;
				}
			}
			int slots = this.slots;
			for(Item product : production) {
				if(product.item.isStackable() && inventory.getItemByItemId(product.item.getItemId()) != null)
					slots--;
			}
			if(slots > 0 && !inventory.validateCapacity(slots)) {
				player.sendPacket(new SystemMessage(SystemMessage.SLOTS_FULL));
				return;
			}
			for(Item need : goods) {
	            if (inventory.getItemByItemId(need.item.getItemId()).isStackable())
	                player.destroyItemByItemId("Multisell", need.item.getItemId(), (need.count * amount), player.getTarget(), true);
	            else
	                for (int i = 1; i <= (need.count * amount); i++)
	                    player.destroyItemByItemId("Multisell", need.item.getItemId(), 1, player.getTarget(), true);
			}
			for(Item product : production) {
				if(product.item.isStackable()) {
					inventory.addItem("Multisell", product.item.getItemId(), amount * product.count, player, player);
				} else {
					for(int i = 0 ; i < product.count*amount ; i++)
						inventory.addItem("Multisell", product.item.getItemId(), 1, player, player);
				}
			}
			//забирает облагаемую налогом аденну
			inventory.reduceAdena("Multisell", adena*amount, player, player);//TODO передалать, добавить npc, через которого ведется торговля
			for(Item product : production) {
				if(product.item.isStackable()) {
			        SystemMessage sm = new SystemMessage(SystemMessage.EARNED_S2_S1_s);
		            sm.addItemName(product.item.getItemId());
		            sm.addNumber(product.count * amount);
		            player.sendPacket(sm);
				} else {
					for(int i = 0 ; i < product.count*amount ; i++) {
				        SystemMessage sm = new SystemMessage(SystemMessage.EARNED_ITEM);
		                sm.addItemName(product.item.getItemId());
		                player.sendPacket(sm);
					}
				}
			}
			player.sendPacket(new ItemList(player, false));
	        StatusUpdate su = new StatusUpdate(player.getObjectId());
	        su.addAttribute(StatusUpdate.CUR_LOAD, player.getCurrentLoad());
	        player.sendPacket(su);
		}
	}
	
	public void show(final L2PcInstance player) {
		if(isShowAll) {
			int count = list.length/0x1c + (list.length % 0x1c == 0 ? 0 : 1);
			for(int i = 0 ; i < count ; i++)
				player.sendPacket(new MultiSellList(this, i, ((i == count - 1 && list.length % 0x1c != 0) ? ((list.length % 0x1c)) : 0x1c), i == (count - 1)));	
		} else {
			final PcInventory inventory = player.getInventory();
			final ArrayList<Barter> list = new ArrayList<>();
			for(Barter barter : this.list) {
				if(barter.test(inventory))
					list.add(barter);
			}
			int count = list.size()/0x1c + (list.size() % 0x1c == 0 ? 0 : 1);
			for(int i = 0 ; i < count ; i++)
				player.sendPacket(new MultiSellList(list, i, ((i == count - 1 && list.size() % 0x1c != 0) ? ((list.size() % 0x1c)) : 0x1c), i == (count - 1), id));
			if(count == 0)
				player.sendPacket(new MultiSellList(list, 0, 0, true, id));
		}
	}

	public Barter get(int index) {
		if(index >= list.length || index < 0)
			return null;
		assert list[index].id == index + 1;
		return list[index];
	}
	
}
