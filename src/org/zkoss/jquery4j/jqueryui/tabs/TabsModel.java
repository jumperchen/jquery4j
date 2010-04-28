package org.zkoss.jquery4j.jqueryui.tabs;

import java.util.LinkedList;
import java.util.List;


public class TabsModel {
	
	String[][] _content ={{"tab1","content1"},{"tab2","content2"}};
	
	private boolean _modified = true;//false;
	private transient List _listeners = new LinkedList();
	
	public TabsModel(){		
	}
	
	public TabsModel(String[][] model){
		_content = model;
	}
	
	public void addTabsDataListener(TabsDataListener l){
		_listeners.add(l);
	}
	
	
	public boolean isModified(){
		return _modified;
	}
	
	public String[][] getContent(){
		return _content;
	}
	
	public void setContent(String[][] content){
		_content = content;
		_modified = true;
	}	
}
