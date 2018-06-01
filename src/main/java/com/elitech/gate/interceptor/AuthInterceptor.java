package com.elitech.gate.interceptor;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.elitech.gate.constant.SysStatus;
import com.elitech.gate.tx.LoginTx;
import com.elitech.gate.util.AesCryptUtil;
import com.elitech.gate.vo.exception.LogicException;

public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private LoginTx tx;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String data = (String) request.getParameter("data");
		
		if (StringUtils.isBlank(data)) {return true;}
		
		byte[] b = Base64.getDecoder().decode(data.getBytes());
		data = new String(b, "utf-8");
		
		JSONObject json = new JSONObject(data);
		String sourceRoot = json.optString("sourceRoot");
		String appId = json.optString("appId");
		String appKey = getAppKey(json.optString("appKey"));
		
		boolean result = tx.findApplication(appId, appKey);
		if (!result) {
			throw new LogicException(SysStatus.LOGIN_ERROR_3);
		}
		
		String accountId = json.optString("accountId");
		
		HttpSession session = request.getSession();
		session.setAttribute("appId", appId);
		session.setAttribute("root", sourceRoot);
		session.setAttribute("accountId", accountId);

		return true;
	}

	private String getAppKey(String key) throws Exception {
		String sp[] = key.split("\\|");
		String decodeKey = sp[1];
		decodeKey = AesCryptUtil.decrypt(decodeKey);
		
		return decodeKey;
	}

}
