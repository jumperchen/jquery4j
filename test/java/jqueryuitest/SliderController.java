package jqueryuitest;


import org.zkoss.jquery4j.jqueryui.slider.Slider;
import org.zkoss.jquery4j.jqueryui.slider.events.ChangedEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;


public class SliderController extends GenericForwardComposer {
	
	
	Slider comp1;
	
	public void onSlideChange$comp1(ChangedEvent e) {
		System.out.println("Slider value changed : "+ e.getValue());
	}
}
