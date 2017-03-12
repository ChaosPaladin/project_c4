package net.sf.l2j.gameserver.model.quest;

import java.util.logging.Logger;

import net.sf.l2j.gameserver.model.actor.instance.L2NpcInstance;

public final class Q002_WhatWomenWant extends Quest {
	private static final Logger log = Logger.getLogger(Q002_WhatWomenWant.class.getName());

	private final State CREATED   = new State("Start",     this);
	private final State STARTED   = new State("Started",   this);
	private final State COMPLETED = new State("Completed", this); 
	
	Q002_WhatWomenWant() {
		super(2, "2_WhatWomenWant1", "What Women Want");
		setInitialState(CREATED);
		addStartNpc(ARUJIEN);
		CREATED.addTalkId(ARUJIEN);
		COMPLETED.addTalkId(ARUJIEN);

		STARTED.addTalkId(MIRABEL);
		STARTED.addTalkId(HERBIEL);
		STARTED.addTalkId(GREENIS);
		STARTED.addTalkId(ARUJIEN);

		STARTED.addQuestDrop(ARUJIEN,GREENIS_LETTER,1);
		STARTED.addQuestDrop(ARUJIEN,ARUJIENS_LETTER3,1); 
		STARTED.addQuestDrop(ARUJIEN,ARUJIENS_LETTER1,1); 
		STARTED.addQuestDrop(ARUJIEN,ARUJIENS_LETTER2,1); 
		STARTED.addQuestDrop(ARUJIEN,POETRY_BOOK,1);
		
		log.info("importing quests: 2: What Women Want" );
	}
	
	@Override
	public String onEvent(String event, QuestState st) {
		String htmltext = event;
		if(event.equals("7223-04.htm")) {
			if(st.getQuestItemsCount(ARUJIENS_LETTER1) == 0 && st.getQuestItemsCount(ARUJIENS_LETTER2) == 0 && st.getQuestItemsCount(ARUJIENS_LETTER3) == 0)
				st.giveItems(ARUJIENS_LETTER1, 1);
		     st.set("cond","1");
		     st.set("id","1");
		     st.setState(STARTED);
		     st.playSound("ItemSound.quest_accept");
		} else if(event.equals("7223-08.htm")) {
		     st.takeItems(ARUJIENS_LETTER3,-1); 
		     st.giveItems(POETRY_BOOK,1); 
		     st.set("cond","4"); 
		     st.set("id","4");
		     st.playSound("ItemSound.quest_middle");	
		} else if(event.equals("7223-10.htm")) {
		     st.takeItems(ARUJIENS_LETTER3,-1);
		     st.giveItems(ADENA,450);
		     st.set("cond","0");
		     st.setState(COMPLETED);
		     st.playSound("ItemSound.quest_finish");
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(L2NpcInstance npc, QuestState st) {
		String htmltext = "<html><head><body>I have nothing to say you</body></html>";
		int npcId = npc.getNpcId();
		State id = st.getState();
		if(id == CREATED) {
			st.set("cond", "0");
			st.set("id", "0");
		}
		int cond = st.getInt("cond");
		if(npcId == ARUJIEN && id== CREATED) {
			if(st.getPlayer().getRace().ordinal() != 1 && st.getPlayer().getRace().ordinal() != 0) {
				htmltext = "7223-00.htm";
			} else if(st.getPlayer().getLevel() >= 2) {
				htmltext = "7223-02.htm";
			} else {
				htmltext = "7223-01.htm"; 
				st.exitQuest(true);
			}
		} else if(npcId == ARUJIEN && id == COMPLETED) {
			 htmltext = "<html><head><body>This quest have already been completed.</body></html>";
		} else if(npcId == ARUJIEN && cond >= 1) {
			if(st.getQuestItemsCount(ARUJIENS_LETTER1) > 0) {
				htmltext = "7223-05.htm";
			} else if(st.getQuestItemsCount(ARUJIENS_LETTER3) > 0) {
				htmltext = "7223-07.htm";
			} else if(st.getQuestItemsCount(ARUJIENS_LETTER2) > 0) {
				htmltext = "7223-06.htm";
			} else if(st.getQuestItemsCount(POETRY_BOOK) > 0) {
				htmltext = "7223-11.htm";
			} else if(st.getQuestItemsCount(GREENIS_LETTER) > 0) {
				htmltext = "7223-10.htm"; 
    	        st.takeItems(GREENIS_LETTER,-1); 
    	        st.giveItems(BEGINNERS_POTION,5); 
    	        st.set("cond","0"); 
    	        st.setState(COMPLETED); 
    	        st.playSound("ItemSound.quest_finish"); 	
			} 
		} else if(npcId == MIRABEL && cond == 1) {
			if(st.getQuestItemsCount(ARUJIENS_LETTER1) > 0) {
				st.takeItems(ARUJIENS_LETTER1,-1); 
				st.giveItems(ARUJIENS_LETTER2,1); 
				st.set("cond","2"); 
				st.set("id","2"); 
				st.playSound("ItemSound.quest_middle"); 	
			} else if(st.getQuestItemsCount(ARUJIENS_LETTER2) > 0 || st.getQuestItemsCount(ARUJIENS_LETTER3) > 0 || st.getQuestItemsCount(POETRY_BOOK) > 0 || st.getQuestItemsCount(GREENIS_LETTER) > 0) {
				htmltext = "7146-02.htm";
			}
		} else if(npcId == HERBIEL && cond == 2 && st.getQuestItemsCount(ARUJIENS_LETTER1) == 0) {
			if(st.getQuestItemsCount(ARUJIENS_LETTER2) > 0) {
				htmltext = "7150-01.htm";
				st.takeItems(ARUJIENS_LETTER2,-1); 
				st.giveItems(ARUJIENS_LETTER3,1); 
				st.set("cond","3"); 
	       		st.set("id","3"); 
	       		st.playSound("ItemSound.quest_middle"); 
			} else if(st.getQuestItemsCount(ARUJIENS_LETTER3) > 0 || st.getQuestItemsCount(POETRY_BOOK) > 0 || st.getQuestItemsCount(GREENIS_LETTER) > 0) {
				htmltext = "7150-02.htm";
			}
		} else if(npcId == GREENIS && cond == 4) {
			if(st.getQuestItemsCount(POETRY_BOOK) > 0) {
				htmltext = "7157-02.htm";
				st.takeItems(POETRY_BOOK,-1);
				st.giveItems(GREENIS_LETTER,1); 
				st.set("cond","5"); 
				st.set("id","5");
				st.playSound("ItemSound.quest_middle");
			}
		} else if(npcId == GREENIS && st.getQuestItemsCount(GREENIS_LETTER) > 0) {
			htmltext = "7157-03.htm";
		} else if(npcId == GREENIS && (st.getQuestItemsCount(ARUJIENS_LETTER1) > 0 || st.getQuestItemsCount(ARUJIENS_LETTER2) > 0 || st.getQuestItemsCount(ARUJIENS_LETTER3) > 0)) {
			htmltext = "7157-01.htm";
		}
		return htmltext;
	}
	//#NPCs 
	private final int ARUJIEN = 7223;
	private final int MIRABEL = 7146;
	private final int HERBIEL = 7150;
	private final int GREENIS = 7157;

	//#ITEMS 
	private final int ARUJIENS_LETTER1 = 1092;
	private final int ARUJIENS_LETTER2 = 1093;
	private final int ARUJIENS_LETTER3 = 1094;
	private final int POETRY_BOOK      = 689;
	private final int GREENIS_LETTER   = 693;
	 
	//#REWARDS 
	private final int ADENA            = 57;
	private final int BEGINNERS_POTION = 1073;
}
