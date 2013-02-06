/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/database/DatabaseLoader.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics.database;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.ChineseFrame;
import java.io.File;
import java.util.Scanner;
import mechanics.ChineseCharacter;
import universals.LogManager;
import universals.UniversalDataStorage;// </editor-fold>

/**
 * THe DatabaseLoader class is used to load the database from a text file into 
 * the computer's RAM. This should be loaded beforre any Chinese characters are
 * found
 * 
 * @author Kieda
 * @version 2.3
 * @since 3-23-2011
 *//*USER DEFINED METHODS WITH RETURN VALUES AND PARAMETERS*/
   /*FILE I/O*/
   /*PARSING TEXT*/
public class DatabaseLoader {
    static Scanner in;//the scanner to receive information about the database
    static int databaseCapacity;
        //the size of the database. THis measurement is taken at the beginning
        //to declare an array of the correct size of ChineseCharacters
    public static void load(){
        File f = new File(LogManager.DATABASE);
            //the path to the stored chinese characters
        try {
            in = new Scanner(f.getAbsoluteFile(), "UTF8");
                //gets the database file as a scanner
        } catch (Exception bbb) {
            in = new Scanner("");
                //if there are any errors, assign the scanner to a dummy
                //variable
            LogManager.logError(bbb, "chinese data file not found", 40
                , "DatabaseDriver", true);
                    //Logs and prints the error that it cannot find the data
                    //file
            ChineseFrame.kill();//exits the program so no harm is done
        }
        databaseCapacity = 0;
            //the capacity of the database, used to initialize the array of
            //Chinese chyaracters
        while (in.hasNext()) {//goes through all of the lines in the database
            in.nextLine();
            databaseCapacity++;//counts the number of lines
        }
        ChineseCharacter[] data = new ChineseCharacter[databaseCapacity];
            //creates a new array the size of the database
        in.close();//closes the scanner so the scanner can go back to the start
        try {
            in = new Scanner(f.getAbsoluteFile(), "UTF8");
                //gets the database file as a scanner
        } catch (Exception bbb) {
            in = new Scanner("");
                //if there are any errors, assign the scanner to a dummy
                //variable
            LogManager.logError(bbb, "chinese data file not found", 63
                , "DatabaseDriver", true);
                    //Logs and prints the error that it cannot find the data
                    //file
            ChineseFrame.kill();//exits the program so no harm is done
        }
        for (int i = 0; in.hasNext();i++) {//goes through the entire database
            data[i] = ChineseCharacter.toChineseCharacter(in.nextLine());
                //creates the array of Chinese characters line by line
        }
        UniversalDataStorage.database = data;//sets the data universally
        try {
            Thread.sleep(500);
                //waits for half a second(so the loading panel doesn't stay open
                //for .1 seconds. THis short tim could give people seizures
        } catch (InterruptedException ex) {}
    }
}
