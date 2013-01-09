package lib;

import java.lang.reflect.InvocationTargetException;
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
    protected HashMap<String, CustomField> customFieldHashMap = new HashMap<String, CustomField>();

    /**
     * Object custom field groups.
     *
     * @var
     */
    protected ArrayList<CustomFieldGroup> customFieldGroups = new ArrayList<CustomFieldGroup>();

    public ArrayList<CustomFieldGroup> getCustomFieldGroups() throws KayakoException {
        return getCustomFieldGroups(false);
    }

    public ArrayList<CustomFieldGroup> getCustomFieldGroups(Boolean refresh) throws KayakoException {
        this.loadCustomFieldGroups(refresh);
        return this.customFieldGroups;
    }

    public void setCustomFieldGroups(ArrayList<CustomFieldGroup> customFieldGroups) {
        this.customFieldGroups = customFieldGroups;
    }

    public HashMap<String, CustomField> getCustomFieldHashMap() throws KayakoException {
        return this.getCustomFieldHashMap(false);
    }

    public HashMap<String, CustomField> getCustomFieldHashMap(Boolean refresh) throws KayakoException {
        this.loadCustomFieldGroups(refresh);
        return customFieldHashMap;
    }

    //This function will return ArrayList of custom fields
    public ArrayList<CustomField> getCustomFields(Boolean refresh) throws KayakoException {
        return new ArrayList<CustomField>(this.getCustomFieldHashMap(refresh).values());
    }

    public void setCustomFieldHashMap(HashMap<String, CustomField> customFieldHashMap) {
        this.customFieldHashMap = customFieldHashMap;
    }

    public static String getObjectIdField() {
        return objectIdField;
    }

    public static void setObjectIdField(String objectIdField) {
        KEntityCustom.objectIdField = objectIdField;
    }

    public KEntityCustom update(String controller) throws KayakoException {
        super.update(controller);
        if (!this.isNew()) {
            this.updateCustomFields(controller);
        }
        return this;
    }

    /**
     * Returns custom field based on its name.
     *
     * @param name Field name.
     * @return CustomField
     */
    public CustomField getCustomField(String name) throws KayakoException {
        this.loadCustomFieldGroups();
        return this.getCustomFieldHashMap().get(name);
    }

    protected ArrayList<CustomFieldGroup> loadCustomFieldGroups() throws KayakoException {
        return this.loadCustomFieldGroups(false);
    }

    protected abstract ArrayList<CustomFieldGroup> loadCustomFieldGroups(Boolean refresh) throws KayakoException;

    /**
     * Prepares local array for custom field fast lookup based on its name.
     * this function should populate this.customFields
     */
    protected abstract ArrayList<CustomField> loadCustomField(Boolean refresh);

    /**
     * Prepares local array for custom field fast lookup based on its name.
     */
    protected void cacheFields() throws KayakoException {
        for (CustomFieldGroup customFieldGroup : this.getCustomFieldGroups()) {
            for (CustomField customField : customFieldGroup.getFields()) {
                this.customFieldHashMap.put(customField.getName(), customField);
            }
        }
    }

    //this method needs to be implemented in the derived class to basically call the overloaded method with correct arguments
    public abstract KEntityCustom updateCustomFields() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, KayakoException;

    /**
     * Updates all custom fields values on Kayako server.
     *
     * @param customGroupController - This is the customGroupController of CustomGroupClass
     * @return KEntityCustom
     */
    public KEntityCustom updateCustomFields(String customGroupController) throws KayakoException {
        if (this.customFieldGroups == null) {
            return this;
        }
        ArrayList<String> parameters = this.getIdArray();
        HashMap<String, HashMap<String, String>> files = new HashMap<String, HashMap<String, String>>();
        //prepare URL customGroupController and parameters
        //collect all field values into request data
        //foreach custom field groups- call build data and merge
        HashMap<String, String> data = new HashMap<String, String>();
        for (CustomFieldGroup customFieldGroup : this.getCustomFieldGroups()) {
            data.putAll(customFieldGroup.buildHashMap());
            //get files from data
            files.putAll(customFieldGroup.buildFilesHashMap());
        }

        //send request
        KEntityCustom.getRESTClient().post(customGroupController, parameters, data, files);

        //reload custom fields from server
        return this;

    }

}
