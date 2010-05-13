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
/**A Datepicker
 * 
 * @author whkuo
 *
 */
public class Datepicker extends XulElement {
	
	//TODO: client should report Date object back to server, not String.
	//It may be affected by date format
	//user report back date in String
	private String _date="";
	
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
	
	/**Returns the calculateWeek
	 * 
	 * @return
	 */
	public String getCalculateWeek() {
		return _calculateWeek;
	}
	
	/**A function to calculate the week of the year for a given date. The default implementation uses the ISO 8601 definition: weeks start on a Monday; the first week of the year contains the first Thursday of the year.
	 * 
	 * @param calculateWeek
	 */
	public void setCalculateWeek(String calculateWeek){
		if(calculateWeek == null || calculateWeek.length() == 0) calculateWeek="";
		if (!_calculateWeek.equals(calculateWeek)) {
			_calculateWeek = calculateWeek;
			smartUpdate("calculateWeek", _calculateWeek);
		}
	}
		
	//TODO. Don't use ON_SELECTION Event.
	//Modify to use customized Event (DateSelectEvent)
	static {
		addClientEvent(Datepicker.class, Events.ON_SELECTION, CE_IMPORTANT|CE_REPEAT_IGNORE);		
	}

	/**Returns the monthNamesShort
	 * 
	 * @return
	 */
	public String[] getMonthNamesShort(){
		return _monthNamesShort;
	}

	/**The list of abbreviated month names, for use as requested via the dateFormat setting. This attribute is one of the regionalisation attributes.
	 * 
	 * @param monthNamesShort
	 */
	public void setMonthNamesShort(String[] monthNamesShort){		
		if(monthNamesShort == null) monthNamesShort = new String[0];
		if(!Objects.equals(_monthNamesShort, monthNamesShort)){
			_monthNamesShortModified = true;
			_monthNamesShort = monthNamesShort;
			smartUpdate("monthNamesShort", _monthNamesShort);
		}		
	}

	/**The list of abbreviated month names, for use as requested via the dateFormat setting. This attribute is one of the regionalisation attributes.
	 * 
	 * @param monthNamesShort
	 */	
	public void setMonthNamesShort(String monthNamesShort){		
		setMonthNamesShort(monthNamesShort.split(","));
	}
	
	/**Returns the monthNames
	 * 
	 * @return
	 */
	public String[] getMonthNames(){
		return _monthNames;
	}

	/**The list of full month names, as used in the month header on each datepicker and as requested via the dateFormat setting. This attribute is one of the regionalisation attributes.
	 * 
	 * @param monthNames
	 */
	public void setMonthNames(String[] monthNames){		
		if(monthNames == null) monthNames = new String[0];
		if(!Objects.equals(_monthNames, monthNames)){
			_monthNamesModified = true;
			_monthNames = monthNames;
			smartUpdate("monthNames", _monthNames);
		}		
	}

	/**The list of full month names, as used in the month header on each datepicker and as requested via the dateFormat setting. This attribute is one of the regionalisation attributes.
	 * 
	 * @param monthNames
	 */
	public void setMonthNames(String monthNames){		
		setMonthNames(monthNames.split(","));
	}
	
	/**Returns the dayNamesShort
	 * 
	 * @return
	 */
	public String[] getDayNamesShort(){
		return _dayNamesShort;
	}

	/**The list of abbreviated day names, starting from Sunday, for use as requested via the dateFormat setting. This attribute is one of the regionalisation attributes.
	 * 
	 * @param dayNamesShort
	 */
	public void setDayNamesShort(String[] dayNamesShort){		
		if(dayNamesShort == null) dayNamesShort = new String[0];
		if(!Objects.equals(_dayNamesShort, dayNamesShort)){
			_dayNamesShortModified = true;
			_dayNamesShort = dayNamesShort;
			smartUpdate("dayNamesShort", _dayNamesShort);
		}		
	}

	/**The list of abbreviated day names, starting from Sunday, for use as requested via the dateFormat setting. This attribute is one of the regionalisation attributes.
	 * 
	 * @param dayNamesShort
	 */
	public void setDayNamesShort(String dayNamesShort){		
		setDayNamesShort(dayNamesShort.split(","));
	}
	
	/**Returns the dayNamesMin
	 * 
	 * @return
	 */
	public String[] getDayNamesMin(){
		return _dayNamesMin;
	}

	/**The list of minimised day names, starting from Sunday, for use as column headers within the datepicker. This attribute is one of the regionalisation attributes.
	 * 
	 * @param dayNamesMin
	 */
	public void setDayNamesMin(String[] dayNamesMin){		
		if(dayNamesMin == null) dayNamesMin = new String[0];
		if(!Objects.equals(_dayNamesMin, dayNamesMin)){
			_dayNamesMinModified = true;
			_dayNamesMin = dayNamesMin;
			smartUpdate("dayNamesMin", _dayNamesMin);
		}		
	}

	/**The list of minimised day names, starting from Sunday, for use as column headers within the datepicker. This attribute is one of the regionalisation attributes.
	 * 
	 * @param dayNamesMin
	 */	
	public void setDayNamesMin(String dayNamesMin){		
		setDayNamesMin(dayNamesMin.split(","));
	}
	
	/**Returns the dayNames
	 * 
	 * @return
	 */
	public String[] getDayNames(){
		return _dayNames;
	}

	/**The list of long day names, starting from Sunday, for use as requested via the dateFormat setting. They also appear as popup hints when hovering over the corresponding column headings. This attribute is one of the regionalisation attributes.
	 * 
	 * @param dayNames
	 */
	public void setDayNames(String[] dayNames){		
		if(dayNames == null) dayNames = new String[0];
		if(!Objects.equals(_dayNames, dayNames)){
			_dayNamesModified = true;
			_dayNames = dayNames;
			smartUpdate("dayNames", _dayNames);
		}		
	}

	/**The list of long day names, starting from Sunday, for use as requested via the dateFormat setting. They also appear as popup hints when hovering over the corresponding column headings. This attribute is one of the regionalisation attributes.
	 * 
	 * @param dayNames
	 */
	public void setDayNames(String dayNames){		
		setDayNames(dayNames.split(","));
	}
	
	/**Returns the stepMonths
	 * 
	 * @return
	 */
	public int getStepMonths(){
		return _stepMonths;
	}

	/**Set how many months to move when clicking the Previous/Next links.
	 * 
	 * @param stepMonths
	 */
	public void setStepMonths(int stepMonths){
		
		if(_stepMonths!=stepMonths){
			_stepMonths = stepMonths;
			smartUpdate("stepMonths",_stepMonths);
		}
	}
	
	/**Returns the showCurrentAtPos
	 * 
	 * @return
	 */
	public int getShowCurrentAtPos(){
		return _showCurrentAtPos;
	}

	/**Specify where in a multi-month display the current month shows, starting from 0 at the top/left.
	 * 
	 * @param showCurrentAtPos
	 */
	public void setShowCurrentAtPos(int showCurrentAtPos){
		if(_showCurrentAtPos!=showCurrentAtPos){
			_showCurrentAtPos = showCurrentAtPos;
			smartUpdate("showCurrentAtPos",_showCurrentAtPos);
		}
	}

	/**Returns the firstDay
	 * 
	 * @return
	 */
	public int getFirstDay(){
		return _firstDay;
	}

	/**Set the first day of the week: Sunday is 0, Monday is 1, ... This attribute is one of the regionalisation attributes.
	 * 
	 * @param firstDay
	 */
	public void setFirstDay(int firstDay){
		if (firstDay < 0 || firstDay > 6)
			throw new UiException("Illegal firstDay: "+firstDay+". Range: 0 ~ 6");
		
		if(_firstDay!=firstDay){
			_firstDay = firstDay;
			smartUpdate("firstDay",_firstDay);
		}
	}
	
	/**Returns the yearSuffix
	 * 
	 * @return
	 */
	public String getYearSuffix() {
		return _yearSuffix;
	}

	/**Additional text to display after the year in the month headers. This attribute is one of the regionalisation attributes.
	 * 
	 * @param yearSuffix
	 */
	public void setYearSuffix(String yearSuffix){
		if(yearSuffix == null || yearSuffix.length() == 0) yearSuffix="";
		if (!_yearSuffix.equals(yearSuffix)) {
			_yearSuffix = yearSuffix;
			smartUpdate("yearSuffix", _yearSuffix);
		}
	}
	
	/**Returns the yearRange
	 * 
	 * @return
	 */
	public String getYearRange() {
		return _yearRange;
	}

	/**Control the range of years displayed in the year drop-down: either relative to today's year (-nn:+nn), relative to the currently selected year (c-nn:c+nn), absolute (nnnn:nnnn), or combinations of these formats (nnnn:-nn).
	 * 
	 * @param yearRange
	 */
	public void setYearRange(String yearRange){
		if(yearRange == null || yearRange.length() == 0) yearRange="c-10:c+10";
		if (!_yearRange.equals(yearRange)) {
			_yearRange = yearRange;
			smartUpdate("yearRange", _yearRange);
		}
	}	
	
	/**Returns the weekHeader
	 * 
	 * @return
	 */
	public String getWeekHeader() {
		return _weekHeader;
	}

	/**The text to display for the week of the year column heading. This attribute is one of the regionalisation attributes. Use showWeek to display this column.
	 * 
	 * @param weekHeader
	 */
	public void setWeekHeader(String weekHeader){
		if(weekHeader == null || weekHeader.length() == 0) weekHeader="Wk";
		if (!_weekHeader.equals(weekHeader)) {
			_weekHeader = weekHeader;
			smartUpdate("weekHeader", _weekHeader);
		}
	}
	
	/**Returns the showOn
	 * 
	 * @return
	 */
	public String getShowOn() {
		return _showOn;
	}

	/**Have the datepicker appear automatically when the field receives focus ('focus'), appear only when a button is clicked ('button'), or appear when either event takes place ('both').
	 * 
	 * @param showOn
	 */
	public void setShowOn(String showOn){
		if(showOn == null || showOn.length() == 0) showOn="focus";
		if (!_showOn.equals(showOn)) {
			_showOn = showOn;
			smartUpdate("showOn", _showOn);
		}
	}
	
	/**Returns the showAnim
	 * 
	 * @return
	 */
	public String getShowAnim() {
		return _showAnim;
	}

	/**Set the name of the animation used to show/hide the datepicker. Use 'show' (the default), 'slideDown', 'fadeIn', any of the show/hide jQuery UI effects, or '' for no animation.
	 * 
	 * @param showAnim
	 */
	public void setShowAnim(String showAnim){
		if(showAnim == null || showAnim.length() == 0) showAnim="show";
		if (!_showAnim.equals(showAnim)) {
			_showAnim = showAnim;
			smartUpdate("showAnim", _showAnim);
		}
	}

	/**Returns the prevText
	 * 
	 * @return
	 */
	public String getPrevText() {
		return _prevText;
	}

	/**The text to display for the previous month link. This attribute is one of the regionalisation attributes. With the standard ThemeRoller styling, this value is replaced by an icon.
	 * 
	 * @param prevText
	 */
	public void setPrevText(String prevText){
		if(prevText == null || prevText.length() == 0) prevText="Prev";
		if (!_prevText.equals(prevText)) {
			_prevText = prevText;
			smartUpdate("prevText", _prevText);
		}
	}
	
	/**Returns the nextText
	 * 
	 * @return
	 */
	public String getNextText() {
		return _nextText;
	}

	/**The text to display for the next month link. This attribute is one of the regionalisation attributes. With the standard ThemeRoller styling, this value is replaced by an icon.
	 * 
	 * @param nextText
	 */
	public void setNextText(String nextText){
		if(nextText == null || nextText.length() == 0) nextText="Next";
		if (!_nextText.equals(nextText)) {
			_nextText = nextText;
			smartUpdate("nextText", _nextText);
		}
	}	
	
	/**Returns the dateFormat
	 * 
	 * @return
	 */
	public String getDateFormat() {
		return _dateFormat;
	}

	/**The format for parsed and displayed dates. This attribute is one of the regionalisation attributes. For a full list of the possible formats see the formatDate function.
	 * 
	 * @param dateFormat
	 */
	public void setDateFormat(String dateFormat){
		if(dateFormat == null || dateFormat.length() == 0) dateFormat="mm/dd/yy";
		if (!_dateFormat.equals(dateFormat)) {
			_dateFormat = dateFormat;
			smartUpdate("dateFormat", _dateFormat);
		}
	}
	
	/**Returns the currentText
	 * 
	 * @return
	 */
	public String getCurrentText() {
		return _currentText;
	}

	/**The text to display for the current day link. This attribute is one of the regionalisation attributes. Use the showButtonPanel to display this button.
	 * 
	 * @param currentText
	 */
	public void setCurrentText(String currentText){
		if(currentText == null || currentText.length() == 0) currentText="Today";
		if (!_currentText.equals(currentText)) {
			_currentText = currentText;
			smartUpdate("currentText", _currentText);
		}
	}
	
	/**Returns the closeText
	 * 
	 * @return
	 */
	public String getCloseText() {
		return _closeText;
	}

	/**The text to display for the close link. This attribute is one of the regionalisation attributes. Use the showButtonPanel to display this button.
	 * 
	 * @param closeText
	 */
	public void setCloseText(String closeText){
		if(closeText == null || closeText.length() == 0) closeText="Done";
		if (!_closeText.equals(closeText)) {
			_closeText = closeText;
			smartUpdate("closeText", _closeText);
		}
	}
	
	/**Returns the buttonText
	 * 
	 * @return
	 */
	public String getButtonText() {
		return _buttonText;
	}

	/**The text to display on the trigger button. Use in conjunction with showOn equal to 'button' or 'both'.
	 * 
	 * @param buttonText
	 */
	public void setButtonText(String buttonText){
		if(buttonText == null || buttonText.length() == 0) buttonText="...";
		if (!_buttonText.equals(buttonText)) {
			_buttonText = buttonText;
			smartUpdate("buttonText", _buttonText);
		}
	}
	
	/**Returns the buttonImage
	 * 
	 * @return
	 */
	public String getButtonImage() {
		return _buttonImage;
	}

	/**The URL for the popup button image. If set, buttonText becomes the alt value and is not directly displayed.
	 * 
	 * @param buttonImage
	 */
	public void setButtonImage(String buttonImage){
		if(buttonImage == null || buttonImage.length() == 0) buttonImage="";
		if (!_buttonImage.equals(buttonImage)) {
			_buttonImage = buttonImage;
			smartUpdate("buttonImage", _buttonImage);
		}
	}
	
	/**Returns the appendText
	 * @return
	 */
	public String getAppendText() {
		return _appendText;
	}

	/**The text to display after each date field, e.g. to show the required format.
	 * @param appendText
	 */
	public void setAppendText(String appendText){
		if(appendText == null || appendText.length() == 0) appendText="";
		if (!_appendText.equals(appendText)) {
			_appendText = appendText;
			smartUpdate("appendText", _appendText);
		}
	}
	
	/**Returns the altFormat
	 * @return
	 */
	public String getAltFormat() {
		return _altFormat;
	}

	/**The dateFormat to be used for the altField option. This allows one date format to be shown to the user for selection purposes, while a different format is actually sent behind the scenes. For a full list of the possible formats see the formatDate function
	 * @param altFormat
	 */
	public void setAltFormat(String altFormat){
		if(altFormat == null || altFormat.length() == 0) altFormat="";
		if (!_altFormat.equals(altFormat)) {
			_altFormat = altFormat;
			smartUpdate("altFormat", _altFormat);
		}
	}
	
	/**Returns the altField
	 * @return
	 */
	public String getAltField() {
		return _altField;
	}

	/**The jQuery selector for another field that is to be updated with the selected date from the datepicker. Use the altFormat setting to change the format of the date within this field. Leave as blank for no alternate field.
	 * @param altField
	 */
	public void setAltField(String altField){
		if(altField == null || altField.length() == 0) altField="";
		if (!_altField.equals(altField)) {
			_altField = altField;
			smartUpdate("altField", _altField);
		}
	}
	
	/**Returns the showWeek
	 * @return
	 */
	public boolean getShowWeek() {
		return _showWeek;
	}
	
	/**When true a column is added to show the week of the year. The calculateWeek option determines how the week of the year is calculated. You may also want to change the firstDay  option.
	 * @param showWeek
	 */
	public void setShowWeek(boolean showWeek) {
		if(_showWeek != showWeek){
			_showWeek = showWeek;
			smartUpdate("showWeek", _showWeek);					
		}
	}
	
	/**Returns the showOtherMonths
	 * @return
	 */
	public boolean getShowOtherMonths() {
		return _showOtherMonths;
	}
	
	/**Display dates in other months (non-selectable) at the start or end of the current month. To make these days selectable use selectOtherMonths.
	 * @param showOtherMonths
	 */
	public void setShowOtherMonths(boolean showOtherMonths) {
		if(_showOtherMonths != showOtherMonths){
			_showOtherMonths = showOtherMonths;
			smartUpdate("showOtherMonths", _showOtherMonths);					
		}
	}

	/**Returns the showMonthAfterYear
	 * @return
	 */
	public boolean getShowMonthAfterYear() {
		return _showMonthAfterYear;
	}
	
	/**Whether to show the month after the year in the header.
	 * @param showMonthAfterYear
	 */
	public void setShowMonthAfterYear(boolean showMonthAfterYear) {
		if(_showMonthAfterYear != showMonthAfterYear){
			_showMonthAfterYear = showMonthAfterYear;
			smartUpdate("showMonthAfterYear", _showMonthAfterYear);					
		}
	}
	
	/**Returns the showButtonPanel
	 * @return
	 */
	public boolean getShowButtonPanel() {
		return _showButtonPanel;
	}
	
	/**Whether to show the button panel.
	 * @param showButtonPanel
	 */
	public void setShowButtonPanel(boolean showButtonPanel) {
		if(_showButtonPanel != showButtonPanel){
			_showButtonPanel = showButtonPanel;
			smartUpdate("showButtonPanel", _showButtonPanel);					
		}
	}	

	/**Returns the selectOtherMonths
	 * @return
	 */
	public boolean getSelectOtherMonths() {
		return _selectOtherMonths;
	}
	
	/**When true days in other months shown before or after the current month are selectable. This only applies if showOtherMonths is also true.
	 * @param selectOtherMonths
	 */
	public void setSelectOtherMonths(boolean selectOtherMonths) {
		if(_selectOtherMonths != selectOtherMonths){
			_selectOtherMonths = selectOtherMonths;
			smartUpdate("selectOtherMonths", _selectOtherMonths);					
		}
	}
		
	/**Returns the navigationAsDateFormat
	 * @return
	 */
	public boolean getNavigationAsDateFormat() {
		return _navigationAsDateFormat;
	}
	
	/**When true the formatDate function is applied to the prevText, nextText, and currentText values before display, allowing them to display the target month names for example.
	 * @param navigationAsDateFormat
	 */
	public void setNavigationAsDateFormat(boolean navigationAsDateFormat) {
		if(_navigationAsDateFormat != navigationAsDateFormat){
			_navigationAsDateFormat = navigationAsDateFormat;
			smartUpdate("navigationAsDateFormat", _navigationAsDateFormat);					
		}
	}
	
	/**Returns the isRTL
	 * @return
	 */
	public boolean getIsRTL() {
		return _isRTL;
	}
	
	/**True if the current language is drawn from right to left. This attribute is one of the regionalisation attributes.
	 * @param isRTL
	 */
	public void setIsRTL(boolean isRTL) {
		if(_isRTL != isRTL){
			_isRTL = isRTL;
			smartUpdate("isRTL", _isRTL);					
		}
	}
	
	/**Returns the hideIfNoPrevNext
	 * @return
	 */
	public boolean getHideIfNoPrevNext() {
		return _hideIfNoPrevNext;
	}
	
	/**Normally the previous and next links are disabled when not applicable (see minDate/maxDate). You can hide them altogether by setting this attribute to true.
	 * @param hideIfNoPrevNext
	 */
	public void setHideIfNoPrevNext(boolean hideIfNoPrevNext) {
		if(_hideIfNoPrevNext != hideIfNoPrevNext){
			_hideIfNoPrevNext = hideIfNoPrevNext;
			smartUpdate("hideIfNoPrevNext", _hideIfNoPrevNext);					
		}
	}
	
	/**Returns the gotoCurrent
	 * @return
	 */
	public boolean getGotoCurrent() {
		return _gotoCurrent;
	}
	
	/**When true the current day link moves to the currently selected date instead of today.
	 * @param gotoCurrent
	 */
	public void setGotoCurrent(boolean gotoCurrent) {
		if(_gotoCurrent != gotoCurrent){
			_gotoCurrent = gotoCurrent;
			smartUpdate("gotoCurrent", _gotoCurrent);					
		}
	}

	/**Returns the constrainInput
	 * @return
	 */
	public boolean getConstrainInput() {
		return _constrainInput;
	}
	
	/**When true entry in the input field is constrained to those characters allowed by the current dateFormat.
	 * @param constrainInput
	 */
	public void setConstrainInput(boolean constrainInput) {
		if(_constrainInput != constrainInput){
			_constrainInput = constrainInput;
			smartUpdate("constrainInput", _constrainInput);					
		}
	}

	/**Returns the buttonImageOnly
	 * @return
	 */
	public boolean getButtonImageOnly() {
		return _buttonImageOnly;
	}
	
	/**Set to true to place an image after the field to use as the trigger without it appearing on a button.
	 * @param buttonImageOnly
	 */
	public void setButtonImageOnly(boolean buttonImageOnly) {
		if(_buttonImageOnly != buttonImageOnly){
			_buttonImageOnly = buttonImageOnly;
			smartUpdate("buttonImageOnly", _buttonImageOnly);					
		}
	}
	
	/**Returns the autoSize
	 * @return
	 */
	public boolean getAutoSize() {
		return _autoSize;
	}
	
	/**Set to true to automatically resize the input field to accomodate dates in the current dateFormat.
	 * @param autoSize
	 */
	public void setAutoSize(boolean autoSize) {
		if(_autoSize != autoSize){
			_autoSize = autoSize;
			smartUpdate("autoSize", _autoSize);					
		}
	}
		
	/**Returns the disabled
	 * @return
	 */
	public boolean getDisabled() {
		return _disabled;
	}
	
	/**Disables (true) or enables (false) the datepicker. Can be set when initialising (first creating) the datepicker.
	 * @param disabled
	 */
	public void setDisabled(boolean disabled) {
		if(_disabled != disabled){
			_disabled = disabled;
			smartUpdate("disabled", _disabled);					
		}
	}
	

	/**Returns the numberOfMonths
	 * @return
	 */
	public Object getNumberOfMonths() {
		if(_numberOfMonths != null){
			return _numberOfMonths.getValue();
		}
		return 1;
	}
	
	/**Set how many months to show at once. The value can be a straight integer, or can be a two-element array to define the number of rows and columns to display.
	 * @param numberOfMonths
	 */
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

	/**Returns the duration
	 * @return
	 */
	public Object getDuration() {
		if(_duration != null){
			return _duration.getValue();
		}
		return null;
	}
	
	/**Control the speed at which the datepicker appears, it may be a time in milliseconds or a string representing one of the three predefined speeds ("slow", "normal", "fast").
	 * @param duration
	 */
	public void setDuration(Object duration){
		if(_duration == null) _duration = new Mix("normal");
		_duration.setValue(duration);
		smartUpdate("duration", _duration.getValue());
	}
	
	
	/**Returns the defaultDate
	 * @return
	 */
	public Object getDefaultDate() {
		if(_defaultDate != null){
			return _defaultDate.getValue();
		}
		return null;
	}
	
	/**Set the date to highlight on first opening if the field is blank. Specify either an actual date via a Date object or as a string in the current dateFormat, or a number of days from today (e.g. +7) or a string of values and periods ('y' for years, 'm' for months, 'w' for weeks, 'd' for days, e.g. '+1m +7d'), or null for today.
	 * @param defaultDate
	 */
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

	/**Returns the minDate
	 * @return
	 */
	public Object getMinDate() {
		if(_minDate != null){
			return _minDate.getValue();
		}
		return null;
	}
	
	/**Set a minimum selectable date via a Date object or as a string in the current dateFormat, or a number of days from today (e.g. +7) or a string of values and periods ('y' for years, 'm' for months, 'w' for weeks, 'd' for days, e.g. '-1y -1m'), or null for no limit.
	 * @param minDate
	 */
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
	
	/**Returns the maxDate
	 * @return
	 */
	public Object getMaxDate() {
		if(_maxDate != null){
			return _maxDate.getValue();
		}
		return null;
	}
	
	/**Set a maximum selectable date via a Date object or as a string in the current dateFormat, or a number of days from today (e.g. +7) or a string of values and periods ('y' for years, 'm' for months, 'w' for weeks, 'd' for days, e.g. '+1m +1w'), or null for no limit.
	 * @param maxDate
	 */
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

	
	/**Returns the changeMonth
	 * @return
	 */
	public boolean getChangeMonth() {
		return _changeMonth;
	}
	
	/**Allows you to change the month by selecting from a drop-down list. You can enable this feature by setting the attribute to true.
	 * @param changeMonth
	 */
	public void setChangeMonth(boolean changeMonth) {
		if(_changeMonth != changeMonth){
			_changeMonth = changeMonth;
			smartUpdate("changeMonth", _changeMonth);					
		}
	}

	/**Returns the changeYear
	 * @return
	 */
	public boolean getChangeYear() {
		return _changeYear;
	}
	
	/**Allows you to change the year by selecting from a drop-down list. You can enable this feature by setting the attribute to true.
	 * @param changeYear
	 */
	public void setChangeYear(boolean changeYear) {	
		if(_changeYear != changeYear){
			_changeYear = changeYear;
			smartUpdate("changeYear", _changeYear);					
		}
	}

	/**Returns the date user choosed
	 * @return
	 */
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


	/**
	 * Processes an AU request.
	 * 
	 */		
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		if (cmd.equals(Events.ON_SELECTION)) {
			//TODO: modify according to Date object
			SelectionEvent evt = SelectionEvent.getSelectionEvent(request);
			_date = evt.getSelectedText(); 
				
			Events.postEvent(evt);			
		} else
			super.service(request, everError);
	}	
}
