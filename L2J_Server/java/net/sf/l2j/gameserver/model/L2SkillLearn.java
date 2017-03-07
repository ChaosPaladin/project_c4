/*
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
package net.sf.l2j.gameserver.model;

import la2.world.model.data.xml.XmlSkillLearn;

/**
 * This class ...
 * 
 * @version $Revision: 1.2.4.2 $ $Date: 2005/03/27 15:29:33 $
 */
public final class L2SkillLearn
{
	// these two build the primary key
	public final int id;
	public final int level;
	
	// not needed, just for easier debug
	public final String name;
	
	public final int sp;
	public final int minLevel;
	public final int costid;
	public final int costcount;
	
	public L2SkillLearn(XmlSkillLearn entry) {
		this(entry.skillId, entry.level, entry.minLevel, entry.name, entry.sp, entry.costid, entry.costcount);
	}
	
	public L2SkillLearn(int id, int lvl, int minLvl, String name, int cost, int costid, int costcount)
	{
		this.id = id;
		this.level = lvl;
		this.minLevel = minLvl;
		this.name = name.intern();
		this.sp = cost;
		this.costid = costid;
		this.costcount = costcount;
	}
	
	/**
	 * @return Returns the id.
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return Returns the level.
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 * @return Returns the minLevel.
	 */
	public int getMinLevel()
	{
		return minLevel;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return Returns the spCost.
	 */
	public int getSpCost()
	{
		return sp;
	}
	public int getIdCost()
	{
		return costid;
	}
	public int getCostCount()
	{
		return costcount;
	}
}
