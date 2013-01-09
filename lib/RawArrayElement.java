package lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * ###############################################
 * <p/>
 * Kayako App
 * _______________________________________________
 *
 * @author Rajat Garg
 * @package lib
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http://www.kayako.com/license
 * @link http://www.kayako.com
 * <p/>
 * ###############################################
 */
public class RawArrayElement {

    Map<String, String> attributes = new HashMap<String, String>();
    //Map<String, RawArrayElement> components = new HashMap<String, RawArrayElement>();
    ArrayList<RawArrayElement> components = new ArrayList<RawArrayElement>();
    private String content;
    private Boolean isComposite;
    private String elementName;

    //Constructors overloaded to be used according to the requirement
    public RawArrayElement() {
        this("Auto Element");
    }

    public RawArrayElement(String elementName) {
        this.elementName = elementName;
        this.isComposite = false;

    }

    public RawArrayElement(String elementName, Map<String, String> attributes) {
        this.elementName = elementName;
        this.attributes = attributes;
        this.isComposite = false;
    }

    public RawArrayElement(String elementName, String content) {
        this.elementName = elementName;
        this.content = content;
        this.isComposite = false;
    }

    public RawArrayElement(String elementName, Map<String, String> attributes, String content) {
        this.isComposite = false;
        this.elementName = elementName;
        this.attributes = attributes;
        this.content = content;
    }

    public String get() throws IllegalAccessException {
        if (this.isComposite()) {
            throw new IllegalAccessException();
        }
        return this.getContent();
    }


    public ArrayList<RawArrayElement> getComponents() {
        return this.components;
    }

    public ArrayList<RawArrayElement> getComponents(String elementName) {
        ArrayList<RawArrayElement> filteredComponents = new ArrayList<RawArrayElement>();
        for (int i = 0; i < this.components.size(); i++) {
            if (this.components.get(i).elementName.equalsIgnoreCase(elementName)) {
                filteredComponents.add(this.components.get(i));
            }
        }
        return filteredComponents;
    }

    //this function can be used as a filter on RawArrayElement type, so as to act as a generic filter on all element types
    public ArrayList<RawArrayElement> filterBy(String filterName, String value) throws KayakoException {
        ArrayList<RawArrayElement> filteredComponents = new ArrayList<RawArrayElement>();
        for (RawArrayElement component : this.getComponents()) {
            ArrayList<RawArrayElement> filterComponents = component.getComponents(filterName);
            if (filterComponents.size() == 0) {
                throw new KayakoException("Supplied filter not available on this data.");
            }
            //there may be a case of multiple components to be used as a filter - any of the filtered attribute equals
            for (RawArrayElement filterComponent : filterComponents) {
                if (Pattern.compile(Pattern.quote(value), Pattern.CASE_INSENSITIVE).matcher(filterComponent.getContent()).find()) {
                    filteredComponents.add(component);
                    break;
                }
            }
        }
        return filteredComponents;
    }

    public String get(String key) {
        return this.getAttribute(key);
    }

    public RawArrayElement put(String content) {
        this.setComposite(false);
        this.setContent(content);
        return this;
    }

    public RawArrayElement put(RawArrayElement component) {
        this.setComposite(true);
        this.components.add(component);
        return this;
    }

    public String getAttribute(String key) {
        return this.attributes.get(key);
    }


    public RawArrayElement setAttribute(String key, String value) {
        this.attributes.put(key, value);
        return this;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String toString() {
        String rawArrayElementString = "";
        rawArrayElementString += "<" + this.elementName;
        for (Map.Entry<String, String> attribute : this.getAttributes().entrySet()) {
            rawArrayElementString += " " + attribute.getKey() + " = '" + attribute.getValue() + "' ";
        }
        rawArrayElementString += ">\n";
        if (this.isComposite()) {
            for (RawArrayElement component : this.components) {
                rawArrayElementString += component.toString();
            }
        } else {
            rawArrayElementString += this.content + "\n";
        }
        rawArrayElementString += "</" + this.elementName + ">\n";
        return rawArrayElementString;
    }

    //Simple getters and setters
    public String getContent() {
        return content;
    }

    public RawArrayElement setContent(String content) {
        if (this.isComposite) {
            return this;
        }
        this.content = content;
        return this;
    }

    public Boolean isComposite() {
        return this.isComposite;
    }

    public String getElementName() {
        return elementName;
    }

    public RawArrayElement setElementName(String elementName) {
        this.elementName = elementName;
        return this;
    }

    public RawArrayElement setComposite(Boolean composite) {
        isComposite = composite;
        return this;
    }
}
