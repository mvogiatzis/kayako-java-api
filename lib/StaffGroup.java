package lib;

import java.util.ArrayList;
import java.util.HashMap;

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
 * ###############################################
 */
public class StaffGroup extends KEntity {

    static protected String controller = "/Base/StaffGroup";
    static protected String objectXmlName = "staffgroup";

    public StaffGroup(RawArrayElement rawArrayElement) throws KayakoException {
        this.populate(rawArrayElement);
    }

    public StaffGroup(String title) {
        this(title, false);
    }

    public StaffGroup(String title, Boolean admin) {
        this.setTitle(title);
        this.setAdmin(admin);
    }

    /**
     * Staff Group identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Staff Group title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Whether this staff group is admin group (built-in).
     *
     * @apiField
     * @var bool
     */
    protected Boolean admin;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getReadOnly() {

        return readOnly;
    }

    public StaffGroup setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        StaffGroup.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        StaffGroup.controller = controller;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public static StaffGroup get(int id) throws KayakoException {
        return new StaffGroup(KEntity.get(controller, id));
    }

    //this function will populate the data of the staff Group instance when supplied with RawArrayElement derived from the xml
    @Override
    public StaffGroup populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        ArrayList<RawArrayElement> components = rawArrayElement.getComponents();
        for (RawArrayElement component : components) {
            String elementName = component.getElementName();
            if (!component.isComposite() && component.getContent() == null) {
                break;
            }
            if (elementName.equals("id")) {
                this.setId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("title")) {
                this.setTitle(component.getContent());
            } else if (elementName.equals("isadmin")) {
                this.setAdmin(Helper.parseInt(component.getContent()) == 1);
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> staffGroupHashMap = new HashMap<String, String>();
        staffGroupHashMap.put("title", this.getTitle());
        staffGroupHashMap.put("isadmin", this.isAdmin() ? "1" : "0");
        return staffGroupHashMap;
    }

    //TODO - create new staff
}
