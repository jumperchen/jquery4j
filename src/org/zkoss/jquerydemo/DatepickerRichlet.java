package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jqueryui.datepicker.Datepicker;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

public class DatepickerRichlet extends GenericRichlet {

	public void service(Page page) {
		Datepicker comp = new Datepicker();
		comp.setChangeMonth(true);
		comp.setChangeYear(true);
		comp.setPage(page);
	}
}
