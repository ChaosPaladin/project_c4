package net.sf.l2j.gameserver.instancemanager;

import net.sf.l2j.gameserver.model.quest.QuestLoader;

public class Manager
{
	public static void loadAll()
	{
		ArenaManager.getInstance();
		AuctionManager.getInstance();
		BoatManager.getInstance();
		CastleManager.getInstance();
		ClanHallManager.getInstance();
		MercTicketManager.getInstance();
		//PartyCommandManager.getInstance();
		PetitionManager.getInstance();
		QuestManager.getInstance();
		QuestLoader.getInstance();
		SiegeManager.getInstance();
		TownManager.getInstance();
		ZoneManager.getInstance();
        OlympiadStadiaManager.getInstance();
	}

	public static void reloadAll()
	{
		ArenaManager.getInstance().reload();
		AuctionManager.getInstance().reload();
		CastleManager.getInstance().reload();
		ClanHallManager.getInstance().reload();
		QuestManager.getInstance().reload();
		TownManager.getInstance().reload();
		ZoneManager.getInstance().reload();
        OlympiadStadiaManager.getInstance().reload();
	}
}