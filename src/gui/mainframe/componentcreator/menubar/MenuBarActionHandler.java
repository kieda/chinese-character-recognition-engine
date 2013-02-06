/*//////////////////////////////////////////////////////////////////////////////
./gui/mainframe/componentcreator/menubar/MenuBarActionHandler.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator.menubar;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.ChineseFrameActionHandler;
import gui.mainframe.componentcreator.drawpanel.InternalDrawFramePanel;
import java.awt.event.ActionEvent;
import universals.InitializationManager;// </editor-fold>

/**
 * THe MenuBarActionHandler
 * @author Kieda
 * @version 2.5
 * @since 3-20-2011
 *//*INHEIRITANCE*/
public class MenuBarActionHandler extends MenuBar{
    public static boolean penOrEraser = true;//the state of the pen or eraser
    /**
     * exits the program. Called on by the menubar's close button
     * @param evt the event that occured
     */
    public static void exitActionPerformed(ActionEvent evt) {
        ChineseFrameActionHandler.exitMenuButtonActionPerformed(evt);
    }
    /**
     * clears the drawpanel and the data points. Called on by the menubar's 
     * clear button
     * @param evt the event that occured
     */
    public static void clearActionPerformed(ActionEvent evt) {
        InternalDrawFramePanel.clearPanel();
    }
    /**
     * pan and eraser should be implemented soon
     * @param evt the event that occured
     */
    public static void penStateChanged(ActionEvent evt) {
        MenuBar.penOrEraser(penOrEraser);
    }
    public static void eraserStateChanged(ActionEvent evt) {
        penOrEraser = false;
        MenuBar.penOrEraser(!penOrEraser);  
    }
    /**
     * Reloads the database into the program's ram. Called on by the menubar's
     * reload button
     * @param evt the event that occured
     */
    public static void reloadDatabase(ActionEvent evt) {
        InitializationManager.addTask(InitializationManager.LOAD_DATABASE
            ,"     Loading Database");
                //adds the task to load the database
        InitializationManager.processTasks();
            //loads the database
    }
}
