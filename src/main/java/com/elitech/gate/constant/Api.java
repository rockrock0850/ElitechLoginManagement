package com.elitech.gate.constant;

/**
 * 定義API路徑常數
 * 
 * @create by Adam
 */
public enum Api {

	// 登入系統的 API
	
	/**
	 * 新增使用者: /ElitechLoginManagement/Rest/addAccount
	 */
	LOGIN_ADD_ACCOUNT("/ElitechLoginManagement/Rest/addAccount");

	private String action;

	/**
	 * @create by Adam
	 * @create date: Nov 16, 2017
	 *
	 * @param root
	 */
	private Api (String action) {
		this.action = action;
	}

	public String getAction () {
		return action;
	}
	
}


