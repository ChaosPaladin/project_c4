package net.sf.l2j.gameserver.clientpackets;

import java.nio.ByteBuffer;
//import java.util.logging.Logger;

import la2.world.model.data.MultiSellData;
import la2.world.model.item.MultiSell;
import la2.world.model.item.MultiSell.Barter;
import net.sf.l2j.gameserver.ClientThread;
import net.sf.l2j.gameserver.ItemTable;
import net.sf.l2j.gameserver.model.L2ItemInstance;
import net.sf.l2j.gameserver.model.L2Multisell;
import net.sf.l2j.gameserver.model.PcInventory;
import net.sf.l2j.gameserver.model.L2Multisell.MultiSellEntry;
import net.sf.l2j.gameserver.model.L2Multisell.MultiSellIngredient;
import net.sf.l2j.gameserver.model.L2Multisell.MultiSellListContainer;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.serverpackets.ItemList;
import net.sf.l2j.gameserver.serverpackets.StatusUpdate;
import net.sf.l2j.gameserver.serverpackets.SystemMessage;


public final class MultiSellChoose extends ClientBasePacket {
    private static final String _C__A7_MULTISELLCHOOSE = "[C] A7 MultiSellChoose";
    
    private int id;//multisell id
    private int index;//barter index
    private int amount;//amount for exchange
    
    public MultiSellChoose(ByteBuffer buf, ClientThread client) {
        super(buf,client);
        id = readD();
        index = readD();
        amount = readD();
    }
    
    public void runImpl()
    {
    	if(id < 0 || amount < 1 || amount > 100)
    		return;
    	doExchange(getClient().getActiveChar(), MultiSellData.getInstance().get(id));
    }
    
    private final void doExchange(final L2PcInstance player, final MultiSell multisell) {
    	if(player == null || multisell == null)
    		return;
    	final Barter barter = multisell.get(index - 1);
    	if(barter != null) {
    		barter.exchange(player, amount);
    	} else
    		System.out.println("not found barter with index " + index);
    }
    
    public String getType() {
        return _C__A7_MULTISELLCHOOSE;
    }
}
