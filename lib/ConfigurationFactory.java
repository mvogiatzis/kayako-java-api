package lib;

import com.google.appengine.api.users.User;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

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
 * <p/>
 * THIS CLASS IS TO BE REWRITTEN FOR SPECIFIC USE
 * ###############################################
 */
public class ConfigurationFactory implements ConfigurationFactoryInterface {

    public boolean initialize(String userDomain) {
        this.getConfiguration(userDomain);
        return true;
    }

    //if the application is to be used on a single domain, this method alone can be used to create configuration object
    //The whole SDK will be relying on this method for connecting to Server
    public Configuration getConfiguration() {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        return this.getConfiguration(user.getAuthDomain());

    }

    public Configuration getConfiguration(String userDomain) {
        return this.getConfiguration(userDomain, true);
    }

    public Configuration getConfiguration(String userDomain, Boolean reload) {
        if (Configuration.getConfiguration() == null || reload) {
            this.reload(userDomain);
        }
        return Configuration.getConfiguration();
    }

    public boolean setConfiguration(String userDomain, String apiURL, String apiKey, String secretKey) {
        Entity configuration = this.getConfigurationEntity(userDomain);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Date date = new Date();
        configuration.setProperty("apikey", apiKey);
        configuration.setProperty("apiurl", apiURL);
        configuration.setProperty("secretkey", secretKey);
        configuration.setProperty("date", date);
        datastore.put(configuration);
        Configuration.getConfiguration().setBaseUrl(apiURL);
        //ConfigurationFactory.reload(userDomain);
        return true;

    }

    private Entity getConfigurationEntity(String userDomain) {
        Key kayakoKey = KeyFactory.createKey("Kayako", userDomain);
        Entity configuration;
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        try {
            configuration = datastore.get(kayakoKey);
        } catch (EntityNotFoundException ex) {
            configuration = new Entity(kayakoKey);
        }
        return configuration;
    }

    private void reload(String userDomain) {
        Entity configuration = this.getConfigurationEntity(userDomain);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Configuration.setConfiguration(new Configuration((String) configuration.getProperty("apiurl"), (String) configuration.getProperty("apikey"), (String) configuration.getProperty("secretkey")));
    }
}
