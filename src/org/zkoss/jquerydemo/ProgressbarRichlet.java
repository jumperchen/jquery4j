package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jqueryui.progressbar.Progressbar;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

public class ProgressbarRichlet extends GenericRichlet {

	public void service(Page page) {
		Progressbar comp = new Progressbar();
		comp.setValue(40);		
		comp.setPage(page);
	}
}
