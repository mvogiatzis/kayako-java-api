package lib;

import java.util.ArrayList;

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

    static String getController() {
        return controller;
    }

    public static String getXmlName() {
        return xmlName;
    }

    public static KEntity get(int id) throws KayakoException {
        return null;
    }

    public static RawArrayElement getAll(String controller) throws KayakoException {
        return KEntity.getAll(controller, new ArrayList<String>());
    }

    public static RawArrayElement getAll(String controller, ArrayList<String> searchParams) {
        RawArrayElement rawArrayElement = new RawArrayElement();
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

    public abstract KEntity populate(RawArrayElement rawArrayElement) throws KayakoException;

    public abstract int getId();

}
