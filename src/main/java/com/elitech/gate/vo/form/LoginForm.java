package com.elitech.gate.vo.form;

/**
 * 
 * @create by Adam
 */
public class LoginForm {

	private String appId;
    private String accountId;
    private String accountPasswd;
    private String accountName;
    private String email;
    private String status;
    private String createUser;
    private String modifyUser;
    
	public String getAccountId () {
		return accountId;
	}
	public void setAccountId (String accountId) {
		this.accountId = accountId;
	}
	public String getAccountPasswd () {
		return accountPasswd;
	}
	public void setAccountPasswd (String accountPasswd) {
		this.accountPasswd = accountPasswd;
	}
	public String getAccountName () {
		return accountName;
	}
	public void setAccountName (String accountName) {
		this.accountName = accountName;
	}
	public String getEmail () {
		return email;
	}
	public void setEmail (String email) {
		this.email = email;
	}
	public String getStatus () {
		return status;
	}
	public void setStatus (String status) {
		this.status = status;
	}
	public String getAppId () {
		return appId;
	}
	public void setAppId (String appId) {
		this.appId = appId;
	}
	public String getCreateUser () {
		return createUser;
	}
	public void setCreateUser (String createUser) {
		this.createUser = createUser;
	}
	public String getModifyUser () {
		return modifyUser;
	}
	public void setModifyUser (String modifyUser) {
		this.modifyUser = modifyUser;
	}
	
}


