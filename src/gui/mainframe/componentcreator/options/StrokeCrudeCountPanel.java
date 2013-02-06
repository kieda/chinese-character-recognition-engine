/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/options/StrokeCrudeCountPanel.java
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
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;// </editor-fold>

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
public class StrokeCrudeCountPanel extends JPanel implements
        ChineseFrameComponent{
    private static JSpinner numberOfStrokesCounter = new JSpinner();
    private static JLabel numberOfStrokesLabel = new JLabel();
        //components used

    /**
     * constructor that builds and initializes the StrokeCrudeCountPanel's GUI
     * THis panel is seen in the manual input panel
     */
    public StrokeCrudeCountPanel() {
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        numberOfStrokesLabel.setText("Number of Strokes");

        numberOfStrokesCounter.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                ChineseFrameActionHandler
                    .numberOfStrokesCounterStateChanged(evt);
            }
        });

        GroupLayout numberOfStrokesPanelLayout = new GroupLayout(this);
        setLayout(numberOfStrokesPanelLayout);
        numberOfStrokesPanelLayout.setHorizontalGroup(
            numberOfStrokesPanelLayout.createParallelGroup(GroupLayout.Alignment
                .LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, numberOfStrokesPanelLayout
                    .createSequentialGroup()
                .addContainerGap()
                .addComponent(numberOfStrokesCounter, GroupLayout.PREFERRED_SIZE
                    , 53, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63
                    , Short.MAX_VALUE)
                .addComponent(numberOfStrokesLabel)
                .addContainerGap())
        );
        numberOfStrokesPanelLayout.setVerticalGroup(
            numberOfStrokesPanelLayout.createParallelGroup(GroupLayout.Alignment
                .LEADING)
            .addGroup(numberOfStrokesPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(numberOfStrokesPanelLayout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                    .addComponent(numberOfStrokesCounter, GroupLayout
                        .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE
                        , GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberOfStrokesLabel))
                .addContainerGap())
        );
    }
    // <editor-fold defaultstate="collapsed" desc="gets and sets">
        //used to get and set the values of the spinner
    public static int getNumberOfStrokes() {
        return Integer.parseInt(numberOfStrokesCounter.getValue() + "");
    }// </editor-fold>
    /**
     * get component required method by ChineseFrameComponent.
     * returns this panel
     * @return the StrokeCrudeCountPanel
     */
    public Component getComponent() {
        return this;
    }

}
