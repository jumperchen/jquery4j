/* Progressbar.js

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

jquery4j.jqueryui.Progressbar = zk.$extends(zul.Widget,{
		
	$define: {
		value: function(){
			$(this.$n('cnt')).progressbar('option','value',this._value);
		},
		
		disabled:function(){
			$(this.$n('cnt')).progressbar('option','disabled',this._disabled);			
		}			
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		
		var wgt = this;
		
		var options = {   		
			change : function (event,ui){							
				wgt.fire('onProgressbarChange', {value:wgt._value});
			}
			,value: this._value
			,disabled: this._disabled
		};
			
		$(content).progressbar(options);				
	},

	unbind_: function () {				
		var content = this.$n('cnt');
		$(content).progressbar('destroy');		
		this.$supers('unbind_', arguments);
	},
		
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'><div id="',this.uuid,'-cnt" ','style="height:100%;"/></div>');
	}		
});