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
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }
        //attribute =  title, id  , type, name
        this.setId(Helper.parseInt(rawArrayElement.getAttribute("customfieldid")));
        this.setGroupId(Helper.parseInt(rawArrayElement.getAttribute("customfieldgroupid")));
        this.setType(Helper.parseInt(rawArrayElement.getAttribute("fieldtype")));
        this.setName(rawArrayElement.getAttribute("fieldname"));
        this.setTitle(rawArrayElement.getAttribute("title"));
        this.setDefaultValue(rawArrayElement.getAttribute("defaultvalue"));
        this.setRequired(Helper.parseInt(rawArrayElement.getAttribute("isrequired")) == 1);
        this.setUserEditable(Helper.parseInt(rawArrayElement.getAttribute("usereditable")) == 1);
        this.setStaffEditable(Helper.parseInt(rawArrayElement.getAttribute("staffeditable")) == 1);
        this.setRegexpValidate(rawArrayElement.getAttribute("regexpvalidate"));
        this.setDisplayOrder(Helper.parseInt(rawArrayElement.getAttribute("displayorder")));
        this.setEncrypted(Helper.parseInt(rawArrayElement.getAttribute("encryptindb")) == 1);
        this.setDescription(rawArrayElement.getAttribute("description"));
        return this;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public CustomFieldDefinition setId(int id) {
        this.id = id;
        return this;
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
        /* Date formatting if required
        if(this.getType() == TYPE_DATE){
            //
        }*/
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

    public CustomFieldDefinition setName(String name) {
        this.name = name;
        return this;
    }

    public static String getObjectXmlName() {
        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        CustomFieldDefinition.objectXmlName = objectXmlName;
    }

    public ArrayList<CustomFieldOption> getOptions() throws KayakoException {
        return this.getOptions(false);
    }

    public ArrayList<CustomFieldOption> getOptions(Boolean refresh) throws KayakoException {
        if (this.options.size() == 0 || refresh) {
            switch (this.getType()) {
                case TYPE_CHECKBOX:
                case TYPE_LINKED_SELECT:
                case TYPE_MULTI_SELECT:
                case TYPE_RADIO:
                case TYPE_SELECT:
                    ArrayList<RawArrayElement> optionsRaw = CustomFieldOption.getAll().getComponents();
                    for (RawArrayElement optionRaw : optionsRaw) {
                        this.addOption(new CustomFieldOption().populate(optionRaw));
                    }
                    break;
                default:
                    this.setOptions(new ArrayList<CustomFieldOption>());
            }
        }
        return this.options;
    }

    public ArrayList<CustomFieldOption> getDefaultOptions(Boolean refresh) throws KayakoException {
        if (this.options.size() == 0 || refresh) {
            switch (this.getType()) {
                case TYPE_CHECKBOX:
                case TYPE_LINKED_SELECT:
                case TYPE_MULTI_SELECT:
                case TYPE_RADIO:
                case TYPE_SELECT:
                    ArrayList<RawArrayElement> optionsRaw = CustomFieldOption.getAll().filterByComponentAttribute("isselected", "0").getComponents();
                    for (RawArrayElement optionRaw : optionsRaw) {
                        this.addOption(new CustomFieldOption().populate(optionRaw));
                    }
                    break;
                default:
                    this.setOptions(new ArrayList<CustomFieldOption>());
            }
        }
        return this.options;
    }

    public CustomFieldDefinition setOptions(ArrayList<CustomFieldOption> options) {
        this.options = options;
        return this;
    }

    public CustomFieldDefinition addOption(CustomFieldOption customFieldOption) {
        this.options.add(customFieldOption);
        return this;
    }

    public CustomFieldOption getOptionById(int id) throws KayakoException {
        for (CustomFieldOption customFieldOption : this.getOptions()) {
            if (customFieldOption.getId() == id) {
                return customFieldOption;
            }
        }
        return null;
    }

    public CustomFieldOption getOptionByValue(String value) throws KayakoException {
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

    public CustomFieldDefinition setRegexpValidate(String regexpValidate) {
        this.regexpValidate = regexpValidate;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CustomFieldDefinition setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    static public RawArrayElement getAll(ArrayList<String> parameters) {
        return KEntity.getAll(controller, parameters);
    }

    static public void clearCache() {
        setDefinitions(new ArrayList<CustomFieldDefinition>());
    }

    public static CustomFieldDefinition get(int id) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    public CustomFieldDefinition refresh() throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }
}
