package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jqueryui.tabs.Tabs;
import org.zkoss.jquery4j.jqueryui.tabs.TabsModel;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

public class TestRichlet extends GenericRichlet {

	public void service(Page page) {
		String[][] model = {{"t1","c1"},{"t2","c2"},{"t3","c3"},{"t4","c4"}};
		TabsModel tm = new TabsModel(model);	

		Tabs comp = new Tabs();
		comp.setModel(tm);
		comp.setPage(page);
	}
}
