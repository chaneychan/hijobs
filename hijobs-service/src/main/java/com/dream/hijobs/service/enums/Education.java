package com.dream.hijobs.service.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 学历
 * @author chaney.chan
 * 2014年9月5日
 */
public enum Education {
	NOT(1, "无"),
	ELEMENTARY_SCHOOL(2, "小学"),
	JUNIOR_HIGH_SCHOOL(3, "初中"),
	HIGH_SCHOOL(4, "高中,中专,技校"),
	JUNIOR_COLLEGA(5, "大专"),
	BACHELOR(6, "本科"),
	MASTER(7, "研究生以及上");

	private static final Map<String, Integer> MAPPING = new LinkedHashMap<String, Integer>();

	private static final Map<Integer, String> INVERSE_MAPPING = new LinkedHashMap<Integer, String>();

	private int value;

	private String text;

	static {
		for (Education em : Education.values()) {
			MAPPING.put(em.getText(), em.getValue());
			INVERSE_MAPPING.put(em.getValue(), em.getText());
		}
	}

	Education(final int value, final String text) {
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
	
	public static Education get(int enumValue) {
		for (Education em : Education.values()) {
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
