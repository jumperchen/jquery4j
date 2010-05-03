package jqueryuitest;

import org.zkoss.jquery4j.jqueryui.datepicker.Datepicker;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class DatepickerController extends GenericForwardComposer {
	
	
	Datepicker target;
	
	public void onSelection$target(Event e) {
		System.out.println("Datepicker selection happen : "+ e);
	}
	
}
