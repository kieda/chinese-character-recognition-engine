/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/ChineseFrameComponent.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator;

import java.awt.Component;//import

/**
 * This is an interface that manages all of the components in the ChineseFrame.
 * All components that are added to the chinese frame implement this class.
 * This class also holds a few key final values for the GUI components
 * @author Kieda
 * @since 3-13-2011
 *//*ABSTRACT DATA TYPES*/
public interface ChineseFrameComponent{//start of interface
    /**
     * The method getComponent() allows the easy addition of new GUI components
     * call getComponent() in the GUI part needed, and the GUI component will be
     * returned
     * @return the component that the class will return, usually will be
     *         return this;
     */
    public Component getComponent();
    
    public static final int deltaMouseMin = 1;
        //the minimum amount that the mouse will have to move to add on another
        //point in the current ChineseStroke. This allows safety in the fact
        //that points won't be coninuallt added (slowing down the computer)
        //This value used to be alot higher, but after deciding to 'can' the
        //idea of having two individual lines intersect more than once,
        //I have found that this method works alot better, and is alot more
        //smooth with more points
    public static final boolean DATABASE = false;
        //final value to call on to initialize a database creator. This value is
        //called by the main classes to decide whether or not to create a
        //database or to find chinese characters. This value, DATABASE (which is
        // false) is called on in ChineseFrame's initFrame(boolean frameType)
        //(in  package gui.mainframe) to decide the frame type
    public static final boolean FINDER = true;
        //final value to call on to initialize a character finder. This value is
        //called by the main classes to decide whether or not to create a
        //database or to find chinese characters. This value, FINDER (which is
        // true) is called on in ChineseFrame's initFrame(boolean frameType)
        //(in  package gui.mainframe) to decide the frame type
}
