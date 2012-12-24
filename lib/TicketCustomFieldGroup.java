package lib;

import java.util.ArrayList;

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
public class TicketCustomFieldGroup extends CustomFieldGroup {
    static protected String controller = "/Tickets/TicketCustomField";
    /**
     * Ticket identifier.
     *
     * @var int
     */
    protected int ticketId;


    /**
     * Fetches ticket custom fields groups from server.
     *
     * @param ticketId Ticket identifier.
     * @return RawArrayElement
     */
    static public RawArrayElement getAll(int ticketId) {
        /*$objects = array();
        if (array_key_exists( static::$object_xml_name, $result)){
            foreach($result[ static::$object_xml_name]as $object_data){
                $objects[]=new static ($ticket_id, $object_data);
            }
        }
        return new kyResultSet($objects);*/
        ArrayList<String> params = new ArrayList<String>();
        params.add(Integer.toString(ticketId));
        RawArrayElement rawArrayElement = KEntity.getAll(controller, params);
        return rawArrayElement;

    }

    /**
     * Returns identifier of the ticket that this group is associated with.
     *
     * @return int
     */
    public int getTicketId() {
        return this.ticketId;
    }
}
