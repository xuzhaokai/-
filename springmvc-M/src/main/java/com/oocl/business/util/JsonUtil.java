package com.oocl.business.util;


import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public static ObjectMapper objectMapper;

	public static <T> T readValue(String jsonStr, Class<T> clazz) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toJSon(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
