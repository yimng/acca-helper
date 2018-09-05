package com.thinkgem.jeesite.freetek.util.jpush;

import java.util.List;
import java.util.Map;

public class JpushEntity {

	private String title;
	private String alert;
	private Map<String, String> extra;
	private List<String> alias;
	
	public JpushEntity(){
		
	}
	
	public JpushEntity(String title, String alert, Map<String, String> extra, List<String> alias){
		this.title = title;
		this.alert = alert;
		this.extra = extra;
		this.alias = alias;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public Map<String, String> getExtra() {
		return extra;
	}
	public void setExtra(Map<String, String> extra) {
		this.extra = extra;
	}
	public List<String> getAlias() {
		return alias;
	}
	public void setAlias(List<String> alias) {
		this.alias = alias;
	}
		
}