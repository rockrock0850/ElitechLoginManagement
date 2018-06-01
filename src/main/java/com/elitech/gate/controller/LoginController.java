package com.elitech.gate.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.elitech.gate.constant.SysStatus;
import com.elitech.gate.controller.base.BaseController;
import com.elitech.gate.tx.LoginTx;
import com.elitech.gate.util.AesCryptUtil;
import com.elitech.gate.util.RedirectUtil;
import com.elitech.gate.vo.exception.LogicException;
import com.elitech.gate.vo.form.LoginForm;
import com.elitech.gate.vo.form.ResetPasswdForm;
import com.elitech.gate.vo.redis.RedisVO;
import com.elitech.gate.vo.rest.ResponseVO;

/**
 * 登入作業
 * 
 * @create by Adam
 * @create date: Oct 23, 2017
 */
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/Function/LoginManagement")
public class LoginController extends BaseController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private LoginTx loginTx;
	
	@PostConstruct
	public void init() {}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView index (HttpServletRequest request) throws Exception {
		return new ModelAndView("gate.login_management");
	}
	
	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public ModelAndView authentication (HttpServletResponse response, @RequestBody LoginForm form) throws Exception {
		String accountId = form.getAccountId();
		String password = form.getAccountPasswd();
		
		LoginForm info = loginTx.findAccountInfo(accountId);
		if (info == null) {
			throw new LogicException(SysStatus.LOGIN_ERROR_1);
		}
		
		String accountPassword = info.getAccountPasswd();
		accountPassword = AesCryptUtil.decrypt(accountPassword);
		if (!StringUtils.equals(accountPassword, password)) {
			throw new LogicException(SysStatus.LOGIN_ERROR_1);
		}
		
		int status = Integer.valueOf(info.getStatus());
		
		if (status == -1) {// 變更密碼
			return new ModelAndView("gate.reset_password", "account", info);
		} else if (status != 1) {// 不等於1代表帳號已失效
			throw new LogicException(SysStatus.LOGIN_ERROR_6);
		}

		String appId = (String) session.getAttribute("appId");
		String root = (String) session.getAttribute("root");
		String url = loginTx.findRedirectURLById(accountId, appId);
		if (StringUtils.isBlank(url)) {
			throw new LogicException(SysStatus.LOGIN_ERROR_5);
		}
		
		url = root + url;
		String sessionId = session.getId();
		String name = info.getAccountName();
		
		RedisVO redisVO = new RedisVO();
		redisVO.setAccountId(accountId);
		redisVO.setAccountName(name);
		redisVO.setAccountPasswd(AesCryptUtil.encrypt(accountPassword));
		redisVO.setAppId(appId);
		loginTx.addLoginInfo(sessionId, redisVO, 5);
		
		sessionId = AesCryptUtil.encrypt(sessionId);
		session.removeAttribute("appId");
		new RedirectUtil(response, url, sessionId);
		
		return new ModelAndView("gate.login_management"); 
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPassword (HttpServletRequest request) throws Exception {
		String accountId = (String) session.getAttribute("accountId");
		
		LoginForm info = new LoginForm();
		info.setAccountId(accountId);
		
		return new ModelAndView("gate.reset_password", "account", info);
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public ResponseVO reset (HttpServletRequest request, @RequestBody ResetPasswdForm form) throws Exception {
		String accountId = form.getAccountId();
		String oriPasswd = form.getOriPassword();
		String newPassword = form.getNewPassword();

		LoginForm info = loginTx.findAccountInfo(accountId);
		String passwd = info.getAccountPasswd();
		passwd = AesCryptUtil.decrypt(passwd);
		if (!StringUtils.equals(passwd, oriPasswd)) {
			throw new LogicException(SysStatus.LOGIN_ERROR_2);
		}
		
		newPassword = AesCryptUtil.encrypt(newPassword);
		loginTx.editPassword(accountId, newPassword, String.valueOf(1));
		
		return new ResponseVO(SysStatus.SUCCESS_1);
	}

	@Override
	@ExceptionHandler({Exception.class, LogicException.class})
	protected Object handleExceptionMsg (HttpServletRequest req, Object e) {
		return super.handleExceptionMsg(req, e);
	}
	
}