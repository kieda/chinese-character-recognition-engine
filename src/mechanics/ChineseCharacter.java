/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/ChineseCharacter.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.ChineseFrame;
import java.util.ArrayList;
import java.util.Arrays;// </editor-fold>
import universals.UniversalDataStorage;

/**
 * A ChineseCharacter is a complete set of the information that defines a 
 * chinese character. currently, a chinese character is defined by its...
 *     String pinyin                    (the pronounciation of the character)
 *     String character                 (the chinese symbol for the character)
 *     String meaning                   (the english meaning of the character)
 *     int    floatingParts             (the number of floating parts a
 *                                       character has)
 *     ArrayList<Integer> intersections (the intersections on the chinese
 *                                       character, a value for each stroke)
 *     int    straightStrokes           (the number of simple strokes a chinese
 *                                       character has)
 *     int    complexStrokes            (the number of complex strokes a chinese
 *                                       character has)
 *
 * The ChineseCharacter class is used for a wide variety of transferring
 * information of chinese characters. It is used from database in saerching
 * characters, and more.
 * @author Kieda
 * @version 2.2
 * @since 3-20-2011
 *//*MAJOR ENCAPSULATION IN HERE*/
   /*USER DEFINED OBJECTS*/
   /*HIERARCHICAL COMPOSITE DATA STRUCTURE*/
public class ChineseCharacter{
    private int    straightStrokes;//the number of complex strokes
    private int    complexStrokes;//the number of simple strokes
    private String pinyin;//the pronounciation of the character
    private String character;//the actual character
    private String meaning;//the meaning of the character
    private int    floatingParts;//the number of floating parts the
                                 //character has
    /*ABSTRACT DATA TYPES: LISTS*/
    private ArrayList<Integer> intersections;
        //THe  array of intersections for each stroke
        //the array of intersections is a different and interesting concept. A 
        //character can be defined by the intersections with other strokes. The
        //layout of these intersections are as follows:
        //Array[STROKE NUMBER] = NUMBER OF INTERSECTIONS ON STROKE
    
    /**
     * THe constructor of the ChineseCharacter. THese parts are needed for
     * defining a chinese character. These ChineseCharacters are usually fetched
     * from the database
     * @param pinyin the pronounciation of the character
     * @param character the actual character
     * @param meaning the meaning of the character
     * @param floatingPartsthe number of floating parts the character has
     * @param intersections The  array of intersections for each stroke
     * @param straightStrokes the number of simple strokes
     * @param complexStrokes the number of complex strokes
     */
    public ChineseCharacter(String pinyin, String character, String meaning
            , int floatingParts, ArrayList<Integer> intersections
            , int straightStrokes, int complexStrokes) {
        this.character = character;
        this.pinyin = pinyin;
        this.meaning = meaning;
        this.floatingParts = floatingParts;
        this.intersections = intersections;
        this.straightStrokes = straightStrokes;
        this.complexStrokes = complexStrokes;
            //sets local variables to global variables for data handling
    }
    // <editor-fold defaultstate="collapsed" desc="gets and sets">
    //A short note on all of the upcoming methods- these are all gets and sets, 
    //allowing the programmer to retrive information about the character and to 
    //change specific information. Because I am too lazy to document all of
    //these, I decided just to put in this short description :D
    public String getCharacter() {
        return character;
    }
    public int getComplexStrokes() {
        return complexStrokes;
    }
    public int getFloatingParts() {
        return floatingParts;
    }
    public ArrayList<Integer> getIntersections() {
        return intersections;
    }
    public String getMeaning() {
        return meaning;
    }
    public String getPinyin() {
        return pinyin;
    }
    public int getStraightStrokes() {
        return straightStrokes;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
    public void setComplexStrokes(int complexStrokes) {
        this.complexStrokes = complexStrokes;
    }
    public void setFloatingParts(int floatingParts) {
        this.floatingParts = floatingParts;
    }
    public void setIntersections(ArrayList<Integer> intersections) {
        this.intersections = intersections;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    public void setStraightStrokes(int straightStrokes) {
        this.straightStrokes = straightStrokes;
    }// </editor-fold>

    /**
     * an extremely helpful method. Allows these characters to be used as
     * compiled strings in data comparison. THe best part is that it returns a
     * string in the same format that the database uses. THis is useful in
     * adding characters to the database or figuring out which character you
     * drew.
     * @return the string of the character in the format
     *
     * separate parameters       (:)
     * separate values in array  (,)
     *
     * String(PINYIN):String(CHARACTER):String(MEANING)#int(FLOATING PARTS)
     *     :array[int(NUMBER OF INTERSECTIONS ON A STROKE)]
     *     :int(STRAIGHT STROKES):int(COMPLEX STROKES):
     * the array[int(NUMBER OF INTERSECTIONS ON A STROKE)] would look something
     *     like
     *         (intersections on stroke 1),(intersections on stroke 2), ....
     */
    @Override
    public String toString() {
        if(this.equals(null)){
                //makes sure that the character really isn't emplty
            System.out.println("ERROR IN RETREIVING INFORMATION ABOUT THE " +
                "CHINESE CHARACTER");
                    //prints out error
            ChineseFrame.kill();//exits the program  errorless-ly
            return "nuthing found";
        }
        else{//if the information on the chracter is filled out
            String intersectionsString = "";
                //initializes a string used for the intersections
            for(Integer i:intersections){//goes through all of the intersections
                intersectionsString+=(i+",");
                    //and creates it into a viable string usable as a
                    //ChineseCharacter
            }
            return String.format("%s:%s:%s#%d:%s:%d:%d"
                ,pinyin, character,meaning,floatingParts,intersectionsString
                ,straightStrokes,complexStrokes);
                    //returns the complete string
        }
    }
    /**
     * THis method is very useful for converting a String to a ChineseCharacter. 
     * This is very useful when loading information from the database so the
     * ChineseCharacter is usable as an object
     *
     * THis method is actually called in initialization, so that the program can
     * store the character into its ram (for increased speed)
     * @param currentLine the linethat will be created into a ChineseCharacter
     * @return the ChineseCharacter needed
     *//*PARSING A LINE OF TEXT*/
    public static ChineseCharacter toChineseCharacter(String currentLine){
        String currentLineData = currentLine.split("#")[1];
            //the geometrical data about the line in string form
        String[] infrormation = currentLineData.split(":");
            //the string of different segments of data from the geometrical data
            //(above)
        String intersectionString = infrormation[1];
            //the string of intersections
        String[] intersectionsString = intersectionString.split(",");
            //an array of the strings of intersections

        Integer[] intersections = new Integer[intersectionsString.length];
        for (int counter = 0; counter < intersectionsString.length; counter++) {
            //goes through all of the values of the intersections
            intersections[counter] = Integer.parseInt(
                intersectionsString[counter]);
                    //converts String to an Integer
        }
            //converts a String array to an Integer array

        int floatingParts = Integer.parseInt(currentLineData.split(":")[0]);
            //gets the number of floating parts from the String
        int straightStrokes = Integer.parseInt(currentLineData.split(":")[2]);
            //gets the number of straight strokes from the String
        int complexStrokes = Integer.parseInt(currentLineData.split(":")[3]);
            //gets the number of complex strokes from the String
        String pinyin = currentLine.split("#")[0].split(":")[0];
            //extracts the pinyin from the String
        String character = currentLine.split("#")[0].split(":")[1];
            //extracts the chinese character from the String
        String meaning = currentLine.split("#")[0].split(":")[2];
            //extracts the english meaning from the String
        Arrays.sort(intersections);
            //sorts the intersections (for easy comparison)
        return new ChineseCharacter(pinyin, character, meaning, floatingParts,
            new ArrayList<Integer>(Arrays.asList(intersections)),
            straightStrokes, complexStrokes);
                //returns a new chinese character with all of the components
                //needed (from String)
    }
}
