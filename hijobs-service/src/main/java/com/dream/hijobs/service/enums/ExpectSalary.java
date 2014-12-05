package com.dream.hijobs.service.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 期望薪资
 * @author chaney.chan
 * 2014年9月5日
 */
public enum ExpectSalary {
	ONE(1, "1800below"),
	TWO(2, "1800-2500"),
	THREE(3, "2500-3500"),
	FOUR(4, "3500-4500"),
	FIVE(5, "4500-5500"),
	SIX(6, "5500-8000"),
	SEVEN(7, "8000more");

	private static final Map<String, Integer> MAPPING = new LinkedHashMap<String, Integer>();

	private static final Map<Integer, String> INVERSE_MAPPING = new LinkedHashMap<Integer, String>();

	private int value;

	private String text;

	static {
		for (ExpectSalary em : ExpectSalary.values()) {
			MAPPING.put(em.getText(), em.getValue());
			INVERSE_MAPPING.put(em.getValue(), em.getText());
		}
	}

	ExpectSalary(final int value, final String text) {
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
	
	public static ExpectSalary get(int enumValue) {
		for (ExpectSalary em : ExpectSalary.values()) {
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
