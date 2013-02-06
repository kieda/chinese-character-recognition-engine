/*////////////////////////////////////////////////////////////////////////>>Main
./src/drivers/DatabaseMain.java
//////////////////////////////////////////////////////////////////////////////*/
/*************************A NOTE ON GENERAL COMMENTING**************************
 * class name/ path is notated by that thing at the top of this class, if the
 * class is a main, ">>Main" will be in the top right corner (like above)
 *
 * comments depend on how log each line is
 * each line in this program is attempted to stay on one line for each page
 * for readability, pleasing graders, etc...
 * Block comments are in the Netbeans/javadoc style, and are generally put
 *     before methods
 * lines that are too long in block comments are tabbed in (see ↑), (if the line
 *     is independent. if the block comment is a paragraph, the comment is one
 *     big block.)
 *
 * line comments are different from what you'll normally expect (maybe), but
 *     it's a system I like. Instead of explaining everything, i'll give some
 *     examples (i'm sure you'll understand)
 *
 * line of code 1;//comment 1
 *
 * really really really really really really really long line of code 2;
 *     //comment 2
 *
 * really really really really really really really really really long line of
 *     code 3;
 *         //comment 3
 *
 * line of code 4;
 *     //really really really really really really really really long comment 4
 *
 * line of code 5;
 *     //really really really really really really really really really long
 *     //comment 5
 *
 * really really really really really really really really really long line of
 *     code 6;
 *         //really really really really really really really really really long
 *         //comment 5
 *
 * each tab is (and probably all is) four spaces wide
 *
 * a note on V 3.0 commenting: not all of the speling is checked/corected :)
 *
 * another note on comments:
 *      @version Is the version number of the specific class.
 *               Version number goes up if the program is modified
 *      @author  Is the author of the code (me) notated as "Kieda"
 *      @since   Is the the last day the class was edited (including comments!)
 * *******************TOTAL LINES IN THIS PROJECT: alot and counting************
 * *******************AS OF THE END OF BETA V_3.0: LINES************************
 */

package drivers;//package, etc...
// <editor-fold defaultstate="collapsed" desc="Imports">
import gui.mainframe.ChineseFrame;
import gui.mainframe.componentcreator.ChineseFrameComponent;
import java.awt.EventQueue;
import universals.LogManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mechanics.database.DatabaseCreator;
import universals.InitializationManager;
import universals.TimeManager;// </editor-fold>
/**
 * Main class for the Chinese Character Finder Project
 * This is the starting positon to create a database
 * @version 1.9
 * @author Kieda
 * @since 2-26-2011
 */
// <editor-fold defaultstate="collapsed" desc="Main">
public class DatabaseMain {
    /*USER DEFINED OBJECTS*/
    public static ChineseFrame f;//calls a new ChineseFrame. This is the main
                                 //GUI that runs this program
    /**
     * main: the start of the program
     * initializes the ChineseFrame to the Database Frame
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
            LogManager.logError(e,"System LookAndFeel unsupported in java", 92
                , "Main", true);
                    //logs and prints if the OS look and feel is unsupported
        }
        //prints error and logs it if there is a UnsupportedLookAndFeelException
        catch (ClassNotFoundException e) {
            LogManager.logError(e,"Cannot Find System's LookAndFeel", 98, "Main"
                , true);
                    //logs and prints error if it Cannot Find the system's
                    //LookAndFeel
        } catch (InstantiationException e) {
            LogManager.logError(e,"Cannot System's Instantiate LookAndFeel", 103
                , "Main");
                    //logs error if it cannot instantiate the system's
                    //LookAndFeel
        } catch (IllegalAccessException e) {
            LogManager.logError(e,"Cannot Access System's LookAndFeel", 108
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
        /*Creates a new DatabaseCreator****************************************/
        EventQueue.invokeLater(new Runnable() {
            //starts a new frame in a Runnable
            public void run() {
                f = new ChineseFrame();
                    //initializes ChineseFrame
                f.initFrame(ChineseFrameComponent.DATABASE);
                    //initialized outside of frame to prevent errors, Creates a
                    //database ChineseFrame
                f.setVisible(true);
                    //makes the frame visible
            }
        });
    }
}// </editor-fold>


