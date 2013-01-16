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
public class TicketType extends KEntity {

    final String TYPE_PUBLIC = "public";
    final String TYPE_PRIVATE = "private";

    static protected String controller = "/Tickets/TicketType";
    static protected String objectXmlName = "tickettype";
    protected Boolean readOnly = true;

    /**
     * Ticket type identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Ticket type title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Ticket type display order.
     *
     * @apiField
     * @var int
     */
    protected int displayOrder;

    /**
     * Linked department identifier.
     * <p/>
     * If a ticket type is linked to a department, it will be visible only under the linked department.
     *
     * @apiField
     * @var int
     */
    protected int departmentId;

    /**
     * Path to icon displayed in GUI for this ticket type.
     *
     * @apiField
     * @var string
     */
    protected String displayIcon;

    /**
     * Type of this ticket type.
     *
     * @apiField
     * @var string
     * @see TicketType::TYPE constants.
     */
    protected String type;

    /**
     * If this ticket type is visible to specific user groups only.
     *
     * @apiField
     * @var bool
     */
    protected Boolean userVisibilityCustom;

    /**
     * Identifier of user group this ticket type is visible to.
     *
     * @apiField name=usergroupid
     * @var int[]
     */
    protected ArrayList<Integer> userGroupIds = new ArrayList<Integer>();

    /**
     * Linked department.
     *
     * @var Department
     */
    private Department department = null;

    private HashMap<Integer, UserGroup> userGroups = new HashMap<Integer, UserGroup>();

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean isUserVisibilityCustom() {

        return userVisibilityCustom;
    }

    public void setUserVisibilityCustom(Boolean userVisibilityCustom) {
        this.userVisibilityCustom = userVisibilityCustom;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplayIcon() {

        return displayIcon;
    }

    public void setDisplayIcon(String displayIcon) {
        this.displayIcon = displayIcon;
    }

    public int getDepartmentId() {

        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDisplayOrder() {

        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

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

    public TicketType setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        TicketType.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        TicketType.controller = controller;
    }

    public ArrayList<Integer> getUserGroupIds() {
        return userGroupIds;
    }

    public void setUserGroupIds(ArrayList<Integer> userGroupIds) {
        this.userGroupIds = userGroupIds;
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

    public TicketType setUserGroups(HashMap<Integer, UserGroup> userGroups) {
        this.userGroups = userGroups;
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

    public Boolean isAvailableInDepartment(int departmentId) {
        if (this.departmentId == 0) {
            return true;
        }
        return this.departmentId == departmentId;
    }

    public Boolean isAvailableInDepartment(Department department) {
        return this.isAvailableInDepartment(department.getId());
    }

    public static TicketType get(int id) throws KayakoException {
        return new TicketType().populate(KEntity.get(controller, id));
    }

    //this function will populate the data of the ticket type instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketType populate(RawArrayElement rawArrayElement) throws KayakoException {
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
                this.setDepartmentId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("displayicon")) {
                this.setDisplayIcon(component.getContent());
            } else if (elementName.equals("type")) {
                this.setType(component.getContent());
            } else if (elementName.equals("uservisibilitycustom")) {
                if (Helper.parseInt(component.getContent()) == 1) {
                    this.setUserVisibilityCustom(true);
                } else {
                    this.setUserVisibilityCustom(false);
                }
            } else if (elementName.equals("usergroupid")) {
                this.userGroupIds.add(new Integer(component.getContent()));
            }

        }

        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
