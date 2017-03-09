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
package net.sf.l2j.gameserver.templates;

import la2.world.model.data.xml.XmlNpc;
import la2.world.model.data.xml.XmlPcStats;

/**
 * This class ...
 * 
 * @version $Revision: 1.2.4.6 $ $Date: 2005/04/02 15:57:51 $
 */
public class L2CharTemplate
{
	// BaseStats
	public final int baseSTR;
	public final int baseCON;
	public final int baseDEX;
	public final int baseINT;
	public final int baseWIT;
	public final int baseMEN;
	public final float baseHpMax;
    public final float baseCpMax;
	public final float baseMpMax;
	
	/** HP Regen base */
	public final float baseHpReg;
	
	/** MP Regen base */
	public final float baseMpReg;
       
    /** CP Regen base */
    public final float baseCpReg;
	
	public final int basePAtk;
	public final int baseMAtk;
	public final int basePDef;
	public final int baseMDef;
	public final int basePAtkSpd;
	public final int baseMAtkSpd;
	public final float baseMReuseRate;
	public final int baseShldDef;
	public final int baseAtkRange;
	public final int baseShldRate;
	public final int baseCritRate;
	public final int baseAttackCancel;
	public final int baseRunSpd;
	// SpecialStats
	public final int baseBreath;
	public final int baseAggression;
	public final int baseBleed;
	public final int basePoison;
	public final int baseStun;
	public final int baseRoot;
	public final int baseMovement;
	public final int baseConfusion;
	public final int baseSleep;
	public final int baseFire;
	public final int baseWind;
	public final int baseWater;
	public final int baseEarth;
 public final int baseHoly;
 public final int baseDark;
	public final int baseAggressionRes;
	public final int baseBleedRes;
	public final int basePoisonRes;
	public final int baseStunRes;
	public final int baseRootRes;
	public final int baseMovementRes;
	public final int baseConfusionRes;
	public final int baseSleepRes;
	public final int baseFireRes;
	public final int baseWindRes;
	public final int baseWaterRes;
	public final int baseEarthRes;
	public final int baseHolyRes;
	public final int baseDarkRes;
    
    //C4 Stats
    public final int baseMpConsumeRate;
    public final int baseHpConsumeRate;
	
    public double collisionRadius;   
    public double collisionHeight;
	
	
    public L2CharTemplate(XmlPcStats stats) {
		baseSTR            = stats.STR;
		baseCON            = stats.CON;
		baseDEX            = stats.DEX;
		baseINT            = stats.INT;
		baseWIT            = stats.WIT;
		baseMEN            = stats.MEN;
		baseHpMax          = (int) stats.defaulthpbase; 
    	baseCpMax          = (int) stats.defaultcpbase;
		baseMpMax          = (int) stats.defaultcpmod;
		baseHpReg          = 1.5f;
        baseCpReg          = 0.01f;
		baseMpReg          = 0.9f;
		basePAtk           = stats.pAtk;
		baseMAtk           = stats.mAtk;
		basePDef           = stats.pDef;
		baseMDef           = stats.mDef;
		basePAtkSpd        = stats.pSpd;
		baseMAtkSpd        = stats.mSpd;
		baseMReuseRate     = 1f;
		baseShldDef        = 0;
		baseAtkRange       = 40;
		baseShldRate       = 0;
		baseCritRate       = stats.critical/10;
		baseAttackCancel   = 50; // 50% to break ATTACK
		baseRunSpd         = stats.moveSpeed;
		// SpecialStats
		baseBreath         = 100;
		baseAggression     = 0;
		baseBleed          = 0;
		basePoison         = 0;
		baseStun           = 0;
		baseRoot           = 0;
		baseMovement       = 0;
		baseConfusion      = 0;
		baseSleep          = 0;
		baseFire           = 0;
		baseWind           = 0;
		baseWater          = 0;
		baseEarth          = 0;
		baseHoly           = 0;
		baseDark           = 0;
		baseAggressionRes  = 0;
		baseBleedRes       = 0;
		basePoisonRes      = 0;
		baseStunRes        = 0;
		baseRootRes        = 0;
		baseMovementRes    = 0;
		baseConfusionRes   = 0;
		baseSleepRes       = 0;
		baseFireRes        = 0;
		baseWindRes        = 0;
		baseWaterRes       = 0;
		baseEarthRes       = 0;
		baseHolyRes        = 0;
		baseDarkRes        = 0;
        
        //C4 Stats
        baseMpConsumeRate      = 0;
        baseHpConsumeRate      = 0;
		
		// Geometry
		collisionRadius    = 0;
		collisionHeight    = 0;
    }
    
	public L2CharTemplate(StatsSet set)
	{
		// Base stats
		baseSTR            = set.getInteger("baseSTR");
		baseCON            = set.getInteger("baseCON");
		baseDEX            = set.getInteger("baseDEX");
		baseINT            = set.getInteger("baseINT");
		baseWIT            = set.getInteger("baseWIT");
		baseMEN            = set.getInteger("baseMEN");
		baseHpMax          = set.getFloat ("baseHpMax");
    	baseCpMax          = set.getFloat("baseCpMax");
		baseMpMax          = set.getFloat ("baseMpMax");
		baseHpReg          = set.getFloat ("baseHpReg");
        baseCpReg          = set.getFloat("baseCpReg");
		baseMpReg          = set.getFloat ("baseMpReg");
		basePAtk           = set.getInteger("basePAtk");
		baseMAtk           = set.getInteger("baseMAtk");
		basePDef           = set.getInteger("basePDef");
		baseMDef           = set.getInteger("baseMDef");
		basePAtkSpd        = set.getInteger("basePAtkSpd");
		baseMAtkSpd        = set.getInteger("baseMAtkSpd");
		baseMReuseRate     = set.getFloat ("baseMReuseDelay", 1.f);
		baseShldDef        = set.getInteger("baseShldDef");
		baseAtkRange       = set.getInteger("baseAtkRange");
		baseShldRate       = set.getInteger("baseShldRate");
		baseCritRate       = set.getInteger("baseCritRate");
		baseAttackCancel   = set.getInteger("baseAttackCancel", 50); // 50% to break ATTACK
		baseRunSpd         = set.getInteger("baseRunSpd");
		// SpecialStats
		baseBreath         = set.getInteger("baseBreath",         100);
		baseAggression     = set.getInteger("baseAggression",     0);
		baseBleed          = set.getInteger("baseBleed",          0);
		basePoison         = set.getInteger("basePoison",         0);
		baseStun           = set.getInteger("baseStun",           0);
		baseRoot           = set.getInteger("baseRoot",           0);
		baseMovement       = set.getInteger("baseMovement",       0);
		baseConfusion      = set.getInteger("baseConfusion",      0);
		baseSleep          = set.getInteger("baseSleep",          0);
		baseFire           = set.getInteger("baseFire",           0);
		baseWind           = set.getInteger("baseWind",           0);
		baseWater          = set.getInteger("baseWater",          0);
		baseEarth          = set.getInteger("baseEarth",          0);
		baseHoly           = set.getInteger("baseHoly",           0);
		baseDark           = set.getInteger("baseDark",           0);
		baseAggressionRes  = set.getInteger("baseAaggressionRes", 0);
		baseBleedRes       = set.getInteger("baseBleedRes",       0);
		basePoisonRes      = set.getInteger("basePoisonRes",      0);
		baseStunRes        = set.getInteger("baseStunRes",        0);
		baseRootRes        = set.getInteger("baseRootRes",        0);
		baseMovementRes    = set.getInteger("baseMovementRes",    0);
		baseConfusionRes   = set.getInteger("baseConfusionRes",   0);
		baseSleepRes       = set.getInteger("baseSleepRes",       0);
		baseFireRes        = set.getInteger("baseFireRes",        0);
		baseWindRes        = set.getInteger("baseWindRes",        0);
		baseWaterRes       = set.getInteger("baseWaterRes",       0);
		baseEarthRes       = set.getInteger("baseEarthRes",       0);
		baseHolyRes        = set.getInteger("baseHolyRes",        0);
		baseDarkRes        = set.getInteger("baseDarkRes",        0);
        
        //C4 Stats
        baseMpConsumeRate      = set.getInteger("baseMpConsumeRate",        0);
        baseHpConsumeRate      = set.getInteger("baseHpConsumeRate",        0);
		
		// Geometry
		collisionRadius    = set.getInteger("collision_radius");
		collisionHeight    = set.getInteger("collision_height");
	}

	protected L2CharTemplate(final XmlNpc entry) {
		// Base stats
		baseSTR            = entry.STR;
		baseCON            = entry.CON;
		baseDEX            = entry.DEX;
		baseINT            = entry.INT;
		baseWIT            = entry.WIT;
		baseMEN            = entry.MEN;
		baseHpMax          = entry.hp; 
    	baseCpMax          = 0;
		baseMpMax          = entry.mp;
		baseHpReg          = 1.5f + ((entry.level-1)/10);
        baseCpReg          = 0;
		baseMpReg          = 0.9f + 0.3f*((entry.level-1)/10);
		basePAtk           = entry.patk;
		baseMAtk           = entry.matk;
		basePDef           = entry.pdef;
		baseMDef           = entry.mdef;
		basePAtkSpd        = entry.atkspd;
		baseMAtkSpd        = entry.matkspd;
		baseMReuseRate     = 1f;
		baseShldDef        = 0;
		baseAtkRange       = entry.attackrange;
		baseShldRate       = 0;
		baseCritRate       = 38;
		baseAttackCancel   = 50; // 50% to break ATTACK
		baseRunSpd         = entry.runspd;
		// SpecialStats
		baseBreath         = 100;
		baseAggression     = 0;
		baseBleed          = 0;
		basePoison         = 0;
		baseStun           = 0;
		baseRoot           = 0;
		baseMovement       = 0;
		baseConfusion      = 0;
		baseSleep          = 0;
		baseFire           = 0;
		baseWind           = 0;
		baseWater          = 0;
		baseEarth          = 0;
		baseHoly           = 0;
		baseDark           = 0;
		baseAggressionRes  = 0;
		baseBleedRes       = 0;
		basePoisonRes      = 0;
		baseStunRes        = 0;
		baseRootRes        = 0;
		baseMovementRes    = 0;
		baseConfusionRes   = 0;
		baseSleepRes       = 0;
		baseFireRes        = 0;
		baseWindRes        = 0;
		baseWaterRes       = 0;
		baseEarthRes       = 0;
		baseHolyRes        = 0;
		baseDarkRes        = 0;
        
        //C4 Stats
        baseMpConsumeRate      = 0;
        baseHpConsumeRate      = 0;
		
		// Geometry
		collisionRadius    = entry.collisionRadius;
		collisionHeight    = entry.collisionHeight;
	}

	
	//TODO rework
	public L2CharTemplate(L2CharTemplate entry, float baseHpMax) {
		// Base stats
		baseSTR            = entry.baseSTR;
		baseCON            = entry.baseCON;
		baseDEX            = entry.baseDEX;
		baseINT            = entry.baseINT;
		baseWIT            = entry.baseWIT;
		baseMEN            = entry.baseMEN;
		this.baseHpMax     = baseHpMax; 
    	baseCpMax          = 0;
		baseMpMax          = entry.baseMpMax;
		baseHpReg          = entry.baseHpReg;
        baseCpReg          = 0;
		baseMpReg          = entry.baseMpReg;
		basePAtk           = entry.basePAtk;
		baseMAtk           = entry.baseMAtk;
		basePDef           = entry.basePDef;
		baseMDef           = entry.baseMDef;
		basePAtkSpd        = entry.basePAtkSpd;
		baseMAtkSpd        = entry.baseMAtkSpd;
		baseMReuseRate     = 1f;
		baseShldDef        = 0;
		baseAtkRange       = entry.baseAtkRange;
		baseShldRate       = 0;
		baseCritRate       = 38;
		baseAttackCancel   = 50; // 50% to break ATTACK
		baseRunSpd         = entry.baseRunSpd;
		// SpecialStats
		baseBreath         = 100;
		baseAggression     = 0;
		baseBleed          = 0;
		basePoison         = 0;
		baseStun           = 0;
		baseRoot           = 0;
		baseMovement       = 0;
		baseConfusion      = 0;
		baseSleep          = 0;
		baseFire           = 0;
		baseWind           = 0;
		baseWater          = 0;
		baseEarth          = 0;
		baseHoly           = 0;
		baseDark           = 0;
		baseAggressionRes  = 0;
		baseBleedRes       = 0;
		basePoisonRes      = 0;
		baseStunRes        = 0;
		baseRootRes        = 0;
		baseMovementRes    = 0;
		baseConfusionRes   = 0;
		baseSleepRes       = 0;
		baseFireRes        = 0;
		baseWindRes        = 0;
		baseWaterRes       = 0;
		baseEarthRes       = 0;
		baseHolyRes        = 0;
		baseDarkRes        = 0;
        
        //C4 Stats
        baseMpConsumeRate      = 0;
        baseHpConsumeRate      = 0;
		
		// Geometry
		collisionRadius    = entry.collisionRadius;
		collisionHeight    = entry.collisionHeight;	
	}
}
