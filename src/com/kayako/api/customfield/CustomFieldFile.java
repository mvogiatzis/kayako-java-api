package com.kayako.api.customfield;

import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.Helper;
import net.iharder.Base64;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The type Custom field file.
 * @author Kayako Support System Pvt Ltd
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class CustomFieldFile extends CustomField {

    /**
     * The Object xml name.
     */
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

    /**
     * Get contents.
     *
     * @return the byte [ ]
     */
    public byte[] getContents() {
        return contents;
    }

    /**
     * Sets contents.
     *
     * @param contents the contents
     */
    public void setContents(byte[] contents) {
        if (!Arrays.equals(contents, this.contents)) {
            this.setChanged(true);
        }
        this.contents = contents;
    }

    /**
     * Sets content from file.
     *
     * @param file the file
     * @return the content from file
     */
    public CustomFieldFile setContentFromFile(File file) {
        try {
            this.contents = Helper.readBytesFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.getFileName().equals(null)) {
            this.setFileName(file.getName());
        }
        this.setRawValue(Base64.encodeBytes(this.contents));
        return this;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        if (fileName != this.fileName) {
            this.setChanged(true);
        }
        this.fileName = fileName;
    }

    /**
     * Gets changed.
     *
     * @return the changed
     */
    public Boolean getChanged() {
        return isChanged;
    }

    /**
     * Sets changed.
     *
     * @param changed the changed
     */
    public void setChanged(Boolean changed) {
        isChanged = changed;
    }

    /**
     * Instantiates a new Custom field file.
     *
     * @param customFieldGroup the custom field group
     */
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
        //this function returns empty HashMap as functional method is buildFileHashMap here
        return hashMap;
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap() {
        return this.buildFilesHashMap(false);
    }

    public HashMap<String, HashMap<String, String>> buildFilesHashMap(Boolean newCustomFieldFile) {
        HashMap<String, String> file = new HashMap<String, String>();
        file.put(this.getFileName(), new String(this.getContents()));
        HashMap<String, HashMap<String, String>> fileHashMap = new HashMap<String, HashMap<String, String>>();
        fileHashMap.put(this.getName(), file);
        return fileHashMap;
    }

}
