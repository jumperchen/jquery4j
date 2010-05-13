/* Slider.java

	Purpose:
		
	Description:
		
	History:
	Apr 16, 2010	, Created by peterkuo

Copyright (C) 2006 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under MIT in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */

package org.zkoss.jquery4j.jqueryui.slider;

import org.zkoss.jquery4j.jqueryui.parameter.Mix;
import org.zkoss.jquery4j.jqueryui.slider.events.ChangedEvent;
import org.zkoss.jquery4j.jqueryui.slider.events.SlideEvent;
import org.zkoss.jquery4j.jqueryui.slider.events.StartEvent;
import org.zkoss.jquery4j.jqueryui.slider.events.StopEvent;
import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.impl.XulElement;

/**A Slider
 * 
 * @author whkuo
 *
 */
public class Slider extends XulElement {
	
	private boolean _disabled = false;
		
	private int _value = 0;	
	private int _max = 100;
	private int _min = 0;
	private int _step = 1;
	private String _orientation = "horizontal";//horizontal, vertical
	private Mix _range = new Mix(false);	
	private Mix _animate = new Mix(false);
	
	private int[] _values = {};
	
	private class SliderEvents {
		public static final String ON_START = "onSlideStart";
		public static final String ON_SLIDE = "onSlide";
		public static final String ON_CHANGE = "onSlideChange";
		public static final String ON_STOP = "onSlideStop";
	}
	
	static {
		addClientEvent(Slider.class, SliderEvents.ON_START, CE_IMPORTANT|CE_REPEAT_IGNORE);
		addClientEvent(Slider.class, SliderEvents.ON_SLIDE, CE_IMPORTANT);
		addClientEvent(Slider.class, SliderEvents.ON_CHANGE, CE_IMPORTANT|CE_REPEAT_IGNORE);
		addClientEvent(Slider.class, SliderEvents.ON_STOP, CE_IMPORTANT|CE_REPEAT_IGNORE);
	}
	
	/**Returns the animate
	 * @return
	 */
	public Object getAnimate() {
		if(_animate != null){
			return _animate.getValue();
		}
		return null;
	}
	
	/**Whether to slide handle smoothly when user click outside handle on the bar. Will also accept a string representing one of the three predefined speeds ("slow", "normal", or "fast") or the number of milliseconds to run the animation (e.g. 1000).
	 * @param animate
	 */
	public void setAnimate(Object animate){
		if(_animate == null) _animate = new Mix(false);
		_animate.setValue(animate);
		smartUpdate("animate", _animate.getValue());
	}
	
	/**Returns the range
	 * @return
	 */
	public Object getRange() {
		if(_range != null){
			return _range.getValue();
		}
		return null;
	}
	
	/**If set to true, the slider will detect if you have two handles and create a stylable range element between these two. Two other possible values are 'min' and 'max'. A min range goes from the slider min to one handle. A max range goes from one handle to the slider max.
	 * @param range
	 */
	public void setRange(Object range){
		if(_range == null) _range = new Mix(false);
		_range.setValue(range);
		smartUpdate("range", _range.getValue());
	}
	
	/**Returns the values
	 * @return
	 */
	public int[] getValues(){
		return _values;
	}
	
	/**This option can be used to specify multiple handles. If range is set to true, the length of 'values' should be 2.
	 * @param values
	 */
	public void setValues(int[] values){		
		if(!Objects.equals(_values, values)){
			_values = values;
			smartUpdate("values", _values);
		}		
	}
	
	/**This option can be used to specify multiple handles. If range is set to true, the length of 'values' should be 2.
	 * @param values
	 */	
	public void setValues(String values){
		int[] vals=(int[]) coerceFromString(values);
		setValues(vals);
	}
	
	protected
	Object coerceFromString(String values){
		String[] s =values.split(",");
		int[] result = new int[s.length];
		for(int i = 0 ;i < s.length; i ++){
			result[i] = Integer.valueOf(s[i]).intValue();	
		}				
		return result;
	}
 		
	/**Returns the  orientation
	 * @return
	 */
	public String getOrientation(){
		return _orientation;
	}
	
	/**This option determines whether the slider has the min at the left, the max at the right or the min at the bottom, the max at the top. Possible values: 'horizontal', 'vertical'.
	 * @param orientation
	 */
	public void setOrientation(String orientation){		
		if(orientation == null) orientation="horizontal";
		if(!orientation.equals("horizontal") && !orientation.equals("vertical"))
			throw new UiException("Illegal orientation: "+orientation+". Range: horizontal,vertical");
		if(!_orientation.equals(orientation)){
			_orientation = orientation;
			smartUpdate("orientation", _orientation);
		}
	}
		
	/**Returns the disabled
	 * @return
	 */
	public boolean getDisabled() {
		return _disabled;
	}
	
	/**Disables (true) or enables (false) the slider. Can be set when initialising (first creating) the slider.
	 * @param disabled
	 */
	public void setDisabled(boolean disabled) {	
		if(_disabled != disabled){
			_disabled = disabled;
			smartUpdate("disabled", _disabled);					
		}
	}

	/**Returns the step
	 * @return
	 */
	public int getStep() {
		return _step;
	}
		
	/**Determines the size or amount of each interval or step the slider takes between min and max. The full specified value range of the slider (max - min) needs to be evenly divisible by the step.
	 * @param step
	 */
	public void setStep(int step) {
		if (_step != step) {
			_step = step;
			smartUpdate("step", _step);
		}
	}
	
	/**Returns the min
	 * @return
	 */
	public int getMin() {
		return _min;
	}
		
	/**The minimum value of the slider.
	 * @param min
	 */
	public void setMin(int min) {
		if (_min != min) {
			_min = min;
			smartUpdate("min", _min);
		}
	}
	
	/**Returns the max
	 * @return
	 */
	public int getMax() {
		return _max;
	}
		
	/**The maximum value of the slider.
	 * @param max
	 */
	public void setMax(int max) {
		if (_max != max) {
			_max = max;
			smartUpdate("max", _max);
		}
	}
	
	/**Returns the value
	 * @return
	 */
	public int getValue() {
		return _value;
	}
		
	/**Determines the value of the slider, if there's only one handle. If there is more than one handle, determines the value of the first handle.
	 * @param value
	 */
	public void setValue(int value) {
		if (value < _min || value > _max)
			throw new UiException("Illegal value: "+value+". Range: _min ~ _max");
		if (_value != value) {
			_value = value;
			smartUpdate("value", _value);
		}
	}


	/**
	 * Processes an AU request.
	 * 
	 */	
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {		
		final String cmd = request.getCommand();

		if (cmd.equals(SliderEvents.ON_CHANGE)) {
			//System.out.println("get change event");
			ChangedEvent evt = ChangedEvent.getChangedEvent(request);
			int val = evt.getValue();
			if(val != _value){
				_value = val;
				Events.postEvent(evt);
			}								
		} else if (cmd.equals(SliderEvents.ON_START)) {
			//System.out.println("get start event");
			StartEvent evt = StartEvent.getStartEvent(request);
			Events.postEvent(evt);
		} else if (cmd.equals(SliderEvents.ON_STOP)) {
			//System.out.println("get stop event");
			StopEvent evt = StopEvent.getStartEvent(request);
			Events.postEvent(evt);
		} else if (cmd.equals(SliderEvents.ON_SLIDE)) {
			//System.out.println("get slide event");
			SlideEvent evt = SlideEvent.getSlideEvent(request);
			int val = evt.getValue();
			//System.out.println("sliding value : "+val);
			Events.postEvent(evt);											
		}
		else {
			super.service(request, everError);
		}
					
	}
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);

		if(_disabled)
			render(renderer, "disabled", _disabled);		

		if(_range != null && _range.isModified()){				
			render(renderer, "range", _range.getValue());
		}
		if(_animate != null && _animate.isModified()){				
			render(renderer, "animate", _animate.getValue());
		}
		
		if(_value != 0)
			render(renderer, "value", _value);	
		
		if(_max != 100)
			render(renderer, "max", _max);
		
		if(_min != 0)
			render(renderer, "min", _min);

		if(_step != 0)
			render(renderer, "step", _step);

		if(!_orientation.equals("horizontal"))
			render(renderer, "orientation", _orientation);
		
		if(_values.length > 0)
			render(renderer, "values", _values);
	}		
}
