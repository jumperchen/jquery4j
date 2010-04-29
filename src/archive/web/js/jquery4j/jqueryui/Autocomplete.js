/* Autocomplete.js

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

jquery4j.jqueryui.Autocomplete = zk.$extends(zul.Widget,{
	
	$define: {		
		selected : null,
		
		disabled :  function(){
			$(this.$n('cnt')).autocomplete('option','disabled',this._disabled);
		},	
		delay :  function(){
			$(this.$n('cnt')).autocomplete('option','delay',this._delay);
		},
		minLength :  function(){
			$(this.$n('cnt')).autocomplete('option','minLength',this._minLength);
		},
		source :  function(){
			$(this.$n('cnt')).autocomplete('option','source',this._source);
		}		
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		

		var wgt=this;
		var options = {
   			select: function(event, ui) {								
				wgt.fire('onSelection',{start: 0, end: 0,
				selected: ui.item.value });
			}
			,source : this._source
			,disabled: this._disabled
			//,disabled:true  still no effect
			,delay: this._delay
			,minLength: this._minLength			
		}
						
		$(content).autocomplete(options);					
	},
	
	unbind_: function () {				
		var content = this.$n('cnt');
		$(content).autocomplete('destroy');		
		this.$supers('unbind_', arguments);
	},	
			
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'>');
		out.push('<input id="',this.uuid,'-cnt" ','type="text"/>');
		out.push('</div>');
	}	
});