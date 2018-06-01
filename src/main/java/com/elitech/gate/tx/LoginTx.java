package com.elitech.gate.tx;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elitech.gate.service.LoginService;
import com.elitech.gate.vo.form.LoginForm;
import com.elitech.gate.vo.redis.RedisVO;

/**
 * 
 * @create by Adam
 */
@Service
public class LoginTx {

	@Autowired
	private LoginService loginService;
	
	@Transactional
	public LoginForm findAccountInfo (String id) {
		return loginService.findAccount(id);
	}

	@Transactional
	public boolean findApplication(String appId, String appKey) throws IOException {
		return loginService.findApplication(appId, appKey);
	}

	@Transactional
	public String findRedirectURLById(String id, String appId) throws IOException {
		return loginService.findRedirectURLById(id, appId);
	}

	/**
	 * 把登入資訊存到Cache
	 * 
	 * @create by Adam
	 * @create date: Nov 8, 2017
	 *
	 * @param key
	 * @param redisVO
	 * @param expireTime
	 */
	@Transactional
	public void addLoginInfo (String key, RedisVO redisVO, int expireTime) {
		loginService.addLoginInfo(key, redisVO, expireTime);
	}

	@Transactional
	public void addAccount (LoginForm login) {
		String appId = login.getAppId();
		String accountId = login.getAccountId();
		String accountName = login.getAccountName();
		String password = login.getAccountPasswd();
		String email = login.getEmail();
		String createUser = login.getCreateUser();
		
		loginService.addAccount(appId, accountId, accountName, password, email, createUser);
	}

	@Transactional
	public void editAccount (LoginForm login) {
		String accountId = login.getAccountId();
		String accountName = login.getAccountName();
		String email = login.getEmail();
		String status = login.getStatus();
		String modifyUser = login.getModifyUser();
		
		loginService.editAccount(accountId, accountName, email, status, modifyUser, new Date());
	}

	@Transactional
	public void editPassword(String accountId, String newPassword, String status) {
		loginService.editPassword(accountId, newPassword, status);
	}
	
}


