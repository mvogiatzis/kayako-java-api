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
public class CustomFieldDefinition extends KEntity {
    static final int TYPE_TEXT = 1;

    static final int TYPE_TEXTAREA = 2;

    static final int TYPE_PASSWORD = 3;

    static final int TYPE_CHECKBOX = 4;

    static final int TYPE_RADIO = 5;

    static final int TYPE_SELECT = 6;

    static final int TYPE_MULTI_SELECT = 7;

    static final int TYPE_CUSTOM = 8;

    static final int TYPE_LINKED_SELECT = 9;

    static final int TYPE_DATE = 10;

    static final int TYPE_FILE = 11;

    static protected String controller = "/Base/CustomField";

    static protected String objectXmlName = "customfield";

    protected Boolean readOnly = true;

    public CustomFieldDefinition(RawArrayElement rawArrayElement) throws KayakoException {
        this.populate(rawArrayElement);
    }

    /**
     * Field identifier.
     *
     * @apiField name=customfieldid
     * @var int
     */
    protected int id;

    /**
     * Field group identifier.
     *
     * @apiField name=customfieldgroupid
     * @var int
     */
    protected int groupId;

    /**
     * Field type.
     *
     * @apiField name=fieldtype
     * @var int
     */
    protected int type;

    /**
     * Field name.
     *
     * @apiField name=fieldname
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
     * Field default value.
     *
     * @apiField
     * @var string
     */
    protected String defaultValue;

    /**
     * Field required flag.
     *
     * @apiField
     * @var bool
     */
    protected Boolean isRequired;

    /**
     * Field user editable flag.
     *
     * @apiField name=usereditable
     * @var bool
     */
    protected Boolean isUserEditable;

    /**
     * Field staff editable flag.
     *
     * @apiField name=staffeditable
     * @var bool
     */
    protected Boolean isStaffEditable;

    /**
     * Field PERL regexp for value validation.
     *
     * @apiField
     * @var string
     */
    protected String regexpValidate;

    /**
     * Field display order.
     *
     * @apiField
     * @var int
     */
    protected int displayOrder;

    /**
     * Field encryption flag.
     *
     * @apiField name=encryptindb
     * @var bool
     */
    protected Boolean isEncrypted;

    /**
     * Field description.
     *
     * @apiField
     * @var string
     */
    protected String description;

    /**
     * Field possible options.
     *
     * @var CustomFieldOption[]
     */
    private ArrayList<CustomFieldOption> options = new ArrayList<CustomFieldOption>();

    /**
     * Cache for all field definitions.
     *
     * @var CustomFieldDefinition[]
     */
    static private ArrayList<CustomFieldDefinition> definitions = new ArrayList<CustomFieldDefinition>();

    @Override
    public KEntity populate(RawArrayElement rawArrayElement) throws KayakoException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getId() {
        return this.id;
    }

    public static String getController() {
        return controller;
    }

    public static void setController(String controller) {
        CustomFieldDefinition.controller = controller;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public static ArrayList<CustomFieldDefinition> getDefinitions() {
        return definitions;
    }

    public static void setDefinitions(ArrayList<CustomFieldDefinition> definitions) {
        CustomFieldDefinition.definitions = definitions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Boolean getEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(Boolean encrypted) {
        isEncrypted = encrypted;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public Boolean getStaffEditable() {
        return isStaffEditable;
    }

    public void setStaffEditable(Boolean staffEditable) {
        isStaffEditable = staffEditable;
    }

    public Boolean getUserEditable() {
        return isUserEditable;
    }

    public void setUserEditable(Boolean userEditable) {
        isUserEditable = userEditable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getObjectXmlName() {
        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        CustomFieldDefinition.objectXmlName = objectXmlName;
    }

    public ArrayList<CustomFieldOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<CustomFieldOption> options) {
        this.options = options;
    }

    public CustomFieldOption getOptionById(int id) {
        for (CustomFieldOption customFieldOption : this.getOptions()) {
            if (customFieldOption.getId() == id) {
                return customFieldOption;
            }
        }
        return null;
    }

    public CustomFieldOption getOptionByValue(String value) {
        for (CustomFieldOption customFieldOption : this.getOptions()) {
            if (customFieldOption.getValue() == value) {
                return customFieldOption;
            }
        }
        return null;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public CustomFieldDefinition setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public String getRegexpValidate() {
        return regexpValidate;
    }

    public void setRegexpValidate(String regexpValidate) {
        this.regexpValidate = regexpValidate;
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
}
