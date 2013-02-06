/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/options/StrokeComplexityPanel.java
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
 * @version 1.0
 * @since 3-23-2011
 */
public class StrokeComplexityPanel extends JPanel
        implements ChineseFrameComponent{
    
    private static JSpinner numberOfStraightStrokesCounter = new JSpinner();
    private static JLabel numberOfStraightStrokesLabel = new JLabel();
    private static JLabel numberOfComplexStrokesLabel= new JLabel();
    private static JSpinner numberOfComplexStrokesCounter = new JSpinner();
        //components used
    
    /**
     * constructor that builds and initializes the StrokeComplexityPanel's GUI
     * THis panel is seen in the manual input panel
     */
    public StrokeComplexityPanel() {
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        numberOfStraightStrokesLabel.setText("Number of Straight Strokes");

        numberOfStraightStrokesCounter.addPropertyChangeListener(
                new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                ChineseFrameActionHandler
                    .numberOfStraightStrokesCounterPropertyChange(evt);
            }
        });

        numberOfComplexStrokesLabel.setText("Number of Complex Strokes");

        numberOfComplexStrokesCounter.addPropertyChangeListener(
                new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                ChineseFrameActionHandler
                    .numberOfComplexStrokesCounterPropertyChange(evt);
            }
        });

        GroupLayout strokeComplexityPanelLayout = new GroupLayout(this);
        setLayout(strokeComplexityPanelLayout);
        strokeComplexityPanelLayout.setHorizontalGroup(
            strokeComplexityPanelLayout.createParallelGroup(GroupLayout.
                Alignment.LEADING)
            .addGroup(strokeComplexityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(strokeComplexityPanelLayout.createParallelGroup(
                        GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberOfComplexStrokesCounter
                        , GroupLayout.Alignment.TRAILING)
                    .addComponent(numberOfStraightStrokesCounter, GroupLayout.
                        Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 51
                        , Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 21
                    , Short.MAX_VALUE)
                .addGroup(strokeComplexityPanelLayout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                    .addComponent(numberOfStraightStrokesLabel, GroupLayout
                        .Alignment.TRAILING)
                    .addComponent(numberOfComplexStrokesLabel, GroupLayout
                        .Alignment.TRAILING))
                .addContainerGap())
        );
        strokeComplexityPanelLayout.setVerticalGroup(
            strokeComplexityPanelLayout.createParallelGroup(GroupLayout.
                Alignment.LEADING)
            .addGroup(strokeComplexityPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(strokeComplexityPanelLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                    .addComponent(numberOfStraightStrokesLabel)
                    .addComponent(numberOfStraightStrokesCounter, GroupLayout.
                        PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.
                        PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(strokeComplexityPanelLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                    .addComponent(numberOfComplexStrokesLabel)
                    .addComponent(numberOfComplexStrokesCounter, GroupLayout.
                        PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.
                        PREFERRED_SIZE))
                .addContainerGap())
        );
    }
    // <editor-fold defaultstate="collapsed" desc="gets and sets">
        //used to get and set the values of the spinners
    public static int getNumberOfStraightStrokes() {
        return Integer.parseInt(numberOfStraightStrokesCounter.getValue()+"");
    }
    public static int getNumberOfComplexStrokes() {
        return Integer.parseInt(numberOfComplexStrokesCounter.getValue()+"");
    }
    public static void setNumberOfStraightStrokes(int i) {
        numberOfStraightStrokesCounter.setValue(i);
    }
    public static void setNumberOfComplexStrokes(int i) {
        numberOfComplexStrokesCounter.setValue(i);
    }// </editor-fold>
    /**
     * get component required method by ChineseFrameComponent.
     * returns this panel
     * @return the StrokeComplexityPanel
     */
    public Component getComponent() {
        return this;
    }

}
