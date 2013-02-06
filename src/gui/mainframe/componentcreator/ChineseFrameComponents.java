/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/ChineseFrameComponents.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.ChineseFrame;
import gui.mainframe.componentcreator.outputpanel.CharacterFieldPanel;
import gui.mainframe.componentcreator.outputpanel.PossibleChineseCharactersPanel;
import gui.mainframe.componentcreator.drawpanel.InternalDrawFrame;
import gui.mainframe.componentcreator.menubar.MenuBar;
import gui.mainframe.componentcreator.options.FloatingPartsCounterPanel;
import gui.mainframe.componentcreator.options.IntersectionStringInputPanel;
import gui.mainframe.componentcreator.options.IntersectionsCrudeAmoutPanel;
import gui.mainframe.componentcreator.options.StrokeComplexityPanel;
import gui.mainframe.componentcreator.options.StrokeCrudeCountPanel;
import gui.mainframe.componentcreator.databasecreator.DatabaseCreatorPanel;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.border.BevelBorder;
import mechanics.database.DatabaseCreator;// </editor-fold>

/**
 * This class stores all of the components used by the ChineseFrame gui
 * This class also builds the the main components for use in the ChineseFrame
 * (the manualInputPanel, the drawPanel, and the bottomPanel)
 * @author Kieda
 * @since 3-5-2011
 *//*USER DEFINED METHODS WITH RETURN VALUES*/
   /*ENCAPSULATION*/
public class ChineseFrameComponents extends ChineseFrame{
                                            //extends the main ChineseFrame
    // <editor-fold defaultstate="collapsed" desc="component declaration">
        /* components are sorted first by JPanel they are in, and second by 
         * their location
         *
         * components in a main JPanel are sorted by
         *     JPanel
         *     JLabel for the JPanel
         *     Other components (sorted by thier location vertically)
         * please add components in this manner
         */
        private static MenuBar menuBar;
            //menubar for providing basic program funtions

        // <editor-fold defaultstate="collapsed" desc="top left panel">
            private static JPanel manualInputPanel = new JPanel();
                //manual input panel. allows a person to see if there is a
                //mistake in his/her writing, and allows them to adjust
            private static JLabel optionsLabel = new JLabel();
                //the label for the manualInputPanel
            private static FloatingPartsCounterPanel numberOfFloatingPartsPanel;
                //the number of floating parts
            private static IntersectionsCrudeAmoutPanel
                totalNumberOfIntersectionsPanel;
                    //the total number of intersections, will probably replace
            private static StrokeCrudeCountPanel numberOfStrokesPanel;
                //the total number of intersections, will probably replace
            private static StrokeComplexityPanel strokeComplexityPanel;
                //the number of complex/simple strokes. updates automatically
            private static IntersectionStringInputPanel
               intersectionsStringInputPanel;
                    //the input for the intersections on each stroke. 
                    //Does so by analyzing an inputted string
        // </editor-fold>
            
        // <editor-fold defaultstate="collapsed" desc="top right panel">
            private static JPanel drawPanel = new JPanel();
                //the panel that contains the InternalDrawFrame.
                //will later be updated for more funtionality/user experience.
            private static JLabel drawPanelLabel = new JLabel();
                //the label for the drawPanel
            private static Runnable drawInternalFrame;
                //the internalDrawFrame: what you actually draw on.
                //is a Runnable to be multithreaded to allow drawing
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="bottom panel">
          //OPTION 1: Database Training
            private static JPanel databaseCreationPanel;
                //the panel responsible for creating the database. Already
                //contains all of its componants
          //OPTION 2: Character Finding
            private static JPanel outputPanel = new JPanel();
                //the panel which displays the possible chinese characters and
                //the character text field. Will update later to add quickly
                //accesible punctuation to improve user experience with a pen.
            private static JLabel outputLabel = new JLabel();
                //the label for the outputPanel
            private static PossibleChineseCharactersPanel
                    possibleChineseCharactersPanel;
                //a list of the possible chinese characters
            private static CharacterFieldPanel characterFieldPanel;
                //the character field that is wrote to when writing
                //chinese characters
        // </editor-fold>
    // </editor-fold>

    /**
     * creates all of the main panels
     * call this method first to initialize all of the components at once, then
     * retrive the components by the get methods
     */
    public void initFinderFrameComponents() {
        initOutputPanel();
            //creates the outputPanel; which is used for finding characters
            //and putting characters together in asentences
        initDrawPanel();
            //creates a drawPanel; which is used for drawing characters
        initManualInputPanel();
            //creates a manualInputPanel; which is used to manually input
            //values for characters
        initMenuBar();
            //creates a menubar; which is useful in the UI
    }
    /**
     * initializes all of the individual components
     * call this method first before ANYTHING ELSE to avoid errors in frame
     * initialization
     */
    public void initStuff(){
        menuBar = new MenuBar();
            //initializes the menuBar

        // <editor-fold defaultstate="collapsed" desc="initialization of the components in the options panel">
        numberOfFloatingPartsPanel = new FloatingPartsCounterPanel();
        totalNumberOfIntersectionsPanel = new IntersectionsCrudeAmoutPanel();
        numberOfStrokesPanel = new StrokeCrudeCountPanel();
        strokeComplexityPanel = new StrokeComplexityPanel();
        intersectionsStringInputPanel = new IntersectionStringInputPanel();
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="initialization of the components in the output panel">
        possibleChineseCharactersPanel = new PossibleChineseCharactersPanel();
        characterFieldPanel = new CharacterFieldPanel();
        // </editor-fold>
        
        databaseCreationPanel =new DatabaseCreatorPanel(DatabaseCreator.next());
            //initializes the databaseCreationPanel, sets the first character
            //to display as the first character from the DatabaseCreatorPanel
        drawInternalFrame = new InternalDrawFrame();
            //creates the Runnable drawInternalFrame as an InternalDrawFrame
        Thread drawInternalFrameThread = new Thread(drawInternalFrame);
            //creates a thread from the drawInternalFrame Runnable
        drawInternalFrameThread.start();
            //starts the drawInternalFrameThread, which allows the
            //InternalDrawFrame to draw constantly
    }
    // <editor-fold defaultstate="collapsed" desc="component initialization">
    /**
     * Initializes the menubar used in the ChineseFrame
     * call this method first, then add it to the ChineseFrame
     * call getMenuBar() to retreive this component
     */
    public void initMenuBar() {/*Nothing goes here, initialization currently
        happens by creating a new MenuBar. Keep this for external initialization
        that may need to occur*/
    }
    /**
     * creates an output panel
     * call this method first, then add it to the ChineseFrame
     * call getOutputPanel() to retreive this component
     */
    public void initOutputPanel() {
        outputPanel.setBorder(BorderFactory
            .createBevelBorder(BevelBorder.RAISED));
                //puts a raised bevel border around the output panel
        outputLabel.setFont(new Font("Tahoma", 0, 14));
            //sets the label to go on the outputPanel to a larger size
        outputLabel.setText("Output");
            //sets the label's text

        GroupLayout outputPanelLayout = new GroupLayout(outputPanel);
            //creates a GroupLayout for the outputPanel
        outputPanel.setLayout(outputPanelLayout);
            //sets the outputPanel's layout to the GroupPanel
        outputPanelLayout.setHorizontalGroup(//starts the horizontal grouping
            outputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                //creates a parallel group to contain everything in the
                //outputPanel
            .addGroup(
                 outputPanelLayout.createSequentialGroup()
                    //creates a sequentialGroup to place the
                    //possibleChineseCharactersPanel and characterFieldPanel
                .addContainerGap()//adds a gap on the left-most part
                .addComponent(possibleChineseCharactersPanel, 100, 600, 600)
                    //adds the possibleChineseCharactersPanel after the gap
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    //adds a gap between the possibleChineseCharactersPanel and
                    //the characterFieldPanel
                .addComponent(characterFieldPanel, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        //adds a resizable characterFieldPanel next
                .addContainerGap()
                    //finally adds a container gap in the horizontal direction
            )
            .addGroup(
                outputPanelLayout.createSequentialGroup()
                    //creates a sequentialGroup to place the outputLabel on
                    //the outputPanel
                .addContainerGap()
                    //adds a containerGap (for label alignment)
                .addComponent(outputLabel, GroupLayout.DEFAULT_SIZE, 102
                    , Short.MAX_VALUE)
                        //adds the outputLabel
            )
        );
        outputPanelLayout.setVerticalGroup(//starts the vertical grouping
            outputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                //creates a parallelGroup for containing the components
            .addGroup(
                outputPanelLayout.createSequentialGroup()
                        //creates a sequentialGroup for containing the
                        //outputLabel, characterFieldPanel, and
                        //possibleChineseCharactersPanel
                    .addComponent(outputLabel)
                        //adds the outputLabel to the top of the outputPanel
                    .addGroup(
                        outputPanelLayout.createParallelGroup(GroupLayout
                            .Alignment.TRAILING, true)
                                //adds a resizable parallel group that contains
                                //the possibleChineseCharactersPanel and
                                //characterFieldPanel
                        .addGroup(
                            outputPanelLayout.createSequentialGroup()
                               //adds a group to contain the characterFieldPanel
                            .addGap(6, 6, 6)
                                //adds a gap above the characterFieldPanel
                            .addComponent(characterFieldPanel, 50, 200
                                , Short.MAX_VALUE)
                                    //adds the characterFieldPanel
                        )
                        .addGroup(
                            outputPanelLayout.createSequentialGroup()
                                    //adds a group to contain the
                                    //possibleChineseCharactersPanel
                                .addPreferredGap(LayoutStyle.ComponentPlacement
                                    .RELATED)
                                        //adds a gap above the
                                        //possibleChineseCharactersPanel
                                .addComponent(possibleChineseCharactersPanel, 50
                                    , 200, Short.MAX_VALUE)
                                       //adds the possibleChineseCharactersPanel
                        )
                    )
                    .addContainerGap()//adds a containerGap on the bottom
            )
        );
    }
    /**
     * creates a drawPanel
     * call this method first, then add it to the ChineseFrame
     * call getDrawPanel() to retreive this component
     */
    public void initDrawPanel() {
        drawPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder
            .LOWERED));//makes a lowered bevel border around the draw panel
            
        drawPanelLabel.setFont(new Font("Tahoma", 0, 14));
            //sets the label to a larger font
        drawPanelLabel.setText("Draw Panel");
            //draws the label on the drawPanel

        GroupLayout drawPanelLayout = new GroupLayout(drawPanel);
            //creates a layout for the drawPanel
        drawPanel.setLayout(drawPanelLayout);
            //sets the layout for the drawpanel
        
        drawPanelLayout.setHorizontalGroup(//starts the horizontal grouping
            drawPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                //adds a parallel group (basically the entire drawPanel)
            .addGroup(drawPanelLayout.createSequentialGroup()
                    //group contains the drawPanel
                .addGap(65, 65, 65)
                    //adds a gap before adding the drawPanel
                .addComponent((JInternalFrame) drawInternalFrame, GroupLayout
                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout
                    .PREFERRED_SIZE)
                        //adds the drawFrame
                .addContainerGap(118, Short.MAX_VALUE)
                    //adds the closing containerGap
            )
            .addGroup(drawPanelLayout.createSequentialGroup()
                    //group contains the drawPanelLabel, added to the drawPanel
                .addContainerGap()
                    //adds a containerGap (for alignment and consistancy)
                .addComponent(drawPanelLabel, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        //adds the drawPanelLabel
            )
        );
        drawPanelLayout.setVerticalGroup(//starts the vertical grouping
            drawPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                //adds a parallel group for basically the entire drawPanel
            .addGroup(drawPanelLayout.createSequentialGroup()
                    //creates a sequential group for the drawPanelLabel and
                    //drawInternalFrame
                .addComponent(drawPanelLabel)
                    //adds the drawPanelLabel on top
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    //adds a gap between the label and the drawInternalFrame
                .addComponent((JInternalFrame) drawInternalFrame, GroupLayout
                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout
                    .PREFERRED_SIZE)
                        //adds the drawInternalFrame to the drawPanel
                .addContainerGap(23, Short.MAX_VALUE)
                    //finally adds a resizable containerGap
            )
        );
    }
    /**
     * creates a manualInputPanel
     * call this method first, then add it to the ChineseFrame
     * call getManualInputPanel() to retreive this component
     */
    public void initManualInputPanel() {
        manualInputPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder
            .LOWERED));
                //sets the border of the manualInputPanel to a lowered
                //BevelBorder
        optionsLabel.setFont(new Font("Tahoma", 0, 14));
            //sets the optionsLabel's font to something larger
        optionsLabel.setText("Options");
            //sets the optionsLabel's text

        GroupLayout manualInputPanelLayout = new GroupLayout(manualInputPanel);
            //creates a new GroupLayout for the manualInputPanel
        manualInputPanel.setLayout(manualInputPanelLayout);
            //sets the manualInputLabel's layout
        manualInputPanelLayout.setHorizontalGroup(
                //start of the horizontal grouping
            manualInputPanelLayout.createParallelGroup(GroupLayout.Alignment
                .LEADING)
                    //parallel group to allow many objects to be added that are
                    //veritcally aligned
            .addGroup(manualInputPanelLayout.createSequentialGroup()
                    .addContainerGap()
                        //creates a sequential group for addition of components
                .addGroup(
                     manualInputPanelLayout.createParallelGroup(GroupLayout
                        .Alignment.LEADING)
                            //creates a parallel group to contain everything
                            //after the containerGap()
                    .addGroup(manualInputPanelLayout.createSequentialGroup()
                            //adds a group to contain the optionsLabel
                        .addComponent(optionsLabel, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                //adds a resizable optionsLabel
                    )
                    .addGroup(
                         manualInputPanelLayout.createSequentialGroup()
                            //creates a sequentialGroup to contain the other
                            //components
                        .addGroup(manualInputPanelLayout.createParallelGroup(
                                GroupLayout.Alignment.TRAILING, true)
                                    //creates a parallelGroup to add the
                                    //input components
                            .addComponent(intersectionsStringInputPanel, 
                                GroupLayout.Alignment.LEADING, 100, 100,
                                Short.MAX_VALUE)
                                    //adds the intersectionsStringInputPanel
                                    //at the top
                            .addComponent(numberOfFloatingPartsPanel, 
                                GroupLayout.Alignment.LEADING, 100, 100,
                                Short.MAX_VALUE)
                                    //adds the numberOfFloatingPartsPanel after
                                    //the intersectionsStringInputPanel
                            .addComponent(totalNumberOfIntersectionsPanel, 
                                GroupLayout.Alignment.LEADING, 100, 100,
                                Short.MAX_VALUE)
                                    //adds the totalNumberOfIntersectionsPanel
                                    //after the numberOfFloatingPartsPanel
                            .addComponent(strokeComplexityPanel, GroupLayout
                                .Alignment.LEADING, 100, 100, Short.MAX_VALUE)
                                    //adds the strokeComplexityPanel after
                                    //the totalNumberOfIntersectionsPanel
                            .addComponent(numberOfStrokesPanel, GroupLayout
                                .Alignment.LEADING, 100, 100, Short.MAX_VALUE)
                                    //adds the numberOfStrokesPanel last
                        )
                        .addContainerGap()//the final gap
                    )
                )
            )
        );
        manualInputPanelLayout.setVerticalGroup(
                //start of the vertical grouping
            manualInputPanelLayout.createParallelGroup(GroupLayout.Alignment
                .LEADING)
            .addGroup(manualInputPanelLayout.createSequentialGroup()
                    //creates a group to contain the components
                .addComponent(optionsLabel).addPreferredGap(LayoutStyle
                    .ComponentPlacement.RELATED)
                        //adds the optionsLabel at the top
                .addComponent(numberOfStrokesPanel, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        //adds the numberOfStrokesPanel below the optionsLabel
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    //adds a gap between the numberOfStrokesPanel and the
                    //numberOfFloatingPartsPanel
                .addComponent(numberOfFloatingPartsPanel, GroupLayout
                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                        //adds the numberOfFloatingPartsPanel below the
                        //numberOfStrokesPanel
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    //adds a gap between the numberOfFloatingPartsPanel and the
                    //totalNumberOfIntersectionsPanel
                .addComponent(totalNumberOfIntersectionsPanel, GroupLayout
                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout
                    .PREFERRED_SIZE)
                        //adds the totalNumberOfIntersectionsPanel below the
                        //numberOfFloatingPartsPanel
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    //adds a gap between the totalNumberOfIntersectionsPanel and
                    //the strokeComplexityPanel
                .addComponent(strokeComplexityPanel, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        //adds the strokeComplexityPanel below the
                        //totalNumberOfIntersectionsPanel
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    //adds a gap between the strokeComplexityPanel and the
                    //intersectionsStringInputPanel
                .addComponent(intersectionsStringInputPanel, GroupLayout
                    .DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        //adds the intersectionsStringInputPanel at the bottom
                        //of the panel
                .addContainerGap()
                    //adds the end container gap
            )
        );
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="get components">
    /**
     * returns the menuBar component
     * menubar is the menubar (uppermost component) of the ChineseFrame
     * @return MenuBar component
     */
    public JMenuBar getChineseMenuBar() {
        return menuBar;//returns the menuBar
    }
    /**
     * databaseCreationPanel is the bottom panel in the ChineseFrame, in
     * Database mode
     * @return the DatabaseCreatorPanel component
     */
    public DatabaseCreatorPanel getDatabaseCreationPanel() {
        return (DatabaseCreatorPanel) databaseCreationPanel;
            //returns databaseCreationPanel (from type JPanel to
            //DatabaseCreatorPanel)
    }
    /**
     * outputPanel is the bottom panel in the ChineseFrame, in Finder mode
     * @return the outputPanel component
     */
    public JPanel getOutputPanel() {
        return outputPanel;//returns the outputPanel
    }
    /**
     * drawPanel is the right-most panel in the ChineseFrame
     * @return the drawPanel component
     */
    public JPanel getDrawPanel() {
        return drawPanel;//returns the drawPanel
    }
    /**
     * manualInputPanel is the left-mostcomponent in the ChineseFrame
     * @return the manualInputPanel component
     */
    public JPanel getManualInputPanel() {
        return manualInputPanel;//returns the manualInputPanel
    }
    // </editor-fold>
}

