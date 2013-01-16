package lib;

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
public class CustomField extends KEntity {

    static protected String objectXmlName = "field";
    /**
     * Field identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Field type.
     *
     * @apiField
     * @var int
     */
    protected int type;

    /**
     * Field name.
     *
     * @apiField
     * @var string
     */
    protected String name;

    /**
     * Field title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Field value.
     *
     * @apiField name=value getter=getRawValue setter=setValue
     * @var string
     */
    protected String rawValue;

    /**
     * Custom field group this field belongs to.
     *
     * @var CustomFieldGroupBase
     */
    protected CustomFieldGroup customFieldGroup;

    /**
     * Cache for field definition.
     *
     * @var CustomFieldDefinition
     */
    protected CustomFieldDefinition definition = null;

    public CustomField(CustomFieldGroup customFieldGroup) {
        this.customFieldGroup = customFieldGroup;
    }

    public int getId() {
        return id;
    }

    public CustomField setId(int id) {
        this.id = id;
        return this;
    }

    public static CustomField get(int id) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public KEntity create(String controller) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public Boolean delete(String controller) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public KEntity refresh(String controller) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public KEntity update(String controller) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public CustomFieldGroup getCustomFieldGroup() {
        return customFieldGroup;
    }

    public CustomField setCustomFieldGroup(CustomFieldGroup customFieldGroup) {
        this.customFieldGroup = customFieldGroup;
        return this;
    }

    public CustomFieldDefinition getDefinition() throws KayakoException {
        return this.getDefinition(false);
    }

    public CustomFieldDefinition getDefinition(Boolean refresh) throws KayakoException {
        if (this.definition != null && !refresh) {
            return this.definition;
        }
        return new CustomFieldDefinition(CustomFieldDefinition.getAll(CustomFieldDefinition.getController()).filterByComponentAttribute("fieldname", this.getName()).getComponents().get(0));
    }

    public CustomField setDefinition(CustomFieldDefinition definition) {
        this.definition = definition;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomField setName(String name) {
        this.name = name;
        return this;
    }

    public String getRawValue() {
        return rawValue;
    }

    public CustomField setRawValue(String rawValue) {
        this.rawValue = rawValue;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CustomField setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getType() {
        return type;
    }

    public CustomField setType(int type) {
        this.type = type;
        return this;
    }

    public CustomFieldOption getOption(String value) throws KayakoException {
        int id = Helper.parseInt(value);
        if (id > 0) {
            return this.getOption(id);
        }
        return this.getDefinition().getOptionByValue(value);
    }

    public CustomFieldOption getOption(int id) throws KayakoException {
        return this.getDefinition().getOptionById(id);
    }

    @Override
    public CustomField populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        //attribute =  title, id  , type, name
        this.setTitle(rawArrayElement.getAttribute("title")).setId(Helper.parseInt(rawArrayElement.getAttribute("id")));
        this.setName(rawArrayElement.getAttribute("name")).setType(Helper.parseInt(rawArrayElement.getAttribute("type")));
        this.setRawValue(rawArrayElement.getContent());
        return this;

    }

    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomField) {
        HashMap<String, String> customFieldHashMap = new HashMap<String, String>();
        customFieldHashMap.put(this.getName(), this.getRawValue());
        return customFieldHashMap;
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap() {
        return buildFilesHashMap(false);
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap(Boolean newCustomFieldFile) {
        HashMap<String, HashMap<String, String>> fileHashMap = new HashMap<String, HashMap<String, String>>();
        //This function returns empty hashMap from here
        return fileHashMap;
    }
}
