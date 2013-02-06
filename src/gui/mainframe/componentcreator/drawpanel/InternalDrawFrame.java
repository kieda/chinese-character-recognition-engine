/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/drawpanel/InternalDrawFrame.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator.drawpanel;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.componentcreator.ChineseFrameComponent;
import gui.mainframe.componentcreator.menubar.MenuBar;
import universals.CreatedCharacter;
import universals.LogManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.JInternalFrame;
import mechanics.database.SearchEngine;
import mechanics.recognition.ChineseManager;
import mechanics.recognition.managers.FloatingPartManager;
import mechanics.recognition.managers.IntersectionManager;
import mechanics.recognition.managers.StrokeComplexityManager;
import mechanics.recognition.managers.StrokeNumberManager;
import universals.ChineseStroke;
import universals.MillisecondTimer;
import universals.UniversalDataStorage;// </editor-fold>

/**
 * The internal draw frame is a JInternal frame that is responsible for holding
 * the jpanel used for drawing the chinese character for data analysis. This
 * internal draw frame is also responsible for updating the mouse position to
 * the panel
 * @version 2.3
 * @author Kieda
 * @since 3-20-2011
 *//*INHEIRITANCE*/
   /*USER DEFINED METHODS WITH RETURN VALUES AND PARAMETERS*/
   /*ENCAPSULATION*/
public class InternalDrawFrame extends JInternalFrame
        implements Runnable, MouseListener, ChineseFrameComponent{
    private int positionX;//the location of the internal frame in the x direction
    private int positionY;//the location of the internal frame in the y direction
    private int frameWidth;//the width and height of this internal frame
    private int xMouseDrawing;//the x location of the cursor
    private int yMouseDrawing;//the y location of the cursor
    private boolean firstTime = true;
        //the first time the program has run. used for painting initialization
    private static boolean dead = false;
        //the fariable that kan kill the program (safely) by ending all threads
        //calling dead as true results in the painting to stop
    IntersectionManager im = new IntersectionManager();
        //the intersection manager used in data analysis
    StrokeComplexityManager cm = new StrokeComplexityManager();
        //the StrokeComplexityManager used in data analysis
    FloatingPartManager fpm  = new FloatingPartManager();
        //the FloatingPartManager used in data analysis
    StrokeComplexityManager scm = new StrokeComplexityManager();
        //the StrokeComplexityManager used in data analysis
    StrokeNumberManager snm = new StrokeNumberManager();
        //the StrokeNumberManager used in data analysis
    private InternalDrawFramePanel drawpanel;
        //the draw panel/ where the chinese character is drawn to
    private boolean create = false;
        //used to decide whether or not points should be drawn. This variable
        //becomes true (so we'll add points) when the mose is pressed, and this
        //variable becomes false when the mouse is released
    /**
     * the constructor for the InternalDrawFrame. This calles for the default
     * customization: 300 by 300 pane at 0,0 (pixels)
     *//*POLYMORPHISM*/
    public InternalDrawFrame() {
        init();//initializes the internal frame with default cusomization
    }
    /**
     * the constructor for the InternalDrawFrame. THis constructor calls the
     * default width/height 300 by 300, and a custom location x and y
     * @param x the starting x location of the internal draw frame
     * @param y the starting y location of the internal draw frame
     *//*POLYMORPHISM*/
    public InternalDrawFrame(int x, int y) {
        init(x,y);
            //initializes the internal frame with default width and custom
            //position
    }
    /**
     * the constructor for the InternalDrawFrame. This constuctor allows a
     * custom width and positon. However, the frame will still be in the shape
     * of a square, so its dimensions will be width by width
     * @param x the starting x location of the internal draw frame
     * @param y the starting y location of the internal draw frame
     * @param width the width and height of the internal draw frame
     *//*POLYMORPHISM*/
    public InternalDrawFrame(int x, int y, int width) {
        init(x,y,width);
            //initializes with these default settings
    }
    /**
     * initializes the frame with default settings
     *//*POLYMORPHISM*/
    private void init(){
        init(0,0);//passes to init(x,y)
    }
    /**
     * initializes the frame with custom x and y location starting
     * position and default width/height
     * @param x the starting x location of the internal draw frame
     * @param y the starting y location of the internal draw frame
     *//*POLYMORPHISM*/
    private void init(int x, int y){
        init(x,y,300);//passes to init(x,y,width)
    }
    /**
     * initializes the frame with custom x and y location starting
     * position and custom width/height. This init actually initializes
     * the internal draw frame
     * @param x the starting x location of the internal draw frame
     * @param y the starting y location of the internal draw frame
     * @param width
     *//*POLYMORPHISM*/
    private void init(int x, int y, int width){
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            //sets the cursor to a crosshair cursor (for ease of drawing)
        UniversalDataStorage.points = new ChineseStroke();
            //initializes the points in the UniversalDataStorage
        positionX = x;//sets the global variable of the x position of the frame
        positionY = y;//sets the global variable of the y position of the frame
        frameWidth = width;//sets the global variable of the frame width
        setBounds(positionX, positionY, frameWidth, frameWidth);
            //sets the internal frame's position, height, and width
        addMouseListener(this);
            //adds the mouse listener to the internal frame so mouse inputs will work
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
            //sets the default close operation so the frame only closes
        setClosable(false);//makes it so the internal frame cant close
        setVisible(true);//makes the internal frame visible
        
        setMaximumSize(new java.awt.Dimension(width, width));
        setMinimumSize(new java.awt.Dimension(width, width));
        setPreferredSize(new java.awt.Dimension(width, width));
            //makes sure that the bounds of this internal frame are exact
        drawpanel = new InternalDrawFramePanel(getBounds());
            //creates a drawpanel the same size as the internal frame
        getContentPane().add(drawpanel, BorderLayout.CENTER);
            //adds the drawpanel in the center
        pack();//packs all of the contents so they fit
    }
    /**
     * this is used to find the local x placement of the mouse. The
     * xMouseDrawingin the universal data storage is NOT exact, because the
     * points are checked to ensure they do not go out of bounds before they
     * are set in the universal data storage
     * @return the x position of the cursor
     *//*ENCAPSULATION*/
    public int getXMouseDrawing() {
        return xMouseDrawing;
    }
    /**
     * this is used to find the local y placement of the mouse. The 
     * xMouseDrawing in the universal data storage is NOT exact, because the
     * points are checked to ensure they do not go out of bounds before they are
     * set in the universal data storage
     * @return the y position of the cursor
     *//*ENCAPSULATION*/
    public int getYMouseDrawing() {
        return yMouseDrawing;
    }
    /**
     * the thread that is run to continually update the mouse position, repaint
     * the InternalDrawFrame, and repaint the drawpanel
     */
    public void run() {
       fast:while(true){
                //continually repaints and updates until the fast is broken
            pointcheck();
                //ensures the drawn bounds are within the internal frame bounds
            UniversalDataStorage.getXMouseDrawing = getXMouseDrawing();
            UniversalDataStorage.getYMouseDrawing = getYMouseDrawing();
                //updates the location of the mouse universally
            repaint();//repaints the frame
            drawpanel.repaint();//repains the drawframe
            try{
                Thread.sleep(15);//waits a little bit before refreshing
            }
            catch(InterruptedException ex){
                LogManager.logError(ex,"Thread interrupted", 185
                    , "InternalDrawFrame");
            }
                //used incase if there is an error with the thread sleeping
            /*USE OF FLAGS*/
            if(dead){
                break fast;//it's a joke
                    //this kills the thread loop, and closes the internal frame
            }
       }
        this.dispose();//closes the internal frame
    }
    /**
     * this method is used to add another x and y coordinate to the
     * UniversalDataStorage's points. the points are "checked" to see if they go
     * out of bounds before adding them to UniversalDataStorage's points
     */
    public void pointcheck(){
        if(create){//if points should be added/ if the mouse is pressed
            /*ADVANCED SELECTION*/
            if(firstTime){
                    //if this is the first time the program is running and the
                    //mouse is clicked
                firstTime = false;//it is not the first time anymore
                xMouseDrawing = getMousePosition().x;
                yMouseDrawing = getMousePosition().y;
                    //set the x and y position of the mouse
                UniversalDataStorage.points.add(new Integer[]{xMouseDrawing-8
                    ,yMouseDrawing-27});
                        //add the points to UniversalDataStorage's points.
                        //there is a -8 and -27 to make up for the offset made
                        //by the internal frame's border
            }
            else{//if this not the first time
                try {
                    if((deltaMouseMin<=Math.abs(xMouseDrawing-
                        getMousePosition().x)||deltaMouseMin<=Math.abs(
                        yMouseDrawing-getMousePosition().y))){
                            //point checks and makes sure the point lies in the
                            //border
                    xMouseDrawing = getMousePosition().x;
                    yMouseDrawing = getMousePosition().y;
                        //set the x and y position of the mouse
                    UniversalDataStorage.points.add(new Integer[]{xMouseDrawing
                        -8,yMouseDrawing-27});
                            //add the points to UniversalDataStorage's points.
                            //there is a -8 and -27 to make up for the offset made
                            //by the internal frame's border
                    }
                } catch (Exception e) {LogManager.logError(e
                    ,"mouse goes off drawpanel", 235, "InternalDrawFrame");}
                        //catches the exceptions thrown when the mouse goes off
                        //the drawpanel
                
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="mouse events">
    public void mousePressed(MouseEvent e) {//if the mouse is pressed
        create = true;//start creating lines
        pointcheck();
            //make sure the lines are in bounds and add them to
            //UniversalDataStorage's points
    }
    public void mouseClicked(MouseEvent e) {/*nothing here*/}
    public void mouseReleased(MouseEvent e) {//if the mouse is released
        if (MenuBar.getPenOrEraser()) {//if it's a pen
            UniversalDataStorage.chineseCharacter.add(UniversalDataStorage
                .points);
                    //add points
        } else {//if it's an eraser
            UniversalDataStorage.chineseCharacter.erase(UniversalDataStorage
                .points);
                    //take points away
                    //currently there is no option for erasing a stroke. I'm 
                    //working on it, however
        }
        snm.updateManager();
                //processes the number of strokes by the CreatedCharacter
        im.updateManager();
                //processes the nintersections by the CreatedCharacter
        cm.updateManager();
                //processes the complex and simple by the CreatedCharacter
        fpm.updateManager();
                //processes the floating parts by the CreatedCharacter
        System.out.printf("Intersections %s; Complex %d; Simple %d; Floating"
                + " %d; Stroke Count %d\n", Arrays.toString(UniversalDataStorage
                .intersections.toArray()), UniversalDataStorage
                .numberOfComplexStrokes, UniversalDataStorage
                .numberOfStraightStrokes, UniversalDataStorage
                .numberOfFloatingParts, UniversalDataStorage.numberOfStrokes);
                    //prints out the data about the character made
        SearchEngine.searchForChineseCharacter();
            //searches for matches of chinese character in the database
        create = false;//makes sure lines wont be made
        UniversalDataStorage.points = new ChineseStroke();
            //clears the points in the last stroke
        UniversalDataStorage.updateOptions();
            //updates all of the components in the options panel
    }
    public void mouseEntered(MouseEvent e) {/*nothing here*/}
    public void mouseExited(MouseEvent e) {/*nothing here*/}
    // </editor-fold>
    /**
     * the required method for classes implementing ChineseFrameComponent,
     * returns the instance of this class
     * @return the InternalDrawFrame
     */
    public Component getComponent() {
        return(this);
    }
    /**
     * this method kills the threads running dealing with the InternalDrawFrame
     * this method is called before exiting and before a fatal exception could
     * occur
     */
    public static void kill(){
        dead = true;//sets the program as dead
    }
}
