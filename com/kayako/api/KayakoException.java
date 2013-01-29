package com.kayako.api;

/**
 * The type Kayako exception.
 *
 * @author Rajat Garg
 * @package api
 * @copyright Copyright (c) 2001-2012, Kayako
 * @license http ://www.kayako.com/license
 * @link http ://www.kayako.com
 */
public class KayakoException extends Exception {
    KayakoException() {
        this("Generic Exception");
    }

    KayakoException(String str) {
        super(str);
    }

}
