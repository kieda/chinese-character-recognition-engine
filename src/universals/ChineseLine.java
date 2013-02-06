/*//////////////////////////////////////////////////////////////////////////////
./src/universals/ChineseLine.java
//////////////////////////////////////////////////////////////////////////////*/
package universals;
/**
 * This class is used for analyzing specific lines on a ChineseStroke
 * a line is a two-point protion of a stroke. The use of slopes found
 * @author Kieda
 * @version 1.2
 * @since 3-13-2011
 *//*USER DEFINED OBJECTS*/
public class ChineseLine {
    private int x1;//the first  x coordinate in the line
    private int x2;//the second x coordinate in the line
    private int y1;//the first  y coordinate in the line
    private int y2;//the second y coordinate in the line
    public double degrees;
        //the degrees the line is pointe from its nearest quandrant
    public double totaldegrees;
        //the degrees the line is pointed from the first quadrant
    public int quadrant;
        //the quandrant the line goes in
    public double magnitude;
        //the magnitude of length of a line
    
    //******************************************//
    //these quadrant values are used when       //
    //defining a line. Other classes can use    //
    //these values to deterine where a line is  //
    //pointing or to define a custom line       //
    //quadrants are as shown in the ascii image //
    //below.                                    //
    //******************************************//
    //                  |Y                      //
    //                  |                       //
    //     QUADRANT II  |  QUADRANT I           //
    //                  |                       //
    //                  |                       //
    //   ---------------+---------------        //
    //                  |              X        //
    //     QUADRANT III |  QUADRANT IV          //
    //                  |                       //
    //                  |                       //
    //******************************************//
    public static final int FIRST_QUADRANT  = 0;//value for the first  quadrant
    public static final int SECOND_QUADRANT = 1;//value for the second quadrant
    public static final int THIRD_QUADRANT  = 2;//value for the third  quadrant
    public static final int FOURTH_QUADRANT = 3;//value for the fourth quadrant

    /**
     * this is the constructor for the ChineseLine
     * this constuctor takes the position points and translates the line into a
     * magnitude and direction
     * @param x1 the first  x coordinate in the line
     * @param x2 the second x coordinate in the line
     * @param y1 the first  y coordinate in the line
     * @param y2 the second y coordinate in the line
     */
    public ChineseLine(int x1, int x2, int y1, int y2) {
        this.x1 = x1;//sets the value of the first  x coordinate in the line
        this.x2 = x2;//sets the value of the second x coordinate in the line
        this.y1 = y1;//sets the value of the first  y coordinate in the line
        this.y2 = y2;//sets the value of the second y coordinate in the line
        if((x2-x1) == 0)//if the line is vertical
            degrees = 90;//the line is going up or down 90 degrees
        else//otherwise
            degrees = Math.toDegrees(Math.abs(Math.atan((y2-y1)/(x2-x1))));
                //the degrees is equal to the arctangent(Δy/Δx), but remember,
                //this depends on which quadrant we are in.
                //we figure out which quadrant we are in by comparing the x
                //and y values
        totaldegrees = degrees;//setting the initial degrees
        if(x2>x1&&y2>y1){//if the x and y values get larger
            quadrant = FIRST_QUADRANT;//we are in the first quadrant
            //we adjust the total angle by adding nothing
        }
        if(x2<x1&&y2>y1){
                //if the x values get smaller and y the values get larger
            quadrant = SECOND_QUADRANT;//we are in the second quadrant
            totaldegrees+=90;//we adjust the total angle by adding 90 degrees
        }
        if(x2<x1&&y2<y1){//if the x and y values get smaller
            quadrant = THIRD_QUADRANT;//we are in the third quadrant
            totaldegrees+=180;//we adjust the total angle by adding 180 degrees
        }
        if(x2>x1&&y2<y1){
                //if the x values get larger and y the values get smaller
            quadrant = FOURTH_QUADRANT;//we are in the fourh quadrant
            totaldegrees+=270;//we adjust the total angle by adding 270 degrees
        }
        magnitude = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            //sets the magnitude o the line by using the distance formula
    }
    /**
     * redefines the position of the line
     * @param x1 the first  x coordinate in the line
     * @param x2 the second x coordinate in the line
     * @param y1 the first  y coordinate in the line
     * @param y2 the second y coordinate in the line
     */
    public void setPoints(int x1, int x2, int y1, int y2) {
        this.x1 = x1;//sets the value of the first  x coordinate in the line
        this.x2 = x2;//sets the value of the second x coordinate in the line
        this.y1 = y1;//sets the value of the first  y coordinate in the line
        this.y2 = y2;//sets the value of the second y coordinate in the line
    }
}
