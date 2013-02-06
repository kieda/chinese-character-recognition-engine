/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/recognition/managers/StrokeNumberManager.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics.recognition.managers;

// <editor-fold defaultstate="collapsed" desc="imports">
import mechanics.recognition.ChineseManager;
import universals.UniversalDataStorage;
import universals.CreatedCharacter;// </editor-fold>
/**
 * this is a manager used to compute the number of strokes. This is rather easy
 * to do, but for consistancy, is done in a manager.
 * @author Kieda
 * @version 1.0
 * @since 3-22-2011
 *//*INHEIRITANCE*/
   /*ABSTRACT DATA IMPLEMENTATION*/
public class StrokeNumberManager extends UniversalDataStorage
        implements ChineseManager<Integer>{
    /**
     * returns the value of the number of strokes without updating
     * @return the number of strokes
     */
    public Integer returnValue() {
        return numberOfStrokes;
    }
    /**
     * gives the number of strokes in a chinese character
     * @param chineseCharacter
     * @return the number of strokes
     */
    public Integer processData(CreatedCharacter chineseCharacter) {
        return chineseCharacter.size();
            //returns the number of strokes in the character
    }
    /**
     * updates the universal dtata. Call this method to update easily!
     */
    public void updateManager() {
        numberOfStrokes = processData(chineseCharacter);//processes the data
    }
}
