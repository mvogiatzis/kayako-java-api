package com.kayako.api;

/**
 * The Flag Enumeration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public enum FlagEnum {
    NONE, PURPLE, ORANGE, GREEN, YELLOW, RED, BLUE;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case NONE:
                return "0";
            case PURPLE:
                return "1";
            case ORANGE:
                return "2";
            case GREEN:
                return "3";
            case YELLOW:
                return "4";
            case RED:
                return "5";
            case BLUE:
                return "6";
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
    public static FlagEnum getEnum(String access) {
        if (access.equalsIgnoreCase("0")) {
            return NONE;
        } else if (access.equalsIgnoreCase("1")) {
            return PURPLE;
        } else if (access.equalsIgnoreCase("2")) {
            return ORANGE;
        } else if (access.equalsIgnoreCase("3")) {
            return GREEN;
        } else if (access.equalsIgnoreCase("4")) {
            return YELLOW;
        } else if (access.equalsIgnoreCase("5")) {
            return RED;
        } else if (access.equalsIgnoreCase("6")) {
            return BLUE;
        }
        return null;
    }
}
