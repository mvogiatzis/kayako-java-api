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
public class CustomFieldSelect extends CustomField {

    /**
     * Selected option.
     *
     * @var CustomFieldOption
     */
    protected CustomFieldOption selectedOption;


    public CustomFieldSelect(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }

    public CustomFieldOption getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(CustomFieldOption selectedOption) {
        this.selectedOption = selectedOption;
        if (this.selectedOption != null) {
            this.setRawValue(this.selectedOption.getValue());
        }

    }

    //TODO - think about getvalue, setvalue and decide how to implement

    @Override
    public CustomFieldSelect populate(RawArrayElement rawArrayElement) throws KayakoException {
        super.populate(rawArrayElement);
        int id = Helper.parseInt(rawArrayElement.getContent());
        if (id == 0) {
            this.setSelectedOption(this.getOption(rawArrayElement.getContent()));
        } else {
            this.setSelectedOption(this.getOption(id));
        }

        return this;
    }


    public HashMap<String, String> buildHashMap(Boolean newCustomFieldSelect) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (this.getSelectedOption() != null) {
            hashMap.put(this.getName(), Integer.toString(this.getSelectedOption().getId()));
        }
        return hashMap;
    }
}
