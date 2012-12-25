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
public class Staff extends KEntity {


    static protected String controller = "/Base/Staff";
    static protected String objectXmlName = "staff";
    protected Boolean readOnly = true;

    public Staff() {
    }

    public Staff(String firstName, String lastName, String userName, String email, StaffGroup staffGroup, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setEmail(email);
        this.setStaffGroup(staffGroup);
        this.setPassword(password);
    }

    /**
     * Staff identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Staff group identifier.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int staffGroupId = 0;
    /**
     * Staff firstName.
     *
     * @apiField
     * @var string
     */


    protected String firstName;

    protected String lastName;

    protected String fullName;

    /**
     * Staff username (login).
     *
     * @apiField required_create=true
     * @var string
     */
    protected String userName;

    protected String email;

    protected String designation;
    /**
     * Staff livechat greeting message.
     *
     * @apiField
     * @var string
     */
    protected String greeting;

    /**
     * Staff signature appended to posts.
     *
     * @apiField
     * @var string
     */
    protected String signature;

    protected String mobileNumber;

    /**
     * Is this staff enabled.
     *
     * @apiField
     * @var bool
     */
    protected Boolean enabled = true;

    protected String timeZone = "GMT";
    /**
     * Is Daylight Saving Time enabled.
     *
     * @apiField
     * @var bool
     */
    protected Boolean DST = false;

    protected String password;

    private StaffGroup staffGroup = null;


    public String getFirstName() {

        return firstName;
    }

    public Staff setFirstName(String FirstName) {
        this.firstName = FirstName;
        return this;
    }

    public int getId() {

        return id;
    }

    public Staff setId(int id) {
        this.id = id;
        return this;
    }

    public Boolean getReadOnly() {

        return readOnly;
    }

    public Staff setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        Staff.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        Staff.controller = controller;

    }

    public int getStaffGroupId() {
        return staffGroupId;
    }

    public Staff setStaffGroupId(int staffGroupId) {
        this.staffGroupId = staffGroupId;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Staff setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getDesignation() {
        return designation;
    }

    public String getGreeting() {
        return greeting;
    }

    public String getSignature() {
        return signature;
    }


    public Staff setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public Staff setGreeting(String greeting) {
        this.greeting = greeting;
        return this;
    }

    public Staff setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public Staff setEmail(String email) {
        this.email = email;
        return this;
    }

    public Staff setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Staff setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }


    public Boolean isEnabled() {
        return enabled;
    }

    public Staff setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Boolean isDST() {
        return DST;
    }

    public String getPassword() {
        return password;
    }

    public Staff setPassword(String password) {
        this.password = password;
        return this;
    }


    public StaffGroup getStaffGroup() throws KayakoException {
        if (this.staffGroup == null) {
            this.staffGroup = (StaffGroup) StaffGroup.get(this.getStaffGroupId());
        }
        return this.staffGroup;
    }

    public Staff setStaffGroup(StaffGroup staffGroup) {
        this.setStaffGroupId(staffGroup.getId());
        this.staffGroup = staffGroup;
        return this;
    }

    public Staff setDST(Boolean DST) {
        this.DST = DST;
        return this;
    }

    public Staff setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public Staff setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    //this function will populate the data of the staff instance when supplied with RawArrayElement derived from the xml
    @Override
    public Staff populate(RawArrayElement rawArrayElement) throws KayakoException {
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
            } else if (elementName.equals("firstname")) {
                this.setFirstName(component.getContent());
            } else if (elementName.equals("lastName")) {
                this.setLastName(component.getContent());
            } else if (elementName.equals("fullname")) {
                this.setFullName(component.getContent());
            } else if (elementName.equals("username")) {
                this.setUserName(component.getContent());
            } else if (elementName.equals("email")) {
                this.setEmail(component.getContent());
            } else if (elementName.equals("designation")) {
                this.setDesignation(component.getContent());
            } else if (elementName.equals("greeting")) {
                this.setGreeting(component.getContent());
            } else if (elementName.equals("mobilenumber")) {
                this.setMobileNumber(component.getContent());
            } else if (elementName.equals("isenabled")) {
                this.setEnabled(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("enabledst")) {
                this.setDST(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("signature")) {
                this.setSignature(component.getContent());
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        HashMap<String, String> staffHashMap = new HashMap<String, String>();
        staffHashMap.put("firstname", this.getFirstName());
        staffHashMap.put("lastname", this.getLastName());
        staffHashMap.put("fullname", this.getFullName());
        staffHashMap.put("username", this.getUserName());
        staffHashMap.put("email", this.getEmail());
        staffHashMap.put("designation", this.getDesignation());
        staffHashMap.put("mobilenumber", this.getMobileNumber());
        staffHashMap.put("staffsignature", this.getSignature());
        staffHashMap.put("greeting", this.getGreeting());
        staffHashMap.put("staffgroupid", Integer.toString(this.getStaffGroupId()));
        staffHashMap.put("isenabled", this.isEnabled() ? "1" : "0");
        staffHashMap.put("enabledst", this.isDST() ? "1" : "0");
        return staffHashMap;
    }


    //TODO create ticket from here

}
