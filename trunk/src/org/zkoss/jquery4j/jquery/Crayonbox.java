/* Crayonbox.java

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

package org.zkoss.jquery4j.jquery;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectionEvent;
import org.zkoss.zul.impl.XulElement;

public class Crayonbox extends XulElement {
	
	String _selected;
	
	static {
		addClientEvent(Crayonbox.class, Events.ON_SELECTION, CE_IMPORTANT|CE_REPEAT_IGNORE);		
	}

	public String getSelected() {
		return _selected;
	}
	
	public void setSelected(String selected){
		if(selected == null) selected="";
		if (!_selected.equals(selected)) {
			_selected = selected;
			smartUpdate("selected", _selected);
		}
	}
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);	
		
		if(_selected!=null){
			render(renderer, "selected", _selected);
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
}
