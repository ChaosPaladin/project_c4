package net.sf.l2j.gameserver.serverpackets;

import java.util.ArrayList;

import la2.world.model.item.MultiSell;
import la2.world.model.item.MultiSell.Barter;
import net.sf.l2j.gameserver.ItemTable;
import net.sf.l2j.gameserver.templates.L2Item;


public final class MultiSellList extends ServerBasePacket {
    private static final String _S__D0_MULTISELLLIST = "[S] D0 MultiSellList";
    protected int id;

    private final MultiSell list;
    
    private int offset;
    
    private int size;
    
    private boolean eop;//end of page
    
    public MultiSellList(final MultiSell multisell, int offset, int size, boolean eop) {
    	list = multisell;
    	this.offset = offset;
    	this.size = size;
    	this.eop = eop;
    	barters = null;
    }

    private final ArrayList<Barter> barters;
    
    public MultiSellList(final ArrayList<Barter> list, int offset, int size, boolean eop, int id) {
    	this.barters = list;
    	this.offset = offset;
    	this.size = size;
    	this.eop = eop;   	
    	this.list = null;
    	this.id = id;
    }
    
    void runImpl() {  }

    void writeImpl()
    {
    	if(list != null) {
    		writeC(0xd0);
    		writeD(list.id);
    		writeD(offset + 1);
    		writeD(eop?1:0);
    		writeD(0x1c);
    		writeD(size);	
    		for(int i = offset*0x1c ; i < offset*0x1c + size ; i++) {
    			writeD(list.list[i].id);
    			writeC(1);
    			writeH(list.list[i].production.length);
    			writeH(list.list[i].goods.length + (list.list[i].adena > 0 ? 1 : 0));
    			for(MultiSell.Barter.Item item : list.list[i].production) {
    				writeH(item.item.getItemId());
    				writeD(item.item.getBodyPart());
    				writeH(item.item.getType2());
    				writeD(item.count);
    				writeH(0);
    			}
    			for(MultiSell.Barter.Item item : list.list[i].goods) {
    				writeH(item.item.getItemId());
    				writeH(item.item.getType2());
    				writeD(item.count);
    				writeH(0);
    			}
    			if(list.list[i].adena > 0) {
    				L2Item adena = ItemTable.getInstance().getTemplate(57);
    				writeH(adena.getItemId());
    				writeH(adena.getType2());
    				writeD(list.list[i].adena);
    				writeH(0);
    			}
    		}
    		return;
    	}
    	if(barters != null) {
    		writeC(0xd0);
    		writeD(id);
    		writeD(offset + 1);
    		writeD(eop?1:0);
    		writeD(0x1c);
    		writeD(size);	
    		for(int i = offset*0x1c ; i < offset*0x1c + size ; i++) {
    			writeD(barters.get(i).id);
    			writeC(1);
    			writeH(barters.get(i).production.length);
    			writeH(barters.get(i).goods.length + (barters.get(i).adena > 0 ? 1 : 0));
    			for(MultiSell.Barter.Item item : barters.get(i).production) {
    				writeH(item.item.getItemId());
    				writeD(item.item.getBodyPart());
    				writeH(item.item.getType2());
    				writeD(item.count);
    				writeH(0);
    			}
    			for(MultiSell.Barter.Item item : barters.get(i).goods) {
    				writeH(item.item.getItemId());
    				writeH(item.item.getType2());
    				writeD(item.count);
    				writeH(0);
    			}
    			if(barters.get(i).adena > 0) {
    				L2Item adena = ItemTable.getInstance().getTemplate(57);
    				writeH(adena.getItemId());
    				writeH(adena.getType2());
    				writeD(barters.get(i).adena);
    				writeH(0);
    			}
    		}
    		return;
    	}
    }

    @Override
    public String getType() {
        return _S__D0_MULTISELLLIST;
    }

}

