/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/database/DatabaseDriver.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics.database;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.componentcreator.outputpanel.PossibleChineseCharactersPanel;
import java.awt.EventQueue;
import universals.LogManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import mechanics.ChineseCharacter;
import universals.TimeManager;
import universals.UniversalDataStorage;// </editor-fold>

/**
 * this is the database driver, it is responsible for I/O in the database,
 * especially searching.
 * @version 1.9
 * @author Kieda
 * @since 3-23-2011
 *//*USER DEFINED METHODS WITH RETURN VALUES AND PARAMETERS*/
public class DatabaseDriver {
    static Scanner in;//the scanner to read from the filled out database
    
    private static ChineseCharacter chinesecharacter = null;
    private static Integer[] intersections;
    private static int floatingParts;
    private static int straightStrokes;
    private static int complexStrokes;
        //local geometric data. Declared here so the searching of characters can
        //be done in a new Runnable
    /**
     * This method is used to search for a chinese character in the database.
     * THe intersections are of type object because of conversion problems from
     * ArrayList<Integer> to Integer[]. THis method converts the Object[] to
     * Integer[], then passes it onto the other searchCharacter(...).
     * This method should be called after a character is updated to give 
     * realtime character updating
     * 
     * @param intersections
     * @param floatingParts
     * @param straightStrokes
     * @param complexStrokes
     * @return
     *//*POLYMORPHISM*/
    public static ChineseCharacter searchCharacter(Object[] intersections
            ,int floatingParts,int straightStrokes,int complexStrokes){
        Integer[] intIntersections = new Integer[intersections.length];
        for(int i = 0;i<intersections.length;i++){
            intIntersections[i] = Integer.parseInt(intersections[i]+"");
        }
        return searchCharacter(intIntersections, floatingParts, straightStrokes
                , complexStrokes);
    }
    /**
     * This method is used to search for a chinese character in the database.
     * This method should be called after a character is updated to give
     * realtime character updating. THis method currently uses an exact matching
     * technique: the data between the values for matches have to match exactly.
     * THis will be changed to a proximity search when more algorithms are added
     * on
     * 
     * @param intersection the array of intersections that the user has inputted
     * @param floatingPart the number of floating parts the user has inputted
     * @param straightStroke the number of simple strokes that the user has
     *        inputted
     * @param complexStroke the number of complex strokes that the user has
     *        inputted
     * @return a chinese character match
     *//*POLYMORPHISM*/
       /*SEARCHING*/
       /*MERGING TWO OR MORE DATA STRUCTURES*/
       /*FILE I/O*/
    public static ChineseCharacter searchCharacter(Integer[] intersection
            ,int floatingPart, int straightStroke, int complexStroke){
        intersections = intersection;
        floatingParts = floatingPart;
        straightStrokes = straightStroke;
        complexStrokes = complexStroke;
            //updates the class variables to the ones defined
        EventQueue.invokeLater(new Runnable() {
            public void run() {//searches for characters in a thread
                
                PossibleChineseCharactersPanel.clearList();
                    //clears the list of possible chinese characters in the
                    //previous character
                /*SORTING*/
                Arrays.sort(intersections);
                    //sorts the incoming intersections (to make a useful
                    //comparison between the database values and the incoming
                    //values)
                /*ARRAYS*/
                for(ChineseCharacter c: UniversalDataStorage.database){
                        //goes through all of the values in the database
                    if (c.getFloatingParts() == floatingParts
                            && Arrays.toString(c.getIntersections().toArray())
                                .equals(Arrays.toString(intersections))
                            && (c.getComplexStrokes() + c.getStraightStrokes())
                                == (complexStrokes + straightStrokes)
                            //IF NEEDED     
                                // &&straightStrokeSearch==straightStrokes
                                // &&complexStrokeSearch==complexStrokes
                                    //sorts by simple/complex, will imoprove
                                    //algorithm later

                                //if there are matches, report a match
                            ) {
                        String pinyin = c.getPinyin();
                        String character = c.getCharacter();
                        String meaning = c.getMeaning();
                            //break up the String character from the database
                            //into its parts
                        
                        chinesecharacter = new ChineseCharacter(pinyin
                            , character, meaning, floatingParts, new
                            ArrayList<Integer>(Arrays.asList(intersections))
                            , straightStrokes, complexStrokes);
                                //constructs a new CHinese character with the 
                                //given meaning/pinyin/character and its
                                //geometrical data
                        System.out.println(chinesecharacter);
                            //prints out the character we made. May remove in
                            //the near future due to the results in the possible
                            //chinese character panel. However, this is looked 
                            //at when using the database main to see if a
                            //character had been added previously
                        PossibleChineseCharactersPanel.addToList(
                            chinesecharacter);
                                //adds the character match to the list of
                                //possible characters
                        LogManager.logThing("Character found:" +chinesecharacter
                            + " " + TimeManager.getCurrentTimeAndDate());
                                //logs the character made
                    }
                }
            }});
        return chinesecharacter;//returns the Chinese character found
    }
}