/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/outputpanel/CharacterFieldPanel.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator.outputpanel;

// <editor-fold defaultstate="collapsed" desc="imports are important">
import gui.mainframe.componentcreator.ChineseFrameComponent;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import universals.FontFinder;
// </editor-fold>

/**
 * This class is a JPanel of the type ChineseFrameComponent.
 * This panel is used on the right-most side in the outputPanel on the
 * ChineseFrame.
 * The use of this panel is to be an output for the found character, so
 * paragraphs could be made continuoulsy.
 * @author Kieda
 * @since 3-13-2011
 */
public class CharacterFieldPanel extends JPanel
        implements ChineseFrameComponent{
    
    private static JScrollPane characterScrollField = new JScrollPane();
        //the scrollPane that contains the textArea, allowing the paragraphs
        //made to be scrollable
    private static JLabel characterFieldLabel = new JLabel();
        //the label for the characterField
    private static JTextArea characterTextField = new JTextArea();
        //the editable textArea that contains the chinese characters
    /**
     * The constructor for the CharacterFieldPanel. This contructor builds and
     * initializes the CharacterFieldPanel
     */
    public CharacterFieldPanel() {
        setBorder(BorderFactory.createEtchedBorder());
            //sets the CharacterFieldPanel's border to an etchedBorder
        characterFieldLabel.setText("Character Field");
            //sets what the text for the CharacterFieldPanel's label
        characterTextField.setColumns(19);
            //sets the number of columns in the characterTextField to 19
        characterTextField.setRows(4);
            //sets the number of rows in the characterTextField to 4
        characterTextField.setFont(new Font(FontFinder.chinesefonts.get(0)
            ,Font.PLAIN,20));
                //sets the font in the characterTextField to a font compatible
                //with Chinese character, and makes the font larger (for
                //readability and user friendly-ness)
        characterScrollField.setViewportView(characterTextField);
            //sets the characterScrollField to display the characterTextField
        GroupLayout characterFieldPanelLayout = new GroupLayout(this);
            //creates a groupLayout for the CharacterFieldPanel
        setLayout(characterFieldPanelLayout);
            //sets CharacterFieldPanel's layout to the above grouplayout
        characterFieldPanelLayout.setHorizontalGroup(
                //start of horizontal group layout
            characterFieldPanelLayout.createParallelGroup(GroupLayout.Alignment
                .LEADING)
                    //creates a parallel group to contain the sequentialGroup
            .addGroup(characterFieldPanelLayout.createSequentialGroup()
                    //adds a sequential group to contain the characterFieldLabel
                    //and characterScrollField
                .addGroup(characterFieldPanelLayout.createParallelGroup(
                    GroupLayout.Alignment.LEADING)
                        //creates a parallel group to have the 
                        //characterFieldLabel and characterScrollField placed
                        //vertically
                    .addGroup(characterFieldPanelLayout.createSequentialGroup()
                        //add a sequential group to contain the
                        //characterFieldLabel
                        .addContainerGap()
                            //gap for alignment
                        .addComponent(characterFieldLabel)
                            //adding the characterFieldLabel to the right of the
                            //gap
                    )
                    .addGroup(characterFieldPanelLayout.createSequentialGroup()
                        //add a sequential group to contain the
                        //characterScrollField
                        .addContainerGap()
                            //gap for alignment
                        .addComponent(characterScrollField, 50, 357, Short
                            .MAX_VALUE)
                                //adds a resizable characterScrollField
                    )
                )
                .addContainerGap()
                    //finally a containerGap
            )
        );
        characterFieldPanelLayout.setVerticalGroup(
                //start of vertical group layout
            characterFieldPanelLayout.createParallelGroup(GroupLayout.Alignment
                .LEADING)
                    //creates a parallel group to contain the sequential
                    //addition of the vertical components
            .addGroup(characterFieldPanelLayout.createSequentialGroup()
                    //creates a sequential group to contain the components
                .addContainerGap()
                    //adds a gap
                .addComponent(characterFieldLabel)
                    //adds the characterFieldLabel below the gap
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    //adds a gap between the characterFieldLabel and the
                    //characterScrollField
                .addComponent(characterScrollField, 50, 96, Short.MAX_VALUE)
                    //adds the characterScrollField at the bottom
                .addContainerGap()
                    //creates the final gap
            )
        );
    }
    /**
     * This method adds a string to the characterTextField. This should be used
     * to add found characters to the characterTextField.
     * @param s
     */
    public static void addToList(String s){
        characterTextField.append(s);
    }
    /**
     * the required method for classes implementing ChineseFrameComponent,
     * returns the instance of this class
     * @return the CharacterFieldPanel
     */
    public Component getComponent() {
        return this;
    }
}
