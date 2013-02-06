/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/ChineseFrame.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.componentcreator.ChineseFrameComponents;
import gui.mainframe.componentcreator.drawpanel.InternalDrawFrame;
import gui.mainframe.componentcreator.drawpanel.InternalDrawFramePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import universals.LogManager;
import universals.UniversalDataStorage;// </editor-fold>

/**
 * ChineseFrame.java
 * The core GUI for the CS IB project
 * Created on Dec 27, 2010, 9:08:04 PM
 * @version BETA 3.0
 * @author Kieda
 * @since 2-25-2011
 *//*IMPLEMENTATION OF ABSTRACT DATA TYPES*/
public class ChineseFrame extends JFrame implements KeyListener, WindowListener{
    // <editor-fold defaultstate="collapsed" desc="declarations">
    private static boolean dead = false;
        //called to kill the program, i'll use this eventually
    public static ChineseFrameComponents components;
        //the components to be added to the frame
    private static JPanel manualInputPanel;
        //the manulInputPanel (panel on the left)
    private static JPanel drawPanel;
        //the panel which is drawn on (panel on the right)
    private static JPanel bottomPanel;
        //the panel that is either output or  the database creation panel
        //(panel on the bottom)
    public boolean frametype;
        //the kind of frametype used (either database or recognition)
    // </editor-fold>
    /**
     * Constructor for the finder frame, the gui component responsibe for
     * interfacing between the user, the text writing algorithms, and the
     * dictionary
     */
    public ChineseFrame() {
        super("CHINESE CHARACTER RECOGNITION BETA 3.0");
            //sets the frame's title
        UniversalDataStorage.intersections = new ArrayList<Integer>(1);
            //initializes intersections to prevent errors
    }

    /**
     * initializes the frame and its components
     * @param frameType the kind of frame to be used
     *        (finder or database creation)
     */
    @SuppressWarnings("unchecked")
    public void initFrame(boolean frameType){

        frametype = frameType;// sets the frameType
        addWindowListener(this);
            //sets the what should happen when the frame is closed

        addKeyListener(this);//adds the keylistener, CURRENTLY NOT WERKING
        
        components = new ChineseFrameComponents();
            //creates the components for the ChineseFrame
        components.initStuff();
            //initializes the variables of the ChineseFrameComponents
        components.initFinderFrameComponents();
            //initializes the components in ChineseFrameComponents for use
            //in the ChineseFrame
        manualInputPanel = components.getManualInputPanel();
            //initializes a local copy of the ManualInputPanel
        drawPanel = components.getDrawPanel();
            //initializes a local copy of the DrawPanel
        if(frametype)//decides what to do based on the frametype given (in
                     //this situation, output)
            bottomPanel = components.getOutputPanel();
                //initializes a local copy of the BottomPanel in the outputPanel
                //form
        else//decides what to do based on the frametype given(in 
            //this situation, database)
            bottomPanel = components.getDatabaseCreationPanel();
                //initializes a local copy of the BottomPanel in the database
                //creator form



        setJMenuBar(components.getChineseMenuBar());
            //sets the JMenuBar to the ChineseMenuBar
        GroupLayout layout = new GroupLayout(getContentPane());
            //creates a new layout based on this frame
        getContentPane().setLayout(layout);
            //sets his frame's layout to the GroupLayout
        // <editor-fold defaultstate="collapsed" desc="template code from the Netbeans IDE">
        layout.setHorizontalGroup(//starts the horizontal grouping
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                //adds a parralel group (basically the entire container)
            .addGroup(layout.createSequentialGroup()
                //creates a squential group in the parallel for the drawpanel
                //and the manualInputPanel (top left and top right)
                .addComponent(manualInputPanel, 250, 250, 250)
                    //creates a maualInputPanel with the horizontal default
                    //size of 250
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    //adds a gap between the manualInputPanel and the drawPanel
                .addComponent(drawPanel, 300, 300, Short.MAX_VALUE)
                    //creates a maualInputPanel with the horizontal default
                    //size of 325
                )//end of sequential group
            .addComponent(bottomPanel, GroupLayout.Alignment.TRAILING
                , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE
                , Short.MAX_VALUE)
                    //adds the bottomPanel beneath
                    //the manualInputPanel/drawPanel
        );//end of horizontal group
        
        layout.setVerticalGroup(//starts the vertical grouping
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            //adds a parralel group (basically the entire container)
            .addGroup(layout.createSequentialGroup()
                //creates a squential group for the drawpanel/manualInputPanel
                //then finally the bottomPanel
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment
                    .TRAILING, false)
                        //creates a parallel group for the drawpanel and
                        //manualInputPanel
                    .addComponent(drawPanel, GroupLayout.Alignment.LEADING, 300
                        , 325, Short.MAX_VALUE)
                            //adds the drawPanel
                    .addComponent(manualInputPanel, GroupLayout.Alignment
                        .LEADING, GroupLayout.DEFAULT_SIZE
                        , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                            //adds the outputPanel
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    //adds a a gap between the manualInputPanel/drawPanel and
                    //the bottomPanel
                .addComponent(bottomPanel, 150, 210
                    , Short.MAX_VALUE)
                        //adds the bottomPanel
                )
        );//end of vertical group
        // </editor-fold>
        pack();//packs all of the components in
    }
    /**
     * kills the program
     */
    public static void kill(){
        dead = true;//stops the running loops
        InternalDrawFrame.kill(); //stops the drawing threads
        try {
            LogManager.closeAllWriters();
                //closes all of the writers
        } catch (IOException ex) {}
        System.exit(0);//exits the sytem
    }
    // <editor-fold defaultstate="collapsed" desc="key events">
    //CURRENTLY NON WERKING (CANNOT REALLY FIND OUT WHY, DO NOT PUT IN IB PROJECT CODE)
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
        /**ADVANCED SELECTION*/
        switch (e.getKeyCode()) {
            case KeyEvent.VK_F5:
                InternalDrawFramePanel.clearPanel();
                break;
            case KeyEvent.VK_ESCAPE:
                ChineseFrameActionHandler.exit();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
    }// </editor-fold>

    public void windowOpened(WindowEvent e) {}

    public void windowClosing(WindowEvent e) {   
        kill();
    }

    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}

}
