/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/options/IntersectionStringInputPanel.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator.options;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.ChineseFrameActionHandler;
import gui.mainframe.componentcreator.ChineseFrameComponent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;// </editor-fold>
/**
 * Due to a recent (something) I have decided to comment my code less than
 * usual. {I think i'm sick of commenting. Or i'm almost out of timee. either
 * one.} Comments on several classes only really have major
 * descriptions of methods. Commenting will improve on this method eventually
 *
 *
 * 
 * @author Kieda
 * @version 2.2
 * @since 3-23-2011
 */
public class IntersectionStringInputPanel extends JPanel
        implements ChineseFrameComponent{
    private static JTextField intersectionsStringInput = new JTextField();
    private static JLabel intersectionsStringInputLabel = new JLabel();
        //components used
    
    /**
     * constructor that builds and initializes the 
     * IntersectionStringInputPanel's GUI. This panel is seen in the manual
     * input panel
     */
    public IntersectionStringInputPanel() {
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        intersectionsStringInput.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent evt) {
                ChineseFrameActionHandler
                    .intersectionsStringInputCaretUpdate(evt);
            }
        });

        intersectionsStringInputLabel
            .setText("String Input (separate by commas)");

        GroupLayout intersectionsStringInputPanelLayout = new GroupLayout(this);
        setLayout(intersectionsStringInputPanelLayout);
        intersectionsStringInputPanelLayout.setHorizontalGroup(
            intersectionsStringInputPanelLayout.createParallelGroup(
                GroupLayout.Alignment.LEADING)
            .addGroup(intersectionsStringInputPanelLayout.createSequentialGroup
                ().addContainerGap()
                .addGroup(intersectionsStringInputPanelLayout
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(intersectionsStringInputPanelLayout
                            .createSequentialGroup()
                        .addComponent(intersectionsStringInput, GroupLayout
                            .DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING,
                            intersectionsStringInputPanelLayout
                            .createSequentialGroup()
                        .addComponent(intersectionsStringInputLabel)
                        .addGap(25, 25, 25))))
        );
        intersectionsStringInputPanelLayout.setVerticalGroup(
            intersectionsStringInputPanelLayout.createParallelGroup(GroupLayout
                .Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING,
                    intersectionsStringInputPanelLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(intersectionsStringInputLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intersectionsStringInput, GroupLayout
                    .PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }
    // <editor-fold defaultstate="collapsed" desc="gets and sets">
        //used to get and set the values of the text field
    public static String getIntersectionsString() {
        return intersectionsStringInput.getText();
    }
    public static void setIntersectionsString(String s) {
        intersectionsStringInput.setText(s);
    }// </editor-fold>
    /**
     * get component required method by ChineseFrameComponent.
     * returns this panel
     * @return the IntersectionStringInputPanel
     */
    public Component getComponent() {
        return this;
    }

}
