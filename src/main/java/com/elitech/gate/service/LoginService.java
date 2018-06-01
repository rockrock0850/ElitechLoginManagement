package com.elitech.gate.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elitech.gate.constant.Resources;
import com.elitech.gate.dao.jdbc.JdbcDAO;
import com.elitech.gate.dao.jpa.AccountAppMappingRepo;
import com.elitech.gate.dao.jpa.AccountRepo;
import com.elitech.gate.dao.jpa.ApplicationSettingRepo;
import com.elitech.gate.dao.redis.LoginRedisRepo;
import com.elitech.gate.pojo.Account;
import com.elitech.gate.pojo.AccountMappingApplication;
import com.elitech.gate.pojo.AccountMappingApplicationId;
import com.elitech.gate.pojo.ApplicationSetting;
import com.elitech.gate.pojo.ApplicationSettingId;
import com.elitech.gate.util.ResourceUtil;
import com.elitech.gate.vo.form.LoginForm;
import com.elitech.gate.vo.redis.RedisVO;

/**
 * 
 * @create by Adam
 */
@Service
public class LoginService {

	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private ApplicationSettingRepo appRepo;
	
	@Autowired
	private JdbcDAO jdbcDAO;
	
	@Autowired
	private AccountAppMappingRepo accountAppMappingRepo;
	
	@Autowired
	private LoginRedisRepo loginRedisRepo;
	
	public LoginForm findAccount (String id) {
		Account pojo = accountRepo.findOne(id);
		
		if (pojo == null) {return null;}
		
		LoginForm vo = new LoginForm();
		BeanUtils.copyProperties(pojo, vo);
		
		return vo;
	}

	public boolean findApplication(String appId, String appKey) {
		ApplicationSettingId id = new ApplicationSettingId();
		id.setAppId(appId);
		id.setAppKey(appKey);
		ApplicationSetting pojo = appRepo.findOne(id);
		
		return pojo == null ? false : true;
	}

	public String findRedirectURLById(String id, String appId) throws IOException {
		String sqlText = ResourceUtil.get(Resources.SQL_LOGIN, "FIND_REDIRECT_URL_BY_ID");
		
		Map<String, Object> params = new HashMap<>();
		params.put("ACCOUNT_ID", id);
		params.put("APP_ID", appId);
		
		Map<String, Object> resultMap = jdbcDAO.queryForMap(sqlText, params);
		System.out.println(resultMap);
		
		return MapUtils.getString(resultMap, "REDIRECT_URL", "");
	}

	public void addLoginInfo (String key, RedisVO redisVO, int expireTime) {
		String accountId = redisVO.getAccountId();
		String modifyUser = redisVO.getAccountName();
		String appId = redisVO.getAppId();
		
		loginRedisRepo.save(key, redisVO);
		loginRedisRepo.setExpire(key, expireTime);
		accountAppMappingRepo.updateLastLoginTime(accountId, appId, modifyUser);
	}

	public void addAccount (String appId, String accountId, String accountName, String accountPasswd, String email, String createUser) {
		Account pojo = new Account();
		pojo.setAccountId(accountId);
		pojo.setAccountName(accountName);
		pojo.setAccountPasswd(accountPasswd);
		pojo.setEmail(email);
		pojo.setStatus("-1");
		pojo.setCreateUser(createUser);
		pojo.setCreateTime(new Date());
		
		accountRepo.save(pojo);
		
		AccountMappingApplicationId id = new AccountMappingApplicationId();
		id.setAccountId(accountId);
		id.setAppId(appId);
		
		AccountMappingApplication pojo2 = new AccountMappingApplication();
		pojo2.setId(id);
		pojo2.setCreateUser(createUser);
		pojo2.setCreateTime(new Date());
		
		accountAppMappingRepo.save(pojo2);
	}

	public void editAccount (String accountId, String accountName, String email, String status, String modifyUser, Date date) {
		status = StringUtils.equals(status, "1") ? "1" : "0";
		accountRepo.updateAccountStatus(accountId, accountName, email, status, modifyUser, date);
	}

	public void editPassword(String accountId, String newPassword, String status) {
		accountRepo.updateAccountPassword(newPassword, accountId, status);
	}
	
}


