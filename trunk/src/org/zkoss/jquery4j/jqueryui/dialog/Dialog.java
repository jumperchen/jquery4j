/* Dialog.java

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

package org.zkoss.jquery4j.jqueryui.dialog;

import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zul.impl.XulElement;

public class Dialog extends XulElement {
	String _title = "";
	String _content = "";

	public String getTitle() {
		return _title;
	}
	
	public void setTitle(String title){	
		if (title == null) title = "";
		if (!Objects.equals(_title, title)) {
			_title = title;
			smartUpdate("title", _title);
		}		
	}

	public String getContent() {
		return _content;
	}
	
	public void setContent(String content){
		if (content == null) content = "";
		if (!Objects.equals(_content, content)) {
			_content = content;
			smartUpdate("content", _content);
		}		
	}
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);

		if(!_title.equals(""))
			render(renderer, "title", _title);

		if(!_content.equals(""))
			render(renderer, "content", _content);		
	}		
}
