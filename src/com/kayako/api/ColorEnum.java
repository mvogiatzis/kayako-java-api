package com.kayako.api;

/**
 * The Color Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum ColorEnum {
    /**
     * The YELLOW.
     */YELLOW, /**
     * The PURPLE.
     */PURPLE, /**
     * The BLUE.
     */BLUE, /**
     * The GREEN.
     */GREEN, /**
     * The RED.
     */RED;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case YELLOW:
                return "1";
            case PURPLE:
                return "2";
            case BLUE:
                return "3";
            case GREEN:
                return "4";
            case RED:
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
    public static ColorEnum getEnum(String access) {
        if (access.equalsIgnoreCase("1")) {
            return YELLOW;
        } else if (access.equalsIgnoreCase("2")) {
            return PURPLE;
        } else if (access.equalsIgnoreCase("3")) {
            return BLUE;
        } else if (access.equalsIgnoreCase("4")) {
            return GREEN;
        } else if (access.equalsIgnoreCase("5")) {
            return RED;
        }
        return null;
    }
}
