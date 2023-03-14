package org.littleRpg.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class EntityList {
    public List<GameEntity> list = new ArrayList<>();

    public void showList(String text){
        if(list != null) {
            ListIterator<GameEntity> itemIterator = list.listIterator();
            while (itemIterator.hasNext()) {
                GameEntity nextItem = itemIterator.next();
                System.out.println(text + nextItem.description);
            }
        }

    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void add(GameEntity gameEntity){
        list.add(gameEntity);
    }
    public void remove(int index){
        list.remove(index);
    }
    public void addAll(List <GameEntity>list){
        list.addAll(list);
    }
    public GameEntity get(int index){
        return list.get(index);
    }
}
