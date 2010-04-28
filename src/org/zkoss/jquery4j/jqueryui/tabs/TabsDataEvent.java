package org.zkoss.jquery4j.jqueryui.tabs;

public class TabsDataEvent {
	
	private final TabsModel _model;
	
	public TabsDataEvent(TabsModel model){
		if (model == null)
			throw new NullPointerException();
		_model = model;
	}
	
	public TabsModel getModel(){
		return _model;		
	}
	
}
