package com.kayako.api;

/**
 * The type Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum TypeAccess {
    PUBLIC, PRIVATE;

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

    public static TypeAccess getEnum(String access) {
        if (access.equalsIgnoreCase("public")) {
            return PUBLIC;
        } else {
            return PRIVATE;
        }
    }
}
