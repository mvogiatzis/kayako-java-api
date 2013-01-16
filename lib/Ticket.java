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
public class Ticket extends KEntityCustom {

    static final int FLAG_NONE = 0;
    static final int FLAG_PURPLE = 1;
    static final int FLAG_ORANGE = 2;
    static final int FLAG_GREEN = 3;
    static final int FLAG_YELLOW = 4;
    static final int FLAG_RED = 5;
    static final int FLAG_BLUE = 6;
    static final int CREATOR_AUTO = 0;
    static final int CREATOR_STAFF = 1;
    static final int CREATOR_USER = 2;
    static final int CREATOR_CLIENT = 2;
    static final int CREATION_MODE_SUPPORTCENTER = 1;
    static final int CREATION_MODE_STAFFCP = 2;
    static final int CREATION_MODE_EMAIL = 3;
    static final int CREATION_MODE_API = 4;
    static final int CREATION_MODE_SITEBADGE = 5;
    static final int CREATION_TYPE_DEFAULT = 1;
    static final int CREATION_TYPE_PHONE = 2;
    /**
     * Flag for searching using query - search the Ticket ID & Mask ID.
     *
     * @var string
     */
    public static final String SEARCH_TICKET_ID = "ticketid";
    /**
     * Flag for searching using query - search the Ticket Post Contents.
     *
     * @var string
     */
    public static final String SEARCH_CONTENTS = "contents";
    /**
     * Flag for searching using query - search the Full Name & Email.
     *
     * @var string
     */
    public static final String SEARCH_AUTHOR = "author";
    /**
     * Flag for searching using query - search the Email Address (Ticket & Posts).
     *
     * @var string
     */
    public static final String SEARCH_EMAIL = "email";
    /**
     * Flag for searching using query - search the Email Address (only Tickets).
     *
     * @var string
     */
    public static final String SEARCH_CREATOR_EMAIL = "creatoremail";
    /**
     * Flag for searching using query - search the Full Name.
     *
     * @var string
     */
    public static final String SEARCH_FULL_NAME = "fullname";
    /**
     * Flag for searching using query - search the Ticket Notes.
     *
     * @var string
     */
    public static final String SEARCH_NOTES = "notes";
    /**
     * Flag for searching using query - search the User Group.
     *
     * @var string
     */
    public static final String SEARCH_USER_GROUP = "usergroup";
    /**
     * Flag for searching using query - search the User Organization.
     *
     * @var string
     */
    public static final String SEARCH_USER_ORGANIZATION = "userorganization";
    /**
     * Flag for searching using query - search the User (Full Name, Email).
     *
     * @var string
     */
    public static final String SEARCH_USER = "user";
    /**
     * Flag for searching using query - search the Ticket Tags.
     *
     * @var string
     */
    public static final String SEARCH_TAGS = "tags";
    static protected String controller = "/Tickets/Ticket";
    static protected String objectXmlName = "ticket";
    //Custom Field Croup Class = TicketCustomFieldGroup is used extensively
    static String customFieldGroupController = TicketCustomFieldGroup.getController();
    static protected String objectIdField = "ticketid";
    /**
     * Default status identifier for new tickets.
     *
     * @var int
     * @see Ticket::setDefaults
     */
    static private int defaultStatusId;
    /**
     * Default priority identifier for new tickets.
     *
     * @var int
     * @see Ticket::setDefaults
     */
    static private int defaultPriorityId;
    /**
     * Default type identifier for new tickets.
     *
     * @var int
     * @see Ticket::setDefaults
     */
    static private int defaultTypeId;
    /**
     * Default status identifier for new tickets.
     *
     * @var int
     * @see Ticket::setDefaults
     */
    static private Boolean autoCreateUser = true;
    /**
     * Ticket identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;
    /**
     * Ticket flag type.
     *
     * @apiField
     * @var int
     * @see Ticket::FLAG static final Stringants.
     */
    protected int flagType;
    /**
     * Ticket display identifier.
     *
     * @apiField
     * @var string
     */
    protected String displayId;
    /**
     * Ticket department identifier.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int departmentId;
    /**
     * Ticket status identifier.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int statusId;
    /**
     * Ticket priority identifier.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int priorityId;
    /**
     * Ticket type identifier.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int typeId;
    /**
     * Identifier of the user ticket was created by.
     *
     * @apiField
     * @var int
     */
    protected int userId;
    /**
     * Name of the organization of the user ticket was created by.
     *
     * @apiField
     * @var string
     */
    protected String userOrganizationName;
    /**
     * Identifier of the organization of the user ticket was created by.
     *
     * @apiField
     * @var int
     */
    protected int userOrganizationId;
    /**
     * Identifier of staff user who owns the ticket.
     *
     * @apiField
     * @var int
     */
    protected int ownerStaffId;
    /**
     * Full name of staff user who owns the ticket.
     *
     * @apiField
     * @var string
     */
    protected String ownerStaffName;
    /**
     * Full name of creator of the ticket.
     *
     * @apiField required_create=true
     * @var int
     */
    protected String fullName;
    /**
     * E-mail of creator of the ticket.
     *
     * @apiField required_create=true
     * @var string
     */
    protected String email;
    /**
     * Full name of the last replier to this ticket.
     *
     * @apiField
     * @var string
     */
    protected String lastReplier;
    /**
     * Ticket subject.
     *
     * @apiField required_create=true
     * @var string
     */
    protected String subject;
    /**
     * Timestamp of when this ticket was created.
     *
     * @apiField
     * @var int
     */
    protected int creationTime;
    /**
     * Timestamp of last activity in this ticket.
     *
     * @apiField
     * @var int
     */
    protected int lastActivity;
    /**
     * Timestamp of last staff user reply.
     *
     * @apiField
     * @var int
     */
    protected int lastStaffReply;
    /**
     * Timestamp of last user reply.
     *
     * @apiField
     * @var int
     */
    protected int lastUserReply;
    /**
     * Service Level Agreement plan identifier.
     *
     * @apiField
     * @var int
     */
    protected int SLAPlanId;
    /**
     * Timestamp of when the next replay is due.
     *
     * @apiField
     * @var int
     */
    protected int nextReplyDue;
    /**
     * Timestamp of when resolution of the ticket is due.
     *
     * @apiField
     * @var int
     */
    protected int resolutionDue;
    /**
     * Reply count.
     *
     * @apiField
     * @var int
     */
    protected int replies;
    /**
     * IP address from which the ticket was created.
     *
     * @apiField
     * @var string
     */
    protected String ipAddress;
    /**
     * Type of the ticket creator.
     *
     * @apiField
     * @var int
     * @see Ticket::CREATOR static final Stringants.
     */
    protected int creator;
    /**
     * Ticket creation mode.
     *
     * @apiField
     * @var int
     * @see Ticket::CREATION_MODE static final Stringants.
     */
    protected int creationMode;
    /**
     * Ticket creation type.
     *
     * @apiField alias=type
     * @var int
     * @see Ticket::CREATION_TYPE static final Stringants.
     */
    protected int creationType;
    /**
     * Is this ticket escalated.
     *
     * @apiField
     * @var bool
     */
    protected Boolean escalated;
    /**
     * Escalation rule identifier.
     *
     * @apiField
     * @var int
     */
    protected int escalationRuleId;
    /**
     * Template group identifier.
     *
     * @apiField getter=getTemplateGroupId setter=setTemplateGroup alias=templategroup
     * @var int
     */
    protected int templateGroupId;
    /**
     * Template group name.
     *
     * @apiField getter=getTemplateGroupName setter=setTemplateGroup
     * @var string
     */
    protected String templateGroupName;
    /**
     * Ticket tags.
     *
     * @apiField
     * @var string
     */
    protected String tags;
    /**
     * Ticket watchers.
     *
     * @apiField
     * @var HashMap<id, full name></>
     */
    protected HashMap<Integer, String> watchers = new HashMap<Integer, String>();
    /**
     * Ticket workflows.
     *
     * @apiField
     * @var HashMap <id, title></>
     */
    protected HashMap<Integer, String> workflows = new HashMap<Integer, String>();
    /**
     * Identifier os staff user who will create this ticket.
     *
     * @apiField
     * @var int
     */
    protected int staffId;
    /**
     * Ticket contents.
     *
     * @apiField required_create=true
     * @var string
     */
    protected String contents = null;
    /**
     * Option to disable autoresponder e-mail.
     *
     * @apiField
     * @var bool
     */
    protected Boolean ignoreAutoResponder = false;
    /**
     * Ticket status.
     *
     * @var TicketStatus
     */
    private TicketStatus status;
    /**
     * Ticket priority.
     *
     * @var TicketPriority
     */
    private TicketPriority priority;
    /**
     * Ticket type.
     *
     * @var TicketType
     */
    private TicketType type;
    /**
     * User, the creator of this ticket.
     *
     * @var User
     */
    private User user;
    /**
     * Organization of user who created this ticket.
     *
     * @var UserOrganization
     */
    private UserOrganization userOrganization;
    /**
     * Staff user, the creator of this ticket.
     *
     * @var Staff
     */
    private Staff staff;
    /**
     * Staff user who is the owner of this ticket.
     *
     * @var Staff
     */
    private Staff ownerStaff;
    /**
     * Department of this ticket.
     *
     * @var Department
     */
    private Department department = null;
    /**
     * List of ticket notes.
     *
     * @var TicketNote[]
     */
    private ArrayList<TicketNote> notes = new ArrayList<TicketNote>();
    /**
     * List of ticket time tracks.
     *
     * @var TicketTimeTrack[]
     */
    private ArrayList<TicketTimeTrack> timeTracks = new ArrayList<TicketTimeTrack>();
    /**
     * List of ticket posts.
     *
     * @var TicketPost[]
     */
    private ArrayList<TicketPost> posts = new ArrayList<TicketPost>();
    /**
     * List of ticket attachments.
     *
     * @var TicketAttachment[]
     */
    private ArrayList<TicketAttachment> attachments = new ArrayList<TicketAttachment>();
    /**
     * Tickets statistic.
     *
     * @var RawArrayElement
     */
    static private RawArrayElement statistics = null;

    public Ticket() {
    }

    private Ticket(Department department, String contents, String subject) {
        this.setDepartment(department);
        this.setContents(contents).setSubject(subject);
        this.setTypeId(getDefaultTypeId()).setPriorityId(getDefaultPriorityId()).setStatusId(getDefaultStatusId());

    }

    public Ticket(Department department, int creator, String contents, String subject) {
        this(department, contents, subject);
        this.setCreator(creator);
    }

    /**
     * Creates new ticket with implicit staff as creator.
     * WARNING: Data is not sent to Kayako unless you explicitly call create() on this method's result.
     */
    public Ticket(Department department, Staff creator, String contents, String subject) {
        this(department, contents, subject);
        this.setCreator(creator);
    }

    /**
     * Creates new ticket with implicit user as creator.
     * WARNING: Data is not sent to Kayako unless you explicitly call create() on this method's result.
     */
    public Ticket(Department department, User creator, String contents, String subject) throws KayakoException {
        this(department, contents, subject);
        this.setCreator(creator);
    }

    /**
     * Creates new ticket with creator user automatically created by server using provided name and e-mail.
     * WARNING: Data is not sent to Kayako unless you explicitly call create() on this method's result.
     */
    public Ticket(Department department, String creatorFullName, String creatorEmail, String contents, String subject) throws KayakoException {
        this(department, contents, subject);
        this.setCreatorAuto(creatorFullName, creatorEmail);
    }

    public Ticket setCreatorId(int creatorId, int creatorType) throws KayakoException {
        switch (creatorType) {
            case CREATOR_STAFF:
                this.setStaffId(creatorId);
                break;
            case CREATOR_USER:
                this.setUserId(creatorId);
        }
        return this;
    }

    public Ticket setCreator(User user) throws KayakoException {
        this.setUser(user);
        return this;
    }

    public Ticket setCreator(Staff staff) {
        this.setStaff(staff);
        return this;
    }

    public Ticket setCreatorAuto(String fullName, String email) throws KayakoException {
        this.setFullName(fullName).setEmail(email).setCreator(CREATOR_AUTO);
        this.setUser(null).setUserId(0).setStaff(null).setStaffId(0);
        return this;
    }

    public static String getController() {
        return controller;
    }

    public static void setController(String controller) {
        Ticket.controller = controller;
    }

    public static String getObjectXmlName() {
        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        Ticket.objectXmlName = objectXmlName;
    }

    public static String getCustomFieldGroupController() {
        return customFieldGroupController;
    }

    public static void setCustomFieldGroupController(String customFieldGroupController) {
        Ticket.customFieldGroupController = customFieldGroupController;
    }

    public static String getObjectIdField() {
        return objectIdField;
    }

    public static void setObjectIdField(String objectIdField) {
        Ticket.objectIdField = objectIdField;
    }

    @Override
    protected ArrayList<CustomFieldGroup> loadCustomFieldGroups(Boolean refresh) throws KayakoException {
        if (this.isNew()) {
            throw new KayakoException("Custom fields are not available for new objects. Save the object before accessing its custom fields.");
        }
        ArrayList<CustomFieldGroup> customFieldGroups = this.getCustomFieldGroups();
        if (customFieldGroups.size() == 0 && !refresh) {
            return customFieldGroups;
        }
        RawArrayElement rawArrayElement = TicketCustomFieldGroup.getAll(Ticket.getCustomFieldGroupController());
        for (RawArrayElement component : rawArrayElement.getComponents()) {
            customFieldGroups.add(new TicketCustomFieldGroup(this.getId(), component));
        }
        this.setCustomFieldGroups(customFieldGroups);
        this.cacheFields();
        return this.getCustomFieldGroups();
    }

    /**
     * Prepares local array for custom field fast lookup based on its name.
     * this function should populate this.customFields
     */
    @Override
    protected ArrayList<CustomField> loadCustomField(Boolean refresh) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static int getDefaultStatusId() {
        return defaultStatusId;
    }

    public static void setDefaultStatusId(int defaultStatusId) {
        Ticket.defaultStatusId = defaultStatusId;
    }

    public static int getDefaultPriorityId() {
        return defaultPriorityId;
    }

    public static void setDefaultPriorityId(int defaultPriorityId) {
        Ticket.defaultPriorityId = defaultPriorityId;
    }

    public static int getDefaultTypeId() {
        return defaultTypeId;
    }

    public static void setDefaultTypeId(int defaultTypeId) {
        Ticket.defaultTypeId = defaultTypeId;
    }

    public static Boolean getAutoCreateUser() {
        return autoCreateUser;
    }

    public static void setAutoCreateUser(Boolean autoCreateUser) {
        Ticket.autoCreateUser = autoCreateUser;
    }

    public int getId() {
        return id;
    }

    public Ticket setId(int id) {
        this.id = id;
        return this;
    }

    public int getFlagType() {
        return flagType;
    }

    public Ticket setFlagType(int flagType) {
        this.flagType = flagType;
        return this;
    }

    public String getDisplayId() {
        return displayId;
    }

    public Ticket setDisplayId(String displayId) {
        this.displayId = displayId;
        return this;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public Ticket setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
        this.department = null;
        return this;
    }

    public int getStatusId() {
        return statusId;
    }

    public Ticket setStatusId(int statusId) {
        this.statusId = statusId;
        this.status = null;
        return this;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public Ticket setPriorityId(int priorityId) {
        this.priorityId = priorityId;
        this.priority = null;
        return this;
    }

    public int getTypeId() {
        return typeId;
    }

    public Ticket setTypeId(int typeId) {
        this.typeId = typeId;
        this.type = null;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Ticket setUserId(int userId) throws KayakoException {
        if (userId > 0) {
            this.creator = CREATOR_USER;
            this.staff = null;
            this.staffId = 0;
            this.user = User.get(userId);
            this.fullName = user.getFullName();
            this.email = user.getEmail();
        }
        this.userId = userId;
        return this;
    }

    /**
     * Returns name of organization the user who created the ticket belongs to.
     *
     * @return userOrganizationName
     */
    public String getUserOrganizationName() {
        return userOrganizationName;
    }

    public Ticket setUserOrganizationName(String userOrganizationName) {
        this.userOrganizationName = userOrganizationName;
        return this;
    }

    /**
     * Returns identifier of organization the user who created the ticket belongs to.
     *
     * @return userOrganizationId
     */

    public int getUserOrganizationId() {
        return userOrganizationId;
    }

    public Ticket setUserOrganizationId(int userOrganizationId) {
        this.userOrganizationId = userOrganizationId;
        return this;
    }

    /**
     * Returns identifier of the staff user, owner of this ticket.
     */
    public int getOwnerStaffId() {
        return ownerStaffId;
    }

    /**
     * Sets identifier of the staff user, owner of this ticket.
     */
    public Ticket setOwnerStaffId(int ownerStaffId) {
        this.ownerStaffId = ownerStaffId;
        this.ownerStaff = null;
        return this;
    }

    /**
     * Returns full name of the staff user, owner of this ticket.
     */
    public String getOwnerStaffName() {
        return ownerStaffName;
    }

    public Ticket setOwnerStaffName(String ownerStaffName) {
        this.ownerStaffName = ownerStaffName;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Ticket setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Ticket setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLastReplier() {
        return lastReplier;
    }

    public Ticket setLastReplier(String lastReplier) {
        this.lastReplier = lastReplier;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Ticket setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public Ticket setCreationTime(int creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public int getLastActivity() {
        return lastActivity;
    }

    public Ticket setLastActivity(int lastActivity) {
        this.lastActivity = lastActivity;
        return this;
    }

    public int getLastStaffReply() {
        return lastStaffReply;
    }

    public Ticket setLastStaffReply(int lastStaffReply) {
        this.lastStaffReply = lastStaffReply;
        return this;
    }

    public int getLastUserReply() {
        return lastUserReply;
    }

    public Ticket setLastUserReply(int lastUserReply) {
        this.lastUserReply = lastUserReply;
        return this;
    }

    public int getSLAPlanId() {
        return SLAPlanId;
    }

    public Ticket setSLAPlanId(int SLAPlanId) {
        this.SLAPlanId = SLAPlanId;
        return this;
    }

    public int getNextReplyDue() {
        return nextReplyDue;
    }

    public Ticket setNextReplyDue(int nextReplyDue) {
        this.nextReplyDue = nextReplyDue;
        return this;
    }

    public int getResolutionDue() {
        return resolutionDue;
    }

    public Ticket setResolutionDue(int resolutionDue) {
        this.resolutionDue = resolutionDue;
        return this;
    }

    public int getReplies() {
        return replies;
    }

    public Ticket setReplies(int replies) {
        this.replies = replies;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Ticket setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public int getCreator() {
        switch (this.creator) {
            case CREATOR_USER:
                return this.userId;
            case CREATOR_STAFF:
                return this.staffId;
        }
        return creator;
    }

    public Ticket setCreator(int creatorId, int type) throws KayakoException {
        switch (type) {
            case CREATOR_USER:
                this.setUserId(creatorId);
                break;
            case CREATOR_STAFF:
                this.setStaffId(creatorId);
                break;
        }
        return this;
    }

    public Ticket setCreator(int creator) {
        this.creator = creator;
        return this;
    }

    public int getCreationMode() {
        return creationMode;
    }

    public Ticket setCreationMode(int creationMode) {
        this.creationMode = creationMode;
        return this;
    }

    public int getCreationType() {
        return creationType;
    }

    public Ticket setCreationType(int creationType) {
        this.creationType = creationType;
        return this;
    }

    public Boolean getEscalated() {
        return escalated;
    }

    public Ticket setEscalated(Boolean escalated) {
        this.escalated = escalated;
        return this;
    }

    public int getEscalationRuleId() {
        return escalationRuleId;
    }

    public Ticket setEscalationRuleId(int escalationRuleId) {
        this.escalationRuleId = escalationRuleId;
        return this;
    }

    public int getTemplateGroupId() {
        return templateGroupId;
    }

    public Ticket setTemplateGroupId(int templateGroupId) {
        this.templateGroupId = templateGroupId;
        this.templateGroupName = null;
        return this;
    }

    public String getTemplateGroupName() {
        return templateGroupName;
    }

    public Ticket setTemplateGroupName(String templateGroupName) {
        this.templateGroupName = templateGroupName;
        this.templateGroupId = 0;
        return this;
    }

    public Ticket setTemplateGroup(int id) {
        return this.setTemplateGroupId(id);
    }

    public Ticket setTemplateGroup(String name) {
        return this.setTemplateGroupName(name);
    }

    public String getTags() {
        return tags;
    }

    public Ticket setTags(String tags) {
        this.tags = tags;
        return this;
    }

    //HashMap staffId => Full Name
    public HashMap<Integer, String> getWatchers() {
        return watchers;
    }

    public Ticket setWatchers(HashMap<Integer, String> watchers) {
        this.watchers = watchers;
        return this;
    }

    public Ticket addWatcher(int id, String name) {
        this.watchers.put(id, name);
        return this;
    }

    public HashMap<Integer, String> getWorkflows() {
        return workflows;
    }

    public Ticket setWorkflows(HashMap<Integer, String> workflows) {
        this.workflows = workflows;
        return this;
    }

    public Ticket addWorkflow(int id, String title) {
        this.workflows.put(id, title);
        return this;
    }

    public int getStaffId() {
        return staffId;
    }

    /**
     * Sets identifier of staff user, the creator of this ticket.
     */
    public Ticket setStaffId(int staffId) throws KayakoException {
        if (staffId > 0) {
            this.staffId = staffId;
            this.creator = CREATOR_STAFF;
            this.user = null;
            this.staff = Staff.get(staffId);
            this.userId = 0;
            this.fullName = staff.getFullName();
            this.email = staff.getEmail();
        }
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Ticket setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public Boolean getIgnoreAutoResponder() {
        return ignoreAutoResponder;
    }

    public Ticket setIgnoreAutoResponder(Boolean ignoreAutoResponder) {
        this.ignoreAutoResponder = ignoreAutoResponder;
        return this;
    }

    public TicketStatus getStatus() throws KayakoException {
        return this.getStatus(false);
    }

    public TicketStatus getStatus(Boolean refresh) throws KayakoException {
        if ((this.status == null || refresh) && this.statusId > 0) {
            status = TicketStatus.get(this.statusId);
        }
        return status;
    }

    public Ticket setStatus(TicketStatus status) {
        if (status != null) {
            this.statusId = status.getId();
            this.status = status;
        }
        return this;
    }

    public TicketPriority getPriority() throws KayakoException {
        return this.getPriority(false);
    }

    public TicketPriority getPriority(Boolean refresh) throws KayakoException {
        if ((this.priority == null || refresh) && this.priorityId > 0) {
            priority = TicketPriority.get(this.priorityId);
        }
        return priority;
    }

    public Ticket setPriority(TicketPriority priority) {
        if (priority != null) {
            this.priorityId = priority.getId();
            this.priority = priority;
        }
        return this;
    }

    public TicketType getType() throws KayakoException {
        return this.getType(false);
    }

    public TicketType getType(Boolean refresh) throws KayakoException {
        if ((this.type == null || refresh) && this.typeId > 0) {
            type = TicketType.get(this.typeId);
        }
        return type;
    }

    public Ticket setType(TicketType type) {
        if (type != null) {
            this.typeId = type.getId();
            this.type = type;
        }
        return this;
    }

    /**
     * Returns user, the creator of this ticket.
     * <p/>
     * Result is cached until the end of script.
     */
    public User getUser() throws KayakoException {
        return this.getUser(false);
    }

    public User getUser(Boolean refresh) throws KayakoException {
        if ((refresh || this.user == null) && this.getUserId() > 0) {
            this.user = User.get(this.getUserId());
        }
        return user;
    }

    /**
     * Sets user, the creator of this post.
     */
    public Ticket setUser(User user) throws KayakoException {
        if (user != null) {
            this.setUserId(user.getId());
            this.user = user;
            this.userId = user.getId();
            this.fullName = user.getFullName();
            this.email = user.getEmail();
            this.creator = CREATOR_USER;
            this.staffId = 0;
            this.staff = null;

        }
        return this;
    }

    /**
     * Return organization the user who created the ticket belongs to.
     * <p/>
     * Result is cached until the end of script.
     */
    public UserOrganization getUserOrganization() throws KayakoException {
        return this.getUserOrganization(false);
    }

    public UserOrganization getUserOrganization(Boolean refresh) throws KayakoException {
        if ((userOrganization == null || refresh) && userOrganizationId > 0) {
            userOrganization = UserOrganization.get(this.userOrganizationId);
        }
        return userOrganization;
    }

    public Ticket setUserOrganization(UserOrganization userOrganization) {
        if (userOrganization != null) {
            this.userOrganizationId = userOrganization.getId();
            this.userOrganization = userOrganization;
        }
        return this;
    }

    public Staff getStaff() throws KayakoException {
        return this.getStaff(false);
    }

    public Staff getStaff(Boolean refresh) throws KayakoException {
        if ((this.staff == null || refresh) && this.staffId > 0) {
            staff = Staff.get(this.staffId);
        }
        return staff;
    }

    /**
     * Sets staff user, the creator of this ticket.
     */
    public Ticket setStaff(Staff staff) {
        if (staff != null) {
            this.staffId = staff.getId();
            this.staff = staff;
            this.creator = CREATOR_STAFF;
            this.userId = 0;
            this.user = null;
            this.fullName = staff.getFullName();
            this.email = staff.getEmail();
        }
        return this;
    }

    /**
     * Return staff user, owner of this ticket.
     * <p/>
     * Result is cached until the end of script.
     *
     * @return Staff
     */
    public Staff getOwnerStaff() throws KayakoException {
        return this.getOwnerStaff(false);
    }

    public Staff getOwnerStaff(Boolean refresh) throws KayakoException {
        if ((this.ownerStaff == null || refresh) && this.ownerStaffId > 0) {
            ownerStaff = Staff.get(this.ownerStaffId);
        }
        return ownerStaff;
    }

    /**
     * Sets staff user, owner of this ticket.
     */
    public Ticket setOwnerStaff(Staff ownerStaff) {
        if (ownerStaff != null) {
            this.ownerStaffId = ownerStaff.getId();
            this.ownerStaff = ownerStaff;
        }
        return this;
    }

    public Department getDepartment() throws KayakoException {
        return this.getDepartment(false);
    }

    public Department getDepartment(Boolean refresh) throws KayakoException {
        if ((this.department == null || refresh) && this.departmentId > 0) {
            department = Department.get(this.departmentId);
        }
        return department;
    }

    public Ticket setDepartment(Department department) {
        if (department != null) {
            this.departmentId = department.getId();
            this.department = department;
        }
        return this;
    }

    public ArrayList<TicketNote> getNotes() throws KayakoException {
        return this.getNotes(false);
    }

    public ArrayList<TicketNote> getNotes(Boolean refresh) throws KayakoException {
        if ((this.notes.size() == 0 || refresh)) {
            for (TicketNote note : TicketNote.getAllNotes(this.getId())) {
                notes.add(note);
            }
        }
        return notes;
    }

    public Ticket setNotes(ArrayList<TicketNote> notes) {
        this.notes = notes;
        return this;
    }

    public Ticket addNote(TicketNote ticketNote) {
        this.notes.add(ticketNote);
        return this;
    }

    public ArrayList<TicketTimeTrack> getTimeTracks() throws KayakoException {
        return this.getTimeTracks(false);
    }

    public ArrayList<TicketTimeTrack> getTimeTracks(Boolean refresh) throws KayakoException {
        if ((this.timeTracks.size() == 0 || refresh)) {
            for (TicketTimeTrack timeTrack : TicketTimeTrack.getAllTimeTracks(this.getId())) {
                timeTracks.add(timeTrack);
            }
        }
        return timeTracks;
    }

    public Ticket setTimeTracks(ArrayList<TicketTimeTrack> timeTracks) {
        this.timeTracks = timeTracks;
        return this;
    }

    public ArrayList<TicketPost> getPosts() throws KayakoException {
        return this.getPosts(false);
    }

    public ArrayList<TicketPost> getPosts(Boolean refresh) throws KayakoException {
        if ((this.posts.size() == 0 || refresh)) {
            for (TicketPost post : TicketPost.getAllPosts(this.getId())) {
                posts.add(post);
            }
        }
        return posts;
    }

    public TicketPost getFirstPost() throws KayakoException {
        ArrayList<TicketPost> posts = this.getPosts();
        if (posts.size() == 0) {
            return null;
        }
        return posts.get(0);
    }

    public Ticket setPosts(ArrayList<TicketPost> posts) {
        this.posts = posts;
        return this;
    }

    public Ticket addPost(TicketPost ticketPost) {
        this.posts.add(ticketPost);
        return this;
    }

    public ArrayList<TicketAttachment> getAttachments() throws KayakoException {
        return this.getAttachments(false);
    }

    public ArrayList<TicketAttachment> getAttachments(Boolean refresh) throws KayakoException {
        if ((this.attachments.size() == 0 || refresh)) {
            for (TicketAttachment attachment : TicketAttachment.getAllAttachments(this.getId())) {
                attachments.add(attachment);
            }
        }
        return attachments;
    }

    public Ticket setAttachments(ArrayList<TicketAttachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public static RawArrayElement getStatistics() {
        return statistics;
    }

    public static void setStatistics(RawArrayElement statistics) {
        Ticket.statistics = statistics;
    }

    public static RawArrayElement getAll(Department department) throws KayakoException {
        return Ticket.getAll(department.getId());
    }

    public static RawArrayElement getAll(int departmentId) throws KayakoException {

        ArrayList<Integer> departments = new ArrayList<Integer>();
        departments.add(departmentId);
        return Ticket.getAll(departments);
    }

    public static RawArrayElement getAll(ArrayList<?> departments) throws KayakoException {
        if (departments.isEmpty()) {
            throw new KayakoException();
        }
        return Ticket.getAll(departments, new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>());
    }

    public static RawArrayElement getAll(ArrayList<?> departments, ArrayList<?> ticketStatuses, ArrayList<?> owners, ArrayList<?> users) throws KayakoException {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        RawArrayElement rawArrayElement = new RawArrayElement();
        if (departments.isEmpty()) {
            throw new KayakoException();
        }
        String departmentString = Integer.toString(KEntity.getId(departments.remove(0)));
        String ticketStatusString = "-1";
        String ownerString = "-1";
        String userString = "-1";
        for (Object department : departments) {
            departmentString += "," + Integer.toString(KEntity.getId(department));
        }

        searchParams.add(departmentString);
        if (!ticketStatuses.isEmpty()) {
            ticketStatusString = Integer.toString(KEntity.getId(ticketStatuses.remove(0)));
            for (Object ticketStatus : ticketStatuses) {
                ticketStatusString += "," + Integer.toString(KEntity.getId(ticketStatus));
            }
            searchParams.add(ticketStatusString);
        }

        if (!owners.isEmpty()) {
            ownerString = Integer.toString(KEntity.getId(owners.remove(0)));
            for (Object owner : owners) {
                ownerString += "," + Integer.toString(KEntity.getId(owner));
            }
            searchParams.add(ownerString);
        }

        if (!users.isEmpty()) {
            userString = Integer.toString(KEntity.getId(users.remove(0)));
            for (Object user : users) {
                userString += "," + Integer.toString(KEntity.getId(user));
            }
            searchParams.add(userString);
        }
        return KEntity.getAll(Ticket.controller, searchParams);
    }

    private static ArrayList<Ticket> refineToArray(RawArrayElement rawArrayElement) throws KayakoException {
        ArrayList<Ticket> Tickets = new ArrayList<Ticket>();
        for (RawArrayElement rawArrayElementTicket : rawArrayElement.getComponents()) {
            Tickets.add(new Ticket().populate(rawArrayElementTicket));
        }
        return Tickets;
    }

    public static ArrayList<Ticket> getAllTickets(int departmentId) throws KayakoException {
        return refineToArray(getAll(departmentId));
    }

    public static ArrayList<Ticket> getAllTickets(Department department) throws KayakoException {
        return getAllTickets(department.getId());
    }

    public static Ticket get(int id) throws KayakoException {
        return new Ticket().populate(KEntity.get(controller, id));
    }

    public static ArrayList<Ticket> search(String query, ArrayList<String> areas) throws KayakoException {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("query", query);
        for (String area : areas) {
            data.put(area, "1");
        }
        RawArrayElement rawTickets = KEntity.getRESTClient().post("/Tickets/TicketSearch", new ArrayList<String>(), data);
        return refineToArray(rawTickets);
    }

    public Ticket updateCustomFields() throws KayakoException {
        return (Ticket) super.updateCustomFields(getCustomFieldGroupController());
    }

    /**
     * Sets default status, priority and type for newly created tickets.
     *
     * @param statusId       Default ticket status identifier.
     * @param priorityId     Default ticket priority identifier.
     * @param typeId         Default ticket type identifier.
     * @param autoCreateUser True to automatically create user if none is provided as creator. False otherwise.
     */

    public static void setDefaults(int statusId, int priorityId, int typeId, Boolean autoCreateUser) {
        Ticket.setDefaultStatusId(statusId);
        Ticket.setDefaultPriorityId(priorityId);
        Ticket.setDefaultTypeId(typeId);
        Ticket.setAutoCreateUser(autoCreateUser);
    }

    public TicketPost createTicketPost(User creator, String contents) {
        return TicketPost.createNew(this, contents, creator);
    }

    public TicketPost createTicketPost(Staff creator, String contents) {
        return TicketPost.createNew(this, contents, creator);
    }

    public TicketNote createTicketNote(Staff creator, String contents) {
        return new TicketNote(this, creator, contents);
    }

    public TicketTimeTrack createTicketTimeTrack(String contents, Staff staff, String timeWorked, String timeBillable) {
        return new TicketTimeTrack(this, contents, staff, timeWorked, timeWorked);
    }

    //TODO - Statistics

    @Override
    public Ticket populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        ArrayList<RawArrayElement> components = rawArrayElement.getComponents();

        this.setId(Helper.parseInt(rawArrayElement.getAttribute("id")));
        this.setFlagType(Helper.parseInt(rawArrayElement.getAttribute("flagtype")));
        for (RawArrayElement component : components) {
            String elementName = component.getElementName();
            if (!component.isComposite() && component.getContent() == null) {
                break;
            }
            if (elementName.equals("displayid")) {
                this.setDisplayId(component.getContent());
            } else if (elementName.equals("departmentid")) {
                this.setDepartmentId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("statusid")) {
                this.setStatusId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("priorityid")) {
                this.setPriorityId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("typeid")) {
                this.setTypeId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("userid")) {
                this.setUserId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("userorganization")) {
                this.setUserOrganizationName(component.getContent());
            } else if (elementName.equals("userorganizationid")) {
                this.setUserOrganizationId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("ownerstaffid")) {
                this.setOwnerStaffId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("ownerstaffname")) {
                this.setOwnerStaffName(component.getContent());
            } else if (elementName.equals("fullname")) {
                this.setFullName(component.getContent());
            } else if (elementName.equals("email")) {
                this.setEmail(component.getContent());
            } else if (elementName.equals("lastreplier")) {
                this.setLastReplier(component.getContent());
            } else if (elementName.equals("subject")) {
                this.setSubject(component.getContent());
            } else if (elementName.equals("creationtime")) {
                this.setCreationTime(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("lastactivity")) {
                this.setLastActivity(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("laststaffreply")) {
                this.setLastStaffReply(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("lastuserreply")) {
                this.setLastUserReply(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("slaplanid")) {
                this.setSLAPlanId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("nextreplydue")) {
                this.setNextReplyDue(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("resolutiondue")) {
                this.setResolutionDue(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("replies")) {
                this.setReplies(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("ipaddress")) {
                this.setIpAddress(component.getContent());
            } else if (elementName.equals("creator")) {
                this.setCreator(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("creationmode")) {
                this.setCreationMode(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("creationtype")) {
                this.setCreationType(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("isescalated")) {
                this.setEscalated(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("escalationruleid")) {
                this.setEscalationRuleId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("templategroupid")) {
                this.setTemplateGroupId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("templategroupname")) {
                this.setTemplateGroupName(component.getContent());
            } else if (elementName.equals("tags")) {
                this.setTags(component.getContent());
            } else if (elementName.equals("watcher")) {
                this.addWatcher(Helper.parseInt(component.getAttribute("staffid")), component.getAttribute("name"));
            } else if (elementName.equals("workflow")) {
                this.addWorkflow(Helper.parseInt(component.getAttribute("id")), component.getAttribute("title"));
            } else if (elementName.equals("note")) {
                TicketNote ticketNote = new TicketNote();
                ticketNote.populate(component);
                this.addNote(ticketNote);
            } else if (elementName.equals("posts")) {
                for (RawArrayElement postRaw : component.getComponents()) {
                    this.addPost(new TicketPost().populate(postRaw));
                }
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> ticketHashMap = buildHashMap();
        return buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newTicket) {
        HashMap<String, String> ticketHashMap = new HashMap<String, String>();
        ticketHashMap.put("subject", this.getSubject());
        ticketHashMap.put("fullname", this.getFullName());
        ticketHashMap.put("email", this.getEmail());

        ticketHashMap.put("departmentid", Integer.toString(this.getDepartmentId()));
        ticketHashMap.put("ticketstatusid", Integer.toString(this.getStaffId()));
        ticketHashMap.put("ticketpriorityid", Integer.toString(this.getPriorityId()));
        ticketHashMap.put("tickettypeid", Integer.toString(this.getTypeId()));
        if (this.getOwnerStaffId() > 0) {
            ticketHashMap.put("ownerstaffid", Integer.toString(this.getOwnerStaffId()));
        }
        if (newTicket) {
            switch (this.getCreator()) {
                case CREATOR_STAFF:
                    ticketHashMap.put("staffid", Integer.toString(this.getStaffId()));
                    break;
                case CREATOR_USER:
                    ticketHashMap.put("userid", Integer.toString(this.getUserId()));
                    break;
                case CREATOR_AUTO:
                    ticketHashMap.put("autouserid", Integer.toString(1));
                    break;
            }
            ticketHashMap.put("contents", this.getContents());
            ticketHashMap.put("type", Integer.toString(this.getCreationType()));
            ticketHashMap.put("ignoreautoresponder", this.getIgnoreAutoResponder() ? "1" : "0");

        } else {
            ticketHashMap.put("userid", Integer.toString(this.getUserId()));
        }

        return ticketHashMap;
    }
}
