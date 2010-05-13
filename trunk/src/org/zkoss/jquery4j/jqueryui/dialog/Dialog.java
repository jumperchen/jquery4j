/* Dialog.java

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

package org.zkoss.jquery4j.jqueryui.dialog;

import org.zkoss.jquery4j.jqueryui.dialog.events.DragEvent;
import org.zkoss.jquery4j.jqueryui.dialog.events.ResizeEvent;
import org.zkoss.jquery4j.jqueryui.parameter.Mix;
import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zul.impl.XulElement;

/**A Dialog
 * 
 * @author whkuo
 *
 */
public class Dialog extends XulElement {

	private class DialogEvents {
		public static final String ON_RESIZE = "onResize";
		public static final String ON_DRAG = "onDrag";
	}

	static {
		addClientEvent(Dialog.class, Events.ON_CLOSE, 0);
		addClientEvent(Dialog.class, Events.ON_OPEN, CE_IMPORTANT);

		//addClientEvent(Dialog.class, Events.ON_FOCUS, CE_DUPLICATE_IGNORE);
		
		addClientEvent(Dialog.class, DialogEvents.ON_RESIZE, CE_DUPLICATE_IGNORE|CE_IMPORTANT);
		addClientEvent(Dialog.class, DialogEvents.ON_DRAG, CE_DUPLICATE_IGNORE|CE_IMPORTANT);	
	}


	//different from other boolean, no render
	private boolean _open = true;
	
	//different from other String, no jquery ui property mapped
	private String _content = "";
	
	private boolean _disabled = false;
	private boolean _autoOpen = true;
	private boolean _closeOnEscape = true;
	private boolean _draggable = true;
	private boolean _modal = false;
	private boolean _resizable = true;
	private boolean _stack = true;
	
		
	private String _closeText = "close";
	private String _dialogClass = "";
	private String _hide = "";//"slide", etc
	private String _show = "";//"slide", etc
	private String _title = "";
	
	//Must convert to Object in client side
	private String _buttons = "";
	
	private int _minHeight = 150;
	private int _minWidth = 150;
	private int _width = 300;
	private int _zIndex = 1000;
	
	private Mix _height = new Mix("auto");
	private Mix _maxHeight = new Mix(false);
	private Mix _maxWidth = new Mix(false);
	private Mix _position = new Mix("center");

	//TODO: how about .dialog( "option" , optionName , [value] ) ??
	//TODO: how to get the result of such?
	public void callWidgetMethod(String methodName){
		//"destroy","disable","enable","widget","close","isOpen","moveToTop","open"
		//TODO: affect variable _open ? should sync such state variable
		smartUpdate("methodName", methodName);
	}
	
	/**Returns the buttons
	 * @return
	 */
	public String getButtons() {
		return _buttons;
	}
	
	
	/**Specifies which buttons should be displayed on the dialog. The property key is the text of the button. The value is the callback function for when the button is clicked. The context of the callback is the dialog element; if you need access to the button, it is available as the target of the event object. 
	 * @param buttons
	 */
	public void setButtons(String buttons){
		if(buttons == null || buttons.length() == 0) buttons="";
		if (!_buttons.equals(buttons)) {
			_buttons = buttons;
			smartUpdate("buttons", _buttons);
		}
	}

	/**Returns the position
	 * @return
	 */
	public Object getPosition() {
		if(_position != null){
			return _position.getValue();
		}
		return null;
	}

	private static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**Specifies where the dialog should be displayed. Possible values:
	 * 1) a single string representing position within viewport: 'center', 'left', 'right', 'top', 'bottom'.
	 * 2) an array containing an x,y coordinate pair in pixel offset from left, top corner of viewport (e.g. [350,100])
	 * 3) an array containing x,y position string values (e.g. ['right','top'] for top right corner).
	 * @param position
	 */
	public void setPosition(Object position){
		if(_position == null) _position = new Mix("center");
		if(position instanceof String){
			if(((String)position).contains(",")){
				String[] sa = ((String)position).split(",");
				position = sa;
				if(isInteger(sa[0])){
					Integer[] ia = new Integer[sa.length];
					ia[0]=Integer.parseInt(sa[0]);
					ia[1]=Integer.parseInt(sa[1]);
					position = ia;
				}
			}
		}
		_position.setValue(position);
		smartUpdate("position", _position.getValue());
	}

	/**Returns the maxWidth
	 * @return
	 */
	public Object getMaxWidth() {
		if(_maxWidth != null){
			return _maxWidth.getValue();
		}
		return null;
	}
	
	/**The maximum width to which the dialog can be resized, in pixels.
	 * @param maxWidth
	 */
	public void setMaxWidth(Object maxWidth){
		if(_maxWidth == null) _maxWidth = new Mix(false);
		_maxWidth.setValue(maxWidth);
		smartUpdate("maxWidth", _maxWidth.getValue());
	}

	/**Returns the maxHeight
	 * @return
	 */
	public Object getMaxHeight() {
		if(_maxHeight != null){
			return _maxHeight.getValue();
		}
		return null;
	}
	
	/**The maximum height to which the dialog can be resized, in pixels.
	 * @param maxHeight
	 */
	public void setMaxHeight(Object maxHeight){
		if(_maxHeight == null) _maxHeight = new Mix(false);
		_maxHeight.setValue(maxHeight);
		smartUpdate("maxHeight", _maxHeight.getValue());
	}

	//TODO: 
	
	/**Returns the height
	 * 
	 */
	@Override
	public String getHeight() {
		if(_height != null){
			return _height.getValue().toString();
		}
		return null;
	}
	
	
	/**The height of the dialog, in pixels. Specifying 'auto' is also supported to make the dialog adjust based on its content.
	 * @param height
	 */
	public void setHeight(Object height){
		if(_height == null) _height = new Mix("auto");
		_height.setValue(height);
		smartUpdate("height", _height.getValue());
	}
	
	
	/**Returns the  zIndex
	 * 
	 */
	public int getZIndex(){
		return _zIndex;
	}
	
	
	/**The starting z-index for the dialog.
	 * 
	 */
	public void setZIndex(int zIndex){
		if (zIndex < 0 )
			throw new UiException("Illegal zIndex: "+zIndex+". Range: 0 ~ ");
		
		if(_zIndex!=zIndex){
			_zIndex = zIndex;
			smartUpdate("zIndex",_zIndex);
		}
	}
	
	/**Returns the width
	 * 
	 */
	public String getWidth(){
		//return _width;
		return String.valueOf(_width);
	}
	
	
	/**The width of the dialog, in pixels.
	 * @param width
	 */
	public void setWidth(int width){
		if (width < 0 )
			throw new UiException("Illegal width: "+width+". Range: 0 ~ ");
		
		if(_width!=width){
			_width = width;
			smartUpdate("width",_width);
		}
	}

	/**Returns the minWidth
	 * @return
	 */
	public int getMinWidth(){
		return _minWidth;
	}
	
	/**The minimum width to which the dialog can be resized, in pixels.
	 * @param minWidth
	 */
	public void setMinWidth(int minWidth){
		if (minWidth < 0 )
			throw new UiException("Illegal minWidth: "+minWidth+". Range: 0 ~ ");
		
		if(_minWidth!=minWidth){
			_minWidth = minWidth;
			smartUpdate("minWidth",_minWidth);
		}
	}


	
	/**Returns the minHeight
	 * @return
	 */
	public int getMinHeight(){
		return _minHeight;
	}
	
	/**The minimum height to which the dialog can be resized, in pixels.
	 * @param minHeight
	 */
	public void setMinHeight(int minHeight){
		if (minHeight < 0 )
			throw new UiException("Illegal minHeight: "+minHeight+". Range: 0 ~ ");
		
		if(_minHeight!=minHeight){
			_minHeight = minHeight;
			smartUpdate("minHeight",_minHeight);
		}
	}


	
	/**Returns the open
	 * @return
	 */
	public boolean getOpen() {
		return _open;
	}
	
	/** set if open the dialog
	 * @param open
	 */
	public void setOpen(boolean open) {
		//if(_open != open){
		_open = open;
		smartUpdate("open", _open);					
		//}
	}

	/**Returns the show
	 * @return
	 */
	public String getShow() {
		return _show;
	}
	
	/**The effect to be used when the dialog is opened.
	 * @param show
	 */
	public void setShow(String show){
		if(show == null || show.length() == 0) show="";
		if (!_show.equals(show)) {
			_show = show;
			smartUpdate("show", _show);
		}
	}


	
	
	/**Returns the hide
	 * @return
	 */
	public String getHide() {
		return _hide;
	}

	/**The effect to be used when the dialog is closed.
	 * @param hide
	 */
	public void setHide(String hide){
		//TODO: should check if valid String here.
		if(hide == null || hide.length() == 0) hide="";
		if (!_hide.equals(hide)) {
			_hide = hide;
			smartUpdate("hide", _hide);
		}
	}

	/**Returns the dialogClass
	 * @return
	 */
	public String getDialogClass() {
		return _dialogClass;
	}
	
	/**The specified class name(s) will be added to the dialog, for additional theming.
	 * @param dialogClass
	 */
	public void setDialogClass(String dialogClass){
		if(dialogClass == null || dialogClass.length() == 0) dialogClass="";
		if (!_dialogClass.equals(dialogClass)) {
			_dialogClass = dialogClass;
			smartUpdate("dialogClass", _dialogClass);
		}
	}
	
	/**Returns the closeText
	 * @return
	 */
	public String getCloseText() {
		return _closeText;
	}
	
	/**Specifies the text for the close button. Note that the close text is visibly hidden when using a standard theme.
	 * @param closeText
	 */
	public void setCloseText(String closeText){
		if(closeText == null || closeText.length() == 0) closeText="close";
		if (!_closeText.equals(closeText)) {
			_closeText = closeText;
			smartUpdate("closeText", _closeText);
		}
	}
	
	/**Returns the stack
	 * @return
	 */
	public boolean getStack() {
		return _stack;
	}
	
	/**Specifies whether the dialog will stack on top of other dialogs. This will cause the dialog to move to the front of other dialogs when it gains focus.
	 * @param stack
	 */
	public void setStack(boolean stack) {
		if(_stack != stack){
			_stack = stack;
			smartUpdate("stack", _stack);					
		}
	}
	
	/**Returns the resizable
	 * @return
	 */
	public boolean getResizable() {
		return _resizable;
	}
	
	/**If set to true, the dialog will be resizeable.
	 * @param resizable
	 */
	public void setResizable(boolean resizable) {
		if(_resizable != resizable){
			_resizable = resizable;
			smartUpdate("resizable", _resizable);					
		}
	}
	
	/**Returns the modal
	 * @return
	 */
	public boolean getModal() {
		return _modal;
	}
	
	/**If set to true, the dialog will have modal behavior; other items on the page will be disabled (i.e. cannot be interacted with). Modal dialogs create an overlay below the dialog but above other page elements.
	 * @param modal
	 */
	public void setModal(boolean modal) {
		if(_modal != modal){
			_modal = modal;
			smartUpdate("modal", _modal);					
		}
	}
	
	/**Returns the draggable
	 * 
	 */
	@Override
	public String getDraggable() {
		//TODO: mismatch jquery ui, conflict with zk predefined api
		//return _draggable;		
		return String.valueOf(_draggable);
	}
	
	
	/**If set to true, the dialog will be draggable will be draggable by the titlebar.
	 * @param draggable
	 */
	public void setDraggable(boolean draggable) {
		if(_draggable != draggable){
			_draggable = draggable;
			smartUpdate("draggable", _draggable);					
		}
	}
	
	/**Returns the closeOnEscape
	 * @return
	 */
	public boolean getCloseOnEscape() {
		return _closeOnEscape;
	}
	
	/**Specifies whether the dialog should close when it has focus and the user presses the esacpe (ESC) key.
	 * @param closeOnEscape
	 */
	public void setCloseOnEscape(boolean closeOnEscape) {
		if(_closeOnEscape != closeOnEscape){
			_closeOnEscape = closeOnEscape;
			smartUpdate("closeOnEscape", _closeOnEscape);					
		}
	}
	
	/**Returns the autoOpen
	 * @return
	 */
	public boolean getAutoOpen() {
		return _autoOpen;
	}
	
	/**When autoOpen is true the dialog will open automatically when dialog is called. If false it will stay hidden until .dialog("open")  is called on it.
	 * @param autoOpen
	 */
	public void setAutoOpen(boolean autoOpen) {
		if(_autoOpen != autoOpen){
			_autoOpen = autoOpen;
			smartUpdate("autoOpen", _autoOpen);					
		}
	}
		
	/**Returns the disabled
	 * @return
	 */
	public boolean getDisabled() {
		return _disabled;
	}

	/**Disables (true) or enables (false) the dialog. Can be set when initialising (first creating) the dialog.
	 * @param disabled
	 */
	public void setDisabled(boolean disabled) {
		if (_disabled != disabled) {
			_disabled = disabled;
			smartUpdate("disabled", _disabled);
		}
	}

	/**Returns the title
	 * @return
	 */
	public String getTitle() {
		return _title;
	}

	/**Specifies the title of the dialog. The title can also be specified by the title attribute on the dialog source element.
	 * @param title
	 */
	public void setTitle(String title) {
		if (title == null)
			title = "";
		if (!Objects.equals(_title, title)) {
			_title = title;
			smartUpdate("title", _title);
		}
	}

	/**Returns the content
	 * @return
	 */
	public String getContent() {
		return _content;
	}

	/**Set the content to be render as the message of the dialog
	 * @param content
	 */
	public void setContent(String content) {
		if (content == null)
			content = "";
		if (!Objects.equals(_content, content)) {
			_content = content;
			smartUpdate("content", _content);
		}
	}


	/**
	 * Processes an AU request.
	 * 
	 */	
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
//		if (cmd.equals(Events.ON_CLOSE)) {
//			System.out.println("event received : close : ");
//		}else 
		if (cmd.equals(Events.ON_OPEN)) {
			OpenEvent evt = OpenEvent.getOpenEvent(request);
			setVisible(evt.isOpen());
			_open = evt.isOpen();
			Events.postEvent(evt);
		}else if (cmd.equals(DialogEvents.ON_RESIZE)) {
			ResizeEvent evt = ResizeEvent.getResizeEvent(request);
			_width = evt.getWidth();
			_height.setValue(evt.getHeight());
			Events.postEvent(evt);
		}else if (cmd.equals(DialogEvents.ON_DRAG)) {			
			DragEvent evt = DragEvent.getDragEvent(request);
			_left = String.valueOf(evt.getLeft());
			_top = String.valueOf(evt.getTop());
			Events.postEvent(evt);
		}else
			super.service(request, everError);
	}
	
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
			throws java.io.IOException {
		super.renderProperties(renderer);

		if (!_title.equals(""))
			render(renderer, "title", _title);

		if (!_content.equals(""))
			render(renderer, "content", _content);

		//boolean
		if (_disabled)
			render(renderer, "disabled", _disabled);
			//renderer.render("disabled", _disabled);

		if(!_autoOpen)
			//render(renderer, "autoOpen", _autoOpen);
			renderer.render("autoOpen", _autoOpen);

		if(!_closeOnEscape)
			//render(renderer, "closeOnEscape", _closeOnEscape);
			renderer.render("closeOnEscape", _closeOnEscape);

		if(!_draggable)
			//render(renderer, "draggable", _draggable);
			renderer.render( "draggable", _draggable);

		if(_modal)
			render(renderer, "modal", _modal);

		if(!_resizable)
			//render(renderer, "resizable", _resizable);
			renderer.render("resizable", _resizable);
		
		if(!_stack)
			//render(renderer, "stack", _stack);
			renderer.render("stack", _stack);
		
		//String
		if(!_closeText.equals("close"))
			render(renderer, "closeText", _closeText);

		if(!_dialogClass.equals(""))
			render(renderer, "dialogClass", _dialogClass);

		if(!_hide.equals(""))
			render(renderer, "hide", _hide);

		if(!_show.equals(""))
			render(renderer, "show", _show);

		//Number
		if(_minHeight != 150)
			render(renderer, "minHeight", _minHeight);

		if(_minWidth != 150)
			render(renderer, "minWidth", _minWidth);

		if(_width != 300)
			render(renderer, "width", _width);

		if(_zIndex != 1000)
			render(renderer, "zIndex", _zIndex);

		//Mix
		if(_height != null && _height.isModified()){				
			render(renderer, "height", _height.getValue());
		}
		if(_maxHeight != null && _maxHeight.isModified()){				
			render(renderer, "maxHeight", _maxHeight.getValue());
		}
		if(_maxWidth != null && _maxWidth.isModified()){				
			render(renderer, "maxWidth", _maxWidth.getValue());
		}
		if(_position != null && _position.isModified()){				
			render(renderer, "position", _position.getValue());
		}

		//Object
		if(!_buttons.equals(""))
			render(renderer, "buttons", _buttons);

	}
}
