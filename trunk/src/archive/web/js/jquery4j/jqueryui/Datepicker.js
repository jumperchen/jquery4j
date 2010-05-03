/* Datepicker.js

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

jquery4j.jqueryui.Datepicker = zk.$extends(zul.Widget,{
				
	$define: {
		disabled :  function(){
			$(this.$n('cnt')).datepicker('option','disabled',this._disabled);
		},
		autoSize :  function(){
			$(this.$n('cnt')).datepicker('option','autoSize',this._autoSize);
		},		
		buttonImageOnly :  function(){
			$(this.$n('cnt')).datepicker('option','buttonImageOnly',this._buttonImageOnly);
		},		
		changeMonth :  function(){
				$(this.$n('cnt')).datepicker('option','changeMonth',this._changeMonth);
		},
		changeYear : function(){
				$(this.$n('cnt')).datepicker('option','changeYear',this._changeYear);
		},
		constrainInput :  function(){
			$(this.$n('cnt')).datepicker('option','constrainInput',this._constrainInput);
		},
		gotoCurrent :  function(){
			$(this.$n('cnt')).datepicker('option','gotoCurrent',this._gotoCurrent);
		},		
		hideIfNoPrevNext :  function(){
			$(this.$n('cnt')).datepicker('option','hideIfNoPrevNext',this._hideIfNoPrevNext);
		},
		isRTL :  function(){
			$(this.$n('cnt')).datepicker('option','isRTL',this._isRTL);
		},
		navigationAsDateFormat :  function(){
			$(this.$n('cnt')).datepicker('option','navigationAsDateFormat',this._navigationAsDateFormat);
		},
		selectOtherMonths :  function(){
			$(this.$n('cnt')).datepicker('option','selectOtherMonths',this._selectOtherMonths);
		},
		showButtonPanel :  function(){
			$(this.$n('cnt')).datepicker('option','showButtonPanel',this._showButtonPanel);
		},
		showMonthAfterYear :  function(){
			$(this.$n('cnt')).datepicker('option','showMonthAfterYear',this._showMonthAfterYear);
		},
		showOtherMonths :  function(){
			$(this.$n('cnt')).datepicker('option','showOtherMonths',this._showOtherMonths);
		},
		showWeek :  function(){
			$(this.$n('cnt')).datepicker('option','showWeek',this._showWeek);
		},


		altField :  function(){
			$(this.$n('cnt')).datepicker('option','altField',this._altField);
		},
		altFormat :  function(){
			$(this.$n('cnt')).datepicker('option','altFormat',this._altFormat);
		},				
		appendText :  function(){
			$(this.$n('cnt')).datepicker('option','appendText',this._appendText);
		},				
		buttonImage :  function(){
			$(this.$n('cnt')).datepicker('option','buttonImage',this._buttonImage);
		},			
		buttonText :  function(){
			$(this.$n('cnt')).datepicker('option','buttonText',this._buttonText);
		},				
		closeText :  function(){
			$(this.$n('cnt')).datepicker('option','closeText',this._closeText);
		},
		currentText :  function(){
			$(this.$n('cnt')).datepicker('option','currentText',this._currentText);
		},
		dateFormat :  function(){
			$(this.$n('cnt')).datepicker('option','dateFormat',this._dateFormat);
		},				
		nextText :  function(){
			$(this.$n('cnt')).datepicker('option','nextText',this._nextText);
		},
		prevText :  function(){
			$(this.$n('cnt')).datepicker('option','prevText',this._prevText);
		},		
		showAnim :  function(){
			$(this.$n('cnt')).datepicker('option','showAnim',this._showAnim);
		},
		showOn :  function(){
			$(this.$n('cnt')).datepicker('option','showOn',this._showOn);
		},
		weekHeader :  function(){
			$(this.$n('cnt')).datepicker('option','weekHeader',this._weekHeader);
		},
		yearRange :  function(){
			$(this.$n('cnt')).datepicker('option','yearRange',this._yearRange);
		},
		yearSuffix :  function(){
			$(this.$n('cnt')).datepicker('option','yearSuffix',this._yearSuffix);
		},

		firstDay :  function(){
			$(this.$n('cnt')).datepicker('option','firstDay',this._firstDay);
		},

		showCurrentAtPos :  function(){
			$(this.$n('cnt')).datepicker('option','showCurrentAtPos',this._showCurrentAtPos);
		},

		stepMonths :  function(){
			$(this.$n('cnt')).datepicker('option','stepMonths',this._stepMonths);
		},

		dayNames :  function(){
			$(this.$n('cnt')).datepicker('option','dayNames',this._dayNames);
		},	
		
		dayNamesMin :  function(){
			$(this.$n('cnt')).datepicker('option','dayNamesMin',this._dayNamesMin);
		},

		dayNamesShort :  function(){
			$(this.$n('cnt')).datepicker('option','dayNamesShort',this._dayNamesShort);
		},	

		monthNames :  function(){
			$(this.$n('cnt')).datepicker('option','monthNames',this._monthNames);
		},	

		monthNamesShort :  function(){
			$(this.$n('cnt')).datepicker('option','monthNamesShort',this._monthNamesShort);
		},	
			
		defaultDateByDate :  function(){					
			$(this.$n('cnt')).datepicker('option','defaultDate',new Date(this._defaultDateByDate));
		},

		defaultDate :  function(){
			$(this.$n('cnt')).datepicker('option','defaultDate',this._defaultDate);	
		},

		duration :  function(){
			$(this.$n('cnt')).datepicker('option','duration',this._duration);
		},

		maxDateByDate :  function(){
			$(this.$n('cnt')).datepicker('option','maxDate',new Date(this._maxDateByDate));
		},

		maxDate :  function(){
			$(this.$n('cnt')).datepicker('option','maxDate',this._maxDate);
		},

		minDateByDate :  function(){
			$(this.$n('cnt')).datepicker('option','minDate',new Date(this._minDateByDate));
		},
		
		minDate :  function(){
			$(this.$n('cnt')).datepicker('option','minDate',this._minDate);
		},

		numberOfMonths :  function(){
			$(this.$n('cnt')).datepicker('option','numberOfMonths',this._numberOfMonths);
		}
		,			
		calculateWeek :  function(){
			$(this.$n('cnt')).datepicker('option','calculateWeek',jq.evalJSON(this._calculateWeek));
		}				
	},
	
	bind_: function () {
		this.$supers('bind_', arguments);
		var content = this.$n('cnt');		
		var wgt = this;	
		
		var options = {   		
				onSelect: function(dateText, inst) {			
					//zk.log(dateText);
					
					wgt.fire('onSelection',{start: 0, end: 0,
					selected: dateText });
				}
			,disabled: this._disabled
			,autoSize: this._autoSize
			,buttonImageOnly: this._buttonImageOnly
			,changeMonth: this._changeMonth
			,changeYear: this._changeYear
			,constrainInput: this._constrainInput
			,gotoCurrent: this._gotoCurrent
			,hideIfNoPrevNext: this._hideIfNoPrevNext
			,isRTL: this._isRTL
			,navigationAsDateFormat: this._navigationAsDateFormat
			,selectOtherMonths: this._selectOtherMonths
			,showButtonPanel: this._showButtonPanel
			,showMonthAfterYear: this._showMonthAfterYear
			,showOtherMonths: this._showOtherMonths
			,showWeek: this._showWeek
			
			,altField: this._altField
			,altFormat: this._altFormat
			,appendText: this._appendText
			,buttonImage: this._buttonImage
			,buttonText: this._buttonText
			,closeText: this._closeText
			,currentText: this._currentText
			,dateFormat: this._dateFormat
			,nextText: this._nextText
			,prevText: this._prevText
			,showAnim: this._showAnim
			,showOn: this._showOn
			,weekHeader: this._weekHeader
			,yearRange: this._yearRange
			,yearSuffix: this._yearSuffix
			
			,firstDay: this._firstDay
			,showCurrentAtPos: this._showCurrentAtPos
			,stepMonths: this._stepMonths
			
			,dayNames: this._dayNames
			,dayNamesMin: this._dayNamesMin
			,dayNamesShort: this._dayNamesShort
			,monthNames: this._monthNames
			,monthNamesShort: this._monthNamesShort

			,defaultDate: this._defaultDate
			,duration: this._duration
			,maxDate: this._maxDate						
			,minDate: this._minDate
			,numberOfMonths: this._numberOfMonths
			
			
			,calculateWeek: jq.evalJSON(this._calculateWeek)
		};						

		$(content).datepicker(options);	
	},
	
	unbind_: function () {				
		var content = this.$n('cnt');
		$(content).datepicker('destroy');		
		this.$supers('unbind_', arguments);
	},	
	
	redraw: function (out) {
		//zk.log(this._disabled);
		out.push('<div',this.domAttrs_(),'>');
		out.push('<input id="',this.uuid,'-cnt" ','type="text"/>');
		out.push('</div>');
	}	
});