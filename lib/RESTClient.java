package lib;

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

//import com.google.appengine.repackaged.com.google.common.util.Base64;
//import com.google.common.util.Base64;

//import org.apache.commons.codec.binary.Base64;
//import com.sun.org.apache.xml.internal.security.utils.Base64;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.zip.GZIPInputStream;

public class RESTClient implements RESTInterface {

    private Configuration config;

    public RESTClient initialize(Configuration config) {
        this.setConfig(config);
        return this;

    }

    @Override
    public RawArrayElement get(String controller) {
        return this.get(controller, new ArrayList<String>());
    }

    @Override
    public RawArrayElement post(String controller) {
        return this.post(controller, new ArrayList<String>());

    }

    @Override
    public RawArrayElement put(String controller) {
        return this.put(controller, new ArrayList<String>());
    }

    @Override
    public RawArrayElement delete(String controller) {
        return this.delete(controller, new ArrayList<String>());
    }

    @Override
    public RawArrayElement get(String controller, ArrayList<String> parameters) {
        return this.processRequest(controller, RESTClient.METHOD_GET, parameters, new HashMap<String, String>(), new HashMap<String, String>());
    }

    @Override
    public RawArrayElement post(String controller, ArrayList<String> parameters) {
        return this.post(controller, new ArrayList<String>(), new HashMap<String, String>());
    }

    @Override
    public RawArrayElement put(String controller, ArrayList<String> parameters) {
        return this.put(controller, new ArrayList<String>(), new HashMap<String, String>());
    }

    @Override
    public RawArrayElement delete(String controller, ArrayList<String> parameters) {
        return this.processRequest(controller, RESTClient.METHOD_DELETE, parameters, new HashMap<String, String>(), new HashMap<String, String>());
    }

    @Override
    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data) {
        return this.post(controller, parameters, data, new HashMap<String, String>());

    }

    @Override
    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data) {
        return this.put(controller, parameters, data, new HashMap<String, String>());
    }

    @Override
    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, String> files) {
        return this.processRequest(controller, RESTClient.METHOD_POST, parameters, data, files);
    }

    @Override
    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, String> files) {
        return this.processRequest(controller, RESTClient.METHOD_PUT, parameters, data, files);
    }


    public Configuration getConfig() {
        return this.config;
    }

    public RESTClient setConfig(Configuration config) {
        this.config = config;
        return this;
    }


    protected RawArrayElement processRequest(String controller, String method, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, String> files) {
        RawArrayElement rawArrayElement = new RawArrayElement();
        try {
            String url = this.getRequestData(controller, method, parameters, data);
            URL swiftURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) swiftURL.openConnection();
            if (method.equals(RESTClient.METHOD_POST)) {
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(this.getPostBody(data));
                writer.close();
            } else if (method.equals(RESTClient.METHOD_GET)) {
                connection.setDoOutput(false);
                connection.setRequestMethod("GET");
            } else if (method.equals(RESTClient.METHOD_DELETE)) {
                connection.setDoOutput(false);
                connection.setRequestMethod("DELETE");
            } else if (method.equals(RESTClient.METHOD_PUT)) {
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(this.getPostBody(data));
                writer.close();
            } else throw new KayakoException();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // OK
                InputStream inputStream = (InputStream) connection.getContent();
                String conEn = connection.getContentEncoding();
                if (conEn != null && conEn.equals("gzip")) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

                InputSource is = new InputSource(reader);
                is.setEncoding("UTF-8");
                //resp.getWriter().println("Content Encoding : " + connection.getContentEncoding());

                XMLHandler myHandler = new XMLHandler();
                SAXParserFactory factory = SAXParserFactory.newInstance();
                try {
                    SAXParser parser = factory.newSAXParser();
                    parser.parse(is, myHandler);
                } catch (ParserConfigurationException e) {
                    // TODO throw appropriate exception here
                    e.printStackTrace();
                } catch (SAXException e) {
                    // TODO throw appropriate exception here
                    e.printStackTrace();
                }
                inputStream.close();
                connection.disconnect();
                rawArrayElement = myHandler.getRawArrayElement();
            } else {
                // Server returned HTTP error code.
                InputStream inputStream = (InputStream) connection.getContent();
                String conEn = connection.getContentEncoding();
                if (conEn != null && conEn.equals("gzip")) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

                InputSource is = new InputSource(reader);
                is.setEncoding("UTF-8");
                BufferedReader bReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String line;
                while ((line = bReader.readLine()) != null) {
                    rawArrayElement.setContent(rawArrayElement.getContent() + "\n" + line);
                }

            }
        } catch (Exception e) {
        }
        return rawArrayElement;
    }


    /**
     * Prepares URL (and returns it) and POST data (updates it via reference).
     */
    private String getRequestData(String controller, String method, ArrayList<String> parameters, HashMap<String, String> data) throws UnsupportedEncodingException {
        Random rand = new Random();
        int salt = rand.nextInt();
        String signature = "";
        String url;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(this.config.getSecretKey().getBytes(), "HmacSHA256");
            mac.init(secret);
            signature = Base64.encodeBytes(mac.doFinal(Integer.toString(salt).getBytes("UTF-8")));
        } catch (Exception e) {
        }
        StringBuilder parameters_str = new StringBuilder();

        for (String parameter : parameters) {
            parameters_str.append("/" + parameter);
        }
        if (this.config.isStandardUrlType()) {
            url = this.config.getBaseUrl() + controller + "/" + parameters_str.toString();
        } else {
            url = this.config.getBaseUrl() + "e=" + controller + "/" + parameters_str.toString();
        }


        if (method.equals(RESTClient.METHOD_POST) || method.equals(RESTClient.METHOD_PUT)) {
            data.put("apikey", this.config.getApiKey());
            data.put("salt", Integer.toString(salt));
            data.put("signature", signature);
        } else {
            url += "&apikey=" + this.config.getApiKey() + "&salt=" + Integer.toString(salt) + "&signature=" + signature;
        }
        return url;
    }

    //TODO - remove when client is complete..this function is for use in testing only
    public String getRequestDataTest(String controller, String method, ArrayList<String> parameters, HashMap<String, String> data) throws UnsupportedEncodingException {
        Random rand = new Random();
        int salt = rand.nextInt();
        salt = 1261155461;
        String signature = "";
        String url;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(this.config.getSecretKey().getBytes("UTF-8"), "HmacSHA256");
            mac.init(secret);
            signature = Base64.encodeBytes(mac.doFinal(Integer.toString(salt).getBytes("UTF-8")));
        } catch (Exception e) {
        }
        StringBuilder parameters_str = new StringBuilder();

        for (String parameter : parameters) {
            parameters_str.append("/" + parameter);
        }
        if (this.config.isStandardUrlType()) {
            url = this.config.getBaseUrl() + controller + "/" + parameters_str.toString();
        } else {
            url = this.config.getBaseUrl() + "e=" + controller + "/" + parameters_str.toString();
        }


        if (method.equals(RESTClient.METHOD_POST) || method.equals(RESTClient.METHOD_PUT)) {
            data.put("apikey", this.config.getApiKey());
            data.put("salt", Integer.toString(salt));
            data.put("signature", signature);
        } else {
            url += "&apikey=" + this.config.getApiKey() + "&salt=" + Integer.toString(salt) + "&signature=" + signature;
        }
        return url;
    }


    private String getPostBody(HashMap<String, String> data) {
        return this.getPostBody(data, new HashMap<String, String>());
    }

    private String getPostBody(HashMap<String, String> data, HashMap<String, String> files) {
        return "";
    }
}
