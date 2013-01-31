package com.kayako.api.customfield;

import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.Helper;

import java.util.HashMap;

/**
 * The type Custom field select.
 * @author Rajat Garg
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class CustomFieldSelect extends CustomField {

    /**
     * Selected option.
     *
     * @var CustomFieldOption
     */
    protected CustomFieldOption selectedOption;

    /**
     * Instantiates a new Custom field select.
     *
     * @param customFieldGroup the custom field group
     */
    public CustomFieldSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    /**
     * Gets selected option.
     *
     * @return the selected option
     */
    public CustomFieldOption getSelectedOption() {
        return selectedOption;
    }

    /**
     * Sets selected option.
     *
     * @param selectedOption the selected option
     * @return the selected option
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
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
     * @param value the value
     * @return CustomFieldSelect value
     * @throws KayakoException the kayako exception
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
