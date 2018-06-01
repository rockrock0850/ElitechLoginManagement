package com.elitech.gate.dao.jdbc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public class JdbcDAO extends NamedParameterJdbcDaoSupport {

	/**
	 * 更新
	 *  
	 * @param sqlText
	 * @return int
	 */
	public int update (String sqlText) {
		return super.getJdbcTemplate().update(sqlText);
	}
	
	public int update (String sqlText, Map<String, Object> sqlParams) {
		return super.getNamedParameterJdbcTemplate().update(sqlText, sqlParams);
	}
	
	/**
	 * 批次更新
	 * 
	 * @param sqlText
	 * @param sqlParam
	 * @return int[]
	 */
	public int[] batchUpdate (String sqlText, List<Map<String, Object>> sqlParam) {
		MapSqlParameterSource[] mapParamArr = new MapSqlParameterSource[sqlParam.size()]; 
		
		for(int i = 0 ; i < sqlParam.size() ; i++) {
			Map<String, ?> m = sqlParam.get(i);
			mapParamArr[i] = new MapSqlParameterSource(m);
		}
		
		int[] effCount = 
				super.getNamedParameterJdbcTemplate().batchUpdate(sqlText, mapParamArr);
		
		return effCount;
	}
	
	/**
	 * 帶參數進行查詢
	 * 
	 * @param sqlText
	 * @param params
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryForMap(String sqlText, Map<String, Object> params) {
		List<Map<String, Object>> dataLs = getNamedParameterJdbcTemplate().queryForList(sqlText, params);
		
		int size = dataLs != null ? dataLs.size() : -1;
		
		if(dataLs != null && dataLs.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(1, size);
		}
		
		if(size == 1) {
			return dataLs.get(0);
		} 
		
		return null;
	}
	
	/**
	 * 不帶參數進行查詢
	 * 
	 * @param sqlText
	 * @param clazz
	 * @return
	 */
	public <T> List<Map<String, Object>> queryForList (String sqlText) {
		List<Map<String, Object>> rtnLs = 
				super.getNamedParameterJdbcTemplate().queryForList(sqlText, new HashMap<String, Object>());
		
		if(CollectionUtils.isEmpty(rtnLs)) {
			return Collections.emptyList();
		}
		
		return rtnLs;
	}
	
	/**
	 * 帶參數進行查詢
	 * 
	 * @param sqlText
	 * @param clazz
	 * @return
	 */
	public <T> List<Map<String, Object>> queryForList (String sqlText, Map<String, Object> params) {
		List<Map<String, Object>> rtnLs = 
				super.getNamedParameterJdbcTemplate().queryForList(sqlText, params);
		
		if(CollectionUtils.isEmpty(rtnLs)) {
			return Collections.emptyList();
		}
		
		return rtnLs;
	}
	
}
