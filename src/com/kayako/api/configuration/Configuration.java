package com.kayako.api.configuration;

import com.kayako.api.rest.RESTClient;
import com.kayako.api.rest.RESTInterface;

/**
 * The type Configuration.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */

public class Configuration {
    private String baseUrl;
    private String apiKey;
    private String secretKey;
    private Boolean debug;
    private Boolean standardUrlType = true;
    private static Configuration configuration = null;
    //http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html
    private String dateFormat = "yyyy-MM-dd";
    private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * REST client.
     *
     * @var RESTInterface
     */
    private RESTInterface restClient = null;

    private Configuration(String baseUrl, String apiKey, String secretKey, Boolean testMode) {
        //this("http://swift.kayako.com/api/index.php?", "80866fb4-b6e2-6548-a51sfda37894", "ZWJiNTkzYzMtNmEwNS1dfgfdgdfghdhYzhmMWRkNDVkYmY2ZmQwYjU4MjgtYzY0ghfGQ0LWJkZjktNWE3NmViZDRhMmFk");
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.debug = testMode;
    }

    public static Configuration init(String baseUrl, String apiKey, String secretKey) {
        return init(baseUrl, apiKey, secretKey, false);
    }

    public static Configuration init(String baseUrl, String apiKey, String secretKey, Boolean testMode) {
        if (configuration == null) {
            configuration = new Configuration(baseUrl, apiKey, secretKey, testMode);
        } else {
            configuration.setApiKey(apiKey);
            configuration.setBaseUrl(baseUrl);
            configuration.setSecretKey(secretKey);
            configuration.setDebug(testMode);
        }
        return configuration;
    }

    /**
     * Gets configuration.
     *
     * @return the configuration
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Sets configuration.
     *
     * @param configuration the configuration
     */
    public static void setConfiguration(Configuration configuration) {
        Configuration.configuration = configuration;
    }

    /**
     * Gets base url.
     *
     * @return the base url
     */
    public String getBaseUrl() {
        return this.baseUrl;
    }

    /**
     * Sets base url.
     *
     * @param baseUrl the base url
     * @return the base url
     */
    public Configuration setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * Gets secret key.
     *
     * @return the secret key
     */
    public String getSecretKey() {
        return this.secretKey;
    }

    /**
     * Sets secret key.
     *
     * @param secretKey the secret key
     * @return the secret key
     */
    public Configuration setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    /**
     * Gets api key.
     *
     * @return the api key
     */
    public String getApiKey() {
        return this.apiKey;
    }

    /**
     * Sets api key.
     *
     * @param apiKey the api key
     * @return the api key
     */
    public Configuration setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    /**
     * Sets debug.
     *
     * @param debug the debug
     * @return the debug
     */
    public Configuration setDebug(Boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * Is debug.
     *
     * @return the boolean
     */
    public Boolean isDebug() {
        return this.debug;
    }

    /**
     * Is standard url type.
     *
     * @return the boolean
     */
    public Boolean isStandardUrlType() {
        return standardUrlType;
    }

    /**
     * Sets standard url type.
     *
     * @param standardUrlType the standard url type
     */
    public void setStandardUrlType(Boolean standardUrlType) {
        this.standardUrlType = standardUrlType;
    }

    /**
     * Gets date format.
     *
     * @return the date format
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * Sets date format.
     *
     * @param dateFormat the date format
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Gets date time format.
     *
     * @return the date time format
     */
    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    /**
     * Sets date time format.
     *
     * @param dateTimeFormat the date time format
     * @return the date time format
     */
    public Configuration setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
        return this;
    }

    /**
     * Gets rest client.
     *
     * @return the rest client
     */
    public RESTInterface getRestClient() {
        if (this.restClient == null) {
            this.restClient = new RESTClient().setConfig(this);
        }
        return this.restClient;
    }

    /**
     * Sets rest client.
     *
     * @param restClient the rest client
     * @return the rest client
     */
    public Configuration setRestClient(RESTInterface restClient) {
        this.restClient = restClient;
        return this;
    }
}
