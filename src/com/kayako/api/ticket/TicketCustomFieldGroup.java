package com.kayako.api.ticket;

import com.kayako.api.customfield.CustomFieldGroup;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;

import java.util.ArrayList;

/**
 * The type Ticket custom field group.
 * @author Kayako Support System Pvt Ltd
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
     * @param element the raw array element
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public TicketCustomFieldGroup(int ticketId, RawArrayElement element) throws KayakoException {
        this.ticketId = ticketId;
        this.type = TYPE_TICKET;
        this.populate(element);
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
        RawArrayElement element = getAll(controller, params);
        return element;
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
