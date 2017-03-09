package net.sf.l2j.gameserver;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import la2.world.model.data.xml.XmlLoader;
import net.sf.l2j.gameserver.templates.L2HelperBuff;

/**
 * This class represents the Newbie Helper Buff list
 * 
 * 
 */
public final class HelperBuffTable {
	private static Logger _log = Logger.getLogger(HennaTable.class.getName());

    /** The table containing all Buff of the Newbie Helper */
    private final List<L2HelperBuff> _helperBuff;
    
    /** The player level since Newbie Helper can give the fisrt buff <BR>
     *  Used to generate message : "Come back here when you have reached level ...") */
    private int _magicClassLowestLevel   = 100;
    private int _physicClassLowestLevel  = 100;
    
    /** The player level above which Newbie Helper won't give any buff <BR>
     *  Used to generate message : "Only novice character of level ... or less can receive my support magic.") */
    private int _magicClassHighestLevel  = 1;
    private int _physicClassHighestLevel = 1;
    
    
    public static HelperBuffTable getInstance() {
    	return SingletonHolder.instance;
    }
    
    
    /**
     * Create and Load the Newbie Helper Buff list from SQL Table helper_buff_list
     */
    private HelperBuffTable() {
    	_helperBuff = new LinkedList<>();
    	XmlLoader.load("helper-buff-list.xml").list.stream().
    		map(L2HelperBuff::new).
    		forEach(_helperBuff::add);
    	_log.config("Helper Buff Table: Loaded " + _helperBuff.size() + " Templates.");
    	_helperBuff.forEach(buff -> {
    		if(buff.isMagicClassBuff()) {
    			_magicClassLowestLevel = Math.min(buff.getLowerLevel(), _magicClassLowestLevel);
    			_magicClassHighestLevel = Math.max(buff.getUpperLevel(), _magicClassHighestLevel);
    		} else {
    			_physicClassHighestLevel = Math.max(buff.getUpperLevel(), _physicClassHighestLevel);
    			_physicClassLowestLevel = Math.min(buff.getLowerLevel(), _physicClassLowestLevel); 
    		}
    	});
    }
    
    /**
     * Return the Helper Buff List
     */
    public List<L2HelperBuff> getHelperBuffTable()
    {
        return _helperBuff;
    }

    
    /**
     * @return Returns the magicClassHighestLevel.
     */
    public int getMagicClassHighestLevel()
    {
        return _magicClassHighestLevel;
    }
    


    /**
     * @return Returns the magicClassLowestLevel.
     */
    public int getMagicClassLowestLevel()
    {
        return _magicClassLowestLevel;
    }
    


    /**
     * @return Returns the physicClassHighestLevel.
     */
    public int getPhysicClassHighestLevel()
    {
        return _physicClassHighestLevel;
    }
    


    /**
     * @return Returns the physicClassLowestLevel.
     */
    public int getPhysicClassLowestLevel()
    {
        return _physicClassLowestLevel;
    }
    
    private static class SingletonHolder {
    	private static final HelperBuffTable instance = new HelperBuffTable();
    }
}
