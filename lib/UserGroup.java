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
public class UserGroup extends KEntity {


    final static String TYPE_REGISTERED = "registered";
    final static String TYPE_GUEST = "guest";

    static protected String controller = "/Base/UserGroup";
    static protected String objectXmlName = "usergroup";


    public UserGroup(String title) {
        this(title, TYPE_REGISTERED);
    }

    public UserGroup(String title, String type) {
        this.setTitle(title);
        this.setType(type);
    }


    /**
     * User Group identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * User Group title.
     *
     * @apiField
     * @var string
     */
    protected String title;


    /**
     * Type of user Group.
     *
     * @apiField
     * @var string
     * @see UserGroup::TYPE constants.
     */
    protected String type;

    /**
     * Whether this user group is master group (built-in).
     *
     * @apiField
     * @var bool
     */
    protected Boolean master;


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

    public UserGroup setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        UserGroup.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        UserGroup.controller = controller;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isMaster() {
        return master;
    }

    public void setMaster(Boolean master) {
        this.master = master;
    }

    //this function will populate the data of the user Group instance when supplied with RawArrayElement derived from the xml
    @Override
    public UserGroup populate(RawArrayElement rawArrayElement) throws KayakoException {
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
                this.setId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("title")) {
                this.setTitle(component.getContent());
            } else if (elementName.equals("grouptype")) {
                this.setType(component.getContent());
            } else if (elementName.equals("ismaster")) {
                this.setMaster(Integer.parseInt(component.getContent()) == 1);
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> userGroupHashMap = new HashMap<String, String>();
        userGroupHashMap.put("title", this.getTitle());
        userGroupHashMap.put("grouptype", this.getType());
        return userGroupHashMap;
    }

    //TODO - create new user
}
