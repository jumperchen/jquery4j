package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jqueryui.button.Button;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Messagebox;

public class ButtonRichlet extends GenericRichlet {

	public void service(Page page) {

		Button comp = new Button();
		comp.setLabel("default");
		comp.addEventListener(Events.ON_CLICK, new EventListener() {
			public void onEvent(Event event) throws Exception {
				Messagebox.show("clicked");
			}
		});
		comp.setPage(page);
	}
}
