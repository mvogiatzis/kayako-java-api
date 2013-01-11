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
public class TicketPost extends KEntity {

    /**
     * Post creator type - staff user.
     */
    static final int CREATOR_STAFF = 1;

    /**
     * Post creator type - user.
     *
     * @param int
     */
    static final int CREATOR_USER = 2;

    /**
     * Post creator type - user.
     *
     * @param int
     */
    static final int CREATOR_CLIENT = 2;

    /**
     * Post creator type - owner of e-mail marked as CC in ticket properties.
     *
     * @param int
     */
    static final int CREATOR_CC = 3;

    /**
     * Post creator type - owner of e-mail marked as BCC in ticket properties.
     *
     * @param int
     */
    static final int CREATOR_BCC = 4;

    /**
     * Post creator type - owner of e-mail marked as Third Party in ticket properties.
     *
     * @param int
     */
    static final int CREATOR_THIRDPARTY = 5;

    static protected String controller = "/Tickets/TicketPost";
    static protected String objectXmlName = "post";

    /**
     * Ticket post identifier.
     *
     * @param int
     */
    protected int id;
    /**
     * Ticket identifier.
     * <p/>
     * required_create=true
     *
     * @param int
     */
    protected int ticketId;

    /**
     * Timestamp of creation date and time.
     *
     * @param int
     */
    protected int dateLine;

    /**
     * Identifier of the user who created this post.
     * <p/>
     * Applicable if the post was created by a known user through an email queue or through the web interface.
     *
     * @param int
     */
    protected int userId = 0;

    /**
     * The full name of the person who created the ticket post.
     *
     * @param String
     */
    protected String fullName;

    /**
     * The email address of the person who created the ticket post.
     *
     * @param string
     */
    protected String email;

    /**
     * The email address of the user associated with the ticket.
     * <p/>
     * Applicable when the 'send email' option is used by the a staff user when creating the ticket post.
     *
     * @param string
     */
    protected String emailTo;

    /**
     * IP address from which this post was created.
     *
     * @param string
     */
    protected String IPAddress;

    /**
     * Whether this ticket post has attachments.
     *
     * @param bool
     */
    protected Boolean hasAttachment;

    /**
     * Type of this ticket post creator.
     * <p/>
     * getter=getCreatorType
     *
     * @param int
     * @see TicketPost::CREATOR constants.
     */
    protected int creator;

    /**
     * Whether this post was created by owner of e-mail marked as Third Party in ticket properties.
     *
     * @param bool
     */
    protected Boolean isThirdParty;

    /**
     * Whether this ticket post contains HTML data.
     *
     * @param bool
     */
    protected Boolean isHTML;

    /**
     * Whether this post was created through an email queue.
     *
     * @param bool
     */
    protected Boolean isEmailed;

    /**
     * Staff user identifier.
     * <p/>
     * Applicable if the post was created by staff user.
     *
     * @param int
     */
    protected int staffId = 0;

    /**
     * Whether this post is a survey comment.
     *
     * @param bool
     */
    protected Boolean isSurveyComment;

    /**
     * Ticket post contents.
     * <p/>
     * required_create=true
     *
     * @param string
     */
    protected String contents;

    /**
     * The subject this ticket post.
     * <p/>
     * If the ticket post was created through an e-mail queue this is subject of the email message that resulted in the creation of the post.
     *
     * @param string
     */
    protected String subject = "";

    /**
     * Whether the ticket post should be created as private (hidden from the customer) or not.
     *
     * @param bool
     */
    protected Boolean isPrivate = false;

    /**
     * Ticket post attachments.
     *
     * @param TicketAttachment[]
     */
    private ArrayList<TicketAttachment> attachments = new ArrayList<TicketAttachment>();

    /**
     * User, the creator of this post.
     *
     * @param User
     */
    private User user = null;

    /**
     * Staff user, the creator of this post.
     * <p/>
     * Applicable if the post was created by staff user.
     *
     * @param Staff
     */
    private Staff staff = null;

    /**
     * Ticket that this post is connected to.
     *
     * @param Ticket
     */
    private Ticket ticket = null;

    @Override
    public ArrayList<String> getIdArray() {
        ArrayList<String> ids = new ArrayList<String>();
        ids.add(Integer.toString(this.getTicketId()));
        ids.add(Integer.toString(this.getId()));
        return ids;
    }

    public int getId() {

        return id;
    }

    public TicketPost setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Boolean getReadOnly() {

        return readOnly;
    }

    @Override
    public TicketPost setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        TicketPost.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        TicketPost.controller = controller;
    }

    public int getTicketId() {
        return ticketId;
    }

    public TicketPost setTicketId(int ticketId) {
        this.ticketId = ticketId;
        this.ticket = null;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public TicketPost setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public TicketPost setUserId(int userId) {
        this.userId = userId;
        if (userId > 0) {
            this.creator = CREATOR_USER;
            this.staffId = 0;
            this.staff = null;
            return this;
        }
        this.user = null;
        return this;

    }

    public int getDateLine() {
        return dateLine;
    }

    public TicketPost setDateLine(int dateLine) {
        this.dateLine = dateLine;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public TicketPost setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public TicketPost setEmailTo(String emailTo) {
        this.emailTo = emailTo;
        return this;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public TicketPost setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
        return this;
    }

    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    public TicketPost setHasAttachment(Boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
        return this;
    }

    public int getCreator() {
        return creator;
    }

    public TicketPost setCreator(int creatorId, int type) {
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

    public TicketPost setCreator(int creator) {
        this.creator = creator;
        return this;
    }

    public TicketPost setCreator(Staff creator) {
        this.setStaff(creator);
        return this;
    }

    public TicketPost setCreator(User creator) {
        this.setUser(creator);
        return this;
    }

    public Boolean getHTML() {
        return isHTML;
    }

    public TicketPost setHTML(Boolean HTML) {
        isHTML = HTML;
        return this;
    }

    public Boolean getThirdParty() {
        return isThirdParty;
    }

    public TicketPost setThirdParty(Boolean thirdParty) {
        isThirdParty = thirdParty;
        return this;
    }

    public Boolean getEmailed() {
        return isEmailed;
    }

    public TicketPost setEmailed(Boolean emailed) {
        isEmailed = emailed;
        return this;
    }

    public int getStaffId() {
        return staffId;
    }

    public TicketPost setStaffId(int staffId) {
        this.staffId = staffId;
        if (staffId > 0) {
            this.creator = CREATOR_STAFF;
            this.user = null;
            this.userId = 0;
            return this;
        }
        this.staff = null;
        return this;
    }

    public Boolean getSurveyComment() {
        return isSurveyComment;
    }

    public TicketPost setSurveyComment(Boolean surveyComment) {
        isSurveyComment = surveyComment;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public TicketPost setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public TicketPost setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Boolean isPrivate() {
        return isPrivate;
    }

    public TicketPost setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
        return this;
    }

    public ArrayList<TicketAttachment> getAttachments() {
        return attachments;
    }

    public TicketPost setAttachments(ArrayList<TicketAttachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public User getUser() throws KayakoException {
        return this.getUser(false);
    }

    public User getUser(Boolean refresh) throws KayakoException {
        if((refresh || this.user == null) && this.getUserId() > 0){
            this.user = User.get(this.getUserId());
        }
        return user;
    }
    public TicketPost setUser(User user) {
        this.user = user;
        this.staff = null;
        this.setUserId(user.getId());
        this.staffId = 0;
        return this;
    }

    public Staff getStaff() {
        return staff;
    }

    public TicketPost setStaff(Staff staff) {
        this.staff = staff;
        this.setStaffId(staff.getId());
        this.userId = 0;
        this.user = null;
        return this;
    }

    public RawArrayElement get(int ticketId, int id) throws KayakoException {
        ArrayList<String> params = new ArrayList<String>();
        params.add(Integer.toString(ticketId));
        params.add(Integer.toString(id));
        return KEntity.get(controller, params);
    }

    public TicketPost update() throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    public Boolean delete() throws KayakoException {
        return super.delete(controller);
    }

    public Ticket getTicket() throws KayakoException {
        return this.getTicket(false);
    }

    public Ticket getTicket(Boolean refresh) throws KayakoException {
        if (this.ticket == null || refresh) {
            if (this.getTicketId() == 0) {
                return null;
            }
            this.ticket = new Ticket().populate(Ticket.get(Ticket.getController(), this.getTicketId()));
        }
        return ticket;
    }

    public TicketPost setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.setTicketId(ticket.getId());
        return this;
    }

    public static RawArrayElement getAll(int ticketId) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        searchParams.add(Integer.toString(ticketId));
        return KEntity.getAll(controller, searchParams);
    }

    public static TicketPost createNew(Ticket ticket, String contents, Staff creator) {
        TicketPost ticketPost = new TicketPost();
        ticketPost.setTicket(ticket);
        ticketPost.setContents(contents);
        ticketPost.setCreator(creator);
        return ticketPost;
    }

    public static TicketPost createNew(Ticket ticket, String contents, User creator) {
        TicketPost ticketPost = new TicketPost();
        ticketPost.setTicket(ticket);
        ticketPost.setContents(contents);
        ticketPost.setCreator(creator);
        return ticketPost;
    }

    //this function will populate the data of the ticket post instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketPost populate(RawArrayElement rawArrayElement) throws KayakoException {
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
            } else if (elementName.equals("ticketid")) {
                this.setTicketId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("dateline")) {
                this.setDateLine(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("userid")) {
                this.setUserId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("fullname")) {
                this.setFullName(component.getContent());
            } else if (elementName.equals("email")) {
                this.setEmail(component.getContent());
            } else if (elementName.equals("emailto")) {
                this.setEmailTo(component.getContent());
            } else if (elementName.equals("ipaddress")) {
                this.setIPAddress(component.getContent());
            } else if (elementName.equals("hasattachment")) {
                this.setHasAttachment(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("creator")) {
                this.setCreator(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("isthirdparty")) {
                this.setThirdParty(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("ishtml")) {
                this.setHTML(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("isemailed")) {
                this.setEmailed(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("staffid")) {
                this.setStaffId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("issurveycomment")) {
                this.setSurveyComment(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("contents")) {
                this.setContents(component.getContent());
            } else if (elementName.equals("isprivate")) {
                this.setPrivate(Helper.parseInt(component.getContent()) == 1);
            }

        }

        return this;
    }

    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newTicketPost) {
        HashMap<String, String> ticketPostHashMap = new HashMap<String, String>();
        ticketPostHashMap.put("ticketid", Integer.toString(this.getTicketId()));
        ticketPostHashMap.put("subject", this.getSubject());
        ticketPostHashMap.put("contents", this.getContents());
        ticketPostHashMap.put("isprivate", this.isPrivate() ? "1" : "0");

        switch (this.getCreator()) {
            case CREATOR_STAFF:
                ticketPostHashMap.put("staffid", Integer.toString(this.getStaffId()));
                break;
            case CREATOR_USER:
                ticketPostHashMap.put("userid", Integer.toString(this.getUserId()));
                break;
        }

        return ticketPostHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    //TODO a lot of functions like getAll etc, plus creating new attachments from here...
}
