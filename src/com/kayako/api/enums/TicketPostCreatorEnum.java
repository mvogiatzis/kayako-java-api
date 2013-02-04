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
public enum TicketPostCreatorEnum {

    /**
     * The AUTO.
     */AUTO, /**
     * The STAFF.
     */STAFF, /**
     * The USER.
     */USER, /**
     * The CLIENT.
     */CLIENT, /**
     * The CC.
     */CC, /**
     * The BCC.
     */BCC, /**
     * The THIRD _ pARTY.
     */THIRD_PARTY;

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
            case CC:
                return "3";
            case BCC:
                return "4";
            case THIRD_PARTY:
                return "5";
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
    public static TicketPostCreatorEnum getEnum(String access) {
        if (access.equalsIgnoreCase("0")) {
            return AUTO;
        } else if (access.equalsIgnoreCase("1")) {
            return STAFF;
        } else if (access.equalsIgnoreCase("2")) {
            return USER;
        } else if (access.equalsIgnoreCase("3")) {
            return CC;
        } else if (access.equalsIgnoreCase("4")) {
            return BCC;
        } else if (access.equalsIgnoreCase("5")) {
            return THIRD_PARTY;
        }
        return null;
    }
}
