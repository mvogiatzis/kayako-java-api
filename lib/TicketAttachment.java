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
        return this;
    }

    public int getTicketPostId() {
        return ticketPostId;
    }

    public TicketAttachment setTicketPostId(int ticketPostId) {
        this.ticketPostId = ticketPostId;
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

    public byte[] getContents() {
        return contents;
    }

    public TicketAttachment setContents(byte[] contents) {
        this.contents = contents;
        return this;
    }

    public TicketAttachment setContentFromFile(File file) {
        try {
            this.contents = Helper.readBytesFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return this;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public TicketAttachment setTicket(Ticket ticket) {
        this.ticket = ticket;
        return this;
    }

    public TicketPost getTicketPost() {
        return ticketPost;
    }

    public TicketAttachment setTicketPost(TicketPost ticketPost) {
        this.ticketPost = ticketPost;
        return this;
    }

    //TODO - write unsupported functions like getAll(controller) , update() etc and throw appropriate exception

    public static RawArrayElement getAll(String controller) throws KayakoException {
        throw new KayakoException("This method is not available");
    }

    public static RawArrayElement getAll(int ticketId) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        searchParams.add(Integer.toString(ticketId));
        return KEntity.getAll(controller, searchParams);
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
                this.setId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("ticketid")) {
                this.setTicketId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("ticketpostid")) {
                this.setTicketPostId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("filename")) {
                this.setFileName(component.getContent());
            } else if (elementName.equals("filesize")) {
                this.setFileSize(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("filetype")) {
                this.setFileType(component.getContent());
            } else if (elementName.equals("dateline")) {
                this.setDateLine(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("contents")) {
                try {
                    this.setContents(Base64.decode(component.getContent()));
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
        ticketAttachmentHashMap.put("contents", Base64.encodeBytes(this.getContents()));
        return ticketAttachmentHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    //TODO a lot of functions like getAll etc, plus creating new attachments from here...
}
