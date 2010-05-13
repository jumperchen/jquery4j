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

/**A Tabs
 * 
 * @author whkuo
 *
 */
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
	
	/**Returns the disabled
	 * @return
	 */
	public Object getDisabled() {
		if(_disabled != null){
			return _disabled.getValue();
		}
		return null;
	}
	
	/**Disables (true) or enables (false) the tabs. Can be set when initialising (first creating) the tabs.
	 * @param disabled
	 */
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
	
	/**Returns the fx
	 * @return
	 */
	public String getFx() {
		return _fx;
	}
	
	/**Enable animations for hiding and showing tab panels. The duration option can be a string representing one of the three predefined speeds ("slow", "normal", "fast") or the duration in milliseconds to run an animation (default is "normal").
	 * @param fx
	 */
	public void setFx(String fx){
		if(fx == null || fx.length() == 0) fx="";
		if (!_fx.equals(fx)) {
			_fx = fx;
			smartUpdate("fx", _fx);
		}
	}	
	
	/**Returns the selected
	 * @return
	 */
	public int getSelected(){
		return _selected;
	}
	
	/**Zero-based index of the tab to be selected on initialization. To set all tabs to unselected pass -1 as value.
	 * @param selected
	 */
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

	/**Returns the cookie
	 * @return
	 */
	public String getCookie() {
		return _cookie;
	}
	
	/**Store the latest selected tab in a cookie. The cookie is then used to determine the initially selected tab if the selected option is not defined. Requires cookie plugin. The object needs to have key/value pairs of the form the cookie plugin expects as options. Available options (example): { expires: 7, path: '/', domain: 'jquery.com', secure: true }. Since jQuery UI 1.7 it is also possible to define the cookie name being used via name property.
	 * @param cookie
	 */
	public void setCookie(String cookie){
		if(cookie == null || cookie.length() == 0) cookie="";
		if (!_cookie.equals(cookie)) {
			_cookie = cookie;
			smartUpdate("cookie", _cookie);
		}
	}

	
	/**Returns the ajaxOptions
	 * @return
	 */
	public String getAjaxOptions() {
		return _ajaxOptions;
	}
	
	/**Additional Ajax options to consider when loading tab content (see $.ajax).
	 * @param ajaxOptions
	 */
	public void setAjaxOptions(String ajaxOptions){
		if(ajaxOptions == null || ajaxOptions.length() == 0) ajaxOptions="";
		if (!_ajaxOptions.equals(ajaxOptions)) {
			_ajaxOptions = ajaxOptions;
			smartUpdate("ajaxOptions", _ajaxOptions);
		}
	}
	
	/**Returns the tabTemplate
	 * @return
	 */
	public String getTabTemplate() {
		return _tabTemplate;
	}
	
	/**HTML template from which a new tab is created and added. The placeholders #{href} and #{label} are replaced with the url and tab label that are passed as arguments to the add method.
	 * @param tabTemplate
	 */
	public void setTabTemplate(String tabTemplate){
		if(tabTemplate == null || tabTemplate.length() == 0) tabTemplate="<li><a href=\"#{href}\"><span>#{label}</span></a></li>";
		if (!_tabTemplate.equals(tabTemplate)) {
			_tabTemplate = tabTemplate;
			smartUpdate("tabTemplate", _tabTemplate);
		}
	}

	/**Returns the spinner
	 * @return
	 */
	public String getSpinner() {
		return _spinner;
	}
	
	/**The HTML content of this string is shown in a tab title while remote content is loading. Pass in empty string to deactivate that behavior. An span element must be present in the A tag of the title, for the spinner content to be visible.
	 * @param spinner
	 */
	public void setSpinner(String spinner){
		if(spinner == null || spinner.length() == 0) spinner="<em>Loading&#8230;</em>";
		if (!_spinner.equals(spinner)) {
			_spinner = spinner;
			smartUpdate("spinner", _spinner);
		}
	}

	/**Returns the panelTemplate
	 * @return
	 */
	public String getPanelTemplate() {
		return _panelTemplate;
	}
	
	/**HTML template from which a new tab panel is created in case of adding a tab with the add method or when creating a panel for a remote tab on the fly.
	 * @param panelTemplate
	 */
	public void setPanelTemplate(String panelTemplate){
		if(panelTemplate == null || panelTemplate.length() == 0) panelTemplate="<div></div>";
		if (!_panelTemplate.equals(panelTemplate)) {
			_panelTemplate = panelTemplate;
			smartUpdate("panelTemplate", _panelTemplate);
		}
	}
	
	/**Returns the idPrefix
	 * @return
	 */
	public String getIdPrefix() {
		return _idPrefix;
	}
	
	/**If the remote tab, its anchor element that is, has no title attribute to generate an id from, an id/fragment identifier is created from this prefix and a unique id returned by $.data(el), for example "ui-tabs-54".
	 * @param idPrefix
	 */
	public void setIdPrefix(String idPrefix){
		if(idPrefix == null || idPrefix.length() == 0) idPrefix="ui-tabs";
		if (!_idPrefix.equals(idPrefix)) {
			_idPrefix = idPrefix;
			smartUpdate("idPrefix", _idPrefix);
		}
	}
	
	/**Returns the event
	 * @return
	 */
	public String getEvent() {
		return _event;
	}
	
	/**The type of event to be used for selecting a tab.
	 * @param event
	 */
	public void setEvent(String event){
		//TODO: check "click", "mouseover"
		if(event == null || event.length() == 0) event="click";
		if (!_event.equals(event)) {
			_event = event;
			smartUpdate("event", _event);
		}
	}

	/**Returns the deselectable
	 * @return
	 */
	public boolean getDeselectable() {
		return _deselectable;
	}
	
	/**deprecated in jQuery UI 1.7, use collapsible.
	 * @param deselectable
	 */
	public void setDeselectable(boolean deselectable) {
		if(_deselectable != deselectable){
			_deselectable = deselectable;
			smartUpdate("deselectable", _deselectable);					
		}
	}

	/**Returns the collapsible
	 * @return
	 */
	public boolean getCollapsible() {
		return _collapsible;
	}
	
	/**Set to true to allow an already selected tab to become unselected again upon reselection.
	 * @param collapsible
	 */
	public void setCollapsible(boolean collapsible) {
		if(_collapsible != collapsible){
			_collapsible = collapsible;
			smartUpdate("collapsible", _collapsible);					
		}
	}


	
	
	/**Returns the cache
	 * @return
	 */
	public boolean getCache() {
		return _cache;
	}
	
	/**Whether or not to cache remote tabs content, e.g. load only once or with every click. Cached content is being lazy loaded, e.g once and only once for the first click. Note that to prevent the actual Ajax requests from being cached by the browser you need to provide an extra cache: false flag to ajaxOptions.
	 * @param cache
	 */
	public void setCache(boolean cache) {
		if(_cache != cache){
			_cache = cache;
			smartUpdate("cache", _cache);					
		}
	}

	/**Returns the data model to render tabs
	 * @return
	 */
	public TabsModel getModel(){
		return _model;
	}
	
	/**Sets the data model to render tabs
	 * @param model
	 */
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


	/**
	 * Processes an AU request.
	 * 
	 */		
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
