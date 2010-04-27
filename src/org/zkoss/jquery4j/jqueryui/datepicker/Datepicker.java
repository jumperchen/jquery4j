/* Datepicker.java

	Purpose:
		
	Description:
		
	History:
	Apr 16, 2010	, Created by peterkuo

Copyright (C) 2006 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under MIT in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */

package org.zkoss.jquery4j.jqueryui.datepicker;

import java.util.Date;

import org.zkoss.jquery4j.jqueryui.parameter.Mix;
import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectionEvent;
import org.zkoss.zul.impl.XulElement;

public class Datepicker extends XulElement {
		
	private boolean _disabled = false;	
	private boolean _autoSize = false;		
	private boolean _buttonImageOnly = false;
	private boolean _changeMonth = false;
	private boolean _changeYear = false;
	private boolean _constrainInput = true;	
	private boolean _gotoCurrent = false;
	private boolean _hideIfNoPrevNext = false;		
	private boolean _isRTL = false;		
	private boolean _navigationAsDateFormat = false;
	private boolean _selectOtherMonths = false;
	private boolean _showButtonPanel = false;	
	private boolean _showMonthAfterYear = false;
	private boolean _showOtherMonths = false;	
	private boolean _showWeek = false;
	
	private String _altField = "";
	private String _altFormat = "";
	private String _appendText = "";
	private String _buttonImage = "";
	private String _buttonText = "...";
	private String _closeText = "Done";
	private String _currentText = "Today";
	private String _dateFormat = "mm/dd/yy";
	private String _nextText = "Next";
	private String _prevText = "Prev";
	private String _showAnim = "show"; //"show","slideDown","fadeIn",""
	private String _showOn = "focus";//"focus","button","both"
	private String _weekHeader = "Wk";
	private String _yearRange = "c-10:c+10";
	private String _yearSuffix = "";

	private int _firstDay = 0;
	private int _showCurrentAtPos = 0;
	private int _stepMonths = 1;

	private boolean _dayNamesModified = false;
	private String [] _dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

	private boolean _dayNamesMinModified = false;
	private String [] _dayNamesMin = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

	private boolean _dayNamesShortModified = false;
	private String [] _dayNamesShort = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

	private boolean _monthNamesModified = false;
	private String [] _monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	private boolean _monthNamesShortModified = false;
	private String [] _monthNamesShort = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

	//TODO: set acceptable values, set acceptable types
	private Mix _defaultDate = new Mix();
	private Mix _duration = new Mix("normal");//"slow","normal","fast"
	private Mix _maxDate = new Mix();
	private Mix _minDate = new Mix();
	private Mix _numberOfMonths = new Mix(1);
	

	
	
	private String _calculateWeek = "";
	
	public String getCalculateWeek() {
		return _calculateWeek;
	}
	
	public void setCalculateWeek(String calculateWeek){
		if(calculateWeek == null || calculateWeek.length() == 0) calculateWeek="";
		if (!_calculateWeek.equals(calculateWeek)) {
			_calculateWeek = calculateWeek;
			smartUpdate("calculateWeek", _calculateWeek);
		}
	}
	
	

	
	String _date="";
	
		
	static {
		addClientEvent(Datepicker.class, Events.ON_SELECTION, CE_IMPORTANT|CE_REPEAT_IGNORE);		
	}

	public String[] getMonthNamesShort(){
		return _monthNamesShort;
	}

	public void setMonthNamesShort(String[] monthNamesShort){		
		if(monthNamesShort == null) monthNamesShort = new String[0];
		if(!Objects.equals(_monthNamesShort, monthNamesShort)){
			_monthNamesShortModified = true;
			_monthNamesShort = monthNamesShort;
			smartUpdate("monthNamesShort", _monthNamesShort);
		}		
	}

	public void setMonthNamesShort(String monthNamesShort){		
		setMonthNamesShort(monthNamesShort.split(","));
	}
	
	public String[] getMonthNames(){
		return _monthNames;
	}

	public void setMonthNames(String[] monthNames){		
		if(monthNames == null) monthNames = new String[0];
		if(!Objects.equals(_monthNames, monthNames)){
			_monthNamesModified = true;
			_monthNames = monthNames;
			smartUpdate("monthNames", _monthNames);
		}		
	}

	public void setMonthNames(String monthNames){		
		setMonthNames(monthNames.split(","));
	}
	
	public String[] getDayNamesShort(){
		return _dayNamesShort;
	}

	public void setDayNamesShort(String[] dayNamesShort){		
		if(dayNamesShort == null) dayNamesShort = new String[0];
		if(!Objects.equals(_dayNamesShort, dayNamesShort)){
			_dayNamesShortModified = true;
			_dayNamesShort = dayNamesShort;
			smartUpdate("dayNamesShort", _dayNamesShort);
		}		
	}

	public void setDayNamesShort(String dayNamesShort){		
		setDayNamesShort(dayNamesShort.split(","));
	}
	
	public String[] getDayNamesMin(){
		return _dayNamesMin;
	}

	public void setDayNamesMin(String[] dayNamesMin){		
		if(dayNamesMin == null) dayNamesMin = new String[0];
		if(!Objects.equals(_dayNamesMin, dayNamesMin)){
			_dayNamesMinModified = true;
			_dayNamesMin = dayNamesMin;
			smartUpdate("dayNamesMin", _dayNamesMin);
		}		
	}

	public void setDayNamesMin(String dayNamesMin){		
		setDayNamesMin(dayNamesMin.split(","));
	}
	
	public String[] getDayNames(){
		return _dayNames;
	}

	public void setDayNames(String[] dayNames){		
		if(dayNames == null) dayNames = new String[0];
		if(!Objects.equals(_dayNames, dayNames)){
			_dayNamesModified = true;
			_dayNames = dayNames;
			smartUpdate("dayNames", _dayNames);
		}		
	}

	public void setDayNames(String dayNames){		
		setDayNames(dayNames.split(","));
	}
	
	public int getStepMonths(){
		return _stepMonths;
	}

	public void setStepMonths(int stepMonths){
		
		if(_stepMonths!=stepMonths){
			_stepMonths = stepMonths;
			smartUpdate("stepMonths",_stepMonths);
		}
	}
	
	public int getShowCurrentAtPos(){
		return _showCurrentAtPos;
	}

	public void setShowCurrentAtPos(int showCurrentAtPos){
		if(_showCurrentAtPos!=showCurrentAtPos){
			_showCurrentAtPos = showCurrentAtPos;
			smartUpdate("showCurrentAtPos",_showCurrentAtPos);
		}
	}

	public int getFirstDay(){
		return _firstDay;
	}

	public void setFirstDay(int firstDay){
		if (firstDay < 0 || firstDay > 6)
			throw new UiException("Illegal firstDay: "+firstDay+". Range: 0 ~ 6");
		
		if(_firstDay!=firstDay){
			_firstDay = firstDay;
			smartUpdate("firstDay",_firstDay);
		}
	}
	
	public String getYearSuffix() {
		return _yearSuffix;
	}

	public void setYearSuffix(String yearSuffix){
		if(yearSuffix == null || yearSuffix.length() == 0) yearSuffix="";
		if (!_yearSuffix.equals(yearSuffix)) {
			_yearSuffix = yearSuffix;
			smartUpdate("yearSuffix", _yearSuffix);
		}
	}
	
	public String getYearRange() {
		return _yearRange;
	}

	public void setYearRange(String yearRange){
		if(yearRange == null || yearRange.length() == 0) yearRange="c-10:c+10";
		if (!_yearRange.equals(yearRange)) {
			_yearRange = yearRange;
			smartUpdate("yearRange", _yearRange);
		}
	}	
	
	public String getWeekHeader() {
		return _weekHeader;
	}

	public void setWeekHeader(String weekHeader){
		if(weekHeader == null || weekHeader.length() == 0) weekHeader="Wk";
		if (!_weekHeader.equals(weekHeader)) {
			_weekHeader = weekHeader;
			smartUpdate("weekHeader", _weekHeader);
		}
	}
	
	public String getShowOn() {
		return _showOn;
	}

	public void setShowOn(String showOn){
		if(showOn == null || showOn.length() == 0) showOn="focus";
		if (!_showOn.equals(showOn)) {
			_showOn = showOn;
			smartUpdate("showOn", _showOn);
		}
	}
	
	public String getShowAnim() {
		return _showAnim;
	}

	public void setShowAnim(String showAnim){
		if(showAnim == null || showAnim.length() == 0) showAnim="show";
		if (!_showAnim.equals(showAnim)) {
			_showAnim = showAnim;
			smartUpdate("showAnim", _showAnim);
		}
	}

	public String getPrevText() {
		return _prevText;
	}

	public void setPrevText(String prevText){
		if(prevText == null || prevText.length() == 0) prevText="Prev";
		if (!_prevText.equals(prevText)) {
			_prevText = prevText;
			smartUpdate("prevText", _prevText);
		}
	}
	
	public String getNextText() {
		return _nextText;
	}

	public void setNextText(String nextText){
		if(nextText == null || nextText.length() == 0) nextText="Next";
		if (!_nextText.equals(nextText)) {
			_nextText = nextText;
			smartUpdate("nextText", _nextText);
		}
	}	
	
	public String getDateFormat() {
		return _dateFormat;
	}

	public void setDateFormat(String dateFormat){
		if(dateFormat == null || dateFormat.length() == 0) dateFormat="mm/dd/yy";
		if (!_dateFormat.equals(dateFormat)) {
			_dateFormat = dateFormat;
			smartUpdate("dateFormat", _dateFormat);
		}
	}
	
	public String getCurrentText() {
		return _currentText;
	}

	public void setCurrentText(String currentText){
		if(currentText == null || currentText.length() == 0) currentText="Today";
		if (!_currentText.equals(currentText)) {
			_currentText = currentText;
			smartUpdate("currentText", _currentText);
		}
	}
	
	public String getCloseText() {
		return _closeText;
	}

	public void setCloseText(String closeText){
		if(closeText == null || closeText.length() == 0) closeText="Done";
		if (!_closeText.equals(closeText)) {
			_closeText = closeText;
			smartUpdate("closeText", _closeText);
		}
	}
	
	public String getButtonText() {
		return _buttonText;
	}

	public void setButtonText(String buttonText){
		if(buttonText == null || buttonText.length() == 0) buttonText="...";
		if (!_buttonText.equals(buttonText)) {
			_buttonText = buttonText;
			smartUpdate("buttonText", _buttonText);
		}
	}
	
	public String getButtonImage() {
		return _buttonImage;
	}

	public void setButtonImage(String buttonImage){
		if(buttonImage == null || buttonImage.length() == 0) buttonImage="";
		if (!_buttonImage.equals(buttonImage)) {
			_buttonImage = buttonImage;
			smartUpdate("buttonImage", _buttonImage);
		}
	}
	
	public String getAppendText() {
		return _appendText;
	}

	public void setAppendText(String appendText){
		if(appendText == null || appendText.length() == 0) appendText="";
		if (!_appendText.equals(appendText)) {
			_appendText = appendText;
			smartUpdate("appendText", _appendText);
		}
	}
	
	public String getAltFormat() {
		return _altFormat;
	}

	public void setAltFormat(String altFormat){
		if(altFormat == null || altFormat.length() == 0) altFormat="";
		if (!_altFormat.equals(altFormat)) {
			_altFormat = altFormat;
			smartUpdate("altFormat", _altFormat);
		}
	}
	
	public String getAltField() {
		return _altField;
	}

	public void setAltField(String altField){
		if(altField == null || altField.length() == 0) altField="";
		if (!_altField.equals(altField)) {
			_altField = altField;
			smartUpdate("altField", _altField);
		}
	}
	
	public boolean getShowWeek() {
		return _showWeek;
	}
	
	public void setShowWeek(boolean showWeek) {
		if(_showWeek != showWeek){
			_showWeek = showWeek;
			smartUpdate("showWeek", _showWeek);					
		}
	}
	
	public boolean getShowOtherMonths() {
		return _showOtherMonths;
	}
	
	public void setShowOtherMonths(boolean showOtherMonths) {
		if(_showOtherMonths != showOtherMonths){
			_showOtherMonths = showOtherMonths;
			smartUpdate("showOtherMonths", _showOtherMonths);					
		}
	}

	public boolean getShowMonthAfterYear() {
		return _showMonthAfterYear;
	}
	
	public void setShowMonthAfterYear(boolean showMonthAfterYear) {
		if(_showMonthAfterYear != showMonthAfterYear){
			_showMonthAfterYear = showMonthAfterYear;
			smartUpdate("showMonthAfterYear", _showMonthAfterYear);					
		}
	}
	
	public boolean getShowButtonPanel() {
		return _showButtonPanel;
	}
	
	public void setShowButtonPanel(boolean showButtonPanel) {
		if(_showButtonPanel != showButtonPanel){
			_showButtonPanel = showButtonPanel;
			smartUpdate("showButtonPanel", _showButtonPanel);					
		}
	}	

	public boolean getSelectOtherMonths() {
		return _selectOtherMonths;
	}
	
	public void setSelectOtherMonths(boolean selectOtherMonths) {
		if(_selectOtherMonths != selectOtherMonths){
			_selectOtherMonths = selectOtherMonths;
			smartUpdate("selectOtherMonths", _selectOtherMonths);					
		}
	}
		
	public boolean getNavigationAsDateFormat() {
		return _navigationAsDateFormat;
	}
	
	public void setNavigationAsDateFormat(boolean navigationAsDateFormat) {
		if(_navigationAsDateFormat != navigationAsDateFormat){
			_navigationAsDateFormat = navigationAsDateFormat;
			smartUpdate("navigationAsDateFormat", _navigationAsDateFormat);					
		}
	}
	
	public boolean getIsRTL() {
		return _isRTL;
	}
	
	public void setIsRTL(boolean isRTL) {
		if(_isRTL != isRTL){
			_isRTL = isRTL;
			smartUpdate("isRTL", _isRTL);					
		}
	}
	
	public boolean getHideIfNoPrevNext() {
		return _hideIfNoPrevNext;
	}
	
	public void setHideIfNoPrevNext(boolean hideIfNoPrevNext) {
		if(_hideIfNoPrevNext != hideIfNoPrevNext){
			_hideIfNoPrevNext = hideIfNoPrevNext;
			smartUpdate("hideIfNoPrevNext", _hideIfNoPrevNext);					
		}
	}
	
	public boolean getGotoCurrent() {
		return _gotoCurrent;
	}
	
	public void setGotoCurrent(boolean gotoCurrent) {
		if(_gotoCurrent != gotoCurrent){
			_gotoCurrent = gotoCurrent;
			smartUpdate("gotoCurrent", _gotoCurrent);					
		}
	}

	public boolean getConstrainInput() {
		return _constrainInput;
	}
	
	public void setConstrainInput(boolean constrainInput) {
		if(_constrainInput != constrainInput){
			_constrainInput = constrainInput;
			smartUpdate("constrainInput", _constrainInput);					
		}
	}

	public boolean getButtonImageOnly() {
		return _buttonImageOnly;
	}
	
	public void setButtonImageOnly(boolean buttonImageOnly) {
		if(_buttonImageOnly != buttonImageOnly){
			_buttonImageOnly = buttonImageOnly;
			smartUpdate("buttonImageOnly", _buttonImageOnly);					
		}
	}
	
	public boolean getAutoSize() {
		return _autoSize;
	}
	
	public void setAutoSize(boolean autoSize) {
		if(_autoSize != autoSize){
			_autoSize = autoSize;
			smartUpdate("autoSize", _autoSize);					
		}
	}
		
	public boolean getDisabled() {
		return _disabled;
	}
	
	public void setDisabled(boolean disabled) {
		if(_disabled != disabled){
			_disabled = disabled;
			smartUpdate("disabled", _disabled);					
		}
	}
	

	public Object getNumberOfMonths() {
		if(_numberOfMonths != null){
			return _numberOfMonths.getValue();
		}
		return 1;
	}
	
	public void setNumberOfMonths(Object numberOfMonths){
		if(_numberOfMonths == null) _numberOfMonths = new Mix(1);
		if(numberOfMonths instanceof String && ((String)numberOfMonths).contains(",")){		
			String[] sa = ((String)numberOfMonths).split(",");
			Integer[] ia = new Integer[sa.length];
			ia[0]=Integer.parseInt(sa[0]);
			ia[1]=Integer.parseInt(sa[1]);
			numberOfMonths = ia;
		}
		_numberOfMonths.setValue(numberOfMonths);
		smartUpdate("numberOfMonths", _numberOfMonths.getValue());
	}

	public Object getDuration() {
		if(_duration != null){
			return _duration.getValue();
		}
		return null;
	}
	
	public void setDuration(Object duration){
		if(_duration == null) _duration = new Mix("normal");
		_duration.setValue(duration);
		smartUpdate("duration", _duration.getValue());
	}
	
	
	public Object getDefaultDate() {
		if(_defaultDate != null){
			return _defaultDate.getValue();
		}
		return null;
	}
	
	public void setDefaultDate(Object defaultDate){
		if(_defaultDate == null) _defaultDate = new Mix();
		_defaultDate.setValue(defaultDate);
		//fixed: why fail??? (missing ] after element list) if Date
		Object val = _defaultDate.getValue();
		if(val instanceof Date){
			Long time = ((Date)val).getTime();
			smartUpdate("defaultDateByDate", time);
		}else{
			smartUpdate("defaultDate", _defaultDate.getValue());
		}
	}

	public Object getMinDate() {
		if(_minDate != null){
			return _minDate.getValue();
		}
		return null;
	}
	
	public void setMinDate(Object minDate){
		if(_minDate == null) _minDate = new Mix();
		_minDate.setValue(minDate);
		Object val = _minDate.getValue();
		if(val instanceof Date){
			Long time = ((Date)val).getTime();
			smartUpdate("minDateByDate", time);
		}else{
			smartUpdate("minDate", _minDate.getValue());
		}				
	}
	
	public Object getMaxDate() {
		if(_maxDate != null){
			return _maxDate.getValue();
		}
		return null;
	}
	
	public void setMaxDate(Object maxDate){
		if(_maxDate == null) _maxDate = new Mix();
		_maxDate.setValue(maxDate);
		Object val = _maxDate.getValue();
		if(val instanceof Date){
			Long time = ((Date)val).getTime();
			smartUpdate("maxDateByDate", time);
		}else{
			smartUpdate("maxDate", _maxDate.getValue());
		}		
	}

	
	public boolean getChangeMonth() {
		return _changeMonth;
	}
	
	public void setChangeMonth(boolean changeMonth) {
		if(_changeMonth != changeMonth){
			_changeMonth = changeMonth;
			smartUpdate("changeMonth", _changeMonth);					
		}
	}

	public boolean getChangeYear() {
		return _changeYear;
	}
	
	public void setChangeYear(boolean changeYear) {	
		if(_changeYear != changeYear){
			_changeYear = changeYear;
			smartUpdate("changeYear", _changeYear);					
		}
	}

	public String getDate() {
		return _date;
	}

/// TODO: how to modify selected date by java?
//	public void setDate(String date) {
//		if (date == null || date.length() == 0)
//			date = "";
//		if (!Objects.equals(date, _date)) {
//			_date = date;
//			smartUpdate("date", _date);
//		}
//	}
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);	
		
		//boolean
		if(_disabled)
			render(renderer, "disabled", _disabled);

		if(_autoSize)
			render(renderer, "autoSize", _autoSize);

		if(_buttonImageOnly)
			render(renderer, "buttonImageOnly", _buttonImageOnly);

		if(_changeMonth)
			render(renderer, "changeMonth", _changeMonth);

		if(_changeYear)
			render(renderer, "changeYear", _changeYear);
		
		if(!_constrainInput)
			renderer.render("constrainInput", _constrainInput);

		if(_gotoCurrent)
			render(renderer, "gotoCurrent", _gotoCurrent);

		if(_hideIfNoPrevNext)
			render(renderer, "hideIfNoPrevNext", _hideIfNoPrevNext);

		if(_isRTL)
			render(renderer, "isRTL", _isRTL);

		if(_navigationAsDateFormat)
			render(renderer, "navigationAsDateFormat", _navigationAsDateFormat);

		if(_selectOtherMonths)
			render(renderer, "selectOtherMonths", _selectOtherMonths);

		if(_showButtonPanel)
			render(renderer, "showButtonPanel", _showButtonPanel);

		if(_showMonthAfterYear)
			render(renderer, "showMonthAfterYear", _showMonthAfterYear);

		if(_showOtherMonths)
			render(renderer, "showOtherMonths", _showOtherMonths);
		
		if(_showWeek)
			render(renderer, "showWeek", _showWeek);

		//String
		if(!_altField.equals(""))
			render(renderer, "altField", _altField);

		if(!_altFormat.equals(""))
			render(renderer, "altFormat", _altFormat);
		
		if(!_appendText.equals(""))
			render(renderer, "appendText", _appendText);

		if(!_buttonImage.equals(""))
			render(renderer, "buttonImage", _buttonImage);
		
		if(!_buttonText.equals("..."))
			render(renderer, "buttonText", _buttonText);

		if(!_closeText.equals("Done"))
			render(renderer, "closeText", _closeText);

		if(!_currentText.equals("Today"))
			render(renderer, "currentText", _currentText);

		if(!_dateFormat.equals("mm/dd/yy"))
			render(renderer, "dateFormat", _dateFormat);
		
		if(!_nextText.equals("Next"))
			render(renderer, "nextText", _nextText);

		if(!_prevText.equals("Prev"))
			render(renderer, "prevText", _prevText);

		if(!_showAnim.equals("show"))
			render(renderer, "showAnim", _showAnim);
		
		if(!_showOn.equals("focus"))
			render(renderer, "showOn", _showOn);

		if(!_weekHeader.equals("Wk"))
			render(renderer, "weekHeader", _weekHeader);

		if(!_yearRange.equals("c-10:c+10"))
			render(renderer, "yearRange", _yearRange);

		if(!_yearSuffix.equals(""))
			render(renderer, "yearSuffix", _yearSuffix);


		//int
		if(_firstDay != 0)
			render(renderer, "firstDay", _firstDay);

		if(_showCurrentAtPos != 0)
			render(renderer, "showCurrentAtPos", _showCurrentAtPos);
		
		if(_stepMonths != 1)
			render(renderer, "stepMonths", _stepMonths);


		//array of strings
		if(_dayNamesModified)
			render(renderer, "dayNames", _dayNames);
		
		if(_dayNamesMinModified)
			render(renderer, "dayNamesMin", _dayNamesMin);

		if(_dayNamesShortModified)
			render(renderer, "dayNamesShort", _dayNamesShort);
		
		if(_monthNamesModified)
			render(renderer, "monthNames", _monthNames);

		if(_monthNamesShortModified)
			render(renderer, "monthNamesShort", _monthNamesShort);
		
		//mixed
		if(_defaultDate != null && _defaultDate.isModified()){				
			render(renderer, "defaultDate", _defaultDate.getValue());
		}
		
		if(_duration != null && _duration.isModified()){				
			render(renderer, "duration", _duration.getValue());
		}

		if(_maxDate != null && _maxDate.isModified()){				
			render(renderer, "maxDate", _maxDate.getValue());
		}

		if(_minDate != null && _minDate.isModified()){				
			render(renderer, "minDate", _minDate.getValue());
		}

		if(_numberOfMonths != null && _numberOfMonths.isModified()){				
			render(renderer, "numberOfMonths", _numberOfMonths.getValue());
		}

		if(!_calculateWeek.equals(""))
			render(renderer, "calculateWeek", _calculateWeek);
		
	}

	
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		if (cmd.equals(Events.ON_SELECTION)) {
			SelectionEvent evt = SelectionEvent.getSelectionEvent(request);
			_date = evt.getSelectedText(); 
					
			Events.postEvent(evt);			
		} else
			super.service(request, everError);
	}	
}
