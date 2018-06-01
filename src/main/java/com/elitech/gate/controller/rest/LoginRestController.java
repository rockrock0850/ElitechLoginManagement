package com.elitech.gate.controller.rest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elitech.gate.constant.SysStatus;
import com.elitech.gate.controller.base.BaseController;
import com.elitech.gate.tx.LoginTx;
import com.elitech.gate.util.ApiUtil;
import com.elitech.gate.util.JsonUtil;
import com.elitech.gate.vo.exception.LogicException;
import com.elitech.gate.vo.form.LoginForm;
import com.elitech.gate.vo.rest.RequestVO;
import com.elitech.gate.vo.rest.ResponseVO;

/**
 * Login API
 * 
 * @create by Adam
 * @create date: Oct 23, 2017
 */
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/Rest")
public class LoginRestController extends BaseController {
	
	@Autowired
	private LoginTx loginTx;
	
	@PostConstruct
	public void init() {}
	
	@RequestMapping(value = "addAccount", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public ResponseVO addAccount (HttpServletRequest request, @RequestBody RequestVO reqVO) throws Exception {
		String data = reqVO.getData();
		data = ApiUtil.validate(data, loginTx);
		LoginForm login = JsonUtil.fromJson(data, LoginForm.class);
		loginTx.addAccount(login);
		
		return new ResponseVO(SysStatus.SUCCESS_1);
	}
	
	@RequestMapping(value = "editAccount", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public ResponseVO editAccount (HttpServletRequest request, @RequestBody RequestVO reqVO) throws Exception {
		String data = reqVO.getData();
		data = ApiUtil.validate(data, loginTx);
		LoginForm login = JsonUtil.fromJson(data, LoginForm.class);
		loginTx.editAccount(login);
		
		return new ResponseVO(SysStatus.SUCCESS_1);
	}

	@Override
	@ExceptionHandler({Exception.class, LogicException.class})
	protected Object handleExceptionMsg (HttpServletRequest req, Object e) {
		return super.handleExceptionMsg(req, e);
	}
	
}