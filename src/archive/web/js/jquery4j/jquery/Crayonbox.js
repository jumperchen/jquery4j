/* Crayonbox.js

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

jquery4j.jquery.Crayonbox = zk.$extends(zul.Widget,{
	
	_selected: null,
	 
	$define: {
		selected: null
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		
		var wgt = this;
		
		//zk.log(this.$n('cnt').id);
		$(content).crayonbox({
			onSelection: function(selected){					
				wgt.fire('onSelection',{start: 0, end: 0,
					selected: selected });	
			}
		});	
	},
	
	
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'>');		
		out.push('<input id="',this.uuid,'-cnt" ','type="text"/>');		
		out.push('</div>');
	}
	
});