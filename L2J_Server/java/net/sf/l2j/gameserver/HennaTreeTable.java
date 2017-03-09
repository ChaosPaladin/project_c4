package net.sf.l2j.gameserver;

import java.util.Arrays;
import java.util.logging.Logger;

import la2.world.model.Utils;
import la2.world.model.creature.PlayerClass;
import la2.world.model.data.xml.XmlHennaTree;
import la2.world.model.data.xml.XmlLoader;
import net.sf.l2j.gameserver.model.L2HennaInstance;
import net.sf.l2j.gameserver.model.base.ClassId;
import net.sf.l2j.gameserver.templates.L2Henna;

public class HennaTreeTable {
	private static Logger _log = Logger.getLogger(HennaTreeTable.class.getName());
	
	private L2HennaInstance[][] tree = new L2HennaInstance[PlayerClass.ARRAY_SIZE][];
	
	public static HennaTreeTable getInstance() {
		return SingletonHolder.instance;
	}
	
	private HennaTreeTable() {
		final int[] count = {0};
		//set null tree values for all classes
		Arrays.fill(tree, new L2HennaInstance[0]);
		XmlLoader.load("henna-tree.xml").list.stream().map(XmlHennaTree.class::cast).forEach(hTree -> {
			if(tree[hTree.pClass.id].length > 0) {
				_log.warning("try define second time henna tree for class " + hTree);
				return;
			}
			tree[hTree.pClass.id] = new L2HennaInstance[hTree.symbols.length];
			for(int i = 0 ; i < hTree.symbols.length ; i++) {
				final L2Henna tmp = HennaTable.getInstance().getTemplate(hTree.symbols[i]);
				if(tmp != null) {
					count[0]++;
					tree[hTree.pClass.id][i] = new L2HennaInstance(tmp);
				} else
					_log.warning("not found henna symbols with id " + hTree.symbols[i] + " in tree for class " + hTree.pClass);
			}
		});
        _log.config("HennaTreeTable: Loaded " + count[0] + " Henna Tree Templates.");
	}
	
	
	
	public L2HennaInstance[] getAvailableHenna(PlayerClass pClass) {
		return Arrays.copyOf(tree[pClass.id], tree[pClass.id].length);
	}
	
	public L2HennaInstance[] getAvailableHenna(ClassId classId) {
		return getAvailableHenna(Utils.map(classId));
	}
	
	private static final class SingletonHolder {
		private static final HennaTreeTable instance = new HennaTreeTable();
	}
}
