package com.dream.hijobs.service.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 设备类型
 * <p>1-android，2-ios，3-wp，4-blackberry，-1-未知
 * @author chaney.chan
 * 2014年9月5日
 */
public enum DevType {
	UNKNOWN(-1,"unknown"),
	ANDROID(1, "android"),
	IOS(2, "ios"),
	WP(3, "wp"),
	BLACKBERRY(4, "blackberry");

	private static final Map<String, Integer> MAPPING = new LinkedHashMap<String, Integer>();

	private static final Map<Integer, String> INVERSE_MAPPING = new LinkedHashMap<Integer, String>();

	private int value;

	private String text;

	static {
		for (DevType em : DevType.values()) {
			MAPPING.put(em.getText(), em.getValue());
			INVERSE_MAPPING.put(em.getValue(), em.getText());
		}
	}

	DevType(final int value, final String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

	public boolean isEqual(int value){
		return this.value == value;
	}
	
	public static DevType get(int enumValue) {
		for (DevType em : DevType.values()) {
			if (em.getValue() == enumValue) {
				return em;
			}
		}
		throw new IllegalArgumentException("Can't get enum with this enumValue.");
	}

	public static Map<String, Integer> mapping() {
		return MAPPING;
	}

	public static Map<Integer, String> inverseMapping() {
		return INVERSE_MAPPING;
	}
}
