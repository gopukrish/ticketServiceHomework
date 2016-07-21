/**
 * 
 */
package com.walmart.ticketservice.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

import com.walmart.ticketservice.util.Messages;

/**
 * @author Gopal 
 * Class to cache all properties from file
 */
public class PropertiesCache implements Messages {
	static ResourceBundle rs;
	static Map<String, String> propsCacheMap;
	static Logger log = Logger.getLogger(PropertiesCache.class);

	static {
		try {
			rs = ResourceBundle.getBundle(PROPS_FILE);
			propsCacheMap = new HashMap<String, String>();
			cacheAllProperties();
		} catch (Exception e) {
			log.error(PROPS_READ_ERROR + " : " + e.getStackTrace());
		}
	}

	private static void cacheAllProperties() {
		log.debug("Caching properties");
		Enumeration<String> propKeys = rs.getKeys();
		while (propKeys.hasMoreElements()) {
			String key = propKeys.nextElement();
			propsCacheMap.put(key, rs.getString(key));
		}
		log.debug("Cached properties : " + propsCacheMap);
	}

	public static String getValue(String property) {
		return propsCacheMap.get(property);
	}

	public static String getValue(String property, String defaultValue) {
		if ((propsCacheMap.get(property) != null)
				&& (propsCacheMap.get(property) != ""))
			return propsCacheMap.get(property);
		else
			return defaultValue;
	}
}
