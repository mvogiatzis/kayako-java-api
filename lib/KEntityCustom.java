package lib;

import java.util.ArrayList;
import java.util.HashMap;

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
    protected ArrayList<CustomFieldGroup> customFieldGroups = new ArrayList<CustomFieldGroup>();


    public ArrayList<CustomFieldGroup> getCustomFieldGroups() {
        return customFieldGroups;
    }

    public void setCustomFieldGroups(ArrayList<CustomFieldGroup> customFieldGroups) {
        this.customFieldGroups = customFieldGroups;
    }

    public ArrayList<CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(ArrayList<CustomField> customFields) {
        this.customFields = customFields;
    }

    public static String getObjectIdField() {
        return objectIdField;
    }

    public static void setObjectIdField(String objectIdField) {
        KEntityCustom.objectIdField = objectIdField;
    }


    /*
    * initFields
    * getCustomFields
    * getCustomFieldGroups
    * getCustomField
    * getCustomFieldValue
    * setCustomFieldValue
    * setCustomFieldValuesFromPOST
   * */

    protected abstract ArrayList<CustomFieldGroup> loadCustomFieldGroups(Boolean refresh);


    /**
     * Prepares local array for custom field fast lookup based on its name.
     * this function should populate this.customFields
     */
    protected abstract ArrayList<CustomField> loadCustomField(Boolean refresh);

    //this method needs to be implemented in the derived class to basically call the overloaded method with correct arguments
    public abstract KEntityCustom updateCustomFields();

    public KEntityCustom updateCustomFields(String controller, String objectIdField) {
        if (this.customFieldGroups == null) {
            return this;
        }


        ArrayList<String> parameters = new ArrayList<String>();
        HashMap<String, HashMap<String, String>> files = new HashMap<String, HashMap<String, String>>();
        //prepare URL controller and parameters
        //parameters.add(objectIdField);
        parameters.add(Integer.toString(this.getId()));
        //collect all field values into request data
        //foreach custom field groups- call build data and merge
        HashMap<String, String> data = new HashMap<String, String>();
        for (CustomFieldGroup customFieldGroup : this.getCustomFieldGroups()) {
            data.putAll(customFieldGroup.buildHashMap());
            //get files from data
            files.putAll(customFieldGroup.buildFilesHashMap());
        }


        //send request
        KEntityCustom.getRESTClient().post(controller, parameters, data, files);

        //reload custom fields from server
        return this;

    }

}
