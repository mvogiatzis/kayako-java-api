package lib;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class CustomFieldFile extends CustomField {


    static protected String objectXmlName = "field";

    /**
     * File name.
     *
     * @apiField
     * @var string
     */
    protected String fileName;

    /**
     * File contents.
     *
     * @var string
     */
    protected byte[] contents;

    /**
     * Was the file changed after fetching or creating.
     *
     * @var bool
     */
    protected Boolean isChanged = false;


    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        if (!Arrays.equals(contents, this.contents)) {
            this.setChanged(true);
        }
        this.contents = contents;
    }


    public CustomFieldFile setContentFromFile(File file) {
        try {
            this.contents = Helper.readBytesFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        if (fileName != this.fileName) {
            this.setChanged(true);
        }
        this.fileName = fileName;
    }

    public Boolean getChanged() {
        return isChanged;
    }

    public void setChanged(Boolean changed) {
        isChanged = changed;
    }

    public CustomFieldFile(CustomFieldGroup customFieldGroup) {
        super(customFieldGroup);
    }


    @Override
    public CustomFieldFile populate(RawArrayElement rawArrayElement) throws KayakoException {
        if (!rawArrayElement.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        this.setFileName(rawArrayElement.getAttribute("filename"));

        try {
            this.setContents(Base64.decode(rawArrayElement.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public HashMap<String, String> buildHashMap(Boolean newCustomFieldFile) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (this.getChanged()) {
            //TODO : This structure is to be taken care off in abstract custom entity class, changes might be required
            //hashMap.put("name", this.ge());
            hashMap.put(KEntity.FILES_DATA_NAME, this.getFileName());
            hashMap.put(this.getFileName(), new String(this.getContents()));
        }
        return hashMap;
    }


}
