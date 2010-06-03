package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jquery.Crayonbox;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

public class CrayonboxRichlet extends GenericRichlet {

	public void service(Page page) {
		Crayonbox comp = new Crayonbox();
		comp.setPage(page);
	}
}
