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

    public TicketNote() {
    }

    public TicketNote(Ticket ticket, Staff creatorStaff, String contents) {
        this.setTicket(ticket).setCreatorStaff(creatorStaff).setContents(contents);
    }

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
        if (this.getType().equals(TYPE_TICKET)) {
            return ticketId;
        } else {
            return 0;
        }
    }

    public TicketNote setTicketId(int ticketId) {
        this.ticketId = ticketId;
        this.ticket = null;
        if (ticketId > 0) {
            this.type = TYPE_TICKET;
        }
        return this;
    }

    public int getUserId() {
        if (!this.getType().equals(TYPE_USER)) {
            return 0;
        }
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

    public User getUser() throws KayakoException {
        return this.getUser(false);
    }

    public User getUser(Boolean refresh) throws KayakoException {
        if (!this.getType().equals(TYPE_USER)) {
            return null;
        }
        if ((this.user == null || refresh) && this.getUserId() > 0) {
            this.user = User.get(this.getUserId());
        }
        return user;
    }

    public TicketNote setUser(User user) {
        this.user = user;
        this.userId = user.getId();
        this.type = TYPE_USER;
        return this;
    }

    public Ticket getTicket() throws KayakoException {
        return this.getTicket(false);
    }

    public Ticket getTicket(Boolean refresh) throws KayakoException {
        if (!this.getType().equals(TYPE_TICKET)) {
            return null;
        }
        if ((this.ticket == null || refresh) && this.getTicketId() > 0) {
            this.ticket = Ticket.get(this.getTicketId());
        }
        return ticket;
    }

    public TicketNote setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.ticketId = ticket.getId();
        this.type = TYPE_TICKET;
        return this;
    }

    public int getUserOrganizationId() {
        if (!this.getType().equals(TYPE_USER_ORGANIZATION)) {
            return 0;
        }
        return userOrganizationId;
    }

    public TicketNote setUserOrganizationId(int userOrganizationId) {
        this.userOrganizationId = userOrganizationId;
        this.userOrganization = null;
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
        this.forStaff = null;
        return this;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public TicketNote setCreationDate(int creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Staff getCreatorStaff() throws KayakoException {
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
        this.creatorStaffId = creatorStaff.getId();
        this.setCreatorStaffName(creatorStaff.getFullName());
        return this;
    }

    public TicketNote setCreator(Staff staff) {
        return this.setCreatorStaff(staff);
    }

    public TicketNote setCreator(int id) {
        return this.setCreatorStaffId(id);
    }

    public TicketNote setCreator(String creatorString) {
        this.creatorStaff = null;
        this.creatorStaffId = 0;
        this.creatorStaffName = creatorString;
        return this;
    }

    /**
     * Returns the staff who this note is for.
     * <p/>
     * Result is cached.
     *
     * @return Staff
     */
    public Staff getForStaff() throws KayakoException {
        return this.getForStaff(false);
    }

    public Staff getForStaff(Boolean refresh) throws KayakoException {
        if (this.forStaff == null || refresh) {
            this.forStaff = (Staff) Staff.get(this.getForStaffId());
        }
        return forStaff;
    }

    public TicketNote setForStaff(Staff forStaff) {
        this.forStaff = forStaff;
        this.forStaffId = forStaff.getId();
        return this;
    }

    public UserOrganization getUserOrganization() throws KayakoException {
        return this.getUserOrganization(false);
    }

    public UserOrganization getUserOrganization(Boolean refresh) throws KayakoException {
        if ((userOrganization == null || refresh) && userOrganizationId > 0) {
            userOrganization = UserOrganization.get(this.userOrganizationId);
        }
        return userOrganization;
    }

    public TicketNote setUserOrganization(UserOrganization userOrganization) {
        this.userOrganization = userOrganization;
        this.userOrganizationId = userOrganization.getId();
        return this;
    }

    public static RawArrayElement getAll(int ticketId) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        searchParams.add(Integer.toString(ticketId));
        return KEntity.getAll(controller, searchParams);
    }

    private static ArrayList<TicketNote> refineToArray(RawArrayElement rawArrayElement) throws KayakoException {
        ArrayList<TicketNote> TicketNotes = new ArrayList<TicketNote>();
        for (RawArrayElement rawArrayElementTicketNote : rawArrayElement.getComponents()) {
            TicketNotes.add(new TicketNote().populate(rawArrayElementTicketNote));
        }
        return TicketNotes;
    }

    public static ArrayList<TicketNote> getAllNotes(int ticketId) throws KayakoException {
        return refineToArray(getAll(ticketId));
    }

    public static TicketNote get(int ticketId, int id) throws KayakoException {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(ticketId));
        arrayList.add(Integer.toString(id));
        return new TicketNote().populate(KEntity.get(controller, arrayList));
    }

    @Override
    public KEntity update(String controller) throws KayakoException {
        throw new KayakoException("This method is not available on this type of objects.");
    }

    public TicketNote create() throws KayakoException {
        if (!this.getType().equals(TYPE_TICKET)) {
            throw new KayakoException("Ticket note creation is for type ticket only.");
        }
        return (TicketNote) super.create(controller);
    }

    public Boolean delete() throws KayakoException {
        return KEntity.getRESTClient().delete(controller, this.getIdArray()) != null;
    }

    public ArrayList<String> getIdArray() {
        ArrayList<String> arrayList = new ArrayList<String>();

        if (this.getType().equals(TYPE_TICKET)) {
            arrayList.add(Integer.toString(this.getTicketId()));
        } else if (this.getType().equals(TYPE_USER)) {
            arrayList.add(Integer.toString(this.getUserId()));
        } else if (this.getType().equals(TYPE_USER_ORGANIZATION)) {
            arrayList.add(Integer.toString(this.getUserOrganizationId()));
        }

        arrayList.add(Integer.toString(this.getId()));
        return arrayList;
    }

    //this function will populate the data of the ticket note instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketNote populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }
        this.setType(rawArrayElement.getAttribute("type")).setId(Helper.parseInt(rawArrayElement.getAttribute("id")));
        if (this.getType().equals(TYPE_TICKET)) {
            this.setTicketId(Helper.parseInt(rawArrayElement.getAttribute("ticketid")));
        } else if (this.getType().equals(TYPE_USER)) {
            this.setUserId(Helper.parseInt(rawArrayElement.getAttribute("userid")));
        } else if (this.getType().equals(TYPE_USER_ORGANIZATION)) {
            this.setUserOrganizationId(Helper.parseInt(rawArrayElement.getAttribute("userorganizationid")));
        }
        this.setNoteColor(Helper.parseInt(rawArrayElement.getAttribute("notecolor")));
        this.setCreatorStaffId(Helper.parseInt(rawArrayElement.getAttribute("creatorstaffid")));
        this.setForStaffId(Helper.parseInt(rawArrayElement.getAttribute("forstaffid")));
        this.setCreatorStaffName(rawArrayElement.getAttribute("creatorstaffname"));
        this.setCreationDate(Helper.parseInt(rawArrayElement.getAttribute("creationdate")));
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

}
