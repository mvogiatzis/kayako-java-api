package com.kayako.api.enums;

/**
 * The type Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum CreationTypeEnum {
    /**
     * The DEFAULT.
     */DEFAULT, /**
     * The PHONE.
     */PHONE;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case DEFAULT:
                return "1";
            case PHONE:
                return "2";
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
    public static CreationTypeEnum getEnum(String access) {
        if (access.equalsIgnoreCase("2")) {
            return DEFAULT;
        } else {
            return PHONE;
        }
    }
}
