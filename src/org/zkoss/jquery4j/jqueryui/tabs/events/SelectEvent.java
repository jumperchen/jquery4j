package org.zkoss.jquery4j.jqueryui.tabs.events;

import java.util.Map;

import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.AuRequests;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

public class SelectEvent extends Event {
	private final int _index;
	
	public static final SelectEvent getSelectEvent(AuRequest request) {
		final Component comp = request.getComponent();
		
		if(comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, request);
		
		final Map data=request.getData();
		
		if(data == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] {data, request});
		
		return new SelectEvent(request.getCommand(), comp, AuRequests.getInt(data, "index", 0));
	}
	
	public SelectEvent(String name, Component target, int index) {
		super(name, target);
		this._index = index;		
	}
	
	public final int getIndex() {
		return this._index;
	}	
}
