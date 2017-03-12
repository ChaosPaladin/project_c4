package net.sf.l2j.gameserver.model.quest;

import java.util.logging.Logger;

import net.sf.l2j.gameserver.model.actor.instance.L2NpcInstance;

public final class Q001_LettersOfLove extends Quest {
	private static final Logger log = Logger.getLogger(Q001_LettersOfLove.class.getName());
	
	private final State CREATED = new State("Start", this);
	private final State STARTING = new State("Starting", this);
	private final State STARTED = new State("Started", this);
	private final State COMPLETED = new State("Completed", this);
	
	Q001_LettersOfLove() {
		super(1, "1_LettersOfLove1", "Letters of Lover");
		setInitialState(CREATED);
		addStartNpc(DARIN);
		STARTING.addTalkId(DARIN);
		STARTED.addTalkId(DARIN);
		STARTED.addTalkId(ROXXY);
		STARTED.addTalkId(BAULRO);
		
		STARTED.addQuestDrop(DARIN, DARINGS_LETTER, 1);
		STARTED.addQuestDrop(DARIN, RAPUNZELS_KERCHIEF, 1);
		STARTED.addQuestDrop(DARIN, DARINGS_RECEIPT, 1); 
		STARTED.addQuestDrop(DARIN, BAULS_POTION, 1);
		log.info("importing quests: 1: Letters Of Love");
	}
	
	@Override
	public String onEvent(String event, QuestState st) {
		String htmltext = event;
		if(event.equals("7048-06.htm")) {
			st.set("cond", "1");
			st.set("id", "1");
			st.setState(STARTED);
			st.playSound("ItemSound.quest_accept");
			if(st.getQuestItemsCount(DARINGS_LETTER) == 0)
				st.giveItems(DARINGS_LETTER, 1);
		}
		return htmltext;
    }
	
	@Override
	public String onTalk(L2NpcInstance npc, QuestState st) {
		String htmltext = "<html><head><body>I have nothing to say you</body></html>";
		int npcId = npc.getNpcId();
		State id = st.getState();
		if(id == CREATED) {
			st.setState(STARTING);
			st.set("cond", "0");
			st.set("onlyone", "0");
			st.set("id", "0");
		}
		int cond = st.getInt("cond");
		int onlyone = st.getInt("onlyone");
		int ItemsCount_DL = st.getQuestItemsCount(DARINGS_LETTER);
		int ItemsCount_RK = st.getQuestItemsCount(RAPUNZELS_KERCHIEF);
		int ItemsCount_DR = st.getQuestItemsCount(DARINGS_RECEIPT);
		int ItemsCount_BP = st.getQuestItemsCount(BAULS_POTION);
		if(npcId == DARIN && cond == 0 && onlyone == 0) {
			if(st.getPlayer().getLevel() >= 2) {
				//???
				if(cond < 15) {
					htmltext = "7048-02.htm";
				} else {
					htmltext = "7048-01.htm";
					st.exitQuest(true);
				}
			} else {
				htmltext = "<html><head><body>Quest for characters level 2 and above.</body></html>";
				st.exitQuest(true);
			}
		} else if(npcId == DARIN && cond == 0 && onlyone == 1) {
			htmltext = "<html><head><body>This quest have already been completed.</body></html>";
		} else if(npcId == ROXXY && cond > 0 && onlyone == 0) {
			if(ItemsCount_RK == 0 && ItemsCount_DL > 0) {
		       htmltext = "7006-01.htm";
		       st.takeItems(DARINGS_LETTER,-1);
			   st.giveItems(RAPUNZELS_KERCHIEF,1);
			   st.set("cond","2");
			   st.set("id","2");
			   st.playSound("ItemSound.quest_middle"); 	
			} else if (ItemsCount_BP > 0 || ItemsCount_DR > 0) {
				htmltext = "7006-03.htm";
			} else if (ItemsCount_RK > 0) {
				htmltext = "7006-02.htm";
			}
		} else if(npcId == DARIN && cond > 0 && ItemsCount_RK > 0 && onlyone == 0) {
		     htmltext = "7048-08.htm";
    	     st.takeItems(RAPUNZELS_KERCHIEF, -1);
    	     st.giveItems(DARINGS_RECEIPT, 1);
    	     st.set("cond", "3");
    	     st.set("id", "3");
    	     st.playSound("ItemSound.quest_middle");			
		} else if(npcId == BAULRO && cond > 0 && onlyone == 0) {
			if(ItemsCount_DR > 0) {
				htmltext = "7033-01.htm";
				st.takeItems(DARINGS_RECEIPT,-1);
				st.giveItems(BAULS_POTION,1);
				st.set("cond","4");
				st.set("id","4");
				st.playSound("ItemSound.quest_middle");
			} else if(ItemsCount_BP > 0) {
				htmltext = "7033-02.htm";
			}
		} else if(npcId == DARIN && cond > 0 && ItemsCount_RK == 0 && onlyone == 0) {
			if(ItemsCount_DR > 0) {
				htmltext = "7048-10.htm";
			} else if(ItemsCount_BP > 0) {
				htmltext = "7048-10.htm";
				st.takeItems(BAULS_POTION,-1);
			   	st.giveItems(NECKLACE,1);
			   	st.set("cond","0");
			   	st.set("onlyone","1");
			   	st.setState(COMPLETED);
			   	st.playSound("ItemSound.quest_finish");
			} else 
				htmltext = "7048-07.htm";
		}
		return htmltext;
	}
	
	//NPCs 
	private static final int DARIN  = 7048;
	private static final int ROXXY  = 7006;
	private static final int BAULRO = 7033;
	//ITEMS 
	private static final int DARINGS_LETTER     = 687;
	private static final int RAPUNZELS_KERCHIEF = 688;
	private static final int DARINGS_RECEIPT    = 1079;
	private static final int BAULS_POTION       = 1080;
	//REWARD 
	private static final int NECKLACE = 908;
}
