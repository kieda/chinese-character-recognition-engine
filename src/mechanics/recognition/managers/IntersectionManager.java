/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/recognition/managers/IntersectionManager.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics.recognition.managers;

// <editor-fold defaultstate="collapsed" desc="imports">
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import mechanics.recognition.ChineseManager;
import universals.UniversalDataStorage;
import universals.CreatedCharacter;// </editor-fold>

/**
 * Due to a recent (something) I have decided to comment my code less than
 * usual. {I think i'm sick of commenting. Or i'm almost out of timee. either
 * one.} Comments on several classes only really have major
 * descriptions of methods. Commenting will improve on this method eventually
 *
 * This clacc is responsible for geometrically calculating the number of
 * intersections on a set of lines. THis is probably one of the most important
 * methods (currently) to distinguishing characters.
 * 
 * @author Kieda
 * @version 2.0
 * @since 3-22-2011
 *//*INHEIRITANCE*/
   /*ABSTRACT DATA IMPLEMENTATION*/
public class IntersectionManager extends UniversalDataStorage
        implements ChineseManager{
    /**
     * the method called to process data on a CreatedCharacter. Calculations
     * dont actually happen in this method. This method is called to initialize
     * the data
     * @param pp the ChineseCharacter to process
     * @return the number of floating parts
     */
    @Override
    public ArrayList<Integer> processData(CreatedCharacter p) {
        /*ABSTRACT DATA TYPES: LIST*/
        ArrayList<Integer> i = new ArrayList<Integer>(p.size());
        i.ensureCapacity(p.size());
        /*NESTED LOOPS*/
        /*SIMPLE SELECTION*/
        /*ANVANCED SELECTION*/
        /*LOOPS*/
        for(int n = 0; n<p.size(); n++){
            int intersection = 0;
            ArrayList<Integer> nnn = new ArrayList<Integer>();
            for(int s = 0; s<(p.get(n).size()-1);s++){
               Line2D l1 = new Line2D.Double(p.get(n).get(s)[0], p.get(n).get(s)
                   [1], p.get(n).get(s+1)[0] , p.get(n).get(s+1)[1]);
               for(int z = 0; z<p.size();z++){
                   if(z!=n&&!nnn.contains(z))
                       stop:for(int q = 0; q<(p.get(z).size()-1);q++){
                           Line2D l2 = new Line2D.Double(p.get(z).get(q)[0], p
                                   .get(z).get(q)[1], p.get(z).get(q+1)[0]
                                   , p.get(z).get(q+1)[1]);
                               /*SEARCHING*/
                               if(l1.intersectsLine(l2)){
                                   intersection++;
                                   nnn.add(z);
                                   /*SENTINELS OR FLAGS*/
                                   break stop;
                               }
                       }

               }
            }
            i.add( intersection);
        }
        return i;
    }
    /**
     * required method of the ChineseManager
     * retrns the intersections without updating the data.
     * @return the Arraylist Integar of intersections
     */
    @Override
    public ArrayList<Integer> returnValue() {
        return intersections;
    }
    /**
     * updates the universalDataStorage's intersections. It also
     * processes the data
     */
    public void updateManager() {
        intersections = processData(chineseCharacter);
    }
}
