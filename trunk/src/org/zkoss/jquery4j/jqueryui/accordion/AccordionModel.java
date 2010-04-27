package org.zkoss.jquery4j.jqueryui.accordion;

import java.util.LinkedList;
import java.util.List;


public class AccordionModel {
	
	String[][] _content ={{"title1","content1"},{"title2","content2"}};
	
	private boolean _modified = true;//false;
	private transient List _listeners = new LinkedList();
	
	public AccordionModel(){		
	}
	
	public AccordionModel(String[][] model){
		_content = model;
	}
	
	public void addAccordionDataListener(AccordionDataListener l){
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
