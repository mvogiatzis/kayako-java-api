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
public enum AccessTypeEnum {
    /**
     * The PUBLIC.
     */PUBLIC, /**
     * The PRIVATE.
     */PRIVATE;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case PUBLIC:
                return "public";
            case PRIVATE:
                return "private";
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
    public static AccessTypeEnum getEnum(String access) {
        if (access.equalsIgnoreCase("public")) {
            return PUBLIC;
        } else {
            return PRIVATE;
        }
    }
}
