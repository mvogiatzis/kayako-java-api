package lib;

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
public class CustomFieldValue {

    //This file is not being used - think on this
    String rawValue;
    Boolean isFile = false;
    String fileName = "";

    public CustomFieldValue(String rawValue) {
        this.rawValue = rawValue;
    }

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
