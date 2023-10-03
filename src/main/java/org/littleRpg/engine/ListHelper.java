package org.littleRpg.engine;

import org.littleRpg.model.GameEntity;

import java.util.List;
import java.util.ListIterator;

public class ListHelper {

    public static void showList(String text, List list, boolean showBackOption){
        if(list != null) {
            list.sort((o1, o2) -> o1.getClass().getName().compareTo(o2.getClass().getName()));
            int listItemNumber = 0;
            ListIterator<GameEntity> itemIterator = list.listIterator();
            if(showBackOption) {
                System.out.println(listItemNumber + " back");
            }
            while (itemIterator.hasNext()) {
                listItemNumber += 1;
                GameEntity nextItem = itemIterator.next();
                String[] processedClassName = nextItem.getClass().getName().split("[.]+");
                System.out.println(text + listItemNumber + " " + nextItem.description +  " " + processedClassName[processedClassName.length-1]);
            }
        }

    }

}
