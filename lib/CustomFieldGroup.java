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
public abstract class CustomFieldGroup extends KEntity {

    static final int TYPE_TICKET = 0;
    static final int TYPE_USER = 1;
    static final int TYPE_USER_ORGANIZATION = 2;
    static final int TYPE_USER_LIVECHAT = 3;
    static final int TYPE_USER_TIME_TRACK = 4;
    static protected String controller;
    static protected String objectXmlName = "group";
    protected Boolean readOnly = true;

    /**
     * Custom field group identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Custom field group title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * List of custom fields in this group.
     *
     * @var CustomField[]
     */
    protected ArrayList<CustomField> fields;

    /**
     * Type of custom field group.
     *
     * @var int
     * @see CustomFieldGroup::TYPE constants
     */
    protected int type;

    public ArrayList<CustomField> getFields() {
        return fields;
    }

    public CustomFieldGroup setFields(ArrayList<CustomField> fields) {
        this.fields = fields;
        return this;
    }

    public int getId() {
        return id;
    }

    public CustomFieldGroup setId(int id) {
        this.id = id;
        return this;
    }

    public static String getObjectXmlName() {
        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        CustomFieldGroup.objectXmlName = objectXmlName;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public CustomFieldGroup setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getController() {
        return controller;
    }

    public static void setController(String controller) {
        CustomFieldGroup.controller = controller;
    }

    public String getTitle() {
        return title;
    }

    public CustomFieldGroup setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getType() {
        return type;
    }

    public CustomFieldGroup setType(int type) {
        this.type = type;
        return this;
    }

    public static CustomFieldGroup get(int id) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public CustomFieldGroup refresh(String controller) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public CustomFieldGroup populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        //attribute =  title, id
        this.setTitle(rawArrayElement.getAttribute("title")).setId(Helper.parseInt(rawArrayElement.getAttribute("id")));
        return this;

    }

    public HashMap<String, String> buildHashMap() {
        return this.buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomFieldGroup) {
        HashMap<String, String> customFieldGroupHashMap = new HashMap<String, String>();
        for (CustomField customField : this.getFields()) {
            customFieldGroupHashMap.putAll(customField.buildHashMap());
        }

        return customFieldGroupHashMap;
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap() {
        return this.buildFilesHashMap(false);
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap(Boolean newCustomFieldFile) {
        HashMap<String, HashMap<String, String>> filesHashMap = new HashMap<String, HashMap<String, String>>();
        for (CustomField customField : this.getFields()) {
            filesHashMap.putAll(customField.buildFilesHashMap());
        }
        return filesHashMap;
    }

}
