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
public class TicketNote extends KEntity {

    /**
     * Color of note - yellow.
     *
     * @var int
     */
    static final int COLOR_YELLOW = 1;

    /**
     * Color of note - purple.
     *
     * @var int
     */
    static final int COLOR_PURPLE = 2;

    /**
     * Color of note - blue.
     *
     * @var int
     */
    static final int COLOR_BLUE = 3;

    /**
     * Color of note - green.
     *
     * @var int
     */
    static final int COLOR_GREEN = 4;

    /**
     * Color of note - red.
     *
     * @var int
     */
    static final int COLOR_RED = 5;

    /**
     * Note type - connected to ticket.
     *
     * @var string
     */
    static final String TYPE_TICKET = "ticket";

    /**
     * Note type - connected to user.
     *
     * @var string
     */
    static final String TYPE_USER = "user";

    /**
     * Note type - connected to user organization.
     *
     * @var string
     */
    static final String TYPE_USER_ORGANIZATION = "userorganization";

    static protected String controller = "/Tickets/TicketNote";
    static protected String objectXmlName = "note";

    /**
     * Ticket note identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Ticket identifier - if this note is associated with ticket.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int ticketId;

    /**
     * User identifier - if this note is associated with user who created the ticket.
     *
     * @apiField
     * @var int
     */
    protected int userId;

    /**
     * User organization identifier - if this note is associated with user organization of user who created the ticket.
     *
     * @apiField
     * @var int
     */
    protected int userOrganizationId;

    /**
     * Note type.
     *
     * @apiField
     * @var string
     * @see TicketNote::TYPE static final Stringants.
     */
    protected String type = TYPE_TICKET;

    /**
     * Ticket note color.
     *
     * @apiField
     * @var int
     * @see TicketNote::COLOR static final Stringants.
     */
    protected int noteColor;

    /**
     * Identifier of staff user who created this note.
     *
     * @apiField alias=staffid
     * @var int
     */
    protected int creatorStaffId = 0;

    /**
     * Full name of staff user who created this note.
     *
     * @apiField getter=getCreatorName setter=setCreator alias=fullname
     * @var string
     */
    protected String creatorStaffName;

    /**
     * Identifier staff user that this note is intended for.
     *
     * @apiField
     * @var int
     */
    protected int forStaffId;

    /**
     * Timestamp of when this ticket note was created.
     *
     * @apiField
     * @var int
     */
    protected int creationDate;

    /**
     * Ticket note contents.
     *
     * @apiField required_create=true
     * @var string
     */
    protected String contents;

    /**
     * Staff user who created this note.
     *
     * @var Staff
     */
    private Staff creatorStaff = null;

    /**
     * Staff user that this note is intended for.
     *
     * @var Staff
     */
    private Staff forStaff = null;

    /**
     * Ticket - if this note is associated with ticket.
     *
     * @var Ticket
     */
    private Ticket ticket = null;

    /**
     * User - if this note is associated with user who created the ticket.
     *
     * @var User
     */
    private User user = null;

    /**
     * User organization - if this note is associated with user organization of user who created the ticket.
     *
     * @var UserOrganization
     */
    private UserOrganization userOrganization = null;

    public int getId() {
        return id;
    }

    public TicketNote setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Boolean getReadOnly() {

        return readOnly;
    }

    @Override
    public TicketNote setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        TicketNote.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        TicketNote.controller = controller;
    }

    public int getTicketId() {
        return ticketId;
    }

    public TicketNote setTicketId(int ticketId) {
        this.ticketId = ticketId;
        return this;
    }


    public int getUserId() {
        return userId;
    }

    public TicketNote setUserId(int userId) {
        this.userId = userId;
        return this;
    }


    public String getContents() {
        return contents;
    }

    public TicketNote setContents(String contents) {
        this.contents = contents;
        return this;
    }


    public User getUser() {
        return user;
    }

    public TicketNote setUser(User user) {
        this.user = user;
        return this;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public TicketNote setTicket(Ticket ticket) {
        this.ticket = ticket;
        return this;
    }

    public int getUserOrganizationId() {
        return userOrganizationId;
    }

    public TicketNote setUserOrganizationId(int userOrganizationId) {
        this.userOrganizationId = userOrganizationId;
        return this;
    }

    public String getType() {
        return type;
    }

    public TicketNote setType(String type) {
        this.type = type;
        return this;
    }

    public int getNoteColor() {
        return noteColor;
    }

    public TicketNote setNoteColor(int noteColor) {
        this.noteColor = noteColor;
        return this;
    }

    public int getCreatorStaffId() {
        return creatorStaffId;
    }

    public TicketNote setCreatorStaffId(int creatorStaffId) {
        this.creatorStaffId = creatorStaffId;
        return this;
    }

    public String getCreatorStaffName() {
        return creatorStaffName;
    }

    public TicketNote setCreatorStaffName(String creatorStaffName) {
        this.creatorStaffName = creatorStaffName;
        return this;
    }

    public int getForStaffId() {
        return forStaffId;
    }

    public TicketNote setForStaffId(int forStaffId) {
        this.forStaffId = forStaffId;
        return this;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public TicketNote setCreationDate(int creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Staff getCreatorStaff() {
        if (this.getCreatorStaffId() == 0) {
            return null;
        }
        if (this.creatorStaff == null) {
            this.creatorStaff = (Staff) Staff.get(this.getCreatorStaffId());
        }
        return this.creatorStaff;
    }

    public TicketNote setCreatorStaff(Staff creatorStaff) {
        this.creatorStaff = creatorStaff;
        return this;
    }

    public Staff getForStaff() {
        if (this.forStaff == null) {
            this.forStaff = (Staff) Staff.get(this.getForStaffId());
        }
        return forStaff;
    }

    public TicketNote setForStaff(Staff forStaff) {
        this.forStaff = forStaff;
        return this;
    }

    public UserOrganization getUserOrganization() {
        return userOrganization;
    }

    public TicketNote setUserOrganization(UserOrganization userOrganization) {
        this.userOrganization = userOrganization;
        return this;
    }


    public static RawArrayElement getAll(int ticketId) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        searchParams.add(Integer.toString(ticketId));
        return KEntity.getAll(controller, searchParams);
    }

    //this function will populate the data of the ticket note instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketNote populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }
        this.setType(rawArrayElement.getAttribute("type")).setId(Integer.parseInt(rawArrayElement.getAttribute("id")));
        if (this.getType().equals(TYPE_TICKET)) {
            this.setTicketId(Integer.parseInt(rawArrayElement.getAttribute("ticketid")));
        } else if (this.getType().equals(TYPE_USER)) {
            this.setUserId(Integer.parseInt(rawArrayElement.getAttribute("userid")));
        } else if (this.getType().equals(TYPE_USER_ORGANIZATION)) {
            this.setUserOrganizationId(Integer.parseInt(rawArrayElement.getAttribute("userorganizationid")));
        }
        this.setNoteColor(Integer.parseInt(rawArrayElement.getAttribute("notecolor"))).setCreatorStaffId(Integer.parseInt(rawArrayElement.getAttribute("creatorstaffid")));
        this.setForStaffId(Integer.parseInt(rawArrayElement.getAttribute("forstaffid"))).setCreatorStaffName(rawArrayElement.getAttribute("creatorstaffname"));
        this.setCreationDate(Integer.parseInt(rawArrayElement.getAttribute("creationdate")));
        this.setContents(rawArrayElement.getContent());
        return this;
    }


    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newTicketNote) {
        HashMap<String, String> ticketNoteHashMap = new HashMap<String, String>();
        ticketNoteHashMap.put("ticketid", Integer.toString(this.getTicketId()));
        ticketNoteHashMap.put("contents", this.getContents());
        if (this.getCreatorStaffId() != 0) {
            ticketNoteHashMap.put("staffid", Integer.toString(this.getCreatorStaffId()));
        } else {
            ticketNoteHashMap.put("fullname", this.getCreatorStaffName());
        }
        ticketNoteHashMap.put("forstaffid", Integer.toString(this.getForStaffId()));
        ticketNoteHashMap.put("notecolor", Integer.toString(this.getNoteColor()));
        return ticketNoteHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    //TODO a lot of functions like getAll etc, plus creating new attachments from here...
}
