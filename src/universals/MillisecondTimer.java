//DO NOT INCLUDE
package universals;

/**
 *
 * @author Kieda
 */
public class MillisecondTimer {
    private long startTime;
    private long currentTime;
    private long time;
    public MillisecondTimer(){
        time = 0;
        startTime = System.currentTimeMillis();
    }
    public MillisecondTimer(long start){
        time = start;
        startTime = System.currentTimeMillis();
    }
    public long getTime(){
        currentTime = System.currentTimeMillis();
        time = currentTime-startTime;
        return time;
    }
    public void setTime(long time){
        this.time = time;
    }
}
