package com.kayako.api.customfield;

import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;

/**
 * The type Custom field factory.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class CustomFieldFactory {

    /**
     * Create custom field.
     *
     * @param customFieldGroup the custom field group
     * @param rawArrayElement  the raw array element
     * @return the custom field
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public static CustomField createCustomField(CustomFieldGroup customFieldGroup, RawArrayElement rawArrayElement) throws KayakoException {
        if (rawArrayElement == null || !rawArrayElement.getElementName().equals(CustomField.getObjectXmlName())) {
            throw new KayakoException("Invalid XML Element Supplied");
        }
        int type = Integer.parseInt(rawArrayElement.getAttribute("type"));

        switch (type) {
            case CustomFieldDefinition.TYPE_FILE:
                return new CustomFieldFile(customFieldGroup).populate(rawArrayElement);
            case CustomFieldDefinition.TYPE_LINKED_SELECT:
                return new CustomFieldLinkedSelect(customFieldGroup).populate(rawArrayElement);
            case CustomFieldDefinition.TYPE_CHECKBOX:
            case CustomFieldDefinition.TYPE_MULTI_SELECT:
                return new CustomFieldMultiSelect(customFieldGroup).populate(rawArrayElement);
            case CustomFieldDefinition.TYPE_RADIO:
            case CustomFieldDefinition.TYPE_SELECT:
                return new CustomFieldSelect(customFieldGroup).populate(rawArrayElement);
            case CustomFieldDefinition.TYPE_DATE:
                return new CustomFieldDate(customFieldGroup).populate(rawArrayElement);
            case CustomFieldDefinition.TYPE_CUSTOM:
            case CustomFieldDefinition.TYPE_PASSWORD:
            case CustomFieldDefinition.TYPE_TEXT:
            case CustomFieldDefinition.TYPE_TEXTAREA:
                return new CustomField(customFieldGroup).populate(rawArrayElement);

        }
        return null;
    }

}
