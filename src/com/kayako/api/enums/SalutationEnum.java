package com.kayako.api.enums;

/**
 * The Salutation Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum SalutationEnum {
    /**
     * The YELLOW.
     */MR, /**
     * The PURPLE.
     */MS, /**
     * The BLUE.
     */MRS, /**
     * The GREEN.
     */DR;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case MR:
                return "Mr.";
            case MS:
                return "Ms.";
            case MRS:
                return "Mrs.";
            case DR:
                return "Dr.";
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
    public static SalutationEnum getEnum(String access) {
        if (access.equalsIgnoreCase("Mr.")) {
            return MR;
        } else if (access.equalsIgnoreCase("Ms.")) {
            return MS;
        } else if (access.equalsIgnoreCase("Mrs.")) {
            return MRS;
        } else if (access.equalsIgnoreCase("Dr.")) {
            return DR;
        }
        return null;
    }
}
