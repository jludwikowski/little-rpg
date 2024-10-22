package org.littleRpg.model;

public class Quests extends GameEntity{

    public int counter;
    public int targetCounter;
    public QuestType questType;
    public QuestStatus questStatus;
    public PrizeType prizesType;
    public int prizesGold;
    public Item prizesItem;
    public MonsterTypes monsterType;

    public Quests(String name, String description,int counter, int targetCounter,QuestType questType,MonsterTypes monsterType,
                  QuestStatus questStatus,PrizeType prizesType, int prizesGold, Item prizesItem){
        super(name, description);
        this.counter = counter;
        this.targetCounter = targetCounter;
        this.questType = questType;
        this.monsterType = monsterType;
        this.questStatus = questStatus;
        this.prizesType = prizesType;
        this.prizesGold = prizesGold;
        this.prizesItem = prizesItem;
    }


}
