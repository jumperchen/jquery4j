/* Slider.js

{{IS_NOTE
	Purpose:

	Description:

	History:
	Apr 16, 2010	, Created by peterkuo
}}IS_NOTE

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under MIT in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/

jquery4j.jqueryui.Slider = zk.$extends(zul.Widget,{
		
	$define: {
		value: function(){
			$(this.$n('cnt')).slider('option','value',this._value);
		},
		disabled : function(){
			$(this.$n('cnt')).slider('option','disabled',this._disabled);			
		},
		max : function(){
			$(this.$n('cnt')).slider('option','max',this._max);			
		},
		min : function(){
			$(this.$n('cnt')).slider('option','min',this._min);			
		},
		step : function(){
			$(this.$n('cnt')).slider('option','step',this._step);			
		},
		orientation : function(){
			$(this.$n('cnt')).slider('option','orientation',this._orientation);			
		},
		range : function(){
				$(this.$n('cnt')).slider('option','range',this._range);			
		},
		animate : function(){
				$(this.$n('cnt')).slider('option','animate',this._animate);			
		},		
		values : function(){
			$(this.$n('cnt')).slider('option','values',this._values);			
		}											
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		
		var wgt=this;
	
		var options = {   		
			change: function(event, ui) {
				wgt._value = ui.value;
				wgt.fire('onSlideChange',{value: ui.value});
			},	
			start: function(event, ui) {
				wgt.fire('onSlideStart');
			},
			stop: function(event, ui) {
				wgt.fire('onSlideStop');
			},
			slide: function(event, ui) {
				wgt.fire('onSlide',{value: ui.value});
			}
			,value: this._value							
			,disabled: this._disabled		
			,max: this._max	
			,min: this._min
			,step: this._step
			,orientation: this._orientation
			,range: this._range
			,values: this._values
			,animate: this._animate
		};		
		
		$(content).slider(options);			
	},
	
	unbind_: function () {				
		var content = this.$n('cnt');
		$(content).slider('destroy');		
		this.$supers('unbind_', arguments);
	},
	
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'><div id="',this.uuid,'-cnt" ','/></div>');
	}	
});