/* Calculator.js

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

//because jquery ui rewrite the implementatoin of show of jquery.
//for other jquery widget not belong to jquery ui, shoule mark include jquery ui script in zk.wpd
jquery4j.jquery.Calculator = zk.$extends(zul.Widget,{
	
	_value: null,
	
	$define: {
		value: null
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		
		var wgt= this;		
		$(content).calculator({		
			onClose: function(value, inst) {
 				wgt.fire('onChange', {value: value});
    		}
		});	
	},
		
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'>');		
		out.push('<input id="',this.uuid,'-cnt" ','type="text"' );
		if(this._value){
			out.push(' value="', this._value ,'"')
		}			
		out.push('/></div>');
	}		
});