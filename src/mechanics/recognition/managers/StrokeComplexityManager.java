/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/recognition/managers/StrokeComplexityManager.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics.recognition.managers;

// <editor-fold defaultstate="collapsed" desc="imports">
import java.util.ArrayList;
import mechanics.recognition.ChineseManager;
import universals.ChineseLine;
import universals.ChineseStroke;
import universals.UniversalDataStorage;
import universals.CreatedCharacter;// </editor-fold>

/**
 * Due to a recent (something) I have decided to comment my code less than 
 * usual. {I think i'm sick of commenting. Or i'm almost out of timee. either
 * one. But it's alright, noone would ACTUALLY spend their time looking through
 * all of this code.} Comments on several classes only really have major
 * descriptions of methods. Commenting will improve on this method eventually
 *
 * This is another geometrical character analysis thingamajig that breaks down
 * the given data set of points into data that is comparable. This class
 * implements ChineseManager and extends UniversalDataStorage
 *
 * @author Kieda
 * @version 1.5
 * @since 3-23-2011
 *//*USER DEFINED METHODS WITH PARAMETERS AND RETURN VALUES*/
public class StrokeComplexityManager extends UniversalDataStorage
        implements ChineseManager{
    /**
     * required method from the ChineseMethod. This returns the value of the
     * numberOfComplexStrokes and numberOfStraightStrokes in an array without
     * updating the numbers.
     * NOTE:
     *     Integer[0] is equal to the numberOfStraightStrokes
     *     Integer[1] is equal to the numberOfComplexStrokes
     * @return the number of complex strokes and simple strokes in an array
     */
    @Override
    public Integer[] returnValue() {
        return new Integer[]{numberOfStraightStrokes, numberOfComplexStrokes};
    }
    /**
     * required method of the ChineseManager. THis method processes the data and
     * updates the universal data
     */
    public void updateManager() {
        numberOfComplexStrokes = processData(chineseCharacter)[1];
        numberOfStraightStrokes = processData(chineseCharacter)[0];
    }
    /**
     * required method from the ChineseManager. This method processes and
     * returns an Integer array {simple, complex} of the number of simple
     * strokes and complex strokes. THis algorithm finds the numjber of complex
     * trokes through geometical analysis, and it finds the number of simple
     * strokes by subtracting the total strokes from the number of complex.
     * @param chineseCharacter the character to analyze geometrically
     * @return n Integer array {simple, complex} of the number of simple
     *           strokes and complex strokes
     *//*SEARCHING*/
    public Integer[] processData(CreatedCharacter chineseCharacter) {
        Integer complex = 0;
        for(ArrayList<Integer[]> s: chineseCharacter){
            if(complexStroke((ChineseStroke) s)){
                complex++;
            }
        }
        Integer simple = chineseCharacter.size()-complex;
        numberOfComplexStrokes = complex;
        numberOfStraightStrokes = simple;
        return new Integer[]{simple,complex};
    }
    /**
     * THis method returns whether or not a stroke is complex. THe stroke is 
     * complex if the value is true. It analyzes the simple/copmplex aspects by
     * analyzing methods from the ChineseLine class.
     *
     * Call this method in a loop asking for each of the strokes in a character
     * @param p the set of points on the line
     * @return whether or not the stroke is complex
     */
    private boolean complexStroke(ChineseStroke p){
        boolean b = false;
        if(p.size()>=2){
            for(int i = 0; i<(p.size()-2);i++){
                ChineseLine cl1 = new ChineseLine(p.get(i)[0], p.get(i+1)[0]
                    , p.get(i+1)[1], p.get(i)[1]);
                ChineseLine cl2 = new ChineseLine(p.get(i+1)[0], p.get(i+2)[0]
                    , p.get(i+2)[1], p.get(i+1)[1]);
                if(Math.abs(cl1.totaldegrees-cl2.totaldegrees)>70
                        &&(cl1.totaldegrees>80||cl2.totaldegrees>80)){
                    b = true;
                }
            }
        }
        return b;
    }
}
