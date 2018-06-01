package com.elitech.gate.vo.form;

/**
 * 
 * @create by Adam
 */
public class ResetPasswdForm {

	private String accountId;
	private String oriPassword;
	private String newPassword;
	private String cofirmPassword;
	
	public String getOriPassword() {
		return oriPassword;
	}
	public void setOriPassword(String oriPassword) {
		this.oriPassword = oriPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getCofirmPassword() {
		return cofirmPassword;
	}
	public void setCofirmPassword(String cofirmPassword) {
		this.cofirmPassword = cofirmPassword;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}


