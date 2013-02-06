/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/recognition/managers/FloatingPartManager.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics.recognition.managers;

// <editor-fold defaultstate="collapsed" desc="imports">
import java.awt.geom.Line2D;
import java.util.ArrayList;
import mechanics.recognition.ChineseManager;
import universals.UniversalDataStorage;
import universals.CreatedCharacter;// </editor-fold>

/**
 * THe floating part manager uses an assortment of algorithms to figure out the 
 * number of foalting partsof a given character. A floating part is a collection
 * of strokes that do not ouch any other strokes but themselves. THis class
 * implements the ChineseManager, which is the main interface that controls the
 * @version 1.9
 * @author Kieda
 * @since 3-21-2011
 *//*INHEIRITANCE*/
   /*ABSTRACT DATA IMPLEMENTATION*/
public class FloatingPartManager extends UniversalDataStorage
        implements ChineseManager{
    private CreatedCharacter p;//the created character inputted
    private int floatingparts;//the number of floating parts
    private ArrayList<Integer> indexed;//the strokes indexed/alerady reviwed
    private ArrayList<Integer> queue;//the queue of srtokes to review

    /**
     * simply returns the value of the number of floating parts when last 
     * updated. does not update the current number of floating parts.
     * @return the number of floating parts
     */
    @Override
    public Integer returnValue() {
        return numberOfFloatingParts;
    }
    /**
     * gives the next character to do when the queue is all empty. When the
     * queu is all empty, a jump must be made to the next floating part. This
     * method does so by picking the next interger that is not indexed
     * @param i the list of strokes already indexed
     * @param length the number of strokes in the character being analyzed
     * @return the next stroke that should be analyzed. If the stroke is -1,
     *         there are no more strokes left to be analyzed. a flag should be
     *         thrown to return the number of intersections
     */
    private int nextToDo(ArrayList<Integer> i, int length){
        for(int count = 0; count<length;count++){
                //goes through the list of characters already done
            if(!i.contains(count))
                return count;
                    //if a certain character has not been done yet, do that
                    //chatacter
        }
        return -1;
            //if there are no strokes left to do, show a dummy flag to end the
            //sequence
    }
    /**
     * processes the number of intersections via a recursion algorithm
     * @param index the starting index
     * @return the number of floating parts
     *//*RECURSION*/
    private Integer processData(int index){
        /*NESTED LOOPS*/
        /*SIMPLE SELECTION*/
        /*ANVANCED SELECTION*/
        /*LOOPS*/

        /*USE OF FLAGS*/
        if(index==-1)
            return floatingparts;
                //use of flags to symbolize the end of data processing for
                //floating parts
        
        /*NESTED LOOPS, LOOPS, ADVANCED SELECTION, SIMPLE SELECTION*/
        if(p.size()>0)
                //prevents errors when geting an index out of range
                //also workes to tell the user they only have one floating part
                //when they make one single stroke
            if(!indexed.contains(index)){//skip this if we already indexed this
                indexed.add(index);
                    //add the current index to the indexes processed
                for(int s = 0; s<(p.get(index).size()-1);s++){
                        //goes through all of the lines on the current stroke
                    Line2D l1 = new Line2D.Double(p.get(index).get(s)[0]
                        , p.get(index).get(s)[1], p.get(index).get(s+1)[0]
                        , p.get(index).get(s+1)[1]);
                            //current line on the current stroks
                    for(int z = 0; z<p.size();z++){
                            //compares the current line in the current character
                            //to all of the othe lines
                        if(z!=index)//except for itself
                            for(int q = 0; q<(p.get(z).size()-1);q++){
                                    //goes through all of the lines in the other
                                    //strokes
                                Line2D l2 = new Line2D.Double(p.get(z).get(q)
                                    [0], p.get(z).get(q)[1], p.get(z)
                                    .get(q+1)[0] , p.get(z).get(q+1)[1]);
                                        //makes a second stroke to compare
                                if(l1.intersectsLine(l2)){
                                        //if there is an intersection
                                    if(!indexed.contains(z)){
                                            //and we didn't already index the
                                            //stroke (to prevent loops)
                                        queue.add(z);
                                            //add the stroke intersected to the queue
                                    }
                                }
                            }
                    }
                }
            }
        if(queue.size()>0)//something is in the queue to process
            /*RECURSION*/
            processData(queue.remove(queue.size()-1));
                //goes to the next item in the queue
        else{//if nothing is in the wueue
            floatingparts++;
            /*RECURSION*/
            processData(nextToDo(indexed, p.size()));
                //jump to the next floating part
        }
        return floatingparts;
    }
    /**
     * the method called to process data on a CreatedCharacter. Calculations
     * dont actually happen in this method. This method is called to initialize
     * the data
     * @param pp the ChineseCharacter to process
     * @return the number of floating parts
     */
    public Integer processData(CreatedCharacter pp) {
        floatingparts = 0;//resets the number of floating parts
        indexed = new ArrayList<Integer>();//resets the index
        queue = new ArrayList<Integer>();//resets the queue
        p = pp;//sets the CreatedCharacter
        return processData(0);//processes the data given
    }
    /**
     * updates the universalDataStorage's number of floating parts. It also
     * processes the data
     */
    public void updateManager() {
        numberOfFloatingParts = (processData(chineseCharacter));
    }
}
