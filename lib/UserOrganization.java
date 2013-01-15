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
public class UserOrganization extends KEntity {

    static final String TYPE_RESTRICTED = "restricted";
    static final String TYPE_SHARED = "shared";

    static protected String controller = "/Base/UserOrganization";
    static protected String objectXmlName = "userorganization";

    /**
     * User Organization identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * User Organization name.
     *
     * @apiField
     * @var string
     */
    protected String name;

    /**
     * Type of user Organization.
     *
     * @apiField
     * @var string
     * @see UserOrganization::TYPE constants.
     */
    protected String type = TYPE_RESTRICTED;

    /**
     * User organization address.
     *
     * @apiField
     * @var string
     */
    protected String address;

    /**
     * User organization city.
     *
     * @apiField
     * @var string
     */
    protected String city;

    /**
     * User organization state.
     *
     * @apiField
     * @var string
     */
    protected String state;

    /**
     * User organization postal code.
     *
     * @apiField
     * @var string
     */
    protected String postalCode;

    /**
     * User organization country.
     *
     * @apiField
     * @var string
     */
    protected String country;

    /**
     * User organization phone number.
     *
     * @apiField
     * @var string
     */
    protected String phone;

    /**
     * User organization FAX number.
     *
     * @apiField
     * @var string
     */
    protected String fax;

    /**
     * User organization website URL.
     *
     * @apiField
     * @var string
     */
    protected String website;

    /**
     * Timestamp of when the user organization was created.
     *
     * @apiField
     * @var int
     */
    protected int dateline;

    /**
     * Timestamp of when the user organization was last updated.
     *
     * @apiField
     * @var int
     */
    protected int lastUpdate;

    /**
     * Identifier of Service Level Agreement plan associated to this user organization.
     *
     * @apiField
     * @var int
     */
    protected int SLAPlanID;

    /**
     * Timestamp of when the Service Level Agreement plan associated to this user organization will expire.
     *
     * @apiField
     * @var int
     */
    protected int SLAPlanExpiry;

    public String getName() {

        return name;
    }

    public UserOrganization setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {

        return id;
    }

    public UserOrganization setId(int id) {
        this.id = id;
        return this;
    }

    public Boolean getReadOnly() {

        return readOnly;
    }

    public UserOrganization setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        UserOrganization.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        UserOrganization.controller = controller;
    }

    public String getType() {
        return type;
    }

    public UserOrganization setType(String type) {
        this.type = type;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserOrganization setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserOrganization setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public UserOrganization setState(String state) {
        this.state = state;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public UserOrganization setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserOrganization setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserOrganization setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public int getSLAPlanExpiry() {
        return SLAPlanExpiry;
    }

    public UserOrganization setSLAPlanExpiry(int SLAPlanExpiry) {
        this.SLAPlanExpiry = SLAPlanExpiry;
        return this;
    }

    public int getSLAPlanID() {

        return SLAPlanID;
    }

    public UserOrganization setSLAPlanID(int SLAPlanID) {
        this.SLAPlanID = SLAPlanID;
        return this;
    }

    public int getLastUpdate() {

        return lastUpdate;
    }

    public UserOrganization setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public int getDateline() {

        return dateline;
    }

    public UserOrganization setDateline(int dateline) {
        this.dateline = dateline;
        return this;
    }

    public String getWebsite() {

        return website;
    }

    public UserOrganization setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getFax() {

        return fax;
    }

    public UserOrganization setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public static UserOrganization get(int id) throws KayakoException {
        return new UserOrganization().populate(KEntity.get(controller, id));
    }

    //this function will populate the data of the user Organization instance when supplied with RawArrayElement derived from the xml
    @Override
    public UserOrganization populate(RawArrayElement rawArrayElement) throws KayakoException {
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
            } else if (elementName.equals("name")) {
                this.setName(component.getContent());
            } else if (elementName.equals("organizationtype")) {
                this.setType(component.getContent());
            } else if (elementName.equals("address")) {
                this.setAddress(component.getContent());
            } else if (elementName.equals("city")) {
                this.setCity(component.getContent());
            } else if (elementName.equals("state")) {
                this.setState(component.getContent());
            } else if (elementName.equals("postalcode")) {
                this.setPostalCode(component.getContent());
            } else if (elementName.equals("country")) {
                this.setCountry(component.getContent());
            } else if (elementName.equals("phone")) {
                this.setPhone(component.getContent());
            } else if (elementName.equals("fax")) {
                this.setFax(component.getContent());
            } else if (elementName.equals("website")) {
                this.setWebsite(component.getContent());
            } else if (elementName.equals("dateline")) {
                this.setDateline(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("lastupdate")) {
                this.setLastUpdate(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("slaplanid")) {
                this.setSLAPlanID(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("slaplanexpiry")) {
                this.setSLAPlanExpiry(Helper.parseInt(component.getContent()));
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> userOrganizationHashMap = new HashMap<String, String>();
        userOrganizationHashMap.put("name", this.getName());
        userOrganizationHashMap.put("organizationtype", this.getType());
        userOrganizationHashMap.put("address", this.getAddress());
        userOrganizationHashMap.put("city", this.getCity());
        userOrganizationHashMap.put("state", this.getState());
        userOrganizationHashMap.put("postalcode", this.getPostalCode());
        userOrganizationHashMap.put("country", this.getCountry());
        userOrganizationHashMap.put("phone", this.getPhone());
        userOrganizationHashMap.put("fax", this.getFax());
        userOrganizationHashMap.put("website", this.getWebsite());
        userOrganizationHashMap.put("slaplanid", Integer.toString(this.getSLAPlanID()));
        userOrganizationHashMap.put("slaplanexpiry", Integer.toString(this.getSLAPlanExpiry()));

        return userOrganizationHashMap;
    }

}
