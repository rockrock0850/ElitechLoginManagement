package com.elitech.gate.controller.common;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.elitech.gate.controller.base.BaseController;
import com.elitech.gate.vo.exception.LogicException;

/**
 * 功能控制類別
 * 
 * @create by Adam
 * @create date: Oct 23, 2017
 */
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/Entries")
public class EntriesController extends BaseController {

	// 功能路徑
	private final String LOGIN_MANAGEMENT = "/Function/LoginManagement";
	private final String RESET_PASSWORD = "/Function/LoginManagement/resetPassword";
	
	@PostConstruct
	public void init() {}

	@RequestMapping(value = "/LoginManagement", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView LoginManagement (HttpServletRequest request) throws Exception {
		return new ModelAndView("one.column.theme", "func", LOGIN_MANAGEMENT);
	}

	@RequestMapping(value = "/ResetPassword", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView ResetPassword (HttpServletRequest request) throws Exception {
		return new ModelAndView("one.column.theme", "func", RESET_PASSWORD);
	}

	@Override
	@ExceptionHandler({Exception.class, LogicException.class})
	protected Object handleExceptionMsg (HttpServletRequest req, Object e) {
		return super.handleExceptionMsg(req, e);
	}
	
}