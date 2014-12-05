package com.dream.hijobs.service.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 设备类型
 * <p>1-本平台，2-qq，3-wechat，4-weibo
 * @author chaney.chan
 * 2014年9月5日
 */
public enum OpenType {
	OWN(-1,"own"),
	QQ(1, "qq"),
	WECHAT(2, "wechat"),
	WEIBO(3, "weibo");

	private static final Map<String, Integer> MAPPING = new LinkedHashMap<String, Integer>();

	private static final Map<Integer, String> INVERSE_MAPPING = new LinkedHashMap<Integer, String>();

	private int value;

	private String text;

	static {
		for (OpenType em : OpenType.values()) {
			MAPPING.put(em.getText(), em.getValue());
			INVERSE_MAPPING.put(em.getValue(), em.getText());
		}
	}

	OpenType(final int value, final String text) {
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
	
	public static OpenType get(int enumValue) {
		for (OpenType em : OpenType.values()) {
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
