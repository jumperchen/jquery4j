jquery4j.jqueryui.Accordion = zk.$extends(zul.Widget,{
	
	$define: {	
		model: function (model) {
			if (this.desktop)
				this.rerender();
		},		
		disabled :  function(){
			$(this.$n('cnt')).accordion('option','disabled',this._disabled);
		},
		autoHeight :  function(){
			$(this.$n('cnt')).accordion('option','autoHeight',this._autoHeight);
		},
		clearStyle :  function(){
			$(this.$n('cnt')).accordion('option','clearStyle',this._clearStyle);
		},
		collapsible :  function(){
			$(this.$n('cnt')).accordion('option','collapsible',this._collapsible);
		},
		navigation :  function(){
			$(this.$n('cnt')).accordion('option','navigation',this._navigation);
		},
		animated :  function(){
			$(this.$n('cnt')).accordion('option','animated',this._animated);
		},
		active :  function(){
			$(this.$n('cnt')).accordion('option','active',this._active);
		},		
		navigationFilter :  function(){
			//TODO: string to function
			$(this.$n('cnt')).accordion('option','navigationFilter',jq.evalJSON(this._navigationFilter));
		},
	
		event :  function(){
			$(this.$n('cnt')).accordion('option','event',this._event);
		},
		
		fillSpace :  function(){
			$(this.$n('cnt')).accordion('option','fillSpace',this._fillSpace);
		},
			
		header :  function(){
			$(this.$n('cnt')).accordion('option','header',this._header);
		},
		//TODO: how to convert from String to object? 
		icons :  function(){
			$(this.$n('cnt')).accordion('option','icons',jq.evalJSON(this._icons));
		}			
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		
		var wgt = this;

		var options = {
			change: function(event, ui) {
				//title
				//alert(ui.newHeader.context.textContent);
				//TODO:			
				//zk.log(ui.newHeader.context.textContent);								
				wgt.fire('onAccordionChange',{title:ui.newHeader.context.textContent});
			}
			,disabled: this._disabled
			,autoHeight: this._autoHeight
			,clearStyle: this._clearStyle
			,collapsible: this._collapsible
			,fillSpace: this._fillSpace
			,navigation: this._navigation	
			,animated: this._animated
			,active: this._active
			//TODO: string to function	
			,navigationFilter: jq.evalJSON(this._navigationFilter)
			,event: this._event
			,header: this._header
			//TODO: how to convert from String to object?
			,icons: jq.evalJSON(this._icons)			
		}			
		
		$(content).accordion(
			options
		);		
			
	},
	
	unbind_: function () {				
		var content = this.$n('cnt');
		$(content).accordion('destroy');		
		this.$supers('unbind_', arguments);
	},
			
	redraw: function (out) {
		out.push('<div',this.domAttrs_(),'><div id="',this.uuid,'-cnt">');				
		if(this._model != null){
			for(var i =0 ;i < this._model.length;i++){
				out.push('<h3><a href="#">',this._model[i][0],'</a></h3><div>',this._model[i][1],'</div>');
			}
		}
		out.push('</div></div>');				
	}		
});