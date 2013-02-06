/*//////////////////////////////////////////////////////////////////////////////
./src/mechanics/recognition/ChineseManager.java
//////////////////////////////////////////////////////////////////////////////*/
package mechanics.recognition;

import universals.CreatedCharacter;//import

/**
 * THis class is an interface used to manage all of the  algorithms used in this
 * program. To add a new algorithm, create a new class that implements this
 * class, then call the updatemanager when the program is refreshed.
 *
 * the data type <T> is any kind of data type the user could really want for
 * comparing chinese characters. One xgample is an Integer used to compare stroke
 * count.
 * 
 * @author Kieda
 * @version 1.0
 * @since 3-22-2011
 *//*MAJOR USE OF ABSTRACT DATA TYPES*/
public interface ChineseManager<T> {
    /**
     * the cuttent <T> value. THis method shold be called when wanting to get
     * the value without updating the manager
     * @return <T> comparable data
     */
    public T returnValue();
    /**
     * this method is where the data calculations should go. THere is an input
     * ChineseCharacter that will be anlyzed geometrically in this method.
     * @param p the character to be geometrically analyzed
     * @return <T> comparable data
     */
    public T processData(CreatedCharacter p);
    /**
     * this method should be called (usullly in place of the returnValue) very
     * often/ used whenever there is a change on the drawpanel. THis method
     * cahh processData with the character from the UniversalDataStroage
     *
     * this method should then update the UnviverSalDatastorage's valuse
     */
    public void updateManager();
}
