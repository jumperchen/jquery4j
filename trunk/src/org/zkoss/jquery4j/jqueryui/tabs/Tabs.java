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

package org.zkoss.jquery4j.jqueryui.tabs;

import org.zkoss.jquery4j.jqueryui.parameter.Mix;
import org.zkoss.jquery4j.jqueryui.tabs.events.SelectEvent;
import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.impl.XulElement;

public class Tabs extends XulElement {

	private TabsModel _model = new TabsModel();
	private transient TabsDataListener _dataListener;
	
	public Tabs(){}

	private class TabsEvents {
		public static final String ON_SELECT = "onSelect";
	}

	static {
		addClientEvent(Tabs.class, TabsEvents.ON_SELECT, CE_IMPORTANT);
	}	
	
	private boolean _cache = false;
	private boolean _collapsible = false;
	//deprecated
	private boolean _deselectable = false;

	private String _event = "click";
	private String _idPrefix = "ui-tabs";
	private String _panelTemplate = "<div></div>";
	private String _spinner = "<em>Loading&#8230;</em>";
	private String _tabTemplate = "<li><a href=\"#{href}\"><span>#{label}</span></a></li>";

	private int _selected = 0;
	
	//Options, Array
	private String _fx = "";
	
	private Mix _disabled = new Mix(false);
	
	public Object getDisabled() {
		if(_disabled != null){
			return _disabled.getValue();
		}
		return null;
	}
	
	public void setDisabled(Object disabled){
		if(_disabled == null) _disabled = new Mix(false);
		if(disabled instanceof String){
			if(disabled.equals("true")||disabled.equals("false")){
				disabled = disabled.equals("true");
			}else {
				String[] sa = ((String)disabled).split(",");
				Integer[] ia = new Integer[sa.length];
				for(int i =0;i<sa.length;i++){
					ia[i]=Integer.parseInt(sa[i]);	
				}
				disabled = ia;
			}
		}		
		_disabled.setValue(disabled);
		smartUpdate("disabled", _disabled.getValue());
	}	
	
	public String getFx() {
		return _fx;
	}
	
	public void setFx(String fx){
		if(fx == null || fx.length() == 0) fx="";
		if (!_fx.equals(fx)) {
			_fx = fx;
			smartUpdate("fx", _fx);
		}
	}	
	
	public int getSelected(){
		return _selected;
	}
	
	public void setSelected(int selected){
		if (selected < 0 )
			throw new UiException("Illegal selected: "+selected+". Range: 0 ~ ");
		
		if(_selected!=selected){
			_selected = selected;
			smartUpdate("selected",_selected);
		}
	}
	
	
	
	//Options
	private String _ajaxOptions = "";
	
	//Object
	private String _cookie = "";

	public String getCookie() {
		return _cookie;
	}
	
	public void setCookie(String cookie){
		if(cookie == null || cookie.length() == 0) cookie="";
		if (!_cookie.equals(cookie)) {
			_cookie = cookie;
			smartUpdate("cookie", _cookie);
		}
	}

	
	public String getAjaxOptions() {
		return _ajaxOptions;
	}
	
	public void setAjaxOptions(String ajaxOptions){
		if(ajaxOptions == null || ajaxOptions.length() == 0) ajaxOptions="";
		if (!_ajaxOptions.equals(ajaxOptions)) {
			_ajaxOptions = ajaxOptions;
			smartUpdate("ajaxOptions", _ajaxOptions);
		}
	}
	
	public String getTabTemplate() {
		return _tabTemplate;
	}
	
	public void setTabTemplate(String tabTemplate){
		if(tabTemplate == null || tabTemplate.length() == 0) tabTemplate="<li><a href=\"#{href}\"><span>#{label}</span></a></li>";
		if (!_tabTemplate.equals(tabTemplate)) {
			_tabTemplate = tabTemplate;
			smartUpdate("tabTemplate", _tabTemplate);
		}
	}

	public String getSpinner() {
		return _spinner;
	}
	
	public void setSpinner(String spinner){
		if(spinner == null || spinner.length() == 0) spinner="<em>Loading&#8230;</em>";
		if (!_spinner.equals(spinner)) {
			_spinner = spinner;
			smartUpdate("spinner", _spinner);
		}
	}

	public String getPanelTemplate() {
		return _panelTemplate;
	}
	
	public void setPanelTemplate(String panelTemplate){
		if(panelTemplate == null || panelTemplate.length() == 0) panelTemplate="<div></div>";
		if (!_panelTemplate.equals(panelTemplate)) {
			_panelTemplate = panelTemplate;
			smartUpdate("panelTemplate", _panelTemplate);
		}
	}
	
	public String getIdPrefix() {
		return _idPrefix;
	}
	
	public void setIdPrefix(String idPrefix){
		if(idPrefix == null || idPrefix.length() == 0) idPrefix="ui-tabs";
		if (!_idPrefix.equals(idPrefix)) {
			_idPrefix = idPrefix;
			smartUpdate("idPrefix", _idPrefix);
		}
	}
	
	public String getEvent() {
		return _event;
	}
	
	public void setEvent(String event){
		//TODO: check "click", "mouseover"
		if(event == null || event.length() == 0) event="click";
		if (!_event.equals(event)) {
			_event = event;
			smartUpdate("event", _event);
		}
	}

	public boolean getDeselectable() {
		return _deselectable;
	}
	
	public void setDeselectable(boolean deselectable) {
		if(_deselectable != deselectable){
			_deselectable = deselectable;
			smartUpdate("deselectable", _deselectable);					
		}
	}

	public boolean getCollapsible() {
		return _collapsible;
	}
	
	public void setCollapsible(boolean collapsible) {
		if(_collapsible != collapsible){
			_collapsible = collapsible;
			smartUpdate("collapsible", _collapsible);					
		}
	}


	
	
	public boolean getCache() {
		return _cache;
	}
	
	public void setCache(boolean cache) {
		if(_cache != cache){
			_cache = cache;
			smartUpdate("cache", _cache);					
		}
	}

	public TabsModel getModel(){
		return _model;
	}
	
	public void setModel(TabsModel model){
		if(!Objects.equals(_model, model)){
			_model = model;
			smartUpdate("model", _model.getContent());
		}		
	}

	private void initDataListener() {
		if (_dataListener == null)
			_dataListener = new TabsDataListener() {
				public void onChange(TabsDataEvent event) {
					onTabsDataChange(event);
				}
			};

		_model.addTabsDataListener(_dataListener);
	}

	private void onTabsDataChange(TabsDataEvent event){
		TabsModel model = event.getModel();
		setModel(model);
	}

	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		//TODO
		if (cmd.equals(TabsEvents.ON_SELECT)) {
			SelectEvent evt = SelectEvent.getSelectEvent(request);
			Events.postEvent(evt);
		}else
			super.service(request, everError);	
	}	
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);
		
		if(_model.isModified())
			render(renderer,"model",_model.getContent());
		
		if(_cache)
			render(renderer, "cache", _cache);
		if(_collapsible)
			render(renderer, "collapsible", _collapsible);
		if(_deselectable)
			render(renderer, "deselectable", _deselectable);

		//String
		if(!_event.equals("click"))
			render(renderer, "event", _event);
		if(!_idPrefix.equals("ui-tabs"))
			render(renderer, "idPrefix", _idPrefix);
		if(!_panelTemplate.equals("<div></div>"))
			render(renderer, "panelTemplate", _panelTemplate);
		if(!_spinner.equals("<em>Loading&#8230;</em>"))
			render(renderer, "spinner", _spinner);
		if(!_tabTemplate.equals("<li><a href=\"#{href}\"><span>#{label}</span></a></li>"))
			render(renderer, "tabTemplate", _tabTemplate);

		//Options
		if(!_ajaxOptions.equals(""))
			render(renderer, "ajaxOptions", _ajaxOptions);

		//Object
		if(!_cookie.equals(""))
			render(renderer, "cookie", _cookie);
		
		//Number
		if(_selected != 0)
			render(renderer, "selected", _selected);

		//Options, Array
		if(!_fx.equals(""))
			render(renderer, "fx", _fx);
		
		//Mix
		if(_disabled != null && _disabled.isModified()){				
			render(renderer, "disabled", _disabled.getValue());
		}
	}		
}
