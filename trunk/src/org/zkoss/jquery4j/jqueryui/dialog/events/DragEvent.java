package org.zkoss.jquery4j.jqueryui.dialog.events;

import java.util.Map;

import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.AuRequests;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

public class DragEvent extends Event {
	private final int _left;
	private final int _top;
	
	public static final DragEvent getDragEvent(AuRequest request) {
		final Component comp = request.getComponent();
		
		if(comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, request);
		
		final Map data=request.getData();
		
		if(data == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] {data, request});
		
		return new DragEvent(request.getCommand(), comp, AuRequests.getInt(data, "left", 0), AuRequests.getInt(data, "top", 0));
	}
	
	public DragEvent(String name, Component target, int left, int top) {
		super(name, target);
		this._left = left; 
		this._top = top;		
	}
	
	public final int getLeft() {
		return this._left;
	}

	public final int getTop() {
		return this._top;
	}

}
