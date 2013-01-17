package lib;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Staff group.
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class StaffGroup extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Base/StaffGroup";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "staffgroup";

    /**
     * Instantiates a new Staff group.
     *
     * @param rawArrayElement the raw array element
     * @throws KayakoException the kayako exception
     */
    public StaffGroup(RawArrayElement rawArrayElement) throws KayakoException {
        this.populate(rawArrayElement);
    }

    /**
     * Instantiates a new Staff group.
     *
     * @param title the title
     */
    public StaffGroup(String title) {
        this(title, false);
    }

    /**
     * Instantiates a new Staff group.
     *
     * @param title the title
     * @param admin the admin
     */
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

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {

        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
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

    /**
     * Gets object xml name.
     *
     * @return the object xml name
     */
    public static String getObjectXmlName() {

        return objectXmlName;
    }

    /**
     * Sets object xml name.
     *
     * @param objectXmlName the object xml name
     */
    public static void setObjectXmlName(String objectXmlName) {
        StaffGroup.objectXmlName = objectXmlName;
    }

    /**
     * Gets controller.
     *
     * @return the controller
     */
    public static String getController() {

        return controller;
    }

    /**
     * Sets controller.
     *
     * @param controller the controller
     */
    public static void setController(String controller) {
        StaffGroup.controller = controller;
    }

    /**
     * Is admin.
     *
     * @return the boolean
     */
    public Boolean isAdmin() {
        return admin;
    }

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * Get staff group.
     *
     * @param id the id
     * @return the staff group
     * @throws KayakoException the kayako exception
     */
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

    /**
     * Create staff.
     *
     * @param firstname the firstname
     * @param lastName the last name
     * @param userName the user name
     * @param email the email
     * @param password the password
     * @return the staff
     */
    public Staff createStaff(String firstname, String lastName, String userName, String email, String password) {
        return new Staff(firstname, lastName, userName, email, this, password);
    }
}
