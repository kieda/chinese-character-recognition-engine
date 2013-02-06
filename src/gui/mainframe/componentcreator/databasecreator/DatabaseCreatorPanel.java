/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/databasecreator/DatabaseCreatorPanel.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator.databasecreator;

// <editor-fold defaultstate="collapsed" desc="imports">
import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;
import gui.mainframe.ChineseFrameActionHandler;
import gui.mainframe.componentcreator.ChineseFrameComponent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import universals.FontFinder;// </editor-fold>

/**
 * This panel is used to assist the user in creating a database. this panel
 * goes on the bottom of the ChineseFrame, and is added when the DatabaseMain is
 * run
 * @version 1.6
 * @author Kieda
 * @since 3-19-2011
 *//*ENCAPSULATION*/
public class DatabaseCreatorPanel extends JPanel
        implements  ChineseFrameComponent{
    private static String characterToWriteString;
        //This is the String chinese character retreived from the database and
        //is converted to the single chinese character the user is supposed to
        //write
    private JButton addToDatabaseButton;
        //the button used to add to the character made on the draw panel to the
        //database.
    private JButton clearButton;
        //The button that clears the drawpanel of all drawing. This button also
        //clears the CreatedCharacter
    private JButton nextButton;
        //the button used to skip chinese characters (used if the user already
        //inputted the chinese character)
    private JLabel characterToWriteLiteral;
        //the label that literally says "Character To Write". this label is used
        //to mark the panel and which character the user is ssupposed to write
    static JLabel characterToWriteLabel;
        //the JLabel that displayes the character that the user should write

    /**
     * The constructor for the DatabaseCreatorPanel. This initializes the 
     * components of the DatabaseCreatorPanel and sets what character will be
     * wrote first
     * @param characterToWrite the character that will show up originally on the 
     *        characterToWriteLabel. This is used to prevent errors and to have
     *        a character to start from
     */
    public DatabaseCreatorPanel(String characterToWrite) {
        DatabaseCreatorPanel.characterToWriteString = characterToWrite;
            //sets the string characterToWriteString to the parameter 
            //characterToWrite
        initComponents();
            //initializes the components of the DatabaseCreatorPanel
    }

    /**
     * this method returns the string of the character that the user wrote,
     * along with the infromation like the characters definition and pinyin
     * This method should be called when the user wants to add the character to
     * the database
     * 
     * @return the string of the character that the user wrote. This comes in
     *         the form "Character:Definition:Pinyin", and should be broken down
     *         accordingly to get data
     *//*ENCAPSULATION*/
    public static String getCharacterToWrite() {
        return characterToWriteString;
    }
    /**
     * this method sets the character that the user will have to write next. THe
     * next character to write is retreived from the database
     * ("UNFILLED_CHARACTER.DAT") and given to the characterToWriteLabel and the
     * datatabase (characters.DAT) when the character is wrote. Call this method
     * when you want to write a different character.
     * 
     * @param characterToWrite the sting character from the database in the form
     *        "Character:Definition:Pinyin" that the user wll have to write next
     */
    public static void setCharacterToWrite(String characterToWrite) {
        DatabaseCreatorPanel.characterToWriteString = characterToWrite;
            //sets the global variable to the local variable given
        characterToWriteLabel.setText(characterToWrite.split(":")[0].
            charAt(characterToWrite.split(":")[0].length()-1)+"");
                //sets the character that the user will write next to the last
                //character befor the first colon (':'). This is because there
                //are some buggy things with the database/chinese fonts where
                //they will sometimes return (a) box(es) character before the
                //actual chinese character.
    }
    /**
     * This method is used to initialize all of the GUI swing components of the 
     * DatabaseCreatorPanel. THis step method should be called SECOND to the
     * etting of the character to write string
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        characterToWriteLabel = new JLabel();
            //creates the characterToWriteLabel
        characterToWriteLabel.setText(characterToWriteString.split(":")[0]
            .charAt(characterToWriteString.split(":")[0].length()-1)+"");
                //takes the character at the end of the string befor the first
                //colon (':'), and puts it on the characterToWriteLabel, so the
                //user knows what character to write.
        characterToWriteLabel.setFont(new Font(FontFinder.chinesefonts.get(3)
            ,Font.PLAIN,140));
                //sets the font of the characterToWriteLabel to onbe that can
                //acutally display Chinese characters. THe font is also set to
                //size 140 for program usbility and to see the characters easily
        setBorder(new BevelBorder(true, Color.DARK_GRAY, Color.LIGHT_GRAY));
            //sets the border of this panel to a raised light/dark gray bevel
            //border
        addToDatabaseButton = new JButton();
            //creates a new addToDatabaseButton that will be used to add 
            //characters to the database
        clearButton = new JButton();
            //creates a new button used to clear the drawpanel
        nextButton = new JButton();
            //creates a new button to jump to the next character
        characterToWriteLiteral = new JLabel();
            //creates a label that will mark the character to write
        addToDatabaseButton.setText("Add to Database");
            //sets the text of the addToDatabaseButton
        clearButton.setText("Clear");
            //sets the text of the clearButton
        nextButton.setText("Next");
            //sets the text of the nextButton
        addToDatabaseButton.addActionListener(new ActionListener() {
                //creates a new action listener for the addToDatabaseButton.
                //This allows something to happen when the button is pressed.
            public void actionPerformed(ActionEvent e) {
                ChineseFrameActionHandler.addToDatabaseActionEvent(e
                    ,characterToWriteString);
                        //executes the acion in the method 
                        //addToDatabaseActionEvent in ChineseFrameActionHandler.
                        //This action adds the character to the database.
            }
        });
        clearButton.addActionListener(new ActionListener() {
                //creates a new action listener for the clearButton.
                //This allows something to happen when the button is pressed.
            public void actionPerformed(ActionEvent e) {
                ChineseFrameActionHandler.clearActionPerformed(e);
                    //clears the DrawFrame and the CreatedCharacter
            }
        });
        nextButton.addActionListener(new ActionListener() {
                //creates a new action listener for the nextButton.
                //This allows something to happen when the button is pressed.
            public void actionPerformed(ActionEvent e) {
                ChineseFrameActionHandler.nextButtonActionPerformed(e);
                    //goes to the next character in the database
            }
        });
        characterToWriteLiteral.setFont(new java.awt.Font("Tahoma", 0, 15));
            //sets the font of the characterToWriteLiteral to something larger
            //(for readability)
        characterToWriteLiteral.setText("Character To Write");
            //sets the text of the characterToWriteLiteral
        System.out.println(characterToWriteString);
            //prints out the next character to write. Refer to the printed 
            //character in case if there is any trouble displaying the
            //CharacterToWrite.
        GroupLayout layout = new GroupLayout(this);
            //creates a GroupLayout for this panel
        this.setLayout(layout);
            //sets this panel's layout to the GroupLayout created above.
        layout.setHorizontalGroup(//start of horizontal grouping
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    //create a group to contain everything
                .addContainerGap()
                    //adds a gap on the left for alignment
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment
                        .LEADING)
                            //adds a parallel group to contain the
                            //characterToWriteLiteral and the
                            //characterToWriteLabel parallel-ey
                    .addComponent(characterToWriteLiteral)
                        //adds the characterToWriteLiteral
                    .addComponent(characterToWriteLabel, GroupLayout
                        .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE
                        , GroupLayout.PREFERRED_SIZE)
                            //adds the characterToWriteLabel
                )
                .addGap(100)//adds a large gap to align the buttons
                .addComponent(addToDatabaseButton,50,125,200)
                    //add the database button
                .addGap(10)//a small gap
                .addComponent(clearButton,50,125,200)//adds the clear button
                .addGap(10)//add a small gap
                .addComponent(nextButton,50,125,200)//adds the next button
                .addContainerGap()
                    //adds a container gap at the end for alignment
            )
        );
        layout.setVerticalGroup(//start of vertical grouping
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    //uses a parallel group/sequential group to contain the 
                    //components on the far left.
                .addContainerGap()//container gap for alignment
                .addComponent(characterToWriteLiteral)
                    //adds the characterToWriteLiteral
                .addGap(10) //adds a gap between the two labels
                .addComponent(characterToWriteLabel, GroupLayout.PREFERRED_SIZE
                    , GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        //adds the characterToWriteLabel
            )
            .addGroup(layout.createParallelGroup()
                    //add a parallel group to hold the buttons and spacings
                    //parallel-ey
                .addGroup(layout.createSequentialGroup()
                        //create sequential group to add a container gap
                    .addContainerGap()//add a container gap for aligment
                    .addGroup(layout.createParallelGroup()
                            //create a parallel group to hold the buttons
                        .addComponent(addToDatabaseButton,100,100,100)
                            //add a addToDatabaseButton
                        .addComponent(clearButton,100,100,100)
                            //add a clearButton
                        .addComponent(nextButton,100,100,100)
                            //add a nextButton
                    )
                )
            )
        );
    }
    /**
     * the required method for classes implementing ChineseFrameComponent,
     * returns the instance of this class
     * @return the DatabaseCreatorPanel
     */
    public Component getComponent() {
        return this;//returns itself/this components
    }
}
