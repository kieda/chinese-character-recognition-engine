/*//////////////////////////////////////////////////////////////////////////////
./src/universals/CreatedCharacter.java
//////////////////////////////////////////////////////////////////////////////*/
package universals;

// <editor-fold defaultstate="collapsed" desc="imports">
import java.awt.geom.Line2D;
import java.util.ArrayList;// </editor-fold>

/**
 * This class is similar to the ChineseStoke class, by the fact that it is used
 * for coding purposes, so instead of calling ArrayList<ArrayList<Integer[]>>
 * each time, one only has to call CreatedCharacter
 * This class has one custom method, erase(ChineseStroke cs), which removes the
 * strokes off of the CreatedCharacter based on the intersections of a different
 * ChineseStroke
 * @author Kieda
 * @since 3-13-2011
 *//*USER DEFINED OBJECTS*/
public class CreatedCharacter extends ArrayList<ArrayList<Integer[]>>{
                                        //ArrayList<Stroke>
                                                  //Stroke<PointsOnStroke>
                                                         //PointsOnStroke[x,y]
    /**
     * Erases lines within the CreatedCharacter based on a given erasing
     * ChineseStroke
     *
     * Call this method if an erase function is implemented
     * @param cs the eraser path that will erase ant ChineseStrokes in its path
     */
    public void erase(ChineseStroke cs){
        /*NESTED LOOPS, ADVANCED SELECTION*/
        /*MERGING TWO OR MORE DATA STRUCTURES*/
        if(size()>1){
                //checks if there are more than one ChineseStroke in the
                //CreatedCharacter; otherwise errors could occur
            for(int s = 0; s<(cs.size()-1);s++){
                    //goes around all of the strokes
                Line2D l1 = new Line2D.Double(cs.get(s)[0], cs.get(s)[1]
                    , cs.get(s+1)[0] , cs.get(s+1)[1]);
                        //creates a linebetween the given points
                        //goes through all of the lines in a stroke
                for(int z = 1; z<size();z++){
                        //goes through all of the strokes in the character being
                        //analyzed
                    for(int q = 0; q<(get(z-1).size()-1);q++){
                            //goes through all of the lines of the character
                            //being analyzed
                        Line2D l2 = new Line2D.Double(get(z).get(q)[0], get(z)
                            .get(q)[1], get(z).get(q+1)[0] , get(z)
                            .get(q+1)[1]);
                                //creates lines between the points of the stroke
                                //being analyzed
                        if(l1.intersectsLine(l2)){
                                //if there is an intersection between the two
                                //lines
                            remove(z);
                                //remove the stroke that intersected with the
                                //eraser marker
                        }
                    }
                }
            }
        }
    }
}
