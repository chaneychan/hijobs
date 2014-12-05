package com.dream.hijobs.service.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 性别
 * <p>1-男，2-女，3-保密
 * @author chaney.chan
 * 2014年9月5日
 */
public enum Gender {
	MALE(1, "male"),
	FEMALE(2, "female"),
	SECRET(3, "secret");

	private static final Map<String, Integer> MAPPING = new LinkedHashMap<String, Integer>();

	private static final Map<Integer, String> INVERSE_MAPPING = new LinkedHashMap<Integer, String>();

	private int value;

	private String text;

	static {
		for (Gender em : Gender.values()) {
			MAPPING.put(em.getText(), em.getValue());
			INVERSE_MAPPING.put(em.getValue(), em.getText());
		}
	}

	Gender(final int value, final String text) {
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
	
	public static Gender get(int enumValue) {
		for (Gender em : Gender.values()) {
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
