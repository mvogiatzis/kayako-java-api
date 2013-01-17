/**
 * @author Rajat Garg
 *
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http://www.kayako.com/license
 * @link http://www.kayako.com
 *

 */

package lib;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interface REST interface.
 */
public interface RESTInterface {

    /**
     * The constant METHOD_GET.
     */
    final static String METHOD_GET = "GET";
    /**
     * The constant METHOD_POST.
     */
    final static String METHOD_POST = "POST";
    /**
     * The constant METHOD_PUT.
     */
    final static String METHOD_PUT = "PUT";
    /**
     * The constant METHOD_DELETE.
     */
    final static String METHOD_DELETE = "DELETE";

    /**
     * Initialize rEST interface.
     *
     * @param config the config
     * @return the rEST interface
     */
    public RESTInterface initialize(Configuration config);

    /**
     * Get raw array element.
     *
     * @param controller the controller
     * @return the raw array element
     */
    public RawArrayElement get(String controller);

    /**
     * Post raw array element.
     *
     * @param controller the controller
     * @return the raw array element
     */
    public RawArrayElement post(String controller);

    /**
     * Put raw array element.
     *
     * @param controller the controller
     * @return the raw array element
     */
    public RawArrayElement put(String controller);

    /**
     * Delete raw array element.
     *
     * @param controller the controller
     * @return the raw array element
     */
    public RawArrayElement delete(String controller);

    /**
     * Get raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @return the raw array element
     */
    public RawArrayElement get(String controller, ArrayList<String> parameters);

    /**
     * Post raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @return the raw array element
     */
    public RawArrayElement post(String controller, ArrayList<String> parameters);

    /**
     * Put raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @return the raw array element
     */
    public RawArrayElement put(String controller, ArrayList<String> parameters);

    /**
     * Delete raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @return the raw array element
     */
    public RawArrayElement delete(String controller, ArrayList<String> parameters);

    /**
     * Post raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @param data the data
     * @return the raw array element
     */
    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data);

    /**
     * Put raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @param data the data
     * @return the raw array element
     */
    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data);

    /**
     * Post raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @param data the data
     * @param files the files
     * @return the raw array element
     */
    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files);

    /**
     * Put raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @param data the data
     * @param files the files
     * @return the raw array element
     */
    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files);
}