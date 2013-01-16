package lib;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * ###############################################
 * Kayako App
 * _______________________________________________
 *
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 * ###############################################
 */
public class CustomFieldDate extends CustomField {
    /**
     * Timestamp representation of date.
     *
     * @var Timestamp
     */
    private Timestamp timestamp;
    static String objectXmlName = "field";

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
        return getDate(new ConfigurationFactory().getConfiguration().getDateFormat());
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
     * @throws KayakoException the kayako exception
     */
    public CustomFieldDate setValue(String value) throws KayakoException {
        try {
            return this.setDate(value);
        } catch (ParseException e) {
            throw new KayakoException();
        }
    }

    @Override
    public CustomFieldDate populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        //content = timestamp
        this.setTimestamp(new Timestamp(Long.parseLong(rawArrayElement.getContent())));
        return this;

    }
}
