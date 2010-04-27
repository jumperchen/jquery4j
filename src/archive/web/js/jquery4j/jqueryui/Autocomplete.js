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
	_selected : null,
	_availableTags :[],
		
	$define: {
		availableTags : null,
		selected : null
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		

		//zk.log(this.$n('cnt').id);		
		//zk.log("tags: "+this._availableTags);		
		var wgt=this;				
		$(content).autocomplete({
			source : this._availableTags,
   			select: function(event, ui) {				
				//zk.log(ui.item.value);
				wgt.fire('onSelection',{start: 0, end: 0,
				selected: ui.item.value });
			}			
		});					
	},
		
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'>');
		out.push('<input id="',this.uuid,'-cnt" ','type="text"/>');
		out.push('</div>');
	}	
});