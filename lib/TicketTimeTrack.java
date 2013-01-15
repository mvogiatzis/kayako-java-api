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
public class TicketTimeTrack extends KEntity {

    /**
     * Color of time track - yellow.
     *
     * @var int
     */
    static final int COLOR_YELLOW = 1;

    /**
     * Color of time track - purple.
     *
     * @var int
     */
    static final int COLOR_PURPLE = 2;

    /**
     * Color of time track - blue.
     *
     * @var int
     */
    static final int COLOR_BLUE = 3;

    /**
     * Color of time track - green.
     *
     * @var int
     */
    static final int COLOR_GREEN = 4;

    /**
     * Color of time track - red.
     *
     * @var int
     */
    static final int COLOR_RED = 5;

    static protected String controller = "/Tickets/TicketTimeTrack";
    static protected String objectXmlName = "timetrack";

    /**
     * Ticket timetrack identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Ticket identifier - if this timetrack is associated with ticket.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int ticketId;

    /**
     * Time worked (in seconds) in this ticket time track.
     *
     * @apiField required_create=true alias=timespent
     * @var int
     */
    protected int timeWorked;

    /**
     * Billable time (in seconds) in this ticket time track.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int timeBillable;

    /**
     * Bill timestamp of this ticket time track.
     *
     * @apiField required_create=true alias=billtimeline
     * @var int
     */
    protected int billDate;

    /**
     * Work timestamp of this ticket time track.
     *
     * @apiField required_create=true alias=worktimeline
     * @var int
     */
    protected int workDate;

    /**
     * Worker staff identifier.
     *
     * @apiField
     * @var int
     */
    protected int workerStaffId = 0;

    /**
     * Worker staff full name.
     *
     * @apiField
     * @var string
     */
    protected String workerStaffName;

    /**
     * Creator staff identifier.
     *
     * @apiField required_create=true alias=staffid
     * @var int
     */
    protected int creatorStaffId;

    /**
     * Creator staff full name.
     *
     * @apiField
     * @var string
     */
    protected String creatorStaffName;

    /**
     * Ticket time track note color.
     *
     * @apiField
     * @var int
     * @see TicketTimeTrack::COLOR constants.
     */
    protected int noteColor;

    /**
     * Note contents of this ticket time track.
     *
     * @apiField required_create=true
     * @var String
     */
    protected String contents;

    /**
     * The ticket that this time track will be connected with.
     *
     * @var Ticket
     */
    private Ticket ticket;

    /**
     * Worker staff.
     *
     * @var Staff
     */
    private Staff workerStaff = null;

    /**
     * Creator staff.
     *
     * @var Staff
     */
    private Staff creatorStaff = null;

    public TicketTimeTrack() {
    }

    public TicketTimeTrack(Ticket ticket, String contents, Staff staff, int timeWorked, int timeBillable) {
        this.setTicketId(ticket.getId()).setContents(contents).setCreatorStaffId(staff.getId());
        this.setTimeWorked(timeWorked).setTimeBillable(timeBillable).setWorkerStaff(staff);
    }

    public TicketTimeTrack(Ticket ticket, String contents, Staff staff, String timeWorked, String timeBillable) {
        this.setTicketId(ticket.getId()).setContents(contents).setCreatorStaffId(staff.getId());
        this.setTimeWorked(timeWorked).setTimeBillable(timeBillable).setWorkerStaff(staff);
    }

    public int getId() {
        return id;
    }

    public TicketTimeTrack setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Boolean getReadOnly() {

        return readOnly;
    }

    @Override
    public TicketTimeTrack setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        TicketTimeTrack.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        TicketTimeTrack.controller = controller;
    }

    public int getTicketId() {
        return ticketId;
    }

    public TicketTimeTrack setTicketId(int ticketId) {
        this.ticketId = ticketId;
        this.ticket = null;
        return this;
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

    public TicketTimeTrack setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.ticketId = ticket.getId();
        return this;
    }

    public int getCreatorStaffId() {
        return creatorStaffId;
    }

    public TicketTimeTrack setCreatorStaffId(int creatorStaffId) {
        this.creatorStaffId = creatorStaffId;
        return this;
    }

    public String getCreatorStaffName() {
        return creatorStaffName;
    }

    public TicketTimeTrack setCreatorStaffName(String creatorStaffName) {
        this.creatorStaffName = creatorStaffName;
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

    public TicketTimeTrack setCreatorStaff(Staff creatorStaff) {
        this.creatorStaff = creatorStaff;
        this.setCreatorStaffId(creatorStaff.getId());
        this.setCreatorStaffName(creatorStaff.getFullName());
        return this;
    }

    public int getTimeWorked() {
        return timeWorked;
    }

    public TicketTimeTrack setTimeWorked(int timeWorked) {
        this.timeWorked = timeWorked;
        return this;
    }

    public TicketTimeTrack setTimeWorked(String timeWorked) {
        this.setTimeWorked(stringToSeconds(timeWorked));
        return this;
    }

    //input String as "12:10" (hh:mm)
    private static int stringToSeconds(String timeWorked) {
        String[] timeArray = timeWorked.split(":");
        return (Helper.parseInt(timeArray[0]) * 60 * 60) + (Helper.parseInt(timeArray[1]) * 60);
    }

    public int getTimeBillable() {
        return timeBillable;
    }

    public TicketTimeTrack setTimeBillable(int timeBillable) {
        this.timeBillable = timeBillable;
        return this;
    }

    public TicketTimeTrack setTimeBillable(String time) {
        this.setTimeBillable(stringToSeconds(time));
        return this;
    }

    public int getBillDate() {
        return billDate;
    }

    public TicketTimeTrack setBillDate(int billDate) {
        this.billDate = billDate;
        return this;
    }

    public int getWorkDate() {
        return workDate;
    }

    public TicketTimeTrack setWorkDate(int workDate) {
        this.workDate = workDate;
        return this;
    }

    public int getWorkerStaffId() {
        return workerStaffId;
    }

    public TicketTimeTrack setWorkerStaffId(int workerStaffId) {
        this.workerStaffId = workerStaffId;
        this.workerStaff = null;
        return this;
    }

    public String getWorkerStaffName() {
        return workerStaffName;
    }

    public TicketTimeTrack setWorkerStaffName(String workerStaffName) {
        this.workerStaffName = workerStaffName;
        return this;
    }

    public int getNoteColor() {
        return noteColor;
    }

    public TicketTimeTrack setNoteColor(int noteColor) {
        this.noteColor = noteColor;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public TicketTimeTrack setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public Staff getWorkerStaff() throws KayakoException {
        return this.getWorkerStaff(false);
    }

    public Staff getWorkerStaff(Boolean refresh) throws KayakoException {
        if (this.workerStaff == null || refresh) {
            this.workerStaff = Staff.get(this.getWorkerStaffId());
            this.setWorkerStaffName(this.workerStaff.getFullName());
        }
        return workerStaff;
    }

    public TicketTimeTrack setWorkerStaff(Staff workerStaff) {
        this.workerStaff = workerStaff;
        this.setWorkerStaffId(workerStaff.getId());
        return this;
    }

    public static RawArrayElement getAll(int ticketId) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        searchParams.add(Integer.toString(ticketId));
        return KEntity.getAll(controller, searchParams);
    }

    private static ArrayList<TicketTimeTrack> refineToArray(RawArrayElement rawArrayElement) throws KayakoException {
        ArrayList<TicketTimeTrack> TicketTimeTracks = new ArrayList<TicketTimeTrack>();
        for (RawArrayElement rawArrayElementTicketTimeTrack : rawArrayElement.getComponents()) {
            TicketTimeTracks.add(new TicketTimeTrack().populate(rawArrayElementTicketTimeTrack));
        }
        return TicketTimeTracks;
    }

    public static ArrayList<TicketTimeTrack> getAllTimeTracks(int ticketId) throws KayakoException {
        return refineToArray(getAll(ticketId));
    }

    public static TicketTimeTrack get(int ticketId, int id) throws KayakoException {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(ticketId));
        arrayList.add(Integer.toString(id));
        return new TicketTimeTrack().populate(KEntity.get(controller, arrayList));
    }

    @Override
    public KEntity update(String controller) throws KayakoException {
        throw new KayakoException("This method is not available on this type of objects.");
    }

    public TicketTimeTrack create() throws KayakoException {
        return (TicketTimeTrack) super.create(controller);
    }

    //this function will populate the data of the ticket time track instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketTimeTrack populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }
        this.setId(Helper.parseInt(rawArrayElement.getAttribute("id"))).setTicketId(Helper.parseInt(rawArrayElement.getAttribute("ticketid")));
        this.setTimeWorked(Helper.parseInt(rawArrayElement.getAttribute("timeworked"))).setTimeBillable(Helper.parseInt(rawArrayElement.getAttribute("timebillable")));
        this.setBillDate(Helper.parseInt(rawArrayElement.getAttribute("billdate"))).setWorkDate(Helper.parseInt(rawArrayElement.getAttribute("workdate")));
        this.setNoteColor(Helper.parseInt(rawArrayElement.getAttribute("notecolor")));
        this.setCreatorStaffName(rawArrayElement.getAttribute("creatorstaffname")).setCreatorStaffId(Helper.parseInt(rawArrayElement.getAttribute("creatorstaffid")));
        this.setWorkerStaffName(rawArrayElement.getAttribute("workerstaffname")).setWorkerStaffId(Helper.parseInt(rawArrayElement.getAttribute("workerstaffid")));
        this.setContents(rawArrayElement.getContent());
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newTicketTimeTrack) {
        HashMap<String, String> ticketTimeTrackHashMap = new HashMap<String, String>();
        ticketTimeTrackHashMap.put("ticketid", Integer.toString(this.getTicketId()));
        ticketTimeTrackHashMap.put("staffid", Integer.toString(this.getCreatorStaffId()));
        ticketTimeTrackHashMap.put("contents", this.getContents());
        ticketTimeTrackHashMap.put("worktimeline", Integer.toString(this.getWorkDate()));
        ticketTimeTrackHashMap.put("billtimeline", Integer.toString(this.getBillDate()));
        ticketTimeTrackHashMap.put("timespent", Integer.toString(this.getTimeWorked()));
        ticketTimeTrackHashMap.put("timebillable", Integer.toString(this.getTimeBillable()));
        if (this.getWorkerStaffId() != 0) {
            ticketTimeTrackHashMap.put("workerstaffid", Integer.toString(this.getWorkerStaffId()));
        }
        ticketTimeTrackHashMap.put("notecolor", Integer.toString(this.getNoteColor()));
        return ticketTimeTrackHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
