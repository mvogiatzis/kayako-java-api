package lib;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ###############################################
 * <p/>
 * Kayako App
 * _______________________________________________
 *
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http://www.kayako.com/license
 * @link http://www.kayako.com
 * <p/>
 * ###############################################
 */

abstract public class KEntity {

    static protected String controller;
    static protected String xmlName;
    protected Boolean readOnly;

    /**
     * Data key for storing files to send as multipart/form-data.
     *
     * @var string
     */
    public static final String FILES_DATA_NAME = "_files";

    static String getController() {
        return controller;
    }

    public static String getXmlName() {
        return xmlName;
    }

    //These functions will return RawArrayElement, similar functions will be written in SubClasses to use these functions
    public static RawArrayElement get(String controller, int id) throws KayakoException {
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(Integer.toString(id));
        return get(controller, parameters);
    }

    //These functions will return RawArrayElement, similar functions will be written in SubClasses to use these functions
    public static RawArrayElement get(String controller, ArrayList<String> parameters) throws KayakoException {
        return KEntity.getRESTClient().get(controller, parameters);
    }

    public KEntity refresh(String controller) throws KayakoException {
        if (this.isNew()) {
            throw new KayakoException("This is a new object, can't be refreshed");
        }
        return this.populate(KEntity.getRESTClient().get(controller, this.getIdArray()).getComponents().get(0));
    }

    public KEntity create(String controller) throws KayakoException {
        if (this.getReadOnly()) {
            throw new KayakoException("This is a read only type, object can't be created");
        }
        return this.populate(KEntity.getRESTClient().post(controller, new ArrayList<String>(), this.buildHashMap(), this.buildFilesHashMap()).getComponents().get(0));
    }

    public KEntity update(String controller) throws KayakoException {
        if (this.isNew()) {
            throw new KayakoException("This object needs to be created first.");
        }
        if (this.getReadOnly()) {
            throw new KayakoException("This is a read only type, object can't be updated");
        }
        return this.populate(KEntity.getRESTClient().put(controller, this.getIdArray(), this.buildHashMap(), this.buildFilesHashMap()).getComponents().get(0));
    }

    public KEntity save(String controller) throws KayakoException {
        if (this.isNew()) {
            return this.create(controller);
        } else {
            return this.update(controller);
        }
    }

    //delete doesn't return anything
    public Boolean delete(String controller) throws KayakoException {
        if (this.readOnly == true) {
            throw new KayakoException("This is a read only object, so can't be deleted");
        }
        if (KEntity.getRESTClient().delete(controller, this.getIdArray()) != null) {
            return true;
        }
        return false;
    }

    public static RawArrayElement getAll(String controller) throws KayakoException {
        return KEntity.getAll(controller, new ArrayList<String>());
    }

    public static RawArrayElement getAll(String controller, ArrayList<String> searchParams) {
        RawArrayElement rawArrayElement;
        rawArrayElement = new RESTClient().initialize(new ConfigurationFactory().getConfiguration()).get(controller, searchParams);
        return rawArrayElement;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public KEntity setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static int getId(Object entity) {
        if (entity.getClass() == Integer.class) {

            return ((Integer) entity).intValue();
        }
        return ((KEntity) entity).getId();
    }

    public Boolean isNew() {
        if (this.getId() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public abstract KEntity populate(RawArrayElement rawArrayElement) throws KayakoException;

    public HashMap<String, String> buildHashMap() {
        return new HashMap<String, String>();
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap() {
        return new HashMap<String, HashMap<String, String>>();
    }

    public abstract int getId();

    public ArrayList<String> getIdArray() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(this.getId()));
        return arrayList;
    }

    public static RESTInterface getRESTClient() {
        return new ConfigurationFactory().getConfiguration().getRestClient();

    }

    public String toString() {
        return this.getClass().getSimpleName() + "- ID: " + Integer.toString(this.getId());
    }

}
