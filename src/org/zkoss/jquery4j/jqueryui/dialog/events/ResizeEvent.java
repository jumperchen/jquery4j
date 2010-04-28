package org.zkoss.jquery4j.jqueryui.dialog.events;

import java.util.Map;

import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.AuRequests;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

public class ResizeEvent extends Event {
	private final int _width;
	private final int _height;
	
	public static final ResizeEvent getResizeEvent(AuRequest request) {
		final Component comp = request.getComponent();
		
		if(comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, request);
		
		final Map data=request.getData();
		
		if(data == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] {data, request});
		
		return new ResizeEvent(request.getCommand(), comp, AuRequests.getInt(data, "width", 0), AuRequests.getInt(data, "height", 0));
	}
	
	public ResizeEvent(String name, Component target, int width, int height) {
		super(name, target);
		this._width = width; 
		this._height = height;		
	}
	
	public final int getWidth() {
		return this._width;
	}

	public final int getHeight() {
		return this._height;
	}

}
