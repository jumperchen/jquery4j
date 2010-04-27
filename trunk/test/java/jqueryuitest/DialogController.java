package jqueryuitest;

import org.zkoss.jquery4j.jqueryui.dialog.Dialog;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class DialogController extends GenericForwardComposer {
	
	
	Dialog target;
	
	public void onXXX$target(Event e) {
		System.out.println("Dialog value changed : "+ e);
	}
	
}
