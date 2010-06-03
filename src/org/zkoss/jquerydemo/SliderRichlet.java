package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jqueryui.slider.Slider;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

public class SliderRichlet extends GenericRichlet {

	public void service(Page page) {
		Slider comp = new Slider();
		comp.setValues(new int[]{30,40});
		comp.setRange(true);
		comp.setPage(page);
	}
}
