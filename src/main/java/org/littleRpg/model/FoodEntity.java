package org.littleRpg.model;



import java.util.List;

public class FoodEntity {
    public void useItem(List<Item> loot) {
        Human.showItems(loot);
        int itemIndex = Human.itemChoice("Wybierz item który chcesz uzyc");
        if (loot.get(itemIndex).type == ItemTypes.bottleOfWater) {
            System.out.println("uzywasz: " + loot.get(itemIndex).description);
            Human.changeThirst(30;
            loot.remove(itemIndex);
        }
        if (loot.get(itemIndex).type == ItemTypes.meat) {
            System.out.println("uzywasz: " + loot.get(itemIndex).description);
            Human.changeSatiety(25);
            loot.remove(itemIndex);
        }
    }

}
