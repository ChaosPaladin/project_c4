package la2.world.model.creature;

public enum PlayerClass {
	fighter(0),
	warrior(1, fighter),
	gladiator(2, warrior),
	warlord(3, warrior),
	knight(4, fighter),
	paladin(5, knight),
	dark_avenger(6, knight),
	rogue(7, fighter),
	treasure_hunter(8, rogue),
	hawkeye(9, rogue),
	mage(10),
	wizard(11, mage),
	sorcerer(12, wizard),
	necromancer(13, wizard),
	warlock(14, wizard),
	cleric(15, mage),
	bishop(16, cleric),
	prophet(17, cleric),
	elven_fighter(18),
	elven_knight(19, elven_fighter),
	temple_knight(20, elven_knight),
	swordsinger(21, elven_knight),
	elven_scout(22, elven_fighter),
	plains_walker(23, elven_scout),
	silver_ranger(24, elven_scout),
	elven_mage(25),
	elven_wizard(26, elven_mage),
	spellsinger(27, elven_wizard),
	elemental_summoner(28, elven_wizard),
	oracle(29, elven_mage),
	elder(30, oracle),
	dark_fighter(31),
	palus_knight(32, dark_fighter),
	shillien_knight(33, palus_knight),
	bladedancer(34, palus_knight),
	assassin(35, dark_fighter),
	abyss_walker(36, assassin),
	phantom_ranger(37, assassin),
	dark_mage(38),
	dark_wizard(39, dark_mage),
	spellhowler(40, dark_wizard),
	phantom_summoner(41, dark_wizard),
	shillien_oracle(42, dark_mage),
	shillien_elder(43, shillien_oracle),
	orc_fighter(44),
	orc_raider(45, orc_fighter),
	destroyer(46, orc_raider),
	orc_monk(47, orc_fighter),
	tyrant(48, orc_monk),
	orc_mage(49),
	orc_shaman(50, orc_mage),
	overlord(51, orc_shaman),
	warcryer(52, orc_shaman),
	dwarven_fighter(53),
	scavenger(54, dwarven_fighter),
	bounty_hunter(55, scavenger),
	artisan(56, dwarven_fighter),
	warsmith(57, artisan),
	//TODO make dummy????
	//3d class
	duelist(88, gladiator),
	dreadnought(89, warlord),
	phoenix_knight(90, paladin),
	hell_knight(91, dark_avenger),
	sagittarius(92, hawkeye),
	adventurer(93, treasure_hunter),
	archmage(94, sorcerer),
	soultaker(95, necromancer),
	arcana_lord(96, warlock),
	cardinal(97, bishop),
	hierophant(98, prophet),
	evas_templar(99, temple_knight),
	sword_muse(100, swordsinger),
	wind_rider(101, plains_walker),
	moonlight_sentinel(102, silver_ranger),
	mystic_muse(103, spellsinger),
	elemental_master(104, elemental_summoner),
	evas_saint(105, elder),
	shillien_templar(106, shillien_knight),
	spectral_dancer(107, bladedancer),
	ghost_hunter(108, abyss_walker),
	ghost_sentinel(109, phantom_ranger),
	storm_screamer(110, spellhowler),
	spectral_master(111, phantom_summoner),
	shillien_saint(112, shillien_elder),
	titan(113, destroyer),
	grand_khavatari(114, tyrant),
	dominator(115, overlord),
	doomcryer(116, warcryer),
	fortune_seeker(117, bounty_hunter),
	maestro(118, warsmith);
	
	public final PlayerClass parent;
	
	public final int id;
	
	private PlayerClass(int id, PlayerClass parent) {
		this.id = id;
		this.parent = parent;
	}
	private PlayerClass(int id) {
		this(id, null);
	}
	
	public static final int MAX_ID = 118;

	public static final int ARRAY_SIZE = MAX_ID + 1;
	
	public static PlayerClass find(int id) {
		for(PlayerClass pClass : values())
			if(pClass.id == id)
				return pClass;
		return null;
	}
}
