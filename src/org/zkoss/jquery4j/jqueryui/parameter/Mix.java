package org.zkoss.jquery4j.jqueryui.parameter;

import java.lang.reflect.Array;
import java.util.Date;

public class Mix {
	public enum Type {
		DATE,NUMBER,STRING,BOOLEAN,ARRAY
	}

	Date _date;
	int _number;
	String _string;
	boolean _boolean;
	Object[] _array;
	
	Type _type;	
	
	boolean _modified = false;
	//Array is hard to implement....
	//Need extension anytime....
	
	//default null
	public Mix(){}
	
	//default not null
	public Mix(Object value){		
		setValue(value);
		_modified = false;
	}
	
	//TODO:
	//default value?
	//acceptable value? and throw exception?
	
	//TODO:
	//limit acceptable type
	
	public void setValue(Object value) {
		if (value instanceof Date) {
			_date = (Date) value;
			_type = Type.DATE;
			_modified = true;
		} else if (value instanceof Integer) {
			// does "+7" get coerced to be int 7? no
			_number = (Integer) value;
			_type = Type.NUMBER;
			_modified = true;
		} else if (value instanceof Object[]){
			//int[] is Object, but not Object[]? Integer[] is Object, and is Object[]? 
			_array = (Object[]) value;
			_type = Type.ARRAY;
			_modified = true;			
		} else if (value instanceof int[]){
			int length = ((int[])value).length;
			_array = new Integer[length];
			for(int i =0;i<length;i++){
				_array[i] = Integer.valueOf(((int[])value)[i]);
			}
			_type = Type.ARRAY;
			_modified = true;						
		} else if (value instanceof String) {
			// "+7" goes to string, "2" goes to String . Should parse to int before here???? 
			try{
				int i = Integer.parseInt((String) value);
				_number = i;
				_type = Type.NUMBER;
				_modified = true;
				
			}catch(NumberFormatException e){
				String s = (String) value;
				if(s.equals("true")||s.equals("false")){
					_boolean = s.equals("true");
					_type = Type.BOOLEAN;
					_modified = true;
				}else{
					_string = (String) value;				
					_type = Type.STRING;
					_modified = true;
				}
			}			
		}
	}

	public Object getValue() {
		switch (_type) {
		case DATE:
			return _date;
		case NUMBER:
			return _number;
		case STRING:
			return _string;
		case BOOLEAN:
			return _boolean;
		case ARRAY:
			return _array;
		}
		return null;
	}

	public boolean isModified() {
		return _modified;
	}
}