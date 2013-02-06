/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/outputpanel/PossibleChineseCharacters.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator.outputpanel;//package

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.componentcreator.ChineseFrameComponent;
import gui.mainframe.componentcreator.drawpanel.InternalDrawFramePanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import mechanics.ChineseCharacter;
import universals.FontFinder;// </editor-fold>

/**
 * This is another component implementing ChineseFrameComponent
 * @author Kieda
 * @since 3-13-2011
 *//*INHEIRITANCE*/
public class PossibleChineseCharactersPanel extends JPanel implements
        ChineseFrameComponent, MouseListener{
    private static JLabel possibleChineseCharactersLabel = new JLabel();
        //the JLabel for this panel
    private static JList listOfPossibleChineseCharacters = new JList();
        //the JList component of this panel that displays the chinese
        //character matches
    private static JScrollPane listOfPossibleChineseCharactersScrollContainer;
        //the scrollpane that holds the list of possible chinese characters
    /*ABSTRACT DATA TYPES: LIST (AS AN ARRAYLIST)*/
    static ArrayList<String> chineseCharacters = new ArrayList<String>();
        //the strings of chinese characters and information to be displayed in
        //the list
    /**
     * the constructor for the PossibleChineseCharactersPanel.
     * declare this before calling on this panel
     *
     * this creates a new jpanel with a label and a scrolled list of the
     * possible chinese characters
     *
     * this panel is added to the left side of the "output panel"
     */
    public PossibleChineseCharactersPanel() {
        listOfPossibleChineseCharacters.setFont(new Font(FontFinder.chinesefonts
            .get(0),Font.PLAIN,14));
                //sets the font of the listOfPossibleChineseCharacters to a font
                //that can support Chinese characters
        setBorder(BorderFactory.createEtchedBorder());
            //sets the border around this panel to an etched border
        possibleChineseCharactersLabel.setText("Possible Chinese Characters");
            //sets the possibleChineseCharactersLabel's text
        listOfPossibleChineseCharacters.setBorder(BorderFactory.createLineBorder
            (new Color(0, 0, 0)));
                //sets a border around the listOfPossibleChineseCharacters to 
                //make the list stand out more (and for dramatic effect!)
        listOfPossibleChineseCharacters.addMouseListener(this);
            //adds a mouslistener to this panel so characters can be readily
            //added to the character field by clicking
        listOfPossibleChineseCharacters.setModel(new AbstractListModel() {
                //sets the model of the listOfPossibleChineseCharacters (which
                //basically defines what elements are in the
                //listOfPossibleChineseCharacters)
            String[] chineseCharacters = {""};
                //the elements originally in the listOfPossibleChineseCharacters
                //this is empty because the program will start out with no
                //drawings on it, etc..
            public int getSize() { return chineseCharacters.length; }
                //a required method. just returns the number of elements in the
                //listOfPossibleChineseCharacters
            public Object getElementAt(int i) { return chineseCharacters[i]; }
                //a required method. returns the element at the input (int i) in
                //the arraylist of strings. this is called later to take
                //characters from this list to the character field
        });
        listOfPossibleChineseCharactersScrollContainer
            = new JScrollPane(listOfPossibleChineseCharacters);
                //creates the scrollpane and sets the
                //listOfPossibleChineseCharacters inside the scrollpane
        GroupLayout possibleChineseCharactersPanelLayout
            = new GroupLayout(this);
                //creates a new layout for this panel
        setLayout(possibleChineseCharactersPanelLayout);
            //sets this panel's layout to the GroupLayout created above.
        possibleChineseCharactersPanelLayout.setHorizontalGroup(
                //start of horizontal grouping
            possibleChineseCharactersPanelLayout.createParallelGroup(GroupLayout
                .Alignment.LEADING)
                    //creates a parallel group for the whole panel
            .addGroup(possibleChineseCharactersPanelLayout
                    .createSequentialGroup()
                        //sequential group to contain the components
                        //sequentially
                .addContainerGap()
                    //adds a container gap (for alignment)
                .addGroup(possibleChineseCharactersPanelLayout
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                            //add a parallel group to contain the scrollpane and
                            //label
                    .addGroup(possibleChineseCharactersPanelLayout
                            .createSequentialGroup()
                               //creates a sequential group to contain the label
                        .addComponent(possibleChineseCharactersLabel)
                            //adds the label
                    )
                    .addGroup(possibleChineseCharactersPanelLayout
                            .createSequentialGroup()
                                //adds a group for the scrollpane
                        .addComponent(
                            listOfPossibleChineseCharactersScrollContainer
                            , GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                //adds the scrollpane
                        .addContainerGap()
                            //adds a container gap to the right of the 
                            //scrollpane (for alignment, etc.)
                    )
                )
            )
        );
        possibleChineseCharactersPanelLayout.setVerticalGroup(
                //start of vertical grouping
            possibleChineseCharactersPanelLayout.createParallelGroup(GroupLayout
                .Alignment.LEADING)
                    //create a parallel group to contain the squential group
            .addGroup(possibleChineseCharactersPanelLayout
                    .createSequentialGroup()
                        //create a sequential group to add components
                        //sequentially
                .addContainerGap()
                    //container gap at the top (for alignment)
                .addComponent(possibleChineseCharactersLabel)
                    //label below the gap
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    //put a small gap between the label and the scrollpane
                .addComponent(listOfPossibleChineseCharactersScrollContainer
                    , GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        //add the scrollpane below the label and gap
                .addContainerGap()
                    //close with a container gap at the bottom
            )
        );
    }
    /**
     * Adds a ChineseCharacter to the list. This list method should be called 
     * when there is a match with the drawn character and a character in the
     * database.
     *
     * When the character is added to the list, the user will be able to see the
     * character he made, and add the character he made to the character field
     * @param c: the ChineseCharacter to be added from a match in the database
     *           to the listOfPossibleChineseCharacters
     */
    public static void addToList(ChineseCharacter c){
        chineseCharacters.add(c.toString());
            //adds the ChineseCharacter (in String format) to an arraylist of
            //characters stored in the list. this list is important for
            //recalling the information and adding the character to the
            //character field when clicked
        listOfPossibleChineseCharacters.setModel(new AbstractListModel() {
                //updates the list of elements in the
                //listOfPossibleChineseCharacters
            public int getSize() { return chineseCharacters.size(); }
                //required method, returns the size of the characters in the
                //list
            public Object getElementAt(int i) {return chineseCharacters.get(i);}
                //required method, returns the String value displayed in the
                //listOfPossibleChineseCharacters
        });
    }
    /**
     * This method clears the gui list of ChineseCharacters in the 
     * listOfPossibleChineseCharacters and clears the ArrayList of Chinese
     * characters in the list. This should be cleared whenever the drawn chinese
     * character/ character made in the manual input panel is updated.
     */
    public static void clearList(){
        chineseCharacters = new ArrayList<String>();
            //clears the arraylist of Characters
        listOfPossibleChineseCharacters.setModel(new AbstractListModel() {
                //updates the list to whatever is in the ArrayList
                //chineseCharacter
            public int getSize() { return chineseCharacters.size(); }
                //returns the size of the ArrayList chineseCharacters
            public Object getElementAt(int i) {return chineseCharacters.get(i);}
                //returns the element at the parameter int i in the ArrayList
                //chineseCharacters
        });
    }
    /**
     * this method returns the JPanel PossibleChineseCharactersPanel. This 
     * method is called through the interface of adding gui components to the
     * ChineseFrame
     * @return
     */
    public Component getComponent() {
        return this;
    }

// <editor-fold defaultstate="collapsed" desc="Mouse Events">
    /**
     * nothing happens in this method.
     * @param e the mousevent that occured and did nothing
     */
    public void mouseClicked(MouseEvent e) {/*Nothing needed here, keep blank
        (for now)*/}
    /**
     * this mouse pressed event is responsible for selecting the found character
     * needed and adding it to a text field of Chinese characters
     * @param e the MouseEvent that occured
     */
    public void mousePressed(MouseEvent e) {
        String s = (((String) listOfPossibleChineseCharacters.getSelectedValue()
            ).split(":")[0]);
                //the first half of the String indexed from the list
        if (s.length() > 1) {
                //sometimes there are problems with the Chinese fonts so more
                //than one character shows up. (it looks like a box (☐) next
                //to a chinese character. So if the sting is too long we
            s = s.charAt(s.length()-1) + "";
                //break up the String to get the character
        }
        CharacterFieldPanel.addToList(s);
            //adds the chinese chracter to the character field
        InternalDrawFramePanel.clearPanel();
            //clears the internaldrawframe (so you dont have to press clear
            //every time you want to add the chracter to the character field)
    }
    /**
     * nothing happens in this method.
     * @param e the mousevent that occured and did nothing
     */
    public void mouseReleased(MouseEvent e) {/*Nothing needed here, keep blank
        (for now)*/}
    /**
     * nothing happens in this method.
     * @param e the mousevent that occured and did nothing
     */
    public void mouseEntered(MouseEvent e) {/*Nothing needed here, keep blank
        (for now)*/}
    /**
     * nothing happens in this method.
     * @param e the mousevent that occured and did nothing
     */
    public void mouseExited(MouseEvent e) {/*Nothing needed here, keep blank
        (for now)*/}// </editor-fold>
}
