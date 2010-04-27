/* Calculator.java

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
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.impl.XulElement;

public class Calculator extends XulElement {
	
	double _value = 0;
		
	static {
		addClientEvent(Calculator.class, Events.ON_CHANGE, CE_IMPORTANT|CE_REPEAT_IGNORE);		
	}

	public Calculator(){}
	
	public Calculator(double value){
		this();
		setValue(value);
	}
	
	public double getValue(){
		return _value;
	}
	
	public void setValue(double value){
		if(_value!=value){
			_value = value;
			smartUpdate("value",value);
		}
	}	

	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);	
		if(_value != 0){
			render(renderer, "value", Double.toString(_value));
		}
	}

	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {		
		final String cmd = request.getCommand();
		if (cmd.equals(Events.ON_CHANGE)) {
			InputEvent evt = InputEvent.getInputEvent(request);
			
			final String value = evt.getValue();
			try {
				final double newval = Double.parseDouble(value);
				if(newval == _value){
					return; //not post event
				}					
				_value = newval;
			} catch (NumberFormatException ex) {
				throw new InternalError(value);
			}

			Events.postEvent(evt);
		} else {			
			super.service(request, everError);
		}		
	}
}
