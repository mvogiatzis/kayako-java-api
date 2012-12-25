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
public class CustomFieldLinkedSelect extends CustomField{



    /**
     * Separator of main and linked select values.
     * @var string
     */
    public static final String PARENT_CHILD_SEPARATOR = "&gt;";



    public CustomFieldLinkedSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    @Override
    public CustomFieldLinkedSelect populate(RawArrayElement rawArrayElement) throws KayakoException {
/*        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        //content = timestamp
        this.setId(Helper.parseInt(rawArrayElement.getAttribute("customfieldoptionid")));
        this.setFieldId(Helper.parseInt(rawArrayElement.getAttribute("customfieldid")));

        this.setValue(rawArrayElement.getAttribute("optionvalue"));
        this.setDisplayOrder(Helper.parseInt(rawArrayElement.getAttribute("displayorder")));
        this.setSelected(Helper.parseInt(rawArrayElement.getAttribute("isselected")) == 0 ? false : true);
        this.setParentOptionId(Helper.parseInt(rawArrayElement.getAttribute("parentcustomfieldoptionid")));*/
        return this;
    }
}
