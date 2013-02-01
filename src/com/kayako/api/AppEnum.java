package com.kayako.api;

/**
 * The App Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum AppEnum {
    /**
     * The TICKETS.
     */TICKETS, /**
     * The LIVE _ cHAT.
     */LIVE_CHAT;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case TICKETS:
                return "tickets";
            case LIVE_CHAT:
                return "livechat";
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
    public static AppEnum getEnum(String access) {
        if (access.equalsIgnoreCase("tickets")) {
            return TICKETS;
        } else {
            return LIVE_CHAT;
        }
    }
}
