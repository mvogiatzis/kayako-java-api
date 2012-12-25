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

    //this function will populate the data of the ticket note instance when supplied with RawArrayElement derived from the xml
    @Override
    public CustomField populate(RawArrayElement rawArrayElement) throws KayakoException {

        return this;
    }


    public int getId() {
        return id;
    }

    public CustomField setId(int id) {
        this.id = id;
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomField) {
        HashMap<String, String> customFieldHashMap = new HashMap<String, String>();

        return customFieldHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public CustomFieldGroup getCustomFieldGroup() {
        return customFieldGroup;
    }

    public void setCustomFieldGroup(CustomFieldGroup customFieldGroup) {
        this.customFieldGroup = customFieldGroup;
    }

    public CustomFieldDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(CustomFieldDefinition definition) {
        this.definition = definition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CustomFieldOption getOption(String value) {
        return this.getDefinition().getOptionByValue(value);
    }

    public CustomFieldOption getOption(int id) {
        return this.getDefinition().getOptionById(id);
    }

    //TODO a lot of functions like getAll etc, plus creating new attachments from here...
}
