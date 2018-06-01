package com.elitech.gate.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.elitech.gate.util.LogUtil;
import com.elitech.gate.vo.exception.LogicException;

/**
 * 基礎控制類別
 * 
 * @create by Adam
 */
public class BaseController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 錯誤訊息管理
	 * 
	 * @create by Adam
	 * @create date: Oct 27, 2017
	 *
	 * @param req
	 * @param exception
	 * @return
	 */
	protected Object handleExceptionMsg (HttpServletRequest req, Object e) {
		String accept = req.getHeader("Content-Type");
		LogicException logicException;
		ModelAndView modelAndView = new ModelAndView();
		
		if (e instanceof LogicException) {
			logicException = (LogicException) e;
		} else {
			Exception exception = (Exception) e;
			String type = exception.getClass().getSimpleName();
			String msg = exception.getMessage();
			String target = LogUtil.getExceptionClazz(exception);
			
			logicException = new LogicException("99999", type + ": " + msg, target);
			log.error("Print Stack Trace: ", exception);	
		}

		if (StringUtils.contains(accept, "json")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(logicException);
		}
		
		modelAndView.addObject("exception", logicException);
		modelAndView.setViewName("common.error");
		
		return modelAndView;
	}
	
}
