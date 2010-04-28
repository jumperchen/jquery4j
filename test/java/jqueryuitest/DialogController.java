package jqueryuitest;

import org.zkoss.jquery4j.jqueryui.dialog.Dialog;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class DialogController extends GenericForwardComposer {
	
	
	Dialog target;
	
//	public void onXXX$target(Event e) {
//		System.out.println("Dialog value changed : "+ e);
//	}

	public void onOpen$target(Event e) {
		System.out.println("In Controller Dialog open : "+ e);
	}

	public void onClose$target(Event e) {
		System.out.println("In Controller Dialog closed : "+ e);
	}
	
	public void onResize$target(Event e) {
		System.out.println("In Controller Dialog resize : "+ e);
	}
	
	public void onDrag$target(Event e) {
		System.out.println("In Controller Dialog drag : "+ e);
	}
}
