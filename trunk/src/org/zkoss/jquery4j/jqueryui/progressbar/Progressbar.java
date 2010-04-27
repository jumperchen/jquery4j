/* Progressbar.java

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

package org.zkoss.jquery4j.jqueryui.progressbar;

import org.zkoss.jquery4j.jqueryui.progressbar.events.ChangedEvent;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.impl.XulElement;

/**
 * A progress bar is a bar that indicates how much of a task has been completed. 
 * Wrap it from jquery ui
 * @author Peterkuo
 */
public class Progressbar extends XulElement {
	
	private int _value = 0;
	private boolean _disabled = false;
	
	private class ProgressbarEvents {
		public static final String ON_CHANGE = "onProgressbarChange";
	}
	
	static {
		addClientEvent(Progressbar.class, ProgressbarEvents.ON_CHANGE, CE_IMPORTANT);
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
	
	public Progressbar(){
		super();
		setHeight("30px");
	}
	
	/** 
	 * Returns the current value of the progress bar.
	 */
	public int getValue() {
		return _value;
	}
	
	/** 
	 * Sets the current value of the progress bar.
	 * <p>Range: 0~100.
	 */
	public void setValue(int value) {
		if (value < 0 || value > 100)
			throw new UiException("Illegal value: "+value+". Range: 0 ~ 100");
		if (_value != value) {
			_value = value;
			smartUpdate("value", _value);
		}
	}

	
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		
		if (cmd.equals(ProgressbarEvents.ON_CHANGE)) {
			ChangedEvent evt = ChangedEvent.getChangedEvent(request);
			int val = evt.getValue();
			if(val != _value && val>=0 && val<=100){
				_value = val;
				Events.postEvent(evt);
			}								
		} else
			super.service(request, everError);
	}	
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);
		
		if(_value != 0){
			render(renderer, "value", _value);	
		}		
		
		if(_disabled){
			render(renderer, "disabled", _disabled);
		}
	}		
}
