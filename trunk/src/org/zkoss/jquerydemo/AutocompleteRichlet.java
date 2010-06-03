package org.zkoss.jquerydemo;

import org.zkoss.jquery4j.jqueryui.autocomplete.Autocomplete;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

public class AutocompleteRichlet extends GenericRichlet {
       
    public void service(Page page) {    
    	String[] model = {"a01","a02","a03","a11","a12","a13","a21","a22","a23","a31","a32","a33"};

		Autocomplete comp= new Autocomplete();
		comp.setSource(model);
        comp.setPage(page);
    }    
}
