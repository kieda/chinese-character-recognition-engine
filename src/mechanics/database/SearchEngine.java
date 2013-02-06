/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/database/SearchEngine.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics.database;

import universals.UniversalDataStorage;

/**
 * THis search engine is the main class that is used for searching for chainese
 * characters. THis class will be improved as ttime goes on, and things like
 * character likelyhood and various rankings will help enhance this class.
 * 
 * @version .3 crappy version :P
 * @author Kieda
 * @since 3-22-2011
 *//*INHEIRITANCE*/
public class SearchEngine extends UniversalDataStorage{
    /**
     * Searches for a character match in the database
     */
    public static void searchForChineseCharacter(){
        if(intersections.size()>0)
                //makes sure that there wont be any errors in the search
            DatabaseDriver.searchCharacter(intersections
                .toArray(), numberOfFloatingParts
                , numberOfStraightStrokes
                , numberOfComplexStrokes);
                    //passes to the database driver's search
    }
}
