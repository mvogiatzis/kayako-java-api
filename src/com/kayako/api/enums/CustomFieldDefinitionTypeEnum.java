package com.kayako.api.enums;

/**
 * The CustomFieldDefinitionType Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum CustomFieldDefinitionTypeEnum {
    /**
     * The TEXT.
     */TEXT, /**
     * The TEXTAREA.
     */TEXTAREA, /**
     * The PASSWORD.
     */PASSWORD, /**
     * The CHECKBOX.
     */CHECKBOX, /**
     * The RADIO.
     */RADIO, /**
     * The SELECT.
     */SELECT, /**
     * The MULTI SELECT.
     */MULTI_SELECT, /**
     * The CUSTOM.
     */CUSTOM, /**
     * The LINKED SELECT.
     */LINKED_SELECT, /**
     * The DATE.
     */DATE, /**
     * The FILE.
     */FILE;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case TEXT:
                return "1";
            case TEXTAREA:
                return "2";
            case PASSWORD:
                return "3";
            case CHECKBOX:
                return "4";
            case RADIO:
                return "5";
            case SELECT:
                return "6";
            case MULTI_SELECT:
                return "7";
            case CUSTOM:
                return "8";
            case LINKED_SELECT:
                return "9";
            case DATE:
                return "10";
            case FILE:
                return "11";
            default:
                return "";
        }

    }

    /**
     * Gets enum.
     *
     * @param access the access
     * @return the enum
     */
    public static CustomFieldDefinitionTypeEnum getEnum(String access) {
        if (access.equalsIgnoreCase("1")) {
            return TEXT;
        } else if (access.equalsIgnoreCase("2")) {
            return TEXTAREA;
        } else if (access.equalsIgnoreCase("3")) {
            return PASSWORD;
        } else if (access.equalsIgnoreCase("4")) {
            return CHECKBOX;
        } else if (access.equalsIgnoreCase("5")) {
            return RADIO;
        } else if (access.equalsIgnoreCase("6")) {
            return SELECT;
        } else if (access.equalsIgnoreCase("7")) {
            return MULTI_SELECT;
        } else if (access.equalsIgnoreCase("8")) {
            return CUSTOM;
        } else if (access.equalsIgnoreCase("9")) {
            return LINKED_SELECT;
        } else if (access.equalsIgnoreCase("10")) {
            return DATE;
        } else if (access.equalsIgnoreCase("11")) {
            return FILE;
        }
        return null;
    }
}
