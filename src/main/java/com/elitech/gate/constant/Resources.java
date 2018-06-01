package com.elitech.gate.constant;

/**
 * 定義專案資源路徑常數
 * 
 * @create by Adam
 */
public enum Resources {

	// Properties root
	PROP("properties."),
	
	// SQL directory
	SQL_LOGIN("/sql", "/login_management", ".sql");

	private String root;
	private String dir;
	private String extension;

	/**
	 * @create by Adam
	 * @create date: Nov 16, 2017
	 *
	 * @param root
	 */
	private Resources (String root) {
		this.root = root;
	}

	/**
	 * @create by Adam
	 * @create date: Nov 16, 2017
	 *
	 * @param root
	 * @param dir
	 * @param extension
	 */
	private Resources (String root, String dir, String extension) {
		this.root = root;
		this.dir = dir;
		this.extension = extension;
	}
	
	public String getRoot () {
		return this.root;
	}
	
	public String getDir () {
		return this.dir;
	}
	
	public String getExtension () {
		return this.extension;
	}
	
}


