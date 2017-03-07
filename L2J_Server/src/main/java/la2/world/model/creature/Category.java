package la2.world.model.creature;

import java.util.Arrays;

public class Category {
	public final static int fighter_group = 0;
	public final static int mage_group = 1;
	public final static int wizard_group = 2;
	public final static int cleric_group = 3;
	public final static int attacker_group = 4;
	public final static int first_class_group = 5;
	public final static int second_class_group = 6;
	public final static int third_class_group = 7;
	public final static int fourth_class_group = 8;
	public final static int bounty_hunter_group = 9;
	public final static int warsmith_group = 10;
	public final static int summon_npc_group = 11;
	public final static int knight_group = 12;
	public final static int white_magic_group = 13;
	public final static int heal_group = 14;
	public final static int assist_magic_group = 15;
	public final static int warrior_group = 16;
	public final static int human_2nd_group = 17;
	public final static int elf_2nd_group = 18;
	public final static int delf_2nd_group = 19;
	public final static int orc_2nd_group = 20;
	public final static int dwarf_2nd_group = 21;
	public final static int strider = 22;
	public final static int pet_group = 23;
	public final static int subjob_group_dagger = 24;
	public final static int subjob_group_bow = 25;
	public final static int subjob_group_knight = 26;
	public final static int subjob_group_summoner = 27;
	public final static int subjob_group_half_healer = 28;
	public final static int subjob_group_dance = 29;
	public final static int subjob_group_wizard = 30;
	public final static int human_fall_class = 31;
	public final static int human_wall_class = 32;
	public final static int human_mall_class = 33;
	public final static int human_call_class = 34;
	public final static int elf_fall_class = 35;
	public final static int elf_mall_class = 36;
	public final static int elf_wall_class = 37;
	public final static int elf_call_class = 38;
	public final static int delf_fall_class = 39;
	public final static int delf_mall_class = 40;
	public final static int delf_wall_class = 41;
	public final static int delf_call_class = 42;
	public final static int orc_fall_class = 43;
	public final static int orc_mall_class = 44;
	public final static int dwarf_all_class = 45;
	public final static int dwarf_bounty_class = 46;
	public final static int dwarf_smith_class = 47;

	//category mask
	private final static boolean[][] category = new boolean[47][]; 
	
	static {
		for(int i = 0 ; i < 47 ; i++)
			category[i] = new boolean[118];
		
		set(fighter_group
			, PlayerClass.fighter, PlayerClass.warrior, PlayerClass.gladiator, PlayerClass.warlord, PlayerClass.knight, PlayerClass.paladin, PlayerClass.dark_avenger, PlayerClass.rogue
			, PlayerClass.treasure_hunter, PlayerClass.hawkeye, PlayerClass.elven_fighter, PlayerClass.elven_knight, PlayerClass.temple_knight, PlayerClass.swordsinger
			, PlayerClass.elven_scout, PlayerClass.plains_walker, PlayerClass.silver_ranger, PlayerClass.dark_fighter, PlayerClass.palus_knight
			, PlayerClass.shillien_knight, PlayerClass.bladedancer, PlayerClass.assassin, PlayerClass.abyss_walker, PlayerClass.phantom_ranger
			, PlayerClass.orc_fighter, PlayerClass.orc_raider, PlayerClass.destroyer, PlayerClass.orc_monk, PlayerClass.tyrant, PlayerClass.dwarven_fighter
			, PlayerClass.scavenger, PlayerClass.bounty_hunter, PlayerClass.artisan, PlayerClass.warsmith, PlayerClass.duelist, PlayerClass.dreadnought
			, PlayerClass.phoenix_knight, PlayerClass.hell_knight, PlayerClass.sagittarius, PlayerClass.adventurer
			, PlayerClass.evas_templar, PlayerClass.sword_muse, PlayerClass.wind_rider, PlayerClass.moonlight_sentinel
			, PlayerClass.shillien_templar, PlayerClass.spectral_dancer, PlayerClass.ghost_hunter, PlayerClass.ghost_sentinel
			, PlayerClass.titan, PlayerClass.grand_khavatari, PlayerClass.fortune_seeker, PlayerClass.maestro
		);
		
		set(mage_group
			, PlayerClass.mage, PlayerClass.wizard, PlayerClass.sorcerer, PlayerClass.necromancer, PlayerClass.warlock, PlayerClass.cleric, PlayerClass.bishop, PlayerClass.prophet
			, PlayerClass.elven_mage, PlayerClass.elven_wizard, PlayerClass.spellsinger, PlayerClass.elemental_summoner, PlayerClass.oracle, PlayerClass.elder
			, PlayerClass.dark_mage, PlayerClass.dark_wizard, PlayerClass.spellhowler, PlayerClass.phantom_summoner, PlayerClass.shillien_oracle
			, PlayerClass.shillien_elder, PlayerClass.orc_mage, PlayerClass.orc_shaman, PlayerClass.overlord, PlayerClass.warcryer
			, PlayerClass.archmage, PlayerClass.soultaker, PlayerClass.arcana_lord, PlayerClass.cardinal, PlayerClass.hierophant, PlayerClass.mystic_muse, PlayerClass.elemental_master, PlayerClass.evas_saint
			, PlayerClass.storm_screamer, PlayerClass.spectral_master, PlayerClass.shillien_saint, PlayerClass.dominator, PlayerClass.doomcryer				
		);
		
		set(wizard_group
			, PlayerClass.mage, PlayerClass.wizard, PlayerClass.sorcerer, PlayerClass.necromancer, PlayerClass.warlock
			, PlayerClass.elven_mage, PlayerClass.elven_wizard, PlayerClass.spellsinger, PlayerClass.elemental_summoner
			, PlayerClass.dark_mage, PlayerClass.dark_wizard, PlayerClass.spellhowler, PlayerClass.phantom_summoner
			, PlayerClass.archmage, PlayerClass.soultaker, PlayerClass.arcana_lord, PlayerClass.mystic_muse, PlayerClass.elemental_master, PlayerClass.storm_screamer, PlayerClass.spectral_master		
		);
		
		set(cleric_group
			, PlayerClass.mage, PlayerClass.cleric, PlayerClass.bishop, PlayerClass.prophet, PlayerClass.elven_mage, PlayerClass.oracle, PlayerClass.elder
			, PlayerClass.dark_mage, PlayerClass.shillien_oracle, PlayerClass.shillien_elder, PlayerClass.cardinal, PlayerClass.hierophant, PlayerClass.evas_saint				
		);
		
		set(attacker_group
			, PlayerClass.fighter, PlayerClass.warrior, PlayerClass.gladiator, PlayerClass.warlord, PlayerClass.rogue
			, PlayerClass.treasure_hunter, PlayerClass.hawkeye, PlayerClass.elven_fighter, PlayerClass.elven_scout, PlayerClass.plains_walker
			, PlayerClass.silver_ranger
			, PlayerClass.dark_fighter, PlayerClass.assassin, PlayerClass.abyss_walker, PlayerClass.phantom_ranger
			, PlayerClass.orc_fighter, PlayerClass.orc_raider, PlayerClass.destroyer, PlayerClass.orc_monk, PlayerClass.tyrant, PlayerClass.dwarven_fighter
			, PlayerClass.duelist, PlayerClass.dreadnought, PlayerClass.adventurer, PlayerClass.sagittarius, PlayerClass.moonlight_sentinel, PlayerClass.wind_rider
			, PlayerClass.ghost_hunter, PlayerClass.ghost_sentinel, PlayerClass.titan, PlayerClass.grand_khavatari				
		);
		
		set(first_class_group
			, PlayerClass.fighter, PlayerClass.mage, PlayerClass.elven_fighter, PlayerClass.elven_mage, PlayerClass.dark_fighter, PlayerClass.dark_mage
			, PlayerClass.orc_fighter, PlayerClass.orc_mage, PlayerClass.dwarven_fighter				
		);
		
		set(second_class_group
			, PlayerClass.warrior, PlayerClass.knight, PlayerClass.rogue, PlayerClass.wizard, PlayerClass.cleric, PlayerClass.elven_knight, PlayerClass.elven_scout, PlayerClass.elven_wizard
			, PlayerClass.oracle, PlayerClass.palus_knight, PlayerClass.assassin, PlayerClass.dark_wizard, PlayerClass.shillien_oracle, PlayerClass.orc_raider
			, PlayerClass.orc_monk, PlayerClass.orc_shaman, PlayerClass.scavenger, PlayerClass.artisan				
		);
		
		set(third_class_group
			, PlayerClass.gladiator, PlayerClass.warlord, PlayerClass.paladin, PlayerClass.dark_avenger, PlayerClass.treasure_hunter, PlayerClass.hawkeye, PlayerClass.sorcerer
			, PlayerClass.necromancer, PlayerClass.warlock, PlayerClass.bishop, PlayerClass.prophet, PlayerClass.temple_knight, PlayerClass.swordsinger, PlayerClass.plains_walker
			, PlayerClass.silver_ranger, PlayerClass.spellsinger, PlayerClass.elemental_summoner, PlayerClass.elder, PlayerClass.shillien_knight
			, PlayerClass.bladedancer, PlayerClass.abyss_walker, PlayerClass.phantom_ranger, PlayerClass.spellhowler, PlayerClass.phantom_summoner
			, PlayerClass.shillien_elder, PlayerClass.destroyer, PlayerClass.tyrant, PlayerClass.overlord, PlayerClass.warcryer, PlayerClass.bounty_hunter, PlayerClass.warsmith		
		);
		
		set(fourth_class_group
			, PlayerClass.duelist, PlayerClass.dreadnought, PlayerClass.phoenix_knight, PlayerClass.hell_knight, PlayerClass.sagittarius, PlayerClass.adventurer
			, PlayerClass.archmage, PlayerClass.soultaker, PlayerClass.arcana_lord, PlayerClass.cardinal, PlayerClass.hierophant, PlayerClass.evas_templar, PlayerClass.sword_muse
			, PlayerClass.wind_rider, PlayerClass.moonlight_sentinel, PlayerClass.mystic_muse, PlayerClass.elemental_master, PlayerClass.evas_saint
			, PlayerClass.shillien_templar, PlayerClass.spectral_dancer, PlayerClass.ghost_hunter, PlayerClass.ghost_sentinel, PlayerClass.storm_screamer
			, PlayerClass.spectral_master, PlayerClass.shillien_saint, PlayerClass.titan, PlayerClass.grand_khavatari, PlayerClass.dominator, PlayerClass.doomcryer, PlayerClass.fortune_seeker, PlayerClass.maestro	
		);
		
		set(bounty_hunter_group
			, PlayerClass.dwarven_fighter, PlayerClass.scavenger, PlayerClass.bounty_hunter, PlayerClass.fortune_seeker	
		);
		
		set(warsmith_group
			, PlayerClass.dwarven_fighter, PlayerClass.artisan, PlayerClass.warsmith, PlayerClass.maestro		
		);
		
		set(knight_group
			, PlayerClass.knight, PlayerClass.elven_knight, PlayerClass.palus_knight	
		);
		
		set(white_magic_group
			, PlayerClass.cleric, PlayerClass.oracle, PlayerClass.paladin, PlayerClass.elven_knight, PlayerClass.knight	
		);
		
		set(heal_group
			, PlayerClass.cleric, PlayerClass.oracle, PlayerClass.shillien_oracle, PlayerClass.orc_shaman	
		);
		
		set(assist_magic_group
			, PlayerClass.cleric, PlayerClass.shillien_oracle		
		);
		
		set(warrior_group
			, PlayerClass.warrior, PlayerClass.elven_knight, PlayerClass.palus_knight, PlayerClass.orc_raider, PlayerClass.orc_monk	
		);
		
		set(human_2nd_group
			,PlayerClass.warrior, PlayerClass.knight, PlayerClass.rogue, PlayerClass.wizard, PlayerClass.cleric	
		);
		
		set(elf_2nd_group
			, PlayerClass.elven_scout, PlayerClass.elven_knight, PlayerClass.elven_wizard, PlayerClass.oracle	
		);
		
		set(delf_2nd_group
			, PlayerClass.assassin, PlayerClass.palus_knight, PlayerClass.dark_wizard, PlayerClass.shillien_oracle		
		);
		
		set(orc_2nd_group
			, PlayerClass.orc_shaman, PlayerClass.orc_raider, PlayerClass.orc_monk
		);	
		
		set(dwarf_2nd_group
			, PlayerClass.scavenger, PlayerClass.artisan
		);
		
		set(subjob_group_dagger
			, PlayerClass.treasure_hunter, PlayerClass.plains_walker, PlayerClass.abyss_walker, PlayerClass.adventurer, PlayerClass.wind_rider, PlayerClass.ghost_hunter	
		);
		
		set(subjob_group_bow
			, PlayerClass.hawkeye, PlayerClass.silver_ranger, PlayerClass.phantom_ranger, PlayerClass.sagittarius, PlayerClass.moonlight_sentinel, PlayerClass.ghost_sentinel
		);
		
		set(subjob_group_knight
			, PlayerClass.paladin, PlayerClass.dark_avenger, PlayerClass.shillien_knight, PlayerClass.temple_knight, PlayerClass.phoenix_knight, PlayerClass.hell_knight, PlayerClass.shillien_templar, PlayerClass.evas_templar
		);
		
		set(subjob_group_summoner
			, PlayerClass.warlock, PlayerClass.elemental_summoner, PlayerClass.phantom_summoner, PlayerClass.arcana_lord, PlayerClass.elemental_master, PlayerClass.spectral_master
		);
		
		set(subjob_group_half_healer
			, PlayerClass.elder, PlayerClass.shillien_elder, PlayerClass.evas_saint, PlayerClass.shillien_saint
		);
		
		set(subjob_group_dance
			, PlayerClass.swordsinger, PlayerClass.bladedancer, PlayerClass.sword_muse, PlayerClass.spectral_dancer
		);
		
		set(subjob_group_wizard
			, PlayerClass.sorcerer, PlayerClass.spellsinger, PlayerClass.spellhowler, PlayerClass.archmage, PlayerClass.mystic_muse, PlayerClass.storm_screamer
		);
		
		set(human_fall_class
			, PlayerClass.fighter, PlayerClass.warrior, PlayerClass.knight, PlayerClass.rogue, PlayerClass.gladiator, PlayerClass.warlord, PlayerClass.paladin, PlayerClass.dark_avenger, PlayerClass.treasure_hunter, PlayerClass.hawkeye
			, PlayerClass.duelist, PlayerClass.dreadnought, PlayerClass.phoenix_knight, PlayerClass.hell_knight, PlayerClass.sagittarius, PlayerClass.adventurer
		);
		
		set(human_wall_class
			, PlayerClass.mage, PlayerClass.wizard, PlayerClass.sorcerer, PlayerClass.necromancer, PlayerClass.warlock, PlayerClass.archmage, PlayerClass.soultaker, PlayerClass.arcana_lord
		);
		
		set(human_mall_class
			, PlayerClass.mage, PlayerClass.wizard, PlayerClass.sorcerer, PlayerClass.necromancer, PlayerClass.warlock, PlayerClass.mage, PlayerClass.cleric, PlayerClass.bishop, PlayerClass.prophet, PlayerClass.soultaker, PlayerClass.arcana_lord, PlayerClass.cardinal, PlayerClass.hierophant
		);
		
		set(human_call_class
			, PlayerClass.mage, PlayerClass.cleric, PlayerClass.bishop, PlayerClass.prophet, PlayerClass.cardinal, PlayerClass.hierophant
		);
		
		set(elf_fall_class
			, PlayerClass.elven_fighter, PlayerClass.elven_knight, PlayerClass.elven_scout, PlayerClass.temple_knight, PlayerClass.swordsinger, PlayerClass.plains_walker, PlayerClass.silver_ranger
			, PlayerClass.evas_templar, PlayerClass.sword_muse, PlayerClass.wind_rider, PlayerClass.moonlight_sentinel
		);
		
		set(elf_mall_class
			, PlayerClass.elven_mage, PlayerClass.elven_wizard, PlayerClass.spellsinger, PlayerClass.elemental_summoner, PlayerClass.elven_mage, PlayerClass.oracle, PlayerClass.elder, PlayerClass.mystic_muse, PlayerClass.elemental_master, PlayerClass.evas_saint
		);
		
		set(elf_wall_class
			, PlayerClass.elven_mage, PlayerClass.elven_wizard, PlayerClass.spellsinger, PlayerClass.elemental_summoner, PlayerClass.mystic_muse, PlayerClass.elemental_master
		);
		
		set(elf_call_class
			, PlayerClass.elven_mage, PlayerClass.oracle, PlayerClass.elder, PlayerClass.evas_saint
		);
		
		set(delf_fall_class
			, PlayerClass.dark_fighter, PlayerClass.palus_knight, PlayerClass.assassin, PlayerClass.shillien_knight, PlayerClass.bladedancer, PlayerClass.abyss_walker, PlayerClass.phantom_ranger
			, PlayerClass.shillien_templar, PlayerClass.spectral_dancer, PlayerClass.ghost_hunter, PlayerClass.ghost_sentinel
		);
		
		set(delf_mall_class
			, PlayerClass.dark_mage, PlayerClass.dark_wizard, PlayerClass.shillien_oracle, PlayerClass.spellhowler, PlayerClass.phantom_summoner, PlayerClass.shillien_elder, PlayerClass.storm_screamer, PlayerClass.spectral_master, PlayerClass.shillien_saint
		);
		
		set(delf_wall_class
			, PlayerClass.dark_mage, PlayerClass.dark_wizard, PlayerClass.spellhowler, PlayerClass.phantom_summoner, PlayerClass.storm_screamer, PlayerClass.spectral_master
		);
		
		set(delf_call_class
			, PlayerClass.dark_mage, PlayerClass.shillien_oracle, PlayerClass.shillien_elder, PlayerClass.shillien_saint
		);
		
		set(orc_fall_class
			, PlayerClass.orc_fighter, PlayerClass.orc_raider, PlayerClass.orc_monk, PlayerClass.destroyer, PlayerClass.tyrant, PlayerClass.titan, PlayerClass.grand_khavatari
		);
		
		set(orc_mall_class
			, PlayerClass.orc_mage, PlayerClass.orc_shaman, PlayerClass.overlord, PlayerClass.warcryer, PlayerClass.dominator, PlayerClass.doomcryer
		);
		
		set(dwarf_all_class
			, PlayerClass.dwarven_fighter, PlayerClass.scavenger, PlayerClass.artisan, PlayerClass.bounty_hunter, PlayerClass.warsmith, PlayerClass.fortune_seeker, PlayerClass.maestro
		);
		
		set(dwarf_bounty_class
			, PlayerClass.dwarven_fighter, PlayerClass.scavenger, PlayerClass.bounty_hunter, PlayerClass.fortune_seeker
		);
		
		set(dwarf_smith_class
			, PlayerClass.dwarven_fighter, PlayerClass.artisan, PlayerClass.warsmith, PlayerClass.maestro
		);
	}
	
	private static void set(int group, PlayerClass...classList) {
		Arrays.fill(category[group], false);
		for(PlayerClass pc : classList)
			category[group][pc.id] = true;
	}
	
	public static boolean is(PlayerClass pc, int id) {
		if(id < 0 || id > 47)
			return false;
		return category[id][pc.id];
	}
}
