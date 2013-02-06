/*//////////////////////////////////////////////////////////////////////////////
./src/universals/FontFinder.java
//////////////////////////////////////////////////////////////////////////////*/
package universals;

// <editor-fold defaultstate="collapsed" desc="imports">
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Vector;// </editor-fold>

/**
 * This class is used to find fonds that can read Chinese characters. This is
 * done by providing sample chinese then seeing if a font can read it. If so,
 * add the font to a Vector of the usable Fonts. This method is called before 
 * initialization, and should be initialized using the InitializationManager.
 * Call this method beforehand, otherwise there are some funky null errors that
 * can occur because the chinesefonts Vector is empty
 * @author Kieda
 * @version 1.0
 * @since 3-21-2011
 */
public class FontFinder {
    public static Vector<String> chinesefonts;
        //the list of fonts that are compatible with Chinese Characters
        //it's a vector because I wanted to use something else than an arraylist
        //for once
    /**
     * Finds all of the fonts that can read Chinese characters. Call this method
     * for initialization. Can be re-called at any time (recalled if a new font
     * was installed or something)
     */
    public static void findFonts(){
        chinesefonts = new Vector();//initializes the Vector of fonts
	Font[] allbuiltinfonts=GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getAllFonts();
                //gets all of the fonts that are in the local graphics
                //environment. these fonts are weeded through to figure out the
                //ones compatible with chinese
	String chinesesample = "\u4e00";//chinese character sample
	for (int j = 0; j < allbuiltinfonts.length; j++) {
	    if (allbuiltinfonts[j].canDisplayUpTo(chinesesample) == -1) {
                    //goes through all of the fonts and finds the ones that can
                    //read Chinese
	        chinesefonts.add(allbuiltinfonts[j].getFontName());
                    //adds the font that can read chinese to the Vector
            }
	}
        try {
            Thread.sleep(500);
                //sleep between finding fonts (for when the frame pops up, used
                //so that it does not flash too quickly)
        } catch (InterruptedException ex) {}
    }
}
