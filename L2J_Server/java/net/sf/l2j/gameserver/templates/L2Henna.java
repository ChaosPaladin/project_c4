package net.sf.l2j.gameserver.templates;

import la2.world.model.data.xml.XmlEntry;
import la2.world.model.data.xml.XmlHenna;

public class L2Henna {
	public final int symbol_id;
	public final String symbol_name;
	public final int dye;
	public final int price;
	public final int amount;
	public final int stat_INT;
	public final int stat_STR;
	public final int stat_CON;
	public final int stat_MEN;
	public final int stat_DEX;
	public final int stat_WIT;
	
	public L2Henna(XmlEntry entry) {
		this((XmlHenna) entry);
	}
	
	public L2Henna(XmlHenna entry) {
		symbol_id = entry.id;
		symbol_name = entry.name;
		dye = entry.dyeId;
		price = entry.price;
		amount = entry.dyeAmount;
		stat_INT = entry.statINT;
		stat_STR = entry.statSTR;
		stat_CON = entry.statCON;
		stat_MEN = entry.statMEN;
		stat_DEX = entry.statDEX;
		stat_WIT = entry.statWIT;
	}
	
	public int getSymbolId()
	{
		return symbol_id;
	}
	/**
	 * @return
	 */
	public int getDyeId()
	{
		return dye;
	}
	/**
	 * @return
	 */
	public int getPrice()
	{
		return price;
	}
	/**
	 * @return
	 */
	public int getAmountDyeRequire()
	{
		return amount;
	}
	/**
	 * @return
	 */
	public int getStatINT()
	{
		return stat_INT;
	}
	/**
	 * @return
	 */
	public int getStatSTR()
	{
		return stat_STR;
	}
	/**
	 * @return
	 */
	public int getStatCON()
	{
		return stat_CON;
	}
	/**
	 * @return
	 */
	public int getStatMEM()
	{
		return stat_MEN;
	}
	/**
	 * @return
	 */
	public int getStatDEX()
	{
		return stat_DEX;
	}
	/**
	 * @return
	 */
	public int getStatWIT()
	{
		return stat_WIT;
	}
	/**
	 * @return
	 */
}
