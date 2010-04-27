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

import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectionEvent;
import org.zkoss.zul.impl.XulElement;

public class Autocomplete extends XulElement {	
	String _selected="";
	String [] _availableTags = {};//{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

	static {
		addClientEvent(Autocomplete.class, Events.ON_SELECTION, CE_IMPORTANT|CE_REPEAT_IGNORE);		
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
	
	public String[] getAvailableTags(){
		return _availableTags;
	}

	public void setAvailableTags(String availableTags){
		if (availableTags == null) availableTags = "";
		String[] tags = (String[]) coerceFromString(availableTags);
		setAvailableTags(tags);
	}

	protected
	Object coerceFromString(String value){
		String[] result=value.split(",");
		return result;
	}
	
	public void setAvailableTags(String[] availableTags){
		if(!Objects.equals(_availableTags, availableTags)){
			_availableTags = availableTags;
			smartUpdate("availableTags", availableTags);
		}				
	}
	
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
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
			
		if(!_selected.equals(""))
			render(renderer, "selected", _selected);

		if(_availableTags.length > 0)
			render(renderer, "availableTags", _availableTags);

	}		
}
