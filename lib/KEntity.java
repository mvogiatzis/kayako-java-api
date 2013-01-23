package lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * The type K entity. This is the base class for all the object types.
 *
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 * <p/>
 */

abstract public class KEntity {

    /**
     * The Controller.
     */
    static protected String controller;
    /**
     * The Xml name.
     */
    static protected String xmlName;
    /**
     * The Read only.
     */
    protected Boolean readOnly = false;

    private static final Logger log = Logger.getLogger(KEntity.class.getName());

    /**
     * Data key for storing files to send as multipart/form-data.
     *
     * @var string
     */
    public static final String FILES_DATA_NAME = "_files";

    static ConfigurationFactoryInterface configurationFactory;

    /**
     * Gets configuration factory.
     *
     * @return the configuration factory
     */
    public static ConfigurationFactoryInterface getConfigurationFactory() {
        if (configurationFactory == null) {
            configurationFactory = new ConfigurationFactory();
        }
        return configurationFactory;
    }

    /**
     * Sets configuration factory.
     *
     * @param configurationFactory the configuration factory
     */
    public static void setConfigurationFactory(ConfigurationFactoryInterface configurationFactory) {
        KEntity.configurationFactory = configurationFactory;
    }

    static String getController() {
        return controller;
    }

    /**
     * Gets xml name.
     *
     * @return the xml name
     */
    public static String getXmlName() {
        return xmlName;
    }

    /**
     * Get raw array element.
     *
     * @param controller the controller
     * @param id         the id
     * @return the raw array element
     * @throws KayakoException the kayako exception
     */ //These functions will return RawArrayElement, similar functions will be written in SubClasses to use these functions
    public static RawArrayElement get(String controller, int id) throws KayakoException {
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(Integer.toString(id));
        return get(controller, parameters).getFirstComponent();
    }

    /**
     * Get raw array element.
     *
     * @param controller the controller
     * @param parameters the parameters
     * @return the raw array element
     * @throws KayakoException the kayako exception
     */ //These functions will return RawArrayElement, similar functions will be written in SubClasses to use these functions
    public static RawArrayElement get(String controller, ArrayList<String> parameters) throws KayakoException {
        return KEntity.getRESTClient().get(controller, parameters);
    }

    /**
     * Refresh k entity.
     *
     * @param controller the controller
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public KEntity refresh(String controller) throws KayakoException {
        if (this.isNew()) {
            throw new KayakoException("This is a new object, can't be refreshed");
        }
        return this.populate(KEntity.getRESTClient().get(controller, this.getIdArray()).getComponents().get(0));
    }

    /**
     * Create k entity.
     *
     * @param controller the controller
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public KEntity create(String controller) throws KayakoException {
        if (this.getReadOnly()) {
            throw new KayakoException("This is a read only type, object can't be created");
        }
        RawArrayElement response = KEntity.getRESTClient().post(controller, new ArrayList<String>(), this.buildHashMap(true), this.buildFilesHashMap());
        return this.populate(response.getComponents().get(0));
    }

    /**
     * Update k entity.
     *
     * @param controller the controller
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public KEntity update(String controller) throws KayakoException {
        if (this.isNew()) {
            throw new KayakoException("This object needs to be created first.");
        }
        if (this.getReadOnly()) {
            throw new KayakoException("This is a read only type, object can't be updated");
        }
        return this.populate(KEntity.getRESTClient().put(controller, this.getIdArray(), this.buildHashMap(false), this.buildFilesHashMap()).getFirstComponent());
    }

    /**
     * Save k entity.
     *
     * @param controller the controller
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public KEntity save(String controller) throws KayakoException {
        if (this.isNew()) {
            return this.create(controller);
        } else {
            return this.update(controller);
        }
    }

    /**
     * Delete boolean.
     *
     * @param controller the controller
     * @return the boolean
     * @throws KayakoException the kayako exception
     */ //delete doesn't return anything
    public Boolean delete(String controller) throws KayakoException {
        if (this.readOnly == true) {
            throw new KayakoException("This is a read only object, so can't be deleted");
        }
        if (KEntity.getRESTClient().delete(controller, this.getIdArray()) != null) {
            return true;
        }
        return false;
    }

    /**
     * Gets all.
     *
     * @param controller the controller
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll(String controller) throws KayakoException {
        return KEntity.getAll(controller, new ArrayList<String>());
    }

    /**
     * Gets all.
     *
     * @param controller   the controller
     * @param searchParams the search params
     * @return the all
     */
    public static RawArrayElement getAll(String controller, ArrayList<String> searchParams) {
        RawArrayElement rawArrayElement;

        rawArrayElement = new RESTClient().initialize(getConfigurationFactory().getConfiguration()).get(controller, searchParams);
        return rawArrayElement;
    }

    /**
     * Gets read only.
     *
     * @return the read only
     */
    public Boolean getReadOnly() {
        return readOnly;
    }

    /**
     * Sets read only.
     *
     * @param readOnly the read only
     * @return the read only
     */
    public KEntity setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    /**
     * Gets id.
     *
     * @param entity the entity
     * @return the id
     */
    public static int getId(Object entity) {
        if (entity.getClass() == Integer.class) {

            return ((Integer) entity).intValue();
        }
        return ((KEntity) entity).getId();
    }

    /**
     * Is new.
     *
     * @return the boolean
     */
    public Boolean isNew() {
        if (this.getId() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Populate k entity.
     *
     * @param rawArrayElement the raw array element
     * @return the k entity
     * @throws KayakoException the kayako exception
     */
    public abstract KEntity populate(RawArrayElement rawArrayElement) throws KayakoException;

    /**
     * Build hash map.
     *
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap(Boolean isNew) {
        return new HashMap<String, String>();
    }

    /**
     * Build files hash map.
     *
     * @return the hash map
     */
    public HashMap<String, HashMap<String, String>> buildFilesHashMap() {
        return new HashMap<String, HashMap<String, String>>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public abstract int getId();

    /**
     * Gets id array.
     *
     * @return the id array
     */
    public ArrayList<String> getIdArray() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(this.getId()));
        return arrayList;
    }

    /**
     * Gets rEST client.
     *
     * @return the rEST client
     */
    public static RESTInterface getRESTClient() {
        return getConfigurationFactory().getConfiguration().getRestClient();

    }

    public String toString() {
        return this.getClass().getSimpleName() + "- ID: " + Integer.toString(this.getId());
    }

}
