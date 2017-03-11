package net.sf.l2j.gameserver.model;

import la2.world.model.data.xml.XmlEntry;
import la2.world.model.data.xml.XmlFish;

public class FishData {
	private final int _id;
	private final int _level;
	private final String _name;
	private final int _HP;
	private final int _HpRegen;
	private final int _type;
	
	public FishData(XmlEntry entry) {
		this((XmlFish) entry);
	}
		
	public FishData(XmlFish entry) {
		_id = entry.id;
		_level = entry.level;
		_name = entry.name;
		_HP = entry.hp;
		_HpRegen = entry.hpregen;
		_type = entry.type;
	}

		/**
		 * @return Returns the id.
		 */
		public int getId()
		{
			return _id;
		}

		/**
		 * @return Returns the level.
		 */
		public int getLevel()
		{
			return _level;
		}

		/**
		 * @return Returns the name.
		 */
		public String getName()
		{
			return _name;
		}

		public int getHP()
		{
			return _HP;
		}
		public int getHpRegen()
		{
			return _HpRegen;
		}
		public int getType()
		{
			return _type;
		}
	}

