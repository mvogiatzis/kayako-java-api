package com.kayako.api;

/**
 * The interface Configuration factory interface.
 * @author Rajat Garg
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public interface ConfigurationFactoryInterface {

    //This function needs to be overridden in class ConfigurationFactory

    /**
     * Gets configuration.
     *
     * @return the configuration
     */ //call Configuration constructor in implementation
    // A simple implementation can be return new Configuration("url", "apikey", "secretKey");
    public Configuration getConfiguration();

}
