package org.littleRpg.engine;

import org.littleRpg.model.Human;

import java.io.*;

public class LoadSaveOperator {



    public static void savePoint(Human player){
        try {
            FileOutputStream saveFile = new FileOutputStream("C:\\save\\saveFile.sam");
            System.out.println(saveFile.toString());
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            System.out.println(save.toString());
            save.writeObject(player);
            save.close();
            saveFile.close();
        }
        catch (Exception e) {
            System.out.println("Problem occurred save operation");
            e.printStackTrace();
        }


    }

    public static Human loadPoint(){
        try {
            FileInputStream loadFile = new FileInputStream("C:\\save\\saveFile.sam");
            System.out.println(loadFile.toString());
            ObjectInputStream load = new ObjectInputStream(loadFile);
            System.out.println(load.toString());
            Human player = (Human) load.readObject();
            System.out.println(player.toString());
            load.close();
            loadFile.close();
            return player;
        }
        catch (Exception e) {
            System.out.println("Operation load has been problem");
            e.printStackTrace();
        }
        return null;
    }
}
