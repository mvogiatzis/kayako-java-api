package lib;

/**
 * The type Configuration factory default.
 */
public class ConfigurationFactoryDefault implements ConfigurationFactoryInterface {
    Configuration configuration;

    /**
     * Instantiates a new Configuration factory default.
     *
     * @param baseUrl   the base url
     * @param apiKey    the api key
     * @param secretKey the secret key
     */
    public ConfigurationFactoryDefault(String baseUrl, String apiKey, String secretKey) {
        configuration = new Configuration(baseUrl, apiKey, secretKey);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }
}
