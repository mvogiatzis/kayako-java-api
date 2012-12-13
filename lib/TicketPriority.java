package lib;

import java.util.ArrayList;

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
public class TicketPriority extends KEntity {


    static final String TYPE_PUBLIC = "public";
    static final String TYPE_PRIVATE = "private";

    static protected String controller = "/Tickets/TicketPriority";
    static protected String objectXmlName = "ticketpriority";
    protected Boolean readOnly = true;

    /**
     * Ticket priority identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Ticket priority title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Ticket priority display order.
     *
     * @apiField
     * @var int
     */
    protected int displayOrder;


    /**
     * Path to icon displayed in GUI for this ticket priority.
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


    /**
     * Background color associated with this ticket priority in GUI.
     * <p/>
     * This color is used for the "General Tab Bar" in Kayako GUI when viewing the ticket.
     *
     * @apiField
     * @var string
     */
    private String backgroundColor;

    /**
     * Font color associated with this ticket priority in GUI.
     *
     * @apiField
     * @var string
     */
    protected String color;
    private ArrayList<UserGroup> userGroups = null;


    public Boolean isUserVisibilityCustom() {

        return userVisibilityCustom;
    }

    public TicketPriority setUserVisibilityCustom(Boolean userVisibilityCustom) {
        this.userVisibilityCustom = userVisibilityCustom;
        return this;
    }


    public String getDisplayIcon() {

        return displayIcon;
    }

    public TicketPriority setDisplayIcon(String displayIcon) {
        this.displayIcon = displayIcon;
        return this;
    }


    public int getDisplayOrder() {

        return displayOrder;
    }

    public TicketPriority setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public String getTitle() {

        return title;
    }

    public TicketPriority setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getId() {

        return id;
    }

    public TicketPriority setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Boolean getReadOnly() {

        return readOnly;
    }

    @Override
    public TicketPriority setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        TicketPriority.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        TicketPriority.controller = controller;
    }


    public String getType() {
        return type;
    }

    public TicketPriority setType(String type) {
        this.type = type;
        return this;
    }

    public ArrayList<Integer> getUserGroupIds() {
        return userGroupIds;
    }

    public TicketPriority setUserGroupIds(ArrayList<Integer> userGroupIds) {
        this.userGroupIds = userGroupIds;
        return this;
    }

    public ArrayList<UserGroup> getUserGroups() {
        return userGroups;
    }

    public TicketPriority setUserGroups(ArrayList<UserGroup> userGroups) {
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

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getColor() {
        return color;
    }

    public TicketPriority setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public TicketPriority setColor(String color) {
        this.color = color;
        return this;
    }

    //this function will populate the data of the ticket priority instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketPriority populate(RawArrayElement rawArrayElement) throws KayakoException {
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
            } else if (elementName.equals("displayorder")) {
                this.setDisplayOrder(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("displayicon")) {
                this.setDisplayIcon(component.getContent());
            } else if (elementName.equals("type")) {
                this.setType(component.getContent());
            } else if (elementName.equals("uservisibilitycustom")) {
                if (Integer.parseInt(component.getContent()) == 1) {
                    this.setUserVisibilityCustom(true);
                } else {
                    this.setUserVisibilityCustom(false);
                }
            } else if (elementName.equals("usergroupid")) {
                this.userGroupIds.add(new Integer(component.getContent()));
            } else if (elementName.equals("frcolorcode")) {
                this.setColor(component.getContent());
            } else if (elementName.equals("bgcolorcode")) {
                this.setBackgroundColor(component.getContent());
            }

        }

        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
