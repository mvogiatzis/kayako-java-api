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
public enum TicketNoteTypeEnum {
    TICKET, USER, USER_ORGANIZATION;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case TICKET:
                return "ticket";
            case USER:
                return "user";
            case USER_ORGANIZATION:
                return "userorganization";
            default:
                return "";
        }

    }

    /**
     * Gets enum.
     *
     * @param type the TicketNote
     * @return the enum
     */
    public static TicketNoteTypeEnum getEnum(String type) {
        if (type.equalsIgnoreCase("ticket")) {
            return TICKET;
        } else if (type.equalsIgnoreCase("user")) {
            return USER;
        } else if (type.equalsIgnoreCase("userorganization")) {
            return USER_ORGANIZATION;
        }
        return null;
    }
}
