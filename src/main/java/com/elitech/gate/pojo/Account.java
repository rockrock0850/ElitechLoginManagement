package com.elitech.gate.pojo;
// Generated Nov 1, 2017 1:46:10 PM by Hibernate Tools 4.0.0


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Account generated by hbm2java
 */
@Entity
@Table(name="account"
    ,schema="public"
)
public class Account  implements java.io.Serializable {


     private String accountId;
     private String accountPasswd;
     private String accountName;
     private String email;
     private String status;
     private String createUser;
     private Date createTime;
     private String modifyUser;
     private Date modifyTime;

    public Account() {
    }

	
    public Account(String accountId, String accountPasswd, String accountName, String createUser, Date createTime) {
        this.accountId = accountId;
        this.accountPasswd = accountPasswd;
        this.accountName = accountName;
        this.createUser = createUser;
        this.createTime = createTime;
    }
    public Account(String accountId, String accountPasswd, String accountName, String email, String status, String createUser, Date createTime, String modifyUser, Date modifyTime) {
       this.accountId = accountId;
       this.accountPasswd = accountPasswd;
       this.accountName = accountName;
       this.email = email;
       this.status = status;
       this.createUser = createUser;
       this.createTime = createTime;
       this.modifyUser = modifyUser;
       this.modifyTime = modifyTime;
    }
   
     @Id 

    
    @Column(name="account_id", unique=true, nullable=false)
    public String getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    
    @Column(name="account_passwd", nullable=false)
    public String getAccountPasswd() {
        return this.accountPasswd;
    }
    
    public void setAccountPasswd(String accountPasswd) {
        this.accountPasswd = accountPasswd;
    }

    
    @Column(name="account_name", nullable=false)
    public String getAccountName() {
        return this.accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    
    @Column(name="email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="status")
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name="create_user", nullable=false)
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", nullable=false, length=35)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    @Column(name="modify_user")
    public String getModifyUser() {
        return this.modifyUser;
    }
    
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modify_time", length=35)
    public Date getModifyTime() {
        return this.modifyTime;
    }
    
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }




}


