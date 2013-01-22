package lib;

/**
 * The Default implemented Configuration factory .
 * Refactor class & file to rename as ConfigurationFactory
 */
public class ConfigurationFactory implements ConfigurationFactoryInterface {
    /**
     * Sets configuration.
     *
     * @param configuration the configuration
     */
    public static void setConfiguration(Configuration configuration) {
        ConfigurationFactory.configuration = configuration;
    }

    private static Configuration configuration;

    /**
     * Instantiates a new Configuration factory .
     */
    public ConfigurationFactory() {
    }

    /**
     * Instantiates a new Configuration factory .
     *
     * @param baseUrl   the base url
     * @param apiKey    the api key
     * @param secretKey the secret key
     */
    public ConfigurationFactory(String baseUrl, String apiKey, String secretKey) {
        configuration = new Configuration(baseUrl, apiKey, secretKey);
    }

    @Override
    public Configuration getConfiguration() {
        return getConfig();
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    public static Configuration getConfig() {
        if (configuration == null) {
            try {
                throw new KayakoException("Configuration is not loaded, please call createNewConfiguration(String, String, String) first.");
            } catch (KayakoException e) {
                e.printStackTrace();
            }
        }
        return configuration;
    }

    /**
     * Create configuration.
     *
     * @param baseUrl   the base url
     * @param apiKey    the api key
     * @param secretKey the secret key
     * @return the configuration
     */
    public static Configuration createNewConfiguration(String baseUrl, String apiKey, String secretKey) {
        configuration = new Configuration(baseUrl, apiKey, secretKey);
        return configuration;
    }
}
