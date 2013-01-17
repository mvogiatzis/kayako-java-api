package lib;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Custom field multi select.
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class CustomFieldMultiSelect extends CustomField {

    /**
     * Separator of main and linked select values.
     *
     * @var string
     */
    public static final String VALUE_SEPARATOR = ", ";

    /**
     * The Options.
     */
    protected ArrayList<CustomFieldOption> options = new ArrayList<CustomFieldOption>();

    /**
     * Instantiates a new Custom field multi select.
     *
     * @param customFieldGroup the custom field group
     */
    public CustomFieldMultiSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    /**
     * Gets options.
     *
     * @return the options
     */
    public ArrayList<CustomFieldOption> getOptions() {
        return options;
    }

    /**
     * Sets options.
     *
     * @param options the options
     * @return the options
     */
    public CustomFieldMultiSelect setOptions(ArrayList<CustomFieldOption> options) {
        this.options = options;
        String rawValue = "";
        for (CustomFieldOption customFieldOption : options) {
            rawValue += customFieldOption.getValue() + VALUE_SEPARATOR;
        }
        this.setRawValue(rawValue);
        return this;
    }

    /**
     * Sets options.
     *
     * @param option the option
     * @return the options
     */
    public CustomFieldMultiSelect setOptions(CustomFieldOption option) {
        ArrayList<CustomFieldOption> arrayList = new ArrayList<CustomFieldOption>();
        arrayList.add(option);
        return this.setOptions(arrayList);
    }

    /**
     * Add to options.
     *
     * @param customFieldOption the custom field option
     * @return the custom field multi select
     */
    public CustomFieldMultiSelect addToOptions(CustomFieldOption customFieldOption) {
        this.options.add(customFieldOption);
        return this;
    }

    /**
     * Gets values.
     *
     * @return the values
     */ //returns HashMap like (option.id => option.value)
    public HashMap<String, String> getValues() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (CustomFieldOption option : this.options) {
            hashMap.put(Integer.toString(option.getId()), option.getValue());
        }
        return hashMap;
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
