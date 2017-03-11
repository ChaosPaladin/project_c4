package net.sf.l2j.gameserver.model;

import la2.world.model.data.xml.XmlEntry;
import la2.world.model.data.xml.XmlFishReward;

/**
/*
 * Author: -Nemesiss-
 *
 */
public class FishDropData {
	private final int _fishId;
	private final int _rewarditemId;
	private final int _drop;
	private final int _minchance;
	private final int _maxchance;

	public FishDropData(XmlEntry entry) {
		this((XmlFishReward) entry);
	}
	
	public FishDropData(XmlFishReward entry) {
		_fishId = entry.fishid;
		_rewarditemId = entry.rewardid;
		_drop = entry.count;
		_minchance = entry.minchance;
		_maxchance = entry.maxchance;
	}

	public int getFishId()
	{
		return _fishId;
	}
	public int getRewardItemId()
	{
		return _rewarditemId;
	}

	/**
	 * Returns the quantity of items dropped
	 * @return int
	 */
	public int getCount()
	{
		return _drop;
	}

	/**
	 * Returns the chance of having a drop
	 * @return int
	 */
	public int getMinChance()
	{
		return _minchance;
	}
	/**
	 * Returns the chance of having a drop
	 * @return int
	 */
	public int getMaxChance()
	{
		return _maxchance;
	}

}

