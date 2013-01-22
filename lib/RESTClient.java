package lib;

/**
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http://www.kayako.com/license
 * @link http://www.kayako.com
 *

 */

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

/**
 * The type REST client.
 */
public class RESTClient implements RESTInterface {

    private Configuration config;
    private static final Logger log = Logger.getLogger(RESTClient.class.getName());
    private static String charset;

    static {
        charset = "UTF-8";
    }

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
        return this.processRequest(controller, RESTClient.METHOD_GET, parameters, new HashMap<String, String>(), new HashMap<String, HashMap<String, String>>());
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
        return this.processRequest(controller, RESTClient.METHOD_DELETE, parameters, new HashMap<String, String>(), new HashMap<String, HashMap<String, String>>());
    }

    @Override
    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data) {
        return this.post(controller, parameters, data, new HashMap<String, HashMap<String, String>>());

    }

    @Override
    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data) {
        return this.put(controller, parameters, data, new HashMap<String, HashMap<String, String>>());
    }

    @Override
    public RawArrayElement post(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files) {
        return this.processRequest(controller, RESTClient.METHOD_POST, parameters, data, files);
    }

    @Override
    public RawArrayElement put(String controller, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files) {
        return this.processRequest(controller, RESTClient.METHOD_PUT, parameters, data, files);
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    public Configuration getConfig() {
        return this.config;
    }

    /**
     * Sets config.
     *
     * @param config the config
     * @return the config
     */
    public RESTClient setConfig(Configuration config) {
        this.config = config;
        return this;
    }

    /**
     * Process request test.
     *
     * @param controller the controller
     * @param method     the method
     * @param parameters the parameters
     * @param data       the data
     * @param files      the files
     * @return the raw array element
     */
    public RawArrayElement processRequestTest(String controller, String method, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files) {
        return this.processRequest(controller, method, parameters, data, files);
    }

    /**
     * Process request.
     *
     * @param controller the controller
     * @param method     the method
     * @param parameters the parameters
     * @param data       the data
     * @param files      the files
     * @return the raw array element
     */
    protected RawArrayElement processRequest(String controller, String method, ArrayList<String> parameters, HashMap<String, String> data, HashMap<String, HashMap<String, String>> files) {
        RawArrayElement rawArrayElement = new RawArrayElement();
        try {
            String url = this.getRequestData(controller, method, parameters, data);
            URL swiftURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) swiftURL.openConnection();
            if (method.equals(RESTClient.METHOD_POST)) {
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                String postBody = "";
                String boundary = Long.toHexString(System.currentTimeMillis());

                if (files.size() > 0) {
                    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                } else {
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                }
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(this.getPostBody(data, files, boundary));
                writer.close();
            } else if (method.equals(RESTClient.METHOD_GET)) {
                connection.setDoOutput(false);
                connection.setRequestMethod("GET");
            } else if (method.equals(RESTClient.METHOD_DELETE)) {
                connection.setDoOutput(false);
                connection.setRequestMethod("DELETE");
            } else if (method.equals(RESTClient.METHOD_PUT)) {
                connection.setDoOutput(true);
                connection.setRequestMethod("PUT");
                String postBody = "";
                String boundary = Long.toHexString(System.currentTimeMillis());

                if (files.size() > 0) {
                    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                } else {
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                }
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(this.getPostBody(data, files, boundary));
                writer.close();
            } else throw new KayakoException();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // OK
                InputStream inputStream = (InputStream) connection.getContent();
                String conEn = connection.getContentEncoding();
                if (conEn != null && conEn.equals("gzip")) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                InputStreamReader reader = new InputStreamReader(inputStream, charset);

                InputSource is = new InputSource(reader);
                is.setEncoding(charset);
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

                if (this.config.isDebug()) {
                    log.warning(rawArrayElement.toString());
                }

            } else {

                // Server returned HTTP error code.
                log.warning("Code : " + connection.getResponseCode() + "Response Message : " + connection.getResponseMessage());
                return null;
                //TODO - this portion is to be reconsidered
/*                InputStream inputStream = (InputStream) connection.getContent();
                String conEn = connection.getContentEncoding();
                if (conEn != null && conEn.equals("gzip")) {
                    inputStream = new GZIPInputStream(inputStream);
                }
                InputStreamReader reader = new InputStreamReader(inputStream, charset);

                InputSource is = new InputSource(reader);
                is.setEncoding(charset);
                BufferedReader bReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
                String line;
                while ((line = bReader.readLine()) != null) {
                    rawArrayElement.setContent(rawArrayElement.getContent() + "\n" + line);
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rawArrayElement;
    }

    /**
     * Prepares URL (and returns it) and POST data (updates it via reference).
     */
    private String getRequestData(String controller, String method, ArrayList<String> parameters, HashMap<String, String> data) throws UnsupportedEncodingException {
        Random rand = new Random();
        int salt = rand.nextInt();
        //for debugging purpose, keep salt fixed and check the signature
        //salt = 1244;
        String signature = "";
        String url;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(this.config.getSecretKey().getBytes(), "HmacSHA256");
            mac.init(secret);
            signature = Base64.encodeBytes(mac.doFinal(Integer.toString(salt).getBytes(charset)));
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
        if (this.config.isDebug()) {
            log.warning("URL : " + url);
        }

        return url;
    }

    /**
     * Gets request data test.
     *
     * @param controller the controller
     * @param method     the method
     * @param parameters the parameters
     * @param data       the data
     * @return the request data test
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String getRequestDataTest(String controller, String method, ArrayList<String> parameters, HashMap<String, String> data) throws UnsupportedEncodingException {
        return this.getRequestData(controller, method, parameters, data);
    }

    private String getPostBody(HashMap<String, String> data) throws UnsupportedEncodingException {
        return this.getPostBody(data, new HashMap<String, HashMap<String, String>>(), "");
    }

    private String getPostBody(HashMap<String, String> data, HashMap<String, HashMap<String, String>> files, String boundary) throws UnsupportedEncodingException {
        String postBody = "";

        //HttpPost httpPost = new HttpPost();
        //String charset = "UTF-8";
        if (files.size() > 0) {

            String CRLF = "\r\n"; // Line separator required by multipart/form-data.

            // Send normal param.
            for (Map.Entry<String, String> attribute : data.entrySet()) {
                postBody += "--" + boundary + CRLF;
                postBody += "Content-Disposition: form-data; name=\"" + attribute.getKey() + "\"" + CRLF;
                postBody += "Content-Type: text/plain; charset=" + charset + CRLF;
                postBody += CRLF;
                postBody += attribute.getValue() + CRLF;
            }

            for (Map.Entry<String, HashMap<String, String>> file : files.entrySet()) {
                // Send binary file.
                String fileName = "";
                String fileContents = "";
                for (Map.Entry<String, String> fileEntry : file.getValue().entrySet()) {
                    fileName = fileEntry.getKey();
                    fileContents = fileEntry.getValue();
                }
                postBody += "--" + boundary + CRLF;
                postBody += "Content-Disposition: form-data; name=\"" + file.getKey() + "\"; filename=\"" + fileName + "\"" + CRLF;
                postBody += "Content-Type: application/octet-stream" + CRLF;
                postBody += "Content-Transfer-Encoding: binary" + CRLF;
                postBody += fileContents;
                postBody += CRLF;
                postBody += "--" + boundary + "--" + CRLF;
            }
        } else {
            //foreach hashMap data element , create query string
            String query = "";
            try {
                for (Map.Entry<String, String> attribute : data.entrySet()) {
                    if (query != "") {
                        query += "&";
                    }
                    query += attribute.getKey() + "=" + URLEncoder.encode(attribute.getValue() == null ? "" : attribute.getValue(), charset);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            postBody = query;
        }
        if (this.config.isDebug()) {
            log.warning("POST Body :: " + postBody);
        }
        return postBody;
    }
}
