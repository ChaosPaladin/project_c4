package net.sf.l2j.gameserver;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javolution.util.FastList;
import la2.world.model.data.xml.XmlLoader;
import net.sf.l2j.gameserver.model.FishData;
import net.sf.l2j.gameserver.model.FishDropData;

/**
 * @author -Nemesiss-
 *
 */
public final class FishTable {
	private static Logger _log = Logger.getLogger(SkillTreeTable.class.getName());

	private List<FishData> _Fishs;
	private List<FishDropData> _FishRewards;

	public static FishTable getInstance() {
		return SingletonHolder.instance;
	}
	
	private FishTable() {
		_Fishs = new LinkedList<>();
		XmlLoader.load("fish.xml").list.stream().map(FishData::new).forEach(_Fishs::add);
		_log.config("FishTable: Loaded " + _Fishs.size() + " Fishes.");
		
		_FishRewards = new LinkedList<>();
		XmlLoader.load("fish-reward.xml").list.stream().map(FishDropData::new).forEach(_FishRewards::add);
		_log.config("FishRewardsTable: Loaded " + _FishRewards.size() + " FishRewards.");
	}
	/**
	 * @param Fish - lvl
	 * @param Fish - type
	 * @return List of Fish that can be fished
	 */
	public List<FishData> getfish(int lvl, int type)
	{
		List<FishData> result = new FastList<FishData>();
		if (_Fishs == null)
		{
			// the fish list is empty
			_log.warning("Fish are not defined !");
			return null;
		}
		for (FishData f: _Fishs)
		{
			if (f.getLevel()!= lvl) continue;
			if (f.getType() != type) continue;

			result.add(f);
		}
		if (result.size() == 0)	_log.warning("Cant Find Any Fish!? - Lvl: "+lvl+" Type: " +type);
		return result;
	}
	/**
	 * @param fishid
	 * @return List of all item that this fish can drop if open
	 */
	public List<FishDropData> GetFishRreward(int fishid)
	{
		List<FishDropData> result = new FastList<FishDropData>();
		if (_FishRewards == null)
		{
			// the fish list is empty
			_log.warning("FishRewards are not defined !");
			return null;
		}
		for (FishDropData d: _FishRewards)
		{
			if (d.getFishId()!= fishid) continue;

			result.add(d);
		}
		if (result.size() == 0)	_log.warning("Cant Find Any Fish Reward for ItemID: "+fishid);

		return result;
	}
	public int GetFishItemCount()
	{
		return _FishRewards.size();
	}
	public int getFishIdfromList(int i)
	{
		return _FishRewards.get(i).getFishId();
	}
	
	private static final class SingletonHolder {
		private static final FishTable instance = new FishTable();
	}

}