package net.sf.l2j.gameserver;

import java.util.Map;
import java.util.logging.Logger;

import la2.world.model.data.xml.XmlLoader;
import la2.world.model.data.xml.XmlTerritory;
import net.sf.l2j.gameserver.model.L2Territory;

public class Territory {
	private static Logger _log = Logger.getLogger(TradeController.class.getName());

	private static Map<Integer,L2Territory> _territory;
	
	public static Territory getInstance() {
		return SingletonHolder.instance;
	}
	
	private Territory()	{
		int count[] = {0};
		XmlLoader.load("territory.xml").list.stream().map(XmlTerritory.class::cast).forEach(entry -> {
			final L2Territory t = new L2Territory(entry.id);
			entry.points.forEach(point -> {
				t.add(point.x, point.y, point.zMin, point.zMax, point.proc);
			});
			_territory.put(entry.id, t);
			count[0]++;
		});
		_log.info("Territory: loaded " + count[0] + " count.");
	}

	public int[] getRandomPoint(int terr) {
		return _territory.get(terr).getRandomPoint();
	}

	public int getProcMax(int terr) {
		return _territory.get(terr).getProcMax();
	}
	
	private static class SingletonHolder {
		private static final Territory instance = new Territory();
	}
}
