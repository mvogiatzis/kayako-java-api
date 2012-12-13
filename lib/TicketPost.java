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
     *
     * @var int
     */
    static final int CREATOR_STAFF = 1;

    /**
     * Post creator type - user.
     *
     * @var int
     */
    static final int CREATOR_USER = 2;

    /**
     * Post creator type - user.
     *
     * @var int
     */
    static final int CREATOR_CLIENT = 2;

    /**
     * Post creator type - owner of e-mail marked as CC in ticket properties.
     *
     * @var int
     */
    static final int CREATOR_CC = 3;

    /**
     * Post creator type - owner of e-mail marked as BCC in ticket properties.
     *
     * @var int
     */
    static final int CREATOR_BCC = 4;

    /**
     * Post creator type - owner of e-mail marked as Third Party in ticket properties.
     *
     * @var int
     */
    static final int CREATOR_THIRDPARTY = 5;

    static protected String controller = "/Tickets/TicketPost";
    static protected String objectXmlName = "post";

    /**
     * Ticket post identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;
    /**
     * Ticket identifier.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int ticketId;

    /**
     * Timestamp of creation date and time.
     *
     * @apiField
     * @var int
     */
    protected int dateLine;

    /**
     * Identifier of the user who created this post.
     * <p/>
     * Applicable if the post was created by a known user through an email queue or through the web interface.
     *
     * @apiField
     * @var int
     */
    protected int userId;

    /**
     * The full name of the person who created the ticket post.
     *
     * @apiField
     * @var String
     */
    protected String fullName;

    /**
     * The email address of the person who created the ticket post.
     *
     * @apiField
     * @var string
     */
    protected String email;

    /**
     * The email address of the user associated with the ticket.
     * <p/>
     * Applicable when the 'send email' option is used by the a staff user when creating the ticket post.
     *
     * @apiField
     * @var string
     */
    protected String emailTo;

    /**
     * IP address from which this post was created.
     *
     * @apiField
     * @var string
     */
    protected String IPAddress;

    /**
     * Whether this ticket post has attachments.
     *
     * @apiField
     * @var bool
     */
    protected Boolean hasAttachment;

    /**
     * Type of this ticket post creator.
     *
     * @apiField getter=getCreatorType
     * @var int
     * @see TicketPost::CREATOR constants.
     */
    protected int creator;

    /**
     * Whether this post was created by owner of e-mail marked as Third Party in ticket properties.
     *
     * @apiField
     * @var bool
     */
    protected Boolean isThirdParty;

    /**
     * Whether this ticket post contains HTML data.
     *
     * @apiField
     * @var bool
     */
    protected Boolean isHTML;

    /**
     * Whether this post was created through an email queue.
     *
     * @apiField
     * @var bool
     */
    protected Boolean isEmailed;

    /**
     * Staff user identifier.
     * <p/>
     * Applicable if the post was created by staff user.
     *
     * @apiField
     * @var int
     */
    protected int staffId;

    /**
     * Whether this post is a survey comment.
     *
     * @apiField
     * @var bool
     */
    protected Boolean isSurveyComment;

    /**
     * Ticket post contents.
     *
     * @apiField required_create=true
     * @var string
     */
    protected String contents;

    /**
     * The subject this ticket post.
     * <p/>
     * If the ticket post was created through an e-mail queue this is subject of the email message that resulted in the creation of the post.
     *
     * @apiField
     * @var string
     */
    protected String subject = "";

    /**
     * Whether the ticket post should be created as private (hidden from the customer) or not.
     *
     * @apiField
     * @var bool
     */
    protected Boolean isPrivate = false;

    /**
     * Ticket post attachments.
     *
     * @var TicketAttachment[]
     */
    private ArrayList<TicketAttachment> attachments = new ArrayList<TicketAttachment>();

    /**
     * User, the creator of this post.
     *
     * @var User
     */
    private User user = null;

    /**
     * Staff user, the creator of this post.
     * <p/>
     * Applicable if the post was created by staff user.
     *
     * @var Staff
     */
    private Staff staff = null;

    /**
     * Ticket that this post is connected to.
     *
     * @var Ticket
     */
    private Ticket ticket = null;

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

    public TicketPost setCreator(int creator) {
        this.creator = creator;
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

    public User getUser() {
        return user;
    }

    public TicketPost setUser(User user) {
        this.user = user;
        return this;
    }

    public Staff getStaff() {
        return staff;
    }

    public TicketPost setStaff(Staff staff) {
        this.staff = staff;
        return this;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public TicketPost setTicket(Ticket ticket) {
        this.ticket = ticket;
        return this;
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
                this.setId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("ticketid")) {
                this.setTicketId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("dateline")) {
                this.setDateLine(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("userid")) {
                this.setUserId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("fullname")) {
                this.setFullName(component.getContent());
            } else if (elementName.equals("email")) {
                this.setEmail(component.getContent());
            } else if (elementName.equals("emailto")) {
                this.setEmailTo(component.getContent());
            } else if (elementName.equals("ipaddress")) {
                this.setIPAddress(component.getContent());
            } else if (elementName.equals("hasattachment")) {
                this.setHasAttachment(Integer.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("creator")) {
                this.setCreator(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("isthirdparty")) {
                this.setThirdParty(Integer.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("ishtml")) {
                this.setHTML(Integer.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("isemailed")) {
                this.setEmailed(Integer.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("staffid")) {
                this.setStaffId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("issurveycomment")) {
                this.setSurveyComment(Integer.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("contents")) {
                this.setContents(component.getContent());
            } else if (elementName.equals("isprivate")) {
                this.setPrivate(Integer.parseInt(component.getContent()) == 1);
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
