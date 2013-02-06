/*////////////////////////////////////////////////////////////////////////>>Main
./src/drivers/FinderMain.java
//////////////////////////////////////////////////////////////////////////////*/
/*
 * Confused about the commenting techniques? Go to the DatabaseMain in package
 * drivers for more clarification.
 */
package drivers;

// <editor-fold defaultstate="collapsed" desc="Imports">
import gui.mainframe.ChineseFrame;
import gui.mainframe.componentcreator.ChineseFrameComponent;
import universals.LogManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mechanics.database.DatabaseCreator;
import universals.InitializationManager;
import universals.TimeManager;// </editor-fold>

/**
 * Main class for the Chinese IB Project
 * This is where everything begins...
 * @version 1.9
 * @author Kieda
 * @since 2-26-2011
 */
// <editor-fold defaultstate="collapsed" desc="Main">
public class FinderMain {
    public static ChineseFrame f;//calls a new ChineseFrame. This is the main
                                 //GUI that runs this program
    /**
     * main: the start of the program
     * initializes the ChineseFrame to the Finder Frame
     * @param args arguements for the command line
     */
    public static void main(String args[]) {//start of main
        LogManager.logThing(TimeManager.getCurrentTimeAndDate()+
                "--\tPROGRAM STARTING UP");
            //First logs that the program has started in the logmanager
        /*Adjusts the Default look&feel to be native to the OS*****************/
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                //Sets the look & feel to be the OS default
        } catch (UnsupportedLookAndFeelException e) {
            LogManager.logError(e,"System LookAndFeel unsupported in java", 45
                , "Main", true);
                    //logs and prints if the OS look and feel is unsupported
        }
        //prints error and logs it if there is a UnsupportedLookAndFeelException
        catch (ClassNotFoundException e) {
            LogManager.logError(e,"Cannot Find System's LookAndFeel", 51, "Main"
                , true);
                    //logs and prints error if it Cannot Find the system's
                    //LookAndFeel
        } catch (InstantiationException e) {
            LogManager.logError(e,"Cannot System's Instantiate LookAndFeel", 56
                , "Main");
                    //logs error if it cannot instantiate the system's
                    //LookAndFeel
        } catch (IllegalAccessException e) {
            LogManager.logError(e,"Cannot Access System's LookAndFeel", 61
                , "Main");
                    //logs error if it cannot access the system's LookAndFeel
                    //(Probably due to restrictions, etc)
        }
        DatabaseCreator.init();
            //initializes the DatabaseCreator so it can be added to the frame
            //initialized outside of frame to prevent errors
        InitializationManager.addTask(InitializationManager.LOAD_DATABASE
            ,"     Loading Database");
                //loads all of the Characters into the Java Virtual Machine
                //increases speed
        InitializationManager.addTask(InitializationManager.LOAD_FONTS
            ,"     Finding Chinese Fonts");
                //finds and records all of the fonts that can properly
                //display Chinese Characters
        InitializationManager.processTasks();
            //processes the tasks
        /*Creates a new ChineseFrame*******************************************/
        java.awt.EventQueue.invokeLater(new Runnable() {
            //starts a new frame in a Runnable
            public void run() {
                f = new ChineseFrame();
                    //initializes ChineseFrame
                f.initFrame(ChineseFrameComponent.FINDER);
                    //initialized outside of frame to prevent errors, Creates a
                    //finder ChineseFrame
                f.setVisible(true);
                    //makes the frame visible
            }
        });
    }
}// </editor-fold>
