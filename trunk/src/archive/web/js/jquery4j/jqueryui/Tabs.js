/* Tabs.js

{{IS_NOTE
	Purpose:

	Description:

	History:
	Apr 28, 2010	, Created by peterkuo
}}IS_NOTE

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under MIT in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/

jquery4j.jqueryui.Tabs = zk.$extends(zul.Widget,{
		
	$define: {
		model: function (model) {
			if (this.desktop)
				this.rerender();
		},
		disabled :  function(){
			//zk.log('define disabled : '+ this._disabled);
			$(this.$n('cnt')).tabs('option','disabled',this._disabled);
		},
		cache :  function(){
			$(this.$n('cnt')).tabs('option','cache',this._cache);
		},				
		collapsible :  function(){
			$(this.$n('cnt')).tabs('option','collapsible',this._collapsible);
		},
		deselectable :  function(){
			$(this.$n('cnt')).tabs('option','deselectable',this._deselectable);
		},
		event :  function(){
			$(this.$n('cnt')).tabs('option','event',this._event);
		},
		idPrefix :  function(){
			$(this.$n('cnt')).tabs('option','idPrefix',this._idPrefix);
		},
		panelTemplate :  function(){
			$(this.$n('cnt')).tabs('option','panelTemplate',this._panelTemplate);
		},
		spinner :  function(){
			$(this.$n('cnt')).tabs('option','spinner',this._spinner);
		},
		tabTemplate :  function(){
			$(this.$n('cnt')).tabs('option','tabTemplate',this._tabTemplate);
		},		
		ajaxOptions :  function(){
			$(this.$n('cnt')).tabs('option','ajaxOptions',jq.evalJSON(this._ajaxOptions));
		},
		cookie :  function(){
			$(this.$n('cnt')).tabs('option','cookie',jq.evalJSON(this._cookie));
		},
		selected :  function(){
			$(this.$n('cnt')).tabs('option','selected',this._selected);
		},
		fx :  function(){
			$(this.$n('cnt')).tabs('option','fx',jq.evalJSON(this._fx));
		}
	
	

		
							
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		
		var wgt=this;
	
		var options = {
			select: function(event,ui){
				//ui.index
				//zk.log("select"+ui.index);
				wgt.fire('onSelect',{index:ui.index});
			}   					
			,disabled: this._disabled
			,cache: this._cache
			,collapsible: this._collapsible
			,deselectable: this._deselectable
			,event: this._event
			,idPrefix: this._idPrefix
			,panelTemplate: this._panelTemplate
			,spinner: this._spinner
			,tabTemplate: this._tabTemplate
			,ajaxOptions: jq.evalJSON(this._ajaxOptions)
			,cookie: jq.evalJSON(this._cookie)
			,selected: this._selected
			,fx: jq.evalJSON(this._fx)			
		};		
		
		//zk.log('bind disabled : '+this._disabled);
		
		$(content).tabs(options);			
	},
	
	unbind_: function () {				
		var content = this.$n('cnt');
		$(content).tabs('destroy');		
		this.$supers('unbind_', arguments);
	},
	
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'><div id="',this.uuid,'-cnt"><ul>');						
		if(this._model != null){
			for(var i =0 ;i < this._model.length;i++){
				out.push('<li><a href="#',this.uuid,'-tabs-',i,'">',this._model[i][0],'</a><li>');
			}
		}
		out.push('</ul>')
		if(this._model != null){
			for(var i =0 ;i < this._model.length;i++){
				out.push('<div id="',this.uuid,'-tabs-',i,'">',this._model[i][1],'</div>');
			}
		}		
		out.push('</div></div>');				
	}		
});