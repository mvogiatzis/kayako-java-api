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
public class CustomFieldSelect extends CustomField {

    /**
     * Selected option.
     *
     * @var CustomFieldOption
     */
    protected CustomFieldOption selectedOption;

    public CustomFieldSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    public CustomFieldOption getSelectedOption() {
        return selectedOption;
    }

    public CustomFieldSelect setSelectedOption(CustomFieldOption selectedOption) throws KayakoException {
        this.selectedOption = selectedOption;
        if (this.selectedOption != null) {
            this.setRawValue(this.selectedOption.getValue());
        } else {
            this.setRawValue(null);
        }
        return this;
    }

    /**
     * Sets the option for this field.
     *
     * @param value
     * @return CustomFieldSelect
     */
    public CustomFieldSelect setValue(String value) throws KayakoException {
        int id = Helper.parseInt(value);
        if (id == 0) {
            this.setSelectedOption(this.getOption(value));
        } else {
            this.setSelectedOption(this.getOption(id));
        }
        return this;
    }

    @Override
    public CustomFieldSelect populate(RawArrayElement rawArrayElement) throws KayakoException {
        super.populate(rawArrayElement);
        int id = Helper.parseInt(rawArrayElement.getContent());
        if (id == 0) {
            this.setSelectedOption(this.getOption(rawArrayElement.getContent()));
        } else {
            this.setSelectedOption(this.getOption(id));
        }

        return this;
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomFieldSelect) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (this.getSelectedOption() != null) {
            hashMap.put(this.getName(), Integer.toString(this.getSelectedOption().getId()));
        }
        return hashMap;
    }
}
