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

public class Configuration {
    private String baseUrl;
    private String apiKey;
    private String secretKey;
    private Boolean debug;
    private Boolean standardUrlType = true;
    private static Configuration configuration = null;
    private String dateFormat = "MM/dd/yyyy";
    private String dateTimeFormat = "Y-m-d H:i:s";

    /**
     * REST client.
     *
     * @var RESTInterface
     */
    private RESTInterface restClient = null;

    public Configuration(String baseUrl, String apiKey, String secretKey, Boolean testMode) {
        //this("http://swift.kayako.com/api/index.php?", "80866fb4-b6e2-1584-a51sfda37894", "ZWJiNTkzYzMtNmEwNS1dfgfdgdfghdhYzhmMWRkNDVkYmY2ZmQwYjU4MjgtYzY0ghfGQ0LWJkZjktNWE3NmViZDRhMmFk");
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.debug = testMode;
    }

    public Configuration(String baseUrl, String apiKey, String secretKey) {
        this(baseUrl, apiKey, secretKey, false);
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        Configuration.configuration = configuration;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public Configuration setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public Configuration setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public Configuration setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public Configuration setDebug(Boolean debug) {
        this.debug = debug;
        return this;
    }

    public Boolean isDebug() {
        return this.debug;
    }

    public Boolean isStandardUrlType() {
        return standardUrlType;
    }

    public void setStandardUrlType(Boolean standardUrlType) {
        this.standardUrlType = standardUrlType;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public Configuration setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
        return this;
    }

    public RESTInterface getRestClient() {
        if (this.restClient == null) {
            this.restClient = new RESTClient().setConfig(this);
        }
        return this.restClient;
    }

    public Configuration setRestClient(RESTInterface restClient) {
        this.restClient = restClient;
        return this;
    }
}
