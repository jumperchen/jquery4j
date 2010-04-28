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
	
	$define: {	
		methodName :  function(){
			$(this.$n('cnt')).dialog(this._methodName);
		},	
	
		open :  function(){
			if(this._open){
				$(this.$n('cnt')).dialog('open');	
			}else{
				$(this.$n('cnt')).dialog('close');
			}			
		},
		title: function (title) {
			if (this.desktop)
				this.rerender();
		},		
		content:function (content) {
			if (this.desktop)
				this.rerender();
		},		
		disabled :  function(){
			$(this.$n('cnt')).dialog('option','disabled',this._disabled);
		},		
		autoOpen :  function(){
			$(this.$n('cnt')).dialog('option','autoOpen',this._autoOpen);
		},
		closeOnEscape :  function(){
			$(this.$n('cnt')).dialog('option','closeOnEscape',this._closeOnEscape);
		},	
		draggable :  function(){
			$(this.$n('cnt')).dialog('option','draggable',this._draggable);
		},
		modal :  function(){
			$(this.$n('cnt')).dialog('option','modal',this._modal);
		},
		resizable :  function(){
			$(this.$n('cnt')).dialog('option','resizable',this._resizable);
		},
		stack :  function(){
			$(this.$n('cnt')).dialog('option','stack',this._stack);
		},
		closeText :  function(){
			$(this.$n('cnt')).dialog('option','closeText',this._closeText);
		},
		dialogClass :  function(){
			$(this.$n('cnt')).dialog('option','dialogClass',this._dialogClass);
		},
		hide :  function(){
			$(this.$n('cnt')).dialog('option','hide',this._hide);
		},
		show :  function(){
			$(this.$n('cnt')).dialog('option','show',this._show);
		},
		minHeight :  function(){
			$(this.$n('cnt')).dialog('option','minHeight',this._minHeight);
		},
		minWidth :  function(){
			$(this.$n('cnt')).dialog('option','minWidth',this._minWidth);
		},
		width :  function(){
			$(this.$n('cnt')).dialog('option','width',this._width);
		},	
		zIndex :  function(){
			$(this.$n('cnt')).dialog('option','zIndex',this._zIndex);
		},	
		height :  function(){
			$(this.$n('cnt')).dialog('option','height',this._height);
		},
		maxHeight :  function(){
			$(this.$n('cnt')).dialog('option','maxHeight',this._maxHeight);
		},
		maxWidth :  function(){
			$(this.$n('cnt')).dialog('option','maxWidth',this._maxWidth);
		},
		position :  function(){
			$(this.$n('cnt')).dialog('option','position',this._position);
		}
		,
		buttons :  function(){
			$(this.$n('cnt')).dialog('option','buttons',jq.evalJSON(this._buttons));
		}				
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		
		var wgt = this;
		
		var options = {   		
			open : function (event,ui){							
				wgt.fire('onOpen', {open:true});
			},
			close: function(event, ui) {
				wgt.fire('onClose'); 
			},		
			resize : function(event,ui){
				wgt.fire('onResize',{width:ui.size.width,height:ui.size.height});
			},
			drag :function(event,ui){
				wgt.fire('onDrag',{left:ui.position.left,top:ui.position.top});
			}

			,disabled: this._disabled
			,autoOpen: this._autoOpen			
			,closeOnEscape: this._closeOnEscape
			,draggable: this._draggable
			,modal: this._modal		
			,resizable: this._resizable
			,stack: this._stack
			,closeText: this._closeText
			,dialogClass: this._dialogClass
			,hide: this._hide
			,show: this._show
			,title: this._title
			,minHeight: this._minHeight
			,minWidth: this._minWidth
			,width: this._width
			,zIndex: this._zIndex				
			,height: this._height
			,maxHeight: this._maxHeight
			,maxWidth: this._maxWidth
			,position: this._position
			,buttons: jq.evalJSON(this._buttons)
			//,buttons: { "Ok": function() { zk.log("button") } }  
		};		

		$(content).dialog(options);				
				
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