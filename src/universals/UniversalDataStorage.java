/*//////////////////////////////////////////////////////////////////////////////
./src/universals/UniversalDataStrorage.java
//////////////////////////////////////////////////////////////////////////////*/
package universals;

// <editor-fold defaultstate="collapsed" desc="imports n' stuff">
import gui.mainframe.componentcreator.options.FloatingPartsCounterPanel;
import gui.mainframe.componentcreator.options.IntersectionStringInputPanel;
import gui.mainframe.componentcreator.options.StrokeComplexityPanel;
import java.util.ArrayList;
import mechanics.ChineseCharacter;// </editor-fold>

/**
 * This is a static, public class used specifically for data storage universally
 * throughout the entire program. This class is in charge of hosting all of the
 * information drawn on the drawpanel, the database of ChineseCharacters, and
 * more. Call on this class to access data that is stored universally.
 *
 * NOTE: final variables dealing with the GUI are found in the 
 * ChineseFrameComponent class.
 *
 * ANOTHER NOTE: Classes may extend this class (to save the trouble of saying
 * UniversalDataStorage."..." each time you want to do something). I'm thinking
 * about making the variables only accesable if they are subclasses (but that is
 * for another day)
 * 
 * @author Kieda
 * @version 2.3
 * @since 3-21-2011
 *//*OBJECTS AS DATA RECORDS ALL OVER HERE*/
public class UniversalDataStorage {
    public static int numberOfStrokes;
    public static int totalNumberOfIntersections;
    public static int numberOfFloatingParts;
    public static int numberOfStraightStrokes;
    public static int numberOfComplexStrokes;
    public static ArrayList<Integer> intersections;
        //geometrical data about the character being created
    
    public static CreatedCharacter chineseCharacter = new CreatedCharacter();
        //the character being created
    public static ChineseStroke points = new ChineseStroke();
        //the current stroke
    public static String character;
    public static String meaning;
    public static String pinyin;
        //the character/meaning/pinyin of the character being made
    public static int getXMouseDrawing;//the x position of the mouse
    public static int getYMouseDrawing;//the y position of the mouse
        
    public static ChineseCharacter[]  database;
        //the chinese characters loaded form the database and stored in the ram
        //(for higher speeds)
    /**
     * this method should be called frequently as possible, usually updating
     * after every mouserelease (but no more!). This updates all of the
     * intersections/strokes/data/whatev on the manual input manel
     */
    public static void updateOptions(){
        FloatingPartsCounterPanel.setNumberOfFloatingParts(
            numberOfFloatingParts);
        IntersectionStringInputPanel.setIntersectionsString(
            intersectionsToString(intersections));
        StrokeComplexityPanel.setNumberOfComplexStrokes(numberOfComplexStrokes);
        StrokeComplexityPanel.setNumberOfStraightStrokes(
            numberOfStraightStrokes);
                //updates the components of the options panel
    }
    /**
     * used specifially for the purpose of converting an array of intersections
     * to a string. Can be accesed by any class (just in case the program needs it)
     * @param intersections the intersections to be converted to a string
     * @return the String of intersections (in the format "intersections 0"
     *         ,"intersections 1","intersections 2", ...etc...
     */
    public static String intersectionsToString(ArrayList<Integer> intersections){
        String s = "";
        for(Integer i: intersections)
            s+=i+",";
                //goes through the array and creates a string with commas
        return s;
    }
}
