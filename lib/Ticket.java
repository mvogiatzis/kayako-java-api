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
    static final String SEARCH_TICKET_ID = "ticketid";
    /**
     * Flag for searching using query - search the Ticket Post Contents.
     *
     * @var string
     */
    static final String SEARCH_CONTENTS = "contents";
    /**
     * Flag for searching using query - search the Full Name & Email.
     *
     * @var string
     */
    static final String SEARCH_AUTHOR = "author";
    /**
     * Flag for searching using query - search the Email Address (Ticket & Posts).
     *
     * @var string
     */
    static final String SEARCH_EMAIL = "email";
    /**
     * Flag for searching using query - search the Email Address (only Tickets).
     *
     * @var string
     */
    static final String SEARCH_CREATOR_EMAIL = "creatoremail";
    /**
     * Flag for searching using query - search the Full Name.
     *
     * @var string
     */
    static final String SEARCH_FULL_NAME = "fullname";
    /**
     * Flag for searching using query - search the Ticket Notes.
     *
     * @var string
     */
    static final String SEARCH_NOTES = "notes";
    /**
     * Flag for searching using query - search the User Group.
     *
     * @var string
     */
    static final String SEARCH_USER_GROUP = "usergroup";
    /**
     * Flag for searching using query - search the User Organization.
     *
     * @var string
     */
    static final String SEARCH_USER_ORGANIZATION = "userorganization";
    /**
     * Flag for searching using query - search the User (Full Name, Email).
     *
     * @var string
     */
    static final String SEARCH_USER = "user";
    /**
     * Flag for searching using query - search the Ticket Tags.
     *
     * @var string
     */
    static final String SEARCH_TAGS = "tags";
    static protected String controller = "/Tickets/Ticket";
    static protected String objectXmlName = "ticket";
    static protected String customFieldGroupClass = "TicketCustomFieldGroup";
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
    protected HashMap<Integer, String> watchers;
    /**
     * Ticket workflows.
     *
     * @apiField
     * @var HashMap <id, title></>
     */
    protected HashMap<Integer, String> workflows;
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

    public Ticket(Department department, String contents, String subject) {
        this.setDepartment(department);
        this.setContents(contents).setSubject(subject);
        this.setTypeId(getDefaultTypeId()).setPriorityId(getDefaultPriorityId()).setStatusId(getDefaultStatusId());

    }

    public Ticket(Department department, int creator, String contents, String subject) {
        this(department, contents, subject);
        this.setCreator(creator);
    }

    public Ticket(Department department, String creatorFullName, String creatorEmail, String contents, String subject) {
        this(department, contents, subject);
        this.setCreatorAuto(creatorFullName, creatorEmail);
    }


    public Ticket setCreatorId(int creatorId, int creatorType) {
        switch (creatorType) {
            case CREATOR_STAFF:
                this.setStaffId(creatorId);
                break;
            case CREATOR_USER:
                this.setUserId(creatorId);
        }
        return this;
    }

    public Ticket setCreator(User user) {
        this.setUser(user);
        return this;
    }

    public Ticket setCreator(Staff staff) {
        this.setStaff(staff);
        return this;
    }

    public Ticket setCreatorAuto(String fullName, String email) {
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

    public static String getCustomFieldGroupClass() {
        return customFieldGroupClass;
    }

    public static void setCustomFieldGroupClass(String customFieldGroupClass) {
        Ticket.customFieldGroupClass = customFieldGroupClass;
    }

    public static String getObjectIdField() {
        return objectIdField;
    }

    public static void setObjectIdField(String objectIdField) {
        Ticket.objectIdField = objectIdField;
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
        return this;
    }

    public int getStatusId() {
        return statusId;
    }

    public Ticket setStatusId(int statusId) {
        this.statusId = statusId;
        return this;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public Ticket setPriorityId(int priorityId) {
        this.priorityId = priorityId;
        return this;
    }

    public int getTypeId() {
        return typeId;
    }

    public Ticket setTypeId(int typeId) {
        this.typeId = typeId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Ticket setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserOrganizationName() {
        return userOrganizationName;
    }

    public Ticket setUserOrganizationName(String userOrganizationName) {
        this.userOrganizationName = userOrganizationName;
        return this;
    }

    public int getUserOrganizationId() {
        return userOrganizationId;
    }

    public Ticket setUserOrganizationId(int userOrganizationId) {
        this.userOrganizationId = userOrganizationId;
        return this;
    }

    public int getOwnerStaffId() {
        return ownerStaffId;
    }

    public Ticket setOwnerStaffId(int ownerStaffId) {
        this.ownerStaffId = ownerStaffId;
        return this;
    }

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
        return creator;
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
        return this;
    }

    public String getTemplateGroupName() {
        return templateGroupName;
    }

    public Ticket setTemplateGroupName(String templateGroupName) {
        this.templateGroupName = templateGroupName;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Ticket setTags(String tags) {
        this.tags = tags;
        return this;
    }

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

    public Ticket setStaffId(int staffId) {
        this.staffId = staffId;
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

    public TicketStatus getStatus() {
        return status;
    }

    public Ticket setStatus(TicketStatus status) {
        this.status = status;
        return this;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public Ticket setPriority(TicketPriority priority) {
        this.priority = priority;
        return this;
    }

    public TicketType getType() {
        return type;
    }

    public Ticket setType(TicketType type) {
        this.type = type;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Ticket setUser(User user) {
        if (user != null) {
            this.setUserId(user.getId());
            this.user = user;
        }
        return this;
    }

    public UserOrganization getUserOrganization() {
        return userOrganization;
    }

    public Ticket setUserOrganization(UserOrganization userOrganization) {
        if (userOrganization != null) {
            this.setUserOrganizationId(userOrganization.getId());
            this.userOrganization = userOrganization;
        }
        return this;
    }

    public Staff getStaff() {
        return staff;
    }

    public Ticket setStaff(Staff staff) {
        if (staff != null) {
            this.setStaffId(staff.getId());
            this.staff = staff;
        }
        return this;
    }

    public Staff getOwnerStaff() {
        return ownerStaff;
    }

    public Ticket setOwnerStaff(Staff ownerStaff) {
        if (ownerStaff != null) {
            this.setOwnerStaffId(ownerStaff.getId());
            this.ownerStaff = ownerStaff;
        }
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public Ticket setDepartment(Department department) {
        if (department != null) {
            this.setDepartmentId(department.getId());
            this.department = department;
        }
        return this;
    }

    public ArrayList<TicketNote> getNotes() {
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

    public ArrayList<TicketTimeTrack> getTimeTracks() {
        return timeTracks;
    }

    public Ticket setTimeTracks(ArrayList<TicketTimeTrack> timeTracks) {
        this.timeTracks = timeTracks;
        return this;
    }

    public ArrayList<TicketPost> getPosts() {
        return posts;
    }

    public Ticket setPosts(ArrayList<TicketPost> posts) {
        this.posts = posts;
        return this;
    }

    public Ticket addPost(TicketPost ticketPost) {
        this.posts.add(ticketPost);
        return this;
    }

    public ArrayList<TicketAttachment> getAttachments() {
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
        String ticketStatusString = "";
        String ownerString = "";
        String userString = "";
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
        ticketHashMap.put("email", this.getEmail());
        ticketHashMap.put("contents", this.getContents());
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
            ticketHashMap.put("content", this.getContents());
            ticketHashMap.put("type", Integer.toString(this.getCreationType()));
            ticketHashMap.put("ignoreautoresponder", this.getIgnoreAutoResponder() ? "1" : "0");

        } else {
            ticketHashMap.put("userid", Integer.toString(this.getUserId()));
        }

        return ticketHashMap;
    }
}
