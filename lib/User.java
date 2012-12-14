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
public class User extends KEntity {
    static final String ROLE_USER = "user";
    static final int DEFAULT_MAX_USERS = 1000;
    static final String ROLE_MANAGER = "manager";
    static final String SALUTATION_MR = "Mr.";
    static final String SALUTATION_MS = "Ms.";
    static final String SALUTATION_MRS = "Mrs.";
    static final String SALUTATION_DR = "Dr.";
    static protected String controller = "/Base/User";
    static protected String objectXmlName = "user";
    protected Boolean readOnly = true;

    public User() {
    }

    public User(String name, String email, UserGroup userGroup, String password) {
        this.setFullName(name);
        this.setEmail(email);
        this.setUserGroup(userGroup);
        this.setPassword(password);
    }

    /**
     * User identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * User group identifier.
     *
     * @apiField required_create=true
     * @var int
     */
    protected int userGroupId = 0;


    /**
     * User role.
     *
     * @apiField
     * @var string
     * @see User::ROLE constants.
     */
    protected String userRole = ROLE_USER;
    protected int userOrganizationId = 0;


    /**
     * User salutation.
     *
     * @apiField
     * @var string
     * @see User::SALUTATION constants.
     */
    protected String salutation;

    protected int userExpiry = 0;

    protected String designation;

    protected String fullName;

    protected ArrayList<String> emails = new ArrayList<String>();

    protected String phone;

    protected int dateLine;

    protected int lastVisit;

    protected Boolean enabled = true;

    protected int SLAPlanId;

    protected int SLAPlanExpiry;

    protected Boolean sendWelcomeEmail = true;

    protected String password;

    /**
     * Timezone of the user.
     *
     * @apiField
     * @var string
     */
    protected String timeZone = "GMT";
    /**
     * Is Daylight Saving Time enabled.
     *
     * @apiField
     * @var bool
     */
    protected Boolean DST = false;


    private UserGroup userGroup = null;

    private UserOrganization userOrganization = null;

    public int getId() {

        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public Boolean isReadOnly() {

        return readOnly;
    }

    public User setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public static String getObjectXmlName() {

        return objectXmlName;
    }

    public static void setObjectXmlName(String objectXmlName) {
        User.objectXmlName = objectXmlName;
    }

    public static String getController() {

        return controller;
    }

    public static void setController(String controller) {
        User.controller = controller;

    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public User setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
        return this;
    }


    public String getFullName() {
        return fullName;
    }


    public String getEmail() {
        return emails.get(0);
    }


    public User setEmail(String email) {
        this.emails.add(email);
        return this;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhone() {
        return phone;
    }


    public String getTimeZone() {
        return timeZone;
    }

    public Boolean isDST() {
        return DST;
    }


    public UserGroup getUserGroup() {
        if (this.userGroup == null) {
            this.userGroup = (UserGroup) UserGroup.get(this.getUserGroupId());
        }
        return this.userGroup;
    }

    public User setUserGroup(UserGroup userGroup) {
        this.setUserGroupId(userGroup.getId());
        this.userGroup = userGroup;
        return this;
    }

    public User setDST(Boolean DST) {
        this.DST = DST;
        return this;
    }

    public User setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public User setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    public String getSalutation() {
        return salutation;
    }

    public User setSalutation(String salutation) {
        this.salutation = salutation;
        return this;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public User setEmails(ArrayList<String> emails) {
        this.emails = emails;
        return this;
    }

    public int getUserExpiry() {
        return userExpiry;
    }

    public User setUserExpiry(int userExpiry) {
        this.userExpiry = userExpiry;
        return this;
    }

    public int getUserOrganizationId() {
        return userOrganizationId;
    }

    public User setUserOrganizationId(int userOrganizationId) {
        this.userOrganizationId = userOrganizationId;
        return this;
    }

    public String getDesignation() {
        return designation;
    }

    public User setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public int getDateLine() {
        return dateLine;
    }

    public User setDateLine(int dateLine) {
        this.dateLine = dateLine;
        return this;
    }

    public int getLastVisit() {
        return lastVisit;
    }

    public User setLastVisit(int lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public int getSLAPlanId() {
        return SLAPlanId;
    }

    public User setSLAPlanId(int SLAPlanId) {
        this.SLAPlanId = SLAPlanId;
        return this;
    }

    public int getSLAPlanExpiry() {
        return SLAPlanExpiry;
    }

    public User setSLAPlanExpiry(int SLAPlanExpiry) {
        this.SLAPlanExpiry = SLAPlanExpiry;
        return this;
    }

    public Boolean isSendWelcomeEmail() {
        return sendWelcomeEmail;
    }

    public User setSendWelcomeEmail(Boolean sendWelcomeEmail) {
        this.sendWelcomeEmail = sendWelcomeEmail;
        return this;
    }

    public UserOrganization getUserOrganization() {
        return userOrganization;
    }

    public User setUserOrganization(UserOrganization userOrganization) {
        this.userOrganization = userOrganization;
        return this;
    }

    public static RawArrayElement getAll(String controller) throws KayakoException {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("Filter");
        return KEntity.getAll(controller, searchParams);
    }

    public static RawArrayElement getAll(int startingUserId) {
        return User.getAll(startingUserId, DEFAULT_MAX_USERS);
    }

    public static RawArrayElement getAll(int startingUserId, int maxItems) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("Filter");
        searchParams.add(Integer.toString(startingUserId));
        searchParams.add(Integer.toString(maxItems));
        return KEntity.getAll(controller, searchParams);
    }


    //this function will populate the data of the user instance when supplied with RawArrayElement derived from the xml
    @Override
    public User populate(RawArrayElement rawArrayElement) throws KayakoException {
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
            } else if (elementName.equals("usergroupid")) {
                this.setUserGroupId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("userrole")) {
                this.setUserRole(component.getContent());
            } else if (elementName.equals("userorganizationid")) {
                this.setUserOrganizationId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("salutation")) {
                this.setSalutation(component.getContent());
            } else if (elementName.equals("userexpiry")) {
                this.setUserExpiry(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("fullname")) {
                this.setFullName(component.getContent());
            } else if (elementName.equals("email")) {
                this.setEmail(component.getContent());
            } else if (elementName.equals("designation")) {
                this.setDesignation(component.getContent());
            } else if (elementName.equals("phone")) {
                this.setPhone(component.getContent());
            } else if (elementName.equals("dateline")) {
                this.setDateLine(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("lastvisit")) {
                this.setLastVisit(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("isenabled")) {
                this.setEnabled(Integer.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("timezone")) {
                this.setTimeZone(component.getContent());
            } else if (elementName.equals("enabledst")) {
                this.setDST(Integer.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("slaplanid")) {
                this.setSLAPlanId(Integer.parseInt(component.getContent()));
            } else if (elementName.equals("slaplanexpiry")) {
                this.setSLAPlanExpiry(Integer.parseInt(component.getContent()));
            }
        }
        return this;
    }


    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newUser) {
        HashMap<String, String> userHashMap = new HashMap<String, String>();
        userHashMap.put("fullname", this.getFullName());
        userHashMap.put("usergroupid", Integer.toString(this.getUserGroupId()));
        if (newUser) {
            userHashMap.put("password", this.getPassword());
            userHashMap.put("sendwelcomeemail", this.isSendWelcomeEmail() ? "1" : "0");
        }
        userHashMap.put("email", this.getEmail());
        userHashMap.put("userorganizaionid", Integer.toString(this.getUserOrganizationId()));
        userHashMap.put("salutaion", this.getSalutation());
        userHashMap.put("designation", this.getDesignation());
        userHashMap.put("phone", this.getPhone());
        userHashMap.put("isenabled", this.isEnabled() ? "1" : "0");
        userHashMap.put("userrole", this.getUserRole());
        userHashMap.put("timezone", this.getTimeZone());
        userHashMap.put("enabledst", this.isDST() ? "1" : "0");
        userHashMap.put("slaplanid", Integer.toString(this.getSLAPlanId()));
        userHashMap.put("slaplanexpiry", Integer.toString(this.getSLAPlanExpiry()));
        userHashMap.put("userexpiry", Integer.toString(this.getUserExpiry()));

        return userHashMap;
    }


    //TODO create ticket from here

}
