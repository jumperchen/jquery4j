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

/**An Autocomplete
 * @author whkuo
 *
 */
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

	/**Returns the source
	 * 
	 * @return
	 */
	public Object getSource() {
		if(_source != null){
			return _source.getValue();
		}
		return null;
	}
	
	/**Defines the data to use, must be specified. See Overview section for more details, and look at the various demos.
	 * 
	 * @param source
	 */
	public void setSource(Object source){
		if(_source == null) source = new Mix();
		if(source instanceof String && ((String)source).contains(",")){
			source = ((String)source).split(",");
		}
		_source.setValue(source);
		smartUpdate("source", _source.getValue());
	}
	
	/**Returns the minLength
	 * 
	 * @return
	 */
	public int getMinLength(){
		return _minLength;
	}
	
	/**The minimum number of characters a user has to type before the Autocomplete activates. Zero is useful for local data with just a few items. Should be increased when there are a lot of items, where a single character would match a few thousand items.
	 * 
	 * @param minLength
	 */
	public void setMinLength(int minLength){
		if (minLength < 0 )
			throw new UiException("Illegal minLength: "+minLength+". Range: 0 ~ ");
		
		if(_minLength!=minLength){
			_minLength = minLength;
			smartUpdate("minLength",_minLength);
		}
	}

	/**Returns the delay
	 * 
	 * @return
	 */
	public int getDelay(){
		return _delay;
	}
	
	/**The delay in milliseconds the Autocomplete waits after a keystroke to activate itself. A zero-delay makes sense for local data (more responsive), but can produce a lot of load for remote data, while being less responsive.
	 * 
	 * @param delay
	 */
	public void setDelay(int delay){
		if (delay < 0 )
			throw new UiException("Illegal delay: "+delay+". Range: 0 ~ ");
		
		if(_delay!=delay){
			_delay = delay;
			smartUpdate("delay",_delay);
		}	
	}

	/**Returns the disabled
	 * 
	 * @return
	 */
	public boolean getDisabled() {
		return _disabled;
	}
	
	/**Disables (true) or enables (false) the autocomplete. Can be set when initialising (first creating) the autocomplete.
	 * @param disabled
	 */
	public void setDisabled(boolean disabled) {
		if(_disabled != disabled){
			_disabled = disabled;
			smartUpdate("disabled", _disabled);					
		}
	}	
		
	/**Returns the selected,user selected value
	 * @return
	 */
	public String getSelected(){
		return _selected;
	}
	
	/** Set the selected, user selected value
	 * @param selected
	 */
	public void setSelected(String selected){								
		if (selected == null) selected = "";
		if (!Objects.equals(_selected, selected)) {
			_selected = selected;
			smartUpdate("selected", _selected);
		}
	}


	/**
	 * Processes an AU request.
	 * 
	 */	
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
