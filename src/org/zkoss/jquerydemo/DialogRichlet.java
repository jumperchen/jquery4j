package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jqueryui.dialog.Dialog;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

public class DialogRichlet extends GenericRichlet {

	public void service(Page page) {
		Dialog comp = new Dialog();
		comp.setTitle("title1");
		comp.setContent("hello world");
		comp.setPage(page);
	}
}
