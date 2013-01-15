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
public class Department extends KEntity {

    static final String TYPE_PUBLIC = "public";
    static final String TYPE_PRIVATE = "private";
    /**
     * App a department can be associated with - Tickets.
     *
     * @var string
     */
    static final String APP_TICKETS = "tickets";

    /**
     * App a department can be associated with - Livechat.
     *
     * @var string
     */
    static final String APP_LIVECHAT = "tickets";

    static protected String controller = "/Base/Department";
    static protected String objectXmlName = "department";
    protected Boolean readOnly = true;

    public Department() {
        this.setType(TYPE_PUBLIC);
    }

    public Department(String title) {
        this(title, TYPE_PUBLIC);
    }

    public Department(String title, String type) {
        this(title, type, APP_TICKETS);
    }

    public Department(String title, String type, String app) {
        this.setTitle(title);
        this.setType(type);
        this.setApp(app);
    }

    /**
     * Department identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Department title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Department display order.
     *
     * @apiField
     * @var int
     */
    protected int displayOrder;

    /**
     * Linked department identifier.
     * <p/>
     * If a department is linked to a department, it will be visible only under the linked department.
     *
     * @apiField
     * @var int
     */
    protected int parentDepartmentId = 0;

    /**
     * Path to icon displayed in GUI for this department.
     *
     * @apiField
     * @var string
     */
    protected String displayIcon;

    /**
     * If this department is visible to specific user groups only.
     *
     * @apiField
     * @var bool
     */
    protected Boolean userVisibilityCustom;

    /**
     * Identifiers of user groups which can change department to this status.
     *
     * @apiField name = usergroupid
     * @var int[]
     */
    protected ArrayList<Integer> userGroupIds = new ArrayList<Integer>();

    /**
     * User groups which can change department to this status.
     *
     * @var UserGroup[]
     */
    private HashMap<Integer, UserGroup> userGroups = new HashMap<Integer, UserGroup>();

    /**
     * Type of department.
     *
     * @apiField
     * @var string
     * @see Department::TYPE constants.
     */
    protected String type;

    /**
     * Department app.
     *
     * @apiField required_create=true
     * @var int
     */

    protected String app;

    /**
     * Parent department.
     *
     * @var Department
     */

    protected Department parentDepartment = null;

    public Department getParentDepartment() throws KayakoException {
        return this.getParentDepartment(false);
    }

    public Department getParentDepartment(Boolean refresh) throws KayakoException {
        if (parentDepartment != null && !refresh) {
            return parentDepartment;
        }
        if (this.getParentDepartmentId() <= 0) {
            return null;
        }
        this.parentDepartment = (Department) Department.get(this.getParentDepartmentId());
        return this.parentDepartment;

    }

    private static Department get(int id) throws KayakoException {
        return new Department().populate(KEntity.get(controller, id));
    }

    public Department setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
        if (parentDepartment != null && parentDepartment.getId() > 0) {
            this.setParentDepartmentId(parentDepartment.getId());
        }
        return this;
    }

    public Boolean isUserVisibilityCustom() {

        return userVisibilityCustom;
    }

    public Department setUserVisibilityCustom(Boolean userVisibilityCustom) {
        this.userVisibilityCustom = userVisibilityCustom;
        return this;
    }

    public String getDisplayIcon() {

        return displayIcon;
    }

    public Department setDisplayIcon(String displayIcon) {
        this.displayIcon = displayIcon;
        return this;
    }

    public int getParentDepartmentId() {

        return parentDepartmentId;
    }

    public Department setParentDepartmentId(int parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
        return this;
    }

    public int getDisplayOrder() {

        return displayOrder;
    }

    public Department setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public String getTitle() {

        return title;
    }

    public Department setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getId() {

        return id;
    }

    public Department setId(int id) {
        this.id = id;
        return this;
    }

    public Boolean getReadOnly() {

        return readOnly;
    }

    public Department setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        Department.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        Department.controller = controller;

    }

    public HashMap<Integer, UserGroup> getUserGroups() throws KayakoException {
        return getUserGroups(false);
    }

    public HashMap<Integer, UserGroup> getUserGroups(Boolean refresh) throws KayakoException {
        for (int userGroupId : this.getUserGroupIds()) {
            if (!userGroups.containsKey(userGroupId) || refresh) {
                userGroups.put(userGroupId, UserGroup.get(userGroupId));
            }
        }
        return userGroups;
    }

    public Department setUserGroups(HashMap<Integer, UserGroup> userGroups) {
        this.userGroups = userGroups;
        return this;
    }

    public String getType() {
        return type;
    }

    public Department setType(String type) {
        this.type = type;
        return this;
    }

    public ArrayList<Integer> getUserGroupIds() {
        return userGroupIds;
    }

    public Department setUserGroupIds(ArrayList<Integer> userGroupIds) {
        this.userGroupIds = userGroupIds;
        return this;
    }

    public Department addUserGroup(UserGroup userGroup) {
        if (userGroupIds.contains(userGroup.getId())) {
            return this;
        }
        this.userGroups.put(userGroup.getId(), userGroup);
        this.userGroupIds.add(userGroup.getId());
        this.setUserVisibilityCustom(true);
        return this;
    }

    public Boolean isVisibleToUserGroup(int userGroupId) {
        if (!isUserVisibilityCustom()) {
            return true;
        }
        return this.userGroupIds.contains(userGroupId);
    }

    public Boolean isVisibleToUserGroup(UserGroup userGroup) {
        return this.isVisibleToUserGroup(userGroup.getId());
    }

    public String getApp() {
        return app;
    }

    public Department setApp(String app) {
        this.app = app;
        return this;
    }

    public Ticket createAutoTicket(String creatorFullName, String email, String contents, String subject) throws KayakoException {
        return new Ticket(this, creatorFullName, email, contents, subject);
    }

    /**
     * Creates new subDepartment in this department. Module of new department will be the same as parent department's module.
     * WARNING: Data is not sent to Kayako unless you explicitly call create() on this method's result.
     *
     * @param title Title of new department.
     * @return Department
     */
    public Department createSubDepartment(String title) {
        return this.createSubDepartment(title, Department.TYPE_PUBLIC);
    }

    public Department createSubDepartment(String title, String type) {
        return new Department(title, type, this.getApp()).setParentDepartment(this);
    }

    //this function will populate the data of the department instance when supplied with RawArrayElement derived from the xml
    @Override
    public Department populate(RawArrayElement rawArrayElement) throws KayakoException {
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
            } else if (elementName.equals("displayorder")) {
                this.setDisplayOrder(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("departmentid")) {
                this.setParentDepartmentId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("displayicon")) {
                this.setDisplayIcon(component.getContent());
            } else if (elementName.equals("type")) {
                this.setType(component.getContent());
            } else if (elementName.equals("app")) {
                this.setApp(component.getContent());
            } else if (elementName.equals("uservisibilitycustom")) {
                if (Helper.parseInt(component.getContent()) == 1) {
                    this.setUserVisibilityCustom(true);
                } else {
                    this.setUserVisibilityCustom(false);
                }
            } else if (elementName.equals("usergroups")) {
                ArrayList<RawArrayElement> userGroupElements = component.getComponents();
                for (RawArrayElement innerComponent : userGroupElements) {
                    if (innerComponent.getElementName().equals("id")) {
                        this.userGroupIds.add(new Integer(component.getContent()));
                    }

                }

            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> departmentHashMap = new HashMap<String, String>();
        departmentHashMap.put("title", this.getTitle());
        departmentHashMap.put("type", this.getType());
        departmentHashMap.put("app", this.getApp());
        departmentHashMap.put("displayorder", Integer.toString(this.getDisplayOrder()));
        departmentHashMap.put("parentdepartmentid", Integer.toString(this.getParentDepartmentId()));
        departmentHashMap.put("uservisibilitycustom", this.isUserVisibilityCustom() ? "1" : "0");
        if (this.isUserVisibilityCustom()) {
            for (Integer id : this.getUserGroupIds()) {
                departmentHashMap.put("usergroupid[]", id.toString());
            }
        }
        return departmentHashMap;
    }
}
