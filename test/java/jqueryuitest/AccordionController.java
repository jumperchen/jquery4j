package jqueryuitest;

import org.zkoss.jquery4j.jqueryui.accordion.Accordion;
import org.zkoss.jquery4j.jqueryui.accordion.events.ChangedEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class AccordionController extends GenericForwardComposer {
	
	
	Accordion target;
	
	public void onAccordionChange$target(ChangedEvent e) {
		System.out.println("title of selected tab: "+e.getTitle());
	}	
}
