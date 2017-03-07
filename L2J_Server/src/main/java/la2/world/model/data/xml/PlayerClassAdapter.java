package la2.world.model.data.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import la2.world.model.creature.PlayerClass;

public class PlayerClassAdapter extends XmlAdapter<Integer, PlayerClass> {

	@Override
	public PlayerClass unmarshal(Integer id) throws Exception {
		return PlayerClass.find(id);
	}

	@Override
	public Integer marshal(PlayerClass pClass) throws Exception {
		return pClass.id;
	}
	
}
