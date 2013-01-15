package lib;

import java.io.File;
import java.io.IOException;
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
public class TicketAttachment extends KEntity {

    static protected String controller = "/Tickets/TicketAttachment";
    static protected String objectXmlName = "attachment";

    /**
     * Ticket attachment identifier.
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
     * Identifier of ticket post that this attachment is attached to.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int ticketPostId;

    /**
     * Attachment file name.
     *
     * @apiField required_create=true
     * @var string
     */
    protected String fileName;

    /**
     * Attachment size in bytes.
     *
     * @apiField
     * @var int
     */
    protected int fileSize;

    /**
     * Attachment MIME type.
     *
     * @apiField
     * @var string
     */
    protected String fileType;

    /**
     * Timestamp of when this attachment was created.
     *
     * @apiField
     * @var int
     */
    protected int dateLine;

    /**
     * Raw contents of attachment.
     *
     * @apiField required_create=true
     * @var string
     */
    protected byte[] contents;

    /**
     * Ticket with this attachment.
     *
     * @var Ticket
     */
    private Ticket ticket = null;

    /**
     * Ticket post with this attachment.
     *
     * @var TicketPost
     */
    private TicketPost ticketPost = null;

    public static String getController() {
        return controller;
    }

    public static void setController(String controller) {
        TicketAttachment.controller = controller;
    }

    public static String getObjectXmlName() {
        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        TicketAttachment.objectXmlName = objectXmlName;
    }

    public int getId() {
        return id;
    }

    public TicketAttachment setId(int id) {
        this.id = id;
        return this;
    }

    public int getTicketId() {
        return ticketId;
    }

    public TicketAttachment setTicketId(int ticketId) {
        this.ticketId = ticketId;
        this.ticket = null;
        return this;
    }

    public int getTicketPostId() {
        return ticketPostId;
    }

    public TicketAttachment setTicketPostId(int ticketPostId) {
        this.ticketPostId = ticketPostId;
        this.ticketPost = null;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public TicketAttachment setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public int getFileSize() {
        return fileSize;
    }

    public TicketAttachment setFileSize(int fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public TicketAttachment setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public int getDateLine() {
        return dateLine;
    }

    public TicketAttachment setDateLine(int dateLine) {
        this.dateLine = dateLine;
        return this;
    }

    public byte[] getContents() throws KayakoException {
        return this.getContents(true);
    }

    public byte[] getContents(Boolean autoFetch) throws KayakoException {
        if (this.contents == null && autoFetch) {
            this.setContents(get(this.getTicketId(), this.getId()).getContents());
        }
        return contents;
    }

    public TicketAttachment setContents(byte[] contents) {
        this.contents = contents;
        return this;
    }

    public TicketAttachment setContentFromFile(File file) {
        try {
            this.contents = Helper.readBytesFromFile(file);
            this.setFileName(file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public TicketAttachment setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.ticketId = ticket.getId();
        return this;
    }

    public TicketPost getTicketPost() throws KayakoException {
        return this.getTicketPost(false);
    }

    public TicketPost getTicketPost(Boolean refresh) throws KayakoException {
        if (this.ticketPost == null || refresh) {
            if (this.getTicketPostId() == 0) {
                return null;
            }
            this.ticketPost = TicketPost.get(this.getTicketId(), this.getTicketPostId());
        }
        return ticketPost;
    }

    /**
     * Sets the ticket post this attachment will be attached to.
     * <p/>
     * Automatically sets the ticket.
     */
    public TicketAttachment setTicketPost(TicketPost ticketPost) throws KayakoException {
        this.ticketPost = ticketPost;
        this.ticketPostId = ticketPost.getId();
        this.ticket = ticketPost.getTicket();
        this.ticketId = this.ticket.getId();
        return this;
    }

    public static TicketAttachment get(int ticketId, int id) throws KayakoException {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(ticketId));
        arrayList.add(Integer.toString(id));
        return new TicketAttachment().populate(KEntity.get(controller, arrayList));
    }

    public TicketAttachment update() throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    public Boolean delete() throws KayakoException {
        return KEntity.getRESTClient().delete(controller, this.getIdArray()) != null;
    }

    public ArrayList<String> getIdArray() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(this.getTicketId()));
        arrayList.add(Integer.toString(this.getId()));
        return arrayList;
    }

    public static RawArrayElement getAll(String controller) throws KayakoException {
        throw new KayakoException("This method is not available");
    }

    public static RawArrayElement getAll(int ticketId) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        searchParams.add(Integer.toString(ticketId));
        return KEntity.getAll(controller, searchParams);
    }

    public static ArrayList<TicketAttachment> getAllAttachments(int ticketId) throws KayakoException {
        return refineToArray(getAll(ticketId));
    }

    private static ArrayList<TicketAttachment> refineToArray(RawArrayElement rawArrayElement) throws KayakoException {
        ArrayList<TicketAttachment> TicketAttachments = new ArrayList<TicketAttachment>();
        for (RawArrayElement rawArrayElementTicketAttachment : rawArrayElement.getComponents()) {
            TicketAttachments.add(new TicketAttachment().populate(rawArrayElementTicketAttachment));
        }
        return TicketAttachments;
    }

    //this function will populate the data of the ticket attachment instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketAttachment populate(RawArrayElement rawArrayElement) throws KayakoException {
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
            } else if (elementName.equals("ticketpostid")) {
                this.setTicketPostId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("filename")) {
                this.setFileName(component.getContent());
            } else if (elementName.equals("filesize")) {
                this.setFileSize(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("filetype")) {
                this.setFileType(component.getContent());
            } else if (elementName.equals("dateline")) {
                this.setDateLine(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("contents")) {
                try {
                    this.setContents(Base64.decode(component.getContent()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newTicketAttachment) {
        HashMap<String, String> ticketAttachmentHashMap = new HashMap<String, String>();
        ticketAttachmentHashMap.put("ticketid", Integer.toString(this.getTicketId()));
        ticketAttachmentHashMap.put("ticketpostid", Integer.toString(this.getTicketPostId()));
        ticketAttachmentHashMap.put("filename", this.getFileName());
        try {
            ticketAttachmentHashMap.put("contents", Base64.encodeBytes(this.getContents()));
        } catch (KayakoException ke) {
            ke.printStackTrace();
        }

        return ticketAttachmentHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static TicketAttachment createTicketAttachment(TicketPost ticketPost, byte[] contents, String fileName) throws KayakoException {
        TicketAttachment ticketAttachment = new TicketAttachment().setTicketPost(ticketPost).setContents(contents).setFileName(fileName);
        return ticketAttachment;
    }

    public static TicketAttachment createTicketAttachment(TicketPost ticketPost, File file) throws KayakoException {
        TicketAttachment ticketAttachment = new TicketAttachment().setTicketPost(ticketPost).setContentFromFile(file);
        return ticketAttachment;
    }

    //Though create() method might not be written everywhere, create(String controller) can be called on almost all objects
    public TicketAttachment create() throws KayakoException {
        return (TicketAttachment) super.create(controller);
    }
}
