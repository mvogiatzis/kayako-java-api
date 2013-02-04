package com.kayako.api;

/**
 * The mode Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum CreationModeEnum {
    /**
     * The SUPPORT CENTRE.
     */SUPPORT_CENTRE, /**
     * The STAFF _ cP.
     */STAFF_CP, /**
     * The EMAIL.
     */EMAIL, /**
     * The API.
     */API, /**
     * The SITE BADGE.
     */SITE_BADGE;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case SUPPORT_CENTRE:
                return "1";
            case STAFF_CP:
                return "2";
            case EMAIL:
                return "3";
            case API:
                return "4";
            case SITE_BADGE:
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
    public static CreationModeEnum getEnum(String access) {
        if (access.equalsIgnoreCase("1")) {
            return SUPPORT_CENTRE;
        } else if (access.equalsIgnoreCase("2")) {
            return STAFF_CP;
        } else if (access.equalsIgnoreCase("3")) {
            return EMAIL;
        } else if (access.equalsIgnoreCase("4")) {
            return API;
        } else if (access.equalsIgnoreCase("5")) {
            return SITE_BADGE;
        }
        return null;
    }
}
