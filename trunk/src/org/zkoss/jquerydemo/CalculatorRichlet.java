package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jquery.Calculator;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

public class CalculatorRichlet extends GenericRichlet {

	public void service(Page page) {
		Calculator comp = new Calculator();
		comp.setPage(page);
	}
}
