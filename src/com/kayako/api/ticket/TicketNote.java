package com.kayako.api.ticket;

import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.staff.Staff;
import com.kayako.api.user.User;
import com.kayako.api.user.UserOrganization;
import com.kayako.api.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Ticket note.
 *
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class TicketNote extends KEntity {

    /**
     * Color of note - yellow.
     *
     * @var int
     */
    public static final int COLOR_YELLOW = 1;

    /**
     * Color of note - purple.
     *
     * @var int
     */
    public static final int COLOR_PURPLE = 2;

    /**
     * Color of note - blue.
     *
     * @var int
     */
    public static final int COLOR_BLUE = 3;

    /**
     * Color of note - green.
     *
     * @var int
     */
    public static final int COLOR_GREEN = 4;

    /**
     * Color of note - red.
     *
     * @var int
     */
    public static final int COLOR_RED = 5;

    /**
     * Note type - connected to ticket.
     *
     * @var string
     */
    public static final String TYPE_TICKET = "ticket";

    /**
     * Note type - connected to user.
     *
     * @var string
     */
    public static final String TYPE_USER = "user";

    /**
     * Note type - connected to user organization.
     *
     * @var string
     */
    public static final String TYPE_USER_ORGANIZATION = "userorganization";

    /**
     * The Controller.
     */
    static protected String controller = "/Tickets/TicketNote";
    /**
     * The Object xml name.
     */
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
     * @apiField required_create =true
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
     * @see  ::TYPE static final Stringants.
     */
    protected String type = TYPE_TICKET;

    /**
     * Ticket note color.
     *
     * @apiField
     * @var int
     * @see  ::COLOR static final Stringants.
     */
    protected int noteColor;

    /**
     * Identifier of staff user who created this note.
     *
     * @apiField alias =staffid
     * @var int
     */
    protected int creatorStaffId = 0;

    /**
     * Full name of staff user who created this note.
     *
     * @apiField getter =getCreatorName setter=setCreator alias=fullname
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
     * @apiField required_create =true
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

    /**
     * Instantiates a new Ticket note.
     */
    public TicketNote() {
    }

    /**
     * Instantiates a new Ticket note.
     *
     * @param ticket the ticket
     * @param creatorStaff the creator staff
     * @param contents the contents
     */
    public TicketNote(Ticket ticket, Staff creatorStaff, String contents) {
        this.setTicket(ticket).setCreatorStaff(creatorStaff).setContents(contents);
    }

    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
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

    /**
     * Gets object xml name.
     *
     * @return the object xml name
     */
    public static String getObjectXmlName() {

        return objectXmlName;
    }

    /**
     * Sets object xml name.
     *
     * @param objectXmlName the object xml name
     */
    public static void setObjectXmlName(String objectXmlName) {
        TicketNote.objectXmlName = objectXmlName;
    }

    /**
     * Gets controller.
     *
     * @return the controller
     */
    public static String getController() {

        return controller;
    }

    /**
     * Sets controller.
     *
     * @param controller the controller
     */
    public static void setController(String controller) {
        TicketNote.controller = controller;
    }

    /**
     * Gets ticket id.
     *
     * @return the ticket id
     */
    public int getTicketId() {
        if (this.getType().equals(TYPE_TICKET)) {
            return ticketId;
        } else {
            return 0;
        }
    }

    /**
     * Sets ticket id.
     *
     * @param ticketId the ticket id
     * @return the ticket id
     */
    public TicketNote setTicketId(int ticketId) {
        this.ticketId = ticketId;
        this.ticket = null;
        if (ticketId > 0) {
            this.type = TYPE_TICKET;
        }
        return this;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        if (!this.getType().equals(TYPE_USER)) {
            return 0;
        }
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     * @return the user id
     */
    public TicketNote setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Gets contents.
     *
     * @return the contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * Sets contents.
     *
     * @param contents the contents
     * @return the contents
     */
    public TicketNote setContents(String contents) {
        this.contents = contents;
        return this;
    }

    /**
     * Gets user.
     *
     * @return the user
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public User getUser() throws KayakoException {
        return this.getUser(false);
    }

    /**
     * Gets user.
     *
     * @param refresh the refresh
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public User getUser(Boolean refresh) throws KayakoException {
        if (!this.getType().equals(TYPE_USER)) {
            return null;
        }
        if ((this.user == null || refresh) && this.getUserId() > 0) {
            this.user = User.get(this.getUserId());
        }
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     * @return the user
     */
    public TicketNote setUser(User user) {
        this.user = user;
        this.userId = user.getId();
        this.type = TYPE_USER;
        return this;
    }

    /**
     * Gets ticket.
     *
     * @return the ticket
     * @throws KayakoException the kayako exception
     */
    public Ticket getTicket() throws KayakoException {
        return this.getTicket(false);
    }

    /**
     * Gets ticket.
     *
     * @param refresh the refresh
     * @return the ticket
     * @throws KayakoException the kayako exception
     */
    public Ticket getTicket(Boolean refresh) throws KayakoException {
        if (!this.getType().equals(TYPE_TICKET)) {
            return null;
        }
        if ((this.ticket == null || refresh) && this.getTicketId() > 0) {
            this.ticket = Ticket.get(this.getTicketId());
        }
        return ticket;
    }

    /**
     * Sets ticket.
     *
     * @param ticket the ticket
     * @return the ticket
     */
    public TicketNote setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.ticketId = ticket.getId();
        this.type = TYPE_TICKET;
        return this;
    }

    /**
     * Gets user organization id.
     *
     * @return the user organization id
     */
    public int getUserOrganizationId() {
        if (!this.getType().equals(TYPE_USER_ORGANIZATION)) {
            return 0;
        }
        return userOrganizationId;
    }

    /**
     * Sets user organization id.
     *
     * @param userOrganizationId the user organization id
     * @return the user organization id
     */
    public TicketNote setUserOrganizationId(int userOrganizationId) {
        this.userOrganizationId = userOrganizationId;
        this.userOrganization = null;
        return this;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     * @return the type
     */
    public TicketNote setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Gets note color.
     *
     * @return the note color
     */
    public int getNoteColor() {
        return noteColor;
    }

    /**
     * Sets note color.
     *
     * @param noteColor the note color
     * @return the note color
     */
    public TicketNote setNoteColor(int noteColor) {
        this.noteColor = noteColor;
        return this;
    }

    /**
     * Gets creator staff id.
     *
     * @return the creator staff id
     */
    public int getCreatorStaffId() {
        return creatorStaffId;
    }

    /**
     * Sets creator staff id.
     *
     * @param creatorStaffId the creator staff id
     * @return the creator staff id
     */
    public TicketNote setCreatorStaffId(int creatorStaffId) {
        this.creatorStaffId = creatorStaffId;
        return this;
    }

    /**
     * Gets creator staff name.
     *
     * @return the creator staff name
     */
    public String getCreatorStaffName() {
        return creatorStaffName;
    }

    /**
     * Sets creator staff name.
     *
     * @param creatorStaffName the creator staff name
     * @return the creator staff name
     */
    public TicketNote setCreatorStaffName(String creatorStaffName) {
        this.creatorStaffName = creatorStaffName;
        return this;
    }

    /**
     * Gets for staff id.
     *
     * @return the for staff id
     */
    public int getForStaffId() {
        return forStaffId;
    }

    /**
     * Sets for staff id.
     *
     * @param forStaffId the for staff id
     * @return the for staff id
     */
    public TicketNote setForStaffId(int forStaffId) {
        this.forStaffId = forStaffId;
        this.forStaff = null;
        return this;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public int getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     * @return the creation date
     */
    public TicketNote setCreationDate(int creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    /**
     * Gets creator staff.
     *
     * @return the creator staff
     * @throws KayakoException the kayako exception
     */
    public Staff getCreatorStaff() throws KayakoException {
        if (this.getCreatorStaffId() == 0) {
            return null;
        }
        if (this.creatorStaff == null) {
            this.creatorStaff = (Staff) Staff.get(this.getCreatorStaffId());
        }
        return this.creatorStaff;
    }

    /**
     * Sets creator staff.
     *
     * @param creatorStaff the creator staff
     * @return the creator staff
     */
    public TicketNote setCreatorStaff(Staff creatorStaff) {
        this.creatorStaff = creatorStaff;
        this.creatorStaffId = creatorStaff.getId();
        this.setCreatorStaffName(creatorStaff.getFullName());
        return this;
    }

    /**
     * Sets creator.
     *
     * @param staff the staff
     * @return the creator
     */
    public TicketNote setCreator(Staff staff) {
        return this.setCreatorStaff(staff);
    }

    /**
     * Sets creator.
     *
     * @param id the id
     * @return the creator
     */
    public TicketNote setCreator(int id) {
        return this.setCreatorStaffId(id);
    }

    /**
     * Sets creator.
     *
     * @param creatorString the creator string
     * @return the creator
     */
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
     * @return Staff for staff
     * @throws KayakoException the kayako exception
     */
    public Staff getForStaff() throws KayakoException {
        return this.getForStaff(false);
    }

    /**
     * Gets for staff.
     *
     * @param refresh the refresh
     * @return the for staff
     * @throws KayakoException the kayako exception
     */
    public Staff getForStaff(Boolean refresh) throws KayakoException {
        if (this.forStaff == null || refresh) {
            this.forStaff = (Staff) Staff.get(this.getForStaffId());
        }
        return forStaff;
    }

    /**
     * Sets for staff.
     *
     * @param forStaff the for staff
     * @return the for staff
     */
    public TicketNote setForStaff(Staff forStaff) {
        this.forStaff = forStaff;
        this.forStaffId = forStaff.getId();
        return this;
    }

    /**
     * Gets user organization.
     *
     * @return the user organization
     * @throws KayakoException the kayako exception
     */
    public UserOrganization getUserOrganization() throws KayakoException {
        return this.getUserOrganization(false);
    }

    /**
     * Gets user organization.
     *
     * @param refresh the refresh
     * @return the user organization
     * @throws KayakoException the kayako exception
     */
    public UserOrganization getUserOrganization(Boolean refresh) throws KayakoException {
        if ((userOrganization == null || refresh) && userOrganizationId > 0) {
            userOrganization = UserOrganization.get(this.userOrganizationId);
        }
        return userOrganization;
    }

    /**
     * Sets user organization.
     *
     * @param userOrganization the user organization
     * @return the user organization
     */
    public TicketNote setUserOrganization(UserOrganization userOrganization) {
        this.userOrganization = userOrganization;
        this.userOrganizationId = userOrganization.getId();
        return this;
    }

    /**
     * Gets all.
     *
     * @param ticketId the ticket id
     * @return the all
     */
    public static RawArrayElement getAll(int ticketId) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        searchParams.add(Integer.toString(ticketId));
        return getAll(controller, searchParams);
    }

    private static ArrayList<TicketNote> refineToArray(RawArrayElement rawArrayElement) throws KayakoException {
        ArrayList<TicketNote> TicketNotes = new ArrayList<TicketNote>();
        for (RawArrayElement rawArrayElementTicketNote : rawArrayElement.getComponents()) {
            TicketNotes.add(new TicketNote().populate(rawArrayElementTicketNote));
        }
        return TicketNotes;
    }

    /**
     * Gets all notes.
     *
     * @param ticketId the ticket id
     * @return the all notes
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<TicketNote> getAllNotes(int ticketId) throws KayakoException {
        return refineToArray(getAll(ticketId));
    }

    /**
     * Get ticket note.
     *
     * @param ticketId the ticket id
     * @param id the id
     * @return the ticket note
     * @throws KayakoException the kayako exception
     */
    public static TicketNote get(int ticketId, int id) throws KayakoException {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(ticketId));
        arrayList.add(Integer.toString(id));
        return new TicketNote().populate(get(controller, arrayList));
    }

    @Override
    public KEntity update(String controller) throws KayakoException {
        throw new KayakoException("This method is not available on this type of objects.");
    }

    /**
     * Create ticket note.
     *
     * @return the ticket note
     * @throws KayakoException the kayako exception
     */
    public TicketNote create() throws KayakoException {
        if (!this.getType().equals(TYPE_TICKET)) {
            throw new KayakoException("Ticket note creation is for type ticket only.");
        }
        return (TicketNote) super.create(controller);
    }

    /**
     * Delete boolean.
     *
     * @return the boolean
     * @throws KayakoException the kayako exception
     */
    public Boolean delete() throws KayakoException {
        return getRESTClient().delete(controller, this.getIdArray()) != null;
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

    /**
     * Build hash map.
     *
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    /**
     * Build hash map.
     *
     * @param newTicketNote the new ticket note
     * @return the hash map
     */
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
