package net.sf.l2j.gameserver.model.quest;

public class QuestLoader {

	private QuestLoader() {
		new Q001_LettersOfLove();
		new Q002_WhatWomenWant();
	}
	
	public static QuestLoader getInstance() {
		return SingletonHolder.instance;
	}
	
	private static final class SingletonHolder {
		private static final QuestLoader instance = new QuestLoader();
	}
}
