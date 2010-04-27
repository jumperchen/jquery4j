package jqueryuitest;

import org.zkoss.jquery4j.jqueryui.datepicker.Datepicker;
import org.zkoss.jquery4j.jqueryui.progressbar.events.ChangedEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class DatepickerController extends GenericForwardComposer {
	
	
	Datepicker comp1;
	
	public void onProgressbarChange$comp1(ChangedEvent e) {
		System.out.println("Datepicker value changed : "+ e.getValue());
	}
	
}
