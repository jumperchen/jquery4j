package jqueryuitest;

import org.zkoss.jquery4j.jqueryui.button.Button;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class ButtonController extends GenericForwardComposer {
		
	Button target;
	Button radio;
	Button checkbox;

	public void onClick$target(Event e) {
		System.out.println("target clicked");
	}

	public void onClick$radio(Event e) {
		System.out.println("radio clicked");
	}

	public void onClick$checkbox(Event e) {
		System.out.println("checkbox clicked");
	}
}
