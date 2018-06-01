package com.elitech.gate.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitech.gate.pojo.ApplicationSetting;
import com.elitech.gate.pojo.ApplicationSettingId;

/**
 * 
 * @create by Adam
 */
public interface ApplicationSettingRepo extends JpaRepository<ApplicationSetting, ApplicationSettingId> {}


