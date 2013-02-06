/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/ChineseFrameActionHandler.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.componentcreator.drawpanel.InternalDrawFramePanel;
import gui.mainframe.componentcreator.options.FloatingPartsCounterPanel;
import gui.mainframe.componentcreator.options.IntersectionStringInputPanel;
import gui.mainframe.componentcreator.options.IntersectionsCrudeAmoutPanel;
import gui.mainframe.componentcreator.options.StrokeComplexityPanel;
import gui.mainframe.componentcreator.options.StrokeCrudeCountPanel;
import gui.mainframe.componentcreator.databasecreator.DatabaseCreatorPanel;
import universals.LogManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javax.swing.event.CaretEvent;
import javax.swing.event.ChangeEvent;
import mechanics.ChineseCharacter;
import mechanics.database.DatabaseCreator;
import mechanics.database.SearchEngine;
import universals.UniversalDataStorage;// </editor-fold>
/**
 * This is another "static method class" where all of the methods are static.
 * This class has a variety of events used by the ChineseFrame
 * @version 1.5
 * @author Kieda
 * @since 2-25-2011
 *//*INHEIRITANCE*/
public class ChineseFrameActionHandler extends ChineseFrame{
    /**
     * the event for the exit button; exits the program
     * @param evt the event that is passed on by new ActionListener(){...}
     */
    public static void exitMenuButtonActionPerformed(ActionEvent evt) {
        exit();//passes to exit(), quits program
    }
    /**
     * exits the program
     */
    public static void exit(){
        ChineseFrame.kill();//passes to kill(), which exits the program
    }
    /**
     * the event for the strokes counter; updates the numberOfStrokes
     * @param evt the event that is passed on by new ActionListener(){...}
     */
    public static void numberOfStrokesCounterStateChanged(ChangeEvent evt) {
        UniversalDataStorage.numberOfStrokes = StrokeCrudeCountPanel
            .getNumberOfStrokes();
                //sets the numberOfStrokes
        SearchEngine.searchForChineseCharacter();
            //searches if the character exists
    }
    /**
     * the event for the floating parts counter; updates the
     * numberOfFloatingParts
     * @param evt the event that is passed on by new ActionListener(){...}
     */
    public static void numberOfFloatingPartsCounterPropertyChange(
            PropertyChangeEvent evt) {
        UniversalDataStorage.numberOfFloatingParts = FloatingPartsCounterPanel
            .getNumberOfFloatingParts();
                //sets the numberOfFloatingParts
        SearchEngine.searchForChineseCharacter();
            //searches if the character exists
    }
    /**
     * the event for the intersections total counter; updates the
     * totalNumberOfIntersections
     * @param evt the event that is passed on by new ActionListener(){...}
     */
    public static void totalNumberOfIntersectionsCounterPropertyChange(
            PropertyChangeEvent evt) {
        UniversalDataStorage.totalNumberOfIntersections = 
            IntersectionsCrudeAmoutPanel.getTotalNumberOfIntersections();
                //sets the totalNumberOfIntersections
        SearchEngine.searchForChineseCharacter();
            //searches if the character exists
    }
    /**
     * the event for mouse pressed in the internalDrawFrame;
     * updates the x and y position of the mouse
     * @param evt the event that is passed on by new ActionListener(){...}
     */
    public static void characterDrawInternalFrameMousePressed(MouseEvent evt) {
        UniversalDataStorage.getXMouseDrawing = evt.getX();
            //sets the getXMouseDrawing
        UniversalDataStorage.getYMouseDrawing = evt.getY();
            //sets the getYMouseDrawing
    }
    /**
     * the event for the straight strokes counter;
     * updates the numberOfStraightStrokes
     * @param evt the event that is passed on by new ActionListener(){...}
     */
    public static void numberOfStraightStrokesCounterPropertyChange(
            PropertyChangeEvent evt) {
        UniversalDataStorage.numberOfStraightStrokes = StrokeComplexityPanel
            .getNumberOfStraightStrokes();
                //sets the numberOfStraightStrokes
        SearchEngine.searchForChineseCharacter();
            //searches if the character exists
    }
    /**
     * the event for the complex strokes counter;
     * updates the numberOfComplexStrokes
     * @param evt the event that is passed on by new ActionListener(){...}
     */
    public static void numberOfComplexStrokesCounterPropertyChange(
            PropertyChangeEvent evt) {
        UniversalDataStorage.numberOfComplexStrokes = StrokeComplexityPanel
            .getNumberOfComplexStrokes();
                //sets the numberOfComplexStrokes
        SearchEngine.searchForChineseCharacter();
                //searches if the character exists
    }
    /**
     * the event for the intersections JTextField; updates the intersections
     * @param evt the event that is passed on by new CaretListener(){...}
     */
    public static void intersectionsStringInputCaretUpdate(CaretEvent evt) {
        if(!IntersectionStringInputPanel.getIntersectionsString().isEmpty()
                &&IntersectionStringInputPanel.getIntersectionsString()
                .contains(",")){//ensures the string input is valid
            UniversalDataStorage.intersections = new ArrayList<Integer>();
                //clears the universal intersections
            String[] intersectionsInput = IntersectionStringInputPanel
                .getIntersectionsString().split(",");
                    //splits up the string inputted to an array
            for(String intersection: intersectionsInput)
                //goes through all values inputted
                try {
                    UniversalDataStorage.intersections.add(Integer.parseInt(
                        intersection));
                            //adds the integer value of the array string
                            //inputted to the intersections
                } catch (Exception e) {LogManager.logError(e,"CANNOT PARSE " +
                    "STRING IN INPUT TEXTBOX", 141
                    , "FinderFrameActionHandler");
                        //logs error if someone puts in an invalid string
                }
            SearchEngine.searchForChineseCharacter();
                //searches if the character exists
        }
    }
    /**
     * for the addtodatabase button; adds a string of line to the database,
     * preferabley an actual chinese character (otherwise errors!)
     * @param evt the event that is passed on by new ActionListener(){...}
     * @param characterToWrite the character to add to the database
     */
    public static void addToDatabaseActionEvent(ActionEvent evt
            , String characterToWrite){
        DatabaseCreatorPanel.setCharacterToWrite(DatabaseCreator.next());
            //goes to the next character to write into the database
        InternalDrawFramePanel.clearPanel();
            //clears the InternalDrawFramePanel
        UniversalDataStorage.character = characterToWrite.split(":")[0];
            //gets the String chinese character
        UniversalDataStorage.meaning = characterToWrite.split(":")[1];
            //gets the String of the meaning of the character in english
        UniversalDataStorage.pinyin = characterToWrite.split(":")[2];
            //gets the String of pinyin
        DatabaseCreator.add(new  ChineseCharacter(UniversalDataStorage.character
            , UniversalDataStorage.meaning
            , UniversalDataStorage.pinyin
            , UniversalDataStorage.numberOfFloatingParts
            , UniversalDataStorage.intersections
            , UniversalDataStorage.numberOfStraightStrokes
            , UniversalDataStorage.numberOfComplexStrokes));
                //adds the new chinese character to the database
        System.out.println(DatabaseCreatorPanel.getCharacterToWrite());
            //prints out the next character to write
    }
    /**
     * the event for the clear button; clears the InternalDrawFramePanel
     * @param evt the event that is passed on by new ActionListener(){...}
     */
    public static void clearActionPerformed(ActionEvent evt) {
        InternalDrawFramePanel.clearPanel();
            //clears the InternalDrawFramePanel
    }
    /**
     * the event for the next button; goes to the next character for the
     * database CharacterFrame
     * @param evt the event that is passed on by new ActionListener(){...}
     */
    public static void nextButtonActionPerformed(ActionEvent evt) {
        DatabaseCreatorPanel.setCharacterToWrite(DatabaseCreator.next());
            //sets the next character to write
        InternalDrawFramePanel.clearPanel();
            //clears the InternalDrawFramePanel
        System.out.println(DatabaseCreatorPanel.getCharacterToWrite());
            //prints out the next character to write
    }
}
