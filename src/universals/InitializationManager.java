/*//////////////////////////////////////////////////////////////////////////////
./src/universals/InitializationManager.java
//////////////////////////////////////////////////////////////////////////////*/
package universals;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.ChineseFrame;
import java.awt.Font;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import mechanics.database.DatabaseLoader;// </editor-fold>

/**
 * This class is used to initialize certain mechanics for character recognition 
 * in this project. Add a task to be processed by the addTask, and the n execute
 * the queued tasks by clling processTasks(). Frome the time of the task's
 * initialization to the task's completion, a JFrame pops up notifying the user
 * that the process in underway.  This class DOES NOT initialize GUI components.
 * THis class can currently handle two tasks: Loading the database or
 * LoadingFonts. THese tasks are given by the final value LOAD_DATABASE or
 * LOAD_FONTS, and are used in the declaration of a task to do.
 *
 * LOAD_DATABASE: load the files in the text document which contains information
 *     about the database/characters that are being searched for and stores them
 *     into the RAM for faster data processing
 *
 * LOAD_FONTS: Searches all of the system fonts that can read Chinese characters
 *     and adds them to a vector of fonts that are used later in the program.
 * 
 * @author Kieda
 * @version 2.2
 * @since 3-22-2011
 */
public class InitializationManager {
    public final static int LOAD_DATABASE = 0;
        //the value to call when queuing a task to execute loading the database
    public final static int LOAD_FONTS = 1;
        //the value to call when queuing a task to execute loading the readable
        //Chinese fonts
    /*USE OF ABSTRACT DATA TYPES: QUEUE*/
    private static Queue<Integer> tasks = new ConcurrentLinkedQueue();
        //the tasks that are to be executed. THese tasks are of the value
        //LOAD_DATABASE or LOAD_FONTS.
    private static Queue<String> description = new ConcurrentLinkedQueue();
        //THe descriptions displayed on the JFrame when a new task is being
        //processed
    
    private static boolean poppedUp = false;
        //whether or not the JFrame is popped up. THis is used to ensure that a
        //JFrame does not pop up more than once
    private static boolean firsttime = true;
        //used to inititialize the program for the first time

    private static JFrame loadingFrame;
        //the JFrame that displays messages
    private static JLabel message;
        //the JLabel message on the message frame

    /**
     * Call this task to queue another task for execution when processTasks() is 
     * called.
     * 
     * @param q the value for the task to be processed. For example, to load the
     *     database, the integer 0 is used. To load the fonts, the integer 1 is
     *     used.
     * @param descript the description to be show on the JFrame. Currently, to
     *     get spacing, put spaces in the front of the string
     */
    public static void addTask(int q, String descript){
        if(firsttime){
            showMessage(descript);
                //if this is the first time running, create the message frame
            try {
                Thread.sleep(500);
                    //pauses for a breif period of time to make sure that the
                    //text doesn't go too fast
            } catch (InterruptedException ex) {}
            firsttime = false;
        }
        if(!poppedUp){
            loadingFrame.setVisible(true);
                //if the frame isn't already popped up, pop open the frame when
                //a new task is added
            poppedUp = true;
        }
        tasks.add(q);//add the task to be executed to the queue
        description.add( descript);
            //add the corresponding string description to another queue
    }

    /**
     * THis method is called to process all of the tasks queud up.
     * Call this method after a series of tasks has been added.
     */
    public static void processTasks(){
        do{
            switch(tasks.remove()){
                    //removes the task to execute from the top of the queue
                case LOAD_DATABASE:
                    changeMessage(description.remove());
                        //changes the JLabel in the frame
                    DatabaseLoader.load();
                        //loads the database
                    break;
                case LOAD_FONTS:
                    changeMessage(description.remove());
                        //changes the JLabel in the frame
                    FontFinder.findFonts();
                        //finds the usable chinese fonts
                    break;
                //ponential for other tasks
                default:
                    System.out.println("TASK DOES NOT EXIST");
                        //if somone tries to enter an invalid task...
                    ChineseFrame.kill();
                        //exits the program
            }
        }while(!tasks.isEmpty());//goes through all of the data in the queue
        closeMessage();//closes the message after everything was processed
    }

    /**
     * Creates a new JFrame with the given starting message.
     * This method should be called on the first run
     * @param messageString the original string for the frame to have
     */
    public static void showMessage(String messageString){
        loadingFrame = new JFrame("LOADING...");
        loadingFrame.setResizable(false);
            //creates a new, unresizable frame
        message = new JLabel(messageString);
        loadingFrame.setBounds(200, 200, 400, 200);
        message.setFont(new Font("Arial", Font.BOLD, 30));
        message.setLocation(90, 200);
        loadingFrame.add(message);

        loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            //makes the Frame unclosable (the window is only up for a short
            //period of time)
        loadingFrame.setVisible(true);
    }

    /**
     * Changes the message in the message frame. this should be called when the
     * program switches to a new task.
     * @param messageString the message to display on the loading frame
     */
    public static void changeMessage(String messageString){
        message.setText(messageString);//resets the text
    }
    /**
     * "closes" the message by setting the jframe to not visible
     */
    public static void closeMessage(){
        poppedUp = false;//sats that it is not popped up
        loadingFrame.setVisible(false);//sets the frame to not visible
    }
}
