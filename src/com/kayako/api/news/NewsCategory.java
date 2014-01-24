package com.kayako.api.news;

import com.kayako.api.enums.AccessTypeEnum;
import com.kayako.api.exception.KayakoException;


public class NewsCategory {

	/**
     * The Controller.
     */
    static protected String controller = "/News/Category";
    
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "newscategories";
    
    /**
     * The Read only.
     */
    protected Boolean readOnly = false;
    
    /**
     * Instantiates a new News Category.
     */
    public NewsCategory(String Title, AccessTypeEnum VisibilityType) {
       this.title = Title;
       this.visibilityType = VisibilityType;
    }
    
    /**
     * @apiField
     * @var int
     */
    protected int id;
    
    /**
     * @apiField
     * @var string
     */
    protected String title;
    
    /**
     * @apiField
     * @var string
     */
    protected int newsitemcount;
    
    /**
     * @apiField
     * @var string
     */
    protected AccessTypeEnum visibilityType;
    
    public static NewsCategory get(int id) throws KayakoException {
    	
    }
    
    
    
}
