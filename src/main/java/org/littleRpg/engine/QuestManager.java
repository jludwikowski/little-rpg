package org.littleRpg.engine;

import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.List;

public class QuestManager {
    public List<Quests> questsList = new ArrayList<>();

    public QuestManager(){
        questsList.add(new Quests("Ghull killer","Players must venture to a haunted graveyard to slay five " +
                "menacing ghouls that are terrorizing the nearby villages.",0, 5,QuestType.KILL,MonsterTypes.ghul,
                QuestStatus.INACTIVE, PrizeType.GOLD, 500, null));

        questsList.add(new Quests("The Missing Helmet","The blacksmith wants to create a new type of helmet. Unfortunately, he can't forge anything new until he gets a good design, and he doesn't leave the forge due to the monsters in the area. Deliver any helmet to him.",
                0,1,QuestType.DELIVERY,null, QuestStatus.INACTIVE, PrizeType.GOLD, 200, null));

        questsList.add(new Quests("The Lost Skulls", "A shopkeeper needed skulls for a client on a special order. Unfortunately, he lost them in the forest while fleeing from monsters. Help him find the skulls.",
                0,3, QuestType.FETCH, null, QuestStatus.INACTIVE, PrizeType.GOLD, 300, null));
    }

    public void chooseQuest(Human monsterTaker){
        if (questsList != null && !questsList.isEmpty()){
            //System.out.println("choose quest to Activate: \n");
            ListHelper.showList("choose quest to Activate: \n", questsList , true);
            int quests = Human.readChoice("Choose number of quests");
            Quests quest = (questsList.get(quests - 1));
            quest.questStatus = QuestStatus.ACTIVE;
            monsterTaker.addQuest(quest);
            // czy aby napewno tak - chodzi o nadpisywanie - wolałbym uniknąć tylko przyjąć liczbę starowę i + ?
            Integer ghulKills = monsterTaker.monsterKillCounter.get(MonsterTypes.ghul);
            if (ghulKills == null) {
                ghulKills = 0;
            }
            quest.counter = ghulKills;

            questsList.remove(quest);
        }else{
            System.out.println("nothing to challenge ... ");
        }
    }

    public void finishQuest(Human player){
        if (questsList != null && !questsList.isEmpty()){
            //System.out.println("choose quest to Activate: \n");
            ListHelper.showList("choose quest to close: \n", questsList , true);
            int quests = Human.readChoice("Choose number of quests");
            Quests quest = (questsList.get(quests - 1));
            switch (quest.questType) {
                case KILL:
                if (quest.counter >= quest.targetCounter) {
                    System.out.println("you have achieved the goal of the task");
                    quest.questStatus = QuestStatus.FINISHED;
                    // określenie i implementacja nagrody
                    if (quest.prizesType == quest.prizesType.GOLD) {
                        player.goldCoins += quest.prizesGold;
                    }
                    if (quest.prizesType == quest.prizesType.ITEM) {
                        player.loot.add(quest.prizesItem);

                    }
                }
                if (quest.counter < quest.targetCounter) {
                    System.out.println("Don't give up, try to complete this task");
                }
                break;
                case FETCH:
                    break;
                case ESCORT:
                    break;
                case DELIVERY:
            }
        }else{
            System.out.println("nothing to Finish ... ");
        }
    }
}
