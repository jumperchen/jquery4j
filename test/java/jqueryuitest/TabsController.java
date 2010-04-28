package jqueryuitest;


import org.zkoss.jquery4j.jqueryui.tabs.Tabs;
import org.zkoss.jquery4j.jqueryui.tabs.events.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class TabsController extends GenericForwardComposer {
	
	
	Tabs target;
	
	public void onSelect$target(SelectEvent e) {
		System.out.println("index of selected tab: "+e.getIndex());
	}	
}
