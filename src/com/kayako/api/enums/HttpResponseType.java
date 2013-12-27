package com.kayako.api.enums;

public enum HttpResponseType {
	XML, JSON, PLAIN;
	/**
	 * Gets string.
	 * 
	 * @return the string
	 */
	public String getString() {
		switch (this) {
		case XML:
			return "xml";
		case JSON:
			return "json";
		case PLAIN:
			return "plain";
		default:
			return "";
		}
	}

	/**
	 * Gets enum.
	 * 
	 * @param response
	 *            the access
	 * @return the enum
	 */
	public static HttpResponseType getEnum(String response) {
		if (response.equalsIgnoreCase("plain")) {
			return PLAIN;
		} else if (response.equalsIgnoreCase("json")) {
			return JSON;
		}else {
			return XML;
		}
	}
}