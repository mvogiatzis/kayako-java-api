package com.kayako.api.customfield;

import com.kayako.api.enums.CustomFieldDefinitionTypeEnum;
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
     * @param element          the raw array element
     * @return the custom field
     * @throws com.kayako.api.exception.KayakoException
     *          the kayako exception
     */
    public static CustomField createCustomField(CustomFieldGroup customFieldGroup, RawArrayElement element) throws KayakoException {
        if (element == null || !element.getElementName().equals(CustomField.getObjectXmlName())) {
            throw new KayakoException("Invalid XML Element Supplied");
        }
        CustomFieldDefinitionTypeEnum type = CustomFieldDefinitionTypeEnum.getEnum(element.getAttribute("type"));

        switch (type) {
            case FILE:
                return new CustomFieldFile(customFieldGroup).populate(element);
            case LINKED_SELECT:
                return new CustomFieldLinkedSelect(customFieldGroup).populate(element);
            case CHECKBOX:
            case MULTI_SELECT:
                return new CustomFieldMultiSelect(customFieldGroup).populate(element);
            case RADIO:
            case SELECT:
                return new CustomFieldSelect(customFieldGroup).populate(element);
            case DATE:
                return new CustomFieldDate(customFieldGroup).populate(element);
            case CUSTOM:
            case PASSWORD:
            case TEXT:
            case TEXTAREA:
                return new CustomField(customFieldGroup).populate(element);

        }
        return null;
    }

}
