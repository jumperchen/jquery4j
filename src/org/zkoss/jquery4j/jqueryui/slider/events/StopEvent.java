package org.zkoss.jquery4j.jqueryui.slider.events;

import java.util.Map;

import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.AuRequests;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

public class StopEvent extends Event {
		
	public static final StopEvent getStartEvent(AuRequest request) {
		final Component comp = request.getComponent();
		
		if(comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, request);
				
		return new StopEvent(request.getCommand(), comp);
	}
	
	public StopEvent(String name, Component target) {
		super(name, target);				
	}		
}
