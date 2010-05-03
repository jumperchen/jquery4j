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
	
	public Object getAnimate() {
		if(_animate != null){
			return _animate.getValue();
		}
		return null;
	}
	
	public void setAnimate(Object animate){
		if(_animate == null) _animate = new Mix(false);
		_animate.setValue(animate);
		smartUpdate("animate", _animate.getValue());
	}
	
	public Object getRange() {
		if(_range != null){
			return _range.getValue();
		}
		return null;
	}
	
	public void setRange(Object range){
		if(_range == null) _range = new Mix(false);
		_range.setValue(range);
		smartUpdate("range", _range.getValue());
	}
	
	public int[] getValues(){
		return _values;
	}
	
	public void setValues(int[] values){		
		if(!Objects.equals(_values, values)){
			_values = values;
			smartUpdate("values", _values);
		}		
	}
	
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
 		
	public String getOrientation(){
		return _orientation;
	}
	
	public void setOrientation(String orientation){		
		if(orientation == null) orientation="horizontal";
		if(!orientation.equals("horizontal") && !orientation.equals("vertical"))
			throw new UiException("Illegal orientation: "+orientation+". Range: horizontal,vertical");
		if(!_orientation.equals(orientation)){
			_orientation = orientation;
			smartUpdate("orientation", _orientation);
		}
	}
		
	public boolean getDisabled() {
		return _disabled;
	}
	
	public void setDisabled(boolean disabled) {	
		if(_disabled != disabled){
			_disabled = disabled;
			smartUpdate("disabled", _disabled);					
		}
	}

	public int getStep() {
		return _step;
	}
		
	public void setStep(int step) {
		if (_step != step) {
			_step = step;
			smartUpdate("step", _step);
		}
	}
	
	public int getMin() {
		return _min;
	}
		
	public void setMin(int min) {
		if (_min != min) {
			_min = min;
			smartUpdate("min", _min);
		}
	}
	
	public int getMax() {
		return _max;
	}
		
	public void setMax(int max) {
		if (_max != max) {
			_max = max;
			smartUpdate("max", _max);
		}
	}
	
	public int getValue() {
		return _value;
	}
		
	public void setValue(int value) {
		if (value < _min || value > _max)
			throw new UiException("Illegal value: "+value+". Range: _min ~ _max");
		if (_value != value) {
			_value = value;
			smartUpdate("value", _value);
		}
	}
	
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
