package com.kayako.api.customfield;

import com.kayako.api.configuration.Configuration;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.Helper;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * The type Custom field date.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class CustomFieldDate extends CustomField {
    /**
     * Timestamp representation of date.
     *
     * @var Timestamp
     */
    private Timestamp timestamp;
    protected static String objectXmlName = "field";

    /**
     * Instantiates a new Custom field date.
     *
     * @param customFieldGroup the custom field group
     */
    public CustomFieldDate(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    /**
     * Returns field value as timestamp.
     *
     * @return Timestamp timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the date using timestamp.
     *
     * @param timestamp Timestamp.
     * @return CustomFieldDate timestamp
     */
    public CustomFieldDate setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        this.setRawValue(Helper.getDateString(timestamp.getTime()));
        return this;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return getDate(Configuration.getConfiguration().getDateFormat());
    }

    /**
     * Gets date.
     *
     * @param dateFormat the date format String
     * @return the date String
     */
    public String getDate(String dateFormat) {
        return Helper.getDateString(this.getTimestamp().getTime(), dateFormat);
    }

    /**
     * Sets date.
     *
     * @param dateStr the date str
     * @return the date
     * @throws ParseException the parse exception
     */
    public CustomFieldDate setDate(String dateStr) throws ParseException {
        this.setTimestamp(new Timestamp(Helper.getTimeStampFromDateString(dateStr)));
        return this;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return this.getDate();
    }

    /**
     * Sets value.
     *
     * @param value the value
     * @return the value
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public CustomFieldDate setValue(String value) throws KayakoException {
        try {
            return this.setDate(value);
        } catch (ParseException e) {
            throw new KayakoException();
        }
    }

    @Override
    public CustomFieldDate populate(RawArrayElement element) throws KayakoException {
        if (!element.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        //content = timestamp
        this.setTimestamp(new Timestamp(Helper.parseLong(element.getContent())));
        return this;

    }
}
