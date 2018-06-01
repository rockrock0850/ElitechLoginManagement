package com.elitech.gate.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elitech.gate.pojo.AccountMappingApplication;
import com.elitech.gate.pojo.AccountMappingApplicationId;

/**
 * 
 * @create by Adam
 */
public interface AccountAppMappingRepo extends JpaRepository<AccountMappingApplication, AccountMappingApplicationId> {
	
	@Modifying
	@Query("UPDATE AccountMappingApplication T1 " + 
			"SET T1.lastlogintime = NOW(), " + 
			"T1.modifyTime = NOW(), " + 
			"T1.modifyUser = :modifyUser WHERE " + 
			"T1.id.accountId = :accountId AND " + 
			"T1.id.appId = :appId")
	public void updateLastLoginTime (
			@Param("accountId") String accountId, 
			@Param("appId") String appId,
			@Param("modifyUser") String modifyUser);
	
}


