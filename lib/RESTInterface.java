/**
 * ###############################################
 *
 * Kayako App
 * _______________________________________________
 *
 * @author Rajat Garg
 *
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http://www.kayako.com/license
 * @link http://www.kayako.com
 *
 * ###############################################
 */

package lib;

import java.util.ArrayList;
import java.util.HashMap;

public interface RESTInterface {

    final static String METHOD_GET = "GET";
    final static String METHOD_POST = "POST";
    final static String METHOD_PUT = "PUT";
    final static String METHOD_DELETE = "DELETE";

    public RESTInterface initialize(Configuration config);

    public RawArrayElement get(String controller);

    public RawArrayElement post(String controller);

    public RawArrayElement put(String controller);

    public RawArrayElement delete(String controller);

    public RawArrayElement get(String controller, ArrayList<String> parameters);

    public RawArrayElement post(String controller, ArrayList<String> parameters);

    public RawArrayElement put(String controller, ArrayList<String> parameters);

    public RawArrayElement delete(String controller, ArrayList<String> parameters);

    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data);

    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data);

    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files);

    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files);
}