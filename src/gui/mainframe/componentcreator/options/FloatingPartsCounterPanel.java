/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/options/FloatingPartsCounterPanel.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator.options;

// <editor-fold defaultstate="collapsed" desc="imports">
import gui.mainframe.ChineseFrameActionHandler;
import gui.mainframe.componentcreator.ChineseFrameComponent;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;// </editor-fold>

/**
 * Due to a recent (something) I have decided to comment my code less than
 * usual. {I think i'm sick of commenting. Or i'm almost out of timee. either
 * one.} Comments on several classes only really have major
 * descriptions of methods. Commenting will improve on this method eventually
 *
 *
 * 
 * @author Kieda
 * @version 1.3
 * @since 3-23-2011
 */
public class FloatingPartsCounterPanel extends JPanel
        implements ChineseFrameComponent{
    private static JSpinner numberOfFloatingPartsCounter = new JSpinner();
    private static JLabel numberOfFloatingPartsLabel = new JLabel();
        //components used

    /**
     * constructor that builds and initializes the FloatingPartsCounterPanel's
     * GUI. This panel is seen in the manual input panel
     */
    public FloatingPartsCounterPanel() {
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        numberOfFloatingPartsLabel.setText("Number of Floating Parts");

        numberOfFloatingPartsCounter.addPropertyChangeListener(
                new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                ChineseFrameActionHandler
                    .numberOfFloatingPartsCounterPropertyChange(evt);
            }
        });

        GroupLayout numberOfFloatingPartsPanelLayout = new GroupLayout(this);
        setLayout(numberOfFloatingPartsPanelLayout);
        numberOfFloatingPartsPanelLayout.setHorizontalGroup(
            numberOfFloatingPartsPanelLayout.createParallelGroup(GroupLayout
                .Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING,
                    numberOfFloatingPartsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberOfFloatingPartsCounter, GroupLayout
                    .PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED
                    , 37, Short.MAX_VALUE)
                .addComponent(numberOfFloatingPartsLabel)
                .addContainerGap())
        );
        numberOfFloatingPartsPanelLayout.setVerticalGroup(
            numberOfFloatingPartsPanelLayout.createParallelGroup(GroupLayout
                .Alignment.LEADING)
            .addGroup(numberOfFloatingPartsPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(numberOfFloatingPartsPanelLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                    .addComponent(numberOfFloatingPartsLabel)
                    .addComponent(numberOfFloatingPartsCounter, 
                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE
                        , GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }
    // <editor-fold defaultstate="collapsed" desc="gets and sets">
        //used to get and set the values of the spinner
    public static int getNumberOfFloatingParts() {
        return Integer.parseInt(numberOfFloatingPartsCounter.getValue()+"");
    }
    public static void setNumberOfFloatingParts(int i) {
        numberOfFloatingPartsCounter.setValue(i);
    }// </editor-fold>
    /**
     * get component required method by ChineseFrameComponent.
     * returns this panel
     * @return the FloatingPartsCounterPanel
     */
    public Component getComponent() {
        return this;
    }

}
