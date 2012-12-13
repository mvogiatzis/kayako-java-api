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
public class TicketStatus extends KEntity {


    final String TYPE_PUBLIC = "public";
    final String TYPE_PRIVATE = "private";

    static protected String controller = "/Tickets/TicketStatus";
    static protected String objectXmlName = "ticketstatus";
    protected Boolean readOnly = true;

    /**
     * Ticket Status identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Ticket Status title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Ticket Status display order.
     *
     * @apiField
     * @var int
     */
    protected int displayOrder;

    /**
     * Linked department identifier.
     * <p/>
     * If a ticket Status is linked to a department, it will be visible only under the linked department.
     *
     * @apiField
     * @var int
     */
    protected int departmentId = 0;

    /**
     * Path to icon displayed in GUI for this ticket Status.
     *
     * @apiField
     * @var string
     */
    protected String displayIcon;

    /**
     * Status of this ticket Status.
     *
     * @apiField
     * @var string
     * @see TicketStatus::Status constants.
     */
    protected String Status;

    /**
     * If this ticket Status is visible to specific user groups only.
     *
     * @apiField
     * @var bool
     */
    protected Boolean staffVisibilityCustom;


    /**
     * Identifiers of staff groups which can change ticket status to this status.
     *
     * @apiField name = staffgroupid
     * @var int[]
     */
    protected ArrayList<Integer> staffGroupIds = new ArrayList<Integer>();
    /**
     * Linked department.
     *
     * @var Department
     */
    private Department department = null;

    /**
     * Staff groups which can change ticket status to this status.
     *
     * @var StaffGroup[]
     */
    private ArrayList<StaffGroup> staffGroups = new ArrayList<StaffGroup>();
    /**
     * Background color associated with this ticket status in GUI.
     * <p/>
     * This color is used for the "General Tab Bar" in Kayako GUI when viewing the ticket.
     *
     * @apiField
     * @var string
     */
    private String backgroundColor;

    /**
     * Font color associated with this ticket status in GUI.
     *
     * @apiField
     * @var string
     */
    protected String color;
    /**
     * Type of ticket status.
     *
     * @apiField
     * @var string
     * @see TicketStatus::TYPE constants.
     */
    protected String type;

    /**
     * If enabled, Kayako will automatically clear the due time for a ticket when the ticket status changes to this status.
     *
     * @apiField
     * @var bool
     */
    protected Boolean resetDueTime;

    /**
     * If enabled, whenever a ticket is changed to this ticket status a survey email will be dispatched to the user asking for rating and comments.
     *
     * @apiField
     * @var bool
     */
    protected Boolean triggerSurvey;

    /**
     * If ticket count for this status is displayed in the filter ticket tree.
     *
     * @apiField
     * @var bool
     */

    protected Boolean displayCount;

    /**
     * If tickets with this status are marked as resolved/closed.
     *
     * @apiField
     * @var bool
     */
    protected Boolean markAsResolved;

    /**
     * If tickets status is to be displayed in main list or not .
     *
     * @apiField
     * @var bool
     */

    protected Boolean displayInMainList;


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean isStaffVisibilityCustom() {

        return staffVisibilityCustom;
    }

    public void setStaffVisibilityCustom(Boolean staffVisibilityCustom) {
        this.staffVisibilityCustom = staffVisibilityCustom;
    }

    public String getStatus() {

        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
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

    public TicketStatus setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        TicketStatus.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        TicketStatus.controller = controller;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<StaffGroup> getStaffGroups() {
        return staffGroups;
    }

    public void setStaffGroups(ArrayList<StaffGroup> staffGroups) {
        this.staffGroups = staffGroups;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getResetDueTime() {
        return resetDueTime;
    }

    public void setResetDueTime(Boolean resetDueTime) {
        this.resetDueTime = resetDueTime;
    }

    public Boolean getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(Boolean displayCount) {
        this.displayCount = displayCount;
    }

    public Boolean getMarkAsResolved() {
        return markAsResolved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getTriggerSurvey() {
        return triggerSurvey;
    }

    public void setTriggerSurvey(Boolean triggerSurvey) {
        this.triggerSurvey = triggerSurvey;
    }


    public void setMarkAsResolved(Boolean markAsResolved) {
        this.markAsResolved = markAsResolved;
    }

    public ArrayList<Integer> getStaffGroupIds() {
        return staffGroupIds;
    }

    public void setStaffGroupIds(ArrayList<Integer> staffGroupIds) {
        this.staffGroupIds = staffGroupIds;
    }


    public Boolean getDisplayInMainList() {
        return displayInMainList;
    }

    public TicketStatus setDisplayInMainList(Boolean displayInMainList) {
        this.displayInMainList = displayInMainList;
        return this;
    }


    public Boolean isVisibleToStaffGroup(int staffGroupId) {
        if (!isStaffVisibilityCustom()) {
            return true;
        }
        return this.staffGroupIds.contains(staffGroupId);
    }

    public Boolean isVisibleToStaffGroup(StaffGroup staffGroup) {
        return this.isVisibleToStaffGroup(staffGroup.getId());
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

    //this function will populate the data of the ticket Status instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketStatus populate(RawArrayElement rawArrayElement) throws KayakoException {
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
            } else if (elementName.equals("departmentid")) {
                this.setDepartmentId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("displayicon")) {
                this.setDisplayIcon(component.getContent());
            } else if (elementName.equals("type")) {
                this.setType(component.getContent());
            } else if (elementName.equals("statuscolor")) {
                this.setColor(component.getContent());
            } else if (elementName.equals("statusbgcolor")) {
                this.setBackgroundColor(component.getContent());
            } else if (elementName.equals("Status")) {
                this.setStatus(component.getContent());
            } else if (elementName.equals("resetduetime")) {
                if (Integer.parseInt(component.getContent()) == 1) {
                    this.setResetDueTime(true);
                } else {
                    this.setResetDueTime(false);
                }
            } else if (elementName.equals("displayinmainlist")) {
                if (Integer.parseInt(component.getContent()) == 1) {
                    this.setDisplayInMainList(true);
                } else {
                    this.setDisplayInMainList(false);
                }
            } else if (elementName.equals("staffvisibilitycustom")) {
                if (Integer.parseInt(component.getContent()) == 1) {
                    this.setStaffVisibilityCustom(true);
                } else {
                    this.setStaffVisibilityCustom(false);
                }
            } else if (elementName.equals("staffgroupid")) {
                this.staffGroupIds.add(new Integer(component.getContent()));
            } else if (elementName.equals("markasresolved")) {
                if (Integer.parseInt(component.getContent()) == 1) {
                    this.setMarkAsResolved(true);
                } else {
                    this.setMarkAsResolved(false);
                }
            } else if (elementName.equals("displaycount")) {
                if (Integer.parseInt(component.getContent()) == 1) {
                    this.setDisplayCount(true);
                } else {
                    this.setDisplayCount(false);
                }
            } else if (elementName.equals("triggersurvey")) {
                if (Integer.parseInt(component.getContent()) == 1) {
                    this.setTriggerSurvey(true);
                } else {
                    this.setTriggerSurvey(false);
                }
            }
        }
        return this;
    }

    public String toString() {
        return super.toString();
    }
}
