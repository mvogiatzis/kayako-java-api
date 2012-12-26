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
public class CustomFieldMultiSelect extends CustomField {


    /**
     * Separator of main and linked select values.
     *
     * @var string
     */
    public static final String VALUE_SEPARATOR = " ,";

    protected ArrayList<CustomFieldOption> options = new ArrayList<CustomFieldOption>();

    public CustomFieldMultiSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    public ArrayList<CustomFieldOption> getOptions() {
        return options;
    }

    public CustomFieldMultiSelect setOptions(ArrayList<CustomFieldOption> options) {
        this.options = options;
        String rawValue = "";
        for (CustomFieldOption customFieldOption : options) {
            rawValue += customFieldOption.getValue() + VALUE_SEPARATOR;
        }
        this.setRawValue(rawValue);
        return this;
    }

    public CustomFieldMultiSelect addToOptions(CustomFieldOption customFieldOption) {
        this.options.add(customFieldOption);
        return this;
    }


    //TODO setValue, getValue

    @Override
    public CustomFieldMultiSelect populate(RawArrayElement rawArrayElement) throws KayakoException {
        super.populate(rawArrayElement);
        String[] values = rawArrayElement.getContent().split(VALUE_SEPARATOR);
        for (String value : values) {
            this.addToOptions(this.getOption(value));
        }
        return this;
    }


    public HashMap<String, String> buildHashMap(Boolean newCustomFieldMultiSelect) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        int i = 0;
        for (CustomFieldOption customFieldOption : this.getOptions()) {
            hashMap.put(this.getName() + "[" + Integer.toString(i++) + "]", Integer.toString(customFieldOption.getId()));
        }
        return hashMap;
    }
}
