/*//////////////////////////////////////////////////////////////////////////////
./src/universals/TimeManager.java
//////////////////////////////////////////////////////////////////////////////*/
package universals;

import java.util.Date;

/**
 * This class is soley responsible for giving 
 * the time and date (as a String)
 * only one String available currently
 * @version 1.0
 * @author Kieda
 */
public class TimeManager {
    /**
     * returns the current time and date in format
     * "Hours:Minutes:Seconds;Day/Month/Year"
     * @return the time and date in String format
     */
    public static String getCurrentTimeAndDate(){
        Date d = new Date();//class date
        String year = d.getYear()+"";
        return d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()+"; "+
            d.getDate()+"/"+d.getMonth()+1+"/"+year.substring(1);
        //these methods accesing the date are depricated (WHY WOULD SOMETHING AS
        //USEFUL AS THIS DEPRICATE??? T.T [it doesn't seem unsafe])
    }
}
