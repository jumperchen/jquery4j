package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jqueryui.accordion.Accordion;
import org.zkoss.jquery4j.jqueryui.accordion.AccordionModel;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

public class AccordionRichlet extends GenericRichlet {
       
    public void service(Page page) {    
		String[][] model = {{"t1","c1"},{"t2","c2"}};	
		AccordionModel tm = new AccordionModel(model);

		Accordion comp= new Accordion();
		comp.setModel(tm);
        comp.setPage(page);
    }    
}
