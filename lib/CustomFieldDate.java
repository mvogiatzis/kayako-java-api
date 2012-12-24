package lib;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ###############################################
 * Kayako App
 * _______________________________________________
 *
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http://www.kayako.com/license
 * @link http://www.kayako.com
 * ###############################################
 */
public class CustomFieldDate extends CustomField {
    /**
     * Timestamp representation of date.
     *
     * @var Timestamp
     */
    private Timestamp timestamp;
    static String objectXmlName = "customfield";

    public CustomFieldDate(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    /**
     * Returns field value as timestamp.
     *
     * @return Timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the date using timestamp.
     *
     * @param timestamp Timestamp.
     * @return CustomFieldDate
     */
    public CustomFieldDate setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getDate() {
        return getDate(new ConfigurationFactory().getConfiguration().getDateFormat());
    }


    public String getDate(String dateFormat) {
        Date date = new Date(this.getTimestamp().getTime());
        Format formatter = new SimpleDateFormat(new ConfigurationFactory().getConfiguration().getDateFormat());
        String dateStr = formatter.format(date);
        return dateStr;
    }

    public CustomFieldDate setDate(String dateStr) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(new ConfigurationFactory().getConfiguration().getDateFormat());
        Date date = dateFormat.parse(dateStr);
        long time = date.getTime();
        this.setTimestamp(new Timestamp(time));
        return this;
    }


    public String getValue() {
        return this.getDate();
    }

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
