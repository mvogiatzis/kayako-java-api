package lib;

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
public class CustomFieldOption extends KEntity {

    static protected String controller = "/Base/CustomField/ListOptions";
    static protected String objectXmlName = "option";
    protected Boolean readOnly = true;

    /**
     * Field option identifier.
     *
     * @apiField name=customfieldoptionid
     * @var int
     */
    protected int id;

    /**
     * Custom field identifier.
     *
     * @apiField name=customfieldid
     * @var int
     */
    protected int fieldId;

    /**
     * Field option value.
     *
     * @apiField name=optionvalue
     * @var string
     */
    protected String value;

    /**
     * Display order.
     *
     * @apiField
     * @var int
     */
    protected int displayOrder;

    /**
     * Is this option selected by default.
     *
     * @apiField
     * @var bool
     */
    protected Boolean isSelected;

    /**
     * Parent field option identifier (for linked selects).
     *
     * @apiField name=parentcustomfieldoptionid
     * @var int
     */
    protected int parentOptionId;

    public static String getController() {
        return controller;
    }

    public static void setController(String controller) {
        CustomFieldOption.controller = controller;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public CustomFieldOption setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public int getFieldId() {
        return fieldId;
    }

    public CustomFieldOption setFieldId(int fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    public int getId() {
        return id;
    }

    public CustomFieldOption setId(int id) {
        this.id = id;
        return this;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public CustomFieldOption setSelected(Boolean selected) {
        isSelected = selected;
        return this;
    }

    public static String getObjectXmlName() {
        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        CustomFieldOption.objectXmlName = objectXmlName;
    }

    public int getParentOptionId() {
        return parentOptionId;
    }

    public CustomFieldOption setParentOptionId(int parentOptionId) {
        this.parentOptionId = parentOptionId;
        return this;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public CustomFieldOption setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public String getValue() {
        return value;
    }

    public CustomFieldOption setValue(String value) {
        this.value = value;
        return this;
    }

    public static RawArrayElement getAll() throws KayakoException {
        return KEntity.getAll(controller);
    }

    public static CustomFieldOption get(int id) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    public CustomFieldOption refresh() throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    @Override
    public CustomFieldOption populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }
        //content = timestamp
        this.setId(Helper.parseInt(rawArrayElement.getAttribute("customfieldoptionid")));
        this.setFieldId(Helper.parseInt(rawArrayElement.getAttribute("customfieldid")));
        this.setValue(rawArrayElement.getAttribute("optionvalue"));
        this.setDisplayOrder(Helper.parseInt(rawArrayElement.getAttribute("displayorder")));
        this.setSelected(Helper.parseInt(rawArrayElement.getAttribute("isselected")) > 0);
        this.setParentOptionId(Helper.parseInt(rawArrayElement.getAttribute("parentcustomfieldoptionid")));
        return this;
    }

}
