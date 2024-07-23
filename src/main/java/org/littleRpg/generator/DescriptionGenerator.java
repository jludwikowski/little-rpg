package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.*;

import java.util.*;


public class DescriptionGenerator extends Generator {
    Random random = new Random();
    public static String [] sceneryElements = {"a river", "a small cave", "many ancient signs on the ground", "a ruined castle", "strangely twisted statues"};
    public static Map<Biome, List<String>> weatherTypes = new HashMap<>();
    public static Map<Biome, String[]> startDescriptions = new HashMap<>();
    public static Map<Biome, String[]> middleDescriptions = new HashMap<>();
    public static Map<Biome, String[]> endDescriptions = new HashMap<>();


    public static String [] meadowDescriptionStart = {"The meadow stretches out in a vibrant expanse of emerald grass, dotted with wildflowers in every imaginable color.",
            "The meadow is a tranquil haven, bathed in the golden light of the sun.",
            "This meadow is a canvas of natural beauty, where the grass glows a vibrant green and the wildflowers form a riot of colors."};
    public static String [] meadowDescriptionMiddle = {"The air is thick with the scent of wild thyme and lavender, growing in untamed abundance.",
            "Tall grasses and wildflowers dance gently in the breeze, their hues vivid against the backdrop of the deepening sky.",
            "A gentle breeze rustles through the tall blades, carrying the sweet scent of blooming flora." };
    public static String [] meadowDescriptionEnd = {"Butterflies flutter from flower to flower, adding to the meadow's enchanting, peaceful ambiance.",
            "Nearby, a weathered stone bench offers a perfect spot to rest and take in the meadow's tranquil beauty.",
            "It's a place where time seems to stand still, inviting adventurers to pause and revel in nature's splendor."};


    public static String [] forestDescriptionStart = {"The forest is a place of ancient wonder, filled with towering trees and thick underbrush.",
            "This forest is a realm of shadows and light, where the canopy above filters the sun into a kaleidoscope of green.",
            "You enter a dense forest, where every step is cushioned by a thick layer of fallen leaves."};
    public static String [] forestDescriptionMiddle = {"Birds sing in the trees, their songs blending into a harmonious symphony.",
            "Moss-covered stones and fallen logs add to the sense of timelessness.",
            "The air is cool and damp, filled with the earthy scent of the forest floor."};
    public static String [] forestDescriptionEnd = {"A deer darts through the trees, startled by your presence.",
            "A hidden stream gurgles softly nearby, its sound blending with the rustling leaves.",
            "In the distance, you hear the howl of a wolf, a reminder of the wildness of this place."};


    public static String [] desertDescriptionStart = {"The desert is a vast expanse of golden sands, stretching out as far as the eye can see.",
            "Under the scorching sun, the desert landscape appears almost otherworldly.",
            "The barren desert is an unforgiving place, where the sand dunes shift like waves in an ocean."};
    public static String [] desertDescriptionMiddle = {"Sparse cacti and hardy shrubs dot the landscape, their resilience a testament to the harsh conditions.",
            "The air shimmers with heat, and the silence is broken only by the occasional call of a distant bird.",
            "The ground beneath your feet is hot and dry, cracked by the relentless sun."};
    public static String [] desertDescriptionEnd = {"As night falls, the temperature drops dramatically, and the sky fills with a stunning array of stars.",
            "A distant oasis promises relief from the heat, its lush greenery a welcome sight.",
            "Mirages flicker on the horizon, illusions created by the intense heat."};

    public static String [] mountainDescriptionStart = {"The mountains rise majestically into the sky, their peaks capped with eternal snow.",
            "You find yourself at the base of a towering mountain range, the air crisp and clear.",
            "The rugged mountains are a formidable barrier, their slopes covered in thick pine forests."};
    public static String [] mountainDescriptionMiddle = {"The path is steep and treacherous, winding its way through rocky terrain.",
            "Eagles soar high above, their cries echoing through the valleys.",
            "Small mountain streams cascade down the slopes, their waters icy cold."};
    public static String [] mountainDescriptionEnd = {"At the summit, you are rewarded with a breathtaking view of the surrounding landscape.",
            "A secluded mountain lake reflects the sky like a mirror, its waters crystal clear.",
            "The wind howls through the peaks, a constant reminder of the mountain's raw power."};

    public static String [] swampDescriptionStart = {"The swamp is a murky, foreboding place, where the air is thick with humidity.",
            "You step into the swamp, where the ground is soft and treacherous.",
            "The swamp is a tangle of twisted trees and creeping vines, shrouded in mist."};
    public static String [] swampDescriptionMiddle = {"Insects buzz incessantly, their droning adding to the swamp's eerie atmosphere.",
            "The water is dark and still, hiding who knows what beneath its surface.",
            "The smell of decay is pervasive, a constant reminder of the swamp's danger."};
    public static String [] swampDescriptionEnd = {"Strange, haunting calls echo through the mist, hinting at unseen creatures.",
            "Patches of bright green moss and ferns add a touch of color to the otherwise gloomy landscape.",
            "The swamp seems to close in around you, making every step a challenge."};

    public static String [] hillDescriptionStart = {"The rolling hills stretch out before you, their gentle slopes covered in green.",
            "You find yourself in a landscape of undulating hills, the sky a brilliant blue above.",
            "The hills rise and fall like waves, creating a peaceful and serene environment."};
    public static String [] hillDescriptionMiddle = {"Small clusters of trees dot the hillsides, providing occasional patches of shade.",
            "Wildflowers bloom in abundance, their colors vivid against the green grass.",
            "The sound of birdsong fills the air, a constant accompaniment to your journey."};
    public static String [] hillDescriptionEnd = {"From the top of the hill, you have a panoramic view of the surrounding countryside.",
            "A winding path leads down into a picturesque valley, inviting you to explore further.",
            "The hills are dotted with ancient stone circles and cairns, remnants of a long-forgotten past."};

    public static String [] shopDescriptionStart = {"You enter a bustling shop, filled with the sounds of haggling and the smell of exotic spices.",
            "The shop is a treasure trove of goods, with shelves stacked high with all manner of items.",
            "This shop is a haven for adventurers, offering everything from weapons to rare potions."};
    public static String [] shopDescriptionMiddle = {"The shopkeeper greets you warmly, ready to assist with your every need.",
            "Trinkets and curiosities line the walls, each with its own story to tell.",
            "The shop is dimly lit, with lanterns casting a warm, inviting glow."};
    public static String [] shopDescriptionEnd = {"As you browse, you find yourself drawn to a particularly intriguing item.",
            "The shopkeeper offers you a fair price, and you leave with your purchase in hand.",
            "The door chimes as you exit, stepping back into the bustling street outside."};

    public static String [] smithyDescriptionStart = {"The smithy is a place of intense heat and hard labor, where the ring of hammer on anvil is constant.",
            "You step into the smithy, where the air is thick with the smell of hot metal.",
            "The smithy is a hive of activity, with sparks flying and the forge blazing."};
    public static String [] smithyDescriptionMiddle = {"The blacksmith is hard at work, shaping metal with practiced skill.",
            "Tools and weapons of all kinds are displayed on the walls, showcasing the blacksmith's craftsmanship.",
            "The heat from the forge is almost overwhelming, and sweat beads on your forehead."};
    public static String [] smithyDescriptionEnd = {"The blacksmith pauses to wipe his brow, giving you a nod of acknowledgment.",
            "A freshly forged sword catches your eye, its blade gleaming in the firelight.",
            "You leave the smithy with a new appreciation for the art of metalworking."};

    public static String [] caveDescriptionStart = {"The cave entrance looms before you, dark and foreboding.",
            "You step into the cave, where the air is cool and damp.",
            "The cave is a labyrinth of tunnels and chambers, stretching deep into the earth."};
    public static String [] caveDescriptionMiddle = {"Stalactites and stalagmites create an otherworldly landscape within the cave.",
            "The sound of dripping water echoes through the tunnels, adding to the eerie atmosphere.",
            "The walls are covered in strange, glowing fungi, casting a dim light."};
    public static String [] caveDescriptionEnd = {"You come across a hidden chamber, filled with glittering crystals.",
            "The cave opens up into a vast underground lake, its waters still and dark.",
            "Strange creatures scurry away at your approach, disappearing into the shadows."};

    private static String getRandomDescription(String[] descriptions) {
        return descriptions[Roller.pickNumberFrom(descriptions.length)];
    }

    public DescriptionGenerator(){
        startDescriptions.put(Biome.meadow, meadowDescriptionStart);
        middleDescriptions.put(Biome.meadow, meadowDescriptionMiddle);
        endDescriptions.put(Biome.meadow, meadowDescriptionEnd);

        startDescriptions.put(Biome.forest, forestDescriptionStart);
        middleDescriptions.put(Biome.forest, forestDescriptionMiddle);
        endDescriptions.put(Biome.forest, forestDescriptionEnd);

        startDescriptions.put(Biome.desert, desertDescriptionStart);
        middleDescriptions.put(Biome.desert, desertDescriptionMiddle);
        endDescriptions.put(Biome.desert, desertDescriptionEnd);

        startDescriptions.put(Biome.mountain, mountainDescriptionStart);
        middleDescriptions.put(Biome.mountain, mountainDescriptionMiddle);
        endDescriptions.put(Biome.mountain, mountainDescriptionEnd);

        startDescriptions.put(Biome.swamp, swampDescriptionStart);
        middleDescriptions.put(Biome.swamp, swampDescriptionMiddle);
        endDescriptions.put(Biome.swamp, swampDescriptionEnd);

        startDescriptions.put(Biome.hill, hillDescriptionStart);
        middleDescriptions.put(Biome.hill, hillDescriptionMiddle);
        endDescriptions.put(Biome.hill, hillDescriptionEnd);

        startDescriptions.put(Biome.shop, shopDescriptionStart);
        middleDescriptions.put(Biome.shop, shopDescriptionMiddle);
        endDescriptions.put(Biome.shop, shopDescriptionEnd);

        startDescriptions.put(Biome.smithy, smithyDescriptionStart);
        middleDescriptions.put(Biome.smithy, smithyDescriptionMiddle);
        endDescriptions.put(Biome.smithy, smithyDescriptionEnd);

        startDescriptions.put(Biome.cave, caveDescriptionStart);
        middleDescriptions.put(Biome.cave, caveDescriptionMiddle);
        endDescriptions.put(Biome.cave, caveDescriptionEnd);


        weatherTypes.put(Biome.desert, Arrays.asList("sunny","sandstorm"));
        weatherTypes.put(Biome.forest,Arrays.asList("sunny", "foggy", "rainy", "stormy"));
        weatherTypes.put(Biome.hill,Arrays.asList("sunny", "foggy", "rainy", "stormy"));
        weatherTypes.put(Biome.meadow,Arrays.asList("sunny", "foggy", "rainy", "stormy"));
        weatherTypes.put(Biome.swamp,Arrays.asList("sunny", "foggy", "rainy", "stormy"));
        weatherTypes.put(Biome.mountain, Arrays.asList("sunny", "foggy", "rainy", "stormy"));
        weatherTypes.put(Biome.shop, Arrays.asList("sunny", "foggy", "rainy", "stormy"));
        weatherTypes.put(Biome.smithy, Arrays.asList("sunny", "foggy", "rainy", "stormy"));
        weatherTypes.put(Biome.cave, Arrays.asList("it's cave !!! Hello "));
    }
    public String getEntity(){
        return null;
    }

    @Override
    public Object adjust(Object entity, String adj) {
        return null;
    }

    public static String generate(MapPlace place){
        List<String> weatherList = weatherTypes.get(place.biome);
        String weather = weatherList.get(Roller.pickNumberFrom(weatherList.size()));
        String placeDescription = "You find yourself in a " + place.biome + ".\n The weather is " + weather +  ".\n";

        String[] startDescription = startDescriptions.get(place.biome);
        String[] middleDescription = middleDescriptions.get(place.biome);
        String[] endDescription = endDescriptions.get(place.biome);

        if (startDescription != null && middleDescription != null && endDescription != null) {
            placeDescription += getRandomDescription(startDescription) + " ";
            placeDescription += getRandomDescription(middleDescription) + " ";
            placeDescription += getRandomDescription(endDescription) + " ";
        }

        if(place.placeFeature != null){
            String feature = place.placeFeature.description;
            placeDescription += "Nearby, there is " + feature;
        }
        if (place.monsters != null && !place.monsters.isEmpty()){
            placeDescription += "\nHere is standing: \n";
            ListIterator<Monster> i = place.monsters.listIterator();
            while(i.hasNext()) {
                Monster nextMonster = i.next();
                placeDescription +=  "-" + nextMonster.getDescription() + nextMonster.monsterLevel+"\n";
            }
        }
        if (place.items != null && place.biome != Biome.shop && !place.items.isEmpty()) {
            placeDescription += "\nHere you can find items: \n";
            ListIterator<Item> k = place.items.listIterator();
            while (k.hasNext()) {
                Item nextItem = k.next();
                placeDescription += "-" + nextItem.getDescription() + "\n";

            }
        }
            return placeDescription;
    }

}
