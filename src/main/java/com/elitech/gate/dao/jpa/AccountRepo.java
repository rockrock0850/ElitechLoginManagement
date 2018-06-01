package com.elitech.gate.dao.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elitech.gate.pojo.Account;

/**
 * 
 * @create by Adam
 */
public interface AccountRepo extends JpaRepository<Account, String> {
	
	@Modifying
	@Query("UPDATE Account T1 SET T1.accountPasswd = :pwd, T1.status = :status WHERE T1.accountId = :id")
	public void updateAccountPassword(
			@Param("pwd") String newPassword, 
			@Param("id") String accountId, 
			@Param("status") String status);

	@Modifying
	@Query("UPDATE Account T1 SET " + 
			"T1.accountName = :accountName, " + 
			"T1.email = :email, " + 
			"T1.status = :status, " + 
			"T1.modifyUser = :modifyUser, " + 
			"T1.modifyTime = :modifyTime " + 
			"WHERE " + 
			"T1.accountId = :accountId")
	public void updateAccountStatus (
			@Param("accountId") String accountId, 
			@Param("accountName") String accountName, 
			@Param("email") String email,
			@Param("status") String status, 
			@Param("modifyUser")String modifyUser, 
			@Param("modifyTime")Date modifyTime);
	
}


