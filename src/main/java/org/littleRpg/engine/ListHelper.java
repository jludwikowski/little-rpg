package org.littleRpg.engine;

import org.littleRpg.model.GameEntity;

import java.util.List;
import java.util.ListIterator;

public class ListHelper {

    public static void showList(String text, List list){
        if(list != null) {
            ListIterator<GameEntity> itemIterator = list.listIterator();
            while (itemIterator.hasNext()) {
                GameEntity nextItem = itemIterator.next();
                System.out.println(text + nextItem.description);
            }
        }

    }

}
