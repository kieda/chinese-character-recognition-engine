/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/database/DatabaseCreator.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics.database;//package

// <editor-fold defaultstate="collapsed" desc="imports">
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import universals.LogManager;
import mechanics.ChineseCharacter;// </editor-fold>

/**
 * THis classis used to interface with the database and databasecratorpanel to
 * help users draw characters and to add characters to the database
 * @version 1.0
 * @author Kieda
 * @since 3-23-2011
 *//*USER DEFINED METHODS WITH RETURN VALUES AND PARAMETERS*/
public class DatabaseCreator {
    private static final File loadfile = new File("UNFILLED_CHARACTERS.DAT");
        //the file loaded that hgas no geometrical data and contains Characters
        //in the format "Character:Definition:Pinyin"
    private static Scanner in;//the scanner used to read the unfilled characters
    /**
     * His method is used to get data in string format directly from the
     * unfilled character database. This method will give the next line until it
     * runs out of lines, where it returns ""
     * @return the next line in the unfilled characters
     */
    public static String next(){
        if(!done())//if we haven't reached the end of the file yet
            return in.nextLine();//give the next line
        else{
            System.out.println("END OF FILE! CONGRATS!");
                //notifies the user they are don inputting characters
            return "";//retrurns a dummy
        }
    }
    /**
     * returns whether or not we have reached the end of the unfilled character
     *     database.
     * true means that we have reached the end, and false means that there still
     *     are more character to go.
     * @return whether or not we have reached the end of the unfilled character
     *         database
     */
    public static boolean done(){
        return !in.hasNext();//whether or not we are at the end of the file
    }
    /**
     * initializes the scanners to read the unfilled character database. This
     * method should be called before running the database mode
     */
    public static void init(){
        try {
            in = new Scanner(loadfile);
                //creates a new scanner to read the unfilled characters
        } catch (IOException e) {}
    }
    /**
     * This method adds a new set geometrical data with a chinese character to
     * the database. This is called when someone hits the "add to database"
     * button while in database mode
     * @param c the ChineseCharacter to be added to the end of the database
     */
    public static void add(ChineseCharacter c){
        LogManager.logThing(c.toString(), LogManager.DATABASE);
            //adds a Chinese character to the database in the correct string
            //format for the database
    }
}