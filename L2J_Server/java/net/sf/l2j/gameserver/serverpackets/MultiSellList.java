/*
 * $Header: MultiSellList.java, 2/08/2005 14:21:01 luisantonioa Exp $
 *
 * $Author: luisantonioa $
 * $Date: 2/08/2005 14:21:01 $
 * $Revision: 1 $
 * $Log: MultiSellList.java,v $
 * Revision 1  2/08/2005 14:21:01  luisantonioa
 * Added copyright notice
 *
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package net.sf.l2j.gameserver.serverpackets;

import la2.world.model.item.MultiSell;
import net.sf.l2j.gameserver.ItemTable;
import net.sf.l2j.gameserver.model.L2ItemInstance;
import net.sf.l2j.gameserver.model.L2Multisell;
import net.sf.l2j.gameserver.model.L2Multisell.MultiSellEntry;
import net.sf.l2j.gameserver.model.L2Multisell.MultiSellIngredient;
import net.sf.l2j.gameserver.model.L2Multisell.MultiSellListContainer;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;


/**
 * This class ...
 * 
 * @version $Revision: 1.2 $ $Date: 2004/06/27 08:12:59 $
 */
public class MultiSellList extends ServerBasePacket
{
    private static final String _S__D0_MULTISELLLIST = "[S] D0 MultiSellList";

    protected L2PcInstance _player;
    protected int _listId;
    protected boolean _inventoryOnly;
    protected MultiSellListContainer _list;

    public MultiSellList(int listId)
    {
        _listId = listId;
        _inventoryOnly = false;
        _player = null;
        list = null;
    }   

    public MultiSellList(int listId, boolean inventoryOnly, L2PcInstance player)
    {
        _listId = listId;
        _inventoryOnly = inventoryOnly;
        _player = player;
        list = null;
    }   
    
    private final MultiSell list;
    
    private int offset;
    
    private int size;
    
    private boolean eop;//end of page
    
    public MultiSellList(final MultiSell multisell, int offset, int size, boolean eop) {
    	list = multisell;
    	this.offset = offset;
    	this.size = size;
    	this.eop = eop;
    	System.out.println(eop);
    }

    void runImpl()
    {
    	if(list != null)
    		return;
        if (_inventoryOnly && _player != null)
        {
            MultiSellListContainer tmpList = L2Multisell.getInstance().getList(_listId);
            if (tmpList == null) return;
            
            L2ItemInstance[] items = _player.getInventory().getUniqueItems(false);
            _list = L2Multisell.getInstance().new MultiSellListContainer();
            _list.setListId(_listId);
            
            for (MultiSellEntry ent : tmpList.getEntries())
            {
                boolean found = false;
                
                for (L2ItemInstance item : items)
                {
                    if (found)
                        break;
                    
                    for (MultiSellIngredient ing : ent.getIngredients())
                    {
                        if (item.getItemId() == ing.getItemId() && !item.isWear())
                        {
                            _list.addEntry(ent);
                            found = true;
                            break;
                        }
                    }
                }
            }
        }
        else
        {
            _list = L2Multisell.getInstance().getList(_listId);
        }
    }

    void writeImpl()
    {
    	if(list != null) {
    		writeC(0xd0);
    		writeD(list.id);
    		writeD(offset + 1);
    		writeD(eop?1:0);
    		writeD(0x1c);
    		writeD(list.list.length);	
    		for(int i = offset*0x1c ; i < offset*0x1c + size ; i++) {
    			writeD(i + 1);
    			writeC(1);
    			writeH(list.list[i].production.length);
    			writeH(list.list[i].goods.length);
    			for(MultiSell.Barter.Item item : list.list[i].production) {
    				writeH(item.item.getItemId());
    				writeD(0);
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
    		}
    		return;
    	}
    	// [ddddd] [dchh] [hdhdh] [hhdh]
    	
        writeC(0xd0);
        writeD(_listId);    // list id
        writeD(1);			// ?
        writeD(1);			// ?
        writeD(0x1c);		// ?
        writeD(_list == null ? 0 : _list.getEntries().size()); //list lenght
        
        if(_list != null)
        {
            for(MultiSellEntry ent : _list.getEntries())
            {
            	int typeP = ItemTable.getInstance().getTemplate(ent.getProductId()).getType2();
    
            	writeD(ent.getEntryId());
            	writeC(1);
            	writeH(1);
            	writeH(ent.getIngredients().size());
    
            	writeH(ent.getProductId());
            	writeD(0);
            	writeH(typeP);
            	writeD(ent.getProductCount());
            	writeH(0);
            	
            	
                for(MultiSellIngredient i : ent.getIngredients())
                {
                	int typeE = ItemTable.getInstance().getTemplate(i.getItemId()).getType2();
                    writeH(i.getItemId());      //ID
                    writeH(typeE);
                    writeD(i.getItemCount());	//Count
                    writeH(i.getItemEnchant()); //Enchant Level
                }
            }
        }
    }

    @Override
    public String getType()
    {
        return _S__D0_MULTISELLLIST;
    }

}

