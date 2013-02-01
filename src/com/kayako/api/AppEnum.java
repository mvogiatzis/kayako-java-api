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
    TICKETS, LIVE_CHAT;

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

    public static AppEnum getEnum(String access) {
        if (access.equalsIgnoreCase("tickets")) {
            return TICKETS;
        } else {
            return LIVE_CHAT;
        }
    }
}
