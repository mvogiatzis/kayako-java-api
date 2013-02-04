package com.kayako.api.enums;

/**
 * The Creator Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum CreatorEnum {

    AUTO, STAFF, USER, CLIENT;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case AUTO:
                return "0";
            case STAFF:
                return "1";
            case USER:
                return "2";
            case CLIENT:
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
    public static CreatorEnum getEnum(String access) {
        if (access.equalsIgnoreCase("0")) {
            return AUTO;
        } else if (access.equalsIgnoreCase("1")) {
            return STAFF;
        } else if (access.equalsIgnoreCase("2")) {
            return USER;
        }
        return null;
    }
}
