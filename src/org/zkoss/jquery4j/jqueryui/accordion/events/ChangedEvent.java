package org.zkoss.jquery4j.jqueryui.accordion.events;

import java.util.Map;

import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

public class ChangedEvent extends Event {
	private final String _title;
	
	public static final ChangedEvent getChangedEvent(AuRequest request) {
		final Component comp = request.getComponent();
		
		if(comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, request);
		
		final Map data=request.getData();
		
		if(data == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] {data, request});
		
		return new ChangedEvent(request.getCommand(), comp, (String)data.get("title"));
	}
	
	public ChangedEvent(String name, Component target, String title) {
		super(name, target);
		this._title = title;		
	}
	
	public final String getTitle() {
		return this._title;
	}	
}
