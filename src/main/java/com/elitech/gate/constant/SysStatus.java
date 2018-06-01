package com.elitech.gate.constant;

/**
 * 系統訊息管理類別
 * 
 * @create by Adam
 */
public enum SysStatus { 

	/**
	 * 修改成功
	 */
	SUCCESS_1("200", "修改成功"),
	
	/**
	 * 帳號或密碼錯誤
	 */
	LOGIN_ERROR_1("999", "帳號或密碼錯誤"),

	/**
	 * 密碼錯誤
	 */
	LOGIN_ERROR_2("999", "密碼錯誤"),

	/**
	 * 查無此系統
	 */
	LOGIN_ERROR_3("999", "查無此系統"),

	/**
	 * 登入資訊錯誤
	 */
	LOGIN_ERROR_4("999", "登入資訊錯誤"),

	/**
	 * 權限不足登入失敗
	 */
	LOGIN_ERROR_5("999", "權限不足登入失敗"),

	/**
	 * 權限不足登入失敗
	 */
	LOGIN_ERROR_6("999", "此帳號已被停用，請與HR聯繫！"),

	/**
	 * 需求參數格式錯誤
	 */
	REST_ERROR_1("999", "需求參數格式錯誤"),

	/**
	 * 查無此系統
	 */
	REST_ERROR_2("999", "查無此系統"),

	/**
	 * 未知的錯誤
	 */
	UNKNOWN_ERROR("999", "未知的錯誤");
	
	private String code;
	private String message;
	
	private SysStatus(String message) {
		this.message = message;
	}
	
	private SysStatus(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getMsg() {
		return message;
	}

	public String getCode() {
		return code;
	}
	
}


