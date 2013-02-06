/*//////////////////////////////////////////////////////////////////////////////
./src/gui/mainframe/componentcreator/drawpanel/InternalDrawFramePanel.java
//////////////////////////////////////////////////////////////////////////////*/
package gui.mainframe.componentcreator.drawpanel;

// <editor-fold defaultstate="collapsed" desc="imports">
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import universals.ChineseStroke;
import universals.UniversalDataStorage;// </editor-fold>

/**
 * this panel is used in the internal draw frame. This panel shows the lines
 * drawn by the user
 * @version 2.2
 * @author Kieda
 * @since 3-20-2011
 */
public class InternalDrawFramePanel extends JPanel{
    /**
     * the constuctor and initialization of the panel. Setting the panel's
     * bounds is required at the beginning
     * @param bounds the bounds of the InternalDrawFramePanel
     */
    public InternalDrawFramePanel(Rectangle bounds) {
        setBounds(bounds);//sets the bounds of this panel
    }
    private BufferedImage bi;
        //the buffered image used for the InternalDrawFramePanel
    private Graphics2D big;
        //the graphics used to paint to the buffered image bi
    private static boolean firstTime = true;
        //a boolean used for initializing and clearing the panel and buffered
        //image

    /**
     * paints the character on the InternalDrawFramePanel. call repaint to
     * refresh the screen
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g){
        ChineseStroke points = UniversalDataStorage.points;
            //gets the current points stored in UniversalDataStorage
        Graphics2D g2= (Graphics2D) g;
            //creates a new Graphics2D in the form g
        if(firstTime){//if this is the first time painting
            Dimension dim = getSize();//get the dimension of the panel
            bi = (BufferedImage) createImage(dim.width, dim.height);
                //create a buffered image from the dimensions (see above)
            big = (Graphics2D) bi.createGraphics();
                //allows the big to edit the buffered image
            big.clearRect(0, 0, bi.getWidth(), bi.getHeight());
                //clears the buffered image
            big.setStroke(new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke
                .JOIN_ROUND, 1.0f, new float[] {10,10,10,10,10}, 0));
                    //makes the stroke large and dashed, used in the background
                    //for drawing alignment purposes
            big.setPaint(Color.LIGHT_GRAY);
                //sets the paint to get ready to draw the backgound dash lines
            big.drawLine(0, dim.height/2, dim.width, dim.height/2);
                //horizontal dashed line
            big.drawLine(dim.width/2, 0, dim.width/2, dim.height);
                //vertical dashed line
            big.setStroke(new BasicStroke(1));//sets the stroke back
            big.setPaint(Color.BLACK);//sets the color back to black
            firstTime = false;//this is no longer the first time
        }
        if(points.size()>1){
                //used because getting an index of -1 in an array
                //doesn't really work out that well
            for(int i = 1; i<points.size();i++){//goes through all of the points
                big.setPaint(Color.BLACK);//sets the color black
                big.drawLine(points.get(i-1)[0], points.get(i-1)[1]
                    , points.get(i)[0] , points.get(i)[1]);
                        //draws a line from the previous point to he next point
                big.setPaint(Color.LIGHT_GRAY);
                    //sets the color to light gray to draw points of line
//                big.drawOval(points.get(i-1)[0]-1, points.get(i-1)[1]-1, 2, 2);
//                    //draws circles where a new line starts
            }
        }
        g2.drawImage(bi, 0, 0, this);//draws the buffered image
    }
    /**
     * this method clears the panel. It creates a new buffered image and clears
     * all of the points. THis method shold be called after adding a character 
     * to the database, after clicking the next button for the database, and a
     * person hits the clear button, and after a user selects a found character
     */
    public static void clearPanel(){
        firstTime = true;
            //to clear the graphics, it is as simple as restating the panel as
            //if it were the first time running
        UniversalDataStorage.chineseCharacter.clear();//clears the character
        UniversalDataStorage.points.clear();//clears the current stroke
    }
}
