package lib;

import java.util.ArrayList;

/**
 * The type Custom field option.
 *
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class CustomFieldOption extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Base/CustomField/ListOptions";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "option";
    /**
     * The Read only.
     */
    protected Boolean readOnly = true;

    /**
     * Field option identifier.
     *
     * @apiField name =customfieldoptionid
     * @var int
     */
    protected int id;

    /**
     * Custom field identifier.
     *
     * @apiField name =customfieldid
     * @var int
     */
    protected int fieldId;

    /**
     * Field option value.
     *
     * @apiField name =optionvalue
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
     * @apiField name =parentcustomfieldoptionid
     * @var int
     */
    protected int parentOptionId;

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
        CustomFieldOption.controller = controller;
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
     * @return the display order
     */
    public CustomFieldOption setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    /**
     * Gets field id.
     *
     * @return the field id
     */
    public int getFieldId() {
        return fieldId;
    }

    /**
     * Sets field id.
     *
     * @param fieldId the field id
     * @return the field id
     */
    public CustomFieldOption setFieldId(int fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public CustomFieldOption setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets selected.
     *
     * @return the selected
     */
    public Boolean getSelected() {
        return isSelected;
    }

    /**
     * Sets selected.
     *
     * @param selected the selected
     * @return the selected
     */
    public CustomFieldOption setSelected(Boolean selected) {
        isSelected = selected;
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
        CustomFieldOption.objectXmlName = objectXmlName;
    }

    /**
     * Gets parent option id.
     *
     * @return the parent option id
     */
    public int getParentOptionId() {
        return parentOptionId;
    }

    /**
     * Sets parent option id.
     *
     * @param parentOptionId the parent option id
     * @return the parent option id
     */
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

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     * @return the value
     */
    public CustomFieldOption setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Gets all.
     *
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll(int fieldId) throws KayakoException {
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(Integer.toString(fieldId));
        return KEntity.getAll(controller, parameters);
    }

    /**
     * Get custom field option.
     *
     * @param id the id
     * @return the custom field option
     * @throws KayakoException the kayako exception
     */
    public static CustomFieldOption get(int id) throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    /**
     * Refresh custom field option.
     *
     * @return the custom field option
     * @throws KayakoException the kayako exception
     */
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
