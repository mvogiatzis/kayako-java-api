package lib;

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
public class CustomField extends KEntity {

    /**
     * Field identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Field type.
     *
     * @apiField
     * @var int
     */
    protected int type;

    /**
     * Field name.
     *
     * @apiField
     * @var string
     */
    protected String name;

    /**
     * Field title.
     *
     * @apiField
     * @var string
     */
    protected String title;

    /**
     * Field value.
     *
     * @apiField name=value getter=getRawValue setter=setValue
     * @var string
     */
    protected String rawValue;

    /**
     * Custom field group this field belongs to.
     *
     * @var CustomFieldGroupBase
     */
    protected CustomFieldGroup customFieldGroup;

    /**
     * Cache for field definition.
     *
     * @var CustomFieldDefinition
     */
    protected String definition = null;


    public CustomField(CustomFieldGroup customFieldGroup) {
        this.customFieldGroup = customFieldGroup;
    }

    //this function will populate the data of the ticket note instance when supplied with RawArrayElement derived from the xml
    @Override
    public CustomField populate(RawArrayElement rawArrayElement) throws KayakoException {

        return this;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomField) {
        HashMap<String, String> customFieldHashMap = new HashMap<String, String>();

        return customFieldHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    //TODO a lot of functions like getAll etc, plus creating new attachments from here...
}
