package lib;

import java.util.ArrayList;

/**
 * ###############################################
 * Kayako App
 * _______________________________________________
 *
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http://www.kayako.com/license
 * @link http://www.kayako.com
 * ###############################################
 */
public abstract class KEntityCustom extends KEntity {


    /**
     * Name of class representing object custom field group.
     *
     * @var string
     */
    static protected String customFieldGroupClass = null;

    /**
     * Name of URL parameter for sending object identifier.
     *
     * @var string
     */
    static protected String objectIdField = null;

    /**
     * For fast lookup of custom fields based on their name.
     *
     * @var array
     */
    protected ArrayList<CustomField> customFields = new ArrayList<CustomField>();

    /**
     * Object custom field groups.
     *
     * @var
     */
    protected RawArrayElement customFieldGroups = null;


    /*
   * loadCustomFieldGroups
    * initFields
    * getCustomFields
    * getCustomFieldGroups
    * getCustomField
    * getCustomFieldValue
    * setCustomFieldValue
    * setCustomFieldValuesFromPOST
    * updateCustomFields
   * */

}
