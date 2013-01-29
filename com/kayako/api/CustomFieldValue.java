package com.kayako.api;

/**
 * The type Custom field value.
 * @author Rajat Garg
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class CustomFieldValue {

    //This file is not being used - think on this
    String rawValue;
    Boolean isFile = false;
    String fileName = "";

    /**
     * Instantiates a new Custom field value.
     *
     * @param rawValue the raw value
     */
    public CustomFieldValue(String rawValue) {
        this.rawValue = rawValue;
    }

    /**
     * Instantiates a new Custom field value.
     *
     * @param rawValue the raw value
     * @param fileName the file name
     */
    public CustomFieldValue(String rawValue, String fileName) {
        this.isFile = true;
        this.fileName = fileName;
        this.rawValue = rawValue;
    }

    public String toString() {
        if (this.isFile) {
            return fileName + " : " + rawValue;
        }
        return rawValue;
    }
}
