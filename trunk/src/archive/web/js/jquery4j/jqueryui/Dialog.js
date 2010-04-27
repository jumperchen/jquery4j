/* Dialog.js

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

jquery4j.jqueryui.Dialog = zk.$extends(zul.Widget,{	
	//_title:null,
	//_content:null,
	
	$define: {	
		title: null,
		content: null
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		

		//zk.log(this.$n('cnt').id);
		$(content).dialog();				
	},
		
	unbind_: function () {				
		var content = this.$n('cnt');
		$(content).dialog('destroy');		
		this.$supers('unbind_', arguments);
	},			
			
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'><div id="',this.uuid,'-cnt"');
		if(this._title)
			out.push(' title="',this._title,'"');		
		out.push(">");
		if(this._content)
			out.push('<p>',this._content,'</p>');					
		out.push('</div></div>');		
	}		
});