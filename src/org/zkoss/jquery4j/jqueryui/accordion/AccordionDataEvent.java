package org.zkoss.jquery4j.jqueryui.accordion;

public class AccordionDataEvent {
	
	private final AccordionModel _model;
	
	public AccordionDataEvent(AccordionModel model){
		if (model == null)
			throw new NullPointerException();
		_model = model;
	}
	
	public AccordionModel getModel(){
		return _model;		
	}
	
}
