package com.kayako.api;

import java.util.ArrayList;

/**
 * The type Ticket custom field group.
 * @author Rajat Garg
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class TicketCustomFieldGroup extends CustomFieldGroup {
    /**
     * The Controller.
     */
    static protected String controller = "/Tickets/TicketCustomField";
    /**
     * Ticket identifier.
     *
     * @var int
     */
    protected int ticketId;

    /**
     * Instantiates a new Ticket custom field group.
     *
     * @param ticketId the ticket id
     * @param rawArrayElement the raw array element
     * @throws KayakoException the kayako exception
     */
    public TicketCustomFieldGroup(int ticketId, RawArrayElement rawArrayElement) throws KayakoException {
        this.ticketId = ticketId;
        this.type = TYPE_TICKET;
        this.populate(rawArrayElement);
    }

    /**
     * Instantiates a new Ticket custom field group.
     *
     * @param ticketId the ticket id
     */
    public TicketCustomFieldGroup(int ticketId) {
        this.ticketId = ticketId;
        this.type = TYPE_TICKET;
    }

    /**
     * Fetches ticket custom fields groups from server.
     *
     * @param ticketId Ticket identifier.
     * @return RawArrayElement all
     */
    static public RawArrayElement getAll(int ticketId) {
        ArrayList<String> params = new ArrayList<String>();
        params.add(Integer.toString(ticketId));
        RawArrayElement rawArrayElement = getAll(controller, params);
        return rawArrayElement;
    }

    /**
     * Returns identifier of the ticket that this group is associated with.
     *
     * @return int ticket id
     */
    public int getTicketId() {
        return this.ticketId;
    }

    /**
     * Gets controller.
     *
     * @return the controller
     */
    public static String getController() {
        return controller;
    }
}
