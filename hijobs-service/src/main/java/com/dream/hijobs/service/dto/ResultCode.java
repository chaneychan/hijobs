package com.dream.hijobs.service.dto;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 错误结果
 * @author chaney.chan
 * 2014年9月2日
 */
public class ResultCode {

    private static final Logger logger = LoggerFactory.getLogger(ResultCode.class);

	public static final Map<Integer, ResultCode> codeMap = new HashMap<Integer, ResultCode>();
	
	public static final ResultCode SUCCESS = createResultCode(0, "success");
	
	public static final ResultCode DATA_NOT_EXISTS = createResultCode(1, "data not exist");
	public static final ResultCode CONN_ERROR = createResultCode(-1, "connection error or timeout");
	public static final ResultCode UNKNOW = createResultCode(-2, "unkonw error");
	public static final ResultCode SERVER_ERROR = createResultCode(-3, "server error");
	public static final ResultCode VER_ERROR = createResultCode(-4, "version error");
	public static final ResultCode SERIALIZE_ERROR = createResultCode(-5, "serialize error");
	public static final ResultCode TIMEOUT = createResultCode(-6, "timeout");
	public static final ResultCode DATA_EXPIRED = createResultCode(-7, "data expired");
	public static final ResultCode SERVER_CANT_WORK = createResultCode(-8, "server can not work");
    public static final ResultCode ILLEGAL_USER=createResultCode(-9, "The illegal user token failed validation");
	
	
	public static final ResultCode SMS_SEND_ERROR = createResultCode(-1001, "sms send error");
	public static final ResultCode OSS_PUT_ERROR = createResultCode(-1002, "oss put avatar error");
	public static final ResultCode VERIFY_CODE_ERROR = createResultCode(-1003, "verify code error");
	public static final ResultCode TOKEN_GENERATE_ERROR = createResultCode(-1004, "token generate error");
	public static final ResultCode TOKEN_PARSE_ERROR = createResultCode(-1005, "token parse error");
	public static final ResultCode USER_NOT_FIND = createResultCode(-1006, "user not find");
	public static final ResultCode COMPANY_EXPIRED = createResultCode(-1007, "company expired");
	public static final ResultCode COMPANY_NOT_FIND = createResultCode(-1008, "company not find");
	public static final ResultCode REGISTER_ERROR = createResultCode(-1009, "register error");
	public static final ResultCode USERNAME_PWD_NULL = createResultCode(-1010, "username or pwd is null");
	public static final ResultCode EMAIL_EXISTS = createResultCode(-1011, "email exists");
	public static final ResultCode OPENID_NULL = createResultCode(-1012, "openid is null");
	public static final ResultCode OPENID_EXISTS = createResultCode(-1013, "openid exists");
	public static final ResultCode PWD_ERROR = createResultCode(-1014, "pwd error");
	public static final ResultCode PROVINCE_CODE_PARAMETER_ERROR = createResultCode(-1015, "province code parameter error");
	public static final ResultCode CITY_CODE_PARAMETER_ERROR = createResultCode(-1016, "city code parameter error");
	public static final ResultCode FILE_UPLOAD_FAIL = createResultCode(-1017, "file upload fail");
	public static final ResultCode COMPANY_IS_NULL = createResultCode(-1018, "company is null");
	
	
	private int code = 0;
	private String message = null;

	private ResultCode(int code) {
		this.code = code;
	}

	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private static ResultCode createResultCode(int code, String message) {
		ResultCode resultCode = new ResultCode(code, message);
		codeMap.put(code, resultCode);
		logger.debug("create ResultCode {code = " + String.valueOf(code) +", message = " + message + "}");
		return resultCode;
	}
	
	public static ResultCode valueOf(int code) {
		ResultCode resultCode = codeMap.get(code);
		if(resultCode == null) {
			return UNKNOW;
		}
		return resultCode;
	}
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	public boolean isSuccess() {
		return code >= 0;
	}

	@Override
	public String toString() {
		return "code=" + code + ", msg=" + message;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && (obj instanceof ResultCode)) {
			ResultCode rc = (ResultCode)obj;
			return rc.getCode() == code;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return code;
	}
}
