package com.kayako.api.enums;

/**
 * The CustomFieldGroupType Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum CustomFieldGroupTypeEnum {
    TICKET, USER, USER_ORGANIZATION, USER_LIVE_CHAT, USER_TIME_TRACK;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case TICKET:
                return "0";
            case USER:
                return "1";
            case USER_ORGANIZATION:
                return "2";
            case USER_LIVE_CHAT:
                return "3";
            case USER_TIME_TRACK:
                return "4";
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
    public static CustomFieldGroupTypeEnum getEnum(String access) {
        if (access.equalsIgnoreCase("0")) {
            return TICKET;
        } else if (access.equalsIgnoreCase("1")) {
            return USER;
        } else if (access.equalsIgnoreCase("2")) {
            return USER_ORGANIZATION;
        } else if (access.equalsIgnoreCase("3")) {
            return USER_LIVE_CHAT;
        } else if (access.equalsIgnoreCase("4")) {
            return USER_TIME_TRACK;
        }
        return null;
    }
}
