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
	
	String _label="";
	String _type="default";//default, radio, checkbox
	String[] _childLabels={};
	
	static {		
		addClientEvent(Button.class, Events.ON_CLICK, CE_DUPLICATE_IGNORE);
	}

//	public Button(){
//		setHeight("50px");
//		setWidth("100px");
//	}

	public String[] getChildLabels(){
		return _childLabels;
	}

	public void setChildLabels(String childLabels){
		String[] childs = (String[]) coerceFromString(childLabels);
		setChildLabels(childs);
	}

	protected
	Object coerceFromString(String value){
		String[] result=value.split(",");
		return result;
	}
	
	public void setChildLabels(String[] childLabels){
		if(!Objects.equals(_childLabels, childLabels)){
			_childLabels = childLabels;
			smartUpdate("childLabels", _childLabels);
		}				
	}	
	
	public String getType() {
		return _type;
	}
	
	public void setType(String type){
		if (type == null) type = "";
		if (!Objects.equals(_type, type)) {		
			_type = type;
			smartUpdate("type", _type);
		}
	}

	public String getLabel() {
		return _label;
	}
	
	public void setLabel(String label){
		if (label == null) label = "";
		if (!Objects.equals(_label, label)) {
			_label = label;
			smartUpdate("label", _label);
		}		
	}
	
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

		if(!_label.equals(""))		
			render(renderer, "label", _label);
		
		if(!_type.equals("default"))
			render(renderer, "type", _type);

		if(_childLabels.length>0)
			render(renderer, "childLabels", _childLabels);
		
	}		
}
