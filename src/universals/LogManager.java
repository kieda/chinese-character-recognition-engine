/*//////////////////////////////////////////////////////////////////////////////
./src/universals/LogManager.java
//////////////////////////////////////////////////////////////////////////////*/
package universals;

// <editor-fold defaultstate="collapsed" desc="imports">
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;// </editor-fold>

/**
 * This class manages output to a log. This is a "static method" class so
 * LogManager does not need to be instantiated to log things
 * @version 4.2
 * @author Kieda
 * @since 3-14-2011
 *//*FILE I/O ALL OVER HERE*/
   /*ENCAPSULATION*/
   /*PARSING TEXT*/
public class LogManager {
    public static final String logsPath = "./logs/";//default path to the logs
    public static final String OLD_ERR_PATH_PRE_2_0 = "./logs/ERR.log";
    public static final String OLD_ERR_PATH_2_0 = "./logs/ERR_V2-0.log";
        //older error log path name
    public static final String DEFAULT_ERR_PATH = "./logs/ERR_V3-0.log";
        //default error log path (for this version)
    public static final String DEFAULT_LOG_PATH = "./logs/LOGS_V3-0.log";
        //default log path
    public static final String DATABASE = "characters.DAT";//Database path

    private static ArrayList<String> logPaths = new ArrayList<String>();
        //the path of the logs being used
    private static ArrayList<BufferedWriter> writers =
        new ArrayList<BufferedWriter>();
            //all of the writes used to write to files

    /**
     * simply logs an error and the time it occured.
     * @param e the error to log (from the catch)
     */
    public static void logError(Exception e){
        logError(e.toString()+"; "+e.getCause(),TimeManager
            .getCurrentTimeAndDate());
                //logs error
    }
    /**
     * This method logs an error, the time it occured, the description, and the
     * place where the error happened
     * @param e the error to log (from the catch)
     * @param description the description of the error that occured (can be user
     *        defined)
     * @param lineNumber the line number of which the error occured
     * @param classOfException the class which called the logError
     * @param print whether or not to printStackTrace()
     */
    public static void logError(Exception e, String description, int lineNumber
            , String classOfException, boolean print){
        if(print)//if the error is to be printed
            e.printStackTrace();//print the error
        logError(e.toString()+"; "+e.getCause(),TimeManager
            .getCurrentTimeAndDate() + "; "+description + "; at line "
            +lineNumber+" of "+ classOfException);
                //compiles data to string, passes to logError(String thing
                //, String time)
    }
    /**
     * similar to logError(Exception e, String description, int lineNumber,
     * String classOfException, boolean print),
     * except this does not print anything (only logs it)
     * @param e the error to log (from the catch)
     * @param description the description of the error that occured (can be user
     *        defined)
     * @param lineNumber the line number of which the error occured
     * @param classOfException the class which called the logError
     */
    public static void logError(Exception e, String description, int lineNumber
            , String classOfException){
        logError(e.toString() + "; " + e.getCause(), TimeManager
            .getCurrentTimeAndDate() + "; " + description + "; at line "
            +lineNumber + " of " + classOfException);
                //compiles data to string, passes to logError(String thing,
                //String time)
    }
    /**
     * similar to logError(Exception e, String description, int lineNumber
     * , String classOfException), except that an object is used and is logged
     * @param e the error to log (from the catch)
     * @param description the description of the error that occured (can be user
     *        defined)
     * @param lineNumber the line number of which the error occured
     * @param classOfException the class which called the logError, in Object
     *        form
     */
    public static void logError(Exception e, String description, int lineNumber
            , Object classOfException){
        logError(e.toString( )+ "; " + e.getCause(),TimeManager
            .getCurrentTimeAndDate() + "; " + description + "; at line "
            + lineNumber + " of "+ classOfException.getClass().getSimpleName());
                //compiles data to string, passes to logError(String thing
                //, String time)
    }
    /**
     * simply logs an error and has a choice to print
     * @param e the error to log (from the catch)
     * @param print whether or not to printStackTrace()
     */
    public static void logError(Exception e, boolean print){
        if(print)//if the error is to be printed
            e.printStackTrace();//print the error
        logError(e.toString(),TimeManager.getCurrentTimeAndDate());
            //compiles error to string, passes to logError(String thing
            //, String time)
    }
    /**
     * logs something into the default log path
     * @param thing String to be logged
     */
    public static void logThing(String thing){
        logThing(thing,DEFAULT_LOG_PATH);
            //passes to logThing(String thing,String location)
    }
    /**
     * logs a string to a specific location
     * @param thing the String to be logged
     * @param location the location for the String to be logged
     */
    public static void logThing(String thing,String location, String time){
        logThing(time+"--\t"+thing,location);
            //passes to logThing(String thing, String location, String time)
    }
    /**
     * logs a String to a specific time
     * @param thing the String to be logged
     * @param time the time recorded
     */
    public static void logError(String thing, String time){
        logThing(time+"--\t"+thing,DEFAULT_ERR_PATH);
            //passes to logThing(String thing, String location, String time)
    }
    /**
     * adds a line to the end of a log. THis should be called when something
     * should be wrote to a database, error log, general log, or any other type
     * of log.
     *
     * The database currently works through a system of adding logs to assigned
     * writers. No need for adding a log each time you want to write to
     * something new! Simply call this method and it will add the log if you
     * hadn't already.
     * 
     * @param thing the String to be logged
     * @param location the location for the String to be logged
     */
    public static void logThing(String thing, String location){
        try {//try catch needed for logging anything, error handling
            if(!logPaths.contains((new File(location).getAbsolutePath()))){
                    //if the directory of logs we are currently using does not
                    //have the log we are trying to write to...
                addLog(location);//add/load the log
            }
            for(int i = 0; i<logPaths.size();i++){
                    //goes through all of the logs
                    //to find a match to the one you're trying to write to
                if(logPaths.get(i).equals((new File(location)
                        .getAbsolutePath()))){
                            //once there is a match (match done by string of
                            //absolute path because it is reliable)...
                    writers.get(i).write(thing);
                        //write what you wnt to to the log
                    writers.get(i).newLine();
                        //adds a new line at the end of the log
                }
            }
        } catch (IOException ex) {/*DO NOT LOG ERRORS HERE
                                  (INFINITE LOOPS SUCK)*/}
    }
    /**
     * Adds a log via string input of the log's path. This is used when there
     * are string values like "DEFAULT_ERR_PATH" etc.
     * @param s the String filepath of the log's location
     */
    public static void addLog(String s){
        addLog(new File(s));//adds a new file
    }
    /**
     * adds a log through a given filepath
     * @param f the file folipath of the log's location
     *//*MAJOR FILE I/O*/
    public static void addLog(File f){
        logPaths.add(f.getAbsolutePath());
            //adds the absolute path of the log to the array of filepaths
        try {//try catch for error handling
            // <editor-fold defaultstate="collapsed" desc="initialization">
            ArrayList<String> originalLog = new ArrayList<String>();
                //creates  a string of all of the files in the original log
            Scanner in = new Scanner(f);
                //the input for reading the file
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="loading file to be logged into memory">
            while (in.hasNext()) {
                //goes through all the previous lines in a log
                originalLog.add(in.nextLine());
                    //adds the file to be written to an ArrayList<String>
            }// </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="rewriting the file">
            writers.add(new BufferedWriter(new FileWriter(f)));
                //makes a new FileWriter to create the log
                //makes a BufferedWriter to write to the log
            for (String line : originalLog) {
                //goes through all of the original lines
                writers.get(writers.size()-1).write(line);//rewrites the log
                writers.get(writers.size()-1).newLine();
                    //makes a new line for each line in ArrayList
            }
            // </editor-fold>
        } catch (FileNotFoundException ex) {}
          catch (IOException ex) {}
    }
    /**
     * THis method is important to call ALLWAYS before closing the program. THis
     * method closes all of the Writers being used. Closing the program without
     * closing these writers will result ina loss of sata of logs
     * @throws IOException for error handling
     */
    public static void closeAllWriters() throws IOException{
        for(int i = 0; i<writers.size();i++)
            writers.get(i).close();//closes all the writers
    }
    /**
     * this returns the string path of the logs that are currently being used.
     * THis is used to allow other classes access to veiwing the logs that are
     * being used, but not to modify them.
     * @return the logs that are currently being used
     *//*ENCAPSULATION*/
    public ArrayList<String> getLogsInUse(){
        return logPaths;
    }
}
