/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/options/IntersectionsCrudeAmountPanel.java
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
 * @version 2.0
 * @since 3-23-2011
 */
public class IntersectionsCrudeAmoutPanel extends JPanel
        implements ChineseFrameComponent{
    private static JSpinner totalNumberOfIntersectionsCounter = new JSpinner();
    private static JLabel totalNumberOfIntersectionsLabel = new JLabel();
        //components used
    
    /**
     * constructor that builds and initializes the 
     * IntersectionsCrudeAmoutPanel's GUI. THis panel is seen in the manual
     * input panel
     */
    public IntersectionsCrudeAmoutPanel() {
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        totalNumberOfIntersectionsLabel.setText("Total Number of Intersections");

        totalNumberOfIntersectionsCounter.addPropertyChangeListener(
                new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                ChineseFrameActionHandler
                    .totalNumberOfIntersectionsCounterPropertyChange(evt);
            }
        });

        GroupLayout totalNumberOfIntersectionsPanelLayout=new GroupLayout(this);
        setLayout(totalNumberOfIntersectionsPanelLayout);
        totalNumberOfIntersectionsPanelLayout.setHorizontalGroup(
            totalNumberOfIntersectionsPanelLayout.createParallelGroup(
                GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING,
                    totalNumberOfIntersectionsPanelLayout
                    .createSequentialGroup()
                .addContainerGap()
                .addComponent(totalNumberOfIntersectionsCounter,
                    GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8
                    , Short.MAX_VALUE)
                .addComponent(totalNumberOfIntersectionsLabel)
                .addContainerGap())
        );
        totalNumberOfIntersectionsPanelLayout.setVerticalGroup(
            totalNumberOfIntersectionsPanelLayout.createParallelGroup(
                GroupLayout.Alignment.LEADING)
            .addGroup(totalNumberOfIntersectionsPanelLayout
                    .createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(totalNumberOfIntersectionsPanelLayout
                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(totalNumberOfIntersectionsLabel)
                    .addComponent(totalNumberOfIntersectionsCounter, GroupLayout
                        .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout
                        .PREFERRED_SIZE))
                .addContainerGap())
        );
    }
    // <editor-fold defaultstate="collapsed" desc="gets and sets">
        //used to get and set the values of the spinner
    public static int getTotalNumberOfIntersections() {
        return Integer
            .parseInt(totalNumberOfIntersectionsCounter.getValue()+"");
    }
    public static void setTotalNumberOfIntersections(int i) {
        totalNumberOfIntersectionsCounter.setValue(i);
    }// </editor-fold>
    /**
     * get component required method by ChineseFrameComponent.
     * returns this panel
     * @return the IntersectionsCrudeAmoutPanel
     */
    public Component getComponent() {
        return this;
    }

}
