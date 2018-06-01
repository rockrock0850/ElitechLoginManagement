package com.elitech.gate.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elitech.gate.constant.Api;
import com.elitech.gate.constant.SysStatus;
import com.elitech.gate.tx.LoginTx;
import com.elitech.gate.vo.exception.LogicException;
import com.elitech.gate.vo.rest.RequestVO;
import com.elitech.gate.vo.rest.ResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;

/**
 * 
 * @create by Adam
 */
public class ApiUtil {
	
	private static Logger log = Logger.getLogger(ApiUtil.class);

	/**
	 * 
	 * @param api
	 * @param data
	 * @param requestMethod
	 * @return
	 * @throws Exception
	 */
	public static ResponseVO call (Api api, RequestVO reqVO, RequestMethod requestMethod) throws Exception {
		URL obj = null;
		HttpURLConnection con = null;
		DataOutputStream wr = null;
		
		String jsonData = reqVO.getData();
		String base64Str = Base64.getEncoder().encodeToString(jsonData.getBytes());
		reqVO.setData(base64Str);

		String action = api.getAction();
		String domain = PropUtil.getProperty("login.domain");
		String url = domain + action;
		String method = requestMethod.toString();
		String reqData = JsonUtil.toJson(reqVO);
		
		switch (StringUtils.upperCase(method)) {
			case "POST":
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod(method);
				con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				con.setDoOutput(true);
				
				wr = new DataOutputStream(con.getOutputStream());
				wr.write(reqData.getBytes("utf-8"));
				wr.flush();
				wr.close();
				
				break;
				
			case "GET":
				obj = new URL(url + "/" + reqData);
				con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod(method);
				con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				
				break;
				
			case "PUT":
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod(method);
				con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				con.setDoOutput(true);
				
				wr = new DataOutputStream(con.getOutputStream());
				wr.write(reqData.getBytes("utf-8"));
				wr.flush();
				wr.close();
				
				break;
				
			case "DELETE":
				obj = new URL(url + "/" + reqData);
				con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod(method);
				con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				
				break;
		}
	
		int responseCode = con.getResponseCode();
		log.info("Response Code : " + responseCode);
		
		if (responseCode != 200) {
			throw new LogicException(SysStatus.UNKNOWN_ERROR);
		}
		
		ResponseVO responseVO = new ResponseVO();
		StringBuffer result = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			result.append(inputLine);
		}
		in.close();
		
		responseVO = new ObjectMapper().readValue(result.toString(), ResponseVO.class);
		
		return responseVO;
	}
	
	/**
	 * 驗證Request的資料是否符合規範
	 * 
	 * @param data 
	 * @param loginTx 
	 * @create by Adam
	 * @create date: Jan 5, 2018
	 *
	 * @return
	 * @throws Exception 
	 */
	public static String validate (String data, LoginTx loginTx) throws Exception {
		if (StringUtils.isBlank(data)) {
			throw new LogicException(SysStatus.REST_ERROR_1);
		}
		
		byte[] b = Base64.getDecoder().decode(data.getBytes());
		data = new String(b, Charsets.UTF_8);
		
		JSONObject json = new JSONObject(data);
		String appId = json.optString("appId");
		String appKey = getAppKey(json.optString("appKey"));
		
		boolean result = loginTx.findApplication(appId, appKey);
		if (!result) {
			throw new LogicException(SysStatus.REST_ERROR_2);
		}
		
		return data;
	}

	private static String getAppKey(String key) throws Exception {
		String sp[] = key.split("\\|");
		String decodeKey = sp[1];
		decodeKey = AesCryptUtil.decrypt(decodeKey);
		
		return decodeKey;
	}
	
}


