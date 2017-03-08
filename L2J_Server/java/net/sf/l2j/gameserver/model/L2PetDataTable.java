package net.sf.l2j.gameserver.model;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import la2.world.model.data.xml.XmlEntry;
import la2.world.model.data.xml.XmlLoader;
import net.sf.l2j.gameserver.model.actor.instance.L2PetInstance;

public class L2PetDataTable
{
	private static Logger _log = Logger.getLogger(L2PetInstance.class.getName()); 
    private static L2PetDataTable _instance;
    
    public static final int[] petList = { 12077, 12312, 12313, 12311, 12527, 12528, 12526 };
    private static Map<Integer, Map<Integer, L2PetData>> petTable;
    
    public static L2PetDataTable getInstance() {
        if (_instance == null)
            _instance = new L2PetDataTable(); 
        return _instance;
    }
    
    private L2PetDataTable() {
        petTable = new TreeMap<Integer, Map<Integer, L2PetData>>();
    }
    
    public void loadPetsData() {
    	int[] count = new int[]{0};
    	XmlLoader.load("pets-stats.xml").list.stream().
    		filter(XmlEntry::isPetsStats).
    		map(XmlEntry::asPetsStats).
    		map(L2PetData::new).
    		forEach(pet -> {
    			if(!petTable.containsKey(pet.getPetID()))
    				petTable.put(pet.getPetID(), new TreeMap<>());
    			petTable.get(pet.getPetID()).put(pet.getPetLevel(), pet);
    			count[0]++;
    		});
    	_log.info("Loaded " + count[0] + " pets stats.");
    }
    
    public L2PetData getPetData(int petID, int petLevel)
    {
        //System.out.println("Getting id "+petID+" level "+ petLevel);
        return petTable.get(petID).get(petLevel);
    }
    
	
	/**
	 * Pets stuffs
	 */
    public static boolean isWolf(int npcId)
    {
    	return npcId == 12077;
    }
    
    public static boolean isHatchling(int npcId)
    {
    	return npcId > 12310 && npcId < 12314;
    }
    
    public static boolean isStrider(int npcId)
    {
    	return npcId > 12525 && npcId < 12529;
    }
    
    public static boolean isWyvern(int npcId)
    {
    	return npcId == 12621;
    }
    
    public static boolean isBaby(int npcId)
    {
    	return npcId > 12779 && npcId < 12783;
    }
    
    
    public static boolean isPetFood(int itemId)
    {
    	return (itemId == 2515) || (itemId == 4038) || (itemId == 5168) || (itemId == 6316) || (itemId == 7582);
    }
    
    public static boolean isWolfFood(int itemId)
    {
    	return itemId == 2515;
    }
    
    public static boolean isHatchlingFood(int itemId)
    {
    	return itemId == 4038;
    }
    
    public static boolean isStriderFood(int itemId)
    {
    	return itemId == 5168;
    }
    
    public static boolean isWyvernFood(int itemId)
    {
    	return itemId == 6316;
    }
    
    public static boolean isBabyFood(int itemId)
    {
    	return itemId == 7582;
    }

    public static int getFoodItemId(int npcId)
    {
    	if (isWolf(npcId))
    		return 2515;
    	else if (isHatchling(npcId))
    		return 4038;
    	else if (isStrider(npcId))
    		return 5168;
    	else if (isBaby(npcId))
    		return 7582;
    	else
    		return 0;
	}
    
    
    public static int getPetIdByItemId(int itemId)
    {
		switch (itemId)
		{
			// wolf pet a
			case 2375:
				return 12077;
			// hatchling of wind
			case 3500:
				return 12311;
			// hatchling of star
			case 3501:
				return 12312;
			// hatchling of twilight
			case 3502:
				return 12313;
			//  wind strider
			case 4422:
				return 12526;
			//	Star strider
			case 4423:
				return 12527;
			// Twilight strider
			case 4424:
				return 12528;
		    // Wyvern
            case 4425:
            	return 12621;
			// Baby Buffalo
			case 6648:
				return 12780;
			// Baby Cougar
			case 6649:
				return 12782;
			// Baby Kookaburra
			case 6650:
				return 12781;
				
			// unknown item id.. should never happen
			default:
				return 0;
		}
    }
    
    public static int getHatchlingWindId()
    {
    	return 12311;
    }
    public static int getHatchlingStarId()
    {
    	return 12312;
    }
    public static int getHatchlingTwilightId()
    {
    	return 12313;
    }
    
    public static int getStriderWindId()
    {
    	return 12526;
    }
    public static int getStriderStarId()
    {
    	return 12527;
    }
    public static int getStriderTwilightId()
    {
    	return 12528;
    }
    
    
    public static int getWyvernItemId()
    {
    	return 4425;
    }
    public static int getStriderWindItemId()
    {
    	return 4422;
    }
    public static int getStriderStarItemId()
    {
    	return 4423;
    }
    public static int getStriderTwilightItemId()
    {
    	return 4424;
    }
    
    public static boolean isPetItem(int itemId)
    {
    	return (itemId == 2375 // wolf
				|| itemId == 3500 || itemId == 3501 || itemId == 3502 // hatchlings
				|| itemId == 4422 || itemId == 4423 || itemId == 4424 // striders
	            || itemId == 4425 // Wyvern
				|| itemId == 6648 || itemId == 6649 || itemId == 6650); // Babies
    }
    
    public static int[] getPetItemsAsNpc(int npcId)
    {
		switch (npcId)
		{
			case 12077:// wolf pet a
				return new int[]{2375};
			
			case 12311:// hatchling of wind
			case 12312:// hatchling of star
			case 12313:// hatchling of twilight
	    		return new int[]{3500, 3501, 3502};
			
			case 12526:// wind strider
			case 12527:// Star strider
			case 12528:// Twilight strider
	    		return new int[]{4422, 4423, 4424};
		    
            case 12621:// Wyvern
        		return new int[]{4425};
			
			case 12780:// Baby Buffalo
			case 12782:// Baby Cougar
			case 12781:// Baby Kookaburra
	    		return new int[]{6648, 6649, 6650};
				
			// unknown item id.. should never happen
			default:
				return new int[]{0};
		}
    }
    
    
    public static boolean isMountable(int npcId)
    {
    	return npcId == 12526		// wind strider
		    	|| npcId == 12527	// star strider
		    	|| npcId == 12528	// twilight strider
		    	|| npcId == 12621;	// wyvern
    }
}
