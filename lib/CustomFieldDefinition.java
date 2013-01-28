package lib;

import java.util.ArrayList;

/**
 * The type Custom field definition.
 *
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class CustomFieldDefinition extends KEntity {
    /**
     * The constant TYPE_TEXT.
     */
    public static final int TYPE_TEXT = 1;

    /**
     * The constant TYPE_TEXTAREA.
     */
    public static final int TYPE_TEXTAREA = 2;

    /**
     * The constant TYPE_PASSWORD.
     */
    public static final int TYPE_PASSWORD = 3;

    /**
     * The constant TYPE_CHECKBOX.
     */
    public static final int TYPE_CHECKBOX = 4;

    /**
     * The constant TYPE_RADIO.
     */
    public static final int TYPE_RADIO = 5;

    /**
     * The constant TYPE_SELECT.
     */
    public static final int TYPE_SELECT = 6;

    /**
     * The constant TYPE_MULTI_SELECT.
     */
    public static final int TYPE_MULTI_SELECT = 7;

    /**
     * The constant TYPE_CUSTOM.
     */
    public static final int TYPE_CUSTOM = 8;

    /**
     * The constant TYPE_LINKED_SELECT.
     */
    public static final int TYPE_LINKED_SELECT = 9;

    /**
     * The constant TYPE_DATE.
     */
    public static final int TYPE_DATE = 10;

    /**
     * The constant TYPE_FILE.
     */
    public static final int TYPE_FILE = 11;

    /**
     * The Controller.
     */
    static protected String controller = "/Base/CustomField";

    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "customfield";

    /**
     * The Read only.
     */
    protected Boolean readOnly = true;

    /**
     * Instantiates a new Custom field definition.
     *
     * @param rawArrayElement the raw array element
     * @throws KayakoException the kayako exception
     */
    public CustomFieldDefinition(RawArrayElement rawArrayElement) throws KayakoException {
        this.populate(rawArrayElement);
    }

    /**
     * Field identifier.
     *
     * @apiField name =customfieldid
     * @var int
     */
    protected int id;

    /**
     * Field group identifier.
     *
     * @apiField name =customfieldgroupid
     * @var int
     */
    protected int groupId;

    /**
     * Field type.
     *
     * @apiField name =fieldtype
     * @var int
     */
    protected int type;

    /**
     * Field name.
     *
     * @apiField name =fieldname
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
     * @apiField name =usereditable
     * @var bool
     */
    protected Boolean isUserEditable;

    /**
     * Field staff editable flag.
     *
     * @apiField name =staffeditable
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
     * @apiField name =encryptindb
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

    static private RawArrayElement rawDefinitions = null;

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

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public CustomFieldDefinition setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets controller.
     *
     * @return the controller
     */
    public static String getController() {
        return controller;
    }

    /**
     * Sets controller.
     *
     * @param controller the controller
     */
    public static void setController(String controller) {
        CustomFieldDefinition.controller = controller;
    }

    /**
     * Gets default value.
     *
     * @return the default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets default value.
     *
     * @param defaultValue the default value
     */
    public void setDefaultValue(String defaultValue) {
        /* Date formatting if required
        if(this.getType() == TYPE_DATE){
            //
        }*/
        this.defaultValue = defaultValue;
    }

    /**
     * Gets definitions.
     *
     * @return the definitions
     */
    public static ArrayList<CustomFieldDefinition> getDefinitions() {
        return definitions;
    }

    /**
     * Sets definitions.
     *
     * @param definitions the definitions
     */
    public static void setDefinitions(ArrayList<CustomFieldDefinition> definitions) {
        CustomFieldDefinition.definitions = definitions;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets display order.
     *
     * @return the display order
     */
    public int getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Sets display order.
     *
     * @param displayOrder the display order
     */
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * Gets group id.
     *
     * @return the group id
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Sets group id.
     *
     * @param groupId the group id
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets encrypted.
     *
     * @return the encrypted
     */
    public Boolean getEncrypted() {
        return isEncrypted;
    }

    /**
     * Sets encrypted.
     *
     * @param encrypted the encrypted
     */
    public void setEncrypted(Boolean encrypted) {
        isEncrypted = encrypted;
    }

    /**
     * Gets required.
     *
     * @return the required
     */
    public Boolean getRequired() {
        return isRequired;
    }

    /**
     * Sets required.
     *
     * @param required the required
     */
    public void setRequired(Boolean required) {
        isRequired = required;
    }

    /**
     * Gets staff editable.
     *
     * @return the staff editable
     */
    public Boolean getStaffEditable() {
        return isStaffEditable;
    }

    /**
     * Sets staff editable.
     *
     * @param staffEditable the staff editable
     */
    public void setStaffEditable(Boolean staffEditable) {
        isStaffEditable = staffEditable;
    }

    /**
     * Gets user editable.
     *
     * @return the user editable
     */
    public Boolean getUserEditable() {
        return isUserEditable;
    }

    /**
     * Sets user editable.
     *
     * @param userEditable the user editable
     */
    public void setUserEditable(Boolean userEditable) {
        isUserEditable = userEditable;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public CustomFieldDefinition setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets object xml name.
     *
     * @return the object xml name
     */
    public static String getObjectXmlName() {
        return objectXmlName;
    }

    /**
     * Sets object xml name.
     *
     * @param objectXmlName the object xml name
     */
    public static void setObjectXmlName(String objectXmlName) {
        CustomFieldDefinition.objectXmlName = objectXmlName;
    }

    /**
     * Gets options.
     *
     * @return the options
     * @throws KayakoException the kayako exception
     */
    public ArrayList<CustomFieldOption> getOptions() throws KayakoException {
        return this.getOptions(false);
    }

    /**
     * Gets options.
     *
     * @param refresh the refresh
     * @return the options
     * @throws KayakoException the kayako exception
     */
    public ArrayList<CustomFieldOption> getOptions(Boolean refresh) throws KayakoException {
        if (this.options.size() == 0 || refresh) {
            switch (this.getType()) {
                case TYPE_CHECKBOX:
                case TYPE_LINKED_SELECT:
                case TYPE_MULTI_SELECT:
                case TYPE_RADIO:
                case TYPE_SELECT:
                    ArrayList<RawArrayElement> optionsRaw = CustomFieldOption.getAll(this.getId()).getComponents();
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

    /**
     * Gets default options.
     *
     * @param refresh the refresh
     * @return the default options
     * @throws KayakoException the kayako exception
     */
    public ArrayList<CustomFieldOption> getDefaultOptions(Boolean refresh) throws KayakoException {
        if (this.options.size() == 0 || refresh) {
            switch (this.getType()) {
                case TYPE_CHECKBOX:
                case TYPE_LINKED_SELECT:
                case TYPE_MULTI_SELECT:
                case TYPE_RADIO:
                case TYPE_SELECT:
                    ArrayList<RawArrayElement> optionsRaw = CustomFieldOption.getAll(this.getId()).filterByComponentAttribute("isselected", "0").getComponents();
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

    /**
     * Sets options.
     *
     * @param options the options
     * @return the options
     */
    public CustomFieldDefinition setOptions(ArrayList<CustomFieldOption> options) {
        this.options = options;
        return this;
    }

    /**
     * Add option.
     *
     * @param customFieldOption the custom field option
     * @return the custom field definition
     */
    public CustomFieldDefinition addOption(CustomFieldOption customFieldOption) {
        this.options.add(customFieldOption);
        return this;
    }

    /**
     * Gets option by id.
     *
     * @param id the id
     * @return the option by id
     * @throws KayakoException the kayako exception
     */
    public CustomFieldOption getOptionById(int id) throws KayakoException {
        for (CustomFieldOption customFieldOption : this.getOptions()) {
            if (customFieldOption.getId() == id) {
                return customFieldOption;
            }
        }
        return null;
    }

    /**
     * Gets option by value.
     *
     * @param value the value
     * @return the option by value
     * @throws KayakoException the kayako exception
     */
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

    /**
     * Gets regexp validate.
     *
     * @return the regexp validate
     */
    public String getRegexpValidate() {
        return regexpValidate;
    }

    /**
     * Sets regexp validate.
     *
     * @param regexpValidate the regexp validate
     * @return the regexp validate
     */
    public CustomFieldDefinition setRegexpValidate(String regexpValidate) {
        this.regexpValidate = regexpValidate;
        return this;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     * @return the title
     */
    public CustomFieldDefinition setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets all.
     *
     * @param parameters the parameters
     * @return the all
     */
    static public RawArrayElement getAll(ArrayList<String> parameters) {
        if (rawDefinitions == null) {
            rawDefinitions = KEntity.getAll(controller, parameters);
        }
        return rawDefinitions;
    }

    /**
     * Gets all definitions.
     *
     * @param parameters the parameters
     * @return the all definitions
     * @throws KayakoException the kayako exception
     */
    static public ArrayList<CustomFieldDefinition> getAllDefinitions(ArrayList<String> parameters) throws KayakoException {
        if (definitions.size() == 0) {
            setDefinitions(refineToArray(getAll(parameters)));
        }
        return definitions;
    }

    private static ArrayList<CustomFieldDefinition> refineToArray(RawArrayElement rawArrayElement) throws KayakoException {
        ArrayList<CustomFieldDefinition> customFieldDefinitions = new ArrayList<CustomFieldDefinition>();
        for (RawArrayElement rawArrayElementCustomFieldDefinition : rawArrayElement.getComponents()) {
            customFieldDefinitions.add(new CustomFieldDefinition(rawArrayElementCustomFieldDefinition));
        }
        return customFieldDefinitions;
    }

    /**
     * Clear cache.
     */
    static public void clearCache() {
        setDefinitions(new ArrayList<CustomFieldDefinition>());
    }

    /**
     * Get custom field definition.
     *
     * @param id the id
     * @return the custom field definition
     * @throws KayakoException the kayako exception
     */
    public static CustomFieldDefinition get(int id) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    /**
     * Refresh custom field definition.
     *
     * @return the custom field definition
     * @throws KayakoException the kayako exception
     */
    public CustomFieldDefinition refresh() throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }
}
