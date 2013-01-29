package com.kayako.api;

import java.util.HashMap;

/**
 * The type Custom field linked select.
 *
 * @author Rajat Garg
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class CustomFieldLinkedSelect extends CustomFieldSelect {

    /**
     * Separator of main and linked select values.
     *
     * @var string
     */
    public static final String PARENT_CHILD_SEPARATOR = " &gt; ";

    /**
     * Instantiates a new Custom field linked select.
     *
     * @param customFieldGroup the custom field group
     */
    public CustomFieldLinkedSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    @Override
    public CustomFieldLinkedSelect populate(RawArrayElement rawArrayElement) throws KayakoException {
        super.populate(rawArrayElement);
        String[] values = rawArrayElement.getContent().split(PARENT_CHILD_SEPARATOR);
        if (values.length > 1) {
            this.setSelectedOption(this.getOption(values[1]));
        }
        return this;
    }

    @Override
    public CustomFieldLinkedSelect setSelectedOption(CustomFieldOption customFieldOption) throws KayakoException {
        if (this.getSelectedOption() != null) {
            this.setRawValue(this.getOption(this.getSelectedOption().getParentOptionId()).getValue() + PARENT_CHILD_SEPARATOR + this.getSelectedOption().getValue());
        } else {
            this.setRawValue(null);
        }

        return this;
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomFieldLinkedSelect) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (this.getSelectedOption().getParentOptionId() > 0) {
            hashMap.put(this.getName() + "[0]", Integer.toString(this.getSelectedOption().getParentOptionId()));
            hashMap.put(this.getName() + "[0][" + Integer.toString(this.getSelectedOption().getParentOptionId()) + "]", Integer.toString(this.getSelectedOption().getId()));
        } else {
            hashMap.put(this.getName() + "[0]", Integer.toString(this.getSelectedOption().getId()));
        }

        return hashMap;
    }
}
