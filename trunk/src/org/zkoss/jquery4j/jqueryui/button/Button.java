/* Button.java

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

package org.zkoss.jquery4j.jqueryui.button;

import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.impl.XulElement;


public class Button extends XulElement {
	
	static {		
		addClientEvent(Button.class, Events.ON_CLICK, CE_DUPLICATE_IGNORE);
	}

	public Button(){
		super();
		//setHeight("50px");
		//setWidth("100px");
	}
	
	String _type="default";//default, radio, checkbox
	String[] _childLabels={};
	
	private boolean _disabled = false;	
	private boolean _text = true;
	
	private String _label = "";
	private String _icons = "{ primary: null, secondary: null }";
	
	/**Returns the icons
	 * 
	 * @return
	 */
	public String getIcons() {
		return _icons;
	}
	
	/**Icons to display, with or without text (see text option). The primary icon is displayed on the left of the label text, the secondary on the right. Value for the primary and secondary properties must be a classname (String), eg. "ui-icon-gear". For using only a primary icon: icons: {primary:'ui-icon-locked'}. For using both primary and secondary icon: icons: {primary:'ui-icon-gear',secondary:'ui-icon-triangle-1-s'}
	 * 
	 * @param icons
	 */
	public void setIcons(String icons){
		if(icons == null || icons.length() == 0) icons="{ primary: null, secondary: null }";
		if (!_icons.equals(icons)) {
			_icons = icons;
			smartUpdate("icons", _icons);
		}
	}
	
	/**Returns the label
	 * 
	 * @return
	 */
	public String getLabel() {
		return _label;
	}
	
	/**Text to show on the button. When not specified (null), the element's html content is used, or its value attribute when it's an input element of type submit or reset; or the html content of the associated label element if its an input of type radio or checkbox
	 * 
	 * @param label
	 */
	public void setLabel(String label){
		if(label == null || label.length() == 0) label="";
		if (!_label.equals(label)) {
			_label = label;
			smartUpdate("label", _label);
		}
	}
		
	/**Returns the text
	 * 
	 * @return
	 */
	public boolean getText() {
		return _text;
	}
	
	/**Whether to show any text - when set to false (display no text), icons (see icons option) must be enabled, otherwise it'll be ignored.
	 * 
	 * @param text
	 */
	public void setText(boolean text) {
		if(_text != text){
			_text = text;
			smartUpdate("text", _text);					
		}
	}
	
	/**Returns the disabled
	 * 
	 * @return
	 */
	public boolean getDisabled() {
		return _disabled;
	}
	
	/**Disables (true) or enables (false) the button. Can be set when initialising (first creating) the button.
	 * 
	 * @param disabled
	 */
	public void setDisabled(boolean disabled) {
		if(_disabled != disabled){
			_disabled = disabled;
			smartUpdate("disabled", _disabled);					
		}
	}
	
	/**childLabels
	 * 
	 * @return
	 */
	public String[] getChildLabels(){
		return _childLabels;
	}

	/** Sets the childLabels to render 
	 * 
	 * @param childLabels
	 */
	public void setChildLabels(String childLabels){
		String[] childs = (String[]) coerceFromString(childLabels);
		setChildLabels(childs);
	}

	protected
	Object coerceFromString(String value){
		String[] result=value.split(",");
		return result;
	}
	
	/** Sets the childLabels to render 
	 * 
	 * @param childLabels
	 */
	public void setChildLabels(String[] childLabels){
		if(!Objects.equals(_childLabels, childLabels)){
			_childLabels = childLabels;
			smartUpdate("childLabels", _childLabels);
		}				
	}	
	
	/**Returns the type
	 * 
	 * @return
	 */
	public String getType() {
		return _type;
	}
	
	/**Set the type, it could be button, radio, checkbox etc
	 * 
	 * @param type
	 */
	public void setType(String type){
		if (type == null) type = "";
		if (!Objects.equals(_type, type)) {		
			_type = type;
			smartUpdate("type", _type);
		}
	}


	/**
	 * Processes an AU request.
	 * 
	 */	
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		if (cmd.equals(Events.ON_CLICK)) {
			Events.postEvent(MouseEvent.getMouseEvent(request));
		} else{
			super.service(request, everError);	
		}			
	}	

	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);

		if(!_type.equals("default"))
			render(renderer, "type", _type);

		if(_childLabels.length>0)
			render(renderer, "childLabels", _childLabels);

		if(_disabled)
			render(renderer, "disabled", _disabled);

		if(!_text)
			renderer.render("text", _text);

		if(!_label.equals(""))
			render(renderer, "label", _label);

		if(!_icons.equals("{ primary: null, secondary: null }"))
			render(renderer, "icons", _icons);

	}		
}
