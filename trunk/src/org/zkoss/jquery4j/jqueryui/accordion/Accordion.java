/* Accordion.java

	Purpose:
		
	Description:
		
	History:
	Apr 23, 2010	, Created by peterkuo

Copyright (C) 2006 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under MIT in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */

package org.zkoss.jquery4j.jqueryui.accordion;

import org.zkoss.jquery4j.jqueryui.accordion.events.ChangedEvent;
import org.zkoss.jquery4j.jqueryui.parameter.Mix;
import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.impl.XulElement;



/**
 * 
 * An accordion
 * @author whkuo
 *
 */
public class Accordion extends XulElement {

	private class AccordionEvents {
		public static final String ON_CHANGE = "onAccordionChange";
	}

	static {
		addClientEvent(Accordion.class, AccordionEvents.ON_CHANGE, CE_IMPORTANT);
	}
	
	private boolean _disabled = false;
	private boolean _autoHeight = true;
	private boolean _clearStyle = false;	
	private boolean _collapsible = false;	
	private boolean _fillSpace = false;
	private boolean _navigation = false;
	
	
	private String _event = "click";//"click","mouseover"
	private String _header = "> li > :first-child,> :not(li):even";
	private String _navigationFilter = "";
	

	private Mix _animated = new Mix("slide");//bounceslide, true, false
	
	//TODO: how to set first child as default value?
	//no mapping object for jquery element?	
	private Mix _active = new Mix();
		
	//JSONObject should support constructor with String
	//private JSONObject _icons = new JSONObject("{ 'header': 'ui-icon-triangle-1-e', 'headerSelected': 'ui-icon-triangle-1-s' }");
	
	private String _icons = "{ 'header': 'ui-icon-triangle-1-e', 'headerSelected': 'ui-icon-triangle-1-s' }";
	
	/**Returns the icons
	 * @return
	 */
	public String getIcons() {
		return _icons;		
	}
	
	
	/**Icons to use for headers. Icons may be specified for 'header' and 'headerSelected', and we recommend using the icons native to the jQuery UI CSS Framework manipulated by jQuery UI ThemeRoller
	 * @param icons
	 */
	public void setIcons(String icons){
		if(icons == null || icons.length() == 0) icons="{ 'header': 'ui-icon-triangle-1-e', 'headerSelected': 'ui-icon-triangle-1-s' }";
		if (!_icons.equals(icons)) {
			_icons = icons;
			smartUpdate("icons", _icons);
		}
	}
		
	/**Returns the header
	 * @return
	 */
	public String getHeader() {
		return _header;
	}
	
	/**Selector for the header element.
	 * 
	 * @param header
	 */
	public void setHeader(String header){
		if(header == null || header.length() == 0) header="> li > :first-child,> :not(li):even";
		if (!_header.equals(header)) {
			_header = header;
			smartUpdate("header", _header);
		}
	}
	
	/**Returns the fillSpace
	 * 
	 * @return
	 */
	public boolean getFillSpace() {
		return _fillSpace;
	}
	
	/**If set, the accordion completely fills the height of the parent element. Overrides autoheight.
	 * 
	 * @param fillSpace
	 */
	public void setFillSpace(boolean fillSpace) {
		if(_fillSpace != fillSpace){
			_fillSpace = fillSpace;
			smartUpdate("fillSpace", _fillSpace);					
		}
	}	
	
	/**Returns the event
	 * 
	 * @return
	 */
	public String getEvent() {
		return _event;
	}
	
	/**The event on which to trigger the accordion.
	 * 
	 * @param event
	 */
	public void setEvent(String event){
		if(event == null || event.length() == 0) event="click";
		if (!_event.equals(event)) {
			_event = event;
			smartUpdate("event", _event);
		}
	}
	
	/**Returns the navigationFilter
	 * 
	 * @return
	 */
	public String getNavigationFilter() {
		return _navigationFilter;
	}
	
	/**Overwrite the default location.href-matching with your own matcher.
	 * 
	 * @param navigationFilter
	 */
	public void setNavigationFilter(String navigationFilter){
		if(navigationFilter == null || navigationFilter.length() == 0) navigationFilter="";
		if (!_navigationFilter.equals(navigationFilter)) {
			_navigationFilter = navigationFilter;
			smartUpdate("navigationFilter", _navigationFilter);
		}
	}

	/**Returns the active.
	 * 
	 * @return
	 */
	public Object getActive() {
		if(_active != null){
			return _active.getValue();
		}
		return null;
	}
	
	/**
	 * Selector for the active element. Set to false to display none at start. Needs collapsible: true.
	 * @param active
	 */
	public void setActive(Object active){
		if(_active == null) _active = new Mix();
		_active.setValue(active);
		smartUpdate("active", _active.getValue());
	}


	/**Returns the animated
	 * @return
	 */
	public Object getAnimated() {
		if(_animated != null){
			return _animated.getValue();
		}
		return null;
	}
	
	/**Choose your favorite animation, or disable them (set to false). In addition to the default, 'bounceslide' and all defined easing methods are supported ('bounceslide' requires UI Effects Core).
	 * 
	 * @param animated
	 */
	public void setAnimated(Object animated){
		if(_animated == null) _animated = new Mix("slide");
		_animated.setValue(animated);
		smartUpdate("animated", _animated.getValue());
	}
	
	/**Returns the navigation
	 * @return
	 */
	public boolean getNavigation() {
		return _navigation;
	}
	
	/**If set, looks for the anchor that matches location.href and activates it. Great for href-based state-saving. Use navigationFilter to implement your own matcher.
	 * 
	 * @param navigation
	 */
	public void setNavigation(boolean navigation) {
		if(_navigation != navigation){
			_navigation = navigation;
			smartUpdate("navigation", _navigation);					
		}
	}

	
	/**Returns the collapsible
	 * @return
	 */
	public boolean getCollapsible() {
		return _collapsible;
	}
	
	
	/**Whether all the sections can be closed at once. Allows collapsing the active section by the triggering event (click is the default).
	 * @param collapsible
	 */
	public void setCollapsible(boolean collapsible) {
		if(_collapsible != collapsible){
			_collapsible = collapsible;
			smartUpdate("collapsible", _collapsible);					
		}
	}
			
	/**Returns the clearStyle
	 * @return
	 */
	public boolean getClearStyle() {
		return _clearStyle;
	}
	
	/**If set, clears height and overflow styles after finishing animations. This enables accordions to work with dynamic content. Won't work together with autoHeight.
	 * @param clearStyle
	 */
	public void setClearStyle(boolean clearStyle) {
		if(_clearStyle != clearStyle){
			_clearStyle = clearStyle;
			smartUpdate("clearStyle", _clearStyle);					
		}
	}
	
	/** Returns the autoHeight
	 * @return
	 */
	public boolean getAutoHeight() {
		return _autoHeight;
	}
	
	
	/**If set, the highest content part is used as height reference for all other parts. Provides more consistent animations.
	 * @param autoHeight
	 */
	public void setAutoHeight(boolean autoHeight) {
		if(_autoHeight != autoHeight){
			_autoHeight = autoHeight;
			smartUpdate("autoHeight", _autoHeight);					
		}
	}

	
	/**Returns the disabled
	 * @return
	 */
	public boolean getDisabled() {
		return _disabled;
	}
	
	/**Disables (true) or enables (false) the accordion. Can be set when initialising (first creating) the accordion.
	 * @param disabled
	 */
	public void setDisabled(boolean disabled) {
		if(_disabled != disabled){
			_disabled = disabled;
			smartUpdate("disabled", _disabled);					
		}
	}
	
	
	private AccordionModel _model = new AccordionModel();
	private transient AccordionDataListener _dataListener;
	
	public Accordion(){
		
	}
	
	public AccordionModel getModel(){
		return _model;
	}
	
	
	/** Sets the data model will be used to render the output.
	 * @param model
	 */
	public void setModel(AccordionModel model){
		if(!Objects.equals(_model, model)){
			_model = model;
			smartUpdate("model", _model.getContent());
		}		
	}

	private void initDataListener() {
		if (_dataListener == null)
			_dataListener = new AccordionDataListener() {
				public void onChange(AccordionDataEvent event) {
					onAccordionDataChange(event);
				}
			};

		_model.addAccordionDataListener(_dataListener);
	}

	private void onAccordionDataChange(AccordionDataEvent event){
		AccordionModel model = event.getModel();
		setModel(model);
	}

	/**
	 * Processes an AU request.
	 * 
	 */	
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		
		if (cmd.equals(AccordionEvents.ON_CHANGE)) {
			ChangedEvent evt = ChangedEvent.getChangedEvent(request);
			//System.out.println("name of new title: " + evt.getTitle());
			String title = evt.getTitle();
			//TODO: update value, and decide whether to postEvent
//			if(title != _title ){
//				_title = title;
//				Events.postEvent(evt);
//			}							
			Events.postEvent(evt);
		} else
			super.service(request, everError);
	}	
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);
		
		if(_model.isModified())
			render(renderer,"model",_model.getContent());
		
		if(_disabled)
			render(renderer, "disabled", _disabled);
		
		if(!_autoHeight)
			renderer.render("autoHeight", _autoHeight);

		if(_clearStyle)
			render(renderer, "clearStyle", _clearStyle);

		if(_collapsible)
			render(renderer, "collapsible", _collapsible);

		if(_fillSpace)
			render(renderer, "fillSpace", _fillSpace);
		
		if(_navigation)
			render(renderer, "navigation", _navigation);

		if(_animated != null && _animated.isModified()){				
			render(renderer, "animated", _animated.getValue());
		}

		if(_active != null && _active.isModified()){				
			render(renderer, "active", _active.getValue());
		}
		
		if(!_navigationFilter.equals(""))
			render(renderer, "navigationFilter", _navigationFilter);

		if(!_event.equals("click"))
			render(renderer, "event", _event);
		
		if(!_header.equals("> li > :first-child,> :not(li):even"))
			render(renderer, "header", _header);
		
		if(!_icons.equals("{ 'header': 'ui-icon-triangle-1-e', 'headerSelected': 'ui-icon-triangle-1-s' }"))
			render(renderer, "icons", _icons);
		
		
	}		
}
