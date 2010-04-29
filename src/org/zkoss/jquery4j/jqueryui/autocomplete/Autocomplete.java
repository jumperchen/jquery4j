/* Autocomplete.java

	Purpose:
		
	Description:
		
	History:
	Apr 16, 2010	, Created by peterkuo

Copyright (C) 2006 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under MIT in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */

package org.zkoss.jquery4j.jqueryui.autocomplete;

import org.zkoss.jquery4j.jqueryui.parameter.Mix;
import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectionEvent;
import org.zkoss.zul.impl.XulElement;

public class Autocomplete extends XulElement {

	static {
		addClientEvent(Autocomplete.class, Events.ON_SELECTION, CE_IMPORTANT|CE_REPEAT_IGNORE);		
	}

	//user selected value
	private String _selected="";

	private boolean _disabled = false;
	
	private int _delay = 300;	
	private int _minLength = 1;
	
	private Mix _source = new Mix();//no default value, but never be null

	public Object getSource() {
		if(_source != null){
			return _source.getValue();
		}
		return null;
	}
	
	public void setSource(Object source){
		if(_source == null) source = new Mix();
		if(source instanceof String && ((String)source).contains(",")){
			source = ((String)source).split(",");
		}
		_source.setValue(source);
		smartUpdate("source", _source.getValue());
	}
	
	public int getMinLength(){
		return _minLength;
	}
	
	public void setMinLength(int minLength){
		if (minLength < 0 )
			throw new UiException("Illegal minLength: "+minLength+". Range: 0 ~ ");
		
		if(_minLength!=minLength){
			_minLength = minLength;
			smartUpdate("minLength",_minLength);
		}
	}
		
	public int getDelay(){
		return _delay;
	}
	
	public void setDelay(int delay){
		if (delay < 0 )
			throw new UiException("Illegal delay: "+delay+". Range: 0 ~ ");
		
		if(_delay!=delay){
			_delay = delay;
			smartUpdate("delay",_delay);
		}	
	}

	
	public boolean getDisabled() {
		return _disabled;
	}
	
	public void setDisabled(boolean disabled) {
		if(_disabled != disabled){
			_disabled = disabled;
			smartUpdate("disabled", _disabled);					
		}
	}	
		
	public String getSelected(){
		return _selected;
	}
	
	public void setSelected(String selected){								
		if (selected == null) selected = "";
		if (!Objects.equals(_selected, selected)) {
			_selected = selected;
			smartUpdate("selected", _selected);
		}
	}
		
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		//TODO: define customized event?
		if (cmd.equals(Events.ON_SELECTION)) {
			SelectionEvent evt = SelectionEvent.getSelectionEvent(request);
			_selected = evt.getSelectedText(); 
					
			Events.postEvent(evt);			
		} else
			super.service(request, everError);
	}
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);
			
		if(_disabled)
			render(renderer, "disabled", _disabled);

		if(_delay != 300)
			render(renderer, "delay", _delay);

		if(_minLength != 1)
			render(renderer, "minLength", _minLength);

		if(_source != null && _source.isModified()){				
			render(renderer, "source", _source.getValue());
		}		
	}		
}
