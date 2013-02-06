/*//////////////////////////////////////////////////////////////////////////////
./gui/mainframe/componentcreator/menubar/MenuBar.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator.menubar;

// <editor-fold defaultstate="collapsed" desc="impoorts">
import gui.mainframe.componentcreator.ChineseFrameComponent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;// </editor-fold>

/**
 * This is the MenuBar at the top of the ChineseFrame. This menubar provides
 * additional functionaliy, usablity, and features
 * @author Kieda
 * @version 1.4
 * @since 3-20-2011
 */
public class MenuBar extends JMenuBar implements ChineseFrameComponent {
    private static JMenu edit = new JMenu();
    private static JMenu file = new JMenu();
    private static JMenu chineseCharacterFinder = new JMenu();
        //JMenus 
    private static JMenuItem exitMenuButton = new JMenuItem();//exits program
    private static JMenuItem clear = new JMenuItem();//clears drawpanel
    private static JMenuItem reloadDatabase = new JMenuItem();//reloads database
        //JMenuItams
    private static JRadioButtonMenuItem eraser = new JRadioButtonMenuItem();
        //eraser. not implemented currently, but will in the future
    private static JRadioButtonMenuItem pen = new JRadioButtonMenuItem();
        //pen. not implemented currently, but will in the future

    /**
     * the constructor for the MenuBar. THis initializes all of the GUI swing
     * components
     */
    public MenuBar() {
        file.setText("File");
        exitMenuButton.setText("Exit");
        clear.setText("Clear");
            //set menu text
        
        // <editor-fold defaultstate="collapsed" desc="action listeners">
        exitMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                    //action that will happen when the exitMenuButton is pressed
                MenuBarActionHandler.exitActionPerformed(evt);
                    //exits the program
            }
        });
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    //action that will happen when the clear button is pressed
                MenuBarActionHandler.clearActionPerformed(e);
                    //clears the drawpanel
            }
        });
        reloadDatabase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    //action that will happen when the reloadDatabase is pressed
                MenuBarActionHandler.reloadDatabase(e);
                    //relaods the database. Helpful when adding new characters
                    //to the database
            }
        });// </editor-fold>
        file.add(exitMenuButton);//adds the exitMenuButton to the JMenu file
        reloadDatabase.setText("Reload Database");
        edit.setText("Edit");
        eraser.setText("Eraser");
        chineseCharacterFinder.setText("Chinese Character Finder");
            //sets the text on some other menu items

        chineseCharacterFinder.add(reloadDatabase);
            //adds the reloadDatabase to the JMenu chineseCharacterFinder
        add(file);//adds file to the main NenuBar
        edit.add(clear);//adds clear to the JMenu edit

        /*Pen and eraser stuff**********/
        eraser.setSelected(false);
        eraser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuBarActionHandler.eraserStateChanged(e);
            }
        });
        pen.setSelected(true);
        pen.setText("Pen");
        pen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuBarActionHandler.penStateChanged(e);
            }
        });
        /*Pen and eraser stuff**********/

        add(edit);//adds edit to the main NenuBar
        add(chineseCharacterFinder);
            //adds chineseCharacterFinder to the main NenuBar
    }
    /**
     * the required method for classes implementing ChineseFrameComponent,
     * returns the instance of this class
     * @return the MenuBar
     */
    @Override
    public Component getComponent() {
        return this;
    }
    /**
     * sets the radio buttons as a pen or an eraser. THis is important to call 
     * to switch from pen to eraser (and back). True is the eraser, false is the
     * pen.
     * @param b pen or eraser boolean
     */
    public static void penOrEraser(boolean b){
        eraser.setSelected(b);
        pen.setSelected(!b);
            //sets the pen selected to the opposite of the eraser
    }
    /**
     * tells whether or not we have a pen. THis method returns true if we are 
     * using a pen, and false if we are using an eraser. This is important while
     * drawing to  ensure we draw or erase correctly
     * @return pen or eraser
     */
    public static boolean getPenOrEraser(){
        return pen.isSelected();
    }
}
