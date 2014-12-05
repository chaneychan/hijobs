package com.dream.hijobs.dao.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 是否特殊省份标识
 * @author chaney.chan
 * 2014年11月21日
 */
public enum IsSpecialProvince {
	YES(1,"是"),
	NO(0,"否");
	private static final Map<String, Integer> MAPPING = new LinkedHashMap<String, Integer>();

	private static final Map<Integer, String> INVERSE_MAPPING = new LinkedHashMap<Integer, String>();

	private int value;

	private String text;

	static {
		for (IsSpecialProvince em : IsSpecialProvince.values()) {
			MAPPING.put(em.getText(), em.getValue());
			INVERSE_MAPPING.put(em.getValue(), em.getText());
		}
	}

	IsSpecialProvince(final int value, final String text) {
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
	
	public static IsSpecialProvince get(int enumValue) {
		for (IsSpecialProvince em : IsSpecialProvince.values()) {
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
