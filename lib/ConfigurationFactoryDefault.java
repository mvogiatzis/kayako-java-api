package lib;

/**
 * The type Configuration factory default.
 * Refactor class & file to rename as ConfigurationFactory
 */
public class ConfigurationFactoryDefault implements ConfigurationFactoryInterface {
    /**
     * Sets configuration.
     *
     * @param configuration the configuration
     */
    public static void setConfiguration(Configuration configuration) {
        ConfigurationFactoryDefault.configuration = configuration;
    }

    private static Configuration configuration;

    /**
     * Instantiates a new Configuration factory default.
     */
    public ConfigurationFactoryDefault() {
    }

    /**
     * Instantiates a new Configuration factory default.
     *
     * @param baseUrl the base url
     * @param apiKey the api key
     * @param secretKey the secret key
     */
    public ConfigurationFactoryDefault(String baseUrl, String apiKey, String secretKey) {
        configuration = new Configuration(baseUrl, apiKey, secretKey);
    }

    @Override
    public Configuration getConfiguration() {
        if (this.configuration == null) {
            try {
                throw new KayakoException("Configuration is not loaded, please call createNewConfiguration(String, String, String) first.");
            } catch (KayakoException e) {
                e.printStackTrace();
            }
        }
        return this.configuration;
    }

    /**
     * Create configuration.
     *
     * @param baseUrl the base url
     * @param apiKey the api key
     * @param secretKey the secret key
     * @return the configuration
     */
    public static Configuration createNewConfiguration(String baseUrl, String apiKey, String secretKey) {
        configuration = new Configuration(baseUrl, apiKey, secretKey);
        return configuration;
    }
}
