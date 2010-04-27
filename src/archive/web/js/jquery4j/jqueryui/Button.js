/* Button.js

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

jquery4j.jqueryui.Button = zk.$extends(zul.Widget,{
	
	_label: null,
	_type: "default",
	_childLabels: [],
	
	$define: {
		label:null,
		type:null,
		childLabels:null
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		

		//zk.log(this.$n('cnt').id);
		if(this._type =="default"){
			//$('#'+this.$n('cnt').id).button({			
			$(content).button({
			});							
		}else if(this._type=="radio" || this._type=="checkbox"){
			$(content).buttonset({	
				//TODO: how do I know which button is clicked?
						
			});										
		}
	},
	
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'>');
		if (this._type == "default") {
			//out.push('<button style="width:100%;height:100%" id="', this.uuid, '-cnt" ', '>', this._label, '</button>');			
			out.push('<button id="', this.uuid, '-cnt" ', '>', this._label, '</button>');
		}
		else if (this._type == "radio") {
			out.push('<div id="', this.uuid, '-cnt" ', '>');
			//radio children
			for(var i =0;i<this._childLabels.length;i++){
				//name="radio" must be given
				out.push('<input type="radio" id="',this.uuid,'-cnt-',i,'" name="radio"/><label for="',this.uuid,'-cnt-',i,'">',this._childLabels[i],"</label>");
			}
			out.push('</div');
		}else if (this._type == "checkbox") {
			out.push('<div id="', this.uuid, '-cnt" ', '>');
			//checkbox children
			for(var i =0;i<this._childLabels.length;i++){
				out.push('<input type="checkbox" id="',this.uuid,'-cnt-',i,'"/><label for="',this.uuid,'-cnt-',i,'">',this._childLabels[i],"</label>");
			}			
			out.push('</div');
		}
		
		out.push('</div>');
	}		
});